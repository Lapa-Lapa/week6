package com.epam.atm.tests;

import com.epam.atm.utils.Logger;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class WebServicesTests {

    @BeforeTest
    public void initTest() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
        Logger.info("Afisha page is open");
    }

    @Test
    public void checkStatusCode() {
        Assert.assertEquals(given().get("/users").andReturn().getStatusCode(), 200);
    }

    @Test
    public void checkResponseHeader() {
        Assert.assertTrue(given().get("/users").andReturn().getHeader("content-type").contains("application/json; charset=utf-8"));
    }

    @Test
    public void checkResponseBody() {
        Response resp = given().get("/users").andReturn();
        RespBody[] respBody = resp.as(RespBody[].class);
        System.out.println(respBody.toString());
        Assert.assertEquals(respBody.length, 10);
    }
}
