package com.abfinance.api.client.exception;

import com.abfinance.api.client.constant.ABFinanceApiConstants;
import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * ABFinance API error object.
 */
public class ABFinanceApiError {

    /**
     * Error code.
     */
    private int code;

    /**
     * Error message.
     */
    private String msg;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ABFinanceApiConstants.TO_STRING_BUILDER_STYLE)
                .append("code", code)
                .append("msg", msg)
                .toString();
    }
}
