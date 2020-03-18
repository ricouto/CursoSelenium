package areaEstudoAutomacao;

import java.util.List;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.junit.Assert;

public class SiteAleatorio extends ConexaoDrive {
	
	@BeforeClass
	public static void openSite(){
		driver.get("https://opensource-demo.orangehrmlive.com/");
	}
	
	@Before
	public void cleanField(){
		driver.findElement(By.xpath("//input[@id='txtUsername']")).clear();
		driver.findElement(By.xpath("//input[@id='txtPassword']")).clear();
	}
	
	@Test
	public void logonApplication() throws InterruptedException{

		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='txtUsername']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("admin123");
		Thread.sleep(1000);

		driver.findElement(By.name("Submit")).click();
		Thread.sleep(2000);

		System.out.println(driver.findElement(By.xpath("//a[contains(text(),'Welcome Admin')]")).getText());
		Assert.assertEquals(driver.findElement(By.xpath("//a[contains(text(),'Welcome Admin')]")).getText().trim(),"Welcome Admin");
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[contains(text(),'Welcome Admin')]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();
		
		Thread.sleep(2000);
		System.out.println(driver.getTitle());
		Assert.assertEquals(driver.getTitle().trim(),"OrangeHRM");
	}
	
	@Test
	public void loginSemUsername() throws InterruptedException{
		
		//driver.get("https://opensource-demo.orangehrmlive.com/");

		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='txtUsername']")).clear();
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("admin123");
		Thread.sleep(1000);

		driver.findElement(By.name("Submit")).click();
		Thread.sleep(1000);
		
		System.out.println(driver.findElement(By.xpath("//div//span[@id='spanMessage']")).getText());
		Assert.assertEquals(driver.findElement(By.xpath("//div//span[@id='spanMessage']")).getText().trim(),"Username cannot be empty");
	}
	
	@Test
	public void loginSemPassword() throws InterruptedException{
		
		//driver.get("https://opensource-demo.orangehrmlive.com/");

		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='txtUsername']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).clear();
		Thread.sleep(1000);

		driver.findElement(By.name("Submit")).click();
		Thread.sleep(1000);
		
		System.out.println(driver.findElement(By.xpath("//div//span[@id='spanMessage']")).getText());
		Assert.assertEquals(driver.findElement(By.xpath("//div//span[@id='spanMessage']")).getText().trim(),"Password cannot be empty");
	}
	
	@Test
	public void consultaCliente() throws InterruptedException{

		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='txtUsername']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("admin123");
		Thread.sleep(1000);

		driver.findElement(By.name("Submit")).click();
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//a/b[contains(text(),'Admin')]")).click();
		Thread.sleep(500);
		driver.findElement(By.xpath("//input[@id='searchSystemUser_userName']")).sendKeys("fiona.grace");
		
		driver.findElement(By.xpath("//input[@id='searchBtn']")).click();
		Thread.sleep(500);
		
		try{
		List<WebElement> listOfElements = driver.findElements(By.xpath("//div/table//td"));
		System.out.println("Number of elements: " +listOfElements.size());
        
		for (int i=0; i<listOfElements.size();i++){
		      System.out.println("Elementos da tabela: " + listOfElements.get(i).getText());
		      Assert.assertEquals(listOfElements.get(1).getText(),"fiona.grace");
		}
        }catch (Exception e){
        	System.out.println("Aconteceu algo durante a execucao da tabela! " + e.getStackTrace());
        }
		
		Thread.sleep(2000);
		driver.findElement(By.xpath("//a[contains(text(),'Welcome Admin')]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();
		
		Thread.sleep(2000);
		System.out.println(driver.getTitle());
		Assert.assertEquals(driver.getTitle().trim(),"OrangeHRM");
	}
}