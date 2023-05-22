package org.freework.models.tool;

public class ComparisonResult {
    private String statementId;
    private String executionMethod;
    private Object statementParam;
    private int equalRows;
    private int sybaseRowsOnly;
    private int mysqlRowsOnly;

    public int getMysqlRowsOnly() {
        return mysqlRowsOnly;
    }

    public void setMysqlRowsOnly(int mysqlRowsOnly) {
        this.mysqlRowsOnly = mysqlRowsOnly;
    }

    public int getSybaseRowsOnly() {
        return sybaseRowsOnly;
    }

    public void setSybaseRowsOnly(int sybaseRowsOnly) {
        this.sybaseRowsOnly = sybaseRowsOnly;
    }

    public int getEqualRows() {
        return equalRows;
    }

    public void setEqualRows(int equalRows) {
        this.equalRows = equalRows;
    }

    public Object getStatementParam() {
        return statementParam;
    }

    public void setStatementParam(Object statementParam) {
        this.statementParam = statementParam;
    }

    public String getExecutionMethod() {
        return executionMethod;
    }

    public void setExecutionMethod(String executionMethod) {
        this.executionMethod = executionMethod;
    }

    public String getStatementId() {
        return statementId;
    }

    public void setStatementId(String statementId) {
        this.statementId = statementId;
    }
}
