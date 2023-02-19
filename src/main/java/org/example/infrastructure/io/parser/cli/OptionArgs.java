package org.example.infrastructure.io.parser.cli;

public class OptionArgs {

    private final String name;
    private final String description;
    private final boolean needValue;

    public OptionArgs(String name, String description, boolean needValue) {
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
