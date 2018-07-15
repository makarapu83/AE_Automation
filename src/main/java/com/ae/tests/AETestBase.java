package com.ae.tests;

import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import static com.codeborne.selenide.Selenide.screenshot;
import com.ae.utils.Config;

public class AETestBase extends Config{
	
	
	@BeforeMethod(alwaysRun=true)
	@Parameters("browser")
	public void beforeMethod(@Optional("firefox") String browser) {
		

		switch(browser){	  

		case "firefox":
			
			System.setProperty("webdriver.gecko.driver", "Drivers/geckodriver.exe");		    
		    System.setProperty("selenide.browser", "firefox");

			break;

		case "ie":

			System.setProperty("webdriver.chrome.driver", "Drivers/IEDriverServer.exe");		    
		    System.setProperty("selenide.browser", "ie");

			break;

		case "chrome":
			System.setProperty("webdriver.chrome.driver", "Drivers/chromedriver.exe");			
		    System.setProperty("selenide.browser", "Chrome");
			break;
		}
	}  
	
	public void userLoginByRole(String role){
		String user = aeProperties.getProperty(role+".username."+profile);
		String password = aeProperties.getProperty(role+".password."+profile);
	}
	
	
	@AfterMethod(alwaysRun=true)
	public void afterMethod(ITestResult result){
		log.info(result.toString());

		String testCase = result.getName();

		if(!(ITestResult.FAILURE==result.getStatus())){ //true
			log.info("Execution result: "+testCase+ " Test Result is -- PASS");			
		}else if(ITestResult.FAILURE == result.getStatus()){ //true
			log.info("Execution result: "+testCase+ " Test Result is -- FAIL");	
			
			String screenshotName = result.getName()+"_"+getTimeStamp()+".png";
			try{

				File file = new File("Screenshots");
				if(!file.exists()){
					file.mkdirs();
				}
				screenshot("Screenshots/"+screenshotName);
				
			}catch(Exception e){
				log.info("An exception occured while taking screenshot "+e.getCause());
			}			
		}

		
	}

	public String getTimeStamp(){
		String timeNow;
		DateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss");
		Calendar cal = Calendar.getInstance();
		timeNow = dateFormat.format(cal.getTime());
		return timeNow;
	}
}
