package com.helm.hemgeneratorservice.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Spec {
    private String serviceAccountName;
    Containers Containers;


}
