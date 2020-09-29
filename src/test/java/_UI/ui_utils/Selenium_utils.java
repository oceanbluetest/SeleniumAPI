package _UI.ui_utils;

import _UI.step_definition.ScenarioContext;
import common_util.DatetTime;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Set;

public class Selenium_utils {
    ScenarioContext context;

    public Selenium_utils(ScenarioContext scenarioContext){
        context = scenarioContext;
    }

    public void quitDriver(){
        context.driver.quit();
        context.driver = null;
    }

    public static void sleep(long milliseconds){
        try{
            Thread.sleep(milliseconds);
        }catch (InterruptedException e){
            e.printStackTrace();;
        }
    }

    public void waitForClickability(WebElement element){
        WebDriverWait wait = new WebDriverWait(context.driver, 10);
        wait.until(ExpectedConditions.elementToBeClickable(element));
    }

    public void waitForVisibility(WebElement element){
        WebDriverWait wait = new WebDriverWait(context.driver, 10);
        wait.until(ExpectedConditions.visibilityOf(element));
    }

    public void waitForVisibilityOfAll(List<WebElement> elementList){
        WebDriverWait wait = new WebDriverWait(context.driver, 10);
        wait.until(ExpectedConditions.visibilityOfAllElements(elementList));
    }

    public void waitForPageToLoad(){
        ExpectedCondition<Boolean> pageLoadCondition = driver -> ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
        WebDriverWait wait = new WebDriverWait(context.driver, 10);
        wait.until(pageLoadCondition);
    }

    public void waitForAlert(){
        WebDriverWait wait = new WebDriverWait(context.driver, 5);
        wait.until(ExpectedConditions.alertIsPresent());
    }

    public void click(WebElement element){
        waitForClickability(element);
        highlightElement(element);
        element.click();
    }

    public void sendKeys(WebElement element, String input){
        waitForVisibility(element);
        highlightElement(element);
        element.sendKeys(input);
    }

    public String getText(WebElement element){
        waitForVisibility(element);
        highlightElement(element);
        return element.getText();
    }

    public void moveIntoView(WebElement element){
        try{
            ((JavascriptExecutor)context.driver).executeScript("arguments[0].scrollIntoView(true);", element);
        }catch (Exception e){
            e.printStackTrace();
        }
        highlightElement(element);
    }

    public void highlightElement(WebElement element){
        JavascriptExecutor executor = (JavascriptExecutor)context.driver;

        for (int i = 0; i < 2; i++){
            try{
                if(i == 0){
                    executor.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: black; border:3px solid red; background: yellow");
                }else {
                    sleep(300);
                    executor.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
                }
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    public void switchToWindow(String currentPageID){
        Set<String> set = context.driver.getWindowHandles();
        for (String window: set){
            if (!window.equalsIgnoreCase(currentPageID)) context.driver.switchTo().window(window);
        }
    }

    public void takeScreenshot(){
        waitForPageToLoad();
        try{
            byte[] screenshot = ((TakesScreenshot) context.driver).getScreenshotAs(OutputType.BYTES);
            context.scenario.attach(screenshot, "image/png", "Screenshot | " + DatetTime.CURRENT_DATETIME);
        }catch (WebDriverException e){
            e.printStackTrace();
        }
    }

    public void takeScreenshot(WebElement element){
        waitForPageToLoad();
        moveIntoView(element);
        JavascriptExecutor executor = (JavascriptExecutor)context.driver;

        try{
            executor.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "color: black; border:3px solid red; background: yellow");
            byte[] screenshot = ((TakesScreenshot) context.driver).getScreenshotAs(OutputType.BYTES);
            context.scenario.attach(screenshot, "image/png", "Screenshot | " + DatetTime.CURRENT_DATETIME);
            executor.executeScript("arguments[0].setAttribute('style', arguments[1]);", element, "");
        }catch (WebDriverException e){
            e.printStackTrace();
        }
    }

    public void logInfo(String msg, boolean takeScreenshot){
        context.scenario.log(DatetTime.CURRENT_DATETIME + " INFO: " + msg);
        if (takeScreenshot)
            takeScreenshot();
    }
}
