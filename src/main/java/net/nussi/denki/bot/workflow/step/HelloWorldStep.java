package net.nussi.denki.bot.workflow.step;

import lombok.extern.slf4j.Slf4j;
import net.nussi.denki.bot.workflow.task.TaskResult;
import net.nussi.denki.bot.workflow.workflow.WorkflowContext;

@Slf4j
public class HelloWorldStep<C> extends Step<C> {
    public HelloWorldStep() {
        super("Print hello world", "Common (hello world)");
    }

    @Override
    protected TaskResult run(WorkflowContext<C> context) throws Exception {
        log.info("{}Hello World", context.loggingPrefix());
        return TaskResult.success("Hello World");
    }
}
