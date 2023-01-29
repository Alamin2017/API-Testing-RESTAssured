import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import io.restassured.http.*;
import java.util.HashMap;
import java.util.Map;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
public class PathQueryParams {

    @Test(priority = 1)
    public void testQueryAndPathParams()
    {
        given()
                .pathParams("myPath","users")
                .queryParam("page",2)
                .queryParam("id",5)
        .when()
                .get("https://reqres.in/api/{myPath}")
        .then()
                .statusCode(200).log().all();
    }
    @Test(priority = 2)
    public void testCookiesAndHeader()
    {
        Response res=given()
        .when()
                .get("https://www.google.com/");
//        String cookies_value=res.getCookie("AEC");
//        System.out.println("value of cookie is====>"+cookies_value);
        Map<String,String> cookies_values=res.getCookies();
        for(String k:cookies_values.keySet())
        {
            String cookie_value=res.getCookie(k);
            System.out.println(k+"     "+cookie_value);
        }
//        String header_value=res.getHeader("Content-Type");
//        System.out.println("The value of Header=====>"+header_value);

        Headers myHeaders=res.getHeaders();
        System.out.println("Header Test Starting Point");
        for(Header hd:myHeaders)
        {
            System.out.println(hd.getName()+"    "+hd.getValue());
        }

    }
    @Test(priority = 3)
    public void testXMLResponse()
    {
        Response res=given()

                .when()
                .get("http://restapi.adequateshop.com/api/Traveler?page=1");

        Assert.assertEquals(res.getStatusCode(),200);

        String pageNo=res.xmlPath().get("TravelerinformationResponse.page").toString();
        Assert.assertEquals(pageNo,"1");

        String travelName=res.xmlPath().get("TravelerinformationResponse.travelers.Travelerinformation[0].name").toString();
        Assert.assertEquals("Developer",travelName);





    }


}
