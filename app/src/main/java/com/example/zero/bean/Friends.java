package com.example.zero.bean;

/**
 * 作 者： ZUST_YTH
 * 日 期： 2018/2/23
 * 时 间： 13:16
 * 项 目： Zero
 * 描 述：
 */

public class Friends {
    private String zero;
    private String head;
    private String nickname;
    private Integer status;

    public Friends(String zero, String head, String nickname, Integer status) {
        this.zero = zero;
        this.head = head;
        this.nickname = nickname;
        this.status = status;
    }

    public String getZero() {
        return zero;
    }

    public void setZero(String zero) {
        this.zero = zero;
    }

    public String getHead() {
        return head;
    }

    public void setHead(String head) {
        this.head = head;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
