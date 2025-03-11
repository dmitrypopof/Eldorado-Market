package org.eldorado.screens;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;

public class MainScreen extends Screen{
    public MainScreen(AndroidDriver driver) {
        super(driver);
    }

    public static final By MY_REGION = AppiumBy.id("ru.mvm.eldo:id/myRegion");
    public static final By MISS = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.widget.Button\").instance(1)");
    public static final By NEXT = AppiumBy.id("ru.mvm.eldo:id/acceptButton");
    public static final By TAB_CATALOG = AppiumBy.id("ru.mvm.eldo:id/catalogFragment");

    public static final By SEARCH_FIELD = AppiumBy.className("android.widget.EditText");
    public static final By RESULT_COUNT_SEARCH = AppiumBy.id("ru.mvm.eldo:id/productAmount");
    public static final By FILTR = AppiumBy.id("ru.mvm.eldo:id/filterContainer");
    public static final By MANUFACTURER = AppiumBy.androidUIAutomator("new UiSelector().className(\"android.view.ViewGroup\").instance(8)");
    public static final By XAOMI = AppiumBy.androidUIAutomator("new UiSelector().text(\"Xiaomi\")");
    public static final By APPLY_BUTTON = AppiumBy.id("ru.mvm.eldo:id/applyButton");
    public static final By SHOW_BUTTON = AppiumBy.id("ru.mvm.eldo:id/showButton");



    @Step("check selected Catalog tabbar")
    public boolean checkSelectedCatalog(){
        return driver.findElement(TAB_CATALOG).isSelected();
    }

    @Step("click Catalog tabbar")
    public MainScreen clickTabbarCatalog(){
        driver.findElement(TAB_CATALOG).click();
        return new MainScreen(driver);
    }

    @Step("click Catalog tabbar")
    public boolean checkClickTabbarCatalog(){
        return driver.findElement(TAB_CATALOG).isSelected();
    }

    @Step("Click to SEARCH_FIELD")
    public MainScreen clickToSearchField(){
        clickToElement(SEARCH_FIELD);
        return  new MainScreen(driver);
    }

    @Step("Get Txt to SEARCH_FIELD")
    public String getTxtSearchField(){
        return  driver.findElement(SEARCH_FIELD).getText();
    }



    @Step("getText RESULT_COUNT_SEARCH")
    public String getTextCountSearch(){
        return driver.findElement(RESULT_COUNT_SEARCH).getText();
    }

    @Step("Click to FILTR")
    public MainScreen clickToFilter(){
        clickToElement(FILTR);
        return  new MainScreen(driver);
    }

    @Step("Click to MANUFACTURER")
    public MainScreen clickToManufacturer(){
        clickToElement(MANUFACTURER);
        return  new MainScreen(driver);
    }

    @Step("Click to XAOMI")
    public MainScreen clickToXaomi(){
        clickToElement(XAOMI);
        return  new MainScreen(driver);
    }

    @Step("Click to APPLY_BUTTON")
    public MainScreen clickToApply(){
        clickToElement(APPLY_BUTTON);
        return  new MainScreen(driver);
    }

    @Step("Click to SHOW_BUTTON")
    public MainScreen clickToShow(){
        clickToElement(SHOW_BUTTON);
        return  new MainScreen(driver);
    }


    @Step("Block screen")
    public MainScreen blockScreen(int durationSec){
        driver.lockDevice(Duration.ofSeconds(durationSec));
        return new MainScreen(driver);
    }


//----------------------------------main click:
    @Description("click to Element {locator}")
    public void clickToElement(By locator){
        driver.findElement(locator).click();
    }

    //-------------main sendKeys:

    @Step("Press keys {keys}")
    public MainScreen pressKeyboardButton(AndroidKey keys) {
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
        return new MainScreen(driver);
    }

    @Step("Press simbol {keys}")
    public MainScreen sendingSetKeys(String keys) {
        new Actions(driver).sendKeys(keys).build().perform();
        return new MainScreen(driver);
    }

    @Step("Press text {keys}")
    public MainScreen pressKeyboardButtons(String text) {
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            sendingSetKeys(String.valueOf(c));
        }
        return new MainScreen(driver);
    }
}
