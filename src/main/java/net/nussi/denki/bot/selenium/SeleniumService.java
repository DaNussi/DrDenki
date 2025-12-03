package net.nussi.denki.bot.selenium;

import lombok.extern.slf4j.Slf4j;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;

@Service
@Slf4j
public class SeleniumService implements InitializingBean, AutoCloseable {

    @Autowired
    private SeleniumConfig config;

    private final LinkedList<WebDriver> drivers = new LinkedList<>();

    public WebDriver getDriver() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability("browserVersion", "142.0");
        chromeOptions.setCapability("browserName", "chrome");
        chromeOptions.setCapability("platformName", "linux");

        WebDriver driver = new RemoteWebDriver(config.getUrl(), chromeOptions);
        drivers.add(driver);
        return driver;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        if(this.config.getUrl() == null) {
            log.error("Selenium config url is missing");
        }
    }

    @Override
    public void close() throws Exception {
        for (WebDriver driver : drivers) {
            driver.close();
        }
    }
}
