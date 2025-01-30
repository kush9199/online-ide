package dev.learn.onlineide.utils;

import dev.learn.onlineide.domain.Program;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class CommonService {
    private CommonService() {}

    public static Process getProcess(String command, Program program, File tempPythonFile) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempPythonFile))) {
            writer.write(program.getCode());
        }
        ProcessBuilder processBuilder = new ProcessBuilder(command, tempPythonFile.getAbsolutePath());
        processBuilder.redirectErrorStream(true);


        return processBuilder.start();
    }

    public static Process getProcess(String command, Program program, File tempPythonFile, String path) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempPythonFile))) {
            writer.write(program.getCode());
        }
        ProcessBuilder processBuilder = new ProcessBuilder(
                command, tempPythonFile.getAbsolutePath(), "-o", path
        );
        processBuilder.redirectErrorStream(true);
        return processBuilder.start();
    }
}
