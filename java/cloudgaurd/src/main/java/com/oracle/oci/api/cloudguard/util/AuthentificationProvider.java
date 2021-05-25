package com.oracle.oci.api.cloudguard.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import com.google.common.base.Supplier;
import com.oracle.bmc.ConfigFileReader;
import com.oracle.bmc.ConfigFileReader.ConfigFile;
import com.oracle.bmc.Region;
import com.oracle.bmc.auth.AuthenticationDetailsProvider;
import com.oracle.bmc.auth.SimpleAuthenticationDetailsProvider;
import com.oracle.bmc.auth.SimplePrivateKeySupplier;
import com.oracle.oci.api.cloudguard.controller.CloudGuardTargetController;

import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

@Component
public class AuthentificationProvider {
    Logger logger = LoggerFactory.getLogger(CloudGuardTargetController.class);
    
    public AuthenticationDetailsProvider getAuthenticationDetailsProvider() throws IOException {

        ClassPathResource configResource = new ClassPathResource("config");
        ClassPathResource ociApiKeyResource = new ClassPathResource("oci_api_key.pem");

        InputStream configIs = configResource.getInputStream();
        File tempConfigFile = File.createTempFile("config", "");

        InputStream ociApiKeyIs = ociApiKeyResource.getInputStream();
        File tempOCIAPIKey = File.createTempFile("oci_api_key", "pem");

        try {
            FileUtils.copyInputStreamToFile(configIs, tempConfigFile);
            FileUtils.copyInputStreamToFile(ociApiKeyIs, tempOCIAPIKey);
        } finally {
            IOUtils.closeQuietly(configIs);
            IOUtils.closeQuietly(ociApiKeyIs);
        }

        ConfigFile config = ConfigFileReader.parse(tempConfigFile.getPath(), "DEFAULT");

        Supplier<InputStream> privateKeySupplier = new SimplePrivateKeySupplier(tempOCIAPIKey.getPath());
        
        AuthenticationDetailsProvider provider = SimpleAuthenticationDetailsProvider.builder()
        .tenantId(config.get("tenancy"))
        .userId(config.get("user"))
        .fingerprint(config.get("fingerprint"))
        .privateKeySupplier(privateKeySupplier)
        .region(Region.US_ASHBURN_1)
        .build();
        
        return provider;
    }
}