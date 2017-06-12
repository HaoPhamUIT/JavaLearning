package JsonToJava;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Map;

/**
 * Created by my-tran on 5/29/17.
 */
public class JsonToMap {
    public static void main(String[] args) {

        Gson gson = new Gson();
        //String json = "{\"name\":\"mkyong\", \"age\":33}";

        try (Reader reader = new FileReader("staff.json")) {
            // Convert JSON to Java StaffObject
            Map<String, Object> map = gson.fromJson(reader, new TypeToken<Map<String, Object>>() {
            }.getType());
            map.forEach((x, y) -> System.out.println("key : " + x + " , value : " + y));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
