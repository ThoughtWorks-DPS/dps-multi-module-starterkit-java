package {{cookiecutter.PKG_TL_NAME}}.{{cookiecutter.PKG_ORG_NAME}}.{{cookiecutter.PKG_GROUP_NAME}}.{{cookiecutter.PKG_SERVICE_NAME}}.architecture;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.core.importer.ImportOption.Predefined;
import org.junit.jupiter.api.Test;

public class LayeredArchitectureTest {
  private final String packageName = "{{cookiecutter.PKG_TL_NAME}}.{{cookiecutter.PKG_ORG_NAME}}.{{cookiecutter.PKG_GROUP_NAME}}.{{cookiecutter.PKG_SERVICE_NAME}}";
  private final JavaClasses classes =
      new ClassFileImporter()
          .withImportOption(Predefined.DO_NOT_INCLUDE_TESTS)
          .importPackages(packageName);

  @Test
  public void layerDependenciesAreRespected() {
    layeredArchitecture()
        .layer("API")
        .definedBy(packageName + "..api..")
        .layer("API-Requests")
        .definedBy(packageName + "..api..requests..")
        .layer("API-Responses")
        .definedBy(packageName + "..api..responses..")
        .layer("Controllers")
        .definedBy(packageName + "..controller..")
        .layer("SPI")
        .definedBy(packageName + "..service.spi..")
        .layer("Services")
        .definedBy(packageName + "..service.provider..")
        .layer("Persistence")
        .definedBy(packageName + "..persistence..")
        .whereLayer("API")
        .mayOnlyBeAccessedByLayers("Controllers")
        .whereLayer("API-Requests")
        .mayOnlyBeAccessedByLayers("Controllers", "API")
        .whereLayer("API-Responses")
        .mayOnlyBeAccessedByLayers("Controllers", "API")
        .whereLayer("Controllers")
        .mayNotBeAccessedByAnyLayer()
        .whereLayer("SPI")
        .mayOnlyBeAccessedByLayers("Controllers", "Services")
        .whereLayer("Persistence")
        .mayOnlyBeAccessedByLayers("Services")
        .check(classes);
  }
}
