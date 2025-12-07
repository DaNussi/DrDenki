package net.nussi.denki.bot.selenium.steps;

import lombok.extern.slf4j.Slf4j;
import net.nussi.denki.bot.selenium.SeleniumService;
import net.nussi.denki.bot.selenium.SeleniumSession;
import net.nussi.denki.bot.workflow.step.Step;
import net.nussi.denki.bot.workflow.task.TaskResult;
import net.nussi.denki.bot.workflow.workflow.WorkflowContext;

@Slf4j
public class NavigateStep<C extends SeleniumSession> extends Step<C> {
    private final String url;

    public NavigateStep(String url) {
        super("Navigate to " + url, "Selenium (navigate)");
        this.url = url;
    }

    @Override
    protected TaskResult run(WorkflowContext<C> context) throws Exception {
        if(context.data.driver == null) return TaskResult.failure(new Exception("Driver is null. Did you forget to connect?"));
        context.data.driver.get(this.url);
        return TaskResult.success();
    }
}
