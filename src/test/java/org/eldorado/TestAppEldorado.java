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
        //TODO:Проверьте, что вкладка «Каталог» в нижнем таббаре не выбрана.
        boolean isNotSelectCatalog = catalogTab.isSelected();
        softAssertions.assertThat(isNotSelectCatalog).as("Catalog selected").isEqualTo(false);
        //TODO:Перейдите в таббаре на вкладку «Каталог
        catalogTab.click();
        //TODO:Проверьте, что вкладка «Каталог» выбрана
        boolean isSelectCatalog = catalogTab.isSelected();
        softAssertions.assertThat(isSelectCatalog).as("Catalog selected").isEqualTo(true);
        //TODO:Реализуйте одинарный скролл вверх и одинарный скролл вниз
        swipe(Direction.UP);
        swipe(720, 1560, 720, 3100);
        //TODO:В поле «Название товара» введите «Телевизор» и перейдите к результатам поиска
        driver.findElement(SEARCH_FIELD).click();
        pressKeyboardButtons("tv");
        pressKeyboardButton(AndroidKey.ENTER);
        //TODO:Сохраните в переменную foundTitleText текст «Найдено … товаров»
        String foundTitleText = driver.findElement(RESULT_COUNT_SEARCH).getText();
        softAssertions.assertThat(foundTitleText).contains("Найдено");
        //TODO:Затем перейдите в «Фильтры»
        driver.findElement(FILTR).click();
        //TODO:Активируйте переключатель «Товары со скидкой» и нажмите на красную кнопку внизу «Показать» = Заменил на Выбор производителя ? Xiaomi
        driver.findElement(MANUFACTURER).click();
        driver.findElement(XAOMI).click();
        driver.findElement(APPLY_BUTTON).click();
        driver.findElement(SHOW_BUTTON).click();
        //TODO:Сохраните в переменную foundSaleTitleText текст «Найдено … товаров»
        String foundSaleTitleText = driver.findElement(RESULT_COUNT_SEARCH).getText();
        //TODO:Проверьте, что переменная foundTitleText не равна foundSaleTitleText
        softAssertions.assertThat(foundTitleText).isNotEqualTo(foundSaleTitleText);
        //TODO:Заблокируйте экран эмулятора на 3 секунды
        driver.lockDevice(Duration.ofSeconds(3));
        //TODO:Проверьте, что после разблокировки экрана в поисковом поле остался текст «Телевизор»
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
        //виртуальный "палец", которым мы будем управлять:
        final PointerInput finger = new PointerInput(PointerInput.Kind.TOUCH, "finger");
        Point start = new Point(X_start, Y_start);
        Point end = new Point(X_end, Y_end);
        //последовательность действий для нашего виртуального пальца.  1 — это порядковый номер действия (в данном случае один):
        Sequence swipe = new Sequence(finger, 1);
        //Перемещение указателя в начальную точку start мгновенно (Duration.ofMillis(0))
        //viewport() указывает, что координаты относятся к области видимости экрана:
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(0), PointerInput.Origin.viewport(), start.getX(), start.getY()));
        //Нажатие левой кнопки мыши (симулирует нажатие пальца на экран)- createPointerDown
        //Перемещение указателя в конечную точку end за 1 секунду (Duration.ofMillis(1000))
        swipe.addAction(finger.createPointerDown(PointerInput.MouseButton.LEFT.asArg()));
        swipe.addAction(finger.createPointerMove(Duration.ofMillis(1000), PointerInput.Origin.viewport(), end.getX(), end.getY()));
        //Отпускание левой кнопки мыши (симулирует отпускание пальца)-createPointerUp
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
