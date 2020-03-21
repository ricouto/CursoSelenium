package areaEstudoAutomacao;

import java.util.List;

import org.junit.AfterClass;
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
	
	public static void logonSiteOrange() throws InterruptedException{
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='txtUsername']")).sendKeys("Admin");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("admin123");
		Thread.sleep(1000);

		driver.findElement(By.name("Submit")).click();
		Thread.sleep(1000);
		System.out.println("\nAcessado o site com o perfil: " + driver.findElement(By.xpath("//a[contains(text(),'Welcome Admin')]")).getText());
	}
	
	public static void logoffSiteOrange() throws InterruptedException{
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[contains(text(),'Welcome Admin')]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//a[contains(text(),'Logout')]")).click();
		
		Thread.sleep(1000);
		System.out.println("\nSaindo do website " + driver.getTitle());
		Assert.assertEquals(driver.getTitle().trim(),"OrangeHRM");
	}
	
	
	@Test
	public void logonApplication() throws InterruptedException{

		logonSiteOrange();
		Thread.sleep(1000);

		System.out.println("Acessado com o perfil: " + driver.findElement(By.xpath("//a[contains(text(),'Welcome Admin')]")).getText().trim());
		Assert.assertEquals(driver.findElement(By.xpath("//a[contains(text(),'Welcome Admin')]")).getText().trim(),"Welcome Admin");
		
		logoffSiteOrange();
	}
	
	
	@Test
	public void loginSemUsername() throws InterruptedException{
		
		Thread.sleep(500);
		driver.findElement(By.xpath("//input[@id='txtUsername']")).clear();
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys("admin123");
		Thread.sleep(500);

		driver.findElement(By.name("Submit")).click();
		Thread.sleep(1000);
		
		System.out.println("\n" + driver.findElement(By.xpath("//div//span[@id='spanMessage']")).getText());
		Assert.assertEquals(driver.findElement(By.xpath("//div//span[@id='spanMessage']")).getText().trim(),"Username cannot be empty");
	}
	
	
	@Test
	public void loginSemPassword() throws InterruptedException{
		
		Thread.sleep(500);
		driver.findElement(By.xpath("//input[@id='txtUsername']")).sendKeys("admin");
		driver.findElement(By.xpath("//input[@id='txtPassword']")).clear();
		Thread.sleep(500);

		driver.findElement(By.name("Submit")).click();
		Thread.sleep(1000);
		
		System.out.println("\n" + driver.findElement(By.xpath("//div//span[@id='spanMessage']")).getText());
		Assert.assertEquals(driver.findElement(By.xpath("//div//span[@id='spanMessage']")).getText().trim(),"Password cannot be empty");
	}
	
	
	@Test
	public void consultaCliente() throws InterruptedException{

		logonSiteOrange();
		Thread.sleep(1000);
		
		System.out.println("\n.... Consulta Colaborador ....\n");
		
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
		logoffSiteOrange();
	}
	
	
	@Test
	public void insereJobTitles() throws InterruptedException{

		logonSiteOrange();
		Thread.sleep(1000);
		System.out.println("\n.... Inserir Job ....\n");
		
		driver.findElement(By.xpath("//a/b[contains(text(),'Admin')]")).click();
		Thread.sleep(500);
		
		driver.findElement(By.xpath("//li//a[contains(text(),'Job') and @id='menu_admin_Job']")).click();
		Thread.sleep(500);
		
		driver.findElement(By.xpath("//li//a[contains(text(),'Job Titles')]")).click();
		
		Thread.sleep(500);
		System.out.println(driver.findElement(By.xpath("//div//h1[contains(text(),'Job Titles')]")).getText());
		Assert.assertEquals(driver.findElement(By.xpath("//div//h1[contains(text(),'Job Titles')]")).getText(),"Job Titles");
	
		Thread.sleep(500);
		driver.findElement(By.xpath("//div//input[@id='btnAdd']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div//h1[contains(text(),'Add Job Title')]")).getText(),"Add Job Title");
		
		driver.findElement(By.xpath("//fieldset//input[@id='jobTitle_jobTitle']")).sendKeys("Quality Assurance");
		
		driver.findElement(By.xpath("//fieldset//textarea[@id='jobTitle_jobDescription']")).sendKeys(""
				+ "It is a long established fact that a reader will be distracted by "
				+ "the readable content of a page when looking at its layout. The point "
				+ "of using Lorem Ipsum is that it has a more-or-less normal distribution "
				+ "of letters, as opposed to using 'Content here, content here', making "
				+ "it look like readable English. Many desktop publishing packages and.");
		
		driver.findElement(By.xpath("//fieldset//textarea[@id='jobTitle_note']")).sendKeys(""
				+ "It is a long established fact that a reader will be distracted by "
				+ "the readable content of a page when looking at its layout. The point "
				+ "of using Lorem Ipsum is that it has a more-or-less normal distribution "
				+ "of letters, as opposed to using 'Content here, content here', making "
				+ "it look like readable English. Many desktop publishing packages and.");
		
		driver.findElement(By.xpath("//div//input[@id='btnSave']")).click();
		Thread.sleep(2000);
		
		try{
		List<WebElement> listOfElements = driver.findElements(By.xpath("//div/table//td/a"));
        
		for (int i=0; i<listOfElements.size();i++){
		      	if (listOfElements.get(i).getText().equalsIgnoreCase("Quality Assurance")){
		      		System.out.println("Incluido o novo cargo Quality Assurance");
		      	}
		}
        }catch (Exception e){
        	System.out.println("Aconteceu algo durante a execucao da tabela! " + e.getStackTrace());
        }
		logoffSiteOrange();
 	}
	
	@Test
	public void duplicateJobTitles() throws InterruptedException{

		logonSiteOrange();
		Thread.sleep(500);
		System.out.println("\n.... Tenta inserir Duplicate Job ....\n");
		
		driver.findElement(By.xpath("//a/b[contains(text(),'Admin')]")).click();
		Thread.sleep(500);
		
		driver.findElement(By.xpath("//li//a[contains(text(),'Job') and @id='menu_admin_Job']")).click();
		Thread.sleep(500);
		
		driver.findElement(By.xpath("//li//a[contains(text(),'Job Titles')]")).click();
		
		Thread.sleep(500);
		System.out.println(driver.findElement(By.xpath("//div//h1[contains(text(),'Job Titles')]")).getText());
		Assert.assertEquals(driver.findElement(By.xpath("//div//h1[contains(text(),'Job Titles')]")).getText(),"Job Titles");
	
		Thread.sleep(500);
		driver.findElement(By.xpath("//div//input[@id='btnAdd']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div//h1[contains(text(),'Add Job Title')]")).getText(),"Add Job Title");
		
		driver.findElement(By.xpath("//fieldset//input[@id='jobTitle_jobTitle']")).sendKeys("Quality Assurance");
		
		Thread.sleep(200);
		System.out.println(driver.findElement(By.xpath("//li//span[contains(text(),'Already exists')]")).getText() + " - job Quality Assurance");
		Assert.assertEquals(driver.findElement(By.xpath("//li//span[contains(text(),'Already exists')]")).getText(),"Already exists");
		
		logoffSiteOrange();
 	}
	
	@AfterClass
	public static void deleteJobTitles() throws InterruptedException{

		logonSiteOrange();
		Thread.sleep(500);
		System.out.println("\n.... Delete Job ....\n");
		
		driver.findElement(By.xpath("//a/b[contains(text(),'Admin')]")).click();
		Thread.sleep(500);
		
		driver.findElement(By.xpath("//li//a[contains(text(),'Job') and @id='menu_admin_Job']")).click();
		Thread.sleep(500);
		
		driver.findElement(By.xpath("//li//a[contains(text(),'Job Titles')]")).click();
		
		Thread.sleep(500);
		System.out.println(driver.findElement(By.xpath("//div//h1[contains(text(),'Job Titles')]")).getText());
		Assert.assertEquals(driver.findElement(By.xpath("//div//h1[contains(text(),'Job Titles')]")).getText(),"Job Titles");
	
		Thread.sleep(500);
		System.out.println("Removendo o cargo: " + driver.findElement(By.xpath("//tbody//td/a[contains(text(),'Quality Assurance')]")).getText());
		Thread.sleep(500);
		driver.findElement(By.xpath("//tbody//td/a[contains(text(),'Quality Assurance')]/../..//input[@type]")).click();
		
		Thread.sleep(1000);
		driver.findElement(By.xpath("//input[@id='btnDelete']")).click();
		driver.switchTo().activeElement();
		System.out.println(driver.findElement(By.xpath("//div[@id='deleteConfModal']//p")).getText());
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='deleteConfModal']//p")).getText().trim(),"Delete records?");
		
		Thread.sleep(500);
		driver.findElement(By.xpath("//div[@id='deleteConfModal']//input[@id='dialogDeleteBtn']")).click();
		
		Thread.sleep(1000);
		try{
		List<WebElement> listOfElements = driver.findElements(By.xpath("//div/table//td/a"));
		System.out.println("Number of elements: " +listOfElements.size());
        
		for (int i=0; i<listOfElements.size();i++){
		      System.out.println("Elementos da tabela: " + listOfElements.get(i).getText());
		      	if (listOfElements.get(i).getText().equalsIgnoreCase("Quality Assurance")){
		      		System.out.println("Não apagou o cargo Quality Assurance");
		      		Assert.fail();
		      	}
		}
		System.out.println("\n.... Removido o cargo Quality Assurance da grid ....\n");
        }catch (Exception e){
        	System.out.println("Aconteceu algo durante a execucao da tabela! " + e.getStackTrace());
        }
		Thread.sleep(200);
		logoffSiteOrange();
 	}
}