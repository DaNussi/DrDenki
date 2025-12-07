package net.nussi.denki.bot.selenium.steps;

import lombok.extern.slf4j.Slf4j;
import net.nussi.denki.bot.selenium.SeleniumSession;
import net.nussi.denki.bot.workflow.step.Step;
import net.nussi.denki.bot.workflow.task.TaskResult;
import net.nussi.denki.bot.workflow.workflow.WorkflowContext;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

@Slf4j
public class ElementWaitStep<C extends SeleniumSession> extends Step<C> {
    private final String xpath;
    private final Duration timeout;

    public ElementWaitStep(String xpath, Duration timeout) {
        super("Clicking on \"" + xpath + "\"", "Selenium (element wait)");
        this.xpath = xpath;
        this.timeout = timeout;
    }

    @Override
    protected TaskResult run(WorkflowContext<C> context) throws Exception {
        if(context.data.driver == null) return TaskResult.failure(new Exception("Driver is null. Did you forget to connect?"));

        WebDriverWait wait = new WebDriverWait(context.data.driver, this.timeout);
        wait.until((d) -> !d.findElements(By.xpath(this.xpath)).isEmpty());

        return TaskResult.success();
    }
}
