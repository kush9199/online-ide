package dev.learn.onlineide;

import ch.qos.logback.core.net.SyslogOutputStream;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

@SpringBootApplication
public class OnlineIdeApplication {

    public static void main(String[] args) {
//        SpringApplication.run(OnlineIdeApplication.class, args);
        String cppFilePath = "src\\main\\java\\dev\\learn\\onlineide\\Main.cpp";
        String outputExecutable = "example";

    }

    public static String getPath(String fileName) {
        Path path = Paths.get(fileName);
        return path.toAbsolutePath().toString();
    }
}
