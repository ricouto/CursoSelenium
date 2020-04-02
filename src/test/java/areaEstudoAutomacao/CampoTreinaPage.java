package areaEstudoAutomacao;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class CampoTreinaPage extends ConexaoDrive{
	
	private static DSL dsl = new DSL();
	
	public void setNome(String nome){
		dsl.escreve("//input[@id='elementosForm:nome']", nome);
	}
	
	public void setSobrenome(String sobrenome){
		dsl.escreve("//input[@id='elementosForm:sobrenome']", sobrenome);
	}
	
	public void setSexoFeminino(){
		dsl.clicarRadio("//input[@id='elementosForm:sexo:1']");
	}
	
	public void setSexoMasculino(){
		dsl.clicarRadio("//input[@id='elementosForm:sexo:0']");
	}
	
	public void setComidaCarne(){
		dsl.clicarRadio("//input[@id='elementosForm:comidaFavorita:0']");
	}
	
	public void setComidaFrango(){
		dsl.clicarRadio("//input[@id='elementosForm:comidaFavorita:1']");
	}
	
	public void setComidaPizza(){
		dsl.clicarRadio("//input[@id='elementosForm:comidaFavorita:2']");
	}
	
	public void setComidaVegetariano(){
		dsl.clicarRadio("//input[@id='elementosForm:comidaFavorita:3']");
	}
	
	public void setEscolaridade(String escolaridade){
		dsl.selecionarCombo("//select[@id='elementosForm:escolaridade']", escolaridade);
	}
	
	public void setEsporte(String... valores){
		for(String valor: valores)
		dsl.selecionarCombo("//select[@id='elementosForm:esportes']", valor);
	}
	
	public void btnCadastrar(){
		dsl.clicarBotao("//input[@id='elementosForm:cadastrar']");
	}
	
	/*   Obter dados do Cadastro   */
	
	public String obterNomeFormulario(){
		return dsl.obterValorCampo("//input[@id='elementosForm:nome']");
	}
	
	public String obterSobrenomeFormulario(){
		return dsl.obterValorCampo("//input[@id='elementosForm:sobrenome']");
	}
	
	public String obterSexoFemiFormulario(){
		return dsl.obterTexto(By.xpath("//input[@id='elementosForm:sexo:1']/../label"));
	}
	
	public String obterComidaVegetaFormulario(){
		return dsl.obterValorCampo("//input[@id='elementosForm:comidaFavorita:3']");
	}
	
	public String obterEscolariFormulario(){
		return dsl.obterValorCombo("//select[@id='elementosForm:escolaridade']");
	}
	
	public List<WebElement> obterEsporteFormulario(){
		return dsl.obterValoresCombo("//select[@id='elementosForm:esportes']");
	}
	
	/*   Obter dados do formulario abaixo do Cadastro   */
	
	public String obterNomeCadastro(){
		return dsl.obterTexto(By.xpath("//div[@id='descNome']/span"));
	}
	
	public String obterSobrenomeCadastro(){
		return dsl.obterTexto(By.xpath("//div[@id='descSobrenome']/span"));
	}

	public String obterSexoCadastro(){
		return dsl.obterTexto(By.xpath("//div[@id='descSexo']/span"));
	}
	
	public String obterComidaCadastro(){
		return dsl.obterTexto(By.xpath("//div[@id='descComida']/span"));
	}
	
	public String obterEscolaridadeCadastro(){
		return dsl.obterTexto(By.xpath("//div[@id='descEscolaridade']/span"));
	}
}
