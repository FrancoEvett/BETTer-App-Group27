package test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import javax.swing.Box;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

import java.sql.*;  

public class Scraper {
	public static ArrayList<String> dataStorage = new ArrayList<>();
	public static boolean weekChecker;
	static Connection con;
	static int checkNumber = 0;
	public static int weekNum;
	public static void main(String[] args) throws InterruptedException, SQLException {
		System.setProperty("webdriver.chrome.driver", "C://Selenium//chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		WebDriverWait wait = new WebDriverWait(driver, 30);
		//driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().pageLoadTimeout(40, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.navigate().to("https://teaching.brunel.ac.uk/SWS-1920/login.aspx");
		JFrame frame = new JFrame("InputDialog Example #1");
	String iD = JOptionPane.showInputDialog(frame, "Enter your ID");
		
		driver.findElement(By.id("tUserName")).sendKeys(iD);
		
		
		Box box = Box.createHorizontalBox();

		JLabel jl = new JLabel("Password: ");
		box.add(jl);

		JPasswordField jpf = new JPasswordField(24);
		box.add(jpf);

		int button = JOptionPane.showConfirmDialog(null, box, "Enter your password", JOptionPane.OK_CANCEL_OPTION);

		if (button == JOptionPane.OK_OPTION) {
		    char[] input = jpf.getPassword();
		    
	
		    
		  String pass = String.copyValueOf(input);
	
		
		
		driver.findElement(By.id("tPassword")).sendKeys(pass);
		driver.findElement(By.id("bLogin")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"LinkBtn_pos\"]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"dlFilter\"]/option[4]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"dlObject\"]/option[72]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"lbWeeks\"]/option[2]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"lbWeeks\"]/option[2]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"lbDays\"]/option[1]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"dlType\"]/option[2]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//*[@id=\"bGetTimetable\"]")).click();
		Thread.sleep(1000);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con =DriverManager.getConnection("jdbc:mysql://db4free.net:3306/bruneltimetable", "bruneluser","Brunel@123");
		} catch(Exception e) {
			System.out.println("Error" + e);
		}
		
		
		
		
		

		do {
			String weekName = driver.findElement(By.xpath("/html/body/table[1]/tbody/tr[2]/td/table/tbody/tr/td[3]/span[2]")).getText();
			weekNum = Integer.parseInt(weekName);
			System.out.println(weekNum);
			loopMethod(driver);	
		}
		while(weekNum < 52);
			System.out.println("No More Data");
			driver.close();
			con.close();
			System.exit(0);		
	}
	}
		
		
	
	public static void loopMethod(WebDriver driver) throws InterruptedException, SQLException {
		for(int i=2; i< 7;i++) {
			driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
			Boolean isWholePresent = driver.findElements(By.className("columnTitles")).size() > 0;
			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			if(isWholePresent) {
				driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
				Boolean isPresent = driver.findElements(By.xpath("/html/body/table["+i+"]/tbody/tr[1]")).size() > 0;
				
				
			//	System.out.println("Break Point 1");
				
				driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
				//System.out.println(isPresent);
				if(isPresent) {
					
					
					///html/body/table[2]/tbody/tr[2]
					
					int colSize = 0;
					int rowSize = 0;
					WebElement tableBody = driver.findElement(By.xpath("/html/body/table[" + i +"]/tbody/tr[not (@class)]"));
					
					//List<WebElement> tableRow = tableBody.findElements(By.tagName("tr"));
					List<WebElement> tableRow = tableBody.findElements(By.xpath("/html/body/table["+ i +"]/tbody/tr[not (@class)]/td"));
					
					
					for(WebElement roe: tableRow) {
						checkNumber++;
						dataStorage.add(roe.getText());
						
						if(checkNumber == 8) {
							//System.out.println("Check Number " + checkNumber);
							//System.out.println(dataStorage);
							checkNumber = 0;
							databaseInster();
							//driver.close();
							//System.exit(0);
						}
						//System.out.println(roe.getText());
					}
				//	System.out.println("BreakPoint 2");
					
					
					
					
					
					
					
					
					
					
					/*
					 * Iterator<WebElement> c = tableRow.iterator(); while(c.hasNext()) { WebElement
					 * roe = c.next(); System.out.println(roe.getText()); System.exit(0); }
					 * 
					 */
					
					
					
					/*
					 * Iterator<WebElement> che = tableRow.iterator(); while(che.hasNext()) {
					 * List<WebElement> col =
					 * ((WebElement)che.next()).findElements(By.tagName("tr")); Iterator<WebElement>
					 * che2 = col.iterator(); while(che2.hasNext()) {
					 * System.out.println(((WebElement)che.next()).getText()+ " "); } }
					 */
					
					
					
					
					
					
					/*
					 * rowSize = tableRow.size(); System.out.println("Size of Table Row: " +
					 * rowSize);
					 * 
					 * 
					 * System.out.println("Check Number 1:  " + checkNumber); for (WebElement row :
					 * tableRow) { List<WebElement> col = row.findElements(By.tagName("td"));
					 * colSize = col.size();
					 * 
					 * 
					 * 
					 * for(WebElement column : col) datastorage.add(column.getText());
					 * dataDisplay(); //System.out.println((column.getText())); //Still need to get
					 * the date }
					 * 
					 */
					
					
					
					
					
					//String table = driver.findElement(By.className("spreadsheet")).getText();
					//String table = driver.findElement(By.xpath("/html/body/table["+ i +"]/tbody")).getText(); 	
					
					//System.out.println("Text in cell: " + table);
			
				}
				//If there is nothing on the day then moves on to the next day
				continue;
			}
			else {
				//If there is nothing on this Week then the system doesn't check the whole page and moves on to the next week.
				break;
			}
		}

	driver.findElement(By.xpath("//*[@id=\"bNextWeek\"]")).click();
	Thread.sleep(1000);
	}
	
	
	
	public static void databaseInster() throws SQLException {
		String sql = "INSERT INTO timetable VALUES ( NULL, NULL, '"+dataStorage.get(0)+"', '"+dataStorage.get(1)+"','"+dataStorage.get(2)+"','"+dataStorage.get(3)+"','"+dataStorage.get(4)+"','"+dataStorage.get(5)+"','"+dataStorage.get(6)+"','"+dataStorage.get(7)+"');";
		Statement stmt = null;
		stmt = con.createStatement();	
		stmt.executeUpdate(sql);
		dataStorage.removeAll(dataStorage);
		stmt.close();
		System.out.println("Data Added");
		
	//Issues with this is that it would take long time due to every time this method is called a connection has to be made again 
		
	}
	
	
	
	
	
	
		
}
