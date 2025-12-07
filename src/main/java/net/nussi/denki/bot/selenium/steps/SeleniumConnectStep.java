package net.nussi.denki.bot.selenium.steps;

import lombok.extern.slf4j.Slf4j;
import net.nussi.denki.bot.selenium.SeleniumConfig;
import net.nussi.denki.bot.selenium.SeleniumSession;
import net.nussi.denki.bot.workflow.step.Step;
import net.nussi.denki.bot.workflow.task.TaskResult;
import net.nussi.denki.bot.workflow.workflow.WorkflowContext;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

@Slf4j
public class SeleniumConnectStep<C extends SeleniumSession> extends Step<C> {
    private final SeleniumConfig config;

    public SeleniumConnectStep(SeleniumConfig config) {
        super("Connect to selenium", "Selenium (connect)");
        this.config = config;
    }

    @Override
    protected TaskResult run(WorkflowContext<C> context) throws Exception {
        log.info("{}Connectiong...", context.loggingPrefix());

        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setCapability("browserVersion", "142.0");
        chromeOptions.setCapability("browserName", "chrome");
        chromeOptions.setCapability("platformName", "linux");
        chromeOptions.setCapability("se:screenResolution", "1920x1080");
        chromeOptions.addArguments("--kiosk");

        RemoteWebDriver driver = new RemoteWebDriver(config.getUrl(), chromeOptions);
        context.data.driver = driver;


        log.info("{}Connected! {}/ui/#/session/{}", context.loggingPrefix(), config.getUrl(), context.data.driver.getSessionId());
        return TaskResult.success();
    }
}
