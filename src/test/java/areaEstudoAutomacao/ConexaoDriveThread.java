package areaEstudoAutomacao;

import java.io.File;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class ConexaoDriveThread {
	
	static String localPasta = System.getProperty("user.dir");
	
	private static ThreadLocal<WebDriver> threadDriver = new ThreadLocal<WebDriver>(){
		@Override
		protected synchronized WebDriver initialValue(){
			return iniciaConexaoDrive();
		}
	};
	
	private ConexaoDriveThread(){}
	
	public static WebDriver getDriver(){
		return threadDriver.get();
	}
	
	public static WebDriver iniciaConexaoDrive(){
		
		WebDriver driver = null;
		
		System.setProperty("webdriver.chrome.driver", 
				localPasta + "\\src\\test\\resources\\chromedriver.exe");
		
		if(driver == null){
			switch (Propriedades.browser) {
			case CHROME: 
				ChromeOptions options = new ChromeOptions();
				options.addArguments("start-maximized");//("--headless");//
				driver = new ChromeDriver(options);
				break;
			case FIREFOX:
				driver = new FirefoxDriver();
				break;
			}
		}
		return driver;
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
	}
	
	public static void finalizaNavegador() {
		WebDriver driver = getDriver();
		if (driver != null){
			driver.manage().deleteAllCookies();
			driver.quit();
			driver = null;
		}
		if(threadDriver != null){
			threadDriver.remove();
		}
	}
}