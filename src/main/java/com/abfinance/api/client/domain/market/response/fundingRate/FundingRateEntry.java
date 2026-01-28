package com.abfinance.api.client.domain.market.response.fundingRate;

import com.abfinance.api.client.constant.ABFinanceApiConstants;
import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;

@Getter
public class FundingRateEntry {
    private String symbol;
    private String fundingRate;
    private String fundingRateTimestamp;

    @Override
    public String toString() {
        return new ToStringBuilder(this, ABFinanceApiConstants.TO_STRING_BUILDER_STYLE)
                .append("symbol", symbol)
                .append("fundingRate", fundingRate)
                .append("fundingRateTimestamp", fundingRateTimestamp)
                .toString();
    }
}
