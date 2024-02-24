package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class NnPages {

    public NnPages() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "(//button[@id='onetrust-accept-btn-handler'])")
    public WebElement acceptCookies;

    @FindBy(xpath = "((//*[@class='c-button'])[2])")
    public WebElement button_MijnNn;


    @FindBy(xpath = "(//span[@lang='en'])")
    public WebElement version_EN;
    @FindBy(xpath = "(//span[@lang='nl'])")
    public WebElement version_NL;

    @FindBy(xpath = "(//input[@name='username'])")
    public WebElement input_Username;
    @FindBy(xpath = "(//input[@name='password'])")
    public WebElement input_Password;

    @FindBy(xpath = "(//button[@data-cy='login-submit'])")
    public WebElement button_Login_Submit;


    @FindBy(xpath = "(//div[@class='u-margin-bottom c-notification c-notification--warning'])")
    public WebElement warningAlert;
}
