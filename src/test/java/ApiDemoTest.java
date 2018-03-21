import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.WaitOptions;
import io.appium.java_client.touch.offset.PointOption;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.net.FileNameMap;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class ApiDemoTest {

    private AndroidDriver<AndroidElement> driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "android");
        desiredCapabilities.setCapability("deviceName", "XX");
        desiredCapabilities.setCapability("appPackage", "io.appium.android.apis");
        desiredCapabilities.setCapability("appActivity", ".ApiDemos");
        desiredCapabilities.setCapability("automationName", "uiautomator2");
        desiredCapabilities.setCapability("dontStopAppOnReset",true);
        desiredCapabilities.setCapability("noReset",true);
        desiredCapabilities.setCapability("fullReset",false);
        URL remoteUrl = new URL("http://localhost:4723/wd/hub");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }


    @Test
    public void toastDemo() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        Dimension screenSize=driver.manage().window().getSize();
        driver.findElementByAccessibilityId("Views").click();
        Thread.sleep(2000);
        (new TouchAction(driver))
                .press( (int)(screenSize.width*0.5), (int)(screenSize.height*0.9))
                .waitAction()
                .moveTo((int)(screenSize.width*0.5), (int)(screenSize.height*0.2))
                .release()
                .perform();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@content-desc='Popup Menu']"))).click();
        driver.findElementByAccessibilityId("Make a Popup!").click();
        Thread.sleep(2000);
        driver.findElementByXPath("//*[contains(@text,'Search')]").click();
        String toastXPath="//*[@class='android.widget.Toast']";
        System.out.println(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(toastXPath))));
        System.out.println(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(toastXPath))).getText());
        System.out.println(driver.findElementByXPath(toastXPath).getText());
        for(int i =0;i <10;i++){
            int size=driver.findElementsByXPath(toastXPath).size();
            System.out.println(size);
            Thread.sleep(500);
        }
    }

    @Test
    public void testScrollLeft() throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElementByXPath("//*[@text='Views']").click();
        Thread.sleep(1000);
        int width = driver.manage().window().getSize().width;
        int height = driver.manage().window().getSize().height;
        (new TouchAction(driver))
                .press(PointOption.point((int)(width*0.9),(int)(height*0.9)))
                .waitAction()
                .moveTo(PointOption.point((int)(width*0.2),(int)(height*0.2)))
                .release().perform();
        Thread.sleep(2000);
       // WebDriverWait wait=new WebDriverWait(driver, 10);
        //System.out.println(wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[contains(@text, '更新')]"))).getText());
    }

    @Test
    public void testRotate() throws InterruptedException {
        driver.rotate(ScreenOrientation.LANDSCAPE);
        driver.rotate(ScreenOrientation.PORTRAIT);
        driver.findElementByXPath("//*[@text='App']").click();
        driver.navigate().back();
        driver.openNotifications();
        Thread.sleep(2000);
    }

    @Test
    public void testScrollTo(){
      driver.findElementByAccessibilityId("Views").click();
      AndroidElement list = driver.findElement(By.id("android:id/list"));
      MobileElement radioGroup = list.findElement(MobileBy.
              AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(" +
                      "new UiSelector().text(\"Radio Group\"));"));
    }


    @Test
    public void testLogs(){
        driver.manage().logs().getAvailableLogTypes();
        for(Object obj :driver.manage().logs().get("logcat").getAll().toArray()){
               System.out.println("testlogs======="+obj.toString());
        }
    }

    @Test
    public void testPerformance() throws Exception {

        System.out.println(driver.getSupportedPerformanceDataTypes());
        System.out.println(driver.getPerformanceData("io.appium.android.apis","memoryinfo",10));
        System.out.println(driver.getPerformanceData("io.appium.android.apis","cpuinfo",10));
        System.out.println(driver.getPerformanceData("io.appium.android.apis","batteryinfo",10));
        System.out.println(driver.getPerformanceData("io.appium.android.apis","networkinfo",10));

    }

    @Test
    public void testWebView(){
        driver.findElementByAccessibilityId("View").click();
        AndroidElement list = driver.findElement(By.id("android:id/list"));
        MobileElement webview = list.findElement(MobileBy.
                AndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(" +
                        "new UiSelector().text(\"webView\"));"));
        webview.click();
        driver.getContextHandles();
        for (AndroidElement element :driver.findElementsByXPath("//*")) {
            System.out.println(element.getTagName());
            System.out.println(element.getText());
        }
        driver.context(driver.getContextHandles().toArray()[1].toString());

        for (AndroidElement element :driver.findElementsByXPath("//*")) {
            System.out.println(element.getTagName());
            System.out.println(element.getText());
        }
    }

    @Test
    public void testNotStop() throws InterruptedException {
        //增加3个配置 dontStopAppOnReset noReset fullReset
        for (AndroidElement element:driver.findElementsByXPath("//*")) {
            System.out.println(element.getText());
        }
        Thread.sleep(2000);
        //driver.findElementByAccessibilityId("Buttons");
    }

    @Test
    public void saveScreen() throws IOException {
        File desc = new File(System.currentTimeMillis()+".png");
        File src = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(src,desc);
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
