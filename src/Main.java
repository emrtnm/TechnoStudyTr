import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Duration;
import java.util.List;
import java.util.Set;
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
    void US4FooterCoursesMenu() throws InterruptedException, IOException {
        TakesScreenshot ts = (TakesScreenshot) driver;
        WebElement courses = driver.findElement(By.xpath("//a[@data-tooltip-menu-id='516093139']"));
        Assert.assertTrue(courses.isDisplayed());

        new Actions(driver).moveToElement(courses).build().perform();

        List<WebElement> listofCourses=driver.findElements(By.xpath("//div[@class='t966__menu-item-title t-name']"));

        for(WebElement e : listofCourses){
            wait.until(ExpectedConditions.visibilityOf(e));
            Assert.assertTrue(e.isDisplayed());
        }

        for (WebElement e : listofCourses){
            Assert.assertTrue(e.isEnabled());
        }

        for (WebElement e : listofCourses) {
            Thread.sleep(2000);
            e.click();
            Thread.sleep(2000);

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='tn-atom'])[1]")));
            WebElement detayliBilgi = driver.findElement(By.xpath("(//div[@class='tn-atom'])[1]"));

            //(BUG2) : Not Veri Bilimi Bootcamp indeki detaylibilgi butonu tiklanmiyor.
            Assert.assertTrue(detayliBilgi.isEnabled());
            detayliBilgi.click();

            SaveScreenshot(ts.getScreenshotAs(OutputType.FILE), "US4_Bug01");

            //BUG(3)
            //Yazilim Test muhendisligi icin detayli bilgiye tiklayinca farkli bir alana yonlendiriyor digerlerinden farkli bir alana
        }

        WebElement courses2 = driver.findElement(By.cssSelector("[class='t-menu__link-item t966__tm-link']"));
        List<WebElement> listofCourses2=driver.findElements(By.cssSelector("[class='t966__menu-item-title t-name']"));

        for (WebElement course : listofCourses2) {
            new Actions(driver).moveToElement(courses2).build().perform();
            wait.until(ExpectedConditions.visibilityOf(course));
            course.click();

            WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='tn-atom'])[1]")));
            String title = driver.getTitle();
            System.out.println("title = " + title);

            Assert.assertTrue(element.getText().equalsIgnoreCase(title));

            SaveScreenshot(ts.getScreenshotAs(OutputType.FILE), "US4_Bug02");

            //Not : yazilim test muhendisinin kisa adi SDET olarak yazilmis digerlerinde kisa adi yok. O yuzden hata aliyorum
            //element.getText()=Yazılım Test Mühendisi
            //title=SDET Yazılım Test Mühendisi

            //BUG4  () : Master's Degree tiklayinca beni turkce sayfadan ingilizce sayfaya yonlendiriyor .
            WebElement courses3 =driver.findElement(By.cssSelector("[class='t-menu__link-item t966__tm-link']"));
            new Actions(driver).moveToElement(courses3).build().perform();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='t966__menu-item-title t-name'])[5]")));
            WebElement mastersProgram=driver.findElement(By.xpath("(//div[@class='t966__menu-item-title t-name'])[5]"));
            mastersProgram.click();

            Assert.assertTrue(driver.getTitle().equalsIgnoreCase("Master's Program"));

            SaveScreenshot(ts.getScreenshotAs(OutputType.FILE), "US4_Bug03");

            driver.navigate().back();

            //BUG5 : Kurslar bolumune mouseover yaptigim zaman JobCenter & Arbeissamt kursuna tikladigimda sayfanin basliginda
            //kursun adi gozukmuyor ve sol ust kosede yer alan kurslar bolumu gozukmuyor. sayfanin ust kismi bos.
            WebElement courses4 =driver.findElement(By.cssSelector("[class='t-menu__link-item t966__tm-link']"));
            new Actions(driver).moveToElement(courses4).build().perform();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='t966__menu-item-title t-name'])[4]")));
            WebElement jobCenterArbeissamt=driver.findElement(By.xpath("(//div[@class='t966__menu-item-title t-name'])[4]"));
            jobCenterArbeissamt.click();
            Assert.assertTrue(courses4.isDisplayed());

            SaveScreenshot(ts.getScreenshotAs(OutputType.FILE), "US4_Bug04");
            driver.navigate().back();
        }
    }

    // @assigned=Selen Dilek
    @Test
    void US5AccessSocialsMedia()
    {
        // *Unused
        String homePage = driver.getWindowHandle();

        JavascriptExecutor js = (JavascriptExecutor) driver;
        List<WebElement> linkler = driver.findElements(By.xpath("//a[@rel='nofollow noopener']"));
        for (WebElement link : linkler) {
            Assert.assertTrue(link.isDisplayed()); //linkler goruluyor mu
            Assert.assertTrue(link.isEnabled()); //linkler tiklanabiliyor mu

            //linklere tiklayinca sayfala aciliyor mu
            js.executeScript("arguments[0].scrollIntoView(true);", link);
            js.executeScript("arguments[0].click();", link);

            Set<String> sosyalMedyaSayfalari = driver.getWindowHandles();

            for (String each : sosyalMedyaSayfalari) {
                Assert.assertTrue(driver.getCurrentUrl().toLowerCase().contains("techno"));
            }
        }
    }

    // @assigned=Emrullah Tanima
    @AfterMethod
    void US6CheckLogoRedirect() throws IOException {
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div/div/a[@href='/home']")));
        WebElement logo = driver.findElement(By.xpath("//div/div/a[@href='/home']"));
        logo.click();

        if (driver.getCurrentUrl().equals(baseUrl)) {
            TakesScreenshot ss = (TakesScreenshot) driver;
            SaveScreenshot(ss.getScreenshotAs(OutputType.FILE), "US6_Bug01");

            //Assert.assertEquals(driver.getCurrentUrl(), baseUrl);
        }
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

    /**
     * TakeScreenshot
     *
     * @param source
     * @param prefix
     * @throws IOException
     */
    public static void SaveScreenshot(File source, String prefix) throws IOException
    {
        String fileName = prefix + "_" + System.currentTimeMillis() + ".png";

        Path currentRelativePath = Paths.get("");
        String target = currentRelativePath.toAbsolutePath().toString();

        FileUtils.copyFile(source, new File(target + "/src/screenshots/" + fileName));
    }

    @AfterClass
    public void close() {
        driver.quit();
    }
}
