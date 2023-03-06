package com.helm.hemgeneratorservice.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Containers {
    private String name;
    private String image;
    private String imagePullPolicy;
    Ports ports;
    LivenessProbe livenessProbe;
    ReadinessProbe readinessProbe;


}