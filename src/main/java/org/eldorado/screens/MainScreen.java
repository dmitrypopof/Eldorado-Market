package org.eldorado.screens;

import io.appium.java_client.AppiumBy;
import org.openqa.selenium.By;

public class MainScreen {
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

}
