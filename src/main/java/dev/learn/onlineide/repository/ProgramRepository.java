package dev.learn.onlineide.repository;

import dev.learn.onlineide.domain.Program;
import dev.learn.onlineide.dto.ProgramRes;

public interface ProgramRepository {
    ProgramRes execute(Program program);
}
