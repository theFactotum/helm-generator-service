package com.helm.hemgeneratorservice.builder;

import com.helm.hemgeneratorservice.model.HelmChartRequest;

import java.nio.file.Path;
import java.util.Map;

public interface HelmChartBuilder {

    public byte[] build(HelmChartRequest request);
    public Map<String, Object> buildDataMap(HelmChartRequest request) ;

    public byte[] transformTemplate(Map<String, Object> data, String fileContent);

}
