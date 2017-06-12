package com.howtoprogram.retrofit2;

import com.object.Todo;
import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * Created by my-tran on 5/30/17.
 */
public class JsonToObject {
    public static void main(String[] args) {

        ObjectMapper mapper = new ObjectMapper();

        Reader reader = null;
        try {
            reader = new FileReader("user.json");

           /* Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl("https://jsonplaceholder.typicode.com/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
            RetrofitTodo resclient= retrofit.create(RetrofitTodo.class);
            Call<Todo> call = resclient.getTodo();
            Response<Todo> response = call.execute();
            Todo resObject = response.body();
            */
            Todo Res = mapper.readValue(reader, Todo.class);
            System.out.println(Res.getTitle());


        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}