package com.abfinance.api.examples.http.sync;

import com.abfinance.api.client.config.ABFinanceApiConfig;
import com.abfinance.api.client.domain.CategoryType;
import com.abfinance.api.client.domain.market.MarketInterval;
import com.abfinance.api.client.domain.market.request.MarketDataRequest;
import com.abfinance.api.client.service.ABFinanceApiClientFactory;

/**
 * Market Data API Examples
 * Demonstrates all market data related API endpoints (public, no authentication required)
 */
public class MarketDataExample {
    public static void main(String[] args) {
        // Market data client - no API key required for public endpoints
        var client = ABFinanceApiClientFactory.newInstance(ABFinanceApiConfig.TESTNET_DOMAIN, true).newMarketDataRestClient();

        // 1. Get Server Time
        System.out.println("========== Get Server Time ==========");
        var serverTime = client.getServerTime();
        System.out.println("Server Time: " + serverTime);

        // 2. Get Market Tickers (SPOT)
        System.out.println("\n========== Get Market Tickers (SPOT) ==========");
        var tickerRequest = MarketDataRequest.builder()
                .category(CategoryType.SPOT)
                .symbol("BTCUSDT")
                .build();
        var tickers = client.getMarketTickers(tickerRequest);
        System.out.println("Tickers: " + tickers);

        // 3. Get All Spot Tickers
        System.out.println("\n========== Get All Spot Tickers ==========");
        var allSpotTickersRequest = MarketDataRequest.builder()
                .category(CategoryType.SPOT)
                .build();
        var allSpotTickers = client.getMarketTickers(allSpotTickersRequest);
        System.out.println("All Spot Tickers: " + allSpotTickers);

        // 4. Get Instruments Info (SPOT)
        System.out.println("\n========== Get Instruments Info (SPOT) ==========");
        var instrumentRequest = MarketDataRequest.builder()
                .category(CategoryType.SPOT)
                .symbol("BTCUSDT")
                .build();
        var instrumentInfo = client.getInstrumentsInfo(instrumentRequest);
        System.out.println("Instrument Info: " + instrumentInfo);

        // 5. Get Market Order Book (SPOT)
        System.out.println("\n========== Get Market Order Book (SPOT) ==========");
        var orderBookRequest = MarketDataRequest.builder()
                .category(CategoryType.SPOT)
                .symbol("BTCUSDT")
                .limit(25)
                .build();
        var orderBook = client.getMarketOrderBook(orderBookRequest);
        System.out.println("Order Book: " + orderBook);

        // 6. Get Recent Trade Data (SPOT)
        System.out.println("\n========== Get Recent Trade Data (SPOT) ==========");
        var recentTradeRequest = MarketDataRequest.builder()
                .category(CategoryType.SPOT)
                .symbol("BTCUSDT")
                .limit(50)
                .build();
        var recentTrades = client.getRecentTradeData(recentTradeRequest);
        System.out.println("Recent Trades: " + recentTrades);

        // 7. Get Market Kline Data (SPOT)
        System.out.println("\n========== Get Market Kline Data (SPOT) ==========");
        var klineRequest = MarketDataRequest.builder()
                .category(CategoryType.SPOT)
                .symbol("BTCUSDT")
                .marketInterval(MarketInterval.DAILY)
                .limit(10)
                .build();
        var klineData = client.getMarketLinesData(klineRequest);
        System.out.println("Kline Data: " + klineData);
    }
}
