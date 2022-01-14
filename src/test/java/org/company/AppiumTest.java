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
        } catch (InterruptedException e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
    }
    public static void openTesingApp() throws InterruptedException {
        DesiredCapabilities cap = new DesiredCapabilities();
        // adb -s emulator-5554 shell getprop
        cap.setCapability("deviceName","Redmi 3S");
        cap.setCapability("udid","28e3019c7d53");
        cap.setCapability("platformName","Android");
        cap.setCapability("platformVersion","6.0.1");
        cap.setCapability("appPackage","com.google.android.calculator");
        cap.setCapability("appActivity","com.android.calculator2.Calculator");
        try {
            URL url = new URL("http://127.0.0.1:4723/wd/hub");
            driver = new AppiumDriver<>(url, cap);
        } catch (MalformedURLException e) {
            System.out.println(e.getCause());
            System.out.println(e.getMessage());
            e.printStackTrace();
        }
        System.out.println("TestApplication started.");
        MobileElement one = driver.findElement(By.id("com.google.android.calculator:id/digit_1"));
        MobileElement two = driver.findElement(By.id("com.google.android.calculator:id/digit_2"));
        MobileElement plus = driver.findElement(By.id("com.google.android.calculator:id/op_add"));
        MobileElement equal = driver.findElement(By.id("com.google.android.calculator:id/eq"));
        one.click();
        plus.click();
        two.click();
        equal.click();
//        Thread.sleep(1000);
        MobileElement result = driver.findElement(By.className("android.widget.TextView"));
        String res = result.getText();
        System.out.println("\n result is " + res);
        Assert.assertEquals(res, "3");
    }
}