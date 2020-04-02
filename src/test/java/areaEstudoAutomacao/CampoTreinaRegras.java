package areaEstudoAutomacao;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class CampoTreinaRegras extends ConexaoDrive {
	
	private static DSL dsl;
	private static CampoTreinaPage page;
	
	@Parameter
	public String nome;
	@Parameter(value=1)
	public String sobrenome;
	@Parameter(value=2)
	public String sexo;
	@Parameter(value=3)
	public List<String> comidas;
	@Parameter(value=4)
	public String escolaridade;
	@Parameter(value=5)
	public String[] esportes;
	@Parameter(value=6)
	public String msg;
	
	@BeforeClass
	public static void openSite(){
		
		driver.get("https://ricouto.github.io/");
		dsl = new DSL();
		page = new CampoTreinaPage();
	}
	
	@Parameters
	public static Collection<Object[]> getCollection(){
		return Arrays.asList(new Object[][]{
				//nome,sb,sexo, 	comida,    escola,     esporte,       msg
				{"","","", Arrays.asList(),"Especializacao",new String[]{},"Nome eh obrigatorio"},
				{"Maitê","","", Arrays.asList(),"Mestrado",new String[]{},"Sobrenome eh obrigatorio"},
				{"Maitê","de Morais e Costa","", Arrays.asList(),"Doutorado",new String[]{},"Sexo eh obrigatorio"},
				{"Maitê","de Morais e Costa","Feminino", Arrays.asList("Carne","Vegetariano"),"2o grau incompleto",new String[]{},"Tem certeza que voce eh vegetariano?"},
				{"Maitê","de Morais e Costa","Feminino", Arrays.asList("Carne"),"Superior",new String[]{"Natacao","O que eh esporte?"},"Voce faz esporte ou nao?"}
		});
	}
	
	@Test
	public void treinaCampoDesafioCadastroAula35() throws InterruptedException{
	
	page.setNome(nome);
	page.setSobrenome(sobrenome);
	if(sexo.equals("Masculino"))
		page.setSexoMasculino();
	if(sexo.equals("Feminino"))
		page.setSexoFeminino();
	if(comidas.contains("Carne")) page.setComidaCarne();
	if(comidas.contains("Frango")) page.setComidaFrango();
	if(comidas.contains("Pizza")) page.setComidaPizza();
	if(comidas.contains("Vegetariano")) page.setComidaVegetariano();
	page.setEscolaridade(escolaridade);
	page.setEsporte(esportes);
	page.btnCadastrar();
	System.out.println(dsl.windowAlert().getText());
	Assert.assertEquals(msg, dsl.windowAlert().getText());
	dsl.windowAlertAccept();
	}
}
