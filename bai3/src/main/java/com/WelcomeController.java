package com;

import com.phh.config.AppConfiguration;
import com.phh.dao.QueryForListReturnListDAO;
import com.phh.model.ShotBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@Controller
public class WelcomeController {
    public static final Logger logger = LoggerFactory.getLogger(WelcomeController.class);

    @RequestMapping(value = "/shouts", method = RequestMethod.GET)
    public ResponseEntity<List<ShotBox>> listAllShotBox() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        QueryForListReturnListDAO dao = context.getBean(QueryForListReturnListDAO.class);
        ShotBox sb1 = new ShotBox();
        //select ra 1 list shotbox luc dau
        List<ShotBox> shotBox = dao.selectShowBox();
        if (shotBox.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<ShotBox>>(shotBox, HttpStatus.OK);
    }

    @RequestMapping(value = "/shouts", method = RequestMethod.POST)
    public ResponseEntity<?> createUser(@RequestBody @Valid ShotBox sb) {
        logger.info("Creating User : {}", sb);
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        QueryForListReturnListDAO dao = context.getBean(QueryForListReturnListDAO.class);
        dao.insertShotBoxJson(sb);
        return new ResponseEntity<>(sb, HttpStatus.OK);
    }

    @RequestMapping(value = "/shouts/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteUser(@PathVariable("id") long id) {
        logger.info("Fetching & Deleting User with id {}", id);
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        QueryForListReturnListDAO dao = context.getBean(QueryForListReturnListDAO.class);
        dao.deleteShotBoxJson(id);

        return new ResponseEntity<ShotBox>(HttpStatus.NO_CONTENT);
    }

}