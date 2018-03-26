import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.categories.Category;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.PageFactory;
import page.MainActivity;
import tags.SmockTest;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;

@RunWith(Parameterized.class)
public class TestPageObjectDataDriver {

    private AndroidDriver driver;

    @Parameterized.Parameters()
    public static List<String[]> data(){

        //todo:from file xml excel
        String[][] array = new String[][]{{"a","4.4"},{"b","5.0"},{"c","6.0"}};
        return Arrays.asList(array);
    }

    @Parameterized.Parameter
    private String deviceName;
    @Parameterized.Parameter(1)
    private String versionName;

    @Before
    public void setUp() throws MalformedURLException {
        System.out.println("deviceName===="+deviceName);
        System.out.println("versionName===="+versionName);
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability("platformName", "android");
        desiredCapabilities.setCapability("deviceName", deviceName);
        desiredCapabilities.setCapability("platformVersion", versionName);
        desiredCapabilities.setCapability("appPackage", "com.xueqiu.android");
        desiredCapabilities.setCapability("appActivity", ".view.WelcomeActivityAlias");
        URL remoteUrl = new URL("http://localhost:4723/wd/hub");
        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
    }

    @Category(SmockTest.class)
    @Test
    public void demo() throws InterruptedException {
        // WelcomeActivity wel = new WelcomeActivity();
        // PageFactory.initElements(driver, wel);
        MainActivity main = new MainActivity();
        PageFactory.initElements(driver, main);
        Thread.sleep(5000);
        //wel.skipClick();
        Thread.sleep(2000);
        //main.zixuan.click();
        Thread.sleep(5000);
       // main.meigu.click();
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
