package yahoo;

import java.util.List;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Reporter;
import org.testng.annotations.Listeners;

import atu.testng.reports.ATUReports;
import atu.testng.reports.listeners.ATUReportsListener;
import atu.testng.reports.listeners.ConfigurationListener;
import atu.testng.reports.listeners.MethodListener;
import atu.testng.reports.logging.LogAs;
import atu.testng.selenium.reports.CaptureScreen;
import atu.testng.selenium.reports.CaptureScreen.ScreenshotOf;

@Listeners({ATUReportsListener.class,ConfigurationListener.class,MethodListener.class})
public class Home 
{
	{
		System.setProperty("atu.reporter.config", "D:\\7AMJuly\\Myproj\\atu.properties");
	}
	WebDriver driver;
	public Home(WebDriver driver)
	{
		this.driver=driver;
	}
	public void open()
	{	
		driver.manage().window().maximize();
		driver.get("http://www.yahoomail.com");
	}
	public void validate_links()
	{
		open();
		String lnks[]={"About Mail","Features","Get App","Help"};
		
		WebElement w=driver.findElement(By.xpath("//*[@class='Fl(end) Mend(10px) Lts(-0.31em) Tren(os) Whs(nw) My(6px)']"));
		List<WebElement> lst=w.findElements(By.tagName("a"));
		for(int i=0;i<lst.size();i++)
		{
			if(lst.get(i).getText().matches(lnks[i]))
			{
				ATUReports.add("Link is matching "+lnks[i],LogAs.PASSED,new CaptureScreen(ScreenshotOf.DESKTOP));
			}
			else
			{
				ATUReports.add("Link is Not  matching "+lnks[i],LogAs.FAILED,new CaptureScreen(ScreenshotOf.DESKTOP));
			}
		}
		
	}
	public void login()
	{
		open();
		driver.findElement(By.name("username")).sendKeys("venkat123456a");
		driver.findElement(By.name("passwd")).sendKeys("mq123456");
		driver.findElement(By.name("signin")).click();
	}
	public void createacc() throws Exception
	{
		open();
		driver.findElement(By.id("login-signup")).click();
		Thread.sleep(5000);
		driver.findElement(By.name("firstname")).sendKeys("abcd");
		
		
		driver.findElement(By.xpath("//span[@class='country-code-arrow']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//*[@data-code='91']")).click();
		driver.findElement(By.id("mobile")).sendKeys("8989898989");
		
		new Select(driver.findElement(By.id("month"))).selectByVisibleText("July");
		new Select(driver.findElement(By.id("day"))).selectByVisibleText("20");
		new Select(driver.findElement(By.id("year"))).selectByVisibleText("1984");
		
		driver.findElement(By.id("male")).click();
	}
}
