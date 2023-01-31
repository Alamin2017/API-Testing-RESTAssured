import com.github.javafaker.Faker;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class Authentications {

    @Test(priority = 1)
    public void basicAuthentication()
    {
        given()
                .auth().basic("postman","password")
        .when()
                .get("https://postman-echo.com/basic-auth")
        .then()
                .statusCode(200)
                .body("authenticated",equalTo(true))
                .log().all();
    }
    @Test(priority = 2)
    public void digestAuthentication()
    {
        given()
                .auth().digest("postman","password")
        .when()
                .get("https://postman-echo.com/basic-auth")
        .then()
                .statusCode(200)
                .body("authenticated",equalTo(true))
                .log().all();
    }
    @Test(priority = 3)
    public void bearerAuthentication()
    {
        String bearerToken="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";
        given()
                //.headers("Authorization","Bearer"+bearerToken)
                .headers("Authorization","Bearer "+bearerToken)

        .when()
                .get("https://api.github.com/user/repos")
        .then()
                .statusCode(200)
                .log().all();
    }
    @Test(priority = 4)
    public void apiKeyAuthentication()
    {
//        given()
//                .queryParam("appid","fe9c5cddb7e01d747b4611c3fc9eaf2c")
//        .when()
//                .get("https://api.openweathermap.org/data/2.5/forecast/daily?q=Delhi&units=metric&cnt=7")
//        .then()
//                .statusCode(200)
//                .log().all();
        given()
                .queryParam("appid","fe9c5cddb7e01d747b4611c3fc9eaf2c")
                .pathParam("mypath","data/2.5/forecast/daily")
                .queryParam("q","Delhi")
                .queryParam("units","metric")
                .queryParam("cnt","7")
        .when()
                .get("https://api.openweathermap.org/{mypath}")
        .then()
                .statusCode(200)
                .log().all();
    }
    @Test(priority = 5)
    public void testGeneratorDummyData()
    {
        Faker faker=new Faker();
        String fullName=faker.name().fullName();
        String firstName=faker.name().firstName();
        String lastName=faker.name().lastName();
        String username=faker.name().username();
        String password=faker.internet().password();
        String phone_no=faker.phoneNumber().cellPhone();
        String email=faker.internet().safeEmailAddress();
        System.out.println("Full Name:"+fullName);
        System.out.println("First Name:"+firstName);
        System.out.println("Last Name:"+lastName);
        System.out.println("Username:"+username);
        System.out.println("Password:"+password);
        System.out.println("Phone no:"+phone_no);
        System.out.println("Email:"+email);
    }
}
