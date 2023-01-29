import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.http.*;
import java.util.HashMap;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class HTTPRequests {
    int id;
    @Test(priority = 1)
    public void getUser()
    {
        given().
        when().
                get("https://reqres.in/api/users?page=2")
        .then()
                .statusCode(200)
                .body("page",equalTo(2))
                .log().all();
    }

    @Test(priority = 2,dataProvider ="emp_data_provider" )
//    @Test(priority = 2)
    public void createUser(String ename,String ejob)
    {
        HashMap data=new HashMap();
        data.put("name",ename);
        data.put("job",ejob);

        id=given()
                .contentType("application/json")
                .body(data)
        .when()
                .post("https://reqres.in/api/users")
                .jsonPath().getInt("id");
//        .then()
//                .statusCode(201).log().all();
    }
    @DataProvider(name="emp_data_provider")
    String [][] getEmpData()
    {
        String emp_data[][]={ {"x123","teacher"},{"xs123","farmer"}};
        return(emp_data);
    }

    @Test(priority = 3,dependsOnMethods = {"createUser"})
    public void updateUser()
    {
        HashMap data1=new HashMap();
        data1.put("name","john");
        data1.put("job","Farmer");

        given()
                .contentType("application/json")
                .body(data1)
        .when()
                .put("https://reqres.in/api/users/"+id)

        .then()
                .statusCode(200).log().all();
    }

    @Test(priority = 4)
    public void deleteUser()
    {
        given()
        .when()
                .delete("https://reqres.in/api/users/"+id)
        .then()
                .statusCode(204)
                .log().all();
    }

}
