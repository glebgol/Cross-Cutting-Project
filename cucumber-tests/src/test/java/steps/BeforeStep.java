package steps;


import io.cucumber.java.en.Given;

import static com.codeborne.selenide.Selenide.open;

public class BeforeStep {
    @Given("^open site \"([^\"]*)\"$")
    public void openSite(String url)  {
        open(url);
    }
}
