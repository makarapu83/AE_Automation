package com.ae.utils;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;



public class Config {
	protected static String profile;
	protected Logger log = LoggerFactory.getLogger(getClass());
	public static Properties aeProperties;
	public static String aeAppURL;
	
  @SuppressWarnings("deprecation")
@BeforeSuite(alwaysRun= true)
  public void setupSuite() throws IOException {
	  
	  profile = System.getenv("AE_ENV");
	  if(profile== null){
		  Assert.fail("AE_ENV environment variable is not set");
	  }
	  
	  log.info("using the profile " + profile);
	  
	  //load properties
	  aeProperties = new Properties();
	  InputStream in = getClass().getResourceAsStream("/config.properties");
	  aeProperties.load(in);
	  in.close();
	  
	  aeAppURL = aeProperties.getProperty("aeApp.url."+profile);
	  
	  if(aeAppURL !=null ){
		  log.info("using URL: " + aeAppURL);
		  log.info("Using profile : \""+profile+"\". Environment is valid !");
	  }else{
		  Assert.fail("Environment \""+profile+"\" IS not supported !!");
	  }
	  
	  
  }
}
