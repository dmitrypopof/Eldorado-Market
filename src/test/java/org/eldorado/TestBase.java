package org.eldorado;



import io.appium.java_client.AppiumDriver;
import org.eldorado.setting.DriverFactory;
import org.eldorado.setting.Platform;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import java.net.MalformedURLException;

public class TestBase {

    public AppiumDriver driver;

    private DriverFactory driverFactory = new DriverFactory();

    @BeforeEach
    public void setDriver() throws MalformedURLException {
        driver = driverFactory.setUp(Platform.ANDROID);
        // for annotations AndroidFindBy:
        /*
        AppiumFieldDecorator appiumFieldDecorator = new AppiumFieldDecorator(driver);
        PageFactory.initElements(appiumFieldDecorator,this);*/
    }

    @AfterEach
    public void tearDown() {
        //driver.terminateApp("ru.mvm.eldo");
        driver.quit();
    }

}
