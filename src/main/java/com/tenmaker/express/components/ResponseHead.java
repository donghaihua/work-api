package com.tenmaker.express.components;

public class ResponseHead {
    private String transType;
    private String transMessageId;
    private String code;
    private String message;

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public String getTransMessageId() {
        return transMessageId;
    }

    public void setTransMessageId(String transMessageId) {
        this.transMessageId = transMessageId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
