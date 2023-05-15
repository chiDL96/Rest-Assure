package test;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

public class GETMethod {
    public static void main(String[] args) {
        String baseUri = "https://jsonplaceholder.typicode.com/";

        //request scope
        RequestSpecification req = given();
        req.baseUri(baseUri);
        req.basePath("/todos");

        //responsive
        final String FIRST_TODO = "/1";
        Response res = req.get(FIRST_TODO);
        res.prettyPrint();

        //verify

        res.then().body("userId", equalTo(1));
        res.then().body("title", equalTo("delectus aut autem"));
        res.then().body("completed", equalTo(true));
    }
}
