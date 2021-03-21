package org.kd.webservice.main;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"org.kd.webservice"})
@EntityScan("org.kd.webservice.entities")
public class Webservice {
    private static final String port = "8081";

    private static ConfigurableApplicationContext context;

    public static void main(String[] args) {
        context = SpringApplication.run(Webservice.class, args);
        writeInfo();
    }

    public static void stop() {
        context.stop();
        context.close();
    }

    private static void writeInfo() {
        LoggerFactory.getLogger(Webservice.class).info("\n\n\nSERVER STARTED");
    }

    public static String getPort() {
        return port;
    }
}
