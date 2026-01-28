package com.abfinance.api.client.domain.account.request;

import com.abfinance.api.client.domain.CategoryType;
import com.abfinance.api.client.domain.account.AccountType;
import com.abfinance.api.client.domain.account.CollateralSwitch;
import com.abfinance.api.client.domain.account.SpotHedgingMode;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDataRequest {
    private AccountType accountType;
    private CategoryType category;
    private String currency;
    private String baseCoin;
    private Long startTime;
    private Long endTime;
    private Integer limit;
    private String cursor;
    private String coin;
    private CollateralSwitch collateralSwitch;
    private String window;
    private String frozenPeriod;
    private String qtyLimit;
    private String deltaLimit;
    private String coins;
    private String symbol;
    private SpotHedgingMode setHedgingMode;
}
