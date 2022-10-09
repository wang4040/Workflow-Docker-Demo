package com.beaconfire.springsecurityauth.domain.response;

import com.beaconfire.springsecurityauth.domain.Demo;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DemoResponse {
    private Demo demo;
}
