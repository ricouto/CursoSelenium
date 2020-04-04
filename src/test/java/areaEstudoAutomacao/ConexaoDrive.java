package areaEstudoAutomacao;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.rules.TestName;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;

//import org.openqa.selenium.ie.InternetExplorerDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;

public class ConexaoDrive {
	
	static WebDriver driver;
	static String localPasta = System.getProperty("user.dir");
	
	@Rule
	public TestName testName = new TestName();
	
	@BeforeClass
	public static void iniciaConexaoDrive(){
		
		System.setProperty("webdriver.chrome.driver", 
				localPasta + "\\src\\test\\resources\\chromedriver.exe");
		
		if(driver == null){
			ChromeOptions options = new ChromeOptions();
			options.addArguments("start-maximized");//("--headless");//
			driver = new ChromeDriver(options); 
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
	
	@After
	public void screenShot() throws IOException{
	Screenshot screenshot = new AShot().shootingStrategy(
				ShootingStrategies.viewportPasting(50))
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