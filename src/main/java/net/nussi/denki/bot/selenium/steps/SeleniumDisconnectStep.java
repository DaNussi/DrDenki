package net.nussi.denki.bot.selenium.steps;

import lombok.extern.slf4j.Slf4j;
import net.nussi.denki.bot.selenium.SeleniumSession;
import net.nussi.denki.bot.workflow.step.Step;
import net.nussi.denki.bot.workflow.task.TaskResult;
import net.nussi.denki.bot.workflow.workflow.WorkflowContext;

@Slf4j
public class SeleniumDisconnectStep<C extends SeleniumSession> extends Step<C> {

    public SeleniumDisconnectStep() {
        super("Disconnects from selenium", "Selenium (disconnect)", true);
    }

    @Override
    protected TaskResult run(WorkflowContext<C> context) throws Exception {
        log.info("{}Disconnecting...", context.loggingPrefix());
        context.data.driver.quit();
        log.info("{}Disconnected!", context.loggingPrefix());
        return TaskResult.success();
    }
}
