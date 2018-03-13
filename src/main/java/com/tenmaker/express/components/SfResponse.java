package com.tenmaker.express.components;

import com.alibaba.fastjson.JSONObject;

public class SfResponse {
    private ResponseHead head;
    private JSONObject body;

    public ResponseHead getHead() {
        return head;
    }

    public void setHead(ResponseHead head) {
        this.head = head;
    }

    public JSONObject getBody() {
        return body;
    }

    public void setBody(JSONObject body) {
        this.body = body;
    }
}
