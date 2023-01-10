package reactuitest;

import org.openqa.selenium.WebDriver;

public abstract class BaseSeleniumPage {
    public static WebDriver driver;

    public static void setDriver(WebDriver webDriver) {
        driver = webDriver;
    }
}
