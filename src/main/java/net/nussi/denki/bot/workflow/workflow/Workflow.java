package net.nussi.denki.bot.workflow.workflow;

import net.nussi.denki.bot.workflow.stage.Stage;
import net.nussi.denki.bot.workflow.step.Step;
import net.nussi.denki.bot.workflow.task.Task;
import net.nussi.denki.bot.workflow.task.TaskResult;

import java.util.List;
import java.util.Optional;

public class Workflow<C> extends Task<C> {
    private final WorkflowContext<C> context;
    private final List<Stage<C>> stages;

    public Workflow(String name, WorkflowContext<C> context, List<Stage<C>> stages) {
        super(name, "Workflow");
        this.context = context;
        this.stages = stages;
    }

    public TaskResult execute() {
        TaskResult result;
        try {
            result = execute(this.context, Optional.empty());
        } catch (Exception e) {
            result = TaskResult.failure(e);
        }
        if (result instanceof TaskResult.Failure failure) {
            failure.getException().printStackTrace();
        }
        return result;
    }

    @Override
    protected TaskResult run(WorkflowContext<C> context) throws Exception {
        TaskResult result = TaskResult.success();

        for (Stage<C> stage : stages) {
            if(result instanceof TaskResult.Failure) {
                if(stage.isCleanupTask()) stage.execute(context.deeper(), Optional.of(this));
                else continue;
            } else {
                result = stage.execute(context.deeper(), Optional.of(this));
            }
        }

        return result;
    }

    public static <D> WorkflowBuilder<D> builder(String name, D context) {
        return new WorkflowBuilder<>(name, context);
    }
}
