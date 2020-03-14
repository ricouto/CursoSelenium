package areaEstudoAutomacao;
import java.util.List;

import org.junit.Assert;
//import org.junit.Ignore;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

public class TesteCampoTreinamento {

	@Test
	public void testTextField() {
		WebDriver driver = new FirefoxDriver();
		driver.get("file:///" + System.getProperty("user.dir")
				+ "/src/main/resources/componentes.html");
		driver.findElement(By.id("elementosForm:nome")).sendKeys(
				"Incluindo valor no campo Text!");
		Assert.assertEquals("Incluindo valor no campo Text!", driver
				.findElement(By.id("elementosForm:nome")).getAttribute("value"));
		driver.quit();

	}

	@Test
	public void deveIntegirComTextArea() {
		WebDriver driver = new FirefoxDriver();
		driver.get("file:///" + System.getProperty("user.dir")
				+ "/src/main/resources/componentes.html");
		driver.findElement(By.id("elementosForm:sugestoes")).sendKeys(
				"Incluindo valor no campo TextArea!");
		Assert.assertEquals("Incluindo valor no campo TextArea!",
				driver.findElement(By.id("elementosForm:sugestoes"))
						.getAttribute("value"));
		driver.quit();
	}

	@Test
	public void deveInteragirComRadioButton() {
		WebDriver driver = new FirefoxDriver();
		driver.get("file:///" + System.getProperty("user.dir")
				+ "/src/main/resources/componentes.html");
		driver.findElement(By.id("elementosForm:sexo:0")).click();
		Assert.assertTrue(driver.findElement(By.id("elementosForm:sexo:0"))
				.isSelected());
		driver.quit();
	}

	@Test
	public void deveInteragirComCheckBox() {
		WebDriver driver = new FirefoxDriver();
		driver.get("file:///" + System.getProperty("user.dir")
				+ "/src/main/resources/componentes.html");
		driver.findElement(By.id("elementosForm:comidaFavorita:2")).click();
		Assert.assertTrue(driver.findElement(
				By.id("elementosForm:comidaFavorita:2")).isSelected());
		driver.quit();
	}

	@Test
	public void deveInteragirComCombo() {
		WebDriver driver = new FirefoxDriver();
		driver.get("file:///" + System.getProperty("user.dir")
				+ "/src/main/resources/componentes.html");
		WebElement element = driver.findElement(By
				.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		// combo.selectByIndex(3);
		// combo.selectByValue("mestrado");
		combo.selectByVisibleText("Superior");

		Assert.assertEquals("Superior", combo.getFirstSelectedOption()
				.getText());
		driver.quit();

	}

	@Test
	public void deveVerificarValoresCombo() {
		WebDriver driver = new FirefoxDriver();
		driver.get("file:///" + System.getProperty("user.dir")
				+ "/src/main/resources/componentes.html");
		WebElement element = driver.findElement(By
				.id("elementosForm:escolaridade"));
		Select combo = new Select(element);
		List<WebElement> options = combo.getOptions();
		Assert.assertEquals(8, options.size());

		boolean encontrou = false;
		for (WebElement option : options) {
			if (option.getText().equals("Mestrado")) {
				encontrou = true;
				break;
			}
		}
		Assert.assertTrue(encontrou);
		driver.quit();
	}

	@Test
	public void deveVerificarValoresComboMultiplo() {
		WebDriver driver = new FirefoxDriver();
		driver.get("file:///" + System.getProperty("user.dir")
				+ "/src/main/resources/componentes.html");
		WebElement element = driver
				.findElement(By.id("elementosForm:esportes"));
		Select combo = new Select(element);
		combo.selectByVisibleText("Natacao");
		combo.selectByVisibleText("Corrida");
		combo.selectByVisibleText("O que eh esporte?");

		List<WebElement> allSelectOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(3, allSelectOptions.size());

		combo.deselectByVisibleText("Corrida");
		allSelectOptions = combo.getAllSelectedOptions();
		Assert.assertEquals(2, allSelectOptions.size());
		driver.quit();
	}

	@Test
	public void deveInteragirComBotao() {
		WebDriver driver = new FirefoxDriver();
		driver.get("file:///" + System.getProperty("user.dir")
				+ "/src/main/resources/componentes.html");
		WebElement botao = driver.findElement(By.id("buttonSimple"));
		botao.click();
		Assert.assertEquals("Obrigado!", botao.getAttribute("value"));
		driver.quit();
	}
	
	@Test
	//@Ignore - ignora a execucao deste metodo
	public void deveInteragirComLinks() {
		WebDriver driver = new FirefoxDriver();
		driver.get("file:///" + System.getProperty("user.dir")
				+ "/src/main/resources/componentes.html");
		driver.findElement(By.linkText("Voltar")).click();
		
		Assert.assertEquals("Voltou!", driver.findElement(By.id("resultado")).getText());
		driver.quit();
	
//		Assert.fail(); falha o metodo deixando em vermelho
	}
	
	@Test
		public void deveInteragirComTextosNaPagina() {
		WebDriver driver = new FirefoxDriver();
		driver.get("file:///" + System.getProperty("user.dir")
				+ "/src/main/resources/componentes.html");
	
		Assert.assertEquals("Campo de Treinamento", driver.findElement(By.tagName("h3")).getText());
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", driver.findElement(By.className("facilAchar")).getText());
		driver.quit();
	}
	
}