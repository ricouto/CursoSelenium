package suiteTeste;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

import areaEstudoAutomacao.SiteEstudoSrBarriga;
import areaEstudoAutomacao.SiteEstudoSrBarrigaDataDriven;

	@RunWith(Suite.class)				
	@Suite.SuiteClasses({				
	  SiteEstudoSrBarriga.class
	  ,SiteEstudoSrBarrigaDataDriven.class
	})	

	public class ExecuteTestSrBarriga {				
						
	}