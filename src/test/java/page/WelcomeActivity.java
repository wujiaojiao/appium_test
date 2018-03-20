package page;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

public class WelcomeActivity {
    @FindBy(xpath = "//*[contains(@text='跳过')]")
    public WebElement skip;
    public void skipClick(){
        skip.click();
    }

}
