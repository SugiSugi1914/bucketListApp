package com.example.bucketList_app.common;


public enum ReportCategory {
    SPAM(1,"スパムや詐欺行為"),
    INAPPROPRIATE(2,"不適切なコンテンツ"),
    HATE(3,"ヘイトスピーチや差別"),
    HARASSMENT(4,"いじめや嫌がらせ"),
    PRIVACY(5,"個人情報の漏洩"),
    COPYRIGHT(6,"著作権侵害"),
    MISINFORMATION(7,"虚偽情報や誤解を招く情報"),
    IMPERSONATION(8,"偽アカウントやなりすまし"),
    VIOLENCE(9,"暴力的または危険な行為の煽動"),
    SELFHARM(10,"自傷行為や自殺の助長"),
    OTHER(11,"その他");

    private int key;
    private String value;

    private ReportCategory(int key, String value){
        this.key = key;
        this.value = value;
    }

    public int getKey(){
        return key;
    }

    public String getValue(){
        return value;
    }
}
