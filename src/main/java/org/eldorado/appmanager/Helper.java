package org.eldorado.appmanager;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;

import java.time.Duration;
import java.util.Arrays;

public class Helper {
    public AndroidDriver driver;

    //swipe standard {direction}
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
    }

    //"swipe webelement {direction}"
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

    //"Swipe by coordinates x_start={X_start},y_start={Y_start},x_end={X_end},x_end={Y_end}"
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

    //"Press keys {keys}"
    public void pressKeyboardButton(AndroidKey keys) {
        driver.pressKey(new KeyEvent(AndroidKey.ENTER));
    }

    //"Press simbol {keys}"
    public void sendingSetKeys(String keys) {
        new Actions(driver).sendKeys(keys).build().perform();
    }

    //"Press text {keys}"
    public void pressKeyboardButtons(String text) {
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            sendingSetKeys(String.valueOf(c));
        }
    }

}
