package pages;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import Utilities.readExcel;


public class SubscribtionPage extends Base  {
	WebDriver driver;


	readExcel excel = new readExcel();

	String Type = null;
	String Price = null;
	String Currency = null;

	ArrayList<String> Actual_Type = new ArrayList<String>();
	ArrayList<String> Actual_Price = new ArrayList<String>();
	ArrayList<String> Actual_Currency= new ArrayList<String>();


	public SubscribtionPage(WebDriver Driver){
		this.driver = Driver;

	}


	public void GetSubscrition_data() throws IOException   {
		List<WebElement> element_type  = driver.findElements(By.xpath("(//div[@class='plan-names'])[1]/div/strong"));
		List<WebElement> element_Price  = driver.findElements(By.xpath("(//div[@class='price'])/b"));
		List<WebElement> element_Currency  = driver.findElements(By.xpath("(//div[@class='price'])/i"));

		for (int i = 0; i < element_type.size(); i++) {
			Type = element_type.get(i).getText();
			Actual_Type.add(Type);

			Price = element_Price.get(i).getText();
			Actual_Price.add(Price);

			Currency = element_Currency.get(i).getText();
			Actual_Currency.add(Currency);

		}

	}
	int sheet = 0;
	public boolean   check_Subscribtion(String c) throws IOException   {
		boolean valid = false;

		if (c.equals("UAE")) {sheet=1;} 
		else if (c.equals("Algeria")) {sheet=2;} 

		System.out.println("Subscription for : "+c);
		System.out.println("Actual Type :"+Actual_Type);
		System.out.println("Actual Price :"+excel.convert(Actual_Price));
		System.out.println("Actual Currency :"+Actual_Currency);

		System.out.println("                --                 ");

		System.out.println("Expected  Type :"+excel.readColumnFromExcel("TestDate.xlsx", sheet, 0));

		System.out.println("Expected  Price :"+excel.readColumnFromExcel("TestDate.xlsx", sheet, 1));

		System.out.println("Expected  Currency :"+excel.readColumnFromExcel("TestDate.xlsx", sheet,2));

		if (
				(excel.readColumnFromExcel("TestDate.xlsx", sheet, 0).equals(Actual_Type))
				&&
				(excel.readColumnFromExcel("TestDate.xlsx", sheet, 2).equals(Actual_Currency))
				&&
				(excel.convert(excel.readColumnFromExcel("TestDate.xlsx", sheet, 1)).equals(excel.convert(Actual_Price))	)
				)	 {
			System.out.println("Subscription Type Data for "+c+" is correct");
			valid = true;
		} else {
			System.out.println("Subscription Type Data for "+c+" not correct");
			valid = false;
		}
		Actual_Type.clear();
		Actual_Currency.clear();
		Actual_Price.clear();
		System.out.println("....................................");

		return valid;
	}
	public void   OpenUAE()   {
		WebElement country_btn  = driver.findElement(By.id("country-btn"));
		WebElement UAE  = driver.findElement(By.id("ae"));

		clickButton(country_btn);
		clickButton(UAE);
		implicitlyWait(1000,driver);


	}
	public void   OpenAlgeria()   {
		WebElement country_btn  = driver.findElement(By.id("country-btn"));
		WebElement Algeria  = driver.findElement(By.id("dz"));

		clickButton(country_btn);
		clickButton(Algeria);
		implicitlyWait(1000,driver);


	}



}
