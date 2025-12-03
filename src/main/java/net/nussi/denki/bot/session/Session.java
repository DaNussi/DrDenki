package net.nussi.denki.bot.session;


import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import net.nussi.denki.Credentials;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.UUID;

@Slf4j
public class Session implements AutoCloseable {
    private static final String LOGIN_BUTTON_XPATH = "//button[text()=\"Mit TU Wien SSO anmelden\"]";

    private final WebDriver driver;
    private final Credentials credentials;

    @Getter
    private final UUID id;

    public Session(WebDriver driver, Credentials credentials) {
        this.driver = driver;
        this.credentials = credentials;
        this.id = UUID.randomUUID();
    }

    public void start() {
        log.info("Session {} logging in.", id);


        log.info("Session {} navigating to \"https://memory.iguw.tuwien.ac.at/login\"", id);
        this.driver.get("https://memory.iguw.tuwien.ac.at/login");

        WebDriverWait wait = new WebDriverWait(driver, java.time.Duration.ofSeconds(10));

        wait.until((driver) ->
           !driver.findElements(By.xpath(LOGIN_BUTTON_XPATH)).isEmpty()
        );

        driver.findElement(By.xpath(LOGIN_BUTTON_XPATH)).click();
    }

    @Override
    public void close() throws Exception {
        this.driver.quit();
    }
}
