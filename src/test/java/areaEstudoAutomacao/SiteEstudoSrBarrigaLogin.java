package areaEstudoAutomacao;

import static areaEstudoAutomacao.ConexaoDriveThread.finalizaNavegador;
import static areaEstudoAutomacao.ConexaoDriveThread.getDriver;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;

public class SiteEstudoSrBarrigaLogin {
	
	private static DSLThread dsl = new DSLThread();
	
	@Before
	public void openSiteLogin() throws InterruptedException{
		getDriver().get("https://seubarriga.wcaquino.me/login");
		System.out.println(getDriver().getTitle());
		
		System.out.println("\n**** Site de estudo Sr Barriga ****\n");
		dsl.escreve("//input[@id='email']", "ricardocaputo@gmail.com");
		dsl.escreve("//input[@id='senha']", "1234567890");
		dsl.clicarBotao("//button[@type='submit']");
		Thread.sleep(500);
	}
	
	@After
	public void encerraSite() throws IOException{
		finalizaNavegador();
	}
}
