package net.nussi.denki.bot.workflow.stage;

import net.nussi.denki.bot.workflow.step.Step;
import net.nussi.denki.bot.workflow.task.TaskResult;
import net.nussi.denki.bot.workflow.workflow.WorkflowContext;

import java.util.List;
import java.util.Optional;

public class SequentialStage<C> extends Stage<C> {

    public SequentialStage(String name) {
        super(name, "Stage (sequential)");
    }

    public SequentialStage(String name, List<Step<C>> steps) {
        super(name, "Stage (sequential)", steps);
    }

    @Override
    protected TaskResult run(WorkflowContext<C> context) throws Exception {
        TaskResult result = TaskResult.success();

        for (Step<C> step : steps) {
            if(result instanceof TaskResult.Failure) {
                if(step.isCleanupTask()) step.execute(context.deeper(), Optional.of(this));
                else continue;
            } else {
                result = step.execute(context.deeper(), Optional.of(this));
            }
        }

        return result;
    }

}
