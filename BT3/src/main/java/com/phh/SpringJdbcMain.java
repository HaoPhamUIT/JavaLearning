package com.phh; /**
 * Created by hao-pham on 6/12/17.
 */

import java.sql.SQLException;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.phh.config.AppConfiguration;
import com.phh.dao.QueryForListReturnListDAO;
import com.phh.model.ShotBox;
import com.phh.objectjson.JsonController;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringJdbcMain {

    public static void main(String[] args) throws SQLException {
        //dung de config voi data suorce
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
        QueryForListReturnListDAO dao = context.getBean(QueryForListReturnListDAO.class);
        ShotBox sb1 = new ShotBox();
        //select ra 1 list shotbox luc dau
        List<ShotBox> shotBox = dao.selectShowBox();
        for (ShotBox sb : shotBox) {
            System.out.println(sb.getUserName() + " said: (at " + sb.getDateTimeText() + "): " + sb.getContent());
        }

        System.out.println("\nHay nhap gia tri Content cua 'Guest said:' vao:");

        //Luu danh sach ShotBot vao Json
        dao.insertShotBoxJson(sb1);
        //dao.insertShotBox(sb1);
        List<ShotBox> shotBox2 = dao.selectShowBox();
        //Chuyen tat ca cac shotbox vao json file
        JsonController js= new JsonController();
        js.getOjectToJson(shotBox2);

        /*for (ShotBox sb : shotBox2) {
            System.out.println(sb.getUserName() + " said: (at " + sb.getDateTimeText() + "): " + sb.getContent());
        }*/
        /* Dung Map de sellect
        List<Map<String, Object>> list = dao.queryForList_ListMap();
        System.out.println("Gia tri cua shotbox truoc khi insert:");
        for (Map<String, Object> map : list) {
            System.out.println("\n-----");
            System.out.print(map.get("username") + " said: (at" + map.get("datetime_text") + "): " + map.get("content"));
                for (String key : map.keySet()) {
                    System.out.print( map.get(key));
                    if(key.equals("username")) {
                        System.out.print(" said (at ");
                    }
                    if(key.equals("datetime_text")){
                        System.out.print("ICT):");
                    }
                }
        }
        list = dao.queryForList_ListMap();
        for (Map<String, Object> map : list) {
            System.out.println("\n----");
            for (String key : map.keySet()) {
                System.out.print(map.get(key));
                if (key.equals("username")) {
                    System.out.print(" said ( at ");
                }
                if (key.equals("datetime_text"))
                    System.out.print("):");
            }
        }*/
    }

}