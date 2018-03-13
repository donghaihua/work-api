package com.tenmaker.express.components;

import com.alibaba.fastjson.JSONObject;

import java.util.List;

/**
 * @author Hayward
 * @date 2018/3/7 0007  13:56
 */
public class SfResponseList {
    private ResponseHead head;
    private List<JSONObject> body;

    public ResponseHead getHead() {
        return head;
    }

    public void setHead(ResponseHead head) {
        this.head = head;
    }

    public List<JSONObject> getBody() {
        return body;
    }

    public void setBody(List<JSONObject> body) {
        this.body = body;
    }

}
