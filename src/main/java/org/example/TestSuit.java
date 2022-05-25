package org.example;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;

public class TestSuit {
    protected static WebDriver driver;
   @Test
   public void userShouldBeAbleToLoginPage()
   {// click on login button
    clickOnElement(By.className("ico-login"));
    String actualLoginUrl = driver.getCurrentUrl();
    Assert.assertTrue(actualLoginUrl.contains("login"),"your login url does not contain register word");
   }

    @Test
    public void RegisterUserShouldBeAbleToSendEmailToFriendForReferProduct(){
       userShouldBeAbleToRegisterPage();
       //click on continue//
        clickOnElement(By.xpath("//a[@class='button-1 register-continue-button']"));
        //click on computer
        clickOnElement(By.linkText("Computers"));
        //click desktop
        clickOnElement(By.linkText("Desktops"));
        //click build your own computer
        clickOnElement(By.linkText("Build your own computer"));
        //click email a friend button//
        clickOnElement(By.xpath("//button[@class='button-2 email-a-friend-button']"));
        //enter email//
        typeText(By.id("FriendEmail"),"pgiya@gmail.com");
        //enter message//
        typeText(By.id("PersonalMessage"),"This is good computer" );
        //click send email button//
        clickOnElement(By.xpath("//button[@class='button-1 send-email-a-friend-button']"));
        //assert  result verification //
        String ExpectedMessage = "Your message has been sent.";
        String ActualMessage = driver.findElement(By.className("result")).getText();
        Assert.assertEquals(ActualMessage,ExpectedMessage,"Message has not been sent successfully");

   }
    @Test
    public void userShouldBeAbleToSelectProductAndPutAddToCart(){
       //click on computer
       clickOnElement(By.linkText("Computers"));
       //click desktop
       clickOnElement(By.linkText("Desktops"));
       //click build your own computer
       clickOnElement(By.linkText("Build your own computer"));
       //select processor
        Select Processor = new Select(driver.findElement(By.id("product_attribute_1")));
        Processor.selectByValue("1");
        //select ram
        Select Ram = new Select(driver.findElement(By.id("product_attribute_2")));
        Ram.selectByValue("3");
        //select HDD//
        driverWaitForElementTobeClickable(By.id("product_attribute_3_6"),10);
        //select OS//
        clickOnElement(By.id("product_attribute_4_9"));
        //select software//

        clickOnElement(By.id("product_attribute_5_11"));
        clickOnElement(By.id("product_attribute_5_12"));
//        Select software = new Select(driver.findElement(By.id("product_attribute_5_11")));
//        software.selectByValue("11");
//        Select Software = new Select(driver.findElement(By.id("product_attribute_5_12")));
//        Software.selectByValue("12");
        //click addToCard //
        clickOnElement(By.id("add-to-cart-button-1"));

        //click shopping cart//
        clickOnElement(By.xpath("//span[@class='cart-label']"));

        //check shoppingCart url//
        String actualUrl=driver.getCurrentUrl();
        Assert.assertTrue(actualUrl.contains("cart"),"your shopping cart url does not contain cart word");

        //check shoppingCart page//
        String expectedMessage = "Shopping cart";
        String actualMessage = getTextFromElement(By.className("page-title"));
        Assert.assertEquals(actualMessage,expectedMessage);

        //check Build your own computer product available in cart//
        String ExpectedMessage = "Build your own computer";
        String ActualMessage = getTextFromElement(By.className("product-name"));
        Assert.assertEquals(ActualMessage,ExpectedMessage);
        }

        @Test
    public void userShouldBeAbleToSeePriceAccordingByTheirSelectedCurrency(){
      clickOnElement(By.id("customerCurrency"));
     Select Currency = new Select(driver.findElement(By.id("customerCurrency")));
     Currency.selectByVisibleText("US Dollar");
            //Verify if build your own computer price is display in Dollar(Assert Point)
            String actualMessage = getTextFromElement(By.className("$1,200.00"));
           String expectedMessage = "$1,200.00";
           Assert.assertEquals(actualMessage,expectedMessage,"price does not show in USDollar");

            //Change the dollar to euro//
            clickOnElement(By.id("customerCurrency"));
            Select currency = new Select(driver.findElement(By.id("customerCurrency")));
            currency.selectByVisibleText("Euro");
            //clickOnElement(By.xpath("//*[@id=\"customerCurrency\"]option[2]"));

            //Verify if build your own computer price is display in euro (Assert Point)
            String ActualMessage =getTextFromElement(By.xpath(("//span[contain(text(),'€1032.00')]")));
            String ExpectedMessage = "€1032.00";
            Assert.assertEquals(ActualMessage,ExpectedMessage,"price does not show in Euro");


    }


     @Test
        public void userShouldBeAbleToRegisterPage(){
       // click on register button
        clickOnElement(By.className("ico-register"));
        String actualRegistrationUrl = driver.getCurrentUrl();

        Assert.assertTrue(actualRegistrationUrl.contains("register"),"your register url does not contain register word");

        //select male or female
        clickOnElement(By.xpath("//input[@id=\"gender-male\"]"));

        //enter first name
        //driver.findElement(By.className("ico-register")).click();
        typeText(By.xpath("//input[@name='FirstName']"),"Automation");

        //enter lastname
       //driver.findElement(By.xpath("//input[@name='FirstName']")).sendKeys("Automation");
        typeText(By.id("LastName"),"patel");
        //driver.findElement(By.id("LastName")).sendKeys("patel");


        //select date of birthday
        Select birthDay = new Select(driver.findElement(By.name("DateOfBirthDay")));
        birthDay.selectByVisibleText("10");

        //select month
        Select birthMonth = new Select(driver.findElement(By.name("DateOfBirthMonth")));
        birthMonth.selectByValue("5");

        //select year
        Select birthYear = new Select(driver.findElement(By.name("DateOfBirthYear")));
        birthYear.selectByVisibleText("1990");

        //email
        typeText(By.id("Email"),"pragnagediya"+randomDate()+"@gmail.com");
        //driver.findElement(By.id("Email")).sendKeys("pragnagediyad@gmail.com");
        //password
        typeText(By.id("Password"),"Sit@1117");
        //driver.findElement(By.id("Password")).sendKeys ( "Sit@1117");
        //confirm password
        typeText(By.id("ConfirmPassword"),"Sit@1117");
        //driver.findElement(By.id("ConfirmPassword")).sendKeys( "Sit@1117");
        //click register
        //clickOnElement(By.name("register-button"));
        driverWaitForElementTobeClickable( By.id("register-button"),10);
         String expectedMessage = "Your registration completed";
//        String actualMessage = "Your registration completed";
        String actualMessage = getTextFromElement(By.xpath("//div[@class='result']"));
        Assert.assertEquals(actualMessage, expectedMessage, "Registration is not successful");}
        //driver.quit();}
    //driver.findElement(By.name("register-button")).click();


  //<<<<<<<<<<<<<<<<<<<<<<After method>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>....
   //@AfterMethod
    //public void closeBrowser()
  // {
      //  driver.quit();
    //}

    //<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<Before method>>>>>>>>>>>>>>>>>>>>>>>>>>>>
    @BeforeMethod
    public void openBrowser()
    {
        System.setProperty("webdriver.chrome.driver","src/test/java/driver/chromedriver.exe");
      //open Chrome browser
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://demo.nopcommerce.com/");
    }
        //driver.findElement(By.name("register-button")).click();


    //user defined method for send keys
    public static void typeText(By by,String text){
        driver.findElement(by).sendKeys(text);}

    //user defined method for click element
    public static void clickOnElement(By by)
    { driver.findElement(by).click();}

    //user defined method for getTextElement
    public static String getTextFromElement(By by){
        return driver.findElement(by).getText(); }

    //get randomDate method
    public static String randomDate(){
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyyyyHHmmss");
        return formatter.format(date);}

    //web driver WebDriverWait methods//
    public static void driverWaitForElementTobeClickable(By by , int time){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(time));
        wait.until(ExpectedConditions.elementToBeClickable(by)).click();}


    //user defined method for driverWaitElementToBESelected//
    public static void driverWaitElementToBESelected(int time ,By by){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(time));
        wait.until(ExpectedConditions.elementToBeSelected(by));}

    //user defined method for driverWaitUrlContains//
    public static void driverWaitUrlContains(int time,By by,String urlName){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(time));
        wait.until(ExpectedConditions.urlContains(urlName));}

    //user defined method for driverWaitAttributeContains//
    public static void driverWaitAttributeContains(int time,String Attribute,String value,By by){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(time));
        wait.until(ExpectedConditions.attributeContains(by,Attribute,value));}

    //user defined method for driverWaitAttributeToBeNotEmpty//
    public static void driverWaitAttributeToBeNotEmpty(int time, WebElement element,String text){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(time));
        wait.until(ExpectedConditions.attributeToBeNotEmpty(element,text));}

    //user defined method for driverWaitTextToBe//
    public static void driverWaitTextToBe(By by,String value,int time){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(time));
        wait.until(ExpectedConditions.textToBe(by,value));}

    //user defined method for driverWaitAlertsPresent//
    public static void driverWaitAlertsPresent(int time){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(time));
        wait.until(ExpectedConditions.alertIsPresent());}

    //user defined method for driverWaitInvisibilityOfWebElement//
    public static void driverWaitInvisibilityOfWebElement(int time,WebElement element){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(time));
        wait.until(ExpectedConditions.invisibilityOfAllElements(element));}

    //user defined method for driverWaitPresenceOfWebElement//
    public static void driverWaitPresenceOfWebElement(int time,By by){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(time));
        wait.until(ExpectedConditions.presenceOfElementLocated(by));}

    //user defined method for driverWaitTitleContains//
    public static void driverWaitTitleContains(int time,String name){
       WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(time));
       wait.until(ExpectedConditions.titleContains(name));}

    //user defined method for driverWaitUrlContains//
    public static void driverWaitUrlContains(int time,String fraction){
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(time));
        wait.until(ExpectedConditions.urlContains(fraction));}


    }








