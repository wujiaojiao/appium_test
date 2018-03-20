import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import page.MainActivity;

import java.net.MalformedURLException;
import java.net.URL;


public class TestPageObject {
    private AndroidDriver driver;
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
//        WelcomeActivity wel = new WelcomeActivity();
//        PageFactory.initElements(driver, wel);
        MainActivity main = new MainActivity();
        PageFactory.initElements(driver, main);
        Thread.sleep(5000);
        //wel.skipClick();
        Thread.sleep(2000);
        main.zixuan.click();
        Thread.sleep(5000);
        main.meigu.click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
