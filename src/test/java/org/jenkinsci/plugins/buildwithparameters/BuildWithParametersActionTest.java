package org.jenkinsci.plugins.buildwithparameters;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import hudson.model.ParameterValue;
import hudson.model.FreeStyleProject;
import hudson.model.ParameterDefinition;
import hudson.model.ParametersDefinitionProperty;
import hudson.model.PasswordParameterDefinition;
import hudson.model.PasswordParameterValue;

import java.io.IOException;

import org.junit.Rule;
import org.junit.Test;
import org.jvnet.hudson.test.JenkinsRule;

public class BuildWithParametersActionTest {
    @Rule public JenkinsRule j = new JenkinsRule();
    
    @Test
    public void getAvailableParameters_passwordParam() throws IOException {
        ParameterDefinition pwParamDef = new PasswordParameterDefinition("n", BuildParameter.JOB_DEFAULT_PASSWORD_PLACEHOLDER, "d");
        BuildWithParametersAction bwpa = testableProject(pwParamDef);

        BuildParameter pwParameter = bwpa.getAvailableParameters().get(0);
        assertTrue(pwParameter.getType()==BuildParameterType.PASSWORD);
    }

    private BuildWithParametersAction testableProject(
            ParameterDefinition pwParamDef) throws IOException {
        FreeStyleProject project = j.createFreeStyleProject();
        ParametersDefinitionProperty paramsDef = new ParametersDefinitionProperty(pwParamDef);
        project.addProperty(paramsDef);
        BuildWithParametersAction bwpa = new BuildWithParametersAction(project) {
            @Override
            ParameterValue getParameterDefinitionValue(
                    ParameterDefinition parameterDefinition) {
                return null;
            }
        };
        return bwpa;
    }
    
    @Test
    public void applyDefaultPassword() throws IOException {
        String jobDefaultPassword = "defaultPassword";
        String passwordFromRequest = BuildParameter.JOB_DEFAULT_PASSWORD_PLACEHOLDER;
        String adjustedPassword = applyDefaultPasswordHelper(jobDefaultPassword, passwordFromRequest);
        
        assertEquals(jobDefaultPassword, adjustedPassword);
    }

    @Test
    public void applyDefaultPassword_nonDefault() throws IOException {
        String jobDefaultPassword = "defaultPassword";
        String passwordFromRequest = "userSuppliedPassword";
        String adjustedPassword = applyDefaultPasswordHelper(jobDefaultPassword, passwordFromRequest);
        
        assertEquals(passwordFromRequest, adjustedPassword);
    }

    private String applyDefaultPasswordHelper(String jobDefaultPassword, String passwordFromRequest) throws IOException {
        ParameterDefinition pwParamDef = new PasswordParameterDefinition("n", jobDefaultPassword, "d");
        BuildWithParametersAction bwpa = testableProject(pwParamDef);
        
        ParameterValue parameterValue = new PasswordParameterValue("n", passwordFromRequest);

        ParameterValue adjustedParamValue = bwpa.applyDefaultPassword(pwParamDef, parameterValue);
        return BuildWithParametersAction.getPasswordValue((PasswordParameterValue)adjustedParamValue);
    }
}
