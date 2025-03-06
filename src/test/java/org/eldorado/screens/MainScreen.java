package org.eldorado.screens;

import com.google.common.annotations.VisibleForTesting;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.Arrays;

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

    //------------------------------main swipe:
    public enum Direction {
        RIGHT, LEFT, UP, DOWN
    }

    @Step("swipe standard {direction}")
    public MainScreen swipe(Direction direction) {
        int edge = 25;
        Dimension dims = driver.manage().window().getSize();
        Point start = new Point(dims.width / 2, dims.height / 2);
        Point end;
        switch (direction) {
            case RIGHT -> end = new Point(dims.width - edge, dims.height / 2);
            case LEFT -> end = new Point(edge, dims.height / 2);
            case UP -> end = new Point(dims.width / 2, edge);
            case DOWN -> end = new Point(dims.width / 2, dims.height - edge);
            default -> throw new IllegalArgumentException("Unexpected value: " + direction);
        }

        swipe(start.x, start.y, end.x, end.y);

        return new MainScreen(driver);
    }

    @Step("swipe webelement {direction}")
    public MainScreen swipe(Direction direction, WebElement element) {
        int edge = 25;
        Dimension dims = driver.manage().window().getSize();
        Point start = new Point(element.getLocation().x, element.getLocation().y);
        Point end;
        switch (direction) {
            case RIGHT -> end = new Point(dims.width - edge, dims.height / 2);
            case LEFT -> end = new Point(edge, dims.height / 2);
            case UP -> end = new Point(dims.width / 2, edge);
            case DOWN -> end = new Point(dims.width / 2, dims.height - edge);
            default -> throw new IllegalArgumentException("Unexpected value: " + direction);
        }
        swipe(start.x, start.y, end.x, end.y);

        return new MainScreen(driver);
    }

    @Step("Swipe by coordinates x_start={X_start},y_start={Y_start},x_end={X_end},x_end={Y_end}")
    public MainScreen swipe(int X_start, int Y_start, int X_end, int Y_end) {
        final PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Point start = new Point(X_start, Y_start);
        Point end = new Point(X_end, Y_end);
        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), start.getX(), start.getY()));

        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), end.getX(), end.getY()));

        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(swipe));
        return new MainScreen(driver);
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
