package com.helm.hemgeneratorservice.loader;

import com.helm.hemgeneratorservice.model.HelmChartRequest;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


@Service
public class HelmChartLoader {

    public static final String TEMPLATE_FILE="/opt/app"+ File.separator+"deployments.yml";

    public String loadTemplate(HelmChartRequest request) {
        try {
            System.out.println(TEMPLATE_FILE+" 1 *********************");
             Path template = Path.of(TEMPLATE_FILE);
            System.out.println(template.getFileSystem()+"*********************");
            String fileContent = Files.readString(template);
            System.out.println("\n \n ########## Templete File ###############: \n  "+ fileContent);
            return fileContent;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

    }


}
