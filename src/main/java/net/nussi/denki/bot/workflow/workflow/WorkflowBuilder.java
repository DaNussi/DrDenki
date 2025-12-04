package net.nussi.denki.bot.workflow.workflow;

import net.nussi.denki.bot.workflow.stage.Stage;
import net.nussi.denki.bot.workflow.stage.StageBuilder;

import java.util.LinkedList;
import java.util.function.Consumer;

public class WorkflowBuilder<T> {
    private final LinkedList<Stage> stages = new LinkedList<>();
    private T contextData;
    private String name;

    public WorkflowBuilder(String name, T contextData) {
        this.contextData = contextData;
        this.name = name;
    }

    public WorkflowBuilder<T> setContextData(T contextData) {
        this.contextData =  contextData;
        return this;
    }

    public WorkflowBuilder<T> setName(String name) {
        this.name = name;
        return this;
    }

    public WorkflowBuilder<T> addStage(Stage step) {
        stages.add(step);
        return this;
    }

    public WorkflowBuilder<T> stage(String name, Consumer<StageBuilder> stageBuilder) {
        StageBuilder builder = Stage.builder(name);
        stageBuilder.accept(builder);
        this.addStage(builder.build());
        return this;
    }

    public Workflow<T> build() {
        return new Workflow<T>(
                this.name,
                new WorkflowContext<>(contextData),
                this.stages.toArray(new Stage[0])
                );
    }

}
