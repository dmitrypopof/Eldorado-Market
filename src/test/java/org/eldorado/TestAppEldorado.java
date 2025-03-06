package org.eldorado;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.nativekey.AndroidKey;
import io.appium.java_client.android.nativekey.KeyEvent;
import io.qameta.allure.Step;
import org.assertj.core.api.SoftAssertions;
import org.eldorado.screens.MainScreen;
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

import static org.eldorado.screens.MainScreen.*;

public class TestAppEldorado extends TestBase {

    @Test
    @DisplayName("Task 1. Android")
    public void openSetting() {
        SoftAssertions softAssertions = new SoftAssertions();
        MainScreen mainScreen = new MainScreen(driver);

        boolean isNotSelectCatalog = mainScreen.checkSelectedCatalog();
        softAssertions.assertThat(isNotSelectCatalog).as("Catalog selected").isEqualTo(false);

        boolean isSelectCatalog = mainScreen
                .clickTabbarCatalog()
                .checkClickTabbarCatalog();
        softAssertions.assertThat(isSelectCatalog).as("Catalog selected").isEqualTo(true);

        mainScreen
                .swipe(Direction.UP)
                .swipe(720, 1560, 720, 3100)
                .clickToSearchField()
                .pressKeyboardButtons("tv")
                .pressKeyboardButton(AndroidKey.ENTER);

        String foundTitleText = mainScreen.getTextCountSearch();
        softAssertions.assertThat(foundTitleText).contains("Найдено");

        mainScreen.clickToFilter()
                .clickToManufacturer()
                .clickToXaomi()
                .clickToApply()
                .clickToShow();
        String foundSaleTitleText = mainScreen.getTextCountSearch();
        softAssertions.assertThat(foundTitleText).isNotEqualTo(foundSaleTitleText);


        mainScreen.blockScreen(3);
        String txt_TV = mainScreen.getTxtSearchField();

        softAssertions.assertThat(txt_TV).isEqualTo("tv");
        softAssertions.assertAll();
    }


}
