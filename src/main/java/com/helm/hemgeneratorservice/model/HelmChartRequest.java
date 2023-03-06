package com.helm.hemgeneratorservice.model;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HelmChartRequest {
 private String apiVersion;
 private String kind;
 Metadata metadata;
 Specs specs;

}

