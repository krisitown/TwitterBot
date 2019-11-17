import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.util.*;

public class Main {
    private static List<String> everyProfile = Arrays.asList("googledevs",
            "Oracle", "VMware", "AndroidDev", "toptal", "treehouse", "mcuban", "garyvee");

    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        //options.setHeadless(true);
        options.addArguments("user-agent=Mozilla/5.0 (Windows NT 5.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/57.0.2987.133 Safari/537.36");

        WebDriver driver = new ChromeDriver(options);
        driver.get("https://twitter.com/home");
        driver.findElement(By.cssSelector("input.js-username-field.email-input.js-initial-focus")).sendKeys(args[0]);
        driver.findElement(By.cssSelector("input.js-password-field")).sendKeys(args[1]);
        driver.findElement(By.cssSelector("button.submit")).click();

        Collections.shuffle(everyProfile);
        for(String profile : everyProfile.subList(0, 4)){
            driver.get("https://twitter.com/" + profile + "/followers");
            int numberToFollow = (int)(Math.random()*3 + 3);
            delay();
            List<WebElement> elements = driver.findElements(By.cssSelector("div[role=button] div[role=button]"));
            numberToFollow = Math.min(numberToFollow, elements.size());
            for(WebElement element : elements.subList(0, numberToFollow)){
                if(element != null){
                    try {
                        element.click();
                        System.out.println("Followed someone from " + profile + "'s profile!");
                    } catch (Exception ex){
                        ex.printStackTrace();
                    }
                }
            }
            delay();
        }

        driver.quit();

        System.out.println("Done");
    }

    public static void delay(){
        try {
            Thread.sleep((int)(Math.random()*2000 + 1000));
        } catch (InterruptedException ex){
            ex.printStackTrace();
        }
    }
}
