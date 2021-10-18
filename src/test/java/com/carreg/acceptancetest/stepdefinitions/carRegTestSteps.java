package com.carreg.acceptancetest.stepdefinitions;

import com.carreg.acceptancetest.Config.Config;
import com.carreg.acceptancetest.pageobjects.CarRegPage;
import com.carreg.acceptancetest.util.DriverFactory;
import com.carreg.acceptancetest.util.ReadCarInputFile;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class carRegTestSteps implements Config {

  private static final Logger LOG = Logger.getLogger(carRegTestSteps.class.getName());
  List<String> carRegDetails = new ArrayList<String>();
  private CarRegPage carRegPage;

  @Given("^user launches car tax check site$")
  public void user_launches_car_Reg_site_site() throws Throwable {
    carRegPage = new CarRegPage(DriverFactory.getDriver());
  }

  @When("^user enter \"([^\"]*)\" carreg numbers$")
  public void userEntersCarRegNumbers(String carSeqNumber) throws Throwable {
    carRegDetails = ReadCarInputFile.getMatchingCarReg();
    carRegPage.enterCarReg(carRegDetails.get(Integer.parseInt(carSeqNumber) - 1));
  }

  @Then("^user should see \"([^\"]*)\" car details$")
  public void userShouldSeeCarDetails(String carSeqNumber) throws Throwable {
    String carMake = carRegPage.getCarMake();
    String carModel = carRegPage.getCarModel();
    String carColour = carRegPage.getCarColour();
    String carYear = carRegPage.getCarYear();
    Assert.assertTrue("No Matching Car Model Found", carModel.length() > 0);
    List<String> carDetails =
        ReadCarInputFile.getCarRegDetails(carRegDetails.get(Integer.parseInt(carSeqNumber) - 1));
    System.out.println(carRegDetails.get(Integer.parseInt(carSeqNumber) - 1));
    System.out.println(carModel);

    Assert.assertEquals(carMake, carDetails.get(1));
    Assert.assertEquals(carModel, carDetails.get(2));
    Assert.assertEquals(carColour, carDetails.get(3));
    Assert.assertEquals(carYear, carDetails.get(4));
  }
}
