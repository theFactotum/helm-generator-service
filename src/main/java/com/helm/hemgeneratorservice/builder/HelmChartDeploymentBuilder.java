package com.helm.hemgeneratorservice.builder;

import com.helm.hemgeneratorservice.loader.HelmChartLoader;
import com.helm.hemgeneratorservice.model.HelmChartRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;


@Component
public class HelmChartDeploymentBuilder implements  HelmChartBuilder{

    @Autowired
    HelmChartLoader helmChartLoader;

    @Override
    public byte[] build(HelmChartRequest request) {

        String fileContent = helmChartLoader.loadTemplate(request);
        Map<String, Object> dataMap = buildDataMap(request);
        return transformTemplate(dataMap, fileContent);
    }



    @Override
    public  Map<String, Object> buildDataMap(HelmChartRequest request) {

        Map<String, Object> data = new HashMap<>();
        data.put("apiVersion", request.getApiVersion());
        data.put("kind", request.getKind());
        data.put("metadata.name", request.getMetadata().getName());
        data.put("metadata.labels", request.getMetadata().getLabels());
        data.put("specs.replicas",request.getSpecs().getReplicas());
        data.put("specs.selector.matchLabels",request.getSpecs().getSelector().getMatchLabels());
        data.put("specs.template.metadata.labels",request.getSpecs().getTemplate().getMetadata().getLabels());
        data.put("specs.template.spec.serviceAccountName",request.getSpecs().getTemplate().getSpec().getServiceAccountName());
        data.put("specs.template.spec.containers.name",request.getSpecs().getTemplate().getSpec().getContainers().getName());
        data.put("specs.template.spec.containers.image.repository",request.getSpecs().getTemplate().getSpec().getContainers().getImage());
        data.put("specs.template.spec.containers.imagePullPolicy",request.getSpecs().getTemplate().getSpec().getContainers().getImagePullPolicy());
        data.put("specs.template.spec.containers.ports.name",request.getSpecs().getTemplate().getSpec().getContainers().getPorts().getName());
        data.put("specs.template.spec.containers.ports.containerPort",request.getSpecs().getTemplate().getSpec().getContainers().getPorts().getContainerPort());
        data.put("specs.template.spec.containers.ports.protocol",request.getSpecs().getTemplate().getSpec().getContainers().getPorts().getProtocol());

        return data;


    }

    @Override
    public byte[] transformTemplate(Map<String, Object> data, String fileContent) {
        try {


            fileContent = fileContent.replace("${apiVersion}", data.get("apiVersion").toString()).
                    replace("${kind}", data.get("kind").toString()).
                    replace("${metadata.name}", data.get("metadata.name").toString()).
                    replace("${metadata.labels}", data.get("metadata.labels").toString()).
                    replace("${specs.replicas}", data.get("specs.replicas").toString()).
                    replace("${specs.selector.matchLabels}", data.get("specs.selector.matchLabels").toString()).
            replace("${specs.template.metadata.labels}", data.get("specs.template.metadata.labels").toString()).
            replace("${specs.template.spec.serviceAccountName}", data.get("specs.template.spec.serviceAccountName").toString()).
            replace("${specs.template.spec.containers.name}", data.get("specs.template.spec.containers.name").toString()).
            replace("${specs.template.spec.containers.image.repository}", data.get("specs.template.spec.containers.image.repository").toString()).
            replace("${specs.template.spec.containers.imagePullPolicy}", data.get("specs.template.spec.containers.imagePullPolicy").toString()).
            replace("${specs.template.spec.containers.ports.name}", data.get("specs.template.spec.containers.ports.name").toString()).replace("${specs.template.spec.containers.ports.containerPort}", data.get("specs.template.spec.containers.ports.containerPort").toString()).
            replace("${specs.template.spec.containers.ports.protocol}", data.get("specs.template.spec.containers.ports.protocol").toString());


            //Files.write(output, fileContent.getBytes());
            System.out.println("File is generated using template ");

            return fileContent.getBytes();

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
}
