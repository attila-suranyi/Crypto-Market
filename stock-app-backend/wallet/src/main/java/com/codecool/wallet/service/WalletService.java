package com.codecool.wallet.service;

import com.codecool.wallet.model.StockAppUser;
import com.codecool.wallet.model.Transaction;
import com.codecool.wallet.model.TransactionType;
import com.codecool.wallet.model.Wallet;
import com.codecool.wallet.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class WalletService {

    @Autowired
    private UserCaller userCaller;

    @Autowired
    private WalletRepository walletRepository;

    private void setWallet(Transaction transaction, long userId) {
        StockAppUser user =userCaller.getUser(userId);
        if (isCryptoInWallet(transaction, user)) {
            updateWallet(transaction);
        } else {
            createWallet(transaction, user);
        }
    }

    private void updateWallet(Transaction transaction) {
        Long userId = transaction.getStockAppUserId();
        StockAppUser user =userCaller.getUser(userId);


        Wallet wallet = user.getWallet().stream()
                .filter(x->x.getSymbol().equals(transaction.getSymbol()))
                .findFirst()
                .orElseThrow(NoSuchElementException::new);

        if (transaction.getTransactionType().equals(TransactionType.BUY) && transaction.isClosedTransaction()) {
            wallet.setTotalAmount(wallet.getTotalAmount() + transaction.getAmount());
        } else if (transaction.getTransactionType().equals(TransactionType.SELL) && transaction.isClosedTransaction()) {
            wallet.setTotalAmount(wallet.getTotalAmount() - transaction.getAmount());
        } else if (transaction.getTransactionType().equals(TransactionType.BUY) && !transaction.isClosedTransaction()) {
            //doesnt need any modification
        } else if (transaction.getTransactionType().equals(TransactionType.SELL) && !transaction.isClosedTransaction()) {
            wallet.setTotalAmount(wallet.getTotalAmount());
            wallet.setInOrder(wallet.getInOrder() + transaction.getAmount());
        }

        wallet.setAvailableAmount(wallet.getTotalAmount() - wallet.getInOrder());

        double currentPrice = currencyAPIService.getSingleCurrencyPrice(transaction.getCurrencyId());

        wallet.setUsdValue(wallet.getTotalAmount() * currentPrice);

        walletRepository.updateWallet(
                wallet.getAvailableAmount(),
                wallet.getInOrder(),
                wallet.getTotalAmount(),
                wallet.getUsdValue(),
                wallet.getSymbol());

    }

    private boolean isCryptoInWallet(Transaction transaction, StockAppUser user) {
        return walletRepository.getWalletsByStockAppUser(user)
                .stream()
                .anyMatch(x -> x.getSymbol()
                        .equals(transaction.getSymbol()));
    }

    private void createWallet(Transaction transaction, StockAppUser user) {
        Wallet wallet = Wallet.builder()
                .availableAmount(transaction.getAmount())
                .symbol(transaction.getSymbol())
                .totalAmount(transaction.getAmount())
                .inOrder(0)
                .usdValue(transaction.getAmount() * transaction.getPrice())
                .stockAppUser(user)
                .build();

        walletRepository.save(wallet);
    }
}
