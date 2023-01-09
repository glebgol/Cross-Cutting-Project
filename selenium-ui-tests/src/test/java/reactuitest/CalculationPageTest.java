package reactuitest;

import org.testng.annotations.Test;
import reactuitest.pages.CalculationPage;

public class CalculationPageTest extends BaseSeleniumTest {
    @Test
    public void check() {
        CalculationPage page = new CalculationPage();

        page.setFile("D:\\Cross-Cutting-Project\\src\\test\\resources\\input.xml");
        page.clickZipCheckBox();
    }
}
