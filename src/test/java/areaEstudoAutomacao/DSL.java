package areaEstudoAutomacao;

import java.util.List;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class DSL extends ConexaoDrive{
	
	public void escreve(By by, String texto){
		driver.findElement(by).sendKeys(texto);
	}
	
	public void escreve(String id_campo, String texto){
		escreve(By.xpath(id_campo), texto);
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
	
	public void clicarLink(String link){
		driver.findElement(By.linkText(link)).click();
	}
	
	public String obterTexto(By by){
		return driver.findElement(by).getText();
	}
		
	public String obterTexto(String id){
		return obterTexto(By.id(id));
	}
	
	public Alert windowAlert(){
		Alert txtAlerta = driver.switchTo().alert();
		//String textoAlerta = txtAlerta.getText();
		return txtAlerta;
	}
	
	public void windowAlertAccept(){
		driver.switchTo().alert().accept();
	}
	


}
