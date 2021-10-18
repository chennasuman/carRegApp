package com.carreg.acceptancetest.pageobjects;

import com.carreg.acceptancetest.util.DriverFactory;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.logging.Logger;

public class CarRegPage extends BasePage {

  private static final Logger LOG = Logger.getLogger(CarRegPage.class.getName());
  By carRegInput = By.id("vrm-input");
  By freeCarCheck = By.xpath("//button[text()='Free Car Check']");
  By carModel = By.xpath("//dt[text()='Model']/following-sibling::dd");
  By carMake = By.xpath("//dt[text()='Make']/following-sibling::dd");
  By carColour = By.xpath("//dt[text()='Colour']/following-sibling::dd");
  By carYear = By.xpath("//dt[text()='Year']/following-sibling::dd");

  public CarRegPage(WebDriver driver) {
    super(driver);
    visit(baseUrl);

    DriverFactory.waitForTextToBePresent(
        driver, "Free Car Check", findElement(By.cssSelector("h1")));
  }

  public void enterCarReg(String carReg) {
    try {
      clear(carRegInput);
      type(carReg, carRegInput);
      click(freeCarCheck);
    } catch (RuntimeException e) {
      takeScreenshot(e, "searchError");
    }
  }

  public String getCarModel() {
    return getText(carModel);
  }

  public String getCarMake() {
    return getText(carMake);
  }

  public String getCarColour() {
    return getText(carColour);
  }

  public String getCarYear() {
    return getText(carYear);
  }
}
