package net.nussi.denki.bot.workflow.workflow;

import net.nussi.denki.bot.workflow.stage.Stage;
import net.nussi.denki.bot.workflow.stage.StageBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Consumer;

public class WorkflowBuilder<C> {
    private final String name;
    private final C context;
    private final List<Stage<C>> stages;
    private final HashMap<String, Object> variables;

    public WorkflowBuilder(String name, C context) {
        this.name = name;
        this.context = context;
        this.stages = new ArrayList<>();
        this.variables = new HashMap<>();
    }

    public Workflow<C> build() {
        WorkflowContext<C> workflowContext = new WorkflowContext<>(context, variables);
        return new Workflow<>(name, workflowContext, stages);
    }

    public WorkflowBuilder<C> variable(String name, Object value) {
        this.variables.put(name, value);
        return this;
    }

    public WorkflowBuilder<C> addStage(Stage<C> stage) {
        stages.add(stage);
        return this;
    }

    public WorkflowBuilder<C> stage(String name, Consumer<StageBuilder<C>> stageBuilderConsumer) {
        StageBuilder<C> stageBuilder  = new StageBuilder<C>(name);
        stageBuilderConsumer.accept(stageBuilder);
        this.addStage(stageBuilder.build());
        return this;
    }
}
