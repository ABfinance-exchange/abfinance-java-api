package com.abfinance.api.client.domain.trade.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SetDcpRequest {
    private Integer timeWindow;
}
