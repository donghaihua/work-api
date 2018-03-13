package com.tenmaker.express.components;

public class RequestHead {
    private String transType;
    private String transMessageId;

    public RequestHead(String transType, String transMessageId) {
        this.transType = transType;
        this.transMessageId = transMessageId;
    }

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
}
