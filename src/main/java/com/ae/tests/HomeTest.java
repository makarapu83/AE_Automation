package com.ae.tests;

import com.ae.pages.HomePage;
import static com.codeborne.selenide.Selenide.open;
import org.testng.annotations.Test;

public class HomeTest extends AETestBase {
	
  @Test
  public void launchApp() {
    HomePage page = open("https://travel.aaaa/home", HomePage.class);
    
  }
}
