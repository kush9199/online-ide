package dev.learn.onlineide.dto;

import lombok.Builder;
import lombok.Data;

public class ProgramRes {
    private String output;
    private int code;

    public ProgramRes() {}

    public ProgramRes(String output, int code) {
        this.output = output;
        this.code = code;
    }

    public String getOutput() {
        return output;
    }

    public void setOutput(String output) {
        this.output = output;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
