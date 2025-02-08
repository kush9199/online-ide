package dev.learn.onlineide.service;

import dev.learn.onlineide.domain.Program;
import dev.learn.onlineide.dto.ProgramRes;
import dev.learn.onlineide.repository.ProgramRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProgramService {
    private ProgramRepository programRepository;

    @Autowired
    public ProgramService(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    public ProgramRes execute(Program program) {
        // execution using repository
        // make object based on TYPE property of program

        return programRepository.getFactory(program.getType().name()).execute(program);
    }

}
