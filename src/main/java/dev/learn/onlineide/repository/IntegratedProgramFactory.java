package dev.learn.onlineide.repository;

import dev.learn.onlineide.exception.InvalidRepositoryType;
import dev.learn.onlineide.repository.integration.JavaProgram;
import dev.learn.onlineide.repository.integration.PythonProgram;
import dev.learn.onlineide.repository.integration.CppProgram;
import dev.learn.onlineide.utils.TYPE;
import org.springframework.stereotype.Component;

@Component
public class IntegratedProgramFactory implements ProgramRepository{
    @Override
    public ProgramFactory getFactory(String type) throws InvalidRepositoryType {
        if (type.equalsIgnoreCase(TYPE.CPP.name())){
            return new CppProgram();
        } else if (type.equalsIgnoreCase(TYPE.PYTHON.name())){
            return new PythonProgram();
        } else if (type.equalsIgnoreCase(TYPE.JAVA.name())){
            return new JavaProgram();
        }
        throw new InvalidRepositoryType("The repository type " + type + " is not supported");
    }

}
