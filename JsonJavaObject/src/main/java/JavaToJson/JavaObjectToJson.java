package JavaToJson;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by my-tran on 5/29/17.
 */
public class JavaObjectToJson {
    public static void main(String[] args) {

        StaffObject.Staff staff = createDummyObject();

        //1. Convert object to JSON string
        Gson gson = new Gson();
        String json = gson.toJson(staff);
        System.out.println(json);

        //2. Convert object to JSON string and save into a file directly
        try (FileWriter writer = new FileWriter("staff.json")) {
            gson.toJson(staff, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static StaffObject.Staff createDummyObject() {

        StaffObject.Staff staff = new StaffObject.Staff();

        staff.setName("haopham123");
        staff.setAge(354);
        staff.setPosition("Founder3");
        staff.setSalary(new BigDecimal("20000"));

        List<String> skills = new ArrayList<>();
        skills.add("java");
        skills.add("python");
        skills.add("c++");

        staff.setSkills(skills);

        return staff;

    }

}

