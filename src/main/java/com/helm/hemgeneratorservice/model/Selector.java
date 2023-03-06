package com.helm.hemgeneratorservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Selector {
    @JsonProperty
    private String matchLabels;


}
