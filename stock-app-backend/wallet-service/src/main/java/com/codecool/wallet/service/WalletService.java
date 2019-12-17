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

    private void setWallet(Transaction transaction) {
        StockAppUser user = userCaller.getUser(transaction.getStockAppUserId());
        if (isCryptoInWallet(transaction)) {
            updateWallet(transaction);
        } else {
            createWallet(transaction);
        }
    }

    private void updateWallet(Transaction transaction) {

        Wallet wallet = walletRepository.findWalletBySymbolAndStockAppUserId(transaction.getSymbol(), transaction.getStockAppUserId());

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

        //TODO API service
        //double currentPrice = currencyAPIService.getSingleCurrencyPrice(transaction.getCurrencyId());
        double currentPrice = 5;

        wallet.setUsdValue(wallet.getTotalAmount() * currentPrice);

        walletRepository.updateWallet(
                wallet.getAvailableAmount(),
                wallet.getInOrder(),
                wallet.getTotalAmount(),
                wallet.getUsdValue(),
                wallet.getSymbol());
    }

    private boolean isCryptoInWallet(Transaction transaction) {

        return walletRepository.getAllByStockAppUserId(transaction.getStockAppUserId())
                .stream()
                .anyMatch(x -> x.getSymbol()
                        .equals(transaction.getSymbol()));
    }

    private void createWallet(Transaction transaction) {
        Wallet wallet = Wallet.builder()
                .availableAmount(transaction.getAmount())
                .symbol(transaction.getSymbol())
                .totalAmount(transaction.getAmount())
                .inOrder(0)
                .usdValue(transaction.getAmount() * transaction.getPrice())
                .stockAppUserId(transaction.getStockAppUserId())
                .build();

        walletRepository.save(wallet);
    }
}
