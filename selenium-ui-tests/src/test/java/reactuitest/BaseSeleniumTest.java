package reactuitest;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.util.HashMap;

public abstract class BaseSeleniumTest {
    protected WebDriver driver;
    protected ChromeOptions options;
    private void setUpOptions() {
        options = new ChromeOptions();
        HashMap<String, Object> chromePrefs = new HashMap<>();
        chromePrefs.put("profile.default_content_setting.popups", 0);

        String downloadFilePath = System.getProperty("user.dir");
        chromePrefs.put("download.default_directory", downloadFilePath);
        chromePrefs.put("download.prompt_for_download", false);
        chromePrefs.put("download.extensions_to_open", "xml");
        chromePrefs.put("safebrowsing.enabled", true);

        options.setExperimentalOption("prefs", chromePrefs);
    }

    @BeforeMethod
    public void setUp() {
        setUpOptions();
        driver = new ChromeDriver(options);
        BaseSeleniumPage.setDriver(driver);
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
