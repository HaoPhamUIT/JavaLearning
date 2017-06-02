package com.howtoprogram.retrofit2;

import com.object.ResponseBodyEntity;
import com.object.Todo;
import org.junit.Test;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class Retrofit2Test {

    @Test
    public void testPostObject() throws IOException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        BookResourceRetrofit2 bookResource = retrofit.create(BookResourceRetrofit2.class);
       /* Book book = new Book(1l, "Java How To Program", "Paul Deitel");
        Call<Book> call = bookResource.addBook(book);
        Response<Book> response = call.execute();
        assertTrue(response.isSuccessful());*/
       RetrofitClient retrofitClient = retrofit.create(RetrofitClient.class);
       Call<ResponseBodyEntity> call = retrofitClient.get1post();
       Response<ResponseBodyEntity> response = call.execute();
       ResponseBodyEntity resBodyObject = response.body();
        //String output = response.raw().toString();
       System.out.println("ID: "+resBodyObject.getId());
       System.out.println("UserId:"+resBodyObject.getUserId());
       System.out.println("Title: "+resBodyObject.getTitle());
       System.out.println("Body: "+resBodyObject.getBody());



    }

    @Test
    public  void testTodoObject() throws IOException{
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitTodo resclient= retrofit.create(RetrofitTodo.class);
        Call<Todo> call = resclient.getTodo();
        Response<Todo> response = call.execute();
        Todo resObject = response.body();
        System.out.println("ID: "+resObject.getId());
        System.out.println("UserId:"+resObject.getUserId());
        System.out.println("Title: "+resObject.getTitle());
        System.out.println("Complete: "+resObject.getCompleted());
    }
    @Test
    public  void testPostsTodoObject() throws IOException, InterruptedException {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitTodo resclient= retrofit.create(RetrofitTodo.class);
        Todo todo = new Todo(216,217,"Pham hao hao", true);
        Call<Todo> call = resclient.postTodo(todo);
        //Synchronous
       /* Response<Todo> response = call.execute();
        Todo resObject = response.body();
        System.out.println("ID: "+resObject.getId());
        System.out.println("UserId:"+resObject.getUserId());
        System.out.println("Title: "+resObject.getTitle());
        System.out.println("Complete: "+resObject.getCompleted());*/

       //Asynchronous
        call.enqueue(new Callback<Todo>() {
            @Override
            public void onResponse(Call<Todo> call, Response<Todo> response) {
                System.out.println("before");
                int statusCode = response.code();
                Todo todo1 = response.body();
                System.out.println("Code: "+statusCode);
                System.out.println("ID: "+todo1.getId());
                System.out.println("UserId:"+todo1.getUserId());
                System.out.println("Title: "+todo1.getTitle());
                System.out.println("Complete: "+todo1.getCompleted());
            }

            @Override
            public void onFailure(Call<Todo> call, Throwable throwable) {
                System.out.println("Complete: ");
            }
        });
        System.out.println("Complete end ");
        Thread.sleep(3000);
        System.out.println("Complete end after 5s ");
    }
    @Test
    public  void testListTodoObject() throws IOException{
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitTodo resclient= retrofit.create(RetrofitTodo.class);
        Call<List<Todo>> Lscall = resclient.getListTodo();
        //Call<Todo> call = resclient.getListTodo();
        Response<List<Todo>> response = Lscall.execute();
        List<Todo> resObject = response.body();
        System.out.println("ID: "+resObject.get(199).getTitle());

    }
//
//    @Test
//    public void testPostJson() throws IOException {
//
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://httpbin.org/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        BookResourceRetrofit2 bookResource = retrofit.create(BookResourceRetrofit2.class);
//        Book book = new Book(1l, "Java How To Program", "Paul Deitel");
//
//        Call<Book> call = bookResource.addBook(book);
//        Response<Book> response = call.execute();
//
//        assertTrue(response.isSuccessful());
//
//    }
//
//    @Test
//    public void testPostForm() throws IOException {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://httpbin.org/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        BookResourceRetrofit2 service = retrofit.create(BookResourceRetrofit2.class);
//
//        Call<Book> call = service.updateBook(1l, "Tom1", "Riddle1");
//
//        Response<Book> response = call.execute();
//        assertTrue(response.isSuccessful());
//
//
//    }
//    @Test
//    public void testPostFormWithFieldMap() throws IOException {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://httpbin.org/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        BookResourceRetrofit2 service = retrofit.create(BookResourceRetrofit2.class);
//        Map<String, String> fieldsMap = new HashMap<>();
//        fieldsMap.put("author","Joshua Bloch");
//        fieldsMap.put("name","Java puzzlers");
//        fieldsMap.put("id","1");
//        Call<Book> call = service.updateBook(fieldsMap);
//        Response<Book> response = call.execute();
//        System.out.print(response.headers());
//        assertTrue(response.isSuccessful());
//
//    }
//
//    @Test
//    public void testPostMultipart() throws IOException {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://httpbin.org/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        BookResourceRetrofit2 service = retrofit.create(BookResourceRetrofit2.class);
//
//        // create RequestBody instance from file
//        ClassLoader classLoader = getClass().getClassLoader();
//        File file = new File(classLoader.getResource("RestImage.png").getFile());
//
//        RequestBody reqFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
//
//        // MultipartBody.Part is used to send also the actual file name
//        MultipartBody.Part body =
//                MultipartBody.Part.createFormData("file", file.getName(), reqFile);
//
//        // add book id part within the multipart request
//        RequestBody id = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(1l));
//
//        Call<Book> call = service.addBookCover(id, body);
//
//        Response<Book> response = call.execute();
//        assertTrue(response.isSuccessful());
//
//    }
//
//    @Test
//    public void testPostMultipart2() throws IOException {
//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl("http://httpbin.org/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        BookResourceRetrofit2 service = retrofit.create(BookResourceRetrofit2.class);
//
//        // create RequestBody instance from file
//        ClassLoader classLoader = getClass().getClassLoader();
//        File file = new File(classLoader.getResource("RestImage.png").getFile());
//
//        RequestBody reqFile = RequestBody.create(MediaType.parse("multipart/form-data"), file);
//
//        RequestBody id = RequestBody.create(MediaType.parse("text/plain"), String.valueOf(1l));
//
//        Call<Book> call = service.addBookCover2(id, reqFile);
//
//        Response<Book> response = call.execute();
//        assertTrue(response.isSuccessful());
//
//    }

}
