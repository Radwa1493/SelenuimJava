package stepDefinitions;

import java.io.IOException;

import MyRunner.TestRunner;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import pages.SubscribtionPage;


public class StepDefinition{

	SubscribtionPage SubscribtionP   = new SubscribtionPage(TestRunner.driver);


	@Then("Check Subscrition Egypt data")
	public void Check_SA() throws IOException  {
		SubscribtionP.GetSubscrition_data();

		TestRunner.softAssertion.assertTrue(SubscribtionP.check_Subscribtion("EGYPT"));

	}
	@Then("Check Subscrition UAE data")
	public void Check_UAE() throws IOException  {
		SubscribtionP.OpenUAE();
		SubscribtionP.GetSubscrition_data();

		TestRunner.softAssertion.assertTrue(SubscribtionP.check_Subscribtion("UAE"));

	}

	@Then("Check Subscrition Algeria data")
	public void Check_Algeria() throws IOException  {
		SubscribtionP.OpenAlgeria();
		SubscribtionP.GetSubscrition_data();

		TestRunner.softAssertion.assertTrue(SubscribtionP.check_Subscribtion("Algeria"));

	}



}
