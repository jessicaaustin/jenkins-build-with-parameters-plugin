package org.jenkinsci.plugins.buildwithparameters;

public class BuildParameter {
    private String name, description, value;

    public BuildParameter(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
