import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
public class AlertAndPopUp {
	public static WebDriver driver;
	public static String path = "resources/chromedriver/chromedriver.exe";
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver", path);
		driver =new ChromeDriver();
		driver.get("http://demo.automationtesting.in/Alerts.html");
	//	WebDriverWait wait = new WebDriverWait(driver,50);
		driver.findElement(By.xpath("//button[@class='btn btn-danger']")).click();
	//	WebElement buutonClick = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[@class='btn btn-success navbar-toggle']")));
	//	buutonClick.click();
		System.out.println("clicked on button to display alert box");
		driver.switchTo().alert();
		System.out.println("switched to alert");
		driver.switchTo().alert().accept();
		driver.findElement(By.xpath("//a[text()='Alert with OK & Cancel ']")).click();
		driver.findElement(By.xpath("//button[@class='btn btn-primary']")).click();
		driver.switchTo().alert();
		System.out.println("switched to alert");
		driver.switchTo().alert().accept();
		System.out.println("alert accepted");
		String data = driver.findElement(By.xpath("//p[@id='demo']")).getText();
		System.out.println("data recieve");
		System.out.println(data);
		driver.findElement(By.xpath("//a[text()='Alert with Textbox ']")).click();
		driver.findElement(By.xpath("//button[@class='btn btn-info']")).click();
		driver.switchTo().alert();
		System.out.println("switched to alert");
		driver.switchTo().alert().sendKeys("alert handling");
		driver.switchTo().alert().accept();
		String data2 = driver.findElement(By.xpath("//p[@id='demo1']")).getText();
		System.out.println("data recieve");
		System.out.println(data2);
		driver.close();
	}
}