package ru.javawebinar.basejava.util;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface PreparedStatementExecutor<T> {
     T execute(PreparedStatement pst) throws SQLException;
}
