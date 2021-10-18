package com.carreg.acceptancetest.pageobjects;

import com.carreg.acceptancetest.Config.Config;
import com.carreg.acceptancetest.util.DriverFactory;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

public class BasePage implements Config {
  private static final Logger LOG = Logger.getLogger(BasePage.class.getName());

  protected WebDriver driver;

  public BasePage(WebDriver driver) {
    this.driver = driver;
  }

  public WebElement findElement(By locator) {
    WebDriverWait wait = new WebDriverWait(driver, 15);
    wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    return driver.findElement(locator);
  }

  public void type(String inputText, By locator) {
    findElement(locator).sendKeys(inputText);
  }

  public void clear(By locator) {
    findElement(locator).clear();
  }

  public void click(By locator) {
    findElement(locator).click();
  }

  public void visit(String url) {
    driver.get(url);
  }

  public String getText(By locator) {
    return findElement(locator).getText();
  }


  protected void takeScreenshot(RuntimeException e, String fileName) {
    File screenShot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    try {
      FileUtils.copyFile(screenShot, new File(fileName + ".png"));
    } catch (IOException ioe) {
      throw new RuntimeException(ioe.getMessage(), ioe);
    }
    throw e;
  }
}
