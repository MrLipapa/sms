package com.xxxx.sms.base;


public class BaseQuery {
    private static Integer page=1;
    private static Integer limit=10;

    public static Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public static Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
