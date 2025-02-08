package dev.learn.onlineide.domain;

import dev.learn.onlineide.utils.TYPE;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Component
public class Program {
    private String code;
    private TYPE type;
    private String input;
    private String output;

    public Program() {
    }

    public Program(String code, TYPE type, String input, String output) {
        this.code = code;
        this.type = type;
        this.input = input;
        this.output = output;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }
}
