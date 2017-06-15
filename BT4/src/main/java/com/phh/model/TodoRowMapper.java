package com.phh.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by hao-pham on 6/15/17.
 */

public final class TodoRowMapper implements RowMapper<Todo> {
    @Override
    public Todo mapRow(ResultSet resultSet, int i) throws SQLException {
        Todo todo = new Todo();
        todo.setId(resultSet.getLong("id"));
        todo.setTitle(resultSet.getString("title"));
        todo.setCompleted(resultSet.getBoolean("completed"));
        todo.setUrl(resultSet.getString("url"));
        return todo;
    }
}
