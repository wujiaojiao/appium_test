import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;

public class BrowserTest {

    private AndroidDriver<AndroidElement> driver;

    @Before
    public void setUp() throws MalformedURLException {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "");
        desiredCapabilities.setCapability("deviceName", "XX");
        desiredCapabilities.setCapability("browserName","Browser");
        desiredCapabilities.setCapability("automationName", "uiautomator2");
        URL remoteUrl = new URL("http://localhost:4723/wd/hub");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Test
    public void testWeb() throws InterruptedException {
        Thread.sleep(10);
    }

    @After
    public void tearDown(){
        driver.quit();
    }
}
