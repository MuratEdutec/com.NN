package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import pages.NnPages;
import utilities.ConfigReader;
import utilities.Driver;
import utilities.ReusableMethods;

public class NnStepDefinitions {
    NnPages pages;

    @Given("go to {string}")
    public void goTo(String url) {
        Driver.getDriver().get(ConfigReader.getProperty(url));
        ReusableMethods.justwait(2);
    }

    @Then("accept cookies")
    public void acceptCookies() {
        pages = new NnPages();
        pages.acceptCookies.click();
    }

    @Then("click mijn.nn button")
    public void clickMijnNnButton() {
        pages = new NnPages();
        pages.button_MijnNn.click();
    }

    //English version of Login
    @Then("click English version link")
    public void clickEnglishVersionLink() {
        pages = new NnPages();
        pages.version_EN.click();

    }

    @Then("enter an invalid username and password")
    public void enterAnInvalidUsernameAndPassword() {
        pages = new NnPages();
        ReusableMethods.scrolldown_onestep();
        ReusableMethods.justwait(2);
        pages.input_Username.sendKeys(ConfigReader.getProperty("invalidUsername"));
        ReusableMethods.justwait(2);
        pages.input_Password.sendKeys(ConfigReader.getProperty("invalidPassword"));
    }

    //Login
    @And("click Login to mijn.nn button")
    public void clickLoginToMijnNnButton() {
        pages = new NnPages();
        ReusableMethods.justwait(2);
        pages.button_Login_Submit.click();
    }

    //Verification
    @Then("verify that the invalid username and password banner is shown")
    public void verifyThatTheInvalidUsernameAndPasswordBannerIsShown() {
        pages = new NnPages();
        Assert.assertTrue(pages.warningAlert.isDisplayed());

    }
    @Then("click the Dutch version link")
    public void clickTheDutchVersionLink() {
        pages = new NnPages();
        pages.version_NL.click();


    }
}


