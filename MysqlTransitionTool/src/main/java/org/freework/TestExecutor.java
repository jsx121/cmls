package org.freework;

import org.freework.clients.MysqlClient;
import org.freework.clients.SybaseClient;
import org.freework.models.tool.ComparisonResult;

import java.util.*;

public class TestExecutor {
    private MysqlClient mysqlClient;
    private SybaseClient sybaseClient;
    private List<ComparisonResult> results = new ArrayList<ComparisonResult>();

    public TestExecutor()
    {
        mysqlClient = new MysqlClient();
        sybaseClient = new SybaseClient();
    }

    public List<ComparisonResult> getResults()
    {
        return results;
    }

    private <T> void compareResults(List<T> mysqlResults, List<T> sybaseResults, ComparisonResult result)
    {
        int same=0, mysqlOnly=0, sybaseOnly=0;
        boolean found = false;

        for(int i = 0; i < mysqlResults.size(); i++) {
            T mysqlData = mysqlResults.get(i);

            found = false;
            for(int j = 0; j < sybaseResults.size(); j++) {
                T sybaseData = sybaseResults.get(i);

                if(mysqlData.equals(sybaseData)) {
                    same++;
                    found = true;
                    break;
                }
            }

            if(!found) {
                mysqlOnly++;
            }
        }

        for(int i = 0; i < sybaseResults.size(); i++) {
            T sybaseData = sybaseResults.get(i);

            found = false;
            for(int j = 0; j < mysqlResults.size(); j++) {
                T mysqlData = mysqlResults.get(i);

                if(mysqlData.equals(sybaseData)) {
                    found = true;
                    break;
                }
            }

            if(!found) {
                sybaseOnly++;
            }
        }

        result.setEqualRows(same);
        result.setMysqlRowsOnly(mysqlOnly);
        result.setSybaseRowsOnly(sybaseOnly);
    }

    public <T> void executeListTest(String statementId)
    {
        List<T> mysqlData = mysqlClient.selectList(statementId);
        List<T> sybaseData = sybaseClient.selectList(statementId);
        ComparisonResult result = new ComparisonResult();

        result.setStatementId(statementId);
        compareResults(mysqlData, sybaseData, result);

        results.add(result);
    }

    public <T> void executeListTest(String statementId, Object p)
    {
        List<T> mysqlData = mysqlClient.selectList(statementId, p);
        List<T> sybaseData = sybaseClient.selectList(statementId, p);
        ComparisonResult result = new ComparisonResult();

        result.setStatementId(statementId);
        result.setStatementParam(p);
        compareResults(mysqlData, sybaseData, result);

        results.add(result);
    }
}