import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.time.Duration;
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
    void US1CoursesMenu()
    {

    }

    // @assigned=Samet Çamoğlu
    @Test
    void US2CampusLogin()
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
        new Actions(driver).scrollByAmount(0,6040).build().perform(); WebElement nameSurname=driver.findElement(By.name("name"));
        nameSurname.sendKeys("Test Ayse Fatma");
        WebElement email=driver.findElement(By.name("email"));
        email.sendKeys("testaysefatma@gmail.com");

        WebElement flag=driver.findElement(By.className("t-input-phonemask__select"));
        flag.click();
        WebElement andorra=driver.findElement(By.id("t-phonemask_ad"));
        andorra.click();

        WebElement number=driver.findElement(By.name("tildaspec-phone-part[]"));
        number.sendKeys("987654");

        WebElement country=driver.findElement(By.name("country"));
        Select select=new Select(country);
        select.selectByVisibleText("Andorra");

        WebElement course=driver.findElement(By.name("course"));
        Select select1=new Select(course);
        select1.selectByIndex(1);

        WebElement survey=driver.findElement(By.name("survey"));
        Select select2=new Select(survey);
        select2.selectByIndex(4);

        WebElement promoCode=driver.findElement(By.name("promo code"));
        promoCode.sendKeys("Anıl10");
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        WebElement conditions=driver.findElement(By.xpath("//*[text()='Şartları']"));                    //
        conditions.click();                                                                                           //
        Assert.assertFalse(driver.getCurrentUrl().contains("terms"),"kullanım şartları sayfasındasınız");     //
////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
        //taralı alanda "bug" vardır. Bug ticket açılmıstır.
        WebElement submitBtn=driver.findElement(By.className("t-submit"));
        submitBtn.click();

    }

    @AfterClass
    public void close() {
        driver.quit();
    }
}
