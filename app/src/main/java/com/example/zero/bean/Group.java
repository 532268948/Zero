package com.example.zero.bean;

import java.util.List;

/**
 * 作 者： ZUST_YTH
 * 日 期： 2018/2/23
 * 时 间： 13:15
 * 项 目： Zero
 * 描 述：
 */

public class Group {
    private Integer sno;
    private String sname;
    private String sdate;
    private List<Friends> list;

    public Group(Integer sno, String sname, String sdate, List<Friends> list) {
        this.sno = sno;
        this.sname = sname;
        this.sdate = sdate;
        this.list = list;
    }

    public Integer getSno() {
        return sno;
    }

    public void setSno(Integer sno) {
        this.sno = sno;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getSdate() {
        return sdate;
    }

    public void setSdate(String sdate) {
        this.sdate = sdate;
    }

    public List<Friends> getList() {
        return list;
    }

    public void setList(List<Friends> list) {
        this.list = list;
    }
}