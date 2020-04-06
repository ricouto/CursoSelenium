package areaEstudoAutomacao;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.junit.Assert;

public class SiteEstudoPrimeFaces extends ConexaoDrive {
	
	private static DSL dsl;
	
	@Rule
	public ScreenShotFailure failure = new ScreenShotFailure();
	
	@BeforeClass
	public static void openSite(){
		dsl = new DSL();
		System.out.println("\n**** PrimeFaces ****\n");
	}
	
	@AfterClass
	public static void exitSite(){
		System.out.println("\n**** Fim testes com PrimeFaces ****\n");
	}
	
	@Test
	public void deveIntegarirComRadioPrime() {
		
		driver.get("https://www.primefaces.org/showcase/ui/input/oneRadio.xhtml");
		dsl.clicarRadio("//label[@for='j_idt721:console:0']/..//span");
		dsl.isRadioMarcado("//label[@for='j_idt721:console:0']/..//span");
	}
	
	@Test
	public void deveIntegarirComComboPrime(){
		driver.get("https://www.primefaces.org/showcase/ui/input/oneMenu.xhtml");
		dsl.obterValorComboPrime("j_idt721:car", "Ford");
		Assert.assertEquals("Ford", dsl.obterTextoPrime("j_idt721:car"));
	}
	
	@Test
	public void deveIntegarirComAjax(){
		driver.get("https://www.primefaces.org/showcase/ui/ajax/basic.xhtml");
		dsl.escreve("//input[@id='j_idt720:name']", "jkadsfklaj fkdjakdf jakdf jkajfd kjsa dkfjsa kdfjkajdfjaskd fjakdfjaksdfj kasjd fkjas dfkja skdfj sadf");
		dsl.clicarBotao("//button[@id='j_idt720:j_idt723']");
		WebDriverWait wait = new WebDriverWait(driver, 30);
		wait.until(ExpectedConditions.textToBe(By.id("j_idt720:display"), 
				"jkadsfklaj fkdjakdf jakdf jkajfd kjsa dkfjsa kdfjkajdfjaskd fjakdfjaksdfj kasjd fkjas dfkja skdfj sadf"));
		//wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[@id='j_idt792']")));
		Assert.assertEquals("jkadsfklaj fkdjakdf jakdf jkajfd kjsa dkfjsa kdfjkajdfjaskd fjakdfjaksdfj kasjd fkjas dfkja skdfj sadf",
				dsl.obterTexto(By.xpath("//span[@id='j_idt720:display']")));
	}
	
	
}
