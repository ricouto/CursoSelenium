package areaEstudoAutomacao;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.ie.InternetExplorerDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteGoogle {

	static WebDriver driver;

	@BeforeClass
	public static void iniciaNavegador() throws InterruptedException {
		
		String localPasta = System.getProperty("user.dir");
		
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");

		System.setProperty(
				"webdriver.chrome.driver",
				localPasta + "\\src\\test\\resources\\chromedriver.exe");
		// WebDriver driver = new FirefoxDriver();

		driver = new ChromeDriver(options);

		// WebDriver driver = new InternetExplorerDriver();

		driver.get("http://www.google.com");
	}

	@AfterClass
	public static void finalizaNavegador() throws InterruptedException {
		if (driver != null)
			driver.quit();
	}

	@Test
	public void teste() throws InterruptedException {

		Assert.assertEquals("Google", driver.getTitle());
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@class='gLFyf gsfi']")).sendKeys(
				"informatica");

		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@class='gLFyf gsfi']")).sendKeys(
				Keys.ENTER);

		String returnText = driver
				.findElement(By.xpath("//div[@id='result-stats']")).getText()
				.substring(0, 15);
		System.out.println("\nApresentou o resultado na tela >> " + returnText
				+ "\n");
		Thread.sleep(4000);
		if (returnText.equalsIgnoreCase("Aproximadamente")) {
			Assert.assertTrue(true);
			System.out.println(driver.findElement(
					By.xpath("//div[@id='result-stats']")).getText());
		} else {
			System.out.println("Favor verificar o texto inserido no Google");
			Assert.fail();
		}
	}
}
