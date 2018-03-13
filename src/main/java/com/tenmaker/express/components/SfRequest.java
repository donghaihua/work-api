package com.tenmaker.express.components;

import com.alibaba.fastjson.JSONObject;

public class SfRequest {
    private RequestHead head;
    private JSONObject body;

    public SfRequest(RequestHead head, JSONObject body) {
        this.head = head;
        this.body = body;
    }

    public RequestHead getHead() {
        return head;
    }

    public void setHead(RequestHead head) {
        this.head = head;
    }

    public JSONObject getBody() {
        return body;
    }

    public void setBody(JSONObject body) {
        this.body = body;
    }
}
