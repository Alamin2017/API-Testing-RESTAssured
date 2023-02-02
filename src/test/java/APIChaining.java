import com.github.javafaker.Faker;
import org.json.simple.JSONObject;
import org.testng.ITestContext;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class APIChaining {

    @Test(priority = 1)
    public void test_CreateUser(ITestContext context)
    {
        Faker faker=new Faker();
        JSONObject data=new JSONObject();
        data.put("name",faker.name().fullName());
        data.put("gender","Male");
        data.put("email",faker.internet().emailAddress());
        data.put("status","inactive");
        String bearerToken="a21a193976a0c106e157d2beafc285b4505bd5db1b86d4f5014f4ea3de62ba4a";
        int id=given()
                .headers("Authorization","Bearer "+bearerToken)
                .contentType("application/json")
                .body(data.toString())
        .when()
                .post("https://gorest.co.in/public/v2/users")
                .jsonPath().getInt("id");
//        .then()
//                .statusCode(201)
//                .log().all();
        System.out.println("Generated id is:"+id);
        context.getSuite().setAttribute("user_id",id);
    }
    @Test(priority = 2)
    public void test_getUser(ITestContext context)
    {
        int id = (Integer) context.getSuite().getAttribute("user_id");
        String bearerToken="a21a193976a0c106e157d2beafc285b4505bd5db1b86d4f5014f4ea3de62ba4a";
        given()
                .headers("Authorization","Bearer "+bearerToken)
                .pathParam("pid",id);
        when()
                .get("https://gorest.co.in/public/v2/users/{id}")
        .then()
                .statusCode(200).log().all();

    }



}
