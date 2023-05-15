package test;

import com.google.gson.Gson;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.PostBody;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class PUTMethod {
    public static void main(String[] args) {
        //Request specification object
        String baseUri = "https://jsonplaceholder.typicode.com/";
        RequestSpecification req = given();
        req.baseUri(baseUri);

        //Content-type => Header
        req.header(new Header("Content-type", "application/json; charset=UTF-8"));

        //Contruct body
        PostBody postBody = new PostBody();
        postBody.setUserId(1);
        postBody.setTitle("new title");
        postBody.setBody("new body");

        //Gson
        Gson gson = new Gson();
        String id = "1";
        String postBodyStr = gson.toJson(postBody);
        Response res = req.body(postBodyStr).put("/posts/" + id);
        res.then().body("userId", equalTo(1));
        res.then().body("title", equalTo("new title"));
        res.then().body("body", equalTo("new body"));
    }
}
