package org.scoula.error_note.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.error_note.dto.CategoryDTO;
import org.scoula.error_note.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
@Log4j2
public class CategoryController {

    private final CategoryService service;

    // 카테고리 전체 조회
    @GetMapping("")
    public ResponseEntity<List<CategoryDTO>> getCategoryList() {
        return ResponseEntity.ok(service.getCategoryList());
    }




}
