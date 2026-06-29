package org.scoula.error_note.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.error_note.service.ErrorService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/errors")
@RequiredArgsConstructor
@Log4j2
public class ErrorController {

    private final ErrorService service;

    // 에러 전체 조회


    // 에러 카테고리별 조회


    // 에러 상세 조회


    // 에러 등록


    // 에러 수정


    // 에러 삭제


}
