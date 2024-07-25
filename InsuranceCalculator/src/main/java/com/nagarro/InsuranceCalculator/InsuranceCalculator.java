package com.nagarro.InsuranceCalculator;

import java.util.Scanner;

import com.nagarro.InsuranceCalculator.Logic.Calculator;


/**
 * Hello world!
 *
 */
public class InsuranceCalculator 
{
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);									//Scanner class object 'sc'
		boolean continueInput = true;
		while (continueInput) {
			try {
			
				System.out.print("Enter Car Model : ");
				String carModel = sc.next();
				
				System.out.print("Car Type (Hatchback/Sedan/SUV): ");			//Taking Input for Car Details
				String carType = sc.next();													
				if (!(carType.equals("Hatchback") || carType.equals("Sedan") || carType.equals("SUV"))) {
					throw new IllegalArgumentException("Invalid car type");
				}
				
				System.out.print("Car Cost Price: $");
				double price1 = sc.nextDouble();
				if (price1 < 0) {												// checking for Exception
					throw new IllegalArgumentException("Invalid price");
				}
				
				System.out.print("Insurance Type (Basic/Premium): ");
				String insurance = sc.next();
				
				System.out.println(" ");
				System.out.println("CarModel = "+ carModel);						// Printing the Input
				System.out.println("CarType = "+ carType);
				System.out.println("Price of Car = $"+ price1);
				System.out.println("InsuranceType : "+ insurance);
				
//				Car C1= new Car(carModel,carType,price1,insurance);		//Creating Object
				Calculator calculator = new Calculator();
				double temp=calculator.calculateInsurancePremium(carType,price1);
				
				//System.out.println("Insurance Premium = $" + C1.CalInsurancePremium());
				System.out.println("Actual Premium to be paid = $" + calculator.calculateActualPremium(temp, insurance));
				System.out.println(" ");
				
				System.out.print("Do you want to enter details of any other car (y/n): ");			//Repeat
		        String inp = sc.next();
		        continueInput = inp.equals("y");
			}
			
			catch (IllegalArgumentException e) {								//Exception Message
				System.out.println("Error: " + e.getMessage() + ", please try again");
			}
		}
	}
}
