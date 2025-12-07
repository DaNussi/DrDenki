package net.nussi.denki;

import org.apache.commons.cli.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.net.Urls;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URI;
import java.net.URL;

public class DrDenki {
    private final Logger log = LoggerFactory.getLogger(getClass());

    public static void main(String[] args) {
        new DrDenki(args);
    }

    public DrDenki(String[] args) {

        Options options = new Options();
        options.addOption("h", "help", false, "show help");
//        options.addOption("v", "version", false, "show version");
        options.addOption(Option.builder()
                .longOpt("selenium-grid-url")
                .hasArg()
                .desc("Selenium Grid URL (default: localhost)")
                .build()
        );

        CommandLineParser parser = new DefaultParser();
        CommandLine commandLine;
        try {
            commandLine = parser.parse(options, args);
        } catch (ParseException e) {
            log.error(e.getMessage());
            return;
        }

        if (commandLine.hasOption("h") || commandLine.getOptions().length == 0) {
            HelpFormatter formatter = new HelpFormatter();
            formatter.printHelp("DrDenki.jar", options);
            return;
        }


        URL seleniumUrl;
        try {
            String seleniumUrlText = commandLine.getOptionValue("selenium-grid-url", "localhost");
            seleniumUrl = Urls.from(seleniumUrlText).toURL();

        } catch (Exception e) {
            log.error("Invalid Selenium Grid URL", e);
            return;
        }
        log.info("Selenium Grid url: {}", seleniumUrl);


        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability("browserVersion", "142.0");
        chromeOptions.setCapability("browserName", "chrome");
        chromeOptions.setCapability("platformName", "linux");
        chromeOptions.setCapability("se:screenResolution", "1920x1080");
        chromeOptions.addArguments("--kiosk");
        log.info("Chrome Options: {}", chromeOptions);


        log.info("Connecting to Selenium Grid...");
        RemoteWebDriver driver = new RemoteWebDriver(seleniumUrl, chromeOptions, false);
        log.info("Connection successful");


         driver.get();



        log.info("Closing the driver");
        driver.quit();

        log.info("Bye!");
    }

}
