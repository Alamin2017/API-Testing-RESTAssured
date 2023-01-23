import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class Test_DataDriven {

    @Test(dataProvider ="emp_data_provider" )
    public void postNewEmployee(String ename,String esal,String eage)
    {
        RestAssured.baseURI="https://dummy.restapiexample.com";
        RequestSpecification httpRequest=RestAssured.given();
        JSONObject requestParams=new JSONObject();
        requestParams.put("name",ename);
        requestParams.put("salary",esal);
        requestParams.put("age",eage);
        httpRequest.header("Content-Type","application/json");
        httpRequest.body(requestParams.toJSONString());
        Response response=httpRequest.request(Method.POST,"/api/v1/create");
        String responseBody=response.getBody().asString();
        System.out.println("Response Body:"+responseBody);


    }
    @DataProvider(name="emp_data_provider")
    String [][] getEmpData()
    {
        String emp_data[][]={ {"x123","30231","90"},{"xs123","45678","91"}};
        return(emp_data);
    }

}
