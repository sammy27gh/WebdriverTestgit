package example;        
 
     
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        /* first Test and Second Test will be used for Selenium  Test*/
       
        /*
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
  
        */
        
        // This is the Excel method of parameterisation
       /* public void Email() throws IOException {   
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
          */ 
           
           // This is the database method 
        @Test
        	 public void DbEmail() throws ClassNotFoundException, SQLException {
        	      	{
        	    String userName = "sa";
        	    String password = "Sk@456321";
        	     

        	    String url = "jdbc:sqlserver://104.37.189.218\\SQLEXPRESS;databaseName=Books";

        	     Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        	     Connection conn = DriverManager.getConnection(url, userName, password);
        	     Statement sta =  conn.createStatement();
        	     String Sql = "select * from Books.[dbo].[InternetMarketing]";
        	     ResultSet rs = sta.executeQuery(Sql);
        	     while (rs.next()) {
        	   
        	    	 System.out.println(rs.getString(1));
        	   
        	    final String fromEmail = "HealthyandWellnessProducts@gmail.com"; //requires valid gmail id
               final String pwd = "HealthyandWellnessProducts123"; // correct password for gmail id
              
    		// final String toEmail = (keyword); // thats to excel sheet email
               final String toEmail = (rs.getString(1));// thats to database Email
               
               System.out.println("TLSEmail Start");
               Properties props = new Properties();
               props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
               props.put("mail.smtp.port", "587"); //TLS Port
               props.put("mail.smtp.auth", "true"); //enable authentication
               props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
               String subject = "HuntGold 1X Hot Fashion Unisex Retro PU Leather Hemp Weave Eight Diagrams";
               String body = ""
               			+ "Estimated Delivery Date: May 2 - 23 when you choose Standard Shipping at checkout."+
						"Ships from and sold by HuntGold(ships from China).Material: PU Leather+Alloy."+
						"Color: red . Leather Length: About 17cm/Width: About 1.2cm. Rope Length: about 9cm-10cm"+
						"Package content: 1 * PU Leather Waistband"+
               	
               		 "http://www.amazon.com/gp/product/B00V4LEFFA/ref=as_li_tl?ie=UTF8&camp=1789&creative=9325"+
               		"&creativeASIN=B00V4LEFFA&linkCode=as2&tag=mobiautomech-20&linkId=6FKLAGXGVFZ5WSTJ ";
               
               
               
               
               
               
               Authenticator auth;
               
               auth = new Authenticator() {
                   //override the getPasswordAuthentication method
                   @Override
                   protected PasswordAuthentication getPasswordAuthentication() {
                       return new PasswordAuthentication(fromEmail, pwd);
                   }
               };
               Session session = Session.getInstance(props, auth);
                
               EmailUtil.sendAttachmentEmail(session, toEmail, subject, body);
                
        	      		}
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
