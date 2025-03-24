package com.example.troubleshootingjava.ch11_1.service;

import com.sun.management.HotSpotDiagnosticMXBean;
import java.io.File;
import java.io.IOException;
import java.lang.management.ManagementFactory;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class JmxService {
    private HotSpotDiagnosticMXBean mbean = null;

    public void dumpHeap(String outputFile, boolean live) {
        if (mbean == null) {
            try {
                mbean = ManagementFactory.newPlatformMXBeanProxy(
                    ManagementFactory.getPlatformMBeanServer(),
                    "com.sun.management:type=HotSpotDiagnostic",
                    HotSpotDiagnosticMXBean.class);
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        }
        try {
            String myFile = outputFile;
            if (!myFile.endsWith(".hprof")) {
                myFile = outputFile + ".hprof";
            }
            mbean.dumpHeap(myFile, live);
            if (!Objects.equals(outputFile, myFile)) {
                File generatedFile = new File(myFile);
                File newFile = new File(outputFile);
                generatedFile.renameTo(newFile);
            }
        }
        catch (IOException e) {
            log.error(e.getMessage(), e);
        }
    }
}
