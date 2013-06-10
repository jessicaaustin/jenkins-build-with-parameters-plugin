jenkins-build-with-parameters-plugin
====================================

Allows the user to provide parameters for a build in the url, prompting for confirmation before triggering the job.

This is VERY similar to /job/JOBNAME/buildWithParameters, with one key difference: a human must confirm that the parameters are correct before the build is triggered. This is useful if you want to create a list of jobs with parameters to trigger ahead of time, and execute it at some future date (e.g. a deployment plan).

An example triggering of a job with the plugin:
![Example screenshot](/example_screenshot.png "Triggering a job with the plugin")
