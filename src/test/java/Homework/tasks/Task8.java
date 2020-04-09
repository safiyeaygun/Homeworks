package Homework.tasks;

import Homework.utilities.BrowserUtils;
import Homework.utilities.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Task8 {

    /*
    Test case #8
    Step 1. Go to “https://practice- cybertekschool.herokuapp.com”
    Step 2. And click on “Autocomplete”.
    Step 3. Enter “United States of America” into country input box.
    Step 4. Verify that following message is displayed: “You selected: United States of America”
     */

    private WebDriver driver;
    private By autocompleteBy = By.linkText("Autocomplete");
    private By countryInputBoxBy = By.id("myCountry");
    private By submitBy = By.cssSelector("[class=\"btn btn-primary\"]");
    private
    By verifyMessageBy = By.cssSelector("#result");

    //Test case #8
    @Test
    public void autocompleteTest(){
        //Step 2. And click on “Autocomplete”.
        driver.findElement(autocompleteBy).click();
        //Step 3. Enter “United States of America” into country input box.
        driver.findElement(countryInputBoxBy).sendKeys("United States of America");
        BrowserUtils.wait(2);
        driver.findElement(submitBy).click();
        //Step 4. Verify that following message is displayed: “You selected: United States of America”
        WebElement verifyMessage = driver.findElement(verifyMessageBy);
        //verify message is displayed
        Assert.assertTrue(verifyMessage.isDisplayed());

        String actual = verifyMessage.getText();
        String expected = "You selected: United States of America";
        Assert.assertEquals(actual,expected, "Message is not displayed");

        System.out.println("expected = " + expected);
        System.out.println("actual = " + actual);
    }

    @BeforeMethod
    public void setup(){
        driver = DriverFactory.createDriver("chrome");
        //Step 1. Go to “https://practice- cybertekschool.herokuapp.com”
        driver.get("https://practice-cybertekschool.herokuapp.com");
        BrowserUtils.wait(2);
    }

    @AfterMethod
    public void teardown(){
        BrowserUtils.wait(2);
        if(driver != null){
            driver.quit();
            driver = null;
        }
    }
}