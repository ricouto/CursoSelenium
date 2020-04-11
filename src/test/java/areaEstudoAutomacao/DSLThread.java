package areaEstudoAutomacao;

import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import static areaEstudoAutomacao.ConexaoDriveThread.getDriver;

public class DSLThread {
	
	public void escreve(By by, String texto){
		getDriver().findElement(by).clear();
		getDriver().findElement(by).sendKeys(texto);
	}
	
	public void escreve(String id_campo, String texto){
		escreve(By.xpath(id_campo), texto);
	}
	
	public void limparCampo(String id_campo){
		getDriver().findElement(By.xpath(id_campo)).clear();
	}
	
	public String obterValorCampo(By by){
		return getDriver().findElement(by).getAttribute("value");
	}
	
	public String obterValorCampo(String id_campo){
		return obterValorCampo(By.xpath(id_campo));
	}
	
	public void clicarBotao(By by){
		getDriver().findElement(by).click();
	}
	
	public void clicarBotao(String id){
		clicarBotao(By.xpath(id));
	}
	
	public void clicarRadio(String id){
		getDriver().findElement(By.xpath(id)).click();
	}
	
	public boolean isRadioMarcado(String id){
		return getDriver().findElement(By.xpath(id)).isSelected();
	}
	
	public void selecionarCombo(String id, String valor){
		WebElement comboEscola = getDriver().findElement(By.xpath(id));
		Select valorComboEscola = new Select(comboEscola);
		//valorComboEscola.selectByIndex(2);
		//valorComboEscola.selectByValue("superior");
		valorComboEscola.selectByVisibleText(valor);
	}
	
/*	public void selecionarCombo(String valor){
		selecionarCombo(valor);
		valorComboEscola.selectByValue("superior");
	}*/
	
	public void deselecionarCombo(String id, String valor){
		WebElement comboEscola = getDriver().findElement(By.xpath(id));
		Select valorComboEscola = new Select(comboEscola);
		//valorComboEscola.selectByIndex(2);
		//valorComboEscola.selectByValue("superior");
		valorComboEscola.deselectByVisibleText(valor);
	}
	
	public String obterValorCombo(String id){
		WebElement comboEscola = getDriver().findElement(By.xpath(id));
		Select valorComboEscola = new Select(comboEscola);
		return valorComboEscola.getFirstSelectedOption().getText();
	}
	
	public List<WebElement> obterValoresCombo(String id){
		WebElement comboEscola = getDriver().findElement(By.xpath(id));
		Select valorComboEscola = new Select(comboEscola);
		return valorComboEscola.getAllSelectedOptions();
	}
	
	public int obterQtdValoresTabela(String xp){
		
		List<WebElement> listOfElements = getDriver().findElements(By.xpath(xp));
		//System.out.println("Number of elements: " +listOfElements.size());
		return listOfElements.size();
	}
	
	public void obterValorComboPrime(String radical, String valor){
		clicarBotao("//label[@id='"+radical+"_label']/..//span");
		clicarBotao("//ul[@id='"+radical+"_items']/li[.='"+valor+"']");
	}
	
	public void clicarLink(String link){
		getDriver().findElement(By.linkText(link)).click();
	}
	
	public String obterTexto(By by){
		return getDriver().findElement(by).getText().trim();
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
		Alert txtAlerta = getDriver().switchTo().alert();
		//String textoAlerta = txtAlerta.getText();
		return txtAlerta;
	}
	
	public void windowAlertAccept(){
		getDriver().switchTo().alert().accept();
	}
	
	public Object executarJS(String cmd, Object... param){
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		return js.executeScript(cmd, param);
	}
	
	public void executarJSZoom(){
		String zoomLevelReduced="75%";
		JavascriptExecutor js = (JavascriptExecutor) getDriver();
		js.executeScript("document.body.style.zoom='" + zoomLevelReduced +"'");
	}
	
	/* site Sr Barriga DSL */
	

	
	public void clicarMenuList(String radical){
		clicarBotao("//li/a[.='"+radical+"']");
	}
	
	public void clicarMenuList(String radical, String valor){
		clicarMenuList(radical);
		clicarBotao("//li/a[.='"+radical+"']/..//a[.='"+valor+"']");
	}
	
	public List<String> obterErros(String xp){
		List<WebElement> erros = getDriver().findElements(By.xpath(xp));
		List<String> retorno = new ArrayList<String>();
		for (WebElement erro : erros){
			retorno.add(erro.getText());
		}
		return retorno;
	}	
}
