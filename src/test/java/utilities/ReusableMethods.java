package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.function.Function;

import static utilities.Driver.driver;

public class ReusableMethods {




    public static String getScreenshot(String name) throws IOException {
        // naming the screenshot with the current date to avoid duplication
        String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        // TakesScreenshot is an interface of selenium that takes the screenshot
        TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
        File source = ts.getScreenshotAs(OutputType.FILE);
        // full path to the screenshot location
        String target = System.getProperty("user.dir") + "/target/Screenshots/" + name + date + ".png";
        File finalDestination = new File(target);
        // save the screenshot to the path given
        FileUtils.copyFile(source, finalDestination);
        return target;
    }
    public static void switchToWindow(String targetTitle) {
        String origin = Driver.getDriver().getWindowHandle();
        for (String handle : Driver.getDriver().getWindowHandles()) {
            Driver.getDriver().switchTo().window(handle);
            if (Driver.getDriver().getTitle().equals(targetTitle)) {
                return;
            }
        }
        Driver.getDriver().switchTo().window(origin);
    }

    //========Hover Over=====//
    public static void hover(WebElement element) {
        Actions actions = new Actions(Driver.getDriver());
        actions.moveToElement(element).perform();
    }

    //==========Return a list of string given a list of Web Element====////
    public static List<String> getElementsText(List<WebElement> list) {
        List<String> elemTexts = new ArrayList<>();
        for (WebElement el : list) {
            if (!el.getText().isEmpty()) {
                elemTexts.add(el.getText());
            }
        }
        return elemTexts;
    }

    //========Returns the Text of the element given an element locator==//
    public static List<String> getElementsText(By locator) {
        List<WebElement> elems = Driver.getDriver().findElements(locator);
        List<String> elemTexts = new ArrayList<>();
        for (WebElement el : elems) {
            if (!el.getText().isEmpty()) {
                elemTexts.add(el.getText());
            }
        }
        return elemTexts;
    }

    //   HARD WAIT WITH THREAD.SLEEP
//   waitFor(5);  => waits for 5 second
    public static void justwait(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    //===============Explicit Wait==============//
    public static WebElement waitForVisibility(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }

    public static WebElement waitForVisibility(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }


    public static WebElement waitForClickablility(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }


    public static void clickWithTimeOut(WebElement element, int timeout) {
        for (int i = 0; i < timeout; i++) {
            try {
                element.click();
                return;
            } catch (WebDriverException e) {
                justwait(1);
            }
        }
    }

    public static void waitForPageToLoad(long timeout) {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        try {
            System.out.println("Waiting for page to load...");
            WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
            wait.until(expectation);
        } catch (Throwable error) {
            System.out.println(
                    "Timeout waiting for Page Load Request to complete after " + timeout + " seconds");
        }
    }

    //======Fluent Wait====//
    public static WebElement fluentWait(final WebElement webElement, int timeout) {
        //FluentWait<WebDriver> wait = new FluentWait<WebDriver>(Driver.getDriver()).withTimeout(timeinsec, TimeUnit.SECONDS).pollingEvery(timeinsec, TimeUnit.SECONDS);
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(Driver.getDriver())
                .withTimeout(Duration.ofSeconds(3))//Wait 3 second each time
                .pollingEvery(Duration.ofSeconds(1));//Check for the element every 1 second

        WebElement element = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return webElement;
            }
        });

        return element;

    }

    // Bir stringi sadece rakam olarak birakir ve integer'a cast eder.
    public static int onlyTakeNumberandParseInteger(String number) {
        return Integer.parseInt(number.replaceAll("\\D", ""));
    }


    // ScrollDown_End_Of_Page --> Murat
    public static void scrolldown() {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)", "");

    }

    public static void scrolldown_600() {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,400)");

    }


    public static void scrolldown_slowly() {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,400)");
        ReusableMethods.justwait(2);
        js.executeScript("window.scrollBy(0,400)");
        ReusableMethods.justwait(2);
        js.executeScript("window.scrollBy(0,400)");
       }


    public static void scrolldown_bypixel_2000() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,2000)");

    }


    public static void logIn(String userEmailAddress, String userPassword) {
        WebElement userEmailTextBox = driver.findElement(By.xpath("//input[@name='login']"));
        WebElement useremailTesxtBox = driver.findElement(By.xpath("//input[@name='password']"));
        WebElement signInbutton = driver.findElement(By.xpath("//button[text()='Sign In']"));
        userEmailTextBox.sendKeys(userEmailAddress);
        useremailTesxtBox.sendKeys(userPassword);
        ReusableMethods.waitToSee(1);
        signInbutton.click();
    }


    // Sayfa üzerinde istenilen elemente gider
    public static void focusToElement(WebElement element)
    {
        Actions actions = new Actions(Driver.getDriver());
        actions.scrollToElement(element);
    }

    public static void scrolldown_for_Load_More() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,5500)");

    }

    public static void waitToSee(int second) {
        try {
            Thread.sleep(second * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void signIn(String userEmailAddress, String userPassword){
        WebElement userEmailTextBox = driver.findElement(By.xpath("//input[@name='login']"));
        WebElement useremailTesxtBox = driver.findElement(By.xpath("//input[@name='password']"));
        WebElement signInbutton = driver.findElement(By.xpath("//button[text()='Sign In']"));
        userEmailTextBox.sendKeys(userEmailAddress);
        useremailTesxtBox.sendKeys(userPassword);
        ReusableMethods.waitToSee(1);
        signInbutton.click();
    }


    public static void navigateback() {
        driver.navigate().back();
    }



    public static Select select(WebElement ddm) {

        Select select;
        return select = new Select(ddm);
    }

    public static Random random() { //

        Random random;
        return random = new Random();
    }

    public static Actions getActions() {

        Actions actions;
        return actions = new Actions(Driver.getDriver());
    }

    // Uyarı veren alertlerde ekran goruntusu alma
    public static String getScreenshotWebElement(String name, WebElement element) throws IOException {

        String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        File source = element.getScreenshotAs(OutputType.FILE);
        // EKRAN GORUNTUSU NUN TAM YOLU
        String wElementSS = System.getProperty("user.dir") + "/target/WElementScreenshots/" + name + date + ".png";
        File finalDestination = new File(wElementSS);

        //EKRAN GORUNTUSU VERILEN YOLA KAYDEDER
        FileUtils.copyFile(source, finalDestination);
        return wElementSS;
    }

    // ScrollDown_End_Of_Page --> Murat

    public static void scrolldown_DownViewAll() {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,600)");
    }


    public static void scrolldown_SideBarMenu() {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,700)");
    }


    public static void scrolldown_NotificationsSettingMenu() {

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("window.scrollBy(0,300)");
    }
    public static WebElement waitForClickablility(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }


}
