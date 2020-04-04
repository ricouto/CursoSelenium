package areaEstudoAutomacao;

import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.junit.rules.MethodRule;
import org.junit.runners.model.FrameworkMethod;
import org.junit.runners.model.Statement;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

public class ScreenShotFailure extends ConexaoDrive implements MethodRule {
	
    static String localPasta = System.getProperty("user.dir");

	public Statement apply(final Statement statement,
			final FrameworkMethod frameworkMethod, final Object o) {

		return new Statement() {

			@Override
			public void evaluate() throws Throwable {
				try {
					statement.evaluate();
				} catch (Throwable t) {
					// only when a test fails exception will be thrown.
					screenShot(frameworkMethod.getName());
					// rethrow to allow the failure to be reported by JUnit
					throw t;
				}
			}
            
			public void screenShot(String fileName) throws IOException {
				TakesScreenshot scrShot = (TakesScreenshot) driver;

				File arquivo = scrShot.getScreenshotAs(OutputType.FILE);

				FileUtils.copyFile(arquivo, new File(
						localPasta
						+ "\\src\\test\\resources\\imagensExecuteTest\\"
						+ fileName + ".png"));
			}
		};
	}
}
