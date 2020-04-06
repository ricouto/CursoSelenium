package areaEstudoAutomacao;

import java.util.List;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.junit.Assert;

public class SiteAleatorio extends ConexaoDrive {
	
	public static String loginUser = "Admin";
	public static String passUser = "admin123";
	public static String searchUserSite = "russel.hamilton";
	public static String jobCRUD = "Quality Assurance";
	public static String lblNameSite = "OrangeHRM";
	public static String lblWelcomeSite = "Welcome Admin";
	public static String lblUserEmpty = "Username cannot be empty";
	public static String lblPassEmpty = "Password cannot be empty";
	public static String lblJobPage = "Job Titles";
	public static String lblAddJob = "Add Job Title";
	public static String lblAlreadyExists = "Already exists";
	public static String lblDelete = "Delete records?";
	private static DSL dsl = new DSL();
	
	@Rule
	public ScreenShotFailure failure = new ScreenShotFailure();
	
	@BeforeClass
	public static void openSite(){
		driver.get("https://opensource-demo.orangehrmlive.com/");
		System.out.println("\n**** Site Orange HRM Live de Treinamento ****\n");
	}
	
	@AfterClass
	public static void exitSite(){
		System.out.println("\n**** Saindo do site Orange HRM Live de Treinamento ****\n");
	}
	
	@Before
	public void cleanField(){
		dsl.limparCampo("//input[@id='txtUsername']");
		dsl.limparCampo("//input[@id='txtPassword']");
	}
	
	public static void logonSiteOrange() throws InterruptedException{
		Thread.sleep(500);
		dsl.escreve("//input[@id='txtUsername']", loginUser);
		dsl.escreve("//input[@id='txtPassword']", passUser);
		Thread.sleep(250);

		dsl.clicarBotao("//input[@id='btnLogin']");
		Thread.sleep(500);
		
		System.out.println("\nAcessado o site com o perfil: " + dsl.obterTexto(By.xpath("//a[contains(text(),'Welcome Admin')]")));
	}
	
	public static void logoffSiteOrange() throws InterruptedException{
		Thread.sleep(500);
		dsl.clicarBotao("//a[contains(text(),'Welcome Admin')]");
		Thread.sleep(250);
		dsl.clicarBotao("//a[contains(text(),'Logout')]");
		
		Thread.sleep(500);
		System.out.println("\nSaindo do website " + driver.getTitle());
		Assert.assertEquals(driver.getTitle().trim(),lblNameSite);
	}
	
	@Test
	public void logonApplication() throws InterruptedException{
		logonSiteOrange();
		Thread.sleep(500);
		System.out.println("Acessado com o perfil: " + dsl.obterTexto(By.xpath("//a[contains(text(),'Welcome Admin')]")));
		Assert.assertEquals(dsl.obterTexto(By.xpath("//a[contains(text(),'Welcome Admin')]")),lblWelcomeSite);
		logoffSiteOrange();
	}
	
	@Test
	public void loginSemUsername() throws InterruptedException{
		dsl.limparCampo("//input[@id='txtUsername']");
		dsl.escreve("//input[@id='txtPassword']", passUser);
		dsl.clicarBotao("//input[@id='btnLogin']");
		Thread.sleep(250);
		System.out.println("\n" + dsl.obterTexto(By.xpath("//div//span[@id='spanMessage']")));
		Assert.assertEquals(dsl.obterTexto(By.xpath("//div//span[@id='spanMessage']")),lblUserEmpty);
	}
	
	@Test
	public void loginSemPassword() throws InterruptedException{
		dsl.escreve("//input[@id='txtUsername']", loginUser);
		dsl.limparCampo("//input[@id='txtPassword']");
		dsl.clicarBotao("//input[@id='btnLogin']");
		Thread.sleep(250);
		System.out.println("\n" + dsl.obterTexto(By.xpath("//div//span[@id='spanMessage']")));
		Assert.assertEquals(dsl.obterTexto(By.xpath("//div//span[@id='spanMessage']")),lblPassEmpty);
	}
	
	@Test
	public void consultaCliente() throws InterruptedException{

		logonSiteOrange();
		
		System.out.println("\n.... Consulta Colaborador ....\n");
		
		dsl.clicarBotao("//a/b[contains(text(),'Admin')]");
		Thread.sleep(250);
		dsl.escreve("//input[@id='searchSystemUser_userName']", searchUserSite);
		
		dsl.clicarBotao("//input[@id='searchBtn']");
		Thread.sleep(250);
		
		try{
		List<WebElement> listOfElements = driver.findElements(By.xpath("//div/table//td"));
		System.out.println("Number of elements: " +listOfElements.size());
        
		for (int i=0; i<listOfElements.size();i++){
		      System.out.println("Elementos da tabela: " + listOfElements.get(i).getText());
		      Assert.assertEquals(listOfElements.get(1).getText(),searchUserSite);
		}
        }catch (Exception e){
        	System.out.println("Aconteceu algo durante a execucao da tabela! " + e.getStackTrace());
        }
		logoffSiteOrange();
	}
	
	
	@Test
	public void insereJobTitles() throws InterruptedException{

		logonSiteOrange();
		System.out.println("\n.... Inserir Job ....\n");
		
		dsl.clicarBotao("//a/b[contains(text(),'Admin')]");
		Thread.sleep(100);

		dsl.clicarBotao("//li//a[contains(text(),'Job') and @id='menu_admin_Job']");
		Thread.sleep(100);
		
		dsl.clicarBotao("//li//a[contains(text(),'Job Titles')]");
		
		Thread.sleep(250);
		dsl.obterTextoXP("//div//h1[contains(text(),'Job Titles')]");
		System.out.println(dsl.obterTextoXP("//div//h1[contains(text(),'Job Titles')]"));
		Assert.assertEquals(dsl.obterTextoXP("//div//h1[contains(text(),'Job Titles')]"),lblJobPage);
	
		Thread.sleep(250);
		dsl.clicarBotao("//div//input[@id='btnAdd']");
		Assert.assertEquals(dsl.obterTextoXP("//div//h1[contains(text(),'Add Job Title')]"),lblAddJob);
		
		try{
		dsl.escreve("//fieldset//input[@id='jobTitle_jobTitle']", jobCRUD);
		dsl.escreve("//fieldset//textarea[@id='jobTitle_jobDescription']", 
				  "It is a long established fact that a reader will be distracted by "
				+ "the readable content of a page when looking at its layout. The point "
				+ "of using Lorem Ipsum is that it has a more-or-less normal distribution "
				+ "of letters, as opposed to using 'Content here, content here', making "
				+ "it look like readable English. Many desktop publishing packages and.");
		
		dsl.escreve("//fieldset//textarea[@id='jobTitle_note']",
				  "It is a long established fact that a reader will be distracted by "
				+ "the readable content of a page when looking at its layout. The point "
				+ "of using Lorem Ipsum is that it has a more-or-less normal distribution "
				+ "of letters, as opposed to using 'Content here, content here', making "
				+ "it look like readable English. Many desktop publishing packages and.");
		
		dsl.clicarBotao("//div//input[@id='btnSave']");
		Thread.sleep(500);
		
		List<WebElement> listOfElements = driver.findElements(By.xpath("//div/table//td/a"));
        
		for (int i=0; i<listOfElements.size();i++){
		      	if (listOfElements.get(i).getText().equalsIgnoreCase(jobCRUD)){
		      		System.out.println("Incluido o novo cargo " + jobCRUD);
		      	}
		}
        }catch (Exception e){
        	System.out.println("Aconteceu algo durante a execucao! " + e.getStackTrace());
        }
		logoffSiteOrange();
 	}
	
	@Test
	public void duplicateJobTitles() throws InterruptedException{

		logonSiteOrange();
		System.out.println("\n.... Tenta inserir Duplicate Job ....\n");
		
		dsl.clicarBotao("//a/b[contains(text(),'Admin')]");
		Thread.sleep(100);
		
		dsl.clicarBotao("//li//a[contains(text(),'Job') and @id='menu_admin_Job']");
		Thread.sleep(100);
		
		dsl.clicarBotao("//li//a[contains(text(),'Job Titles')]");
		Thread.sleep(250);
		
		System.out.println(dsl.obterTextoXP("//div//h1[contains(text(),'Job Titles')]"));
		Assert.assertEquals(dsl.obterTextoXP("//div//h1[contains(text(),'Job Titles')]"),lblJobPage);
	
		Thread.sleep(250);
		dsl.clicarBotao("//div//input[@id='btnAdd']");
		Assert.assertEquals(dsl.obterTextoXP("//div//h1[contains(text(),'Add Job Title')]"),lblAddJob);
		
		dsl.escreve("//fieldset//input[@id='jobTitle_jobTitle']", jobCRUD);
		
		Thread.sleep(200);
		
		System.out.println(dsl.obterTextoXP("//li//span[contains(text(),'Already exists')]") + " - job " + jobCRUD);
		Assert.assertEquals(dsl.obterTextoXP("//li//span[contains(text(),'Already exists')]"),lblAlreadyExists);
		
		logoffSiteOrange();
 	}
	
	@AfterClass
	public static void deleteJobTitles() throws InterruptedException{

		logonSiteOrange();
		System.out.println("\n.... Delete Job ....\n");
		
		dsl.clicarBotao("//a/b[contains(text(),'Admin')]");
		Thread.sleep(100);
		
		dsl.clicarBotao("//li//a[contains(text(),'Job') and @id='menu_admin_Job']");
		Thread.sleep(100);
		
		dsl.clicarBotao("//li//a[contains(text(),'Job Titles')]");
		Thread.sleep(100);
		
		System.out.println(dsl.obterTextoXP("//div//h1[contains(text(),'Job Titles')]"));
		Assert.assertEquals(dsl.obterTextoXP("//div//h1[contains(text(),'Job Titles')]"),lblJobPage);
		
		try{
		System.out.println("Removendo o cargo: " + dsl.obterTextoXP("//tbody//td[2]/a[contains(text(),'"+ jobCRUD +"')]"));
		Thread.sleep(100);
		
		dsl.clicarBotao("//tbody//td[2]/a[contains(text(),'"+ jobCRUD +"')]/../..//input[@type]");
		Thread.sleep(250);
		
		dsl.clicarBotao("//input[@id='btnDelete']");
		driver.switchTo().activeElement();
		dsl.obterTextoXP("//div[@id='deleteConfModal']//p");
		System.out.println(dsl.obterTextoXP("//div[@id='deleteConfModal']//p"));
		Assert.assertEquals(dsl.obterTextoXP("//div[@id='deleteConfModal']//p"),lblDelete);
		
		Thread.sleep(250);
		dsl.clicarBotao("//div[@id='deleteConfModal']//input[@id='dialogDeleteBtn']");
		
		Thread.sleep(250);
		List<WebElement> listOfElements = driver.findElements(By.xpath("//div/table//td/a"));
		System.out.println("Number of elements: " +listOfElements.size());
        
		for (int i=0; i<listOfElements.size();i++){
		      System.out.println("Elementos da tabela: " + listOfElements.get(i).getText());
		      	if (listOfElements.get(i).getText().equalsIgnoreCase(jobCRUD)){
		      		System.out.println("Não apagou o cargo " + jobCRUD);
		      		Assert.fail();
		      	}
		}
		System.out.println("\n.... Removido o cargo " + jobCRUD + " ....\n");
        }catch (Exception e){
        	System.out.println("Aconteceu algo durante a execucao da tabela! " + e.getStackTrace());
        }
		logoffSiteOrange();
 	}
}