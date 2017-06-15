package com.phh.model;

import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by hao-pham on 6/14/17.
 */
public final class ShotBoxRowMapper implements RowMapper<ShotBox> {
    @Override
    public ShotBox mapRow(ResultSet resultSet, int i)throws SQLException {
        ShotBox shotbox = new ShotBox();
        shotbox.setId(resultSet.getLong("id"));
        shotbox.setUserName(resultSet.getString("username"));
        shotbox.setContent(resultSet.getString("content"));
        shotbox.setDateTimeText(resultSet.getString("datetime_text"));
        return shotbox;
    }
}
