package org.jenkinsci.plugins.buildwithparameters;

import hudson.Extension;
import hudson.model.Action;
import hudson.model.Job;
import jenkins.model.ParameterizedJobMixIn.ParameterizedJob;
import jenkins.model.TransientActionFactory;

import java.util.Collection;
import java.util.Collections;
import javax.annotation.Nonnull;

@Extension
public class ParamBuilderActionFactory extends TransientActionFactory<Job> {

    @Override
    public Class<Job> type() {
        return Job.class;
    }

    @Nonnull
    @Override
    @SuppressWarnings("unchecked")
    public Collection<? extends Action> createFor(@Nonnull Job target) {
        if (target instanceof ParameterizedJob) {
            return Collections.singletonList(new BuildWithParametersAction(target));
        } else {
            return Collections.emptyList();
        }
    }

}
