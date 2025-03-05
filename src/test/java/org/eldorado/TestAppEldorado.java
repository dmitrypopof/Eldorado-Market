package org.eldorado;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.qameta.allure.Step;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.Arrays;

public class TestAppEldorado extends TestBase {
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

    @Test
    @DisplayName("Task 1. Android")
    public void openSetting() {
        SoftAssertions softAssertions = new SoftAssertions();
        WebElement catalogTab = driver.findElement(TAB_CATALOG);
        boolean isNotSelectCatalog = catalogTab.isSelected();
        softAssertions.assertThat(isNotSelectCatalog).as("Catalog selected").isEqualTo(false);
        catalogTab.click();
        boolean isSelectCatalog = catalogTab.isSelected();
        softAssertions.assertThat(isSelectCatalog).as("Catalog selected").isEqualTo(true);
        swipe(Direction.UP);
        swipe(720, 1560, 720, 3100);
        driver.findElement(SEARCH_FIELD).click();
        pressKeyboardButtons("tv");
        pressKeyboardButton(AndroidKey.ENTER);
        String foundTitleText = driver.findElement(RESULT_COUNT_SEARCH).getText();
        softAssertions.assertThat(foundTitleText).contains("�������");
        driver.findElement(FILTR).click();
        driver.findElement(MANUFACTURER).click();
        driver.findElement(XAOMI).click();
        driver.findElement(APPLY_BUTTON).click();
        driver.findElement(SHOW_BUTTON).click();
        String foundSaleTitleText = driver.findElement(RESULT_COUNT_SEARCH).getText();
        softAssertions.assertThat(foundTitleText).isNotEqualTo(foundSaleTitleText);
        driver.lockDevice(Duration.ofSeconds(3));
        String txt_TV = driver.findElement(SEARCH_FIELD).getText();
        softAssertions.assertThat(txt_TV).isEqualTo("tv");

        softAssertions.assertAll();
    }

    private enum Direction {
        RIGHT, LEFT, UP, DOWN
    }

    @Step("swipe standard {direction}")
    public void swipe(Direction direction) {
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

    }

    @Step("swipe webelement {direction}")
    public void swipe(Direction direction, WebElement element) {
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

    }

    @Step("Swipe by coordinates x_start={X_start},y_start={Y_start},x_end={X_end},x_end={Y_end}")
    public void swipe(int X_start, int Y_start, int X_end, int Y_end) {
        final PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Point start = new Point(X_start, Y_start);
        Point end = new Point(X_end, Y_end);
        Sequence swipe = new Sequence(finger, 1);

        swipe.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), start.getX(), start.getY()));

        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), end.getX(), end.getY()));

        swipe.addAction(finger.createPointerUp(PointerInput.MouseButton.LEFT.asArg()));
        driver.perform(Arrays.asList(swipe));
    }

    @Step("Press keys {keys}")
    public void pressKeyboardButton(AndroidKey keys) {
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
    }

    @Step("Press simbol {keys}")
    public void sendingSetKeys(String keys) {
        new Actions(driver).sendKeys(keys).build().perform();
    }

    @Step("Press text {keys}")
    public void pressKeyboardButtons(String text) {
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            sendingSetKeys(String.valueOf(c));
        }
    }


}
