package areaEstudoAutomacao;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
//import org.openqa.selenium.ie.InternetExplorerDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteGoogle {

	@Test
	public void teste() throws InterruptedException {
		ChromeOptions options = new ChromeOptions();
		options.addArguments("--headless");
		 
		System.setProperty("webdriver.chrome.driver",
		"C:\\Users\\HiTECH-PC\\workspace\\CursoSelenium\\src\\test\\resources\\chromedriver.exe");
		// WebDriver driver = new FirefoxDriver();
		
		WebDriver driver = new ChromeDriver(options);
				
		// WebDriver driver = new InternetExplorerDriver();

		driver.get("http://www.google.com");
		Assert.assertEquals("Google", driver.getTitle());
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@class='gLFyf gsfi']")).sendKeys("informatica");
				
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@class='gLFyf gsfi']")).sendKeys(Keys.ENTER);

		String returnText = driver.findElement(By.xpath("//div[@id='result-stats']")).getText().substring(0, 15);
		System.out.println("\nApresentou o resultado na tela >> " + returnText +"\n");
		Thread.sleep(5000);
		if (returnText.equalsIgnoreCase("Aproximadamente")) {
			Assert.assertTrue(true);
			System.out.println(driver.findElement(By.xpath("//div[@id='result-stats']")).getText());
		} else {
			System.out.println("Favor verificar o texto inserido no Google");
			Assert.fail();
		}
		driver.quit();
	}
}
