package org.jenkinsci.plugins.buildwithparameters;

import hudson.model.ParameterValue;
import hudson.model.BooleanParameterValue;
import hudson.model.PasswordParameterValue;
import hudson.model.StringParameterValue;

public class BuildParameter {
    static final String JOB_DEFAULT_PASSWORD_PLACEHOLDER = "job_default_password";
    private boolean isPasswordParam;
    private String name, description, value;

    public BuildParameter(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public static boolean isDefaultPasswordPlaceholder(String candidate) {
    	if(candidate == null) {
    	    return false;
    	}
    	return JOB_DEFAULT_PASSWORD_PLACEHOLDER.equals(candidate);
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

    public void setValue(ParameterValue parameterValue) {
        if (parameterValue instanceof StringParameterValue) {
            this.value = ((StringParameterValue) parameterValue).value;
        } else if (parameterValue instanceof BooleanParameterValue) {
            this.value = String.valueOf(((BooleanParameterValue) parameterValue).value);
        } else if (parameterValue instanceof PasswordParameterValue) {
            this.value = JOB_DEFAULT_PASSWORD_PLACEHOLDER;
        }
    }

    public void setPasswordParam(boolean isPasswordParam) {
        this.isPasswordParam = isPasswordParam;
	}
    
    public boolean isPasswordParam() {
        return isPasswordParam;
	}
}
