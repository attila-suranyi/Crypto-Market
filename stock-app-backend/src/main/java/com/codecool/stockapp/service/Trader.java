package com.codecool.stockapp.service;

import com.codecool.stockapp.model.Util;
import com.codecool.stockapp.model.entity.User;
import com.codecool.stockapp.model.entity.Wallet;
import com.codecool.stockapp.model.entity.currency.CryptoCurrency;
import com.codecool.stockapp.model.entity.currency.CurrencyDetails;
import com.codecool.stockapp.model.entity.currency.SingleCurrency;
import com.codecool.stockapp.model.entity.transaction.Transaction;
import com.codecool.stockapp.model.entity.transaction.TransactionType;
import com.codecool.stockapp.model.repository.TransactionRepository;
import com.codecool.stockapp.model.repository.UserRepository;
import com.codecool.stockapp.model.repository.WalletRepository;
import com.codecool.stockapp.service.api.CurrencyAPIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class Trader {

    @Autowired
    CurrencyAPIService currencyAPIService;

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    WalletRepository walletRepository;

    public Trader() {
    }

    //TODO gives back boolean, return the value to the frontend and rename this method according to this
    @Transactional
    public boolean buy(Transaction transaction, long userId) {
        transaction.setUser(userRepository.findById(userId));

        if (checkBalance(transaction)) {
            if (this.isTransactionExecutable(transaction)) {
                this.saveTransactionWithDetails(transaction, true);
            } else {
                this.saveTransactionWithDetails(transaction, false);
            }
            System.out.println(transactionRepository.findAll());
            return true;
        }
        return false;
    }
    @Transactional
    public boolean sell(Transaction transaction, long userId) {
        transaction.setUser(userRepository.findById(userId));
        this.saveTransactionWithDetails(transaction, true);
        return true;
    }

    private void saveTransactionWithDetails(Transaction transaction, boolean isTransactionClosed) {
        transaction.setDate(Util.getCurrentDate());
        transaction.setClosedTransaction(isTransactionClosed);
        transactionRepository.saveAndFlush(transaction);

        this.modifyUserBalanceByTransactionTotal(transaction);
    }

    //TODO make it usable for sell as well
    //TODO save transaction with new state(closed or not)
    private void modifyUserBalanceByTransactionTotal(Transaction transaction) {
        if (transaction.getTransactionType().equals(TransactionType.BUY)) {
            double balance = transaction.getUser().getBalance() - transaction.getTotal();
            userRepository.updateBalance(balance, transaction.getUser().getId());
            transactionRepository.closeTransaction(transaction.getId());
        } else {
            double balance = transaction.getUser().getBalance() + transaction.getTotal();
            userRepository.updateBalance(balance, transaction.getUser().getId());
            transactionRepository.closeTransaction(transaction.getId());
        }
    }

    public boolean isTransactionExecutable(Transaction transaction) {
        double currentPrice = currencyAPIService.getSingleCurrencyPrice(transaction.getCurrencyId());

        return (transaction.getTransactionType().equals(TransactionType.BUY) && transaction.getPrice() >= currentPrice ||
                transaction.getTransactionType().equals(TransactionType.SELL) && transaction.getPrice() <= currentPrice) &&
                this.checkBalance(transaction);
    }

    //TODO review this snippet
    public void scanOpenOrders() {
        new java.util.Timer().schedule(
                new java.util.TimerTask() {

                    @Autowired
                    Trader trader;

                    @Override
                    public void run() {
                        List<Transaction> openTransactions = transactionRepository.findAllByClosedTransactionFalse();
                        openTransactions.forEach( transaction -> {
                            if (trader.isTransactionExecutable(transaction)  && trader.checkBalance(transaction)) {
                                trader.modifyUserBalanceByTransactionTotal(transaction);
                            }
                        });
                        scanOpenOrders();
                    }
                },
                60000
        );
    }

    public List<Transaction> getTransactions() {
        return transactionRepository.findAll();
    }

    public CryptoCurrency getCurrencies(String sortBy, String sortDir) {
        return currencyAPIService.getCurrencies(sortBy, sortDir);
    }

    public CurrencyDetails getCurrencyById(long id) {
        SingleCurrency currency = currencyAPIService.getSingleCurrency(id);
        return currency.getData().get(id);
    }

    //TODO call in isTransactionExecutable, since they always work together
    private boolean checkBalance(Transaction transaction) {
        return (transaction.getTotal() < transaction.getUser().getBalance());
    }

    public Wallet getWallet(long id) {
        User user = userRepository.findById(id);
        return walletRepository.getWalletByUser(user);
    }
}
