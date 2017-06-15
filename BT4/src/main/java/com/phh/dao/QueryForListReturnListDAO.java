package com.phh.dao;

/**
 * Created by hao-pham on 6/13/17.
 */

import com.phh.model.Todo;
import com.phh.model.TodoRowMapper;
import jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
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

    // Sung dung rowmapper de sellect
    public List<Todo> selectListTodo() {

        String sql = "SELECT id,title,completed,url  FROM TODO";
        List<Todo> list = jdbcTemplate.query(sql, new TodoRowMapper());
        return list;
    }

    public int insertTodoJson(Todo td) {
        String query = "INSERT INTO TODO (TITLE,COMPLETED,URL) " +
                "VALUES ('" + td.getTitle() + "','" + td.isCompleted() + "','" + td.getUrl() + "');";
        return jdbcTemplate.update(query);
    }

    public int updateTodoJson(Todo td) {
        String query = "UPDATE TODO SET TITLE = '" + td.getTitle() + "', COMPLETED= " + td.isCompleted() + ", URL='" + td.getUrl() + "' WHERE ID = " + td.getId() + "";
        return jdbcTemplate.update(query);
    }

    public int deleteOneTodo(long id) {
        //JsonController js= new JsonController();
        // sb=js.postJsonToObject(sb);
        String query = "DELETE FROM TODO WHERE id=?";
        return jdbcTemplate.update(query, id);
    }

    public Todo selectOneTodo(long id) {
        try {
            String sql = "SELECT * FROM TODO WHERE id = ?";
            Todo todo = (Todo) getJdbcTemplate().queryForObject(
                    sql, new Object[]{id}, new TodoRowMapper());
            return todo;
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }
}
