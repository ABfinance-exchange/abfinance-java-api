package com.abfinance.api.client.restApi;

import com.abfinance.api.client.domain.market.request.MarketDataRequest;

public interface ABFinanceApiMarketRestClient {
    // Market Data
    Object getServerTime();
    Object getMarketLinesData(MarketDataRequest marketKlineRequest);
    Object getInstrumentsInfo(MarketDataRequest instrumentInfoRequest);
    Object getMarketOrderBook(MarketDataRequest marketOrderBookRequest);
    Object getMarketTickers(MarketDataRequest marketDataTickerRequest);
    Object getRecentTradeData(MarketDataRequest recentTradeRequest);
}
