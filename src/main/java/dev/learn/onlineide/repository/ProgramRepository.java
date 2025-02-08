package dev.learn.onlineide.repository;

import dev.learn.onlineide.domain.Program;
import dev.learn.onlineide.dto.ProgramRes;
import org.springframework.stereotype.Repository;

@Repository
public interface ProgramRepository {
    ProgramFactory getFactory(String type);
}
