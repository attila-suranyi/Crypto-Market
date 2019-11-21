package com.codecool.stockapp.service;

import com.codecool.stockapp.model.Util;
import com.codecool.stockapp.model.entity.User;
import com.codecool.stockapp.model.entity.Wallet;
import com.codecool.stockapp.model.entity.currency.CryptoCurrency;
import com.codecool.stockapp.model.entity.currency.CurrencyDetails;
import com.codecool.stockapp.model.entity.currency.SingleCurrency;
import com.codecool.stockapp.model.entity.transaction.OpenTransaction;
import com.codecool.stockapp.model.entity.transaction.Transaction;
import com.codecool.stockapp.model.entity.transaction.TransactionType;
import com.codecool.stockapp.model.repository.TransactionRepository;
import com.codecool.stockapp.model.repository.UserRepository;
import com.codecool.stockapp.model.repository.WalletRepository;
import com.codecool.stockapp.service.api.CurrencyAPIService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import java.util.ArrayList;
import java.util.List;

@Service
public class Trader {

    @Autowired
    private CurrencyAPIService currencyAPIService;

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

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
                setWallet(transaction, userId);
            } else {
                this.saveTransactionWithDetails(transaction, false);
                setWallet(transaction, userId);
            }
            return true;
        }
        return false;
    }

    @Transactional
    public boolean sell(Transaction transaction, long userId) {
        transaction.setUser(userRepository.findById(userId));
        this.saveTransactionWithDetails(transaction, true);
        setWallet(transaction, userId);
        return true;
    }

    private void saveTransactionWithDetails(Transaction transaction, boolean isTransactionClosed) {
        transaction.setDate(Util.getCurrentDate());
        transaction.setClosedTransaction(isTransactionClosed);
        transactionRepository.saveAndFlush(transaction);

        this.modifyUserBalanceByTransactionTotal(transaction);
    }


    private void setWallet(Transaction transaction, long userId) {
        User user = userRepository.findById(userId);
        if (isCryptoInWallet(transaction, user)) {
            Wallet updatableWallet = walletRepository.getWalletsByUser(user).stream()
                            .filter(x -> x.getSymbol()
                            .equals(transaction.getSymbol()))
                            .findFirst()
                            .get();

            updateWallet(transaction, updatableWallet, user);

        } else {
            createWallet(transaction, user);
        }
    }

    private void updateWallet(Transaction transaction, Wallet wallet, User user) {
        if (transaction.getTransactionType().equals(TransactionType.BUY)) {
            wallet.setTotalAmount(wallet.getTotalAmount() + transaction.getAmount());
        } else {
            wallet.setTotalAmount(wallet.getTotalAmount() - transaction.getAmount());
        }
        wallet.setInOrder(
                wallet.getTotalAmount() - transactionRepository.findAllByClosedTransactionFalseAndUserId(user.getId()).stream()
                        .filter(x -> x.getSymbol().equals(transaction.getSymbol()))
                        .mapToDouble(Transaction::getAmount)
                        .sum());
        wallet.setAvailableAmount(wallet.getAvailableAmount() - wallet.getInOrder());

        walletRepository.updateWallet(
                wallet.getTotalAmount(),
                wallet.getAvailableAmount(),
                wallet.getInOrder(),
                wallet.getSymbol());
    }

    private boolean isCryptoInWallet(Transaction transaction, User user) {
        return walletRepository.getWalletsByUser(user)
                .stream()
                .anyMatch(x -> x.getSymbol()
                        .equals(transaction.getSymbol()));
    }

    private void createWallet(Transaction transaction, User user) {
        Wallet wallet = Wallet.builder()
                .availableAmount(transaction.getAmount())
                .symbol(transaction.getSymbol())
                .totalAmount(transaction.getAmount())
                .inOrder(0)
                .user(user)
                .build();

        walletRepository.save(wallet);
    }

    private void modifyUserBalanceByTransactionTotal(Transaction transaction) {
        double balance;

        if (transaction.getTransactionType().equals(TransactionType.BUY)) {
            balance = transaction.getUser().getBalance() - transaction.getTotal();
        } else {
            balance = transaction.getUser().getBalance() + transaction.getTotal();
        }
        userRepository.updateBalance(balance, transaction.getUser().getId());
    }

    public boolean isTransactionExecutable(Transaction transaction) {
        double currentPrice = currencyAPIService.getSingleCurrencyPrice(transaction.getCurrencyId());

        if (transaction.getTransactionType().equals(TransactionType.BUY)) {
            return transaction.getPrice() >= currentPrice;
        } else if (transaction.getTransactionType().equals(TransactionType.SELL)) {
            return transaction.getPrice() <= currentPrice;
        }
        return false;
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
                            if (this.trader.isTransactionExecutable(transaction)) {
                                transactionRepository.closeTransaction(transaction.getId());
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

    private boolean checkBalance(Transaction transaction) {
        return (transaction.getTotal() < transaction.getUser().getBalance());
    }

    //TODO mutyizás ezerrel
    public List<OpenTransaction> getOpenTransactions(Long userId) {
        List<OpenTransaction> openTransactions = new ArrayList<>();
        List<Transaction> transactions = transactionRepository.getOpenTransactionsByUserId(userId);

        for (Transaction transaction : transactions) {
            OpenTransaction openTransaction = new OpenTransaction();
            BeanUtils.copyProperties(transaction, openTransaction);
            Long id = transaction.getCurrencyId();
            Double currentPrice = currencyAPIService.getSingleCurrencyPrice(id);
            openTransaction.setCurrentPrice(currentPrice);
            openTransactions.add(openTransaction);
        }
        return openTransactions;
    }

    public List<Transaction> getTransactionHistoryByUserId(Long userId) {
        return transactionRepository.getClosedTransactionsByUserId(userId);
    }

    public List<Wallet> getWallet(long id) {
        User user = userRepository.findById(id);
        return walletRepository.getWalletsByUser(user);
    }
}
