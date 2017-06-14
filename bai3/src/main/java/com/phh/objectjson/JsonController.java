package com.phh.objectjson;


import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.phh.model.ShotBox;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * Created by my-tran on 5/29/17.
 */
public class JsonController {

public  void getOjectToJson(List<ShotBox> sb) {
    ObjectMapper mapper = new ObjectMapper();
    try {
        //Convert object to JSON string and save into file directly
        mapper.writeValue(new File("shotbox.json"), sb);

        //Convert object to JSON string
        String jsonInString = mapper.writeValueAsString(sb);
        System.out.println(jsonInString);

        //Convert object to JSON string and pretty print
        jsonInString = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(sb);
        System.out.println(jsonInString);
    } catch (JsonGenerationException e) {
        e.printStackTrace();
    } catch (JsonMappingException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    }
}
    public ShotBox postJsonToObject(ShotBox sb) {
        ObjectMapper mapper = new ObjectMapper();

        Reader reader = null;
        try {
            reader = new FileReader("shotboxpost.json");
            // Convert JSON string from file to Object
            sb = mapper.readValue(reader, ShotBox.class);
            // Convert JSON string to Object
         /*   String jsonInString = "{\"age\":33,\"messages\":[\"msg 1\",\"msg 2\"],\"name\":\"mkyong\"}";
            User user1 = mapper.readValue(jsonInString, User.class);
            System.out.println(user1.getName());*/

        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb;
    }

}