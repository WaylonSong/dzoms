package com.dz.module.driver;

/**
 * @author doggy
 *         Created on 16-5-19.
 */
public class UrlAttachBean {
    private String idNum;
    private String name;
    private String url;

    public UrlAttachBean(String idNum, String name, String url) {
        this.idNum = idNum;
        this.name = name;
        this.url = url;
    }

    public String getIdNum() {
        return idNum;
    }

    public void setIdNum(String idNum) {
        this.idNum = idNum;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
