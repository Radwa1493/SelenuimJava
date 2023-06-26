package MyRunner;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.asserts.SoftAssert;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.github.bonigarcia.wdm.WebDriverManager;

@CucumberOptions(
		features = "src/main/java/Features"
		, glue = {"stepDefinitions"}

		)public class TestRunner extends AbstractTestNGCucumberTests {
//	public static  WebDriver driver;
	public static SoftAssert softAssertion= new SoftAssert();
	public static  RemoteWebDriver driver = null;
	
	@Parameters({ "browser" })
	@BeforeTest
	public static void setUp(String browser) throws MalformedURLException {
		System.out.println("Starting..... ");	
		if (browser.equals("Firefox") ){
			System.out.println("Open Local Firefox");
			WebDriverManager.firefoxdriver().setup();
			driver= new FirefoxDriver();
			DesiredCapabilities capabilities = DesiredCapabilities.firefox();
			capabilities.setCapability("browser.privatebrowsing.autostart", true);
		}
		else if (browser.equals("Chrome") ){
			System.out.println("Open Local Chrome");
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability(ChromeOptions.CAPABILITY, options);
			driver = new ChromeDriver(options); 
		}

		else if (browser.equals("Edge") ){
			System.out.println("Open Local Edge");
			// Download and setup Edge WebDriver
			WebDriverManager.edgedriver().setup();
			DesiredCapabilities capabilities = DesiredCapabilities.edge();
			capabilities.setCapability("browser.privatebrowsing.autostart", true);
			driver = new EdgeDriver();
		}

		else if (browser.equals("ChromeDocker") ){
			System.out.println("Open Local Chrome Docker");
			ChromeOptions options = new ChromeOptions();
			options.addArguments("--no-sandbox");
			options.addArguments("--disable-dev-shm-usage");

			driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), options);
		}

		driver.manage().window().maximize(); 
		driver.get("http://subscribe.stctv.com");
	}

	@AfterSuite
	public static void tearDown() {
		softAssertion.assertAll();
	}


}


