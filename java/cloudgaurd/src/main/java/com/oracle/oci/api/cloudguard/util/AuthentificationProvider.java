package com.oracle.oci.api.cloudguard.util;

import java.io.IOException;
import java.io.InputStream;

import com.google.common.base.Supplier;
import com.oracle.bmc.ConfigFileReader;
import com.oracle.bmc.ConfigFileReader.ConfigFile;
import com.oracle.bmc.Region;
import com.oracle.bmc.auth.AuthenticationDetailsProvider;
import com.oracle.bmc.auth.SimpleAuthenticationDetailsProvider;
import com.oracle.bmc.auth.SimplePrivateKeySupplier;

import org.springframework.stereotype.Component;

@Component
public class AuthentificationProvider {

    public AuthenticationDetailsProvider getAuthenticationDetailsProvider() throws IOException {
        ConfigFile config = ConfigFileReader.parse("~/.oci/config", "DEFAULT");

        Supplier<InputStream> privateKeySupplier = new SimplePrivateKeySupplier(config.get("key_file"));
    
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