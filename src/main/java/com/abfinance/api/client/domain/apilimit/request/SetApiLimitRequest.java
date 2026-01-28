package com.abfinance.api.client.domain.apilimit.request;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class SetApiLimitRequest {
    private String quotaId;
    private String dailyCap;
}
