package com.beaconfire.springsecurityauth.domain.response;

import com.beaconfire.springsecurityauth.domain.ServiceStatus;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MessageResponse {
    ServiceStatus serviceStatus;
    String message;
}
