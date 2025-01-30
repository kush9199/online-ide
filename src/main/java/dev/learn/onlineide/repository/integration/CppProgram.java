package dev.learn.onlineide.repository.integration;

import dev.learn.onlineide.domain.Program;
import dev.learn.onlineide.dto.ProgramRes;
import dev.learn.onlineide.repository.ProgramRepository;
import dev.learn.onlineide.utils.CONSTANT;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import static dev.learn.onlineide.utils.CommonService.getProcess;

public class CppProgram implements ProgramRepository {
    @Override
    public ProgramRes execute(Program program) {
        try {
        	File tempPythonFile = File.createTempFile("tempScript", ".cpp");
            Process process = getProcess("g++", code, tempPythonFile);

            StringBuffer output = new StringBuffer();
            // Capture and print the output
//            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
//                reader.lines().forEach(line -> output.append(line).append(System.lineSeparator()));
//            }

            // Wait for the process to complete
            int exitCode = process.waitFor();

            System.out.println(output.toString());
            System.out.println(exitCode);

            // Delete the temporary file
            tempPythonFile.deleteOnExit();

        } catch (Exception e) {
            // custom exception web wali
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
    
    public static Process getProcess(String command, String code, File tempPythonFile) throws IOException {
        // Write the C++ code to the temporary file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempPythonFile))) {
            writer.write(code);
        }

        // Specify the output path for the compiled executable
        File tempExecFile = File.createTempFile("tempExec", ".exe", tempPythonFile.getParentFile());
        tempExecFile.delete();  // Delete the temp file immediately so it can be used as output

        // Build the process to execute the C++ file with the specified output executable path
        ProcessBuilder processBuilder = new ProcessBuilder(
            command, tempPythonFile.getAbsolutePath(), "-o", tempExecFile.getAbsolutePath()
        );
        processBuilder.redirectErrorStream(true); // Redirect errors to the same output stream

        // Start the process
        return processBuilder.start();
    }
}
