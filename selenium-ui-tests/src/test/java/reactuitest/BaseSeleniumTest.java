package reactuitest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.HashMap;

public abstract class BaseSeleniumTest {
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        ChromeOptions options = new ChromeOptions();
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_setting.popups", 0);

        String downloadFilePath = System.getProperty("user.dir");
        chromePrefs.put("download.default_directory", downloadFilePath);
        options.setExperimentalOption("prefs", chromePrefs);

        driver = new ChromeDriver(options);
        BaseSeleniumPage.setDriver(driver);
    }

    @AfterMethod
    public void tearDown() {
        driver.close();
    }

    @AfterClass
    public void quit() {
        driver.quit();
    }
}
