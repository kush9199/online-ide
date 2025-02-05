package dev.learn.onlineide.repository.integration;

import dev.learn.onlineide.domain.Program;
import dev.learn.onlineide.dto.ProgramRes;
import dev.learn.onlineide.repository.ProgramRepository;
import dev.learn.onlineide.utils.CONSTANT;

import java.io.*;

import static dev.learn.onlineide.utils.CommonService.getProcess;

// TODO: create exception handling
public class PythonProgram implements ProgramRepository {
    @Override
    public ProgramRes execute(Program program) {
        try {
           File tempPythonFile = File.createTempFile("tempScript", ".py");
            Process process = getProcess(CONSTANT.PYTHON, program, tempPythonFile);
            StringBuffer output = new StringBuffer();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()))) {
                reader.lines().forEach(line -> output.append(line).append(System.lineSeparator()));
            }

            int exitCode = process.waitFor();
            ProgramRes res = ProgramRes.builder().output(output.toString()).code(exitCode).build();
            tempPythonFile.deleteOnExit();
            return res;
            
        } catch (Exception e) {
            // custom exception web wali
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
