package com.phh.controller;

import com.phh.config.AppConfiguration;
import com.phh.dao.QueryForListReturnListDAO;
import com.phh.model.Todo;
import com.phh.util.TodoErrorType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;


import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
public class RequestController {
    public static final Logger logger = LoggerFactory.getLogger(RequestController.class);

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<List<Todo>> listAllTodo() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        QueryForListReturnListDAO dao = context.getBean(QueryForListReturnListDAO.class);
        List<Todo> todo = dao.selectListTodo();
        if (todo.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<>(todo, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public ResponseEntity<Todo> oneTodo(@PathVariable("id") long id) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        QueryForListReturnListDAO dao = context.getBean(QueryForListReturnListDAO.class);
        Todo todo = dao.selectOneTodo(id);
        if (todo == null) {
            logger.error("User with id {} not found.", id);
            return new ResponseEntity(new TodoErrorType("User with id " + id
                    + " not found"), HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Todo>(todo, HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public ResponseEntity<?> createTodo(@RequestBody @Valid Todo td, HttpServletRequest request, UriComponentsBuilder ucBuilder) {
        logger.info("Creating User : {}", td);
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        QueryForListReturnListDAO dao = context.getBean(QueryForListReturnListDAO.class);
        td.setUrl(String.valueOf(request.getRequestURL()));
        dao.insertTodoJson(td);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/{id}").buildAndExpand(td.getId()).toUri());
        return new ResponseEntity<String>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteTodo(@PathVariable("id") long id) {
        logger.info("Fetching & Deleting User with id {}", id);
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        QueryForListReturnListDAO dao = context.getBean(QueryForListReturnListDAO.class);
        dao.deleteOneTodo(id);

        return new ResponseEntity<Todo>(HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<?> updateTodo(@PathVariable("id") long id, @RequestBody Todo todo, UriComponentsBuilder ucBuilder) {
        logger.info("Updating User with id {}", id);
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        QueryForListReturnListDAO dao = context.getBean(QueryForListReturnListDAO.class);
        Todo currentTodo = dao.selectOneTodo(id);
        if (currentTodo == null) {
            dao.insertTodoJson(todo);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(ucBuilder.path("/{id}").buildAndExpand(todo.getId()).toUri());
            return new ResponseEntity<String>(headers, HttpStatus.CREATED);
        } else {
            //currentTodo.setId(todo.getId());
            currentTodo.setTitle(todo.getTitle());
            currentTodo.setCompleted(todo.isCompleted());
            currentTodo.setUrl(todo.getUrl());
            dao.updateTodoJson(currentTodo);
            return new ResponseEntity<Todo>(currentTodo, HttpStatus.OK);
        }
    }

}