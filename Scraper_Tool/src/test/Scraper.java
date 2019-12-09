package test;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;
import java.util.Date;

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
import java.text.ParseException;
import java.text.SimpleDateFormat;  

public class Scraper {
	public static ArrayList<String> dataStorage = new ArrayList<>();
	public static boolean weekChecker;
	static Connection con;
	static int checkNumber = 0;
	static String nextDate;
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
		String weeksDate = driver.findElement(By.xpath("/html/body/table[1]/tbody/tr[2]/td/table/tbody/tr/td[3]/span[4]")).getText();
		System.out.println(weeksDate);
		String [] sp = weeksDate.split("-");
		String new_Format = "dd-MM-yyyy";

		SimpleDateFormat formatter = new SimpleDateFormat("dd MMMM yyyy");
		try {
			Date d = formatter.parse(sp[0]);
			formatter.applyPattern(new_Format);
			nextDate = formatter.format(d);
			System.out.println(nextDate);

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}



		for(int i=2; i< 7;i++) {





			System.out.println("Point 1");




			driver.manage().timeouts().implicitlyWait(0, TimeUnit.MILLISECONDS);
			Boolean isPresent = driver.findElements(By.xpath("/html/body/table["+i+"]/tbody/tr[1]")).size() > 0;


			//	System.out.println("Break Point 1");

			driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
			//System.out.println(isPresent);
			if(isPresent) {

				System.out.println("Point 2");
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
						checkNumber = 0;
						databaseInster();
					}

				}






			}
			//If there is nothing on the day then moves on to the next day
			System.out.println("----------------------------------------------");



			Calendar c = Calendar.getInstance();
			try {
				SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
				c.setTime(sdf.parse(nextDate));
				c.add(Calendar.DATE, 1);
				nextDate = sdf.format(c.getTime());
				System.out.println(nextDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			continue;
		}



		driver.findElement(By.xpath("//*[@id=\"bNextWeek\"]")).click();
		Thread.sleep(1000);
	}



	public static void databaseInster() throws SQLException {
		String sql = "INSERT INTO timetable VALUES ( NULL,'"+nextDate+"',  '"+dataStorage.get(0)+"', '"+dataStorage.get(1)+"','"+dataStorage.get(2)+"','"+dataStorage.get(3)+"','"+dataStorage.get(4)+"','"+dataStorage.get(5)+"','"+dataStorage.get(6)+"','"+dataStorage.get(7)+"');";
		Statement stmt = null;
		stmt = con.createStatement();	
		stmt.executeUpdate(sql);
		dataStorage.removeAll(dataStorage);
		stmt.close();
		System.out.println("Data Added");

		//Issues with this is that it would take long time due to every time this method is called a connection has to be made again 

	}







}
