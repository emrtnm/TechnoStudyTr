import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
    public WebDriver driver;
    public WebDriverWait wait;
    public String baseUrl = "https://techno.study/tr/";

    @BeforeClass
    @Parameters("webDriver")
    public void init(String webDriver) {
        Logger logger = Logger.getLogger("");
        logger.setLevel(Level.SEVERE);

        switch (webDriver)
        {
            case "firefox":
                driver = new FirefoxDriver();
                break;
            case "safari":
                driver = new SafariDriver();
                break;
            case "ie":
                driver = new InternetExplorerDriver();
                break;
            case "edge":
                driver = new EdgeDriver();
                break;
            default:
                driver = new ChromeDriver();
                break;
        }

        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
        wait = new WebDriverWait(driver, Duration.ofSeconds(20));
    }

    @BeforeMethod
    void openUri()
    {
        driver.get(baseUrl);
    }

    // @assigned=Samet Çamoğlu
    @Test
    void US1CoursesMenu() throws InterruptedException {
        {
            WebElement basvur = driver.findElement(By.xpath("//a[@class='tn-atom js-click-zero-stat']"));
            basvur.click();

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='t-input-block']/input[contains(@aria-label, 'name')]")));
            WebElement firstName = driver.findElement(By.xpath("//div[@class='t-input-block']/input[contains(@aria-label, 'name')]"));
            firstName.sendKeys("Test_ali Cebbar ");

            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='t-input-block']/input[contains(@aria-label, 'email')]")));
            WebElement email = driver.findElement(By.xpath("//div[@class='t-input-block']/input[contains(@aria-label, 'email')]"));
            email.sendKeys("test_alicebbar@gmail.com ");

            JavascriptExecutor js = (JavascriptExecutor) driver;
            js.executeScript("window.scrollBy(0,350)", "");
            WebElement optionsList = driver.findElement(By.xpath("//span[@class='t-input-phonemask__select-triangle']"));
            optionsList.click();
            WebElement optionsList1 = driver.findElement(By.xpath("//*[@id='t-phonemask_ad']"));
            optionsList1.click();
            WebElement phoneNumber = driver.findElement(By.xpath("//input[@class='t-input t-input-phonemask']"));
            phoneNumber.sendKeys("652-301");

            WebElement country = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='sb-1667664755026']")));
            new Select(country).selectByVisibleText("Andorra");

            WebElement kurs = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='sb-1663337581601']")));
            new Select(kurs).selectByVisibleText("SDET Türkçe");

            WebElement howLearn = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='sb-1670423010572']")));
            new Select(howLearn).selectByVisibleText("Youtube");

            WebElement promosyon = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@aria-label='oneline']")));
            promosyon.sendKeys("SDET");

            WebElement checkbox = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='t-checkbox__indicator']")));
            checkbox.click();

            WebElement submitButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@class='t-submit']")));
            submitButton.click();

            WebElement mesaj = driver.findElement(By.xpath("//span[text()='Lütfen posta kutunuzu kontrol ediniz.']"));

            Assert.assertFalse("Mesaj Gonderildi".isEmpty(), mesaj.getText());
        }

        // @assigned=Selen Dilek
        @Test
        void US4FooterCoursesMenu() throws InterruptedException, IOException {
            WebElement courses = driver.findElement(By.xpath("//a[@data-tooltip-menu-id='516093139']"));
            Assert.assertTrue(courses.isDisplayed());

            new Actions(driver).moveToElement(courses).build().perform();

            List<WebElement> listofCourses = driver.findElements(By.xpath("//a[@class='t966__menu-link']"));

            for(WebElement e : listofCourses){
                wait.until(ExpectedConditions.visibilityOf(e));
                Assert.assertTrue(e.isDisplayed());
            }

            for (WebElement e : listofCourses){
                Assert.assertTrue(e.isEnabled());
            }

            for (WebElement e : listofCourses) {
                new Actions(driver).moveToElement(courses).build().perform();
                Thread.sleep(1000);

                wait.until(ExpectedConditions.elementToBeClickable(e));
                e.click();

                wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='tn-atom'])[1]")));
                WebElement detayliBilgi = driver.findElement(By.xpath("(//div[@class='tn-atom'])[1]"));

                //(BUG2) : Not Veri Bilimi Bootcamp indeki detaylibilgi butonu tiklanmiyor.
                Assert.assertTrue(detayliBilgi.isEnabled());
                detayliBilgi.click();

                //SaveScreenshot(ts.getScreenshotAs(OutputType.FILE), "US4_Bug01");

                driver.navigate().back();
            }


        }


    // @assigned=Samet Çamoğlu
    @Test
    void US2CampusLogin() throws InterruptedException
    {


        }




    // @assigned=Rustam Rozbayev
    @Test
    void US3ApplyToCourse()
    {

    }

    // @assigned=Selen Dilek
    @Test
    void US4FooterCoursesMenu()
    {

    }

    // @assigned=Selen Dilek
    @Test
    void US5AccessSocialsMedia()
    {

    }

    // @assigned=Emrullah Tanima
    @AfterMethod
    void US6CheckLogoRedirect()
    {

    }

    // @assigned=Ümit Boyraz
    @Test
    void US7CourseDetails()
    {

    }

    // @assigned=Umut Can Güzel
    @Test
    void US8AcceptTermsOfUse()
    {

    }

    @AfterClass
    public void close() {
        driver.quit();
    }
}
