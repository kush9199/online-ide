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

        // Build the process to execute the Python file
        ProcessBuilder processBuilder = new ProcessBuilder(command, tempPythonFile.getAbsolutePath());
        processBuilder.redirectErrorStream(true); // Redirect errors to the same output stream

        // Start the process
        return processBuilder.start();
    }
}
