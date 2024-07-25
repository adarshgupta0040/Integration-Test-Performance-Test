package com.nagarro.InsuranceCalculator;

import com.nagarro.InsuranceCalculator.Entity.Car;
import com.nagarro.InsuranceCalculator.Logic.Calculator;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import junit.framework.Assert;


public class AppTest {

    private Car car;
    private Calculator calculator;
    private double actualPremium;

    @Given("^Execute Calculator Business$")
    public void execute_InsuranceCalculator_Business() {
        calculator = new Calculator();
    }

    @When("^I enter the car model \"([^\"]*)\"$")
    public void i_enter_the_car_model(String model) {
        car = new Car(model, null, 0, null);
    }

    @When("^the car type \"([^\"]*)\"$")
    public void the_car_type(String type) {
        car.setCarType(type);
    }

    @When("^the car price (\\d+\\.\\d+)$")
    public void the_car_price(double price) {
        car.setPrice(price);
    }

    @When("^the insurance type \"([^\"]*)\"$")
    public void the_insurance_type(String insuranceType) {
        car.setInsuranceType(insuranceType);
    }

    @Then("^we should get the actual premium (\\d+\\.\\d+)$")
    public void we_should_get_the_actual_premium(double expectedPremium) {
        double calculatedPremium = calculator.calculateInsurancePremium(car.getCarType(), car.getPrice());
        actualPremium = calculator.calculateActualPremium(calculatedPremium, car.getInsuranceType());
        Assert.assertEquals(expectedPremium, actualPremium, 0.01);
    }
}
