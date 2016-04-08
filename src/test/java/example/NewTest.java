package example;        
 
     
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebDriver;       
import org.openqa.selenium.firefox.FirefoxDriver;       
import org.testng.Assert;       
import org.testng.annotations.Test; 
import org.testng.annotations.BeforeTest;   
import org.testng.annotations.AfterTest;        
public class NewTest {      
	
        private WebDriver driver;       
        
        @Test
        public void firstTest() {    
        	System.out.println("This is the first test ");
            driver.get("http://www.guru99.com/selenium-tutorial.html");  
            String title = driver.getTitle();   
            System.out.println("Test if the title containf free selenium tutorial ");
            Assert.assertTrue(title.contains("Free Selenium Tutorials"));       
        } 
        @Test
        public void secondTest() {    
        	System.out.println("This is the second test ");
            driver.get("http://www.indeed.com/"); 
            String expected = "Job Search | one search. all jobs. Indeed.com";
            String actual = driver.getTitle();   
            System.out.println("the check for equals has  ");
            Assert.assertEquals(actual, expected);
        } 
        @Test
        public void ThirdTest() {    
        	System.out.println("This is the third Test ");
            driver.get("http://www.indeed.com/");  
            String title = driver.getTitle();   
            System.out.println("seee if third test worked ");
            Assert.assertTrue(title.contains("indeed"));       
        }
        @Test
        public void Email() throws IOException {   
            //connect to excel sheet
             File excel = new File("C:\\Users\\emaildoc\\TestData1.xlsx");
             FileInputStream fis = new FileInputStream(excel);

                          XSSFWorkbook wb = new XSSFWorkbook(fis);
                          XSSFSheet ws = wb.getSheet("Sheet1");

                          
                          int rowNum = ws.getLastRowNum() + 1;
                          int colNum = ws.getRow(0).getLastCellNum();
                          String[][] data = new String[(rowNum - 1)][colNum];

             for (int i=0; i <= ws.getLastRowNum(); i++){
    
             String keyword = ws.getRow(i).getCell(1).getStringCellValue();
             
            System.out.println(keyword);
           
           
           
           
           
           
           final String fromEmail = "andoh.samuel@gmail.com"; //requires valid gmail id
           final String password = "ska123456"; // correct password for gmail id
           final String toEmail = (keyword); // can be any email id 
           
           System.out.println("TLSEmail Start");
           Properties props = new Properties();
           props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
           props.put("mail.smtp.port", "587"); //TLS Port
           props.put("mail.smtp.auth", "true"); //enable authentication
           props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
           Authenticator auth;
           
           auth = new Authenticator() {
               //override the getPasswordAuthentication method
               @Override
               protected PasswordAuthentication getPasswordAuthentication() {
                   return new PasswordAuthentication(fromEmail, password);
               }
           };
           Session session = Session.getInstance(props, auth);
            
           EmailUtil.sendAttachmentEmail(session, toEmail,"TLSEmail Testing Subject", " Attachment TLSEmail Testing Body");
            
       }
    
        
       
       
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
