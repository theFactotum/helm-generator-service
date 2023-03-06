package com.helm.hemgeneratorservice.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HttpGet {
    private String path;
    private String port;


}
