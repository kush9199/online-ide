package dev.learn.onlineide.service;

import dev.learn.onlineide.domain.Program;
import dev.learn.onlineide.repository.ProgramRepository;

public class ProgramService {
    private ProgramRepository programRepository;

    public ProgramService(ProgramRepository programRepository) {
        this.programRepository = programRepository;
    }

    public String execute(Program program) {
        // execution using repository
        // make object based on TYPE property of program
        return null;
    }

}
