package example;        
 
import org.openqa.selenium.By;      
import org.openqa.selenium.WebDriver;       
import org.openqa.selenium.firefox.FirefoxDriver;       
import org.testng.Assert;       
import org.testng.annotations.Test; 
import org.testng.annotations.BeforeTest;   
import org.testng.annotations.AfterTest;        
public class NewTest {      
	
        private WebDriver driver;       
        @Test              
        public void testEasy() {    
            driver.get("http://www.guru99.com/selenium-tutorial.html");  
            String title = driver.getTitle();   
            System.out.println("Test if the title containf free selenium tutorial ");
            Assert.assertTrue(title.contains("Free Selenium Tutorials"));       
        }   
        public void testEasy2() {    
        	System.out.println("This is the second test ");
            driver.get("http://www.guru99.com/selenium-tutorial.html");  
            String title = driver.getTitle();   
            System.out.println("Test if the title containf free selenium tutorial ");
            Assert.assertTrue(title.contains("kofi"));       
        } 
        @BeforeTest
        public void beforeTest() {  
            driver = new FirefoxDriver();  
        }       
        @AfterTest
        public void afterTest() {
            driver.quit();          
        }       
}   
