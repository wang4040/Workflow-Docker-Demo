package com.beaconfire.springsecurityauth.domain.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContentUpdateRequest {
    Integer id;
    String content;
}
