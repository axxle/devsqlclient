package ru.axxle.devsqlclient;

import java.util.List;

public class TableObject {

    //когда это было
    private Long dbTimestamp;

    //название таблицы
    private String tableName;

    //какие колонки
    private String[] headers;

    //таблица данных
    private List<String[]> dataRows;

    public TableObject() {}

    public Long getDbTimestamp() {
        return dbTimestamp;
    }

    public void setDbTimestamp(Long dbTimestamp) {
        this.dbTimestamp = dbTimestamp;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String[] getHeaders() {
        return headers;
    }

    public void setHeaders(String[] headers) {
        this.headers = headers;
    }

    public List<String[]> getDataRows() {
        return dataRows;
    }

    public void setDataRows(List<String[]> dataRows) {
        this.dataRows = dataRows;
    }
}
