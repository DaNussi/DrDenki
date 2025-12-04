package net.nussi.denki.bot.workflow.stage;

import lombok.extern.slf4j.Slf4j;
import net.nussi.denki.bot.workflow.step.Step;
import net.nussi.denki.bot.workflow.task.Task;
import net.nussi.denki.bot.workflow.workflow.WorkflowContext;

@Slf4j
public class Stage implements Task {
    private final String name;
    private final Step[] steps;

    public Stage(String name, Step[] steps) {
        this.name = name;
        this.steps = steps;
    }

    public static StageBuilder builder(String name) {
        return new StageBuilder(name);
    }

    @Override
    public void execute(WorkflowContext<?> context) {
        log.info("Workflow \"{}\" started", name);
        for (Step steps : steps) {
            steps.execute(context.deeper());
        }
        log.info("Workflow \"{}\" finished", name);
    }
}
