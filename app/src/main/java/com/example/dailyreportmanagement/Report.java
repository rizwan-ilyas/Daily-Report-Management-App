package com.example.dailyreportmanagement;

public class Report {

    public int id;
    public int StdID;
    public int lines;
    public String sabki;
    public int manzal;
    public int para;
    public String date;

    public Report(int stdID, int para, int lines, String sabki, int manzal) {
        this.StdID = stdID;
        this.para=para;
        this.lines = lines;
        this.sabki = sabki;
        this.manzal = manzal;
    }

    public Report(int id, int stdID,int para, int lines, String sabki, int manzal) {
        this.id = id;
        this.StdID = stdID;
        this.para=para;
        this.lines = lines;
        this.sabki = sabki;
        this.manzal = manzal;
    }

    public Report(int id, int stdID,String d,int para, int lines, String sabki, int manzal) {
        this.id = id;
        this.StdID = stdID;
        this.para=para;
        this.lines = lines;
        this.sabki = sabki;
        this.manzal = manzal;
        this.date=d;
    }
}
