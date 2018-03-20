
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static java.time.Duration.ofSeconds;

public class XueqiuTest {

    private AndroidDriver<AndroidElement> driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "android");
        desiredCapabilities.setCapability("deviceName", "XX");
        desiredCapabilities.setCapability("appPackage", "com.xueqiu.android");
        desiredCapabilities.setCapability("appActivity", ".view.WelcomeActivityAlias");
        URL remoteUrl = new URL("http://localhost:4723/wd/hub");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }


    @Test
    public void demo() throws InterruptedException {
       Thread.sleep(5000);
       locate("//*[@text='自选']").click();
        Thread.sleep(2000);
        locate("//*[@text='美股']").click();
        Thread.sleep(5000);
        locate("action_create_cube").click();
        locate("search_input_text").sendKeys("wujiaojiao");

    }

    @Test
    public void testScroll() throws InterruptedException {
        Thread.sleep(2000);
        (new TouchAction(driver)).press(100,200).moveTo(300,400).release().perform();
    }

    /*java.lang.ClassCastException:
     io.appium.java_client.android.AndroidDriver
     cannot be cast to org.openqa.selenium.interactions.HasTouchScreen*/
    @Test
    public void testScrolls() throws InterruptedException {
        Thread.sleep(2000);
        TouchActions action = new TouchActions(driver);
        action.scroll(locate("//*[@text='房产']"), 300, 400);
        action.perform();
    }

    @Test
    public void testWait() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        driver.findElementByXPath("//*[@text='自选']").click();

    }
    @Test
    public void testWait2(){
        WebDriverWait wait=new WebDriverWait(driver, 50);
        for(int i=0;i<10;i++) {
            System.out.println(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@text='自选']"))).getLocation());
        }
        driver.findElementByXPath("//*[@text='自选']").click();
    }

    @Test
    public void testScrollLeft(){
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElementByXPath("//*[@text='自选']").getLocation();
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        Point pt = driver.findElementByXPath("//*[@text='直播']").getLocation();
        Point pt_m = driver.findElementByXPath("//*[@text='行情']").getLocation();
        (new TouchAction(driver))
                .press(PointOption.point(pt.x,pt.y))
                //.press(PointOption.point((int)(width*0.8),(int)(height*0.2)))
                .waitAction()
                .moveTo(PointOption.point(pt_m.x,pt_m.y))
                //.moveTo(PointOption.point((int)(width*0.2),(int)(height*0.8)))
                //.waitAction(WaitOptions.waitOptions(Duration.ofSeconds(2)))
                .release().perform();
        WebDriverWait wait=new WebDriverWait(driver, 10);
        System.out.println(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text, '更新')]"))).getText());
    }

    public WebElement locate(String loacter){
        try{
            if(loacter.matches("\\/\\/.*")){
                return driver.findElementByXPath(loacter);
            }else{
                return driver.findElementById(loacter);
            }
        }finally {
            System.out.println(driver.getPageSource());
            for(AndroidElement e: driver.findElementsByXPath("//*")){
                System.out.println("\n");
                System.out.println(e.getTagName());
                System.out.println(e.getText());
                System.out.println(e.getAttribute("resource-id"));
                System.out.println(e.getAttribute("content-desc"));
            }
        }
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}



