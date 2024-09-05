package com.example.sonarqube;

import org.sonar.api.batch.sensor.Sensor;
import org.sonar.api.batch.sensor.SensorContext;
import org.sonar.api.batch.sensor.SensorDescriptor;
import org.sonar.api.batch.fs.FileSystem;
import org.sonar.api.batch.fs.InputFile;
import org.sonar.api.batch.fs.FilePredicates;
import org.sonar.api.rule.RuleKey;
import org.sonar.api.batch.sensor.issue.NewIssue;
import org.sonar.api.batch.sensor.issue.NewIssueLocation;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class TerraformSensor implements Sensor {

    @Override
    public void describe(SensorDescriptor descriptor) {
        descriptor.name("Terraform Sensor for google_organization_iam_binding detection")
                .onlyOnLanguage("tf"); // Limit the sensor to Terraform files
    }

    @Override
    public void execute(SensorContext context) {
        FileSystem fs = context.fileSystem();
        FilePredicates predicates = fs.predicates();
        Iterable<InputFile> terraformFiles = fs.inputFiles(predicates.hasLanguage("tf"));

        RuleKey ruleKey = RuleKey.of("terraform-rules", "google-organization-iam-binding-detection");

        for (InputFile file : terraformFiles) {
            try {
                List<String> lines = Files.readAllLines(Paths.get(file.uri()));
                for (int i = 0; i < lines.size(); i++) {
                    String line = lines.get(i);
                    if (line.contains("google_organization_iam_binding")) {
                        NewIssue newIssue = context.newIssue().forRule(ruleKey);
                        NewIssueLocation location = newIssue.newLocation()
                                .on(file)
                                .at(file.selectLine(i + 1)) // Use TextRange in NewIssueLocation
                                .message("Usage of google_organization_iam_binding detected");

                        newIssue.at(location).save();
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
