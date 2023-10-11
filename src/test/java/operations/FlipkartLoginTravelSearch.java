package operations;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class FlipkartLoginTravelSearch {
    public static void main(String[] args) {
        // Set the path to your ChromeDriver executable
        System.setProperty("webdriver.chrome.driver", "testoutput/chromedriver.exe");

        // Initialize a new instance of the ChromeDriver
        WebDriver driver = new ChromeDriver();

        // Open the Flipkart website
        driver.get("https://www.flipkart.com");

        // Locate the login elements and interact with them
        WebElement flights = driver.findElement(By.xpath("/html/body/div[1]/div/div[1]/div/div/div/div/div[1]/div/div[1]/div/div[2]/div[1]/div/div[1]/div/div/div/div/div[1]/a[9]/div/div/span/span"));
        flights.click();

        WebElement roundTripOption = driver.findElement(By.xpath("/html/body/div/div/div[2]/div[1]/div/div[2]/div/div[1]/div/div/label[2]/div[1]"));
        roundTripOption.click();

        // Select 'From' and 'To' locations and dates
        WebElement fromLocation = driver.findElement(By.xpath("/html/body/div/div/div[2]/div[1]/div/div[2]/div/div[2]/form/div/div[1]/div[1]/div[1]/div[1]/input"));
        fromLocation.sendKeys("New York"); // Replace with your desired location

        WebElement toLocation = driver.findElement(By.xpath("/html/body/div/div/div[2]/div[1]/div/div[2]/div/div[2]/form/div/div[1]/div[3]/div[1]/div[1]/input"));
        toLocation.sendKeys("Los Angeles"); // Replace with your desired location

        WebElement departureDate = driver.findElement(By.xpath("/html/body/div/div/div[2]/div[1]/div/div[2]/div/div[2]/form/div/div[3]/div[1]/div[2]/div/div/div/div/table[1]/tbody/tr[2]/td[5]/div/button"));
        departureDate.sendKeys("2023-11-15"); // Replace with your desired date

        WebElement returnDate = driver.findElement(By.xpath("/html/body/div/div/div[2]/div[1]/div/div[2]/div/div[2]/form/div/div[3]/div[3]/div/input"));
        returnDate.sendKeys("2023-11-20"); // Replace with your desired date

        // Click on the search button
        WebElement searchButton = driver.findElement(By.xpath("/html/body/div/div/div[2]/div[1]/div/div[2]/div/div[2]/form/div/button/svg/g/path[2]"));
        searchButton.click();

        // Wait for the search results to load (adjust the timeout as needed)
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("flight_results")));

        // Get the prices of the outbound and return flights
        WebElement outboundPriceElement = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div[2]/div[2]/div/div[1]/div[1]/div/div[1]/div/div[2]/div[2]/div[2]/span[2]"));
        WebElement returnPriceElement = driver.findElement(By.xpath("/html/body/div/div/div[2]/div/div[2]/div[2]/div[2]/div/div[1]/div[2]/div/div[1]/div/div[2]/div[2]/div[2]/span[2]"));

        // Extract the prices as integers
        int outboundPrice = Integer.parseInt(outboundPriceElement.getText().replaceAll("[^0-9]", ""));
        int returnPrice = Integer.parseInt(returnPriceElement.getText().replaceAll("[^0-9]", ""));

        // Calculate the total price
        int totalExpectedPrice = outboundPrice + returnPrice;

        // Get the total price displayed on the page
        WebElement totalFlightPriceElement = driver.findElement(By.id("total_flight_price"));
        int totalFlightPrice = Integer.parseInt(totalFlightPriceElement.getText().replaceAll("[^0-9]", ""));

        // Validate if the calculated total price matches the displayed total price
        if (totalExpectedPrice == totalFlightPrice) {
            System.out.println("Test Passed: Total flight price is correct.");
        } else {
            System.out.println("Test Failed: Total flight price is incorrect.");
        }

        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Close the browser
        driver.quit();
    }
}
