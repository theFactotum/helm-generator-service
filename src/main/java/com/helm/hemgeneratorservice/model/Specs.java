package com.helm.hemgeneratorservice.model;


import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Specs {
    private String replicas;
    Selector selector;
    Template template;


}
