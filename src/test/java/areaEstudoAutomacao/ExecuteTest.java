package areaEstudoAutomacao;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
/*
import org.junit.runner.JUnitCore;		
import org.junit.runner.Result;		
import org.junit.runner.notification.Failure;
*/

	@RunWith(Suite.class)				
	@Suite.SuiteClasses({				
	  TesteGoogle.class,
	  SiteAleatorio.class,
	  TestAPI.class
	})	
		

	public class ExecuteTest {				
						
	}


/*
public class ExecuteTest {
	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(
				TesteGoogle.class,
				SiteAleatorio.class,
				TestAPI.class
				);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		System.out.println("Result == " + result.wasSuccessful());
	}
	
}	
	*/