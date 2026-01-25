package com.abfinance.api.client.service;


import com.abfinance.api.client.impl.*;
import com.abfinance.api.client.log.LogOption;
import com.abfinance.api.client.restApi.*;
import com.abfinance.api.client.websocket.httpclient.WebsocketStreamClient;
import com.abfinance.api.client.websocket.impl.WebsocketStreamClientImpl;
import com.abfinance.api.client.websocket.callback.WebSocketMessageCallback;

import static com.abfinance.api.client.config.ABFinanceApiConfig.MAINNET_DOMAIN;
import static com.abfinance.api.client.constant.ABFinanceApiConstants.*;

/**
 * A factory for creating ABFinance API client objects.
 */
public class ABFinanceApiClientFactory {

    /**
     * API Key
     */
    private final String apiKey;

    /**
     * Secret.
     */
    private final String secret;

    /**
     * BaseUrl
     */
    private final String baseUrl;

    /**
     * DebugMode to print request and response header
     */
    private final Boolean debugMode;

    /**
     * DebugMode log option to print request and response header
     */
    private final String logOption;

    /**
     * recvWindow to print request and response header
     */
    private final Long recvWindow;

    /**
     * broker referer code
     */
    private final String referer;

    /**
     * Instantiates a new API client factory.
     *
     * @param apiKey    api key
     * @param secret    api secret
     * @param baseUrl   base url
     * @param debugMode debugMode
     */
    private ABFinanceApiClientFactory(String apiKey, String secret, String baseUrl, boolean debugMode, long recvWindow, String logOption, String referer) {
        this.apiKey = apiKey;
        this.secret = secret;
        this.baseUrl = baseUrl;
        this.debugMode = debugMode;
        this.recvWindow = recvWindow;
        this.logOption = logOption;
        this.referer = referer;
    }

    /**
     * New instance of Api Mainnet Client
     *
     * @param apiKey the API key
     * @param secret the Secret
     * @return the api client factory
     */
    public static ABFinanceApiClientFactory newInstance(String apiKey, String secret) {
        return new ABFinanceApiClientFactory(apiKey, secret, MAINNET_DOMAIN, false, DEFAULT_RECEIVING_WINDOW, LogOption.SLF4J.getLogOptionType(), "");
    }


    /**
     * New instance of Api Mainnet Client with url
     *
     * @param apiKey  the API key
     * @param secret  the Secret
     * @param baseUrl the baseUrl
     * @return the api client factory.
     */
    public static ABFinanceApiClientFactory newInstance(String apiKey, String secret, String baseUrl) {
        return new ABFinanceApiClientFactory(apiKey, secret, baseUrl, false, DEFAULT_RECEIVING_WINDOW, LogOption.SLF4J.getLogOptionType(), "");
    }

    /**
     *
     * New instance of Api Mainnet Client with receive windows
     *
     * @param apiKey
     * @param secret
     * @param recvWindow
     * @return
     */
    public static ABFinanceApiClientFactory newInstance(String apiKey, String secret, long recvWindow) {
        return new ABFinanceApiClientFactory(apiKey, secret, MAINNET_DOMAIN, false, recvWindow, LogOption.SLF4J.getLogOptionType(), "");
    }

    /**
     * New instance of Api Mainnet Client with debug mode
     *
     * @param apiKey    the API key
     * @param secret    the Secret
     * @param debugMode to print request and response header
     * @return the api client factory.
     */
    public static ABFinanceApiClientFactory newInstance(String apiKey, String secret, boolean debugMode) {
        return new ABFinanceApiClientFactory(apiKey, secret, MAINNET_DOMAIN, debugMode, DEFAULT_RECEIVING_WINDOW, LogOption.SLF4J.getLogOptionType(), "");
    }

    /**
     * New instance of Api Mainnet Client with broker referer code
     *
     * @param apiKey    the API key
     * @param secret    the Secret
     * @param referer Broker referer code
     * @return the api client factory.
     */
    public static ABFinanceApiClientFactory newInstance(String apiKey, String secret, String baseUrl, String referer) {
        return new ABFinanceApiClientFactory(apiKey, secret, baseUrl, false, DEFAULT_RECEIVING_WINDOW, LogOption.SLF4J.getLogOptionType(), referer);
    }

    /**
     * New instance of Api Mainnet Client with debug mode and log option
     *
     * @param apiKey
     * @param secret
     * @param debugMode
     * @param logOption
     * @return
     */
    public static ABFinanceApiClientFactory newInstance(String apiKey, String secret, boolean debugMode, String logOption) {
        return new ABFinanceApiClientFactory(apiKey, secret, MAINNET_DOMAIN, debugMode, DEFAULT_RECEIVING_WINDOW, logOption, "");
    }

    public static ABFinanceApiClientFactory newInstance(String apiKey, String secret, boolean debugMode, long recvWindow) {
        return new ABFinanceApiClientFactory(apiKey, secret, MAINNET_DOMAIN, debugMode, recvWindow, LogOption.SLF4J.getLogOptionType(), "");
    }

    public static ABFinanceApiClientFactory newInstance(String apiKey, String secret, boolean debugMode, long recvWindow, String logOption) {
        return new ABFinanceApiClientFactory(apiKey, secret, MAINNET_DOMAIN, debugMode, recvWindow, logOption,  "");
    }

    /**
     *
     * new instance set url with debug mode
     *
     * @param apiKey
     * @param secret
     * @param baseUrl
     * @param debugMode
     * @return
     */
    public static ABFinanceApiClientFactory newInstance(String apiKey, String secret, String baseUrl, boolean debugMode) {
        return new ABFinanceApiClientFactory(apiKey, secret, baseUrl, debugMode, DEFAULT_RECEIVING_WINDOW, LogOption.SLF4J.getLogOptionType(), "");
    }

    /**
     *
     * new instance with base url and receive windows
     *
     * @param apiKey
     * @param secret
     * @param baseUrl
     * @param recvWindow
     * @return
     */
    public static ABFinanceApiClientFactory newInstance(String apiKey, String secret, String baseUrl, long recvWindow) {
        return new ABFinanceApiClientFactory(apiKey, secret, baseUrl, false, recvWindow, LogOption.SLF4J.getLogOptionType(), "");
    }


    /**
     * New instance without authentication.
     *
     * @return the api client factory
     */
    public static ABFinanceApiClientFactory newInstance() {
        return new ABFinanceApiClientFactory(null, null, MAINNET_DOMAIN, false, DEFAULT_RECEIVING_WINDOW, LogOption.SLF4J.getLogOptionType(), "");
    }

    public static ABFinanceApiClientFactory newInstance(long recvWindow) {
        return new ABFinanceApiClientFactory(null, null, MAINNET_DOMAIN, false, recvWindow, LogOption.SLF4J.getLogOptionType(), "");
    }

    /**
     * New instance without authentication and with optional url
     *
     * @param baseUrl base url
     * @return the api client factory.
     */
    public static ABFinanceApiClientFactory newInstance(String baseUrl) {
        return new ABFinanceApiClientFactory(null, null, baseUrl, false, DEFAULT_RECEIVING_WINDOW, LogOption.SLF4J.getLogOptionType(), "");
    }

    /**
     * New instance without authentication and with optional debug mode
     *
     * @param debugMode debug mode
     * @return the api client factory.
     */
    public static ABFinanceApiClientFactory newInstance(boolean debugMode) {
        return new ABFinanceApiClientFactory(null, null, MAINNET_DOMAIN, debugMode, DEFAULT_RECEIVING_WINDOW, LogOption.SLF4J.getLogOptionType(), "");
    }

    public static ABFinanceApiClientFactory newInstance(boolean debugMode, String logOption) {
        return new ABFinanceApiClientFactory(null, null, MAINNET_DOMAIN, debugMode, DEFAULT_RECEIVING_WINDOW, logOption, "");
    }

    /**
     * New instance without authentication and with optional base url and debug mode
     *
     * @param baseUrl   base url
     * @param debugMode debug mode
     * @return the api client factory.
     */
    public static ABFinanceApiClientFactory newInstance(String baseUrl, boolean debugMode, String logOption) {
        return new ABFinanceApiClientFactory(null, null, baseUrl, debugMode, DEFAULT_RECEIVING_WINDOW, logOption, "");
    }

    public static ABFinanceApiClientFactory newInstance(String baseUrl, boolean debugMode) {
        return new ABFinanceApiClientFactory(null, null, baseUrl, debugMode, DEFAULT_RECEIVING_WINDOW, LogOption.SLF4J.getLogOptionType(), "");
    }

    /**
     * Creates a new synchronous/blocking REST client.
     */
    public ABFinanceApiUserRestClient newUserRestClient() {
        return new ABFinanceApiUserRestClientImpl(apiKey, secret, baseUrl, debugMode, recvWindow, logOption);
    }

    /**
     * Creates a new asynchronous/non-blocking REST client to User and upgrade endpoints.
     */
    public ABFinanceApiAsyncUserRestClient newAsyncUserRestClient() {
        return new ABFinanceApiAsyncUserRestClientImpl(apiKey, secret, baseUrl, debugMode, recvWindow, logOption);
    }

    /**
     * Creates a new synchronous/blocking REST client to Market Data Endpoints
     */
    public ABFinanceApiMarketRestClient newMarketDataRestClient() {
        return new ABFinanceApiMarketRestClientImpl(baseUrl, debugMode, recvWindow, logOption);
    }

    /**
     * Creates a new asynchronous/non-blocking client to Market Data Endpoints
     */
    public ABFinanceApiAsyncMarketDataRestClient newAsyncMarketDataRestClient() {
        return new ABFinanceApiMarketAsyncRestClientImpl(baseUrl, debugMode, recvWindow, logOption);
    }

    /**
     * Creates a new synchronous/blocking REST client to trading
     */
    public ABFinanceApiTradeRestClient newTradeRestClient() {
        return new ABFinanceApiTradeRestClientImpl(apiKey, secret, baseUrl, debugMode, recvWindow, logOption, referer);
    }

    /**
     * Creates a new asynchronous/non-blocking REST client to trading
     */
    public ABFinanceApiAsyncTradeRestClient newAsyncTradeRestClient() {
        return new ABFinanceApiTradeAsyncRestClientImpl(apiKey, secret, baseUrl, debugMode, recvWindow, logOption, referer);
    }

    /**
     * Creates a new synchronous/blocking REST client to Account data
     */
    public ABFinanceApiAccountRestClient newAccountRestClient() {
        return new ABFinanceApiAccountRestClientImpl(apiKey, secret, baseUrl, debugMode, recvWindow, logOption);
    }

    /**
     * Creates a new asynchronous/non-blocking client to Account data
     */
    public ABFinanceApiAsyncAccountRestClient newAsyncAccountRestClient() {
        return new ABFinanceApiAsyncAccountRestClientImpl(apiKey, secret, baseUrl, debugMode, recvWindow, logOption);
    }

    /**
     * Creates a new synchronous/blocking REST client to Asset data
     */
    public ABFinanceApiAssetRestClient newAssetRestClient() {
        return new ABFinanceApiAssetRestClientImpl(apiKey, secret, baseUrl, debugMode, recvWindow, logOption);
    }

    /**
     * Creates a new asynchronous/non-blocking client to Asset data
     */
    public ABFinanceApiAsyncAssetRestClient newAsyncAssetRestClient() {
        return new ABFinanceApiAsyncAssetRestClientImpl(apiKey, secret, baseUrl, debugMode, recvWindow, logOption);
    }

    /**
     * Creates a new synchronous/blocking REST client to Earn data
     */
    public ABFinanceApiEarnRestClient newEarnRestClient() {
        return new ABFinanceApiEarnRestClientImpl(apiKey, secret, baseUrl, debugMode, recvWindow, logOption);
    }

    /**
     * Creates a new asynchronous/non-blocking client to Earn data
     */
    public ABFinanceApiAsyncEarnRestClient newAsyncEarnRestClient() {
        return new ABFinanceApiAsyncEarnRestClientImpl(apiKey, secret, baseUrl, debugMode, recvWindow, logOption);
    }

    /**
     * Access to public websocket
     */
    public WebsocketStreamClient newWebsocketClient() {
        return new WebsocketStreamClientImpl(apiKey, secret, baseUrl, DEFAULT_PING_INTERVAL, DEFAULT_MAX_ALIVE_TIME, debugMode, logOption, System.out::println);
    }

    public WebsocketStreamClient newWebsocketClient(int pingInterval) {
        return new WebsocketStreamClientImpl(apiKey, secret, baseUrl, pingInterval, DEFAULT_MAX_ALIVE_TIME, debugMode, logOption, System.out::println);
    }

    public WebsocketStreamClient newWebsocketClient(String maxAliveTime) {
        return new WebsocketStreamClientImpl(apiKey, secret, baseUrl, DEFAULT_PING_INTERVAL, maxAliveTime, debugMode, logOption, System.out::println);
    }

    public WebsocketStreamClient newWebsocketClient(WebSocketMessageCallback messageHandler) {
        return new WebsocketStreamClientImpl(apiKey, secret, baseUrl, DEFAULT_PING_INTERVAL, DEFAULT_MAX_ALIVE_TIME, debugMode, logOption, messageHandler);
    }

    public WebsocketStreamClient newWebsocketClient(int pingInterval, WebSocketMessageCallback messageHandler) {
        return new WebsocketStreamClientImpl(apiKey, secret, baseUrl, pingInterval, DEFAULT_MAX_ALIVE_TIME, debugMode, logOption, messageHandler);
    }
    public WebsocketStreamClient newWebsocketClient(int pingInterval, String maxAliveTime) {
        return new WebsocketStreamClientImpl(apiKey, secret, baseUrl, pingInterval, maxAliveTime, debugMode, logOption, System.out::println);
    }

    public WebsocketStreamClient newWebsocketClient(String maxAliveTime, WebSocketMessageCallback messageHandler) {
        return new WebsocketStreamClientImpl(apiKey, secret, baseUrl, DEFAULT_PING_INTERVAL, maxAliveTime, debugMode, logOption, System.out::println);
    }

    public WebsocketStreamClient newWebsocketClient(int pingInterval, String maxAliveTime, WebSocketMessageCallback messageHandler) {
        return new WebsocketStreamClientImpl(apiKey, secret, baseUrl, pingInterval, maxAliveTime, debugMode, logOption, messageHandler);
    }
}
