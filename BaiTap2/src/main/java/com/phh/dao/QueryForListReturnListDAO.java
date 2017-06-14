package com.phh.dao;

/**
 * Created by hao-pham on 6/13/17.
 */

import javax.sql.DataSource;

import com.phh.model.ShotBox;
import com.phh.model.ShotBoxRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.*;

@Repository
public class QueryForListReturnListDAO extends JdbcDaoSupport {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public QueryForListReturnListDAO(DataSource dataSource) {

        this.setDataSource(dataSource);
        //do du lieu data source vao jdbctemlate
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // Tao ra mot list cac map cac thuoc tinh de sellect bang du lieu
    public List<Map<String, Object>> queryForList_ListMap() {

        String sql = "SELECT S.USERNAME,S.DATETIME_TEXT,S.CONTENT  FROM SHOTBOX S WHERE USERNAME='Guest'";
        // List<Map<String, Object>> queryForList(String sql)
        List<Map<String, Object>> list = jdbcTemplate.queryForList(sql);
        return list;
    }

    // Sung dung rowmapper de sellect
    public List<ShotBox> selectShowBox() {

        String sql = "SELECT id,username,content,datetime_text  FROM SHOTBOX S";
        List<ShotBox> list = jdbcTemplate.query(sql, new ShotBoxRowMapper());

        return list;
    }

    public int insertShotBox(ShotBox sb) {
        Scanner s = new Scanner(System.in);
        sb.setContent(s.nextLine());
        Date Time = new Date();
        SimpleDateFormat setTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss z");
        setTime.setTimeZone(TimeZone.getTimeZone("ICT"));
        String showTime = setTime.format(Time.getTime());
        sb.setId(Time.getTime());
        String query = "INSERT INTO SHOTBOX (ID,USERNAME,CONTENT,DATETIME_TEXT) " +
                "VALUES ('" + sb.getId() + "','Guest','" + sb.getContent() + "','" + showTime + "');";
        return jdbcTemplate.update(query);
    }
}