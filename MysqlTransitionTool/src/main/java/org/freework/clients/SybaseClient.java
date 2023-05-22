package org.freework.clients;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.freework.models.mysql.TestModel;

import java.io.InputStream;
import java.util.List;

public class SybaseClient extends MybatisClient {
    public SybaseClient()
    {
        createConnection();
    }

    void createConnection()
    {
        try {
            String resource = "sqlmaps/sybase/sybase-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);

            session = sqlSessionFactory.openSession();
        } catch(Exception ex) {
            System.out.println("SybaseClient exception: " + ex.getMessage());
        }
    }
}
