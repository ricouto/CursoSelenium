package areaEstudoAutomacao;

import org.junit.Test;
import org.openqa.selenium.By;
import org.junit.Assert;

public class SiteAleatorio extends ConexaoDrive {
	
	@Test
	public void loginApplication() throws InterruptedException{
		
		driver.get("https://opensource-demo.orangehrmlive.com/");

		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='txtUsername']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("admin123");
		Thread.sleep(1000);

		driver.findElement(By.name("Submit")).click();
		Thread.sleep(3000);

		System.out.println(driver.getTitle());
		Assert.assertEquals(driver.getTitle().trim(),"OrangeHRM");
	}
}