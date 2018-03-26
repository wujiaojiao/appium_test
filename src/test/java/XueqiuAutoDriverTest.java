import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.touch.offset.PointOption;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.touch.TouchActions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class XueqiuAutoDriverTest {

    private AndroidDriver<AndroidElement> driver;
    /*
     * 多个设备，设备名字从外部来取
     * 调用环境变量idea的Terminal执行mvn test -Dtest = "XueqiuAutoDriverTest"指定跑少量的用例
     * deviceName = "123" mvn test -Dtest = "XueqiuAutoDriverTest"
     * */
    @Before
    public void setUp() throws MalformedURLException {

        System.out.println(System.getenv("deviceName"));

        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "android");
        desiredCapabilities.setCapability("deviceName",System.getenv("deviceName"));
        desiredCapabilities.setCapability("appPackage", "com.xueqiu.android");
        desiredCapabilities.setCapability("appActivity", ".view.WelcomeActivityAlias");
        desiredCapabilities.setCapability("automationName","uiautomator2");
        URL remoteUrl = new URL("http://localhost:4723/wd/hub");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS);
    }


    @Test
    public void demo() throws InterruptedException {
        Thread.sleep(5000);
        locate("//*[@text='自选']").click();
        Thread.sleep(2000);
        locate("//*[@text='美股']").click();
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
        }
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}



