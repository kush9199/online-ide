package dev.learn.onlineide.repository.integration;

import dev.learn.onlineide.domain.Program;
import dev.learn.onlineide.dto.ProgramRes;
import dev.learn.onlineide.repository.ProgramFactory;
import dev.learn.onlineide.utils.CONSTANT;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import static dev.learn.onlineide.utils.CommonService.getProcess;

// TODO: create exception handling

public class JavaProgram implements ProgramFactory {
    public JavaProgram() {}

    @Override
    public ProgramRes execute(Program program) {
        try {
            File tempPythonFile = File.createTempFile("tempScript", ".java");
            Process process = getProcess(CONSTANT.JAVA, program, tempPythonFile);
            StringBuffer output = new StringBuffer();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                reader.lines().forEach(line -> output.append(line).append(System.lineSeparator()));
            }

            int exitCode = process.waitFor();
            ProgramRes res = new ProgramRes();
            tempPythonFile.deleteOnExit();
            return res;

        } catch (Exception e) {
            // custom exception web wali
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
