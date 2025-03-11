package org.eldorado;

import io.appium.java_client.android.nativekey.AndroidKey;
import org.assertj.core.api.SoftAssertions;
import org.eldorado.action.Direction;
import org.eldorado.action.SwipeHelper;
import org.eldorado.screens.MainScreen;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class TestAppEldorado extends TestBase {

    @Test
    @DisplayName("Task 1. Android")
    public void openSetting() {
        SoftAssertions softAssertions = new SoftAssertions();
        MainScreen mainScreen = new MainScreen(driver);
        SwipeHelper swipeHelper = new SwipeHelper(driver);

        boolean isNotSelectCatalog = mainScreen.checkSelectedCatalog();
        softAssertions.assertThat(isNotSelectCatalog).as("Catalog selected").isEqualTo(false);

        boolean isSelectCatalog = mainScreen
                .clickTabbarCatalog()
                .checkClickTabbarCatalog();
        softAssertions.assertThat(isSelectCatalog).as("Catalog selected").isEqualTo(true);

        swipeHelper
                .swipe(Direction.UP);
        swipeHelper
                .swipe(720, 1560, 720, 3100);

        mainScreen
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
