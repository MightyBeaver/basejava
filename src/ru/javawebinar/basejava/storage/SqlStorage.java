package ru.javawebinar.basejava.storage;

import ru.javawebinar.basejava.exception.NotExistStorageException;
import ru.javawebinar.basejava.model.Resume;
import ru.javawebinar.basejava.util.SqlHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlStorage implements Storage {
    private final SqlHelper sqlHelper;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        sqlHelper = new SqlHelper(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void clear() {
        sqlHelper.execute("DELETE FROM resume", (ps) -> ps.execute());
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.execute("SELECT * FROM resume r WHERE r.uuid = ?", pst -> {
            pst.setString(1, uuid);
            ResultSet rs = pst.executeQuery();
            if(!rs.next()) {
                throw new NotExistStorageException(uuid);
            }
            return new Resume(uuid, rs.getString("full_name"));
        });
    }

    @Override
    public void update(Resume r) {
        sqlHelper.execute("UPDATE resume SET full_name = ? WHERE uuid = ?", pst -> {
            pst.setString(1, r.getFullName());
            pst.setString(2, r.getUuid());
            if(pst.executeUpdate() == 0)
            {
                throw new NotExistStorageException(r.getUuid());
            }
            return null;
        });
    }

    @Override
    public void save(Resume r) {
        sqlHelper.execute("INSERT INTO resume (uuid, full_name) VALUES (?,?)", pst -> {
            pst.setString(1, r.getUuid());
            pst.setString(2, r.getFullName());
            pst.execute();
            return null;
        });
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.execute("DELETE FROM resume WHERE uuid = ?", pst -> {
            pst.setString(1, uuid);
            if(pst.executeUpdate() == 0){
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return sqlHelper.execute("SELECT * FROM resume ORDER BY full_name", pst -> {
            ResultSet rs = pst.executeQuery();
            List<Resume> resumes = new ArrayList<>();
            while(rs.next()) {
                resumes.add(new Resume(rs.getString(1).trim(), rs.getString(2)));
            }
            return resumes;
        });
    }

    @Override
    public int size() {
        return sqlHelper.execute("SELECT COUNT (*) FROM resume", pst -> {
            ResultSet rs = pst.executeQuery();
            if(!rs.next()) {
                return 0;
            }
            return rs.getInt(1);
        });
    }
}