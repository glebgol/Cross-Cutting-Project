package reactuitest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class BaseSeleniumTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driver = new ChromeDriver();
        BaseSeleniumPage.setDriver(driver);
    }

    @AfterMethod
    public void tearDown() {
        //driver.close();
    }

    @AfterClass
    public void quit() {
        //driver.quit();
    }
}
