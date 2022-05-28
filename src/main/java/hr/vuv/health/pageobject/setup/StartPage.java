package hr.vuv.health.pageobject.setup;

import hr.vuv.health.base.BaseClass;
import org.openqa.selenium.support.PageFactory;

public class StartPage extends BaseClass {

    public StartPage(){
        PageFactory.initElements(getDriver(), this);
    }

    public void startApplication() {
        loadConfig();
        startAppliaction("chrome");
    }
}
