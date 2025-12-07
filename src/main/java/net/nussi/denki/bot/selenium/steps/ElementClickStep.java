package net.nussi.denki.bot.selenium.steps;

import lombok.extern.slf4j.Slf4j;
import net.nussi.denki.bot.selenium.SeleniumSession;
import net.nussi.denki.bot.workflow.step.Step;
import net.nussi.denki.bot.workflow.task.TaskResult;
import net.nussi.denki.bot.workflow.workflow.WorkflowContext;
import org.openqa.selenium.By;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

@Slf4j
public class ElementClickStep<C extends SeleniumSession> extends Step<C> {
    private final String xpath;

    public ElementClickStep(String xpath) {
        super("Clicking on \"" + xpath + "\"", "Selenium (element click)");
        this.xpath = xpath;
    }

    @Override
    protected TaskResult run(WorkflowContext<C> context) throws Exception {
        if(context.data.driver == null) return TaskResult.failure(new Exception("Driver is null. Did you forget to connect?"));

        context.data.driver.findElement(By.xpath(this.xpath)).click();

        return TaskResult.success();
    }
}
