package org.freework.clients;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.freework.models.mysql.TestModel;

import java.io.InputStream;
import java.util.List;

public class MysqlClient extends MybatisClient {
    public MysqlClient() {
        createConnection();
    }

    void createConnection() {
        try {
            String resource = "sqlmaps/mysql/mysql-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            session = sqlSessionFactory.openSession();
        } catch (Exception ex) {
            System.out.println("MysqlClient exception: " + ex.getMessage());
        }
    }
}
