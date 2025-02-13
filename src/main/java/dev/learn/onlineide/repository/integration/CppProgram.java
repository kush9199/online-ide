package dev.learn.onlineide.repository.integration;

import dev.learn.onlineide.domain.Program;
import dev.learn.onlineide.dto.ProgramRes;
import dev.learn.onlineide.repository.IntegratedProgramFactory;
import dev.learn.onlineide.repository.ProgramFactory;
import dev.learn.onlineide.repository.ProgramRepository;
import dev.learn.onlineide.utils.CONSTANT;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.concurrent.TimeUnit;

import static dev.learn.onlineide.utils.CommonService.getProcess;

// TODO: create exception handling
public class CppProgram implements ProgramFactory {
    public CppProgram(){}

    @Override
    public ProgramRes execute(Program program) {
        System.out.println("CppProgram.execute");
        try {
        	File cppTempFile = File.createTempFile("tempFile", ".cpp");
            File tempExecFile = File.createTempFile("tempExec", ".exe", cppTempFile.getParentFile());
            Process process = getProcess(CONSTANT.CPP,  program, cppTempFile, tempExecFile.getAbsolutePath());
            boolean flag = process.waitFor(CONSTANT.TIMEOUT_DURATION, TimeUnit.SECONDS);
            if(!flag) {
                cppTempFile.deleteOnExit();
                tempExecFile.deleteOnExit();
                process.destroy();
                return new ProgramRes("", -1);
            }
            StringBuffer output = new StringBuffer();

            int exitCode = process.exitValue();
            ProcessBuilder pb2 = new ProcessBuilder(tempExecFile.getAbsolutePath());
            pb2.redirectErrorStream(true);
            Process process2 = pb2.start();

            try (BufferedReader reader = new BufferedReader(new InputStreamReader(process2.getInputStream()))) {
                reader.lines().forEach(line -> output.append(line).append(System.lineSeparator()));
            }
            ProgramRes resp = new ProgramRes();

            cppTempFile.deleteOnExit();
            tempExecFile.deleteOnExit();
            return resp;
        } catch (Exception e) {
            // custom exception web wali
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

}
