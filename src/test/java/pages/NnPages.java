package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class NnPages {

    public NnPages() { PageFactory.initElements(Driver.getDriver(), this);}

@FindBy(xpath = "(//button[@id='onetrust-accept-btn-handler'])")
    public WebElement acceptCookies;

@FindBy(xpath = "((//a[@href='/mijn.nn.htm'])[2])")
    public WebElement button_MijnNn;


    @FindBy(xpath = "(//span[@lang='en'])")
    public WebElement version_EN;




}
