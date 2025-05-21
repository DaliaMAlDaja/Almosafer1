package Almosafer_last_edit.Almosafer_last_edit;

import java.text.DateFormat;
import java.time.Duration;
import java.time.LocalDate;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.time.format.DateTimeFormatter;

public class AppTest {

	WebDriver Driver = new ChromeDriver();

	String Expected_Value_For_Hotel_Tab = "false";
	String Expected_Language_En = "en";
	String Expected_Language_Ar = "ar";

	@BeforeTest
	public void My_Setup() {

		String Url = "https://www.almosafer.com/en?ncr=1";
		Driver.get(Url);
		Driver.manage().window().maximize();
		Driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

	}

	@Test(priority = 1, enabled = true)
	public void Verify_Default_Language_Is_English() {

		String Language = Driver.findElement(By.tagName("html")).getDomAttribute("lang");
		String Expected_Language = "en";
		Assert.assertEquals(Language, Expected_Language);
		System.out.println("The Language: " + Language);
	}

	@Test(priority = 2, enabled = true)
	public void Verify_Default_Currency_Is_SAR() {
		WebDriverWait Wait = new WebDriverWait(Driver, Duration.ofSeconds(10));
		WebElement Currency_Element = Wait
				.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".sc-hUfwpO.kAhsZG")));
		String Actual_Currency = Currency_Element.getText();
		String Expected_Currency = "SAR";
		Assert.assertEquals(Actual_Currency, Expected_Currency);

		System.out.println("The Currency: " + Actual_Currency);
	}

	@Test(priority = 3, enabled = true)
	public void Verify_Displayed_Contact_Number_Is_Correct() {
		String Actual_Number = Driver.findElement(By.tagName("strong")).getText();
		String Expected_Number = "+966554400000";

		Assert.assertEquals(Actual_Number, Expected_Number);
		System.out.println("Check Phone Number: " + Actual_Number);
	}

	@Test(priority = 4, enabled = true)
	public void Verify_That_Qitaf_Logo_Is_Displayed_In_Footer() {
		WebElement Footer = Driver.findElement(By.tagName("footer"));

		WebElement Qitaf = Footer.findElement(By.cssSelector(".sc-bdVaJa.bxRSiR.sc-lcpuFF.jipXfR"));

		boolean Qitaf_Logo_Is_Displayed = Qitaf.isDisplayed();

		Assert.assertEquals(Qitaf_Logo_Is_Displayed, true);
		if (Qitaf_Logo_Is_Displayed)
			System.out.println("Qitaf Logo is Displayed");
	}

	@Test(priority = 5, enabled = true)
	public void Verify_Hotel_Tab_Is_Not_Selected_By_Default() {
		WebElement Hotel_Tab_Element = Driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		String Actual_Value_For_Hotel_Tab = Hotel_Tab_Element.getDomAttribute("aria-selected");

		Assert.assertEquals(Actual_Value_For_Hotel_Tab, Expected_Value_For_Hotel_Tab);
		System.out.println("Hotel Tab Value = " + Actual_Value_For_Hotel_Tab);
	}

	@Test(priority = 6, enabled = true)
	public void Verify_Default_Departure_And_Return_Dates_Are_Set() {
		List<WebElement> Dates = Driver.findElements(By.cssSelector(".sc-dXfzlN.iPVuSG"));
		String Actual_Departure_Date = Dates.get(0).getText();
		String Actual_Return_Date = Dates.get(1).getText();

		LocalDate Today = LocalDate.now();
		int Departure_Day = Today.plusDays(1).getDayOfMonth();
		int Return_Day = Today.plusDays(2).getDayOfMonth();

		String Expected_Departure_Date = String.format("%02d", Departure_Day);
		String Expected_Return_Date = String.format("%02d", Return_Day);

		Assert.assertEquals(Actual_Departure_Date, Expected_Departure_Date);
		Assert.assertEquals(Actual_Return_Date, Expected_Return_Date);
	}

	@Test(priority = 7, enabled = true)
	public void Verify_That_Language_Changes_To_Random_Option() {
		String[] Urls = { "https://www.almosafer.com/en?ncr=1", "https://www.almosafer.com/ar/?ncr=1" };
		Random Rand = new Random();
		int Random_Index = Rand.nextInt(Urls.length);
		String Random_Url = Urls[Random_Index];
		Driver.get(Random_Url);

		String Actual_Language = Driver.findElement(By.tagName("html")).getDomAttribute("lang");

		if (Driver.getCurrentUrl().contains("en")) {
			Assert.assertEquals(Actual_Language, Expected_Language_En);
		} else {
			Assert.assertEquals(Actual_Language, Expected_Language_Ar);
		}

		System.out.println("Actual Language = " + Actual_Language);
	}

	@Test(priority = 8, enabled = true)
	public void Search_Hotel_By_Random_City_Based_On_Website_Language() throws InterruptedException

	{
		WebElement Hotel_Tab = Driver.findElement(By.id("uncontrolled-tab-example-tab-hotels"));
		Hotel_Tab.click();

		String[] En_Cities = { "Dubai", "Jeddah", "Riyadh" };
		String[] Ar_Cities = { "دبي", "جدة" };

		Random Rand = new Random();

		String Random_City = Driver.getCurrentUrl().contains("en") ? En_Cities[Rand.nextInt(En_Cities.length)]
				: Ar_Cities[Rand.nextInt(Ar_Cities.length)];

		WebElement Search_Input = Driver.findElement(By.cssSelector(".sc-phbroq-2.uQFRS.AutoComplete__Input"));

		Search_Input.sendKeys(Random_City);
		Thread.sleep(2000);
		Search_Input.sendKeys(Keys.ENTER);
		Search_Input.click();
	}

	@Test(priority = 9, enabled = true)
	public void Select_Hotel_CheckIn_And_CheckOut_With_Custom_Dates() throws InterruptedException {

		WebElement Calendar_Open_Button = Driver.findElement(By.cssSelector(".sc-5uo3xu-1.ckohYI"));
		Calendar_Open_Button.click();

		LocalDate Today = LocalDate.now();
		LocalDate Depart_Date = Today.plusDays(11);
		LocalDate Return_Date = Today.plusDays(15);

		DateTimeFormatter Formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		String Depart_Date_Str = Depart_Date.format(Formatter);
		String Return_Date_Str = Return_Date.format(Formatter);


		WebDriverWait Wait = new WebDriverWait(Driver, Duration.ofSeconds(10));
		Thread.sleep(2000);

		WebElement Depart_Element = Wait.until(ExpectedConditions
				.elementToBeClickable(By.cssSelector("[data-testid='HotelsSearchCalendar__" + Depart_Date_Str + "']")));
		Depart_Element.click();

		Thread.sleep(1000);

		WebElement Return_Element = Wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(
				By.cssSelector("[data-testid='HotelsSearchCalendar__" + Return_Date_Str + "']"))));
		Return_Element.click();

		Thread.sleep(3000);
	}

	@Test(priority = 10, enabled = true)
	public void Select_Number_Of_Guests_And_Rooms() {
		Random Rand = new Random();

		WebElement Room_Selector = Driver.findElement(By.cssSelector(".sc-tln3e3-1.gvrkTi"));
		Room_Selector.click();

		Select Room_Select = new Select(Room_Selector);
		int Random_Option_Index = Rand.nextInt(2);
		Room_Select.selectByIndex(Random_Option_Index);

		WebElement Search_Button = Driver
				.findElement(By.xpath("//button[@data-testid='HotelSearchBox__SearchButton']"));
		Search_Button.click();
	}

	@Test(priority = 11, enabled = true)
	public void Verify_That_Hotel_Search_Results_Are_Displayed() {

		Driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		WebDriverWait Wait = new WebDriverWait(Driver, Duration.ofSeconds(15));

		WebElement Search_Result_Element = Wait.until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath("//span[@data-testid='srp_properties_found']")));

		String Search_Result_Text = Search_Result_Element.getText();

		boolean Actual_Result = Search_Result_Text.contains("stays") || Search_Result_Text.contains("مكان");
		boolean Expected_Result = true;

		Assert.assertEquals(Actual_Result, Expected_Result);
		System.out.println("Search Result: " + Search_Result_Text);
	}
}
