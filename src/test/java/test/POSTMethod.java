package test;
import com.google.gson.Gson;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.PostBody;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;

public class POSTMethod {
    public static void main(String[] args) {
        //Request specification object
        String baseUri = "https://jsonplaceholder.typicode.com/";
        RequestSpecification req = given();
        req.baseUri(baseUri);

        //Content-type => Header
        req.header(new Header("Content-type", "application/json; charset=UTF-8"));

        //Form up request body
//        String postBody = "{\n" +
//                "  \"userId\": 1,\n" +
//                "  \"id\": 1,\n" +
//                "  \"title\": \"new title\",\n" +
//                "  \"body\": \"new body\"}";

        //Gson
        Gson gson = new Gson();
        PostBody postBody = new PostBody();
        postBody.setUserId(1);
        postBody.setTitle("new title1");
        postBody.setBody("new body1");

        //Send POST request
        Response res = req.body(gson.toJson(postBody)).post("/posts");
        res.prettyPrint();

        //Verification
        res.then().statusCode(201);
        res.then().statusLine(containsString("201 Created"));
        res.then().body("userId", equalTo(1));
        res.then().body("title", equalTo("new title1"));
        res.then().body("body", equalTo("new body1"));
    }
}
