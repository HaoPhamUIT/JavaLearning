package JsonToJava;

import StaffObject.Staff;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.List;

/**
 * Created by my-tran on 5/29/17.
 */
public class JsonArraytoList {
    public static void main(String[] args) {

        Gson gson = new Gson();
        try (Reader reader = new FileReader("liststaff.json")) {
            // Convert JSON to Java StaffObject
            List<Staff> Lststaff = gson.fromJson(reader, new TypeToken<List<StaffObject.Staff>>() {
            }.getType());
            Lststaff.forEach(x -> System.out.println(x));

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
