package dev.learn.onlineide.controller;

import dev.learn.onlineide.domain.Program;
import dev.learn.onlineide.dto.ProgramReq;
import dev.learn.onlineide.dto.ProgramRes;
import dev.learn.onlineide.service.ProgramService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController("api/v1/program")
public class ProgramController {
    private ProgramService programService;

	@Autowired
	public ProgramController(ProgramService programService) {
		this.programService = programService;
	}

    @PostMapping("/")
    public ResponseEntity<ProgramRes> userProgram(@RequestBody Program programReq){


        return ResponseEntity.ok(programService.execute(programReq));

    }
}
