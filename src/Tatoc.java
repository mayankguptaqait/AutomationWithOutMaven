import java.util.ArrayList;
import java.lang.*;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Tatoc {
	public static String driverPath = "resources/chromedriver/chromedriver.exe";
	public static WebDriver driver;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", driverPath);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://10.0.1.86/tatoc/basic/grid/gate");		
		driver.findElement(By.xpath("//div[@class='greenbox']")).click();
		System.out.println("clicked green box");	
		//Frame Dungeon
		driver.switchTo().frame("main");
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement element1 = driver.findElement(By.xpath("//div[text()='Box 1']"));
		String box1_class = element1.getAttribute("class");
		System.out.println("box 1 class is "+box1_class);
		driver.switchTo().frame("child");
		//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		WebElement element2 = driver.findElement(By.xpath("//div[text()='Box 2']"));
		String box2_class = element2.getAttribute("class");
		System.out.println("box 2 class is "+box2_class);
		driver.switchTo().defaultContent();
		driver.switchTo().frame("main");
		while(box1_class!=box2_class) {						
			 driver.findElement(By.xpath("//a[text()='Repaint Box 2']")).click(); 
		     System.out.println("clicked Repaint");
		 	driver.switchTo().frame("child");
			element2 = driver.findElement(By.xpath("//div[text()='Box 2']"));
			box2_class = element2.getAttribute("class");
			System.out.println("box 2 changed class is "+box2_class);
			driver.switchTo().defaultContent();
			driver.switchTo().frame("main");
			if(box1_class.contentEquals(box2_class)) {
				System.out.println("same");
				break;
			}
		}
		driver.findElement(By.xpath("//a[text()='Proceed']")).click();
		//Drag Around
		//Element which needs to drag.    		
    	WebElement From=driver.findElement(By.xpath("//div[@id='dragbox']"));	
     	//Element on which need to drop.		
    	WebElement To=driver.findElement(By.xpath("//div[@id='dropbox']"));					
     	//Using Action class for drag and drop.		
    	Actions act=new Actions(driver);					
    	//Dragged and dropped.		
    	act.dragAndDrop(From, To).build().perform();	
	   	driver.findElement(By.xpath("//a[text()='Proceed']")).click(); 
	   	//driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    	System.out.println("drag and drog");
    	//pop up windows
    	driver.findElement(By.xpath("//a[text()='Launch Popup Window']")).click(); 
       	ArrayList<String> tabs2 = new ArrayList<String> (driver.getWindowHandles());
        driver.switchTo().window(tabs2.get(1));
        driver.findElement(By.id("name")).sendKeys("hello automation");
        driver.findElement(By.id("submit")).click();        
        //driver.close(); not use bcoz window automatically close after submit
        driver.switchTo().window(tabs2.get(0));
        driver.findElement(By.xpath("//a[text()='Proceed']")).click();
        System.out.println("data submited on pop windows");
        //Token generation
        driver.findElement(By.xpath("//a[text()='Generate Token']")).click();
        System.out.println("token generate");
        WebElement element_token = driver.findElement(By.id("token"));
		String token = element_token.getText();
		String final_token="";
		System.out.println(token);
		for(int i=7;i<token.length();i++) {
			final_token=final_token+token.charAt(i);
		}
		System.out.println(final_token);
		Cookie name = new Cookie("Token", final_token);
		driver.manage().addCookie(name);
        driver.findElement(By.xpath("//a[text()='Proceed']")).click();
        driver.close();
	}
}