import com.jayway.restassured.response.Header;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import java.io.IOException;
import java.util.Date;
import static com.jayway.restassured.RestAssured.given;

public class TSExample {

    String projectName = "TestSpicerExample";
    String suiteName = "smokeTests";


    public static void main(String[] args) throws Exception {

        TSExample ts = new TSExample();
        ts.testFormCanBeFilledWithValidValues();
    }

    private void testFormCanBeFilledWithValidValues() throws IOException {

        String testName = "testFormCanBeFilled";
        String description = "Test+to+ensure+that+form+can+be+filled+with+different+values";
        String functionalArea = "Validations,Registration, Usability";
        String riskCoverage = "Functional,Reputation,Security";

        String sessionID = TSWrapper.startTest(projectName, suiteName, testName, description, functionalArea, riskCoverage);

        WebDriver driver = new FirefoxDriver();

        // And now use this to visit Google
        driver.get("https://docs.google.com/forms/d/1kj5EbaMQyZ2XVSqgqPK4pfA1G9iEFTBQb3iDAjzm0M4/viewform");


        String randomAlphabets = TSWrapper.getARandomString(sessionID);
        WebElement firstTB = driver.findElement(By.id("entry_1514270459"));
        firstTB.sendKeys(randomAlphabets);

        WebElement secondTB = driver.findElement(By.id("entry_825682212"));
        secondTB.sendKeys(TSWrapper.getARandomUnicodeString(sessionID));

        WebElement thirdTB = driver.findElement(By.id("entry_1950512444"));
        thirdTB.sendKeys(TSWrapper.getARandomAge(sessionID));


        Select monthSelectBox = new Select(driver.findElement(By.id("entry.1227485340_month")));
            monthSelectBox.selectByVisibleText("May");
        Select daySelectBox = new Select(driver.findElement(By.id("entry.1227485340_day")));
            daySelectBox.selectByVisibleText("11");
        Select yearSelectBox = new Select(driver.findElement(By.id("entry.1227485340_year")));
            yearSelectBox.selectByVisibleText(TSWrapper.getARandomDate(sessionID));

        WebElement fourthTB = driver.findElement(By.id("entry_905665605"));
        fourthTB.sendKeys(TSWrapper.getARandomCC(sessionID));

        driver.close();

        TSWrapper.finishTest(sessionID, "pass");


    }



}
