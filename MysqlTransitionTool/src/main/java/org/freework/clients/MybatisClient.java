package org.freework.clients;

import org.apache.ibatis.session.SqlSession;

import java.util.List;

public abstract class MybatisClient {
    protected SqlSession session;

    abstract void createConnection();

    public <T> T selectOne(String statementId)
    {
        return session.selectOne(statementId);
    }

    public <T> T selectOne(String statementId, Object p)
    {
        return session.selectOne(statementId, p);
    }

    public <T> List<T> selectList(String statementId)
    {
        List<T> data = session.selectList(statementId);

        return data;
    }

    public <T> List<T> selectList(String statementId, Object p)
    {
        List<T> data = session.selectList(statementId, p);

        return data;
    }
}
