package com.abfinance.api.client.impl;

import com.abfinance.api.client.restApi.ABFinanceApiAsyncMarketDataRestClient;
import com.abfinance.api.client.restApi.ABFinanceApiCallback;
import com.abfinance.api.client.restApi.ABFinanceApiService;
import com.abfinance.api.client.domain.market.request.MarketDataRequest;

import static com.abfinance.api.client.service.ABFinanceApiServiceGenerator.createService;

public class ABFinanceApiMarketAsyncRestClientImpl implements ABFinanceApiAsyncMarketDataRestClient {
    private final ABFinanceApiService apiService;

    public ABFinanceApiMarketAsyncRestClientImpl(String baseUrl, boolean debugMode, long recvWindow, String logOption) {
        apiService = createService(ABFinanceApiService.class, baseUrl, debugMode, recvWindow, logOption);
    }

    // Market Data endpoints
    @Override
    public void getServerTime(ABFinanceApiCallback<Object> callback) {
        apiService.getServerTime().enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void getMarketLinesData(MarketDataRequest marketKlineRequest, ABFinanceApiCallback<Object> callback) {
        apiService.getMarketLinesData(
                marketKlineRequest.getCategory().getCategoryTypeId(),
                marketKlineRequest.getSymbol(),
                marketKlineRequest.getMarketInterval() == null ? null : marketKlineRequest.getMarketInterval().getIntervalId(),
                marketKlineRequest.getStart(),
                marketKlineRequest.getEnd(),
                marketKlineRequest.getLimit()).enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void getInstrumentsInfo(MarketDataRequest instrumentInfoRequest, ABFinanceApiCallback<Object> callback) {
        apiService.getInstrumentsInfo(
                instrumentInfoRequest.getCategory().getCategoryTypeId(),
                instrumentInfoRequest.getSymbol(),
                instrumentInfoRequest.getInstrumentStatus() == null ? null : instrumentInfoRequest.getInstrumentStatus().getStatus(),
                instrumentInfoRequest.getBaseCoin(),
                instrumentInfoRequest.getLimit(),
                instrumentInfoRequest.getCursor()
        ).enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void getMarketOrderBook(MarketDataRequest marketOrderBookRequest, ABFinanceApiCallback<Object> callback) {
        apiService.getMarketOrderBook(
                marketOrderBookRequest.getCategory().getCategoryTypeId(),
                marketOrderBookRequest.getSymbol(),
                marketOrderBookRequest.getLimit()
        ).enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void getMarketTickers(MarketDataRequest marketDataTickerRequest, ABFinanceApiCallback<Object> callback) {
        apiService.getMarketTickers(
                marketDataTickerRequest.getCategory().getCategoryTypeId(),
                marketDataTickerRequest.getSymbol(),
                marketDataTickerRequest.getBaseCoin(),
                marketDataTickerRequest.getExpDate()
        ).enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }

    @Override
    public void getRecentTradeData(MarketDataRequest recentTradeRequest, ABFinanceApiCallback<Object> callback) {
        apiService.getRecentTradeData(
                recentTradeRequest.getCategory().getCategoryTypeId(),
                recentTradeRequest.getSymbol(),
                recentTradeRequest.getBaseCoin(),
                recentTradeRequest.getOptionType() == null ? null : recentTradeRequest.getOptionType().getOpType(),
                recentTradeRequest.getLimit()
        ).enqueue(new ABFinanceApiCallbackAdapter<>(callback));
    }
}
