package com.ae.tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;

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
}
