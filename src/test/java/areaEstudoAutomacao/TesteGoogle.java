package areaEstudoAutomacao;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TesteGoogle extends ConexaoDrive{
	
	private static DSL dsl = new DSL();

	@Test
	public void testeGoogle() throws InterruptedException {
		
		WebDriverWait wait = new WebDriverWait(driver, 30);
		
		driver.get("http://www.google.com");

		Assert.assertEquals("Google", driver.getTitle());
		dsl.escreve("//input[@class='gLFyf gsfi']", "informatica");
		dsl.clicarBotao("//div[@class='tfB0Bf']//input[@class='gNO89b'] "); //	.sendKeys(Keys.ENTER);
		
		wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='result-stats']")));
		
		String returnText = dsl.obterTexto(By.xpath("//div[@id='result-stats']")).substring(0, 15);

		System.out.println("\nApresentou o resultado na tela >> " + returnText + "\n");

		if (returnText.equalsIgnoreCase("Aproximadamente")) {
			Assert.assertTrue(true);
			System.out.println(dsl.obterTexto(By.xpath("//div[@id='result-stats']")));
		} else {
			System.out.println("Favor verificar o texto inserido no Google");
			Assert.fail();
		}
	}
}
