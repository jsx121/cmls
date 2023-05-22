package org.freework;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.freework.models.mysql.TestModel;
import org.freework.models.tool.ComparisonResult;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class Main {
    private static void baseConnectionTest()
    {
        String url = "jdbc:mysql://localhost:3306";
        String username = "root";
        String password = "mysql_root";

        System.out.println("Loading driver...");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver loaded!");
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Cannot find the driver in the classpath!", e);
        }

        System.out.println("Connecting database...");

        try (Connection connection = DriverManager.getConnection(url, username, password)) {
            System.out.println("Database connected!");
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
    }

    private static void mysqlMybatisConnectionTest() throws Exception
    {
        String resource = "sqlmaps/mysql/mysql-config.xml";
        InputStream inputStream = Resources.getResourceAsStream(resource);
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

        try (SqlSession session = sqlSessionFactory.openSession()) {
            List<TestModel> data = session.selectList("org.freework.mysql.test.selectTest");

            System.out.println("COUNT OF RESULTS: " + data.size());

            for(int i = 0; i < data.size(); i++) {
                System.out.println("#" + i + ": " + data.get(i).getId());
            }
        }
    }

    private static void printResults(List<ComparisonResult> results)
    {
        for(ComparisonResult r : results) {
            System.out.println("--- " + r.getStatementId() + " ---");
            if(r.getEqualRows() > 0 && r.getMysqlRowsOnly() == 0 && r.getSybaseRowsOnly() == 0) {
                System.out.println("all results match");
            } else {
                System.out.println("matching=" + r.getEqualRows() + ", mysql only=" + r.getMysqlRowsOnly() + ", sybase only=" + r.getSybaseRowsOnly());
            }

            System.out.println();
        }
    }

    // IMPORTANT: This is purely example. Your MySQL DB won't have this table, and the
    //            Sybase SQL map does not have this statement even.
    //
    // test methods should follow this pattern though, just call as many statements
    // defined in the SQL maps that you can
    private static void runBasicTest()
    {
        TestExecutor executor = new TestExecutor();

        executor.executeListTest("org.freework.mysql.test.selectTest");

        printResults(executor.getResults());
    }

    public static void main(String[] args) {
        try {
            //baseConnectionTest();
            //mysqlMybatisConnectionTest();
            //
            // call some test method here
        } catch(Exception ex) {
            System.out.println("exception: " + ex.getMessage());
        }
    }
}