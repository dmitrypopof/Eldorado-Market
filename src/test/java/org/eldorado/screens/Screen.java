package org.eldorado.screens;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.eldorado.action.SwipeHelper;
import org.openqa.selenium.support.PageFactory;

public class Screen {
    AndroidDriver driver;

    SwipeHelper swipeHelper;

    public Screen(AndroidDriver driver) {
        PageFactory.initElements(new AppiumFieldDecorator(driver), this);
        this.driver = driver;
        swipeHelper = new SwipeHelper(driver);
    }
}
