package org.example.infrastructure.config;

public class ActionArgs {
    private final String name;
    private final String description;
    private final boolean needValue;

    public ActionArgs(String name, String description, boolean needValue) {
        this.name = name;
        this.description = description;
        this.needValue = needValue;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public boolean isNeedValue() {
        return needValue;
    }
}
