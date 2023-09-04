import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.*;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;

public class Main {
    public WebDriver driver;
    public WebDriverWait wait;
    public String baseUrl = "https://techno.study/tr/";

    public TakesScreenshot ts;

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
        ts = (TakesScreenshot) driver;
    }

    @BeforeMethod
    void openUri()
    {
        driver.navigate().to(baseUrl);
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

            SaveScreenshot(ts.getScreenshotAs(OutputType.FILE), "US4_Bug01");

            driver.navigate().back();
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
            SaveScreenshot(ts.getScreenshotAs(OutputType.FILE), "US6_Bug01");

            //Assert.assertEquals(driver.getCurrentUrl(), baseUrl);
        }
    }

    // @assigned=Ümit Boyraz
    @Test
    void US7CourseDetails() throws InterruptedException {
        Assert.assertTrue(driver.getCurrentUrl().equals(baseUrl));

        WebElement mainLogo = driver.findElement(By.xpath("//img[@class='t228__imglogo ']"));
        Assert.assertTrue(mainLogo.getAttribute("class").equals("t228__imglogo "),"Logo is not seen");

        WebElement scrollToSdet = driver.findElement(By.xpath("//div[@field='tn_text_1490289646296']"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView(true);", scrollToSdet);

        WebElement sdet =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='SDET']")));
        WebElement android =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(text(),'Android Uygulama geli')]")));
        WebElement veri =wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[text()='Veri Bilimi']")));

        Assert.assertTrue(sdet.isDisplayed(),"'SDET' section is not displayed");
        Assert.assertTrue(android.isDisplayed(),"'Android Uygulama Geliştiricisi' section is not displayed");
        Assert.assertTrue(veri.isDisplayed(),"'Veri Bilimi' section is not displayed");

        List<WebElement> detayliBilgiButtons = driver.findElements(By.linkText("Detaylı bilgi"));
        for (WebElement dBb : detayliBilgiButtons){
            js.executeScript("arguments[0].click();",dBb);
            WebElement logo = driver.findElement(By.xpath("//img[@class='t228__imglogo ']"));
            Assert.assertTrue(logo.getAttribute("class").equals("t228__imglogo "),"Logo is not seen");
            Thread.sleep(1000);

            Assert.assertEquals(driver.getCurrentUrl(),dBb.getAttribute("href"));
            driver.navigate().back();
        }

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
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String timeStamp = dateFormat.format(new Date());
        String fileName = prefix + "_" + timeStamp + ".png";

        Path currentRelativePath = Paths.get("");
        String target = currentRelativePath.toAbsolutePath().toString();

        FileUtils.copyFile(source, new File(target + "/src/screenshots/" + fileName));
    }

    @AfterClass
    public void close() {
        driver.quit();
    }
}
