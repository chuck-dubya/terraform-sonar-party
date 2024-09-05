package com.example.sonarqube;

import org.sonar.api.Plugin;

public class TerraformSonarQubePlugin implements Plugin {

    @Override
    public void define(Context context) {
        // Register extensions
        context.addExtension(TerraformRuleDefinition.class);
        context.addExtension(TerraformSensor.class);
    }
}
