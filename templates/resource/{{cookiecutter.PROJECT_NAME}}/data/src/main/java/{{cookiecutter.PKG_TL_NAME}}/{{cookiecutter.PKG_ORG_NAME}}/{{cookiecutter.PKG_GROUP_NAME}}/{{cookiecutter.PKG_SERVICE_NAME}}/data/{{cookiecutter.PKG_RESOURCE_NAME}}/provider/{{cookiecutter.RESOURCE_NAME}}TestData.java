package {{cookiecutter.PKG_TL_NAME}}.{{cookiecutter.PKG_ORG_NAME}}.{{cookiecutter.PKG_GROUP_NAME}}.{{cookiecutter.PKG_SERVICE_NAME}}.data.{{cookiecutter.PKG_RESOURCE_NAME}}.provider;

import {{cookiecutter.PKG_TL_NAME}}.{{cookiecutter.PKG_ORG_NAME}}.starter.boot.test.data.provider.GenericDataLoader;
import {{cookiecutter.PKG_TL_NAME}}.{{cookiecutter.PKG_ORG_NAME}}.{{cookiecutter.PKG_GROUP_NAME}}.{{cookiecutter.PKG_SERVICE_NAME}}.data.{{cookiecutter.PKG_RESOURCE_NAME}}.model.{{cookiecutter.RESOURCE_NAME}}Data;

import java.util.Arrays;

public class {{cookiecutter.RESOURCE_NAME}}TestData extends GenericDataLoader<{{cookiecutter.RESOURCE_NAME}}Data> {

  /** construct hard-coded test data. */
  public {{cookiecutter.RESOURCE_NAME}}TestData() {
    getData()
        .put(
            "default",
            {{cookiecutter.RESOURCE_NAME}}Data.builder()
                .firstName("Agent")
                .lastName("Smith")
                .userName("asmith")
                .fullName("Agent Smith")
                .pii("eigenvalue")
                .id("uuid-unit-vector")
                .build());
    getData()
        .put(
            "bogus",
            {{cookiecutter.RESOURCE_NAME}}Data.builder()
                .firstName("Ted")
                .lastName("Logan")
                .userName("theodore")
                .fullName("Ted Logan")
                .pii("wyldstallyns")
                .id("Reeves")
                .build());
    getCollections()
        .put(
            "default",
            Arrays.asList(
                {{cookiecutter.RESOURCE_NAME}}Data.builder()
                    .firstName("Agent")
                    .lastName("Smith")
                    .userName("asmith")
                    .fullName("Agent Smith")
                    .pii("eigenvalue")
                    .id("uuid-unit-vector")
                    .build(),
                {{cookiecutter.RESOURCE_NAME}}Data.builder()
                    .firstName("Neo")
                    .lastName("None")
                    .userName("neo")
                    .fullName("Neo None")
                    .pii("sunglasses")
                    .id("Reeves")
                    .build()));
  }
}
