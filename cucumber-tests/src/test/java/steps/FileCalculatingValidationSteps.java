package steps;


import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static org.junit.Assert.*;

public class FileCalculatingValidationSteps {
    @When("attached file")
    public void attachedFile() {
        $(By.name("file")).sendKeys("D:\\Cross-Cutting-Project\\cucumber-tests\\testfiles\\helloworld.txt");
    }

    @And("^type to input with name \"([^\"]*)\" text: \"([^\"]*)\"$")
    public void typeToInputWithNameText(String input, String text) {
        $(By.name(input)).sendKeys(text);
    }

    @And("select file extension")
    public void selectFileExtension() {
        $(By.cssSelector("select")).selectOption("Txt");
    }

    @And("click calculate button")
    public void clickCalculateButton() {
        $(By.name("calculate")).click();
    }

    @Then("appears message {string}")
    public void appearsMessage(String expectedMessage) {
        String message = $(By.cssSelector("div[style='color: red;']")).getText();
        assertEquals(expectedMessage, message);
    }

    @Then("^button with name \"([^\"]*)\" should be disabled$")
    public void buttonWithNameShouldBeDisabled(String name) {
        boolean isEnabled = $(By.name(name)).isEnabled();
        assertFalse("Button with name " + name + " should be disabled", isEnabled);
    }
}
