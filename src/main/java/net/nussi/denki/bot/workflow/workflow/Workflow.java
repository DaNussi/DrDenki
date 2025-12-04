package net.nussi.denki.bot.workflow.workflow;

import lombok.extern.slf4j.Slf4j;
import net.nussi.denki.bot.workflow.stage.Stage;
import net.nussi.denki.bot.workflow.task.Task;

@Slf4j
public class Workflow<T> implements Task {
    private final String name;
    private final WorkflowContext<T> context;
    private final Stage[] stages;

    public Workflow(String name, WorkflowContext<T> context, Stage[] stages) {
        this.name = name;
        this.context = context;
        this.stages = stages;
    }

    public static <D> WorkflowBuilder<D> builder(String name, D contextData) {
        return new WorkflowBuilder<D>(name, contextData);
    }

    @Override
    public void execute(WorkflowContext<?> context) {
        log.info("Workflow \"{}\" started", name);
        for (Stage stage : stages) {
            stage.execute(context.deeper());
        }
        log.info("Workflow \"{}\" finished", name);
    }
}
