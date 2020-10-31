package trial;

import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {

	public static WebDriver driver;
		@BeforeSuite
		public void setup () {
			
			WebDriverManager.chromedriver().setup();
			driver = new ChromeDriver();
			driver.get("http:\\google.com");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		
			System.out.println("First Window "+driver.getTitle());
			
			driver.switchTo().newWindow(WindowType.TAB);
			driver.get("http:\\gmail.com");
			System.out.println("Second Window "+driver.getTitle());
			driver.findElement(By.linkText("Sign in")).click();
			
			Set<String> element = driver.getWindowHandles();
			Iterator<String> iterate= element.iterator();
			
			iterate.next();
			iterate.next();
			String third_window= iterate.next();
			
			driver.switchTo().window(third_window);
			System.out.println("Third_window "+driver.getTitle());
			
			driver.switchTo().newWindow(WindowType.WINDOW);
			driver.get("http://gmail.com");		
		}
	@Test
	public void Login() {
		
		driver.findElement(By.id("identifierId")).sendKeys("shashimaurya813@gmail.com");
		driver.findElement(By.xpath("//*[@id=\"identifierNext\"]/div/button/div[2]")).click();
		driver.findElement(By.id("Password")).sendKeys("test123");
	}
	@AfterTest
	public void tearDown() {
		driver.quit();
	}

}
