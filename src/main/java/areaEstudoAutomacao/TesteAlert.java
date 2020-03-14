package areaEstudoAutomacao;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class TesteAlert {
	@Test
	public void testTextField() {
		WebDriver driver = new FirefoxDriver();
		driver.get("file:///" + System.getProperty("user.dir")
				+ "/src/main/resources/componentes.html");
		
	}
}
