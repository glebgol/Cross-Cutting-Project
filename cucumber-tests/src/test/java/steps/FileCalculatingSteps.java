package steps;


import com.codeborne.selenide.Selenide;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;
import static org.junit.Assert.*;

public class FileCalculatingSteps {
    @When("attached file {string}")
    public void attachedFile(String file) {
        $(By.name("file")).sendKeys(file);
    }

    @And("^type to input with name \"([^\"]*)\" text: \"([^\"]*)\"$")
    public void typeToInputWithNameText(String input, String text) {
        $(By.name(input)).sendKeys(text);
    }

    @And("select {string} file extension")
    public void selectFileExtension(String fileExtension) {
        $(By.cssSelector("select")).selectOption(fileExtension);
    }

    @And("click {string} button")
    public void clickButton(String name) {
        $(By.name(name)).click();
        Selenide.sleep(1000);
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
