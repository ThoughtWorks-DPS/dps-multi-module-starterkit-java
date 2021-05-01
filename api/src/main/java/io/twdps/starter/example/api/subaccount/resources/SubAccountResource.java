package io.twdps.starter.example.api.subaccount.resources;

import io.micrometer.core.annotation.Timed;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.twdps.starter.boot.exception.RequestValidationException;
import io.twdps.starter.boot.exception.ResourceNotFoundException;
import io.twdps.starter.example.api.subaccount.requests.SubAccountRequest;
import io.twdps.starter.example.api.subaccount.responses.SubAccountResponse;
import io.twdps.starter.example.api.subaccount.responses.PagedSubAccountResponse;
import io.twdps.starter.example.api.responses.PagedResponse;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.zalando.problem.Problem;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RequestMapping(value = "/v1/example/subaccounts", produces = "application/json")
@Tag(name = "SubAccount API", description = "Starter Kit template API, essentially CRUD access")
@SecurityRequirement(name = "bearer-jwt")
@Timed
public interface SubAccountResource {

  @Operation(summary = "Create a new SubAccount")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "201",
            description = "Created a new SubAccount",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = SubAccountResponse.class))
            }),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid data provided",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Problem.class))),
        @ApiResponse(
            responseCode = "401",
            description = "Not authorized",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Problem.class))),
        @ApiResponse(
            responseCode = "403",
            description = "Forbidden",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Problem.class)))
      })
  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  @Timed(value = "example.subaccounts.create")
  ResponseEntity<SubAccountResponse> addEntity(
      @Parameter(
              description = "metadata for new SubAccount resource. Cannot null or empty.",
              required = true,
              schema = @Schema(implementation = SubAccountRequest.class))
          @Valid
          @RequestBody
          SubAccountRequest request)
      throws RequestValidationException;

  @Operation(summary = "Find a specific SubAccount based on entity identifier")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Found the SubAccount",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = SubAccountResponse.class))
            }),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid id supplied",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Problem.class))),
        @ApiResponse(
            responseCode = "401",
            description = "Not authorized",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Problem.class))),
        @ApiResponse(
            responseCode = "403",
            description = "Forbidden",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Problem.class))),
        @ApiResponse(
            responseCode = "404",
            description = "Not found",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Problem.class)))
      })
  @GetMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  @Timed(value = "example.subaccounts.findById")
  ResponseEntity<SubAccountResponse> findEntityById(
      @Parameter(
              description = "unique identifier for SubAccount resource. Cannot null or empty.",
              example = "uuid",
              required = true)
          @NotNull
          @PathVariable(value = "id")
          String id)
      throws ResourceNotFoundException;

  @Operation(summary = "Get all SubAccounts")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "All existing SubAccounts",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = PagedSubAccountResponse.class))
            }),
        @ApiResponse(
            responseCode = "401",
            description = "Not authorized",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Problem.class))),
        @ApiResponse(
            responseCode = "403",
            description = "Forbidden",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Problem.class))),
        @ApiResponse(
            responseCode = "404",
            description = "Not found",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Problem.class)))
      })
  @GetMapping()
  @ResponseStatus(HttpStatus.OK)
  @Timed(value = "example.subaccounts.findAll")
  ResponseEntity<PagedResponse<SubAccountResponse>> findEntities(
      @Parameter(
              description = "Paging specification for retrieving a subset of the full list.",
              required = false)
          Pageable pageable);

  @Operation(summary = "Update an existing SubAccount")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Updated SubAccount info",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = SubAccountResponse.class))
            }),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid entity",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Problem.class))),
        @ApiResponse(
            responseCode = "401",
            description = "Not authorized",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Problem.class))),
        @ApiResponse(
            responseCode = "403",
            description = "Forbidden",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Problem.class))),
        @ApiResponse(
            responseCode = "404",
            description = "SubAccount not found",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Problem.class)))
      })
  @PutMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  @Timed(value = "example.subaccounts.update")
  ResponseEntity<SubAccountResponse> updateEntityById(
      @Parameter(
              description = "unique identifier for SubAccount resource. Cannot be null or empty",
              example = "uuid",
              required = true)
          @NotNull
          @PathVariable(value = "id")
          String id,
      @Parameter(
              description = "updated metadata SubAccount resource. Cannot null or empty.",
              required = true,
              schema = @Schema(implementation = SubAccountRequest.class))
          @Valid
          @RequestBody
          SubAccountRequest request)
      throws ResourceNotFoundException, RequestValidationException;

  @Operation(summary = "Delete an existing SubAccount")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Deleted SubAccount info",
            content = {
              @Content(
                  mediaType = "application/json",
                  schema = @Schema(implementation = SubAccountResponse.class))
            }),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid id supplied",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Problem.class))),
        @ApiResponse(
            responseCode = "401",
            description = "Not authorized",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Problem.class))),
        @ApiResponse(
            responseCode = "403",
            description = "Forbidden",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Problem.class))),
        @ApiResponse(
            responseCode = "404",
            description = "SubAccount not found",
            content =
                @Content(
                    mediaType = "application/json",
                    schema = @Schema(implementation = Problem.class)))
      })
  @DeleteMapping("/{id}")
  @ResponseStatus(HttpStatus.OK)
  @Timed(value = "example.subaccounts.delete")
  ResponseEntity<SubAccountResponse> deleteEntityById(
      @Parameter(
              description = "unique identifier for SubAccount resource. Cannot null or empty.",
              example = "uuid",
              required = true)
          @NotNull
          @PathVariable(value = "id")
          String id)
      throws ResourceNotFoundException;
}
