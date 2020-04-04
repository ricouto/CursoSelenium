package areaEstudoAutomacao;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.junit.Assert;

public class SiteAleatorioCopy extends ConexaoDrive {
	
	
	public static String loginUser = "Admin";
	public static String passUser = "admin123";
	public static String searchUserSite = "russel.hamilton";
	public static String jobCRUD = "Quality Assurance";
	public static String lblNameSite = "OrangeHRM";
	public static String lblWelcomeSite = "Welcome Admin";
	public static String lblUserEmpty = "Username cannot be empty";
	public static String lblPassEmpty = "Password cannot be empty";
	public static String lblJobPage;
	public static String lblAddJob = "Add Job Title";
	public static String lblAlreadyExists = "Already exists";
	public static String lblDelete = "Delete records?";
	ArrayList<String> linksJob = new ArrayList<String>();
	final String caseJob = "Job Titles";
	final String casePay = "Pay Grades";
	final String caseEmployment = "Employment Status";
	final String caseCategories = "Job Categories";
	final String caseWork = "Work Shifts";
	static int tcNewPay = 0;
	
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
		Thread.sleep(500);
		driver.findElement(By.xpath("//input[@id='txtUsername']")).sendKeys(loginUser);
		driver.findElement(By.xpath("//input[@id='txtPassword']")).sendKeys(passUser);
		Thread.sleep(250);

		driver.findElement(By.name("Submit")).click();
		Thread.sleep(500);
		System.out.println("\nAcessado o site com o perfil: " + driver.findElement(By.xpath("//a[@id='welcome']")).getText());
	}
	
	public static void logoffSiteOrange() throws InterruptedException{
		Thread.sleep(500);
		driver.findElement(By.xpath("//a[@id='welcome']")).click();
		Thread.sleep(250);
		driver.findElement(By.xpath("//a[@href='/index.php/auth/logout']")).click();
		
		Thread.sleep(500);
		System.out.println("\nSaindo do website " + driver.getTitle());
		Assert.assertEquals(driver.getTitle().trim(),lblNameSite);
	}
	
	public static void btnAddNewThingSite() throws InterruptedException{
		Thread.sleep(500);
		driver.findElement(By.xpath("//div//input[@id='btnAdd']")).click();
	}
	
	public static void btnSaveNewThingSite() throws InterruptedException{
		Thread.sleep(500);
		if (tcNewPay > 1){
		driver.findElement(By.xpath("//div//input[@id='btnSave']")).click();
		Thread.sleep(150);
		System.out.println(driver.findElement(By.xpath("//div[contains(text(),'Successfully Saved')]")).getText().substring(0, 19).trim());
		
		String msgSuccess = driver.findElement(By.xpath("//div[contains(text(),'Successfully Saved')]")).getText().substring(0, 19).trim();
		Assert.assertEquals(msgSuccess,"Successfully Saved");
		
		}
		Thread.sleep(1000);
		
		try{
		List<WebElement> listOfJobsSite = driver.findElements(By.xpath("//div/table//td/a"));
		//System.out.println("Number of elements: " +listOfJobsSite.size());
        
		for (int y=0; y<listOfJobsSite.size();y++){
		      	if (listOfJobsSite.get(y).getText().equalsIgnoreCase(jobCRUD)){
		      		System.out.println("   Incluido na grid .... " + jobCRUD);
		      	}
		}
        }catch (Exception e){
        	System.out.println("Aconteceu algo durante a execucao da tabela! " + e.getStackTrace());
        }
	}
	
	public static void btnCancelThingSite() throws InterruptedException{
		driver.findElement(By.xpath("//div//input[@id='btnCancel']")).click();
		Thread.sleep(500);
	}
	
	
	
		
	@Test
	public void insertNewJobTitles() throws InterruptedException{

		logonSiteOrange();
		Thread.sleep(250);
		System.out.println("\n.... Access Menu Job ....\n");
		
		driver.findElement(By.xpath("//a/b[contains(text(),'Admin')]")).click();
		Thread.sleep(500);
		
		driver.findElement(By.xpath("//li//a[contains(text(),'Job') and @id='menu_admin_Job']")).click();
		Thread.sleep(500);
		
		try{//captura todos os links do menu Job  //li[@class='selected']/ul/li
			//li//a[contains(text(),'Job') and @id='menu_admin_Job']/..//ul/li
			
		List<WebElement> listOfElements = driver.findElements(By.xpath("//li//a[contains(text(),'Job') and @id='menu_admin_Job']/..//ul/li"));
		System.out.println("Number of elements: " +listOfElements.size());
			
		for (WebElement element: listOfElements){
			System.out.println("Elementos da tabela: " + element.getText());
			linksJob.add(element.getText());
		}
		
		for (int i = 0; i < linksJob.size(); i++) {
			String linkJob = linksJob.get(i);
			System.out.println("\n.... Acessando o link " + linksJob.get(i) + " ....\n");
      		
	      	switch (linkJob) {
	      	  case caseJob:
	      		driver.findElement(By.xpath("//li//a[contains(text(),'" + linksJob.get(i) + "')]")).click();
	      		insereNewJobTitles();
	      	    break;
	      	  case casePay:
	      		driver.findElement(By.xpath("//li//a[contains(text(),'" + linksJob.get(i) + "')]")).click();
	      		insereNewPayGrades();
	      	    break;
	      	  case "Employment Status":
	      		driver.findElement(By.xpath("//li//a[contains(text(),'" + linksJob.get(i) + "')]")).click();
	      		insereNewEmploymentStatus();
	      	    break;
	      	case "Job Categories":
	      		driver.findElement(By.xpath("//li//a[contains(text(),'" + linksJob.get(i) + "')]")).click();
	      		insereNewJobCategories();
	      	    break;
	      	case "Work Shifts":
	      		driver.findElement(By.xpath("//li//a[contains(text(),'" + linksJob.get(i) + "')]")).click();
	      		insereNewWorkShifts();
	      	    break;
	      	default:
	      	    System.out.println("Nao existe opcao para a ser selecinada!");
	      		}
      		driver.findElement(By.xpath("//li//a[contains(text(),'Job') and @id='menu_admin_Job']")).click();
		    }

        }catch (Exception e){
        	System.out.println("Aconteceu algo durante a execucao da tabela! " + e.getStackTrace());
        }
		
		logoffSiteOrange();
}
		

		public void insereNewJobTitles() throws InterruptedException{
		tcNewPay = 2;
	
		Thread.sleep(500);
		driver.findElement(By.xpath("//div//input[@id='btnAdd']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div//h1[contains(text(),'Add Job Title')]")).getText(),lblAddJob);
		
		driver.findElement(By.xpath("//fieldset//input[@id='jobTitle_jobTitle']")).sendKeys(jobCRUD);
		
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
		
		Thread.sleep(150);
		btnSaveNewThingSite();
 	}
		
	public void insereNewPayGrades () throws InterruptedException{
		tcNewPay = 1;
		Thread.sleep(500);
		btnAddNewThingSite();
		driver.findElement(By.xpath("//input[@id='payGrade_name']")).sendKeys(jobCRUD);
		
		Thread.sleep(150);
		driver.findElement(By.xpath("//div//input[@id='btnSave']")).click();
		System.out.println(driver.findElement(By.xpath("//div[contains(text(),'Successfully Saved')]")).getText().substring(0, 19).trim());
		String msgSuccess = driver.findElement(By.xpath("//div[contains(text(),'Successfully Saved')]")).getText().substring(0, 19).trim();
		Assert.assertEquals(msgSuccess,"Successfully Saved");
		
		Thread.sleep(150);
		btnCancelThingSite();
		Thread.sleep(150);
		btnSaveNewThingSite();
	}
	
	public void insereNewEmploymentStatus() throws InterruptedException{
		tcNewPay = 2;
		Thread.sleep(500);
		btnAddNewThingSite();
		driver.findElement(By.xpath("//input[@id='empStatus_name']")).sendKeys(jobCRUD);
		btnSaveNewThingSite();
	}
	
	public void insereNewJobCategories() throws InterruptedException{
		tcNewPay = 2;
		Thread.sleep(500);
		btnAddNewThingSite();
		driver.findElement(By.xpath("//input[@id='jobCategory_name']")).sendKeys(jobCRUD);
		btnSaveNewThingSite();
	} 
	
	public void insereNewWorkShifts() throws InterruptedException{
		tcNewPay = 2;
		Thread.sleep(500);
		btnAddNewThingSite();
		driver.findElement(By.xpath("//input[@id='workShift_name']")).sendKeys(jobCRUD);
		btnSaveNewThingSite();
	} 
		
	@Ignore
	@Test
	public void duplicateJobTitles() throws InterruptedException{

		logonSiteOrange();
		Thread.sleep(250);
		System.out.println("\n.... Tenta inserir Duplicate Job ....\n");
		
		driver.findElement(By.xpath("//a/b[contains(text(),'Admin')]")).click();
		Thread.sleep(250);
		
		driver.findElement(By.xpath("//li//a[contains(text(),'Job') and @id='menu_admin_Job']")).click();
		Thread.sleep(250);
		
		driver.findElement(By.xpath("//li//a[contains(text(),'Job Titles')]")).click();
		Thread.sleep(500);
		
		System.out.println(driver.findElement(By.xpath("//div//h1[contains(text(),'Job Titles')]")).getText());
		Assert.assertEquals(driver.findElement(By.xpath("//div//h1[contains(text(),'Job Titles')]")).getText(),lblJobPage);
	
		Thread.sleep(500);
		driver.findElement(By.xpath("//div//input[@id='btnAdd']")).click();
		Assert.assertEquals(driver.findElement(By.xpath("//div//h1[contains(text(),'Add Job Title')]")).getText(),lblAddJob);
		
		driver.findElement(By.xpath("//fieldset//input[@id='jobTitle_jobTitle']")).sendKeys(jobCRUD);
		
		Thread.sleep(200);
		System.out.println(driver.findElement(By.xpath("//li//span[contains(text(),'Already exists')]")).getText() + " - job " + jobCRUD);
		Assert.assertEquals(driver.findElement(By.xpath("//li//span[contains(text(),'Already exists')]")).getText(),lblAlreadyExists);
		
		logoffSiteOrange();
 	}
	
	
	@Test
	public void removeCategoriesTitles() throws InterruptedException{

		logonSiteOrange();
		Thread.sleep(250);
		System.out.println("\n.... Delete Categories Job ....\n");
		
		driver.findElement(By.xpath("//a/b[contains(text(),'Admin')]")).click();
		Thread.sleep(500);
		
		driver.findElement(By.xpath("//li//a[contains(text(),'Job') and @id='menu_admin_Job']")).click();
		Thread.sleep(500);
		
		try{
		List<WebElement> listOfElements = driver.findElements(By.xpath("//li//a[contains(text(),'Job') and @id='menu_admin_Job']/..//ul/li"));
		//System.out.println("Number of elements: " +listOfElements.size());
			
		for (WebElement element: listOfElements){
			System.out.println("Elementos da tabela: " + element.getText());
			linksJob.add(element.getText());
		}
		
		for (int i = 0; i < linksJob.size(); i++) {
			String linkJob = linksJob.get(i);
			System.out.println("\n.... Acessando o link " + linksJob.get(i) + " ....\n");
		
			switch (linkJob) {
	      	  case caseJob:
	      		driver.findElement(By.xpath("//li//a[contains(text(),'" + linksJob.get(i) + "')]")).click();
	      		removeItemGrid();
	      	    break;
	      	  case casePay:
	      		driver.findElement(By.xpath("//li//a[contains(text(),'" + linksJob.get(i) + "')]")).click();
	      		removeItemGrid();
	      	    break;
	      	  case "Employment Status":
	      		driver.findElement(By.xpath("//li//a[contains(text(),'" + linksJob.get(i) + "')]")).click();
	      		removeItemGrid();
	      	    break;
	      	case "Job Categories":
	      		driver.findElement(By.xpath("//li//a[contains(text(),'" + linksJob.get(i) + "')]")).click();
	      		removeItemGrid();
	      	    break;
	      	case "Work Shifts":
	      		driver.findElement(By.xpath("//li//a[contains(text(),'" + linksJob.get(i) + "')]")).click();
	      		removeItemGrid();
	      	    break;
	      	default:
	      	    System.out.println("Nao existe opcao para a ser selecinada!");
	      		}
    		driver.findElement(By.xpath("//li//a[contains(text(),'Job') and @id='menu_admin_Job']")).click();
		    }
	    }catch (Exception e){
        	System.out.println("Aconteceu algo durante a execucao da tabela! " + e.getStackTrace());
        }
		
		logoffSiteOrange();
	}
	
	
	public void removeItemGrid() throws InterruptedException{
		
		Thread.sleep(500);
		System.out.println("Removendo o cargo: " + driver.findElement(By.xpath("//tbody//td/a[contains(text(),'"+ jobCRUD +"')]")).getText());
		Thread.sleep(500);
		driver.findElement(By.xpath("//tbody//td/a[contains(text(),'"+ jobCRUD +"')]/../..//input[@type]")).click();
		
		Thread.sleep(500);
		driver.findElement(By.xpath("//input[@id='btnDelete']")).click();
		driver.switchTo().activeElement();
		System.out.println(driver.findElement(By.xpath("//div[@id='deleteConfModal']//p")).getText());
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='deleteConfModal']//p")).getText().trim(),lblDelete);
		
		Thread.sleep(500);
		driver.findElement(By.xpath("//div[@id='deleteConfModal']//input[@id='dialogDeleteBtn']")).click();
		
		System.out.println(driver.findElement(By.xpath("//div[contains(text(),'Successfully Deleted')]")).getText().substring(0, 20).trim());
		String msgSuccess = driver.findElement(By.xpath("//div[contains(text(),'Successfully Deleted')]")).getText().substring(0, 20).trim();
		Assert.assertEquals(msgSuccess,"Successfully Deleted");
		
		Thread.sleep(500);
		try{
		List<WebElement> listOfElements = driver.findElements(By.xpath("//div/table//td/a"));
		//System.out.println("Number of elements: " +listOfElements.size());
        
		for (int i=0; i<listOfElements.size();i++){
		      //System.out.println("Elementos da tabela: " + listOfElements.get(i).getText());
		      	if (listOfElements.get(i).getText().equalsIgnoreCase(jobCRUD)){
		      		System.out.println("Não apagou o cargo " + jobCRUD);
		      		Assert.fail();
		      	}
		}
		System.out.println("\n.... Removido o cargo " + jobCRUD + " ....\n");
        }catch (Exception e){
        	System.out.println("Aconteceu algo durante a execucao da tabela! " + e.getStackTrace());
        }
	}
	
	
	
	
	
	
	
	
	@Ignore
	@Test
	public void deleteJobTitles() throws InterruptedException{

		logonSiteOrange();
		Thread.sleep(250);
		System.out.println("\n.... Delete Job ....\n");
		
		driver.findElement(By.xpath("//a/b[contains(text(),'Admin')]")).click();
		Thread.sleep(250);
		
		driver.findElement(By.xpath("//li//a[contains(text(),'Job') and @id='menu_admin_Job']")).click();
		Thread.sleep(250);
		
		driver.findElement(By.xpath("//li//a[contains(text(),'Job Titles')]")).click();
		Thread.sleep(250);
		
		System.out.println(driver.findElement(By.xpath("//div//h1[contains(text(),'Job Titles')]")).getText());
		//Assert.assertEquals(driver.findElement(By.xpath("//div//h1[contains(text(),'Job Titles')]")).getText(),lblJobPage);
	
		Thread.sleep(500);
		System.out.println("Removendo o cargo: " + driver.findElement(By.xpath("//tbody//td/a[contains(text(),'"+ jobCRUD +"')]")).getText());
		Thread.sleep(500);
		driver.findElement(By.xpath("//tbody//td/a[contains(text(),'"+ jobCRUD +"')]/../..//input[@type]")).click();
		
		Thread.sleep(500);
		driver.findElement(By.xpath("//input[@id='btnDelete']")).click();
		driver.switchTo().activeElement();
		System.out.println(driver.findElement(By.xpath("//div[@id='deleteConfModal']//p")).getText());
		Assert.assertEquals(driver.findElement(By.xpath("//div[@id='deleteConfModal']//p")).getText().trim(),lblDelete);
		
		Thread.sleep(500);
		driver.findElement(By.xpath("//div[@id='deleteConfModal']//input[@id='dialogDeleteBtn']")).click();
		
		Thread.sleep(500);
		try{
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
		Thread.sleep(200);
		logoffSiteOrange();
 	}
}