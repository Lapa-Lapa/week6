package com.epam.atm.tests;

import com.epam.atm.utils.Logger;
import io.restassured.RestAssured;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

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
        SoftAssert softAssert = new SoftAssert();
        User[] users = given().get("/users").andReturn().as(User[].class);
        for (int i = 0; i < users.length; i++) {
            softAssert.assertNotNull(users[i].getId());
            softAssert.assertFalse(users[i].getName().isEmpty());
            softAssert.assertFalse(users[i].getUsername().isEmpty());
        }
        softAssert.assertEquals(users.length, 10);
        softAssert.assertAll();
    }
}