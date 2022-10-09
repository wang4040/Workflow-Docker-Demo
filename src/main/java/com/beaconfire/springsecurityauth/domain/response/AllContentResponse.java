package com.beaconfire.springsecurityauth.domain.response;

import com.beaconfire.springsecurityauth.domain.ServiceStatus;
import com.beaconfire.springsecurityauth.entity.Content;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class AllContentResponse {
    ServiceStatus serviceStatus;
    List<Content> contents;
}
