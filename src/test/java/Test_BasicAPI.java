import io.restassured.RestAssured;
import io.restassured.authentication.PreemptiveBasicAuthScheme;
import io.restassured.http.*;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class Test_BasicAPI {
    @Test(priority = 1)
    public void test_1() {
        Response response = RestAssured.get("https://reqres.in/api/users?page=2");
        System.out.println(response.statusCode());
        System.out.println(response.getTime());
        System.out.println(response.getBody().asString());
        System.out.println(response.getStatusLine());
        System.out.println(response.getHeader("content-type"));
        int status_code = response.statusCode();
        Assert.assertEquals(status_code, 200);
    }
    @Test(priority = 2)
    public void test_2() {
        baseURI = "https://reqres.in/api";
        given().
                get("/users?page=2").
                then().
                statusCode(200).
                body("data[1].id", equalTo(8))
                .log().all();
    }
    @Test(priority = 3)
    public void testGet() {
        baseURI = "https://reqres.in/api";
        given().
                get("/users?page=2").
        then().
                statusCode(200).
                body("data[4].first_name", equalTo("George")).
                body("data.first_name", hasItem("George"));
    }
    @Test(priority = 4)
    public void testPost() {

        JSONObject request=new JSONObject();
        request.put("name","Raghav");
        request.put("job","Teacher");
        System.out.println(request.toJSONString());
        baseURI="https://reqres.in/api";
        given().
                header("Content-Type","application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
        when().
               post("/users").
        then().
                statusCode(201).log().all();
    }
    @Test(priority = 5)
    public void testPut()
    {
        JSONObject request=new JSONObject();
        request.put("name","Raghav");
        request.put("job","Teacher");
        System.out.println(request.toJSONString());
        baseURI="https://reqres.in/api";
        given().
                header("Content-Type","application/json").
                contentType(ContentType.JSON).
                accept(ContentType.JSON).
                body(request.toJSONString()).
        when().
                put("/users/2").
        then().
                statusCode(200).log().all();
    }
    @Test(priority = 6)
    public void testDelete()
    {
        baseURI="https://reqres.in";
        when().
                delete("api/users/2").
        then().
                statusCode(204).log().all();
    }
    @Test(priority = 7)
    public void test_Get_api()
    {
        baseURI="https://reqres.in/api";
        RequestSpecification httpRequest=RestAssured.given();
        Response response=httpRequest.request(Method.GET,"users?page=2");
        String responseBody=response.getBody().asString();
        System.out.println("Response Body is:"+responseBody);
    }
    @Test(priority = 8)
    public void test_Post_Api()
    {
        baseURI="https://reqres.in/api";
        RequestSpecification httpRequest=RestAssured.given();
        JSONObject requestParams=new JSONObject();
        requestParams.put("name","Al-Amin");
        requestParams.put("job","Engineer");
        httpRequest.body(requestParams.toJSONString());
        httpRequest.header("Content-Type","application/json");
        Response response=httpRequest.request(Method.POST,"/users");
        String responseBody=response.getBody().asString();
        System.out.println("Response Body is:"+responseBody);
        int statusCode=response.getStatusCode();
        System.out.println("Status code is:"+statusCode);
    }
    @Test(priority = 9)
    public void HeaderTest() {
        RestAssured.baseURI = "https://bookstore.toolsqa.com";
        RequestSpecification httpRequest = RestAssured.given();
        Response response = httpRequest.request(Method.GET, "/BookStore/v1/Books");
        String responseBody = response.getBody().asString();
        System.out.println("Response Body is:" + responseBody);
        Headers all_headers = response.headers();
        for (Header header : all_headers) {
            System.out.println(header.getName() + "->>>" + header.getValue());
        }
    }
    @Test(priority = 10)
    public void JsonTest()
    {
            RestAssured.baseURI="https://reqres.in";
            RequestSpecification httpRequest=RestAssured.given();
            Response response=httpRequest.request(Method.GET,"/api/users?page=2");
            JsonPath jsonpath =response.jsonPath();
            System.out.println((int) jsonpath.get("page"));
    }
    @Test(priority = 11)
    public void AuthenticationTest()
    {
        RestAssured.baseURI="https://postman-echo.com/basic-auth";
        PreemptiveBasicAuthScheme authscheme=new PreemptiveBasicAuthScheme();
        authscheme.setUserName("postman");
        authscheme.setPassword("password");
        authentication=authscheme;
        RequestSpecification httpRequest=RestAssured.given();
        Response response=httpRequest.request(Method.GET,"/");
        String responseBody=response.getBody().asString();
        System.out.println("Response Body is:"+responseBody);
    }

}