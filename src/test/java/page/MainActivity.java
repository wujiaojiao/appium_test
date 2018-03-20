package page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainActivity {
    @FindBy(xpath = "//*[@text='自选']")
    public WebElement zixuan;
    @FindBy(xpath = "//*[@text='美股']")
    public WebElement meigu;
}
