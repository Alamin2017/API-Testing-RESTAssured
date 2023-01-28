import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.http.*;
import java.util.HashMap;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class POSTRequests {

    @Test(priority = 1)
    public void testPostRequest()
    {
        HashMap data=new HashMap();
        data.put("name","Scott");
        data.put("location","France");
        data.put("phone","123456");

        String courseArr[]={"C","C++"};
        data.put("courses",courseArr);
        baseURI="http://localhost:3000";
        given()
                .contentType("application/json")
                .body(data)
        .when()
                .post("/students")
        .then()
                .statusCode(201)
                .body("name",equalTo("Scott"))
                .body("location",equalTo("France"))
                .body("phone",equalTo("123456"))
                .body("courses[0]",equalTo("C"))
                .body("courses[1]",equalTo("C++"))
                .log().all();
    }
    @Test(priority = 2)
    public void testDelete()
    {
         given()
        .when()
                .delete("http://localhost:3000/students/34")
        .then()
                .statusCode(200).log().all();

    }


}
