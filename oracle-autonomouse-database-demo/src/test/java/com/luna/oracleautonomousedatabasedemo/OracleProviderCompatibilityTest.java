package com.luna.oracleautonomousedatabasedemo;

import org.junit.jupiter.api.Test;

class OracleProviderCompatibilityTest {

    @Test
    void ociProvidersInitializeWithJdbcDriver() throws ClassNotFoundException {
        Class.forName("oracle.jdbc.provider.oci.configuration.OciObjectStorageProvider");
        Class.forName("oracle.jdbc.provider.oci.configuration.OciVaultJsonProvider");
        Class.forName("oracle.jdbc.provider.oci.configuration.OciVaultSecretProvider");
        Class.forName("oracle.jdbc.provider.oci.configuration.OciDatabaseToolsConnectionProvider");
    }
}