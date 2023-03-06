package com.helm.hemgeneratorservice.controller;

import com.helm.hemgeneratorservice.builder.HelmChartDeploymentBuilder;
import com.helm.hemgeneratorservice.model.HelmChartRequest;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

    @RestController
    @RequestMapping("/generate")
    public class HelmGenerateController {

        @Autowired
        HelmChartDeploymentBuilder helmChartDeploymentBuilder;

        @GetMapping(value="/helloZip",  produces = "application/zip")
        public byte[] helloMsg(@RequestBody HelmChartRequest request, HttpServletResponse response) throws IOException {

            response.setStatus(HttpServletResponse.SC_OK);
            response.addHeader("Content-Disposition", "attachment; filename=\"helm.zip\"");

            // Creating byteArray stream, make it bufferable and passing this buffer to ZipOutputStream
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(byteArrayOutputStream);
            ZipOutputStream zipOutputStream = new ZipOutputStream(bufferedOutputStream);

            byte[] fileResponse = helmChartDeploymentBuilder.build(request);

            // Simple file list, just for tests
            ArrayList<File> files = new ArrayList<>(2);
            //files.add(new File("C:\\Rakesh_MauryaDrive\\tem\\v1\\READ.md"));

            zipOutputStream.putNextEntry(new ZipEntry("chart.yml"));
            ByteArrayInputStream byeInputStream = new ByteArrayInputStream(fileResponse);

            IOUtils.copy(byeInputStream, zipOutputStream);

            // Packing files
            for (File file : files) {
                // New zip entry and copying InputStream with file to ZipOutputStream, after all closing streams
                zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
                FileInputStream fileInputStream = new FileInputStream(file);

                IOUtils.copy(fileInputStream, zipOutputStream);

                fileInputStream.close();
                zipOutputStream.closeEntry();
            }

            if (zipOutputStream != null) {
                zipOutputStream.finish();
                zipOutputStream.flush();
                IOUtils.closeQuietly(zipOutputStream);
            }
            IOUtils.closeQuietly(bufferedOutputStream);
            IOUtils.closeQuietly(byteArrayOutputStream);

            return byteArrayOutputStream.toByteArray();
        }


        @GetMapping(value = "/zip",  produces = "application/zip")
        public byte[] zipFiles(HttpServletRequest request, HttpServletResponse response) throws IOException {

            response.setStatus(HttpServletResponse.SC_OK);
            response.addHeader("Content-Disposition", "attachment; filename=\"helm.zip\"");

            // Creating byteArray stream, make it bufferable and passing this buffer to ZipOutputStream
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(byteArrayOutputStream);
            ZipOutputStream zipOutputStream = new ZipOutputStream(bufferedOutputStream);

            // Simple file list, just for tests
            ArrayList<File> files = new ArrayList<>(2);
            files.add(new File("README.md"));

            // Packing files
            for (File file : files) {
                // New zip entry and copying InputStream with file to ZipOutputStream, after all closing streams
                zipOutputStream.putNextEntry(new ZipEntry(file.getName()));
                FileInputStream fileInputStream = new FileInputStream(file);

                IOUtils.copy(fileInputStream, zipOutputStream);

                fileInputStream.close();
                zipOutputStream.closeEntry();
            }

            if (zipOutputStream != null) {
                zipOutputStream.finish();
                zipOutputStream.flush();
                IOUtils.closeQuietly(zipOutputStream);
            }
            IOUtils.closeQuietly(bufferedOutputStream);
            IOUtils.closeQuietly(byteArrayOutputStream);

            return byteArrayOutputStream.toByteArray();
        }

}
