package com.beaconfire.springsecurityauth.domain.response;

import com.beaconfire.springsecurityauth.domain.ServiceStatus;
import com.beaconfire.springsecurityauth.entity.Content;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ContentResponse {
    ServiceStatus serviceStatus;
    Content content;
}
