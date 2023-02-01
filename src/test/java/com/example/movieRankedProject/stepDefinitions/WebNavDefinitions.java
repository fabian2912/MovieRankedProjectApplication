package com.example.movieRankedProject.stepDefinitions;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class WebNavDefinitions {

    WebDriver webDriver;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
    }

    @After
    public void tearDown() throws InterruptedException {
        Thread.sleep(2000);
        if(webDriver != null) {
            webDriver.close();
        }
    }

    @Given("I have a browser open")
    public void iHaveABrowserOpen() {
        webDriver = new ChromeDriver();

//        ChromeOptions options = new ChromeOptions();
//       options.addArguments("--headless");
//        webDriver = new ChromeDriver(options);
    }

    @When("I navigate to {string}")
    public void iNavigateTo(String url) {
        webDriver.get(url);
    }

    @Then("I am on the {string} page")
    public void iAmOnThePage(String title) {
        String pageTitle = webDriver.getTitle();
        assertTrue(pageTitle.contains(title));
    }

}
