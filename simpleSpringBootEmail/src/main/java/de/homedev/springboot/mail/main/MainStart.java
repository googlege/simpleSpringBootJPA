
package de.homedev.springboot.mail.main;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.util.Assert;

@SpringBootApplication
@EnableAutoConfiguration
@PropertySource(value = "application.properties", ignoreResourceNotFound = false)
@ComponentScan(basePackages = { "de.homedev.springboot.mail" })
public class MainStart {
    private static final Logger log = LoggerFactory.getLogger(MainStart.class);
    private static Path filesDir;
    static {
        filesDir = Paths.get(System.getProperty("user.dir"), "files");
    }

    public static void main(String[] args) {
        try {
            ConfigurableApplicationContext ctx = SpringApplication.run(MainStart.class, args);
            EmailService emailService = ctx.getBean(EmailServiceImpl.class);
            Assert.notNull(emailService, "emailService is null");
            String to = "deleted";
            String subject = "Test EMail Subject";
            String text = "Test EMail Text";
            File file = filesDir.resolve("file1.png").toFile();
            if (file.isFile()) {
                FileSystemResource fileResource = new FileSystemResource(file);
                emailService.sendMessage(to, subject, text, fileResource);
                log.info("Message has been send with attachment");
            } else {
                emailService.sendMessage(to, subject, text);
                log.info("Message has been send without attachment");
            }

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
    }

}
