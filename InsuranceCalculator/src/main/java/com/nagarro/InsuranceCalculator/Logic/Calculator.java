package com.nagarro.InsuranceCalculator.Logic;

public class Calculator {
	
	public double calculateInsurancePremium(String carType,double price){
		
		double insurancePremium = 0;
		if(carType.equals("Hatchback")) {
			insurancePremium = price*0.05;
		}
		else if(carType.equals("Sedan")){
			insurancePremium = price*0.08;
		}
		else if(carType.equals("SUV")) {
			insurancePremium = price*0.10;
		}
		return insurancePremium;
	}
	
	
	public double calculateActualPremium(double temp, String insuranceType) {
		double ActualPremium=0;
		if(insuranceType.equals("Premium")) {
			ActualPremium=temp+(temp*0.20);
			return ActualPremium;
		}
		else {
			return temp;
		}
	}
}
