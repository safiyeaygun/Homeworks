package Homework.tasks;

import Homework.utilities.BrowserUtils;
import Homework.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class Task6 {

    static WebDriver driver;
    private String urlEmail = "https://www.tempmailaddress.com/";
    private By emailBy = By.id("email");
    private String urlCybertek = "https://practice-cybertekschool.herokuapp.com";
    private By mailingListBy = By.linkText("Sign Up For Mailing List");
    private By fullNameBy = By.name("full_name");
    private By signUpPageEmailBy = By.name("email");
    private By signupBy = By.name("wooden_spoon");
    private By signupMessageBy = By.xpath("//*[text()='Thank you for signing up. Click the button below to return to the home page.']");
    // private By doNotReplyBy = By.xpath("//tr//td[text()=\"Thanks for subscribing to practice.cybertekschool.com!\"]");
    private By emailIsFromBy = By.id("odesilatel");
    private By subjectBy = By.id("predmet");

    //Test case #6
    @Test
    public void verifySubscribeMessage(){

        driver = Driver.getDriver();

        //Step 1. Go to "https://www.tempmailaddress.com/"
        driver.get(urlEmail);
        driver.manage().window().maximize();

        //Step 2. Copy and save email as a string.
        String email = driver.findElement(emailBy).getText();

        //Step 3. Then go to “https://practice-cybertekschool.herokuapp.com”
        driver.get(urlCybertek);
        driver.manage().window().maximize();

        //Step 4. And click on “Sign Up For Mailing List"
        driver.findElement(mailingListBy).click();

        //Step 5. Enter any valid name.
        driver.findElement(fullNameBy).sendKeys("Naomi Thampson");

        //Step 6. Enter email from the Step 2.
        driver.findElement(signUpPageEmailBy).sendKeys(email);

        //Step 7. Click Sign Up
        driver.findElement(signupBy).click();

        //Step 8. Verify that following message is displayed:
        //“Thank you for signing up. Click the button below to return to the home page.”
        WebElement signupMessage = driver.findElement(signupMessageBy);
        assertTrue(signupMessage.isDisplayed(), "Sign up message is not displayed");
        BrowserUtils.wait(3);

        //Step 9. Navigate back to the “https:// www.tempmailaddress.com/”
        driver.navigate().to(urlEmail);

        //Step 10. Verify that you’ve received an email from
        //“do-not-reply@practice.cybertekschool.com”
        WebElement emailAd = driver.findElement(By.xpath("//tbody[@id='schranka']/tr[1]/td[1]"));
        System.out.println("emailAd.isDisplayed() = " + emailAd.isDisplayed());
        String actual = emailAd.getText().trim();
        String expected = "do-not-reply@practice.cybertekschool.com";
        assertEquals(actual, expected);


        //  Step 11. Click on that email to open it.
        BrowserUtils.wait(2);
        emailAd.click();

        //Step 12. Verify that email is from: “do-not- reply@practice.cybertekschool.com”
        WebElement emailFromCybertek = driver.findElement(emailIsFromBy);
        String expected2 = "do-not-reply@practice.cybertekschool.com";
        String actual2 = emailFromCybertek.getText();
        assertEquals(actual2, expected2);

        //Step 13. Verify that subject is: “Thanks for subscribing to practice.cybertekschool.com!”
        WebElement subject = driver.findElement(subjectBy);
        String expected3 = "Thanks for subscribing to practice.cybertekschool.com!";
        String actual3 = subject.getText();
        assertEquals(actual3,expected3);

        driver.quit();
    }
}