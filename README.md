# abfinance-java-api
[![Java 11](https://img.shields.io/badge/Java-11-brightgreen.svg)](https://github.com/abfinance/abfinance-java-api)   [![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://github.com/abfinance/abfinance-java-api/blob/main/LICENSE)

## Table of Contents
- [About](#about)
- [Installation](#installation)
- [Usage](#usage)

## About
The Official Java API connector for ABFinance's HTTP and WebSocket APIs.

Functionalities:
- Market Data Retrieval
- Trade Execution
- Account and Asset Info Retrieval
- User Management
- Public Websocket Streaming
- Private Websocket Streaming
- Earn Products

## Installation
Ensure you have Java 11 or higher.

Maven Example
```xml
<dependency>
    <groupId>io.github.johnnywic</groupId>
    <artifactId>abfinance-java-api</artifactId>
    <version>1.0.1</version>
</dependency>
```

## Usage

### HTTP Client Examples

#### Trade Example
```java
var client = ABFinanceApiClientFactory.newInstance("YOUR_API_KEY", "YOUR_API_SECRET", ABFinanceApiConfig.TESTNET_DOMAIN).newTradeRestClient();

// Create a new order
var newOrderRequest = TradeOrderRequest.builder()
    .category(CategoryType.LINEAR)
    .symbol("XRPUSDT")
    .side(Side.BUY)
    .orderType(TradeOrderType.MARKET)
    .qty("10")
    .build();
var newOrder = client.createOrder(newOrderRequest);
System.out.println(newOrder);
```

#### Account Example
```java
var client = ABFinanceApiClientFactory.newInstance("YOUR_API_KEY", "YOUR_API_SECRET", ABFinanceApiConfig.TESTNET_DOMAIN).newAccountRestClient();

// Get Wallet Balance
var walletBalanceRequest = AccountDataRequest.builder()
    .accountType(AccountType.UNIFIED)
    .build();
var walletBalanceData = client.getWalletBalance(walletBalanceRequest);
System.out.println(walletBalanceData);
```

#### Market Data Example
```java
var client = ABFinanceApiClientFactory.newInstance(ABFinanceApiConfig.TESTNET_DOMAIN).newMarketDataRestClient();

// Get market Kline
var marketKLineRequest = MarketDataRequest.builder()
    .category(CategoryType.LINEAR)
    .symbol("BTCUSDT")
    .marketInterval(MarketInterval.WEEKLY)
    .build();
var marketKlineResult = client.getMarketLinesData(marketKLineRequest);
System.out.println(marketKlineResult);
```

### WebSocket Examples

#### Public Channel
```java
var client = ABFinanceApiClientFactory.newInstance(ABFinanceApiConfig.STREAM_TESTNET_DOMAIN, true).newWebsocketClient(20);
client.getPublicChannelStream(List.of("orderbook.50.BTCUSDT"), ABFinanceApiConfig.V5_PUBLIC_LINEAR);
```

#### Private Channel
```java
var client = ABFinanceApiClientFactory.newInstance("YOUR_API_KEY", "YOUR_API_SECRET", ABFinanceApiConfig.STREAM_TESTNET_DOMAIN).newWebsocketClient();
client.getPrivateChannelStream(List.of("position"), ABFinanceApiConfig.V5_PRIVATE);
```
