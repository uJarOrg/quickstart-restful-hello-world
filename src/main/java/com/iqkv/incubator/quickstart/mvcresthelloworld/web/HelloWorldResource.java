/*
 * Copyright 2024 IQKV.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.iqkv.incubator.quickstart.mvcresthelloworld.web;

import com.iqkv.boot.mvc.rest.ApiError;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Tag(name = "Hello World Resource", description = "API for greeting")
@RequestMapping("/api/v1/hello-world")
record HelloWorldResource() {

  private static final Logger log = org.slf4j.LoggerFactory.getLogger(HelloWorldResource.class);

  @GetMapping
  @Operation(
      description = "Generate greeting",
      responses = {
          @ApiResponse(responseCode = "200",
                       description = "Success"),
          @ApiResponse(responseCode = "500",
                       description = "Internal error",
                       content = @Content(schema = @Schema(implementation = ApiError.class))),
          @ApiResponse(responseCode = "400",
                       description = "Bad request",
                       content = @Content(schema = @Schema(implementation = ApiError.class))),
      })
  ResponseEntity<GreetingDto> getGreeting() {
    final var greeting = new GreetingDto();
    log.info("Send RESTFul API Response with {} message.", greeting.getMessage());
    return new ResponseEntity<>(greeting, HttpStatus.OK);
  }

  record GreetingDto() {
    private static final String MESSAGE = "Hello, World!";

    public String getMessage() {
      return MESSAGE;
    }
  }
}
