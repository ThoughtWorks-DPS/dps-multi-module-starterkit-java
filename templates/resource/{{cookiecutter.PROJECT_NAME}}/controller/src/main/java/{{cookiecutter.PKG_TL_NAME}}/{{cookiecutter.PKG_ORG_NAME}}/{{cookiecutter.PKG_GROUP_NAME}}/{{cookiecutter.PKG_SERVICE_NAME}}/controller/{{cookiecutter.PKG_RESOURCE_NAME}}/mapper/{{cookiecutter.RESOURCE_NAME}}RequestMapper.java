package {{cookiecutter.PKG_TL_NAME}}.{{cookiecutter.PKG_ORG_NAME}}.{{cookiecutter.PKG_GROUP_NAME}}.{{cookiecutter.PKG_SERVICE_NAME}}.controller.account.mapper;

import {{cookiecutter.PKG_TL_NAME}}.{{cookiecutter.PKG_ORG_NAME}}.{{cookiecutter.PKG_GROUP_NAME}}.{{cookiecutter.PKG_SERVICE_NAME}}.api.{{cookiecutter.PKG_RESOURCE_NAME}}.requests.{{cookiecutter.RESOURCE_NAME}}Request;
{%- if cookiecutter.CREATE_SUBRESOURCE == "y" %}
import {{cookiecutter.PKG_TL_NAME}}.{{cookiecutter.PKG_ORG_NAME}}.{{cookiecutter.PKG_GROUP_NAME}}.{{cookiecutter.PKG_SERVICE_NAME}}.api.{{cookiecutter.PKG_RESOURCE_NAME}}.requests.{{cookiecutter.SUB_RESOURCE_NAME}}Request;
{%- endif %}
import {{cookiecutter.PKG_TL_NAME}}.{{cookiecutter.PKG_ORG_NAME}}.{{cookiecutter.PKG_GROUP_NAME}}.{{cookiecutter.PKG_SERVICE_NAME}}.api.{{cookiecutter.PKG_RESOURCE_NAME}}.responses.{{cookiecutter.RESOURCE_NAME}}Response;
{%- if cookiecutter.CREATE_SUBRESOURCE == "y" %}
import {{cookiecutter.PKG_TL_NAME}}.{{cookiecutter.PKG_ORG_NAME}}.{{cookiecutter.PKG_GROUP_NAME}}.{{cookiecutter.PKG_SERVICE_NAME}}.api.{{cookiecutter.PKG_RESOURCE_NAME}}.responses.{{cookiecutter.SUB_RESOURCE_NAME}}Response;
{%- endif %}
import {{cookiecutter.PKG_TL_NAME}}.{{cookiecutter.PKG_ORG_NAME}}.{{cookiecutter.PKG_GROUP_NAME}}.{{cookiecutter.PKG_SERVICE_NAME}}.service.spi.{{cookiecutter.PKG_RESOURCE_NAME}}.model.{{cookiecutter.RESOURCE_NAME}};
{%- if cookiecutter.CREATE_SUBRESOURCE == "y" %}
import {{cookiecutter.PKG_TL_NAME}}.{{cookiecutter.PKG_ORG_NAME}}.{{cookiecutter.PKG_GROUP_NAME}}.{{cookiecutter.PKG_SERVICE_NAME}}.service.spi.{{cookiecutter.PKG_RESOURCE_NAME}}.model.{{cookiecutter.SUB_RESOURCE_NAME}};
{%- endif %}
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface {{cookiecutter.RESOURCE_NAME}}RequestMapper {

{%- if cookiecutter.CREATE_SUBRESOURCE == "y" %}
  @Mapping(target = "response", expression = "java(\"Hello \" + src.getFirstName())")
  {{cookiecutter.SUB_RESOURCE_NAME}}Response to{{cookiecutter.SUB_RESOURCE_NAME}}Response({{cookiecutter.SUB_RESOURCE_NAME}} src);
{%- endif %}

  @Mapping(constant = "UNKNOWN_ID", target = "id")
  {{cookiecutter.RESOURCE_NAME}} toModel({{cookiecutter.RESOURCE_NAME}}Request request);

{%- if cookiecutter.CREATE_SUBRESOURCE == "y" %}
  @Mapping(constant = "UNKNOWN_ID", target = "id")
  {{cookiecutter.SUB_RESOURCE_NAME}} toModel({{cookiecutter.SUB_RESOURCE_NAME}}Request request);
{%- endif %}

  @Mapping(
      target = "fullName",
      expression = "java(String.format(\"%s %s\",src.getFirstName(),src.getLastName()))")
  {{cookiecutter.RESOURCE_NAME}}Response to{{cookiecutter.RESOURCE_NAME}}Response({{cookiecutter.RESOURCE_NAME}} src);

{%- if cookiecutter.CREATE_SUBRESOURCE == "y" %}
  @Mapping(
      target = "fullName",
      expression = "java(String.format(\"%s %s\",src.getFirstName(),src.getLastName()))")
  {{cookiecutter.SUB_RESOURCE_NAME}}Response to{{cookiecutter.SUB_RESOURCE_NAME}}Response({{cookiecutter.SUB_RESOURCE_NAME}} src);
{%- endif %}

  default {{cookiecutter.RESOURCE_NAME}}Response to{{cookiecutter.RESOURCE_NAME}}Response(Optional<{{cookiecutter.RESOURCE_NAME}}> src) {
    return to{{cookiecutter.RESOURCE_NAME}}Response(src.orElse(null));
  }

  List<{{cookiecutter.RESOURCE_NAME}}Response> to{{cookiecutter.RESOURCE_NAME}}ResponseList(List<{{cookiecutter.RESOURCE_NAME}}> src);

{%- if cookiecutter.CREATE_SUBRESOURCE == "y" %}
  default {{cookiecutter.SUB_RESOURCE_NAME}}Response to{{cookiecutter.SUB_RESOURCE_NAME}}Response(Optional<{{cookiecutter.SUB_RESOURCE_NAME}}> src) {
    return to{{cookiecutter.SUB_RESOURCE_NAME}}Response(src.orElse(null));
    }

  List<{{cookiecutter.SUB_RESOURCE_NAME}}Response> to{{cookiecutter.SUB_RESOURCE_NAME}}ResponseList(List<{{cookiecutter.SUB_RESOURCE_NAME}}> src);
{%- endif %}
}
