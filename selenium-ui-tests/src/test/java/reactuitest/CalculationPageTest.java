package reactuitest;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CalculationPageTest extends BaseSeleniumTest {
    @Test
    public void verifyDownloadFile() throws InterruptedException {
        CalculationPage page = new CalculationPage();
        String fileName = "otput.txt";
        page.setFile("D:/Cross-Cutting-Project/selenium-ui-tests/src/test/resources/default.txt");
        page.setOutputFileName(fileName);
        page.selectFileExtension("txt");
        page.calculate();
        page.download();

        Thread.sleep(5000);

        boolean isExist = FileUtil.isExist(fileName);
        Assert.assertTrue(isExist);
    }
}
