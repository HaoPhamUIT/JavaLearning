package JsonToObject;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Created by my-tran on 5/29/17.
 */
public class JsonToObject {
    public static void main(String[] args) {

        ObjectMapper mapper = new ObjectMapper();

        Reader reader = null;
        try {
            reader = new FileReader("user.json");
            // Convert JSON string from file to Object
            User user = mapper.readValue(reader, User.class);
            System.out.println(user.getName());
            System.out.println(user);

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

    }
}

