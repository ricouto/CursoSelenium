package areaEstudoAutomacao;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL extends ConexaoDrive{
	
	public void escreve(By by, String texto){
		driver.findElement(by).clear();
		driver.findElement(by).sendKeys(texto);
	}
	
	public void escreve(String id_campo, String texto){
		escreve(By.xpath(id_campo), texto);
	}
	
	public void limparCampo(String id_campo){
		driver.findElement(By.xpath(id_campo)).clear();
	}
	
	public String obterValorCampo(By by){
		return driver.findElement(by).getAttribute("value");
	}
	
	public String obterValorCampo(String id_campo){
		return obterValorCampo(By.xpath(id_campo));
	}
	
	public void clicarBotao(By by){
		driver.findElement(by).click();
	}
	
	public void clicarBotao(String id){
		clicarBotao(By.xpath(id));
	}
	
	public void clicarRadio(String id){
		driver.findElement(By.xpath(id)).click();
	}
	
	public boolean isRadioMarcado(String id){
		return driver.findElement(By.xpath(id)).isSelected();
	}
	
	public void selecionarCombo(String id, String valor){
		WebElement comboEscola = driver.findElement(By.xpath(id));
		Select valorComboEscola = new Select(comboEscola);
		//valorComboEscola.selectByIndex(2);
		//valorComboEscola.selectByValue("superior");
		valorComboEscola.selectByVisibleText(valor);
	}
	
	public void deselecionarCombo(String id, String valor){
		WebElement comboEscola = driver.findElement(By.xpath(id));
		Select valorComboEscola = new Select(comboEscola);
		//valorComboEscola.selectByIndex(2);
		//valorComboEscola.selectByValue("superior");
		valorComboEscola.deselectByVisibleText(valor);
	}
	
	public String obterValorCombo(String id){
		WebElement comboEscola = driver.findElement(By.xpath(id));
		Select valorComboEscola = new Select(comboEscola);
		return valorComboEscola.getFirstSelectedOption().getText();
	}
	
	public List<WebElement> obterValoresCombo(String id){
		WebElement comboEscola = driver.findElement(By.xpath(id));
		Select valorComboEscola = new Select(comboEscola);
		return valorComboEscola.getAllSelectedOptions();
	}
	
	public void obterValorComboPrime(String radical, String valor){
		clicarBotao("//label[@id='"+radical+"_label']/..//span");
		clicarBotao("//ul[@id='"+radical+"_items']/li[.='"+valor+"']");
	}
	
	public void clicarLink(String link){
		driver.findElement(By.linkText(link)).click();
	}
	
	public String obterTexto(By by){
		return driver.findElement(by).getText().trim();
	}
		
	public String obterTexto(String id){
		return obterTexto(By.id(id));
	}
	
	public String obterTextoXP(String xp){
		return obterTexto(By.xpath(xp));
	}
	
	public String obterTextoPrime(String radical){
		return obterTexto(By.xpath("//label[@id='"+radical+"_label']"));
	}
	
	public Alert windowAlert(){
		Alert txtAlerta = driver.switchTo().alert();
		//String textoAlerta = txtAlerta.getText();
		return txtAlerta;
	}
	
	public void windowAlertAccept(){
		driver.switchTo().alert().accept();
	}
	
	public Object executarJS(String cmd, Object... param){
		JavascriptExecutor js = (JavascriptExecutor) driver;
		return js.executeScript(cmd, param);
	}
	
	public void executarJSZoom(){
		String zoomLevelReduced="75%";
		JavascriptExecutor js = (JavascriptExecutor) driver;
		js.executeScript("document.body.style.zoom='" + zoomLevelReduced +"'");
	}
}
