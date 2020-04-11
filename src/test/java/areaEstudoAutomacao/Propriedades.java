package areaEstudoAutomacao;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.lang.time.DateUtils;

public class Propriedades {
	
	static String localPasta = System.getProperty("user.dir");
	
	public static final String localData
	 = localPasta + "\\src\\test\\resources\\planilhaMassa\\usuarios.csv";
	
	//static String SimpleOrderManagerTest=localData;
	
	public static Browsers browser = Browsers.CHROME;
	
	public enum Browsers{
		CHROME,
		FIREFOX
	}
	
	public String dataAtual() {
		String pattern = "dd/MM/yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String newDate = simpleDateFormat.format(new Date());
		return newDate; 
	}	
	
	public String dataFutura() {
		String pattern = "dd/MM/yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String newDate = simpleDateFormat.format(DateUtils.addDays(new Date(), 2));
		return newDate; 
	}
	
	public String mesAtual() {
		String pattern = "MMMMM";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String newDate = simpleDateFormat.format(new Date());
		return newDate; 
	}
	
	public String anoAtual() {
		String pattern = "yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String newDate = simpleDateFormat.format(new Date());
		return newDate; 
	}
	
	public String anoPassado() {
		String pattern = "yyyy";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String newDate = simpleDateFormat.format(DateUtils.addYears(new Date(), -1));
		return newDate; 
	}
	
}
