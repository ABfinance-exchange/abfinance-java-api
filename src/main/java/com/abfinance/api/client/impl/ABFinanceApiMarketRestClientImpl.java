package com.abfinance.api.client.impl;

import com.abfinance.api.client.restApi.ABFinanceApiMarketRestClient;
import com.abfinance.api.client.restApi.ABFinanceApiService;
import com.abfinance.api.client.domain.market.request.MarketDataRequest;
import lombok.Getter;

import static com.abfinance.api.client.service.ABFinanceApiServiceGenerator.createService;
import static com.abfinance.api.client.service.ABFinanceApiServiceGenerator.executeSync;

@Getter
public class ABFinanceApiMarketRestClientImpl implements ABFinanceApiMarketRestClient {
    private final ABFinanceApiService apiService;

    public ABFinanceApiMarketRestClientImpl(String baseUrl, boolean debugMode, long recvWindow, String logOption) {
        apiService = createService(ABFinanceApiService.class, baseUrl, debugMode, recvWindow, logOption);
    }

    // Market Data endpoints
    @Override
    public Object getServerTime() {
        return executeSync(apiService.getServerTime());
    }

    @Override
    public Object getMarketLinesData(MarketDataRequest marketKlineRequest) {
        return executeSync(
                apiService.getMarketLinesData(
                        marketKlineRequest.getCategory().getCategoryTypeId(),
                        marketKlineRequest.getSymbol(),
                        marketKlineRequest.getMarketInterval() == null ? null : marketKlineRequest.getMarketInterval().getIntervalId(),
                        marketKlineRequest.getStart(),
                        marketKlineRequest.getEnd(),
                        marketKlineRequest.getLimit()
                )
        );
    }

    @Override
    public Object getInstrumentsInfo(MarketDataRequest instrumentInfoRequest) {
        return executeSync(apiService.getInstrumentsInfo(
                instrumentInfoRequest.getCategory().getCategoryTypeId(),
                instrumentInfoRequest.getSymbol(),
                instrumentInfoRequest.getInstrumentStatus() == null ? null : instrumentInfoRequest.getInstrumentStatus().getStatus(),
                instrumentInfoRequest.getBaseCoin(),
                instrumentInfoRequest.getLimit(),
                instrumentInfoRequest.getCursor()
        ));
    }

    @Override
    public Object getMarketOrderBook(MarketDataRequest marketOrderBookRequest) {
        return executeSync(apiService.getMarketOrderBook(
                marketOrderBookRequest.getCategory().getCategoryTypeId(),
                marketOrderBookRequest.getSymbol(),
                marketOrderBookRequest.getLimit()
        ));
    }

    @Override
    public Object getMarketTickers(MarketDataRequest marketDataTickerRequest) {
        return executeSync(apiService.getMarketTickers(
                marketDataTickerRequest.getCategory().getCategoryTypeId(),
                marketDataTickerRequest.getSymbol(),
                marketDataTickerRequest.getBaseCoin(),
                marketDataTickerRequest.getExpDate()
        ));
    }

    @Override
    public Object getRecentTradeData(MarketDataRequest recentTradeRequest) {
        return executeSync(apiService.getRecentTradeData(
                recentTradeRequest.getCategory().getCategoryTypeId(),
                recentTradeRequest.getSymbol(),
                recentTradeRequest.getBaseCoin(),
                recentTradeRequest.getOptionType() == null ? null : recentTradeRequest.getOptionType().getOpType(),
                recentTradeRequest.getLimit()
        ));
    }
}
