package net.nussi.denki.bot.selenium.steps;

import lombok.extern.slf4j.Slf4j;
import net.nussi.denki.bot.selenium.SeleniumSession;
import net.nussi.denki.bot.workflow.step.Step;
import net.nussi.denki.bot.workflow.task.TaskResult;
import net.nussi.denki.bot.workflow.workflow.WorkflowContext;
import org.openqa.selenium.By;

@Slf4j
public class ElementWriteStep<C extends SeleniumSession> extends Step<C> {
    private final String xpath;
    private final String text;

    public ElementWriteStep(String xpath, String text) {
        super("Sending text to \"" + xpath + "\"", "Selenium (element write)");
        this.xpath = xpath;
        this.text = text;
    }

    @Override
    protected TaskResult run(WorkflowContext<C> context) throws Exception {
        if(context.data.driver == null) return TaskResult.failure(new Exception("Driver is null. Did you forget to connect?"));

        context.data.driver.findElement(By.xpath(this.xpath)).sendKeys(this.text);

        return TaskResult.success();
    }
}
