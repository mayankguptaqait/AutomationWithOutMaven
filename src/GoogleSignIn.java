import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class GoogleSignIn {
		public static String path = "resources/chromedriver/chromedriver.exe";
		public static WebDriver driver;
		public static void main(String[] args) {
		System.out.println("launching chrome browser");
		System.setProperty("webdriver.chrome.driver", path);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
        String url = "https://accounts.google.com/signin";
        driver.get(url);
        driver.findElement(By.id("identifierId")).sendKeys("testmailbymayank@gmail.com");
        driver.findElement(By.xpath("//span[@class='CwaK9']")).click();   
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//input[@name='password']")).sendKeys("qwerty@123");    
        boolean staleElement = true; 
        while(staleElement){
          try{
              driver.findElement(By.xpath("//span[text()='Next']")).click(); 
             staleElement = false;
          } catch(StaleElementReferenceException e){
            staleElement = true;
          }
        }
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.close();
	}
}