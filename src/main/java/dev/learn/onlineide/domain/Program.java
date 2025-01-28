package dev.learn.onlineide.domain;

import dev.learn.onlineide.utils.TYPE;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Program {
    private String code;
    private TYPE type;
    private String input;
    private String output;
}
