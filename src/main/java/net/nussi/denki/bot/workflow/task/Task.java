package net.nussi.denki.bot.workflow.task;

import lombok.extern.slf4j.Slf4j;
import net.nussi.denki.bot.workflow.workflow.WorkflowContext;

import java.util.Optional;

@Slf4j
public abstract class Task<C> {
    protected final String name;
    protected final String type;
    protected final boolean isCleanup;

    public Task(String name, String type) {
        this.name = name;
        this.type = type;
        this.isCleanup = false;
    }

    public Task(String name, String type, boolean isCleanup) {
        this.name = name;
        this.type = type;
        this.isCleanup = isCleanup;
    }

    public TaskResult execute(WorkflowContext<C> context, Optional<Task<C>> parent) {
//        log.info("{}{} \"{}\" starting", context.loggingPrefixStart(), type, name);
        log.info("{}{} - {}", context.loggingPrefixStart(), type, name);

        TaskResult result;
        try {
            result = run(context);
        } catch (Exception e) {
            result = TaskResult.failure(e);
        }

        if(parent.isPresent()) {
            parent.get().onChildTaskFinished(this, result);
        }

//        log.info("{}{} \"{}\" finished with \"{}\"", context.loggingPrefixEnd(), type, name, result.type.name());
        log.info("{}|{}|", context.loggingPrefixEnd(), result.type.name());
        return result;
    }

    public void onChildTaskFinished(Task<C> task, TaskResult result) {}

    protected abstract TaskResult run(WorkflowContext<C> context) throws Exception;

    public boolean isCleanupTask() {
        return this.isCleanup;
    }
}
