package reactuitest;

import org.testng.Assert;
import org.testng.annotations.AfterGroups;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

public class CalculationPageTest extends BaseSeleniumTest {
    @AfterMethod(groups = "download-output.txt")
    void deleteFile() {
        FileUtil.deleteFile("output.txt");
    }
    @Test(groups = "download-output.txt")
    public void verifyDownloadFile() throws InterruptedException {
        CalculationPage page = new CalculationPage();
        String fileName = "output.txt";
        String defaultTxtFile = System.getProperty("user.dir") + "\\src\\test\\resources\\default.txt";

        page.setFile(defaultTxtFile);
        page.setOutputFileName(fileName);
        page.selectFileExtension("txt");
        page.calculate();
        page.download();

        Thread.sleep(5000);

        boolean isExist = FileUtil.isExist(fileName);

        Assert.assertTrue(isExist);
    }
}
