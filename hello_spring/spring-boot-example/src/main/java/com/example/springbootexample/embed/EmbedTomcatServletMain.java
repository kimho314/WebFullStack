package com.example.springbootexample.embed;

import com.example.springbootexample.servlet.HelloServlet;
import java.io.File;
import org.apache.catalina.Context;
import org.apache.catalina.Host;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.connector.Connector;
import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.autoconfigure.web.ServerProperties;

public class EmbedTomcatServletMain {

    public static void main(String[] args) throws LifecycleException {
        System.out.println("EmbedTomcatServletMain.main");
        Tomcat tomcat = new Tomcat();
        Connector connector = new Connector();
        connector.setPort(8080);
        tomcat.setConnector(connector);

        // register servlet
        Context context = tomcat.addContext("", "/");

        File docBaseFile = new File(context.getDocBase());
        if(!docBaseFile.isAbsolute()){
            docBaseFile = new File(((Host) context.getParent()).getAppBaseFile(), docBaseFile.getPath());
        }
        docBaseFile.mkdirs();

        tomcat.addServlet("","helloServlet", new HelloServlet());
        context.addServletMappingDecoded("/hello-servlet", "helloServlet");
        tomcat.start();
    }

}
