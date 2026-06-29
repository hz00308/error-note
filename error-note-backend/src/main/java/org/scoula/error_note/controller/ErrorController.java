package org.scoula.error_note.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.error_note.dto.ErrorDTO;
import org.scoula.error_note.service.ErrorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/errors")
@RequiredArgsConstructor
@Log4j2
public class ErrorController {

    private final ErrorService service;

    // 에러 전체 조회


    // 에러 카테고리별 조회


    // 에러 상세 조회
    @GetMapping("/{eid}")
    public ResponseEntity<ErrorDTO> getError(@PathVariable int eid) {
        return ResponseEntity.ok(service.getError(eid));
    }


    // 에러 등록
    @PostMapping("")
    public ResponseEntity<ErrorDTO> createError(@RequestBody ErrorDTO error) {
        return ResponseEntity.ok(service.createError(error));
    }


    // 에러 수정
    @PutMapping("/{eid}")
    public ResponseEntity<ErrorDTO> updateErrpr(@PathVariable int eid,
                                                @RequestBody ErrorDTO error) {
        error.setErrorId(eid);
        return ResponseEntity.ok(service.updateError(error));
    }


    // 에러 삭제


}
