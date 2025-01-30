package dev.learn.onlineide.repository.integration;

import dev.learn.onlineide.domain.Program;
import dev.learn.onlineide.dto.ProgramRes;
import dev.learn.onlineide.repository.ProgramRepository;
import dev.learn.onlineide.utils.CONSTANT;

import java.io.*;

import static dev.learn.onlineide.utils.CommonService.getProcess;

public class CppProgram implements ProgramRepository {
    @Override
    public ProgramRes execute(Program program) {
        try {
        	File tempPythonFile = File.createTempFile("tempScript", ".cpp");
            File tempExecFile = File.createTempFile("tempExec", ".exe", tempPythonFile.getParentFile());
            Process process = getProcess("g++",  program, tempPythonFile, tempExecFile.getAbsolutePath());
            StringBuffer output = new StringBuffer();

            int exitCode = process.waitFor();
            ProcessBuilder pb2 = new ProcessBuilder(tempExecFile.getAbsolutePath());
            pb2.redirectErrorStream(true);
            Process process2 = pb2.start();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process2.getInputStream()))) {
                reader.lines().forEach(line -> output.append(line).append(System.lineSeparator()));
            }
            ProgramRes resp = ProgramRes.builder().code(exitCode).output(output.toString()).build();

            tempPythonFile.deleteOnExit();
            tempExecFile.deleteOnExit();
            return resp;

        } catch (Exception e) {
            // custom exception web wali
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
