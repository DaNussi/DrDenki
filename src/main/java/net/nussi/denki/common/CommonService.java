package net.nussi.denki.common;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CommonService {

    public void navigateToUrl(WebDriver driver, String url) {
        driver.get(url);
        log.info("Navigating to {}", url);
    }

    public void waitForElement(WebDriver driver, By locator, long timeoutMillis) throws TimeoutException {
        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofMillis(timeoutMillis));
        log.debug("Waiting for {}", locator);
        wait.until((d) -> !d.findElements(locator).isEmpty());
    }

    public void waitForElement(WebDriver driver, ElementConfig element) throws TimeoutException {
        waitForElement(driver, By.xpath(element.getXpath()), element.getTimeout());
    }

    public void writeElement(WebDriver driver, ElementConfig element, String text) throws TimeoutException {
        waitForElement(driver, element);
        log.info("Writing element {}", By.xpath(element.getXpath()));
        driver.findElement(By.xpath(element.getXpath())).sendKeys(text);
    }

    public void clickElement(WebDriver driver, ElementConfig element) throws TimeoutException {
        waitForElement(driver, element);
        log.info("Clicking element {}", By.xpath(element.getXpath()));
        driver.findElement(By.xpath(element.getXpath())).click();
    }
}
