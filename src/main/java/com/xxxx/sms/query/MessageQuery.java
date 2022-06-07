package com.xxxx.sms.query;

import com.xxxx.sms.base.BaseQuery;

public class MessageQuery extends BaseQuery {

    private String sourceId; // 接收人
    private String isRead; // 是否已读

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getIsRead() {
        return isRead;
    }

    public void setIsRead(String isRead) {
        this.isRead = isRead;
    }
}
