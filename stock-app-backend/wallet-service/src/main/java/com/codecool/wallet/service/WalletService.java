package com.codecool.wallet.service;

import com.codecool.cryptomarketjsonclasses.model.transaction.Transaction;
import com.codecool.cryptomarketjsonclasses.model.transaction.TransactionType;
import com.codecool.cryptomarketjsonclasses.model.Wallet;
import com.codecool.wallet.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class WalletService {

    @Autowired
    private UserCaller userCaller;

    @Autowired
    private CurrencyCaller currencyCaller;

    @Autowired
    private WalletRepository walletRepository;

    public void setWallet(Transaction transaction) {
        if (isCryptoInWallet(transaction)) {
            updateWallet(transaction);
        } else {
            createWallet(transaction);
        }
    }

    public void updateWallet(Transaction transaction) {

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


        double currentPrice = currencyCaller.getSingleCurrencyPrice(transaction.getCurrencyId());

        wallet.setUsdValue(wallet.getTotalAmount() * currentPrice);

        walletRepository.updateWallet(
                wallet.getAvailableAmount(),
                wallet.getInOrder(),
                wallet.getTotalAmount(),
                wallet.getUsdValue(),
                wallet.getSymbol());
    }

    public boolean isCryptoInWallet(Transaction transaction) {

        return walletRepository.getAllByStockAppUserId(transaction.getStockAppUserId())
                .stream()
                .anyMatch(x -> x.getSymbol()
                        .equals(transaction.getSymbol()));
    }

    public void createWallet(Transaction transaction) {
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
