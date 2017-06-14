package com;

import com.phh.config.AppConfiguration;
import com.phh.dao.QueryForListReturnListDAO;
import com.phh.model.ShotBox;
import com.phh.objectjson.JsonController;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.Map;

@Controller
public class WelcomeController {

	// inject via application.properties

//	@RequestMapping(value ="/shouts", method= RequestMethod.GET)
//	public String method(Map<String, Object> model) {
//		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
//		QueryForListReturnListDAO dao = context.getBean(QueryForListReturnListDAO.class);
//		ShotBox sb1 = new ShotBox();
//		//select ra 1 list shotbox luc dau
//		List<ShotBox> shotBox = dao.selectShowBox();
//		for (ShotBox sb : shotBox) {
//			model.put("message",sb.getUserName() + " said: (at " + sb.getDateTimeText() + "): " + sb.getContent());
//		}
//		return "welcome";
//	}
	@RequestMapping(value ="/shouts", method= RequestMethod.GET)
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
	@RequestMapping(value ="/shouts", method= RequestMethod.POST)
	public ResponseEntity<List<ShotBox>> postShotBox() {
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
		QueryForListReturnListDAO dao = context.getBean(QueryForListReturnListDAO.class);
		ShotBox sb1 = new ShotBox();
		System.out.println("\nHay nhap gia tri Content cua 'Guest said:' vao:");
		//Luu danh sach ShotBot vao Json
		dao.insertShotBoxJson(sb1);
		//dao.insertShotBox(sb1);
		List<ShotBox> shotBox2 = dao.selectShowBox();
		JsonController js= new JsonController();
		js.getOjectToJson(shotBox2);
		if (shotBox2.isEmpty()) {
			return new ResponseEntity(HttpStatus.NO_CONTENT);
			// You many decide to return HttpStatus.NOT_FOUND
		}
		return new ResponseEntity<List<ShotBox>>(shotBox2, HttpStatus.OK);
	}

//	@RequestMapping(value ="/shouts", method= RequestMethod.POST)
//	public String method2(Map<String, Object> model) {
//		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
//		QueryForListReturnListDAO dao = context.getBean(QueryForListReturnListDAO.class);
//		ShotBox sb1 = new ShotBox();
//		System.out.println("\nHay nhap gia tri Content cua 'Guest said:' vao:");
//		//Luu danh sach ShotBot vao Json
//		dao.insertShotBoxJson(sb1);
//		//dao.insertShotBox(sb1);
//		List<ShotBox> shotBox2 = dao.selectShowBox();
//		JsonController js= new JsonController();
//		js.getOjectToJson(shotBox2);
//		for (ShotBox sb : shotBox2) {
//			model.put("message",sb.getUserName() + " said: (at " + sb.getDateTimeText() + "): " + sb.getContent());
//		}
//		return "welcome";
//	}

}