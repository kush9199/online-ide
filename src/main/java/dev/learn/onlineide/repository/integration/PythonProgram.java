package dev.learn.onlineide.repository.integration;

import dev.learn.onlineide.domain.Program;
import dev.learn.onlineide.dto.ProgramRes;
import dev.learn.onlineide.repository.ProgramFactory;
import dev.learn.onlineide.utils.CONSTANT;

import java.io.*;
import java.util.concurrent.TimeUnit;

import static dev.learn.onlineide.utils.CommonService.getProcess;

// TODO: create exception handling
public class PythonProgram implements ProgramFactory {
    public PythonProgram() {}

    @Override
    public ProgramRes execute(Program program) {
        try {
           File tempPythonFile = File.createTempFile("tempScript", ".py");
            Process process = getProcess(CONSTANT.PYTHON, program, tempPythonFile);

            boolean flag = process.waitFor(CONSTANT.TIMEOUT_DURATION, TimeUnit.SECONDS);
            if(!flag) {
                tempPythonFile.deleteOnExit();
                process.destroy();
                return new ProgramRes("", -1);
            }

            StringBuffer output = new StringBuffer();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                reader.lines().forEach(line -> output.append(line).append(System.lineSeparator()));
            } catch (Exception e) {
                // response banake bhejna h bhar
                e.printStackTrace();
            }

            int exitCode = process.exitValue();
            ProgramRes res = new ProgramRes(output.toString(), exitCode);
            tempPythonFile.deleteOnExit();
            return res;
            
        } catch (Exception e) {
            // custom exception web wali
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
