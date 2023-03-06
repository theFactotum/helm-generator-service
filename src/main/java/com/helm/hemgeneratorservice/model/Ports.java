package com.helm.hemgeneratorservice.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Ports {
    private String name;
    private String containerPort;
    private String protocol;


}