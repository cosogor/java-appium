package org.company;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;

import java.net.MalformedURLException;
import java.net.URL;


public class AppiumTest {
    WebDriver    driver1;    // for desktop browsers
//    AndroidDriver driver; // for android specific testing
//    IOSDriver   driver; // for IOS specific testing
    static AppiumDriver<MobileElement> driver; //  for appium


    public static void main (String[] args){
        try {
            openTesingApp();
        } catch (MalformedURLException e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    public static void openTesingApp() throws MalformedURLException {
        DesiredCapabilities cap = new DesiredCapabilities();
        // adb -s emulator-5554 shell getprop
        cap.setCapability("deviceName","Redmi 9C NFC");
        cap.setCapability("udid","NBAAB6V4TOVCT4OR");
        cap.setCapability("platformName","Android");
        cap.setCapability("platformVersion","10");
        cap.setCapability("appPackage","com.miui.calculator");
        cap.setCapability("appActivity","com.miui.calculator.cal.CalculatorActivity");
        URL url = new URL("http://127.0.0.1:4723/wd/hub");
        driver = new AppiumDriver<>(url, cap);
        System.out.println("TestApplication started.");
        MobileElement one = driver.findElement(By.id("com.miui.calculator:digit1"));
        MobileElement two = driver.findElement(By.id("com.miui.calculator:digit2"));
        MobileElement plus = driver.findElement(By.id("com.miui.calculator:plus"));
        MobileElement equal = driver.findElement(By.id("com.miui.calculator:equal"));
        MobileElement result = driver.findElement(By.className("android.widget.EditText"));
        one.click();
        plus.click();
        two.click();
        equal.click();
        String res = result.getText();
        System.out.println("\n result is " + res);
        Assert.assertNotEquals(res, "5");
    }
}
