package reactuitest;

import helpers.Urls;
import org.testng.Assert;
import org.testng.annotations.Test;

public class MainPageTest extends BaseSeleniumTest {
    @Test
    public void verifyCalculateUrl() {
        MainPage page = new MainPage();
        page.clickCalculateButton();

        String currentUrl = page.getCurrentUrl();

        Assert.assertEquals(currentUrl, Urls.CALCULATE_URL);
    }

    @Test
    public void verifyDecryptUrl() {
        MainPage page = new MainPage();
        page.clickDecryptButton();

        String currentUrl = page.getCurrentUrl();

        Assert.assertEquals(currentUrl, Urls.DECRYPT_URL);
    }

    @Test
    public void verifyEncryptUrl() {
        MainPage page = new MainPage();
        page.clickEncryptButton();

        String currentUrl = page.getCurrentUrl();

        Assert.assertEquals(currentUrl, Urls.ENCRYPT_URL);
    }

    @Test
    public void verifyZipUrl() {
        MainPage page = new MainPage();
        page.clickZipButton();

        String currentUrl = page.getCurrentUrl();

        Assert.assertEquals(currentUrl, Urls.ZIP_URL);
    }

    @Test
    public void verifyUnZipUrl() {
        MainPage page = new MainPage();
        page.clickUnZipButton();

        String currentUrl = page.getCurrentUrl();

        Assert.assertEquals(currentUrl, Urls.UNZIP_URL);
    }
}
