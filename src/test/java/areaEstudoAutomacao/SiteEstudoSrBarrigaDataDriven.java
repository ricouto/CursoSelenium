package areaEstudoAutomacao;

import java.util.ArrayList;

import org.easetech.easytest.annotation.DataLoader;
import org.easetech.easytest.annotation.Param;
import org.easetech.easytest.loader.LoaderType;
import org.easetech.easytest.runner.DataDrivenTestRunner;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(DataDrivenTestRunner.class)
@DataLoader(filePaths = {"C:/Users/HiTECH-PC/workspace/CursoSelenium/src/test/resources/planilhaMassa/usuarios.xml"}, loaderType = LoaderType.XML)
public class SiteEstudoSrBarrigaDataDriven extends SiteEstudoSrBarrigaLogin {

	private static DSLThread dsl = new DSLThread();
	static ArrayList<String> userList = new ArrayList<String>();

	@Rule
	public ScreenShotFailureThread failure = new ScreenShotFailureThread();
/*	
	@BeforeClass
	public static void logonPoral() throws InterruptedException {
		loginSrBarriga.openSiteLogin();
	}
	
	@AfterClass
	public static void exitSite() {
		System.out.println("\n**** Fim dos testes no site Sr Barriga ****\n");
	}
*/
	
	@Test
	//@Repeat(times = 8)
	public void addFromCSV(@Param(name = "a") String a,
			@Param(name = "expected") String expected) throws InterruptedException {
		dsl.clicarMenuList("Contas ", "Adicionar");
		dsl.escreve("//input[@id='nome']", a);
		userList.add(a);
		dsl.clicarBotao("//button[.='Salvar']");
		Assert.assertEquals(a, dsl.obterTextoXP("//table/tbody//td[.='"+a+"'][1]"));
		System.out.println("Conta " +a+ " adicionada com sucesso!");
	}
	
	
	@Test
	public void removeFromCSV() {
		int n = userList.size();
		dsl.clicarMenuList("Contas ", "Listar");
		//int i = dsl.obterQtdValoresTabela("//table/tbody//td[2]/a[2]");
		  for (int i=0; i<n; i++) {
			  String valorGrid = userList.get(i);
		      dsl.clicarBotao("//table/tbody//td[.='"+valorGrid+"'][1]/..//td[2]/a[2]");
		      Assert.assertEquals("Conta removida com sucesso!",
						dsl.obterTextoXP("//div[@class='alert alert-success']"));
				System.out.println("Conta " + valorGrid + " removida com sucesso!");
		    }
		
		/*
		while (i != 0) {
			String valorGrid = dsl.obterTextoXP("//table/tbody//td[1]");
			dsl.clicarBotao("//table/tbody//td[2]/a[2]");
			Assert.assertEquals("Conta removida com sucesso!",
					dsl.obterTextoXP("//div[@class='alert alert-success']"));
			System.out.println("Conta " + valorGrid + " removida com sucesso!");
			i--;
		}
		*/
	}
}
