package net.nussi.denki.bot.workflow.step;

import lombok.extern.slf4j.Slf4j;
import net.nussi.denki.bot.workflow.task.TaskResult;
import net.nussi.denki.bot.workflow.workflow.WorkflowContext;

import java.time.Duration;

@Slf4j
public class DelayStep<C> extends Step<C> {
    private final Duration delay;

    public DelayStep(Duration delay) {
        super("Delay for " + delay.toString(), "Common (Delay)");
        this.delay = delay;
    }

    @Override
    protected TaskResult run(WorkflowContext<C> context) throws Exception {
        log.info("{}Delaying for {}", context.loggingPrefix(), delay);
        Thread.sleep(delay.toMillis());
        return TaskResult.success();
    }
}
