package com.beaconfire.springsecurityauth.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ServiceStatus {
    private boolean success;
    private String message;
}
