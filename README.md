### 1. ��������� ��� ���������� ���������� Eldorado

* ������ ����������
* Java Amazon Corretto version 17.0.12
* Junit 5
* Appium version 2.12.1
* UiAutomator2 version 3.8.1
* Allure-report version 2.24.0
* Emulator Pixel 7 API 35

### 2. �������� �������� ��� smoke-������������:
1.

2.

3.

### 3.������������ allure-������ - path/

### 4. ������:
1. ����� activities -
   * adb shell dumpsys activity activities | grep mActivityComponent | cut -d= -f2 | sort -u
   * "appium:appPackage": "ru.mvm.eldo"
   * "appium:appActivity": ".presentation.splash.activity.SplashActivity"
   * 

