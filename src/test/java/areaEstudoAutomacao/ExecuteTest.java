package areaEstudoAutomacao;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

	@RunWith(Suite.class)				
	@Suite.SuiteClasses({				
	  TesteGoogle.class,
	  SiteAleatorio.class,
	  SiteEstudo.class,
	  SiteEstudoPrimeFaces.class,
	  CampoTreinaRegras.class,
	  TestAPI.class 
	  /*SiteAleatorioCopy.class*/
	})	

	public class ExecuteTest {				
						
	}