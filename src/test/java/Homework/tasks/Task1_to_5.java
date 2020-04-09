package Homework.tasks;


import Homework.utilities.BrowserUtils;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Task1_to_5 {

    /*
    Test case #1
    Step 1. Go to “https://practice-cybertekschool.herokuapp.com”
    Step 2. Click on “Registration Form”Step
    3. Enter “wrong_dob” into date of birth input box.Step
    4. Verify that warning message is displayed: “The date of birth is not valid”
     */
    private WebDriver driver;
    private By registrationButton = By.xpath("//a[text()='Registration Form']");
    private By message = By.xpath("//small[text()='The date of birth is not valid']");
    private By warningFirstName = By.xpath("//small[text()='first name must be more than 2 and less than 64 characters long']");
    private By warningLastName = By.xpath("//small[text()='The last name must be more than 2 and less than 64 characters long']");
    private By firstName = By.name("firstname");
    private By lastName = By.name("lastname");
    private By userName = By.name("username");
    private By email = By.name("email");
    private By password = By.name("password");
    private By phone = By.name("phone");
    private By gender = By.xpath("//input[@value='female']");
    private By birthdayButton = By.xpath("//input[@data-bv-field='birthday']");
    private By signUp = By.id("wooden_spoon");

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().version("79").setup();
        driver = new ChromeDriver();
        driver.get("https://practice-cybertekschool.herokuapp.com");
        driver.manage().window().maximize();
        BrowserUtils.wait(2);
    }

    @Test
    public void invalidMessage() {
        driver.findElement(registrationButton).click();
        driver.findElement(birthdayButton).sendKeys("wrong_dob", Keys.ENTER);
        String actual = driver.findElement(message).getText();
        String expected = "The date of birth is not valid";

        Assert.assertEquals(actual, expected);

    }

    @AfterMethod
    public void closeBrowser() {
        driver.quit();
    }

    @Test
    public void displayed() {
        driver.findElement(registrationButton).click();
        driver.findElement(birthdayButton).sendKeys("wrong_dob", Keys.ENTER);
        WebElement warningMessage = driver.findElement(message);
        Assert.assertTrue(warningMessage.isDisplayed());

    }
    /*
    Test case #2
    Step 1. Go to “https://practice-cybertekschool.herokuapp.com”
    Step 2. Click on “Registration Form”
    Step 3. Verify that following options for programming languages are displayed: c++, java, JavaScript
     */
    @Test
    public void language(){
        driver.findElement(registrationButton).click();


    }


/*
Test case #3 Step 1. Go to “https://practice-cybertekschool.herokuapp.com”
Step 2. Click on “Registration Form”
Step 3. Enter only one alphabetic character into first name input box.
Step 4. Verify that warning message is displayed: “first name must be more than 2 and less than 64 characters long”
 */

    @Test
    public void alphabetical(){
        driver.findElement(registrationButton).click();
        BrowserUtils.wait(2);
        driver.findElement(firstName).sendKeys("w" , Keys.ENTER);
        WebElement warning = driver.findElement(warningFirstName);
        Assert.assertTrue(warning.isDisplayed());
    }

    /*
    Test case #4
    Step 1. Go to https://practice-cybertekschool.herokuapp.com
    Step 2. Click on “Registration Form”
    Step 3. Enter only one alphabetic character into last name input box.
    Step 4. Verify that warning message is displayed: “The last name must be more than 2 and less than 64 characters long”
     */
    @Test
    public void lastName(){
        driver.findElement(registrationButton).click();
        BrowserUtils.wait(2);
        driver.findElement(lastName).sendKeys("w" , Keys.ENTER);
        WebElement warning = driver.findElement(warningLastName);
        Assert.assertTrue(warning.isDisplayed());

    }

    /*
    Test case #5
    Step 1. Go to “https://practice-cybertekschool.herokuapp.com”
    Step 2. Click on “Registration Form”
    Step 3. Enter any valid first name.
    Step 4. Enter any valid last name.
    Step 5. Enter any valid user name.
    Step 6. Enter any valid password.
    Step 7. Enter any valid phone number.
    Step 8. Select gender.
    Step 9. Enter any valid date of birth.
    Step 10. Select any department.
    Step 11. Enter any job title.
    Step 12. Select java as a programming language.
    Step 13. Click Sign up.Step 14. Verify that following success message is displayed: “You've successfully completed registration!”
    Note: for using dropdown, please refer to the documentation:
    https://selenium.dev/selenium/docs/api/java/org/openqa/selenium/support/ui/Select.html or,
     please watch short video about drop-downs that is posted on canvas.
     */
    @Test
    public void Form(){
        driver.findElement(registrationButton).click();
        BrowserUtils.wait(2);
        driver.findElement(firstName).sendKeys("Safiye");
        driver.findElement(lastName).sendKeys("Aygun");
        driver.findElement(userName).sendKeys("safiyeaygun");
        driver.findElement(email).sendKeys("test@email.com");
        driver.findElement(password).sendKeys("melihaaygun");
        driver.findElement(phone).sendKeys("500-355-4444");
        driver.findElement(gender).click();
        driver.findElement(birthdayButton).sendKeys("01/01/2222");

        Select selectDepartment = new Select(driver.findElement(By.name("department")));
        selectDepartment.selectByVisibleText("Department of Engineering");

        Select selectJob = new Select(driver.findElement(By.name("job_title")));
        selectJob.selectByVisibleText("SDET");
        driver.findElement(By.id("inlineCheckbox2")).click(); //java
        BrowserUtils.wait(3);
        driver.findElement(signUp).click();

        String expected = "You've successfully completed registration!";
        String actual = driver.findElement(By.tagName("p")).getText();

        Assert.assertEquals(actual, expected);

    }




}