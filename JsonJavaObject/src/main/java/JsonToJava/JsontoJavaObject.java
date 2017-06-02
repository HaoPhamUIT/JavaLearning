package JsonToJava; /**
 * Created by my-tran on 5/29/17.
 */
import com.google.gson.Gson;

import java.io.*;

public class JsontoJavaObject {

        public static void main(String[] args) {

            Gson gson = new Gson();
            Reader reader = null;
            try {
                reader = new FileReader("staff.json");
                // Convert JSON to Java StaffObject
                StaffObject.Staff staff = gson.fromJson(reader, StaffObject.Staff.class);
                System.out.println(staff.getName());
                System.out.println(staff.getAge());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

    }
