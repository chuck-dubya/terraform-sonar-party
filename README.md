## Terraform Sensor for SonarQube

This is a custom SonarQube sensor designed to detect the usage of `google_organization_iam_binding` in Terraform code. It helps ensure that your Terraform configurations adhere to best practices and security guidelines.

### Features

* **Detects `google_organization_iam_binding`:**  Scans Terraform files for the presence of this resource.
* **Raises SonarQube Issues:**  Creates issues in SonarQube to highlight the usage, allowing for easy tracking and remediation.
* **Precise Location:**  Pinpoints the exact line in the Terraform file where the resource is used.

### How it works

1. **Sensor Integration:** The sensor is integrated into your SonarQube analysis process.
2. **Terraform File Scanning:** The sensor scans all `.tf` files in your project.
3. **Resource Detection:**  The sensor looks for lines containing `google_organization_iam_binding`.
4. **Issue Creation:** When the resource is detected, an issue is created in SonarQube with a clear message and location.

### Usage

1. **Include in your SonarQube Project:**  Make sure this custom sensor is included in your SonarQube project's analysis configuration.
2. **Run SonarQube Analysis:**  Run your regular SonarQube analysis.
3. **Review Issues:** Check the SonarQube dashboard for any issues raised by this sensor.

### Example

```java
import org.sonar.api.batch.sensor.Sensor;
import org.sonar.api.batch.sensor.SensorContext;
import org.sonar.api.batch.sensor.SensorDescriptor;
// ... other imports

public class TerraformSensor implements Sensor {
    // ... implementation (see provided code) 
}
```

### Important Notes

* **Rule Configuration:** Ensure you have a corresponding rule defined in SonarQube with the key `terraform-rules:google-organization-iam-binding-detection`.
* **Customization:** You can easily customize this sensor to detect other Terraform resources or patterns.

**Disclaimer:** This is a basic example. Make sure to adapt and enhance it based on your specific requirements and SonarQube setup.

Feel free to contribute or report any issues!
