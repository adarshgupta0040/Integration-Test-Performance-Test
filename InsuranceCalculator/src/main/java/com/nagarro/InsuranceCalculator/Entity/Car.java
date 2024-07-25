package com.nagarro.InsuranceCalculator.Entity;

public class Car {
	String carModel;
	String carType;
	double price;
	String insuranceType;

    public Car(String carModel, String carType, double price, String insuranceType) {
    	this.carModel = carModel;
		this.carType = carType;
		this.price = price;
		this.insuranceType = insuranceType;
    }

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public String getCarType() {
		return carType;
	}

	public void setCarType(String carType) {
		this.carType = carType;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getInsuranceType() {
		return insuranceType;
	}

	public void setInsuranceType(String insuranceType) {
		this.insuranceType = insuranceType;
	}

    
}