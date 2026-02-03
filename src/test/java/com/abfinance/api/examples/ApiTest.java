package com.abfinance.api.examples;

import com.abfinance.api.client.config.ABFinanceApiConfig;
import com.abfinance.api.client.domain.CategoryType;
import com.abfinance.api.client.domain.account.AccountType;
import com.abfinance.api.client.domain.account.request.AccountDataRequest;
import com.abfinance.api.client.domain.market.MarketInterval;
import com.abfinance.api.client.domain.market.request.MarketDataRequest;
import com.abfinance.api.client.service.ABFinanceApiClientFactory;

public class ApiTest {
    private static final String API_KEY = "XXX";
    private static final String API_SECRET = "XXX";

    public static void main(String[] args) {
        System.out.println("=".repeat(60));
        System.out.println("ABFinance API Test");
        System.out.println("=".repeat(60));

        testMarketData();
        testAccount();
    }

    private static void testMarketData() {
        System.out.println("\n>>> TESTING MARKET DATA API (Public) <<<\n");

        try {
            var client = ABFinanceApiClientFactory.newInstance(ABFinanceApiConfig.TESTNET_DOMAIN, true)
                    .newMarketDataRestClient();

            System.out.println("1. Get Server Time:");
            var serverTime = client.getServerTime();
            System.out.println("   " + serverTime);

            System.out.println("\n2. Get BTCUSDT Ticker:");
            var tickerRequest = MarketDataRequest.builder()
                    .category(CategoryType.SPOT)
                    .symbol("BTCUSDT")
                    .build();
            var ticker = client.getMarketTickers(tickerRequest);
            System.out.println("   " + ticker);

            System.out.println("\n3. Get BTCUSDT Order Book (top 5):");
            var orderBookRequest = MarketDataRequest.builder()
                    .category(CategoryType.SPOT)
                    .symbol("BTCUSDT")
                    .limit(5)
                    .build();
            var orderBook = client.getMarketOrderBook(orderBookRequest);
            System.out.println("   " + orderBook);

            System.out.println("\n[MARKET DATA] All tests passed!");

        } catch (Exception e) {
            System.err.println("[MARKET DATA] Error: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void testAccount() {
        System.out.println("\n>>> TESTING ACCOUNT API (Private) <<<\n");

        try {
            var client = ABFinanceApiClientFactory.newInstance(API_KEY, API_SECRET, ABFinanceApiConfig.TESTNET_DOMAIN, true)
                    .newAccountRestClient();

            System.out.println("1. Get Account Info:");
            var accountInfo = client.getAccountInfo();
            System.out.println("   " + accountInfo);

            System.out.println("\n2. Get Wallet Balance (UNIFIED):");
            var walletRequest = AccountDataRequest.builder()
                    .accountType(AccountType.UNIFIED)
                    .build();
            var walletBalance = client.getWalletBalance(walletRequest);
            System.out.println("   " + walletBalance);

            System.out.println("\n3. Get Fee Rate (SPOT BTCUSDT):");
            var feeRequest = AccountDataRequest.builder()
                    .category(CategoryType.SPOT)
                    .symbol("BTCUSDT")
                    .build();
            var feeRate = client.getAccountFreeRate(feeRequest);
            System.out.println("   " + feeRate);

            System.out.println("\n[ACCOUNT] All tests passed!");

        } catch (Exception e) {
            System.err.println("[ACCOUNT] Error: " + e.getMessage());
            e.printStackTrace();
        }
    }
}
