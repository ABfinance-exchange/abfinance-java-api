package com.abfinance.api.examples.http.sync;

import com.abfinance.api.client.config.ABFinanceApiConfig;
import com.abfinance.api.client.domain.account.request.AccountDataRequest;
import com.abfinance.api.client.domain.account.request.BatchSetCollateralCoinRequest;
import com.abfinance.api.client.domain.account.AccountType;
import com.abfinance.api.client.domain.account.CollateralSwitch;
import com.abfinance.api.client.domain.CategoryType;
import com.abfinance.api.client.domain.account.request.SetCollateralCoinRequest;
import com.abfinance.api.client.service.ABFinanceApiClientFactory;

import java.util.Arrays;

/**
 * Account API Examples
 * Demonstrates all account-related API endpoints
 */
public class AccountExample {
    public static void main(String[] args) {
        var client = ABFinanceApiClientFactory.newInstance("efiuofAdYrZoyPxA7y", "rPTv8JsneQt3HvpzZOl3ZTB2H2KA6He9Hmim", ABFinanceApiConfig.TESTNET_DOMAIN, true).newAccountRestClient();

        // 1. Get Wallet Balance
        // Query the wallet balance for UNIFIED account
        System.out.println("========== Get Wallet Balance ==========");
        var walletBalanceRequest = AccountDataRequest.builder()
                .accountType(AccountType.UNIFIED)
                .build();
        var walletBalanceData = client.getWalletBalance(walletBalanceRequest);
        System.out.println("Wallet Balance: " + walletBalanceData);

        // 2. Get Account Borrow History
        // Query account borrow history with optional filters
        System.out.println("\n========== Get Borrow History ==========");
        var accountBorrowHistoryRequest = AccountDataRequest.builder()
                .currency("USDT") // Optional: filter by currency
                .limit(50) // Optional: limit results
                .build();
        var accountBorrowData = client.getAccountBorrowHistory(accountBorrowHistoryRequest);
        System.out.println("Borrow History: " + accountBorrowData);

        // 3. Get Account Info
        // Query account configuration information
        System.out.println("\n========== Get Account Info ==========");
        var accountInfo = client.getAccountInfo();
        System.out.println("Account Info: " + accountInfo);

        // 4. Get Transaction Log
        // Query transaction logs with filters
        System.out.println("\n========== Get Transaction Log ==========");
        var transactionLogRequest = AccountDataRequest.builder()
                .accountType(AccountType.UNIFIED)
                .category(CategoryType.SPOT) // Optional: filter by category
                .limit(20)
                .build();
        var transactionLogData = client.getTransactionLog(transactionLogRequest);
        System.out.println("Transaction Log: " + transactionLogData);

        // 5. Get Account Fee Rate
        // Query trading fee rate
        System.out.println("\n========== Get Fee Rate ==========");
        var feeRateRequest = AccountDataRequest.builder()
                .category(CategoryType.SPOT)
                .symbol("BTCUSDT") // Optional: specific symbol
                .build();
        var feeRateData = client.getAccountFreeRate(feeRateRequest);
        System.out.println("Fee Rate: " + feeRateData);

        // 6. Get Collateral Info
        // Query collateral information for all coins
        System.out.println("\n========== Get Collateral Info ==========");
        var collateralInfoRequest = AccountDataRequest.builder()
                .currency("BTC") // Optional: specific currency
                .build();
        var collateralInfo = client.getAccountCollateralInfo(collateralInfoRequest);
        System.out.println("Collateral Info: " + collateralInfo);

        // 7. Set Collateral Coin (Single)
        // Enable or disable a single coin as collateral
        System.out.println("\n========== Set Collateral Coin ==========");
        var setCollateralRequest = AccountDataRequest.builder()
                .coin("BTC")
                .collateralSwitch(CollateralSwitch.ON) // ON or OFF
                .build();
        var setCollateralResult = client.setAccountCollateralCoin(setCollateralRequest);
        System.out.println("Set Collateral Result: " + setCollateralResult);

        // 8. Batch Set Collateral Coins
        // Set multiple coins as collateral in one request
        System.out.println("\n========== Batch Set Collateral Coins ==========");
        var batchSetCollateralRequest = BatchSetCollateralCoinRequest.builder()
                .request(Arrays.asList(
                        SetCollateralCoinRequest.builder().coin("BTC").collateralSwitch(CollateralSwitch.ON.name()).build(),
                        SetCollateralCoinRequest.builder().coin("ETH").collateralSwitch(CollateralSwitch.ON.name()).build(),
                        SetCollateralCoinRequest.builder().coin("USDT").collateralSwitch(CollateralSwitch.ON.name()).build()
                ))
                .build();
        var batchSetCollateralResult = client.batchSetAccountCollateralCoin(batchSetCollateralRequest);
        System.out.println("Batch Set Collateral Result: " + batchSetCollateralResult);

        // 9. Get MMP State (Market Maker Protection)
        // Query MMP state for market makers
        System.out.println("\n========== Get MMP State ==========");
        var mmpStateRequest = AccountDataRequest.builder()
                .baseCoin("BTC")
                .build();
        var mmpState = client.getAccountMMPState(mmpStateRequest);
        System.out.println("MMP State: " + mmpState);

        // 10. Get SMP Group (Self-Match Prevention)
        // Query SMP group ID
        System.out.println("\n========== Get SMP Group ==========");
        var smpGroup = client.getAccountSMPGroup();
        System.out.println("SMP Group: " + smpGroup);
    }
}
