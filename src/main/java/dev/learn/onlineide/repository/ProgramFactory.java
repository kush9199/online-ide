package dev.learn.onlineide.repository;

import dev.learn.onlineide.domain.Program;
import dev.learn.onlineide.dto.ProgramRes;

public interface ProgramFactory {
    ProgramRes execute(Program program);
}
