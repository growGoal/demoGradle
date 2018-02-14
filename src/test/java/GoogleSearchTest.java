import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.annotations.AfterTest;
import org.testng.Assert;
import static com.thoughtworks.selenium.SeleneseTestNgHelper.assertEquals;




public class GoogleSearchTest {

    private WebDriver driver;

    @BeforeTest
    public void setUp() {
        driver = new FirefoxDriver();
        driver.manage().window().maximize();
        driver.get("http://www.google.com");

    }

    @Test
    public void testGoogleSearch() {
        JSONReadFromFile obj = new JSONReadFromFile();
        for (String ob : obj.getWords("seleniumWords.json")) {
            WebElement element = driver.findElement(By.name("q"));
            element.clear();
            element.sendKeys(ob);
            element.submit();

            new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver d) {
                    return d.getTitle().toLowerCase()
                            .startsWith("selenium testing tools cookbook");
                }
            });

            assertEquals("Selenium testing tools cookbook - Google Search",
                    driver.getTitle());
        }
    }

    @Test
    public void testNGGoogleSearch() {
        JSONReadFromFile obj = new JSONReadFromFile();
        for (String ob : obj.getWords("testNGWords.json")) {
            WebElement element = driver.findElement(By.name("q"));
            element.clear();
            element.sendKeys(ob);
            element.submit();
            new WebDriverWait(driver, 10).until(new ExpectedCondition<Boolean>() {
                public Boolean apply(WebDriver d) {
                    return d.getTitle().toLowerCase()
                            .startsWith("TestNG");
                }
            });
            Assert.assertEquals("Selenium testing tools cookbook - Google Search",
                        driver.getTitle(), "Test has failed");
        }
    }

    @AfterTest
    public void tearDown() throws Exception {
        // Close the browser
        driver.quit();
    }
}
