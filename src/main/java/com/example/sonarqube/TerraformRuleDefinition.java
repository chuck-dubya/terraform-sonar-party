package com.example.sonarqube;

import org.sonar.api.server.rule.RulesDefinition;

public class TerraformRuleDefinition implements RulesDefinition {

    @Override
    public void define(Context context) {
        NewRepository repository = context.createRepository("terraform-rules", "tf").setName("Terraform Rules");

        NewRule rule = repository.createRule("google-organization-iam-binding-detection")
                .setName("Detect google_organization_iam_binding in Terraform")
                .setHtmlDescription(
                        "This rule detects the usage of google_organization_iam_binding in Terraform files");

        rule.setTags("security", "gcp", "terraform");

        repository.done();
    }
}
