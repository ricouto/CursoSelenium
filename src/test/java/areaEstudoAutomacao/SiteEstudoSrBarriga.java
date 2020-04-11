package areaEstudoAutomacao;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import static areaEstudoAutomacao.ConexaoDriveThread.*;

public class SiteEstudoSrBarriga extends SiteEstudoSrBarrigaLogin {
	
	private static DSLThread dsl = new DSLThread();
	//private static SiteEstudoSrBarrigaLogin loginSrBarriga = new SiteEstudoSrBarrigaLogin();
	private static Propriedades propriedades = new Propriedades();
	
	@Rule
	public ScreenShotFailureThread failure = new ScreenShotFailureThread();
	
	/*	@BeforeClass
	public static void resetDadosSite() throws InterruptedException{
		dsl.clicarMenuList("Home");
		dsl.clicarBotao("//span/a[.='reset']");
	}
	
	@AfterClass
	public static void exitSite(){
		finalizaNavegador();
		System.out.println("\n**** Fim dos testes no site Sr Barriga ****\n");
	}
*/	
	
	@Test
	public void teste0_resetDadosSite() {
		dsl.clicarMenuList("Home");
		dsl.clicarBotao("//span/a[.='reset']");
	}	
	
	@Test
	public void teste1_adicionaDadosConta() {
		dsl.clicarMenuList("Contas ", "Adicionar");
		dsl.escreve("//input[@id='nome']", "conta Sr Madruga");
		dsl.clicarBotao("//button[.='Salvar']");
		System.out.println(dsl.obterTextoXP("//div[@role]"));
		Assert.assertEquals("Conta adicionada com sucesso!", dsl.obterTextoXP("//div[@role]"));
	}
	
	@Test
	public void teste2() { //_alteraDadosConta
		dsl.clicarMenuList("Contas ", "Listar");
		dsl.clicarBotao("//table/tbody//td[.='Conta para alterar'][1]/../td[2]/a[1]");
		dsl.escreve("//input[@id='nome']", "Conta para alterar - Alterada!");
		dsl.clicarBotao("//button[.='Salvar']");
		System.out.println(dsl.obterTextoXP("//div[@role]"));
		Assert.assertEquals("Conta alterada com sucesso!", dsl.obterTextoXP("//div[@role]"));
	}
	
	@Test
	public void teste3() { //_duplicaDadosConta
		dsl.clicarMenuList("Contas ", "Adicionar");
		dsl.escreve("//input[@id='nome']", "Conta mesmo nome");
		dsl.clicarBotao("//button[.='Salvar']");
		System.out.println(dsl.obterTextoXP("//div[@role]"));
		Assert.assertEquals("Já existe uma conta com esse nome!", dsl.obterTextoXP("//div[@role]"));
	}
	
	@Test
	public void teste4() { //_insereMovimentacao
		dsl.clicarMenuList("Criar Movimentação");
		dsl.selecionarCombo("//select[@id='tipo']", "Despesa");
		dsl.escreve("//input[@id='data_transacao']", propriedades.dataAtual());
		dsl.escreve("//input[@id='data_pagamento']", propriedades.dataAtual());
		dsl.escreve("//input[@id='descricao']", "Pgto realizado com sucesso!");
		dsl.escreve("//input[@id='interessado']", "setor contas a pagar");
		dsl.escreve("//input[@id='valor']", "150");
		dsl.selecionarCombo("//select[@id='conta']", "Conta para movimentacoes");
		dsl.clicarBotao("//input[@id='status_pago']");
		
		dsl.clicarBotao("//button[.='Salvar']");
		System.out.println(dsl.obterTextoXP("//div[@role]"));
		Assert.assertEquals("Movimentação adicionada com sucesso!", dsl.obterTextoXP("//div[@role]"));
	}
	
	
	@Test
	public void teste5() { //_camposObrigatoriosMovimentacao
		dsl.clicarMenuList("Criar Movimentação");
		dsl.selecionarCombo("//select[@id='conta']", "Conta para movimentacoes");
	
		dsl.clicarBotao("//button[.='Salvar']");
		List<String> erros = dsl.obterErros("//div[@class='alert alert-danger']//li");
		Assert.assertTrue(erros.containsAll(Arrays.asList(
				"Data da Movimentação é obrigatório",
				"Data do pagamento é obrigatório",
				"Descrição é obrigatório",
				"Interessado é obrigatório",
				"Valor é obrigatório",
				"Valor deve ser um número")));
		Assert.assertEquals(6, erros.size());
		System.out.println(dsl.obterTextoXP("//div[@class='alert alert-danger']/ul"));
	}
	
	@Test
	public void teste6() { //_insereMovimentacaoFutura
		dsl.clicarMenuList("Criar Movimentação");
		dsl.selecionarCombo("//select[@id='tipo']", "Despesa");
		dsl.escreve("//input[@id='data_transacao']", propriedades.dataFutura());
		dsl.escreve("//input[@id='data_pagamento']", propriedades.dataFutura());
		dsl.escreve("//input[@id='descricao']", "Pgto realizado com sucesso!");
		dsl.escreve("//input[@id='interessado']", "setor contas a pagar");
		dsl.escreve("//input[@id='valor']", "8550");
		dsl.selecionarCombo("//select[@id='conta']", "Conta para movimentacoes");
		dsl.clicarBotao("//input[@id='status_pendente']");
		
		dsl.clicarBotao("//button[.='Salvar']");
		List<String> erros = dsl.obterErros("//div[@class='alert alert-danger']//li");
		Assert.assertTrue(erros.contains(
				"Data da Movimentação deve ser menor ou igual à data atual"));
		Assert.assertEquals(1, erros.size());
		System.out.println(dsl.obterTextoXP("//div[@role]"));
	}
	
	@Test
	public void teste8() { //_removaContaComMovimentacao
		dsl.clicarMenuList("Contas ", "Listar");
		dsl.clicarBotao("//table/tbody//td[.='Conta com movimentacao'][1]/../td[2]/a[2]");
		System.out.println(dsl.obterTextoXP("//div[@role]"));
		Assert.assertEquals("Conta em uso na movimentações", dsl.obterTextoXP("//div[@role]"));
	}
	
	@Test
	public void teste7() { //_saldoContas
		dsl.clicarMenuList("Home");
		Assert.assertEquals("534.00", 
				dsl.obterTextoXP("//table/tbody//td[.='Conta para saldo'][1]/../td[2]"));
		System.out.println(dsl.obterTextoXP("//table/tbody//td[.='Conta para saldo'][1]"));
		System.out.println(dsl.obterTextoXP("//table/tbody//td[.='Conta para saldo'][1]/../td[2]"));
	}
	
	@Test
	public void teste9() { //_removeMovimentacao
		dsl.clicarMenuList("Resumo Mensal");
		dsl.selecionarCombo("//select[@id='mes']", propriedades.mesAtual());
		dsl.selecionarCombo("//select[@id='ano']", propriedades.anoAtual());
		dsl.clicarBotao("//input[@value='Buscar']");
		dsl.clicarBotao("//table//td[.='Movimentacao para exclusao']/..//td[6]/a");
		System.out.println(dsl.obterTextoXP("//div[@role]"));
		Assert.assertEquals("Movimentação removida com sucesso!", dsl.obterTextoXP("//div[@role]"));
	}
	
	@Test
	public void teste91_removerDadosConta() { //_removerDadosConta
		dsl.clicarMenuList("Contas ", "Listar");
		dsl.clicarBotao("//table/tbody//td[.='conta Sr Madruga'][1]/../td[2]/a[2]");
		System.out.println(dsl.obterTextoXP("//div[@role]"));
		Assert.assertEquals("Conta removida com sucesso!", dsl.obterTextoXP("//div[@role]"));
	}
	
	@Test
	public void teste92_alteraDadosContaOriginal() { //_alteraDadosConta
		dsl.clicarMenuList("Contas ", "Listar");
		dsl.clicarBotao("//table/tbody//td[.='Conta para alterar - Alterada!'][1]/../td[2]/a[1]");
		dsl.escreve("//input[@id='nome']", "Conta para alterar");
		dsl.clicarBotao("//button[.='Salvar']");
		System.out.println(dsl.obterTextoXP("//div[@role]"));
		Assert.assertEquals("Conta alterada com sucesso!", dsl.obterTextoXP("//div[@role]"));
	}
	
	@Test
	public void teste93_validaResumoMensal() { //_validaResumoMensalAnoPassado
		dsl.clicarMenuList("Resumo Mensal");
		dsl.selecionarCombo("//select[@id='mes']", propriedades.mesAtual());
		dsl.selecionarCombo("//select[@id='ano']", propriedades.anoPassado());
		dsl.clicarBotao("//input[@value='Buscar']");
		
		List<WebElement> resumoMensal = getDriver().findElements(By.xpath("//table[@id='tabelaExtrato']/tbody/tr"));
		Assert.assertEquals(0, resumoMensal.size());
	}
	
}
