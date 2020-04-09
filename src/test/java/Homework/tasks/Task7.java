package Homework.tasks;

import Homework.utilities.BrowserUtils;
import Homework.utilities.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class Task7 {

    static WebDriver driver;
    String url = "https://practice-cybertekschool.herokuapp.com";
    private By fileUploadBy = By.linkText("File Upload");
    private By chooseFileBy = By.id("file-upload");
    private By uploadBy = By.id("file-submit");
    private By verifyMessageBy = By.cssSelector("[id=\"content\"]>div>h3");
    private By fileNameBy = By.xpath("//div[contains(text(),'homework.txt')]");


    @BeforeMethod
    public void setup(){
        driver = Driver.getDriver();
        //Step 1. Go to “https://practice- cybertekschool.herokuapp.com”
        driver.get(url);
    }

    //Test case #7
    @Test
    public void fileUpload()  {

        //Step 2. And click on “File Upload".
        driver.findElement(fileUploadBy).click();

        //Step 3. Upload any file with .txt extension from your computer.
        BrowserUtils.wait(2);
        WebElement chooseFile = driver.findElement(chooseFileBy);
        String filePath = "/Users/sedacivan/IdeaProjects/Fall2019Selenium/src/test/java/com/automation/tests/homework_3/homework.txt";
        chooseFile.sendKeys(filePath);
        BrowserUtils.wait(3);

        //Step 4. Click “Upload” button.
        driver.findElement(uploadBy).click();

        //Step 5. Verify that subject is: “File Uploaded!”
        WebElement verifyMessage = driver.findElement(verifyMessageBy);
        String actual = verifyMessage.getText();
        String expected = "File Uploaded!";
        assertEquals(actual,expected);

        // Step 6. Verify that uploaded file name is displayed.
        WebElement fileName = driver.findElement(fileNameBy);
        String actual2 = fileName.getText();
        String expected2 = "homework.txt";
        assertEquals(actual2,expected2);
    }


    @AfterMethod
    public void teardown(){
        if(driver != null){
            driver.quit();
            driver = null;
        }
    }

}