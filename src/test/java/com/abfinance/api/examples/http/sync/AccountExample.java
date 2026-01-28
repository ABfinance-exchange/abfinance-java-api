package com.abfinance.api.examples.http.sync;

import com.abfinance.api.client.config.ABFinanceApiConfig;
import com.abfinance.api.client.domain.account.request.AccountDataRequest;
import com.abfinance.api.client.domain.account.AccountType;
import com.abfinance.api.client.domain.CategoryType;
import com.abfinance.api.client.service.ABFinanceApiClientFactory;

/**
 * Account API Examples
 * Demonstrates all account-related API endpoints
 */
public class AccountExample {
    public static void main(String[] args) {
        var client = ABFinanceApiClientFactory.newInstance("YOUR_API_KEY", "YOUR_API_SECRET", ABFinanceApiConfig.TESTNET_DOMAIN, true).newAccountRestClient();

        // 1. Get Wallet Balance
        // Query the wallet balance for UNIFIED account
        System.out.println("========== Get Wallet Balance ==========");
        var walletBalanceRequest = AccountDataRequest.builder()
                .accountType(AccountType.UNIFIED)
                .build();
        var walletBalanceData = client.getWalletBalance(walletBalanceRequest);
        System.out.println("Wallet Balance: " + walletBalanceData);

        // 2. Get Account Info
        // Query account configuration information
        System.out.println("\n========== Get Account Info ==========");
        var accountInfo = client.getAccountInfo();
        System.out.println("Account Info: " + accountInfo);

        // 3. Get Transaction Log
        // Query transaction logs with filters
        System.out.println("\n========== Get Transaction Log ==========");
        var transactionLogRequest = AccountDataRequest.builder()
                .accountType(AccountType.UNIFIED)
                .category(CategoryType.SPOT) // Optional: filter by category
                .limit(20)
                .build();
        var transactionLogData = client.getTransactionLog(transactionLogRequest);
        System.out.println("Transaction Log: " + transactionLogData);

        // 4. Get Account Fee Rate
        // Query trading fee rate
        System.out.println("\n========== Get Fee Rate ==========");
        var feeRateRequest = AccountDataRequest.builder()
                .category(CategoryType.SPOT)
                .symbol("BTCUSDT") // Optional: specific symbol
                .build();
        var feeRateData = client.getAccountFreeRate(feeRateRequest);
        System.out.println("Fee Rate: " + feeRateData);

        // 5. Get SMP Group (Self-Match Prevention)
        // Query SMP group ID
        System.out.println("\n========== Get SMP Group ==========");
        var smpGroup = client.getAccountSMPGroup();
        System.out.println("SMP Group: " + smpGroup);

        // 6. Get User Setting Config
        // Query user setting configuration
        System.out.println("\n========== Get User Setting Config ==========");
        var userSettingConfig = client.getUserSettingConfig();
        System.out.println("User Setting Config: " + userSettingConfig);
    }
}
