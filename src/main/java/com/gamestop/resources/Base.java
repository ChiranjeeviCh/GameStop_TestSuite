package com.gamestop.resources;



import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class Base {
	
	public static WebDriver driver;
	
	public static Properties properties = null;
	public static String url = null;
	public static String gamstopUrl = null;
	public static String projectpath = System.getProperty("user.dir");
	public String driverLocation = null;
	HashMap<String, String> envConfigData;
	HashMap<String, String> executionConfigData;
	 private static XSSFSheet ExcelWSheet;	 
	 private static XSSFWorkbook ExcelWBook;	 
	 private static String browsername;	  
	 public static ExtentHtmlReporter report;
	 public static ExtentReports extent;
	 public static ExtentTest test;
	 public static HashMap<String, List<String>> configuration;
	 
		static FileInputStream fis;
		static FileOutputStream fout=null;
		static XSSFSheet sheet;
		static XSSFRow row;
		static XSSFCell cell;
	
	public static Logger log = LogManager.getLogger(Base.class.getName());
	
	
   @AfterSuite

    public void ExtentClose() {
    	
          extent.flush();
          Base.closeBrowsers();
    }

    /* Generic in base class for report and screenshot */
   

    public static String screenshot(String shotname) throws IOException {
          TakesScreenshot takeshot = (TakesScreenshot) driver;
          File shotsrc = takeshot.getScreenshotAs(OutputType.FILE);
          String shotpath = System.getProperty("user.dir") + "/screenshot/" + shotname + ".png";
          File shotdestination = new File(shotpath);
          FileUtils.copyFile(shotsrc, shotdestination);
          return shotpath;
    }

    public static void resultDetails(ITestResult result, ExtentTest test) {
          if (result.getStatus() == ITestResult.FAILURE) {
                 test.log(Status.FAIL, MarkupHelper.createLabel(result.getName() + " FAILED ", ExtentColor.RED));
                 test.fail(result.getThrowable());
                 try {
                       String attachName = Base.screenshot(result.getName());
                        test.fail(result.getThrowable().getMessage(),
                              MediaEntityBuilder.createScreenCaptureFromPath(attachName).build());

                 } catch (IOException e) {

                       e.printStackTrace();
                 }

          } else if (result.getStatus() == ITestResult.SUCCESS) {
                 test.log(Status.PASS, MarkupHelper.createLabel(result.getName() + "   PASSED ", ExtentColor.GREEN));
                 
          } else {
                 test.log(Status.SKIP, MarkupHelper.createLabel(result.getName() + "   SKIPPED ", ExtentColor.ORANGE));
                 test.skip(result.getThrowable());
          }
    }

    /*
    * ****************************END**********************************************
    */
    /*
     * ***************************Generic Method**********************************************
     */
     public static void WaitMethod(WebElement element, int time, String type, WebDriver driver) {
           switch (type) {
           case "WaitForObjectToBeClickable":
                  WebDriverWait wait1 = new WebDriverWait(driver, time);
             wait1.until(ExpectedConditions.elementToBeClickable(element));
                  break;

           case "WaitForElementPresent":
                  WebDriverWait wait2 = new WebDriverWait(driver, time);
                  wait2.until(ExpectedConditions.visibilityOf(element));
                  break;

           case "waitForElementToBeDisplayed":
                  WebDriverWait wait3 = new WebDriverWait(driver, time);
             wait3.until(ExpectedConditions.elementToBeClickable(element));
                  element.isDisplayed();
                  break;

           }

     }

     public static void waitTime(int n) {
           long start = new Date().getTime();
           int x = n * 1000;
           while (new Date().getTime() - start < x) {

           }
     }

     public static void rightclick(WebElement element) {
           Actions action = new Actions(driver);
           action.contextClick(element).build().perform();
     }

     public static boolean checkIfElementSelected(WebElement element, int time) {
           try {
                  WaitForElementPresent(element,time);
                  return element.isSelected();
           } catch (Exception e) {
                  return false;
           }
     }

     public static boolean checkIfElementEnabled(WebElement element, int time) {
           try {
                  WaitForElementPresent(element,time);
                  return element.isEnabled();
           } catch (Exception e) {
                  return false;
           }
     }

     public static void refresh() {
           driver.navigate().refresh();
     }

     public static int countofDDOptions(WebElement objname) {

           Select select = new Select((objname));
           List<WebElement> DdOptions = select.getOptions();
           return DdOptions.size();

     }

     public static String firstSelectedDDOptiobns(WebElement objname) {

           Select select = new Select((objname));
           String firstValue = select.getFirstSelectedOption().getText();
           return firstValue;

     }

     public static void Asserting(String type, Object actual, Object expected) {

           switch (type) {
           case "equals":
                  Assert.assertEquals(actual, expected);
                  
                  break;

           case "notEqual":
                  Assert.assertNotEquals(actual, expected);
                  break;
                  
           case "contains":
        	   
        	   Assert.assertTrue(actual.toString().contains(expected.toString()));
        	  
        	   break;
        	   
           

           }

     }
     
     public static void assertElementExistence(String type,WebElement element)
     {
    	 if(type.equalsIgnoreCase("present"))
    	 {
    		 Assert.assertTrue(Base.checkIfElementPresent(element));
    	 }
    	 else 
    	 {
    		 Assert.assertFalse(Base.checkIfElementPresent(element));
    	 }
     }

        public static void textVerfication(WebElement element, String value) {
        Assert.assertTrue(element.getText().equalsIgnoreCase(value));

     }

     public static String alertGetData() {
           Alert alert = driver.switchTo().alert();
           return alert.getText();

     }

      public static void mouseHover(WebElement element) {
           Actions action = new Actions(driver);
           action.moveToElement(element).build().perform();
     }
  	//read file and save it in Hash map
  	public static HashMap<String, List<String>> readFileOnce(String  path,String name) throws FileNotFoundException {
  		HashMap<String, List<String>> data = new LinkedHashMap<>();
  		File file = new File(path);
  		FileInputStream ExcelFile = new FileInputStream(file);
  		new ArrayList<Object>();
  		try

  		{
  			
  			ExcelWBook = new XSSFWorkbook(ExcelFile);
  		} catch (IOException e) {
  			e.printStackTrace();
  		}
  		//ExcelWSheet = ExcelWBook.getSheetAt(0);
  		ExcelWSheet = ExcelWBook.getSheet(name);
  		int Row = ExcelWSheet.getLastRowNum();
  		int Cell = ExcelWSheet.getRow(0).getLastCellNum();
  		int k = 0;
  		
  		
  		for (int i = 0; i < Cell; i++) {
  			List<String> list = new ArrayList<String>();
  			String key = ExcelWSheet.getRow(k).getCell(i).getStringCellValue();
  			for (int j = 1; j <= Row; j++) {
  				

  				XSSFRow row = ExcelWSheet.getRow(j);
  				XSSFCell cell = row.getCell(i);
  				if (cell != null) {
  					if (cell.getCellType() == cell.CELL_TYPE_NUMERIC) {
  						// int value = (int) ExcelWSheet.getRow(i).getCell(j).getNumericCellValue();
  						int value = (int) ExcelWSheet.getRow(j).getCell(i).getNumericCellValue();
  						
  						//String.valueOf(value) ;
  						list.add(String.valueOf(value));

  					} else if (cell.getCellType() == cell.CELL_TYPE_STRING) {
  						list.add(ExcelWSheet.getRow(j).getCell(i).getStringCellValue());
  					}
  				} else {
  					list.add("");
  				}
  				//System.out.println(list);
  			}
  			
  			data.put(key, list);
  		
  		}

  		return data;

  		
  	}
  
     /* ****************************END************************************* */


	
    public static WebDriver initializedriver() throws IOException {
        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(
                     System.getProperty("user.dir") + "/src/main/java/com/gamestop/resources/commondata.properties");
        prop.load(fis);
        browsername = prop.getProperty("browser");
     
        log.info("Browser selected is :" + browsername);
       
        if (browsername.isEmpty()) {
        	if(configuration.get("Browser").get(0).equals("Chrome"))
        	{
        		//Base.test.log(Status.INFO, "Initializing web driver");
        		ChromeOptions options = new ChromeOptions();
                options.addArguments("--disable-notifications");
                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");
                driver = new ChromeDriver(options);	
                //driver = new HtmlUnitDriver(true);
        	}
        	else if(configuration.get("Browser").get(0).equals("FireFox"))
        	{
        		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/drivers/geckodriver.exe");
                driver = new FirefoxDriver();
        	}
               
        } else if (browsername.equalsIgnoreCase("chrome")) {
               ChromeOptions options = new ChromeOptions();
               options.addArguments("--disable-notifications");
               System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");
               driver = new ChromeDriver(options);
        } else if (browsername.equalsIgnoreCase("firefox")) {
               System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/drivers/geckodriver.exe");
               driver = new FirefoxDriver();
        }

        driver.manage().window().maximize();
        return driver;

  }
    public static WebDriver initialiazinDriverForMobile() throws Exception {
			Properties prop = new Properties();
	        FileInputStream fis = new FileInputStream(
	                     System.getProperty("user.dir") + "/src/main/java/com/gamestop/resources/commondata.properties");
	        prop.load(fis);
	        browsername = prop.getProperty("browser");
	     
	        log.info("Browser selected is :" + browsername);
	       
	        if (browsername.isEmpty()) {
	        	if(configuration.get("Browser").get(0).equals("Chrome"))
	        	{
	        		//Base.test.log(Status.INFO, "Initializing web driver");
	        		ChromeOptions options = new ChromeOptions();
	                options.addArguments("--disable-notifications");
	                System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");
	                Map<String, String> mobileEmulation = new HashMap<>();
	       		 mobileEmulation.put("deviceName", "Galaxy S5");
	       		 ChromeOptions chromeOptions = new ChromeOptions();
	       		 chromeOptions.setExperimentalOption("mobileEmulation", mobileEmulation);
	       		 DesiredCapabilities capabilities = DesiredCapabilities.chrome(); 
	       			capabilities.setCapability(ChromeOptions.CAPABILITY, chromeOptions);
	                driver = new ChromeDriver(options);	
	                //driver = new HtmlUnitDriver(true);
	        	}
	        	else if(configuration.get("Browser").get(0).equals("FireFox"))
	        	{
	        		System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/drivers/geckodriver.exe");
	                driver = new FirefoxDriver();
	        	}
	               
	        } else if (browsername.equalsIgnoreCase("chrome")) {
	               ChromeOptions options = new ChromeOptions();
	               options.addArguments("--disable-notifications");
	               System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");
	               driver = new ChromeDriver(options);
	        } else if (browsername.equalsIgnoreCase("firefox")) {
	               System.setProperty("webdriver.gecko.driver", System.getProperty("user.dir") + "/drivers/geckodriver.exe");
	               driver = new FirefoxDriver();
	        }

	        driver.manage().window().maximize();
	        return driver;


    }
    public static void switchToFrame(String frameID) {
		try {
			driver.switchTo().frame(frameID);
			//driver.switchTo().frame(frameElement)
		} catch (Exception e) {
			System.out.println("Main browser has been closed. Unable to switch to frame ."+frameID.toString());
		}
		
	}
	public static void switchToFrameElement(WebElement element) {
		try {
			driver.switchTo().frame(element);
		} catch (Exception e) {
			System.out.println("Main browser has been closed. Unable to switch to frame ");
		}
		
	}
	
		 //This method is to capture the screenshot and return the path of the screenshot.
		 public static String getScreenShot(WebDriver driver, String screenshotName) throws IOException {
		 String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		 TakesScreenshot ts = (TakesScreenshot) driver;
		 File source = ts.getScreenshotAs(OutputType.FILE);
		 // after execution, you could see a folder "FailedTestsScreenshots" under src folder
		 String destination = System.getProperty("user.dir") + "/Screenshots/" + screenshotName + dateName + ".png";
		 File finalDestination = new File(destination);
		 FileUtils.copyFile(source, finalDestination);
		 return destination;
		 }

		 /* Loading all the values from Config properties */	 	
			public Properties loadPropertyFile() throws IOException {
				FileInputStream fileInputStream = new FileInputStream( System.getProperty("user.dir") + "/src/main/java/com/gamestop/resources/commondata.properties");
				properties = new Properties();
				properties.load(fileInputStream);
				return properties;
			}

			/* Loading all the values from Config properties */	 
			public void loadBrowser() {
				try {
					String browser = properties.getProperty("browser");	
					gamstopUrl = System.getProperty("env"); 
					if (browser.equalsIgnoreCase("chrome"))
					{	
						//Create prefs map to store all preferences 
						Map<String, Object> prefs = new HashMap<String, Object>();

						//Put this into prefs map to switch off browser notification
						prefs.put("profile.default_content_setting_values.notifications", 2);

						//Create chrome options to set this prefs
						ChromeOptions options = new ChromeOptions();
						options.setExperimentalOption("prefs", prefs);
						
						  options.addArguments("--disable-notifications");
						  options.setCapability("disable-restore-session-state", true);
						driverLocation = properties.getProperty("driverChromeLocation");
						System.setProperty("webdriver.chrome.driver", projectpath + driverLocation);
						driver = new ChromeDriver(options);		
					}
				}catch(Exception e) {
					e.printStackTrace();
					test.log(Status.FAIL, "StackTrace Result: " + Thread.currentThread().getStackTrace());
					assertTrue(false);
				}

			}

			/* Loading IE browser for chat portal */	
			public void loadIEBrowser(String browser) {
				try {
					if (browser.equalsIgnoreCase("IE"))
					{
						driverLocation = properties.getProperty("driverIELocation");
						System.setProperty("webdriver.ie.driver", projectpath + driverLocation);
						driver = new InternetExplorerDriver();		
					}
					else {
						log.info("No Browser");
					}
				}catch(Exception e) {
					e.printStackTrace();
					test.log(Status.FAIL, "StackTrace Result: " + Thread.currentThread().getStackTrace());
					assertTrue(false);
				}
			}

			/* Capture Screen Shots */	
			public static String takeScreenShot(String filename) 
			{
				String destination= null;
				try {
					String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
					File scr = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
					//destination = projectpath + properties.getProperty("screenshots") + filename + "-" + date + ".png";
					destination = "./ExtentReports/screenshots/" + filename + date + ".png";
					File finalDest= new File(destination); 
					FileUtils.copyFile(scr, finalDest); 
					return "." + destination;
				}catch(Exception e) {
					e.printStackTrace();
					test.log(Status.FAIL, "StackTrace Result: " + Thread.currentThread().getStackTrace());
					return destination;
				}
			}


			
			public static boolean exportData(String path,String sheetName, String colName, int rowNum, String data) {
				try {
					//String path=System.getProperty("user.dir")+"\\Testdata\\ExportExcel.xlsx";
					fis = new FileInputStream(path);
					ExcelWBook = new XSSFWorkbook(fis);

					if (rowNum <= 0)
						return false;

					int index = ExcelWBook.getSheetIndex(sheetName);
					int colNum = -1;
					if (index == -1)
						return false;

					sheet = ExcelWBook.getSheetAt(index);

					row = sheet.getRow(0);
					for (int i = 0; i < row.getLastCellNum(); i++) {
						// System.out.println(row.getCell(i).getStringCellValue().trim());
						if (row.getCell(i).getStringCellValue().trim().equals(colName))
							colNum = i;
					}
					if (colNum == -1)
						return false;

					sheet.autoSizeColumn(colNum);
					row = sheet.getRow(rowNum - 1);
					if (row == null)
						row = sheet.createRow(rowNum - 1);

					cell = row.getCell(colNum);
					if (cell == null)
						cell = row.createCell(colNum);

					// cell style
					// CellStyle cs = workbook.createCellStyle();
					// cs.setWrapText(true);
					// cell.setCellStyle(cs);
					cell.setCellValue(data);

					fout = new FileOutputStream(path);

					ExcelWBook.write(fout);
					System.out.println("data entered successfully");

					fout.close();
					ExcelWBook.close();

				} catch (Exception e) {
					e.printStackTrace();
					return false;
				}
				return true;
			}

			public static int getRowCount(String path,String sheetName) throws IOException {
				fis = new FileInputStream(path);
				ExcelWBook = new XSSFWorkbook(fis);
				int index = ExcelWBook.getSheetIndex(sheetName);
				if (index == -1)
					return 0;
				else {
					sheet = ExcelWBook.getSheetAt(index);
					int number = sheet.getLastRowNum() + 1;
					return number;
				}
				}

			
	  @BeforeSuite 
	  public void extentReportSetup() throws IOException {
	  log.info("GameStop login testcase Execution Started"); 
	  loadPropertyFile();
	  log.info("Loaded config property file"); 
	  report = new
	  ExtentHtmlReporter(projectpath + properties.getProperty("extentReports"));
	  report = new ExtentHtmlReporter("ExtentReports/extentReport.html");
	  report.config().setDocumentTitle(properties.getProperty("documentTitle"));
	  // Title of the Report
	  report.config().setReportName(properties.getProperty("reportName")); //Name	  Of the Report
	  report.config().setTheme(Theme.DARK);
	  extent = new ExtentReports(); 
	  extent.attachReporter(report);
	  extent.setSystemInfo(properties.getProperty("testedBy"),properties.getProperty("testerName")); 
	 // lauchBrowser(); 
	 
	  }
			/* Flush Extent Report */	
			@AfterSuite
			public void flushExtentReport() {
				extent.flush();
				
				driver.quit();
			}
			public static void wait10Seconds() {
				long start = new Date().getTime();
				while (new Date().getTime() - start < 10000) {

				}
			}

			public static void wait1Min() {
				long start = new Date().getTime();
				while (new Date().getTime() - start < 100000) {

				}
			}

			
			public static void getHrefs(List<WebElement> listOfEle,List<String> addToList) throws Exception {
				Base.WaitForListOfElementPresent(listOfEle, 20);
				for(WebElement ele:listOfEle) {
					String href=ele.getAttribute("href");
					addToList.add(href);
					
				}
				
			}
			

			
			
			//@BeforeMethod
			/* Integrating all the functions together to launch the application for test execution */	
			public void lauchBrowser() {			
				try {			
					loadBrowser();		
					log.info("Loaded the Browser");
					driver.manage().window().maximize();
					driver.manage().deleteAllCookies();
					log.info("Maximize the window \n Test case Initiated");
					Thread.sleep(1000);
					driver.get(properties.getProperty("stage"));		
					Thread.sleep(1000);
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);	
					
				}catch(Exception e) {
					e.printStackTrace();
					test.log(Status.FAIL, "StackTrace Result: " + Thread.currentThread().getStackTrace());
					assertTrue(false);
				}
			}


			/* Extent Report TearDown to get the Test case details in the Report */	
			@AfterMethod
			public void extentReportTearDown(ITestResult result) throws IOException, InterruptedException {
				String screenshotPath = takeScreenShot(result.getName());
				if (result.getStatus() == ITestResult.FAILURE) {
					test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getName()); // to add name in extent report
					test.log(Status.FAIL, "TEST CASE FAILED IS " + result.getThrowable()); // to add error/exception in extent report
					test.addScreenCaptureFromPath(screenshotPath);// adding screen shot
				} else if (result.getStatus() == ITestResult.SKIP) {
					test.log(Status.SKIP, "Test Case SKIPPED IS " + result.getName());
				}
				else if (result.getStatus() == ITestResult.SUCCESS) {
					test.log(Status.PASS, "Test Case PASSED IS " + result.getName());
				}

				log.info("Gamestop testcase Execution Completed");			
			}

	//@BeforeMethod
	public static void closeBrowsers(){
		try {
			Runtime.getRuntime().exec("taskkill /F /IM chrome.exe");
			Runtime.getRuntime().exec("taskkill /F /IM firefox.exe");
			Runtime.getRuntime().exec("taskkill /F /IM iexplore.exe");
			Runtime.getRuntime().exec("taskkill /F /IM microsoftedge.exe");
			Runtime.getRuntime().exec("taskkill /F /IM chromedriver.exe");
			Runtime.getRuntime().exec("taskkill /F /IM geckodriver.exe");
			Runtime.getRuntime().exec("taskkill /F /IM IEServerDriver.exe");
			Runtime.getRuntime().exec("taskkill /F /IM edgedriver.exe");
		} catch (IOException e) {
			
			System.out.println("Exception while closing the browsers.");
		}
	}
	
	public String getbaseURL() throws IOException {

        Properties prop = new Properties();
        FileInputStream fis = new FileInputStream(
                     System.getProperty("user.dir") + "/src/main/java/resources/commondata.properties");
        prop.load(fis);
        
        String urlValue =System.getProperty("env");
        String url = null;
        if(urlValue.isEmpty())
        {
        	 
        	if(configuration.get("Env").get(0).equals("stage"))
        	{
        		url = prop.getProperty("stage");
        	}
        	else if(configuration.get("Env").get(0).equals("prod"))
        	{
        		url = prop.getProperty("prod");
        	}
        }
        else 
        {
        	 url = prop.getProperty(System.getProperty("env"));
        }


        return url;
  }

public HashMap<String, String> readAllProperties(String propFile) {
		
		HashMap<String, String> propertyData = new HashMap<>();
		
		try {
			FileInputStream f = new FileInputStream(propFile);
			Properties props = new Properties();
			props.load(f);
			Set<Object> allKeys = props.keySet();
			for (Object key : allKeys) {
				String propVal = props.getProperty(key.toString());
				propertyData.put(key.toString(), propVal);
			   f.close();				
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return propertyData;
	}

		public static void WaitForObjectToBeClickable(WebElement element, int time) throws Exception {
			WebDriverWait wait = new WebDriverWait(driver, time);
			wait.until(ExpectedConditions.elementToBeClickable(element)).click();

		}
		public static void WaitForObjectToBeClick(WebElement element, int time) throws Exception {
			WebDriverWait wait = new WebDriverWait(driver, time);
			wait.until(ExpectedConditions.elementToBeClickable(element));

		}
		public static void WaitForElementPresent(WebElement element, int time) throws Exception {
			try {

				WebDriverWait newWait = new WebDriverWait(driver, time);
				newWait.until(ExpectedConditions.visibilityOf(element));

			} catch (Exception e) {

			}

		} 
		
public static void WaitForListOfElementPresent(List<WebElement> element, int time) throws Exception {
			try {
				WebDriverWait newWait = new WebDriverWait(driver, time);
				for(WebElement e:element) {
				newWait.until(ExpectedConditions.visibilityOf(e));
				}

			} catch (Exception e) {

			}

		} 
		public static void wait20Seconds(){
			long start = new Date().getTime();
			while(new Date().getTime() - start < 20000){

			}		
		}  

		public static void wait5Seconds(){
			long start = new Date().getTime();
			while(new Date().getTime() - start < 5000){

			}		
		}  
		public static void wait2Seconds(){
			long start = new Date().getTime();
			while(new Date().getTime() - start < 2000){

			}		
		}  
				
		public static String  getText(WebElement element,int index)  {
		    WebDriverWait wait=new WebDriverWait(driver,20);
		    wait.until(ExpectedConditions.elementToBeClickable(element));
		    //wait.until(ExpectedConditions.visibilityOfElementLocated((By) element));
		    WebElement result=driver.findElement(By.xpath("(//ul[@class='menu-list level-2 view-more']/li/a//span[@class='category-name '])"+"["+(index+1)+"]"));
		    System.out.println(result.getText());
		    return element.getText();
		}
		
		/*
		 * public void waitforsometime() { Wait wait = new FluentWait(driver)
		 * .withTimeout(100, TimeUnit.MILLISECONDS) .pollingEvery(100,
		 * TimeUnit.MILLISECONDS) .ignoring(Exception.class); }
		 * 
		 */	public static void Click(WebElement objname) throws Exception {
			try {
				WaitForObjectToBeClickable(objname, 300);
				WaitForElementPresent(objname,10);
				//String objText = objname.getText();
				if (objname.isDisplayed())
					objname.click();
				
			} catch (Exception e) {
				System.out.println("element '" + objname + "' is not displayed");
				
				throw e;// Better not no handle the exception still in specific
				// scenario if you are handling the exception then just
				// throw like throw e;
			}
		}
		
		public static boolean checkIfElementPresent(WebElement element){
			try{
				WaitForElementPresent(element,30);
				
				return element.isDisplayed();
			}catch(Exception e){
				e.printStackTrace();
				return false;
			}
		}
		public static void JavaScriptClick(WebElement element)
		{
			JavascriptExecutor executor = (JavascriptExecutor) driver;
			executor.executeScript("arguments[0].click();", element); 

		}
		public static void dobleclick(WebElement element) {
			Actions action = new Actions(driver);
			action.moveToElement(element).build().perform();
			action.doubleClick(element).build().perform();
		}
		public static void SendKeys(WebElement objname, String value) throws Exception {
			try {
				WaitForElementPresent(objname,10);
				// WaitForObjectToBePresent(objname,60);

				objname.sendKeys(value);

			} catch (Exception e) {
				
			
				throw e;// Better not no handle the exception still in specific
				// scenario if you are handling the exception then just
				// throw like throw e;
			}
		}

		/** isElementPresent method */
		public static boolean IsElementPresent(WebElement objname) throws Exception {
			try {
				objname.isDisplayed();
				return true;
			} catch (NoSuchElementException e) {
				return false;

			}
		}
		/**
		 * selectTextFromDropdown method (objname = the object name from objectmap
		 * file)
		 * 
		 * @throws Exception
		 */
		public static void selectTextByVisibletext(WebElement objname, String text) throws Exception {
			try {
				Select select = new Select((objname));

				select.selectByVisibleText(text);

			} catch (Exception e) {
				
				throw e;// Better not no handle the exception still in specific
				// scenario if you are handling the exception then just
				// throw like throw e;
			}

		}
		/**
		 *  Wait for particular constant time.
		 * @param timeInMillis
		 */
		public static void waitConstantTime(int timeInMillis) {
			 try {
					Thread.sleep(timeInMillis);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
		
		public static void scrolldown() {
			Actions actions = new Actions(driver);
			actions.keyDown(Keys.CONTROL).sendKeys(Keys.PAGE_DOWN).perform();
			waitConstantTime(5000);
		}
		public void scroll() {
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("scroll(0,700)");
		}

		
		public static void scrollToElement(WebElement element) {
			JavascriptExecutor jse = (JavascriptExecutor)driver;
			jse.executeScript("arguments[0].scrollIntoView()",element);
			
		}
		public static void scrollToElementAction(WebElement element) {
			Actions a = new Actions(driver);
			a.moveToElement(element).perform();
			
		}
		
		public static void scrollToElementAndClick(WebElement element) {
			Actions a = new Actions(driver);
			a.moveToElement(element).click().perform();
			}

		
		public void clickTabtitle(List<WebElement> anchors,String title) {
	        Iterator<WebElement> i = anchors.iterator();
	        while(i.hasNext()) {
	            WebElement anchor = i.next();
	            if(anchor.getAttribute("title").contains(title)) {
	                anchor.click();
	                break;
	            }
	        }
	    }
		
		public void clickTabLink(List<WebElement> tabs) {
			Iterator<WebElement> i=tabs.iterator();
			while(i.hasNext()) {
				WebElement tab=i.next();
				
				tab.click();
			}
		}
		public static void waitForLoad(WebDriver driver) {
	         ExpectedCondition<Boolean> pageLoadCondition = new
	                 ExpectedCondition<Boolean>() {
	                     public Boolean apply(WebDriver driver) {
	                         return ((JavascriptExecutor)driver).executeScript("return document.readyState").equals("complete");
	                     }
	                 };
	         WebDriverWait wait = new WebDriverWait(driver, 200);
	         wait.until(pageLoadCondition);
	     }
		
		
		// method will scroll page end
			public static void scrolltopageend() throws Exception {
				try {
					Actions actions = new Actions(driver);
					actions.keyDown(Keys.CONTROL).sendKeys(Keys.END).perform();
				
					waitConstantTime(5000);
					
				} catch (Exception e) {
					
					e.printStackTrace();
				}

			}
			public void scrollToPageUp() throws Exception {
				try {
					Actions actions = new Actions(driver);
					actions.keyDown(Keys.CONTROL).sendKeys(Keys.HOME).perform();
					waitConstantTime(5000);
					
				} catch (Exception e) {
					
					e.printStackTrace();
				}

			}
			/**
			 * used for creating a new folder if the folder is not exists in given path
			 * @param path Absolute path of the folder that needs to created
			 * @author VIKRAM
			 */
			public static void createFolder(String path){
				File f = new File(path);
				
				if (!f.exists()){
					f.mkdir();
				}
				
			}
			/**
			 *  To  deleting the all files inside the given  folder.
			 * 
			 * @param foldPath absolute path of the folder where the files need 
			 */
			public static void deleteFiles(String foldPath) {
				File f = new File(foldPath);		
				if (f.exists() && f.isDirectory()){			
					File[] allFiles = f.listFiles();
					for (File file:allFiles){				
						try{			
							file.delete();				
						} catch(Exception E){
							System.out.println("Exception while deleting the file : " + file.getName());
						}
					}
				
				}
			}
			
			public static HashMap<String, String> readPropertiesToMap(String propertyFile){
				HashMap<String, String> propertyData=new HashMap<>();
				
				try {
					FileInputStream fi=new FileInputStream(propertyFile);
					Properties propFile=new Properties();
					propFile.load(fi);
					
					Set<Object> allkeys=propFile.keySet();
					for(Object key:allkeys) {
						String propValue=propFile.getProperty(key.toString());
						propertyData.put(key.toString(), propValue);
					}
					
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return propertyData;
				
			}
			
			public static void scrollIntoMiddle(WebElement element)
			{
				try
				{
					String scrollElementIntoMiddle = 
							"var viewPortHeight = Math.max(document.documentElement.clientHeight, window.innerHeight || 0);"
							+ "var elementTop = arguments[0].getBoundingClientRect().top;window.scrollBy(0, elementTop-(viewPortHeight/2));";		
					((JavascriptExecutor) driver).executeScript(scrollElementIntoMiddle, element);
					
					//element.click();
				}
				catch (Exception ex)
				{
					
				}
			} 
			
			
	}