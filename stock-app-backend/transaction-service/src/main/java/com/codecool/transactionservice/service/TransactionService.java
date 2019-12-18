package com.codecool.transactionservice.service;

import com.codecool.transactionservice.model.Util;
import com.codecool.transactionservice.model.currency.CryptoCurrency;
import com.codecool.transactionservice.model.currency.CurrencyDetails;
import com.codecool.transactionservice.model.currency.SingleCurrency;
import com.codecool.transactionservice.repository.TransactionRepository;
import com.codecool.transactionservice.model.transaction.OpenTransaction;
import com.codecool.transactionservice.model.transaction.Transaction;
import com.codecool.transactionservice.model.transaction.TransactionType;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserCaller userCaller;

    @Autowired
    private  WalletCaller walletCaller;

    @Autowired
    private CurrencyCaller currencyCaller;

    @Transactional
    public boolean buy(Transaction transaction, long userId) {
        transaction.setStockAppUserId(userId);

        if (checkBalance(transaction)) {
            if (isTransactionExecutable(transaction)) {
                saveTransactionWithDetails(transaction, true);
                walletCaller.setWallet(transaction);
            } else {
                saveTransactionWithDetails(transaction, false);
                walletCaller.setWallet(transaction);
            }
            return true;
        }
        return false;
    }

    @Transactional
    public boolean sell(Transaction transaction, long userId) {
        transaction.setStockAppUserId(userId);

        if (checkAmount(transaction)) {
            if (this.isTransactionExecutable(transaction)) {
                this.saveTransactionWithDetails(transaction, true);
                walletCaller.updateWallet(transaction);
            } else {
                this.saveTransactionWithDetails(transaction, false);
                walletCaller.updateWallet(transaction);
            }
            return true;
        }
        return false;
    }

    private void saveTransactionWithDetails(Transaction transaction, boolean isTransactionClosed) {
        transaction.setDate(Util.getCurrentDate());
        transaction.setClosedTransaction(isTransactionClosed);
        transactionRepository.saveAndFlush(transaction);

        this.modifyUserBalanceByTransactionTotal(transaction);
    }

    private void modifyUserBalanceByTransactionTotal(Transaction transaction) {
        double balance;

        if (transaction.getTransactionType().equals(TransactionType.BUY)) {
            balance = userCaller.getBalance(transaction.getStockAppUserId()) - transaction.getTotal();
        } else {
            balance = userCaller.getBalance(transaction.getStockAppUserId()) + transaction.getTotal();
        }
        userCaller.updateBalance(balance, transaction.getStockAppUserId());
    }

    public boolean isTransactionExecutable(Transaction transaction) {
        double currentPrice = currencyCaller.getSingleCurrencyPrice(transaction.getCurrencyId());

        if (transaction.getTransactionType().equals(TransactionType.BUY)) {
            return transaction.getPrice() >= currentPrice;
        } else if (transaction.getTransactionType().equals(TransactionType.SELL)) {
            return transaction.getPrice() <= currentPrice;
        }
        return false;
    }

    @Transactional
    //TODO replace this
    @Scheduled(fixedDelay = 60000)
    public void scanOpenOrders() {

        List<Transaction> openTransactions = transactionRepository.findAllByClosedTransactionFalse();
        openTransactions.forEach(transaction -> {
            if (isTransactionExecutable(transaction)) {
                transactionRepository.closeTransaction(transaction.getId());
            }
        });
    }

    public List<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

    public CryptoCurrency getCurrencies(String sortBy, String sortDir) {
        return currencyCaller.getCurrencies(sortBy, sortDir);
    }

    public CurrencyDetails getCurrencyById(long id) {
        SingleCurrency currency = currencyCaller.getCurrency(id);
        return currency.getData().get(id);
    }

    private boolean checkBalance(Transaction transaction) {
        return (transaction.getTotal() < userCaller.getBalance((transaction.getStockAppUserId())));
    }

    private boolean checkAmount(Transaction transaction) {
        return (transaction.getAmount() <= walletCaller.getWallet(transaction.getStockAppUserId(),transaction.getSymbol())
                .getAvailableAmount());
    }

    public List<OpenTransaction> getOpenTransactions(Long userId) {
        List<OpenTransaction> openTransactions = new ArrayList<>();
        List<Transaction> transactions = transactionRepository.getTransactionsByUserIdAndTransactionType(userId, false);

        for (Transaction transaction : transactions) {
            OpenTransaction openTransaction = new OpenTransaction();
            BeanUtils.copyProperties(transaction, openTransaction);
            Long id = transaction.getCurrencyId();
            Double currentPrice = currencyCaller.getSingleCurrencyPrice(id);
            openTransaction.setCurrentPrice(currentPrice);
            openTransactions.add(openTransaction);
        }
        return openTransactions;
    }

    public List<Transaction> getTransactionHistoryByUserId(Long userId) {
        return transactionRepository.getTransactionsByUserIdAndTransactionType(userId, true);
    }
}
