package areaEstudoAutomacao;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Assert;

public class SiteEstudo extends ConexaoDrive {
	
	private static DSL dsl;
	private static CampoTreinaPage page;
	
	@Rule
	public ScreenShotFailure failure = new ScreenShotFailure();
	
	@BeforeClass
	public static void openSite(){
		dsl = new DSL();
		page = new CampoTreinaPage();
		driver.get("https://ricouto.github.io/");
		System.out.println("\n**** Site de estudos Campo de Treinamento ****\n");
	}
	
	@AfterClass
	public static void exitSite(){
		System.out.println("\n**** Fim dos testes no site de estudos Campo de Treinamento ****\n");
	}
	@Test
	public void treinaCampoTexto() throws InterruptedException{
		Thread.sleep(500);
		System.out.println(driver.getTitle());
		
		page.setNome("João da Silva Jr");
		
		Assert.assertEquals("João da Silva Jr", page.obterNomeFormulario());
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
	@Ignore
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
	
	page.setNome("Maitê");
	page.setSobrenome("Souza Morais");
	page.setSexoFeminino();
	page.setComidaVegetariano();
	page.setEscolaridade("Especializacao");
	page.setEsporte("Karate", "Corrida");
	page.btnCadastrar();
	
	Assert.assertEquals(page.obterNomeCadastro(), page.obterNomeFormulario());
	Assert.assertEquals(page.obterSobrenomeCadastro(), page.obterSobrenomeFormulario());
	Assert.assertEquals(page.obterSexoCadastro(),page.obterSexoFemiFormulario());
	Assert.assertEquals(page.obterComidaCadastro().toLowerCase(),page.obterComidaVegetaFormulario().toLowerCase());
	Assert.assertEquals(page.obterEscolaridadeCadastro().toLowerCase(),page.obterEscolariFormulario().toLowerCase());
	Assert.assertEquals(2, page.obterEsporteFormulario().size());
	
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
	public void treinaCampoFrameEscondido() throws InterruptedException{
	
	WebElement frame = driver.findElement(By.id("frame2"));
	dsl.executarJS("window.scrollBy(0, arguments[0])", frame.getLocation().y);
	driver.switchTo().frame("frame2");
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
	dsl.escreve(By.tagName("textarea"), "Deu Certo!");
	//driver.findElement(By.tagName("textarea")).sendKeys("Deu Certo!");
	driver.close();
	
	driver.switchTo().window("");
	dsl.escreve(By.tagName("textarea"), "e..... agora?");
	//driver.findElement(By.tagName("textarea")).sendKeys("e..... agora?");
	
	}
	
	@Test
	public void treinaCampoJanelaSemTitulo() throws InterruptedException{
	
	driver.findElement(By.xpath("//input[@id='buttonPopUpHard']")).click();
	//System.out.println(driver.getWindowHandle());
	//System.out.println(driver.getWindowHandles());
	
	driver.switchTo().window((String)driver.getWindowHandles().toArray()[1]);
	dsl.escreve(By.tagName("textarea"), "Escrevi na Janela do Mal....");
			//By.tagName("textarea")).sendKeys("Escrevi na Janela do Mal....");
	
	driver.switchTo().window((String)driver.getWindowHandles().toArray()[0]);
	dsl.escreve(By.tagName("textarea"), "Novamente na janela do Bem S2....");
	//driver.findElement(By.tagName("textarea")).sendKeys("Novamente na janela do Bem S2....");
	
	}
	
	@Ignore
	@Test
	public void treinaCampoDesafioRNAula39() throws InterruptedException{
		
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
	
	@Test
	public void escreveApagaNome(){
		dsl.escreve("//input[@id='elementosForm:nome']", "Carlinhos");
		Assert.assertEquals("Carlinhos", dsl.obterValorCampo(By.xpath("//input[@id='elementosForm:nome']")));
		
		dsl.escreve("//input[@id='elementosForm:nome']", "Mariazinha");
		Assert.assertEquals("Mariazinha", dsl.obterValorCampo(By.xpath("//input[@id='elementosForm:nome']")));
	}
	
	@Test
	public void deveUtilizarEsperaFixa() throws InterruptedException{
		dsl.clicarBotao("//input[@id='buttonDelay']");
		Thread.sleep(5000);
		dsl.escreve("//input[@id='novoCampo']", "Escreve aqui neste campo!!!");
	}
	
	@Test
	public void deveUtilizarEsperaImplicita(){
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		dsl.clicarBotao("//input[@id='buttonDelay']");
		dsl.escreve("//input[@id='novoCampo']", "Escreve aqui neste campo!!!");
		driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
	}
	
	@Test
	public void deveUtilizarEsperaExplicita(){
		dsl.clicarBotao("//input[@id='buttonDelay']");
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.presenceOfElementLocated(By.id("novoCampo")));
		dsl.escreve("//input[@id='novoCampo']", "Escreve aqui neste campo!!!");
	}
	
}