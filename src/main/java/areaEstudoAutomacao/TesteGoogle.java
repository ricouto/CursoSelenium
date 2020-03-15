package areaEstudoAutomacao;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.ie.InternetExplorerDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;

public class TesteGoogle {

	@Test
	public void teste() throws InterruptedException {
		// System.setProperty("webdriver.firefox.marionette",
		// "C://Users//HiTECH-PC//Documents//estudandoSelenium//geckodriver.exe");
		// WebDriver driver = new FirefoxDriver();
		WebDriver driver = new ChromeDriver();
		// WebDriver driver = new InternetExplorerDriver();

		driver.get("http://www.google.com");
		Assert.assertEquals("Google", driver.getTitle());
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@class='gLFyf gsfi']")).sendKeys("informatica");
				
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@class='gLFyf gsfi']")).sendKeys(Keys.ENTER);

		int returnText = driver.findElement(By.xpath("//div[@id='result-stats']")).getText().hashCode();
		Thread.sleep(2000);
		if (returnText > 0) {
			Assert.assertTrue(true);
			System.out.println(driver.findElement(By.xpath("//div[@id='result-stats']")).getText());
		} else {
			System.out.println("Favor verificar o texto inserido no Google");
			Assert.fail();
		}
		driver.close();
	}
}
