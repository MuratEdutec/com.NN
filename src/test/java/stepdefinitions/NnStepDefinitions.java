package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pages.NnPages;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class NnStepDefinitions {
NnPages pages;

    @Given("go to {string}")
    public void goTo(String url) {
        Driver.getDriver().get(ConfigReader.getProperty(url));
        ReusableMethods.justwait(3);
    }

    @Then("accept cookies")
    public void acceptCookies() {
        pages=new NnPages();
        pages.acceptCookies.click();
    }

    @Then("click mijn.nn button")
    public void clickMijnNnButton() {
        pages=new NnPages();
        pages.button_MijnNn.click();
    }

    @Then("click English version link")
    public void clickEnglishVersionLink() {
        pages=new NnPages();
        pages.version_EN.click();

    }
}


