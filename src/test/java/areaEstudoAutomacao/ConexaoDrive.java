package areaEstudoAutomacao;

import java.io.File;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
//import org.openqa.selenium.ie.InternetExplorerDriver;

public class ConexaoDrive {
	
	static WebDriver driver;
	static String localPasta = System.getProperty("user.dir");
	
	//@Rule
	//public TestName testName = new TestName();
	
	@BeforeClass
	public static void iniciaConexaoDrive(){
		
		System.setProperty("webdriver.chrome.driver", 
				localPasta + "\\src\\test\\resources\\chromedriver.exe");
		
		if(driver == null){
			switch (Propriedades.browser) {
			case CHROME: 
				ChromeOptions options = new ChromeOptions();
				options.addArguments("--headless");//("start-maximized");//
				driver = new ChromeDriver(options);
				break;
			case FIREFOX:
				driver = new FirefoxDriver();
				break;
			}
		}
	}
		
	public static void cleanDirectory() {
		File dir = new File(localPasta
				+ "\\src\\test\\resources\\imagensExecuteTest\\");

		if (dir.isDirectory() == false) {
			System.out.println("Not a directory. Do nothing");
			return;
		}
		File[] listFiles = dir.listFiles();
		for (File file : listFiles) {
			System.out.println("Deleting " + file.getName());
			file.delete();
		}
		// now directory is empty, so we can delete it
		// System.out.println("Deleting Directory. Success = " + dir.delete());
	}

/*	
	@After
	public void screenShot() throws IOException{
    TakesScreenshot scrShot = (TakesScreenshot) driver;

    File arquivo = scrShot.getScreenshotAs(OutputType.FILE);
			
	FileUtils.copyFile(arquivo, new File(localPasta + "\\src\\test\\resources\\imagensExecuteTest\\" 
		+ testName.getMethodName() + ".png"));
	}
	
	@After
	public void screenShot02() throws IOException{
	Screenshot screenshot = new AShot().shootingStrategy( 
			ShootingStrategies.viewportPasting(ShootingStrategies.scaling(1f), 0))
				
				//ShootingStrategies.viewportPasting(50))
				.takeScreenshot(driver);
		try {
			ImageIO.write(screenshot.getImage(),
					"png", 
					new File(localPasta + "\\src\\test\\resources\\imagensExecuteTest\\" 
							+ testName.getMethodName() + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
*/
	
	@AfterClass
	public static void finalizaNavegador() throws InterruptedException {
		if (driver != null)
			driver.manage().deleteAllCookies();
			driver.quit();
			driver = null;
	}
}

/*	@BeforeClass
public static void iniciaNavegador() throws InterruptedException {
	
	String localPasta = System.getProperty("user.dir");
	
	ChromeOptions options = new ChromeOptions();
	options.addArguments("--headless");//("start-maximized");//

	System.setProperty(
			"webdriver.chrome.driver",
			localPasta + "\\src\\test\\resources\\chromedriver.exe");
	// WebDriver driver = new FirefoxDriver();

	driver = new ChromeDriver(options);
}*/