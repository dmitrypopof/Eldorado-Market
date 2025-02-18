### 1. Автотесты для мобильного приложения Eldorado

* Версия приложения
* Java Amazon Corretto version 17.0.12
* Junit 5
* Appium version 2.12.1
* UiAutomator2 version 3.8.1
* Allure-report version 2.24.0
* Emulator Pixel 7 API 35

### 2. Ключевые проверки для smoke-тестирования:
1.

2.

3.

### 3.Формирование allure-отчета - path/

### 4. Прочее:
1. Поиск activities -
   * adb shell dumpsys activity activities | grep mActivityComponent | cut -d= -f2 | sort -u
   * "appium:appPackage": "ru.mvm.eldo"
   * "appium:appActivity": ".presentation.splash.activity.SplashActivity"
   * 

