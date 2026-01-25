package com.abfinance.api.client.restApi;

import com.abfinance.api.client.domain.market.request.MarketDataRequest;

public interface ABFinanceApiAsyncMarketDataRestClient {
    // Market endpoints
    void getServerTime(ABFinanceApiCallback<Object> callback);
    void getMarketLinesData(MarketDataRequest marketKlineRequest, ABFinanceApiCallback<Object> callback);
    void getInstrumentsInfo(MarketDataRequest instrumentInfoRequest, ABFinanceApiCallback<Object> callback);
    void getMarketOrderBook(MarketDataRequest marketOrderBookRequest, ABFinanceApiCallback<Object> callback);
    void getMarketTickers(MarketDataRequest marketDataTickerRequest, ABFinanceApiCallback<Object> callback);
    void getRecentTradeData(MarketDataRequest recentTradeRequest, ABFinanceApiCallback<Object> callback);
}
