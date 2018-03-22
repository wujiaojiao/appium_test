import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class WebViewTest {

    private AndroidDriver<AndroidElement> driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "android");
        desiredCapabilities.setCapability("deviceName", "XX");
        desiredCapabilities.setCapability("appPackage", "io.appium.android.apis");
        desiredCapabilities.setCapability("appActivity", ".ApiDemos");
        desiredCapabilities.setCapability("automationName", "uiautomator2");
        desiredCapabilities.setCapability("showChromedriverLog",true);
        URL remoteUrl = new URL("http://localhost:4723/wd/hub");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    /*
    * https://testerhome.com/topics/12248
    * https://github.com/appium/appium/blob/master/CHANGELOG.md
    * Android O有bug,所以执行不成功 未获取到webview进程
    * 想要在Android O上执行成功，升级webview版本
    * 执行命令 adb shell cat /proc/net/unix | grep webview
    * https://github.com/appium/appium/blob/master/docs/en/writing-running-appium/web/chromedriver.md
    *
    * 执行命令
    * appium -g /tmp/appium.log --chromdriver-executable ~/temp/appium/2.2.0/chromdriver
    *
    * 手机开发者选项可以查看webview版本
    * */
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

    @After
    public void tearDown(){
        driver.quit();
    }
}
