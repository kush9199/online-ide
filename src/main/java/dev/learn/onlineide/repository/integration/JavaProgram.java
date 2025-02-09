package dev.learn.onlineide.repository.integration;

import dev.learn.onlineide.domain.Program;
import dev.learn.onlineide.dto.ProgramRes;
import dev.learn.onlineide.repository.ProgramFactory;
import dev.learn.onlineide.utils.CONSTANT;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

import static dev.learn.onlineide.utils.CommonService.getProcess;

// TODO: create exception handling

public class JavaProgram implements ProgramFactory {
    public JavaProgram() {}

    @Override
    public ProgramRes execute(Program program) {
        try {
            File tempJavaFile = File.createTempFile("tempScript", ".java");
            Process process = getProcess(CONSTANT.JAVA, program, tempJavaFile);

            boolean flag = process.waitFor(CONSTANT.TIMEOUT_DURATION, TimeUnit.SECONDS);
            if(!flag) {
                tempJavaFile.deleteOnExit();
                process.destroy();
                return new ProgramRes("", -1);
            }

            StringBuffer output = new StringBuffer();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                reader.lines().forEach(line -> output.append(line).append(System.lineSeparator()));
            }

            int exitCode = process.waitFor();
            ProgramRes res = new ProgramRes();
            tempJavaFile.deleteOnExit();
            return res;
        } catch (Exception e) {
            // custom exception web wali
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
