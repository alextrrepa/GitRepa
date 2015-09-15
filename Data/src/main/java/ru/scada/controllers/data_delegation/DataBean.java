package ru.scada.controllers.data_delegation;


public class DataBean {
    private String dtime1;
    private String dtime2;
    private String selectData;

    public DataBean(String dtime1, String dtime2, String selectData) {
        this.dtime1 = dtime1;
        this.dtime2 = dtime2;
        this.selectData = selectData;
    }

    public String getDtime1() {
        return dtime1;
    }

    public String getDtime2() {
        return dtime2;
    }

    public String getSelectData() {
        return selectData;
    }
}
