package areaEstudoAutomacao;

import java.util.List;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.junit.Assert;

public class SiteEstudo extends ConexaoDrive {
	
	private static DSL dsl;
	
	@BeforeClass
	public static void openSite(){
		
		driver.get("https://ricouto.github.io/");
		dsl = new DSL();
	}

	@Test
	public void treinaCampoTexto() throws InterruptedException{

		Thread.sleep(500);
		System.out.println(driver.getTitle());
		
		dsl.escreve("//input[@id='elementosForm:nome']", "João da Silva Jr");
		Assert.assertEquals("João da Silva Jr", dsl.obterValorCampo("//input[@id='elementosForm:nome']"));
	}
	
	@Test
	public void treinaCampoTextArea() throws InterruptedException{
		dsl.escreve("//textarea[@id='elementosForm:sugestoes']", "passando o texto....");
		Assert.assertEquals("passando o texto....", dsl.obterValorCampo("//textarea[@id='elementosForm:sugestoes']")); 
	}
	
	@Test
	public void treinaCampoRadio() throws InterruptedException{
		dsl.clicarRadio("//input[@id='elementosForm:sexo:0']");
		Assert.assertTrue(dsl.isRadioMarcado("//input[@id='elementosForm:sexo:0']"));
	}
	
	@Test
	public void treinaCampoCheck() throws InterruptedException{
		dsl.clicarRadio("//input[@id='elementosForm:comidaFavorita:2']");
		Assert.assertTrue(dsl.isRadioMarcado("//input[@id='elementosForm:comidaFavorita:2']"));
	}
	
	@Test
	public void treinaCampoCombo() throws InterruptedException{
		dsl.selecionarCombo("//select[@id='elementosForm:escolaridade']", "Especializacao");
		Assert.assertEquals("Especializacao", dsl.obterValorCombo("//select[@id='elementosForm:escolaridade']"));
	}
	
	@Test
	public void treinaCampoValoresCombo() throws InterruptedException{
		WebElement comboEscola = driver.findElement(By.xpath("//select[@id='elementosForm:escolaridade']"));
		Select valorComboEscola = new Select(comboEscola);
		
		List<WebElement> options =  valorComboEscola.getOptions();
		Assert.assertEquals(8, options.size());
		
		boolean encontrou = false;
		for (WebElement option: options){
			if(option.getText().equals("Mestrado")){
				encontrou = true;
				break;
			}
		}
		Assert.assertTrue(encontrou);
	}
	
	@Test
	public void treinaCampoValoresComboMulti() throws InterruptedException{
		dsl.selecionarCombo("//select[@id='elementosForm:esportes']", "Natacao");
		dsl.selecionarCombo("//select[@id='elementosForm:esportes']", "Corrida");
		dsl.selecionarCombo("//select[@id='elementosForm:esportes']", "O que eh esporte?");
		
		Assert.assertEquals(3, dsl.obterValoresCombo("//select[@id='elementosForm:esportes']").size());
	}
	
	@Test
	public void treinaCampoBotoes() throws InterruptedException{
		dsl.clicarBotao("//input[@id='buttonSimple']");
		Assert.assertEquals("Obrigado!", dsl.obterValorCampo("//input[@id='buttonSimple']")); 
	}
	
	@Test
	public void treinaCampoLinks() throws InterruptedException{
		dsl.clicarLink("Voltar");
		Assert.assertEquals("Voltou!", dsl.obterTexto("resultado"));
	}
	
	@Test
	public void treinaCampoTextos() throws InterruptedException{
		//Assert.assertTrue(driver.findElement(By.tagName("body")).getText().contains("Campo de Treinamento"));
		
		Assert.assertEquals("Campo de Treinamento", dsl.obterTexto(By.tagName("h3")));
		
		Assert.assertEquals("Cuidado onde clica, muitas armadilhas...", dsl.obterTexto(By.className("facilAchar")));
	}
	
	@Test
	public void treinaCampoAlertSimples() throws InterruptedException{
		
		dsl.clicarBotao(By.id("alert"));
		
		dsl.windowAlert();
		
		String textoAlerta = dsl.windowAlert().getText();
		
		Assert.assertEquals("Alert Simples", textoAlerta);
		
		dsl.windowAlertAccept();
		
		dsl.escreve(By.id("elementosForm:nome"), textoAlerta);
		
	}
	
	@Test
	public void treinaCampoAlertConfirma() throws InterruptedException{

		//Confirma
		driver.findElement(By.id("confirm")).click();
		Alert txtAlerta = driver.switchTo().alert();
		Assert.assertEquals("Confirm Simples",txtAlerta.getText());
		txtAlerta.accept();
		Assert.assertEquals("Confirmado",txtAlerta.getText());
		txtAlerta.accept();
		
		//Negado
		driver.findElement(By.id("confirm")).click();
		txtAlerta = driver.switchTo().alert();
		Assert.assertEquals("Confirm Simples",txtAlerta.getText());
		txtAlerta.dismiss();
		Assert.assertEquals("Negado",txtAlerta.getText());
		txtAlerta.accept();
	}
	
	
	@Test
	public void treinaCampoPrompt() throws InterruptedException{
		driver.findElement(By.id("prompt")).click();
		Alert txtAlerta = driver.switchTo().alert();
		Assert.assertEquals("Digite um numero",txtAlerta.getText());
		
		txtAlerta.sendKeys("1");
		txtAlerta.accept();
		
		Assert.assertEquals("Era 1?",txtAlerta.getText());
		txtAlerta.accept();
		
		Assert.assertEquals(":D",txtAlerta.getText());
		txtAlerta.accept();
	}
	
	@Test
	public void treinaCampoDesafioCadastroAula35() throws InterruptedException{
	
	dsl.escreve("//input[@id='elementosForm:nome']", "Maitê");
	dsl.escreve("//input[@id='elementosForm:sobrenome']", "Souza Morais");	
	dsl.clicarRadio("//input[@id='elementosForm:sexo:1']");
	dsl.clicarRadio("//input[@id='elementosForm:comidaFavorita:3']");
	
	dsl.selecionarCombo("//select[@id='elementosForm:escolaridade']", "Especializacao");
	
	dsl.selecionarCombo("//select[@id='elementosForm:esportes']", "Natacao");
	dsl.selecionarCombo("//select[@id='elementosForm:esportes']", "Karate");
	
	dsl.clicarBotao("//input[@id='elementosForm:cadastrar']");
	
	Assert.assertEquals(dsl.obterTexto(By.xpath("//div[@id='descNome']/span")), dsl.obterValorCampo("//input[@id='elementosForm:nome']"));
	Assert.assertEquals(dsl.obterTexto(By.xpath("//div[@id='descSobrenome']/span")), dsl.obterValorCampo("//input[@id='elementosForm:sobrenome']"));
	Assert.assertEquals(dsl.obterTexto(By.xpath("//input[@id='elementosForm:sexo:1']/../label")), 
			dsl.obterTexto(By.xpath("//div[@id='descSexo']/span")));
	
	Assert.assertEquals(dsl.obterValorCampo("//input[@id='elementosForm:comidaFavorita:3']").toLowerCase(), 
			dsl.obterTexto(By.xpath("//div[@id='descComida']/span")).toLowerCase());
	
	Assert.assertEquals(dsl.obterTexto(By.xpath("//div[@id='descEscolaridade']/span")).toLowerCase(),
			dsl.obterValorCombo("//select[@id='elementosForm:escolaridade']").toLowerCase());
	
	Assert.assertEquals(2, dsl.obterValoresCombo("//select[@id='elementosForm:esportes']").size());
	
	}
	
	@Test
	public void treinaCampoFrame() throws InterruptedException{
	
	driver.switchTo().frame("frame1");
	driver.findElement(By.xpath("//input[@id='frameButton']")).click();
	Alert alertPopUp = driver.switchTo().alert();
	String msgAlert = alertPopUp.getText();
	Assert.assertEquals("Frame OK!", msgAlert);
	alertPopUp.accept();
	
	driver.switchTo().defaultContent();
	driver.findElement(By.xpath("//input[@id='elementosForm:nome']")).sendKeys(msgAlert);
	
	}
	
	@Test
	public void treinaCampoJanela() throws InterruptedException{
	
	driver.findElement(By.xpath("//input[@id='buttonPopUpEasy']")).click();
	driver.switchTo().window("Popup");
	driver.findElement(By.tagName("textarea")).sendKeys("Deu Certo!");
	driver.close();
	
	driver.switchTo().window("");
	driver.findElement(By.tagName("textarea")).sendKeys("e..... agora?");
	
	}
	
	@Test
	public void treinaCampoJanelaSemTitulo() throws InterruptedException{
	
	driver.findElement(By.xpath("//input[@id='buttonPopUpHard']")).click();
	System.out.println(driver.getWindowHandle());
	System.out.println(driver.getWindowHandles());
	
	driver.switchTo().window((String)driver.getWindowHandles().toArray()[1]);
	driver.findElement(By.tagName("textarea")).sendKeys("Escrevi na Janela do Mal....");
	
	driver.switchTo().window((String)driver.getWindowHandles().toArray()[0]);
	driver.findElement(By.tagName("textarea")).sendKeys("Novamente na janela do Bem S2....");
	
	}
	
	@Test
	public void treinaCampoDesafioRNAula39() throws InterruptedException{
		Alert txtAlerta;
		
		//valida RN campo Nome
		dsl.clicarBotao("//input[@id='elementosForm:cadastrar']");
		Assert.assertEquals("Nome eh obrigatorio",dsl.windowAlert().getText());
		dsl.windowAlertAccept();
		dsl.escreve("//input[@id='elementosForm:nome']", "Maitê");

		//valida RN campo Sobrenome
		dsl.clicarBotao("//input[@id='elementosForm:cadastrar']");
		Assert.assertEquals("Sobrenome eh obrigatorio",dsl.windowAlert().getText());
		dsl.windowAlertAccept();
		dsl.escreve("//input[@id='elementosForm:sobrenome']", "de Morais e Costa");
		
		//valida RN campo Sexo
		dsl.clicarBotao("//input[@id='elementosForm:cadastrar']");
		Assert.assertEquals("Sexo eh obrigatorio",dsl.windowAlert().getText());
		dsl.windowAlertAccept();
		dsl.clicarBotao("//input[@id='elementosForm:sexo:1']");
		Assert.assertTrue(dsl.isRadioMarcado("//input[@id='elementosForm:sexo:1']"));
		
		//valida RN campo Comida Favorita
		dsl.clicarRadio("//input[@id='elementosForm:comidaFavorita:0']");
		dsl.clicarRadio("//input[@id='elementosForm:comidaFavorita:3']");
		dsl.clicarBotao("//input[@id='elementosForm:cadastrar']");
		Assert.assertEquals("Tem certeza que voce eh vegetariano?",dsl.windowAlert().getText());
		dsl.windowAlertAccept();
		dsl.clicarRadio("//input[@id='elementosForm:comidaFavorita:0']");
		Assert.assertTrue(dsl.isRadioMarcado("//input[@id='elementosForm:comidaFavorita:3']"));
		
		//valida RN campo Esporte
		dsl.selecionarCombo("//select[@id='elementosForm:esportes']", "Natacao");
		dsl.selecionarCombo("//select[@id='elementosForm:esportes']", "O que eh esporte?");
		dsl.clicarBotao("//input[@id='elementosForm:cadastrar']");
		Assert.assertEquals("Voce faz esporte ou nao?",dsl.windowAlert().getText());
		dsl.windowAlertAccept();
		dsl.deselecionarCombo("//select[@id='elementosForm:esportes']", "O que eh esporte?");
		
	}
	
	
	
}