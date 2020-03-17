package areaEstudoAutomacao;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.ie.InternetExplorerDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;

public class ConexaoDrive {
	
	static WebDriver driver;

	@BeforeClass
	public static void iniciaNavegador() throws InterruptedException {
		
		String localPasta = System.getProperty("user.dir");
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");//"start-maximized"

		System.setProperty(
				"webdriver.chrome.driver",
				localPasta + "\\src\\test\\resources\\chromedriver.exe");
		// WebDriver driver = new FirefoxDriver();

		driver = new ChromeDriver(options);
	}

	@AfterClass
	public static void finalizaNavegador() throws InterruptedException {
		if (driver != null)
			driver.quit();
	}
}