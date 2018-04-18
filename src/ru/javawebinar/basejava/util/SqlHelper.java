package ru.javawebinar.basejava.util;

import ru.javawebinar.basejava.exception.ExistStorageException;
import ru.javawebinar.basejava.exception.StorageException;
import ru.javawebinar.basejava.sql.ConnectionFactory;

import java.sql.*;

public class SqlHelper {
    public final ConnectionFactory connectionFactory;

    public SqlHelper(String dbUrl, String dbUser, String dbPassword) {
        connectionFactory = () -> DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    public <T> T execute(String query, PreparedStatementExecutor<T> executor) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement pst = conn.prepareStatement(query)) {
            return executor.execute(pst);
        } catch (SQLException e) {
            if("23505".equals(e.getSQLState())) {
                throw new ExistStorageException(null);
            }
            throw new StorageException(e);
        }
    }
}
