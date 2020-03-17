package areaEstudoAutomacao;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;

public class TesteGoogle extends ConexaoDrive{

	@Test
	public void teste() throws InterruptedException {
		
		driver.get("http://www.google.com");

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
