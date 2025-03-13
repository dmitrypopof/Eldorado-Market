package org.eldorado.screens;


import io.appium.java_client.AppiumDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.eldorado.action.SwipeHelper;
import org.openqa.selenium.support.PageFactory;

public class Screen {
    AppiumDriver driver;

    SwipeHelper swipeHelper;

    public Screen(AppiumDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.driver = driver;
        swipeHelper = new SwipeHelper(driver);
    }
}
