package org.scoula.error_note.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.error_note.dto.CategoryDTO;
import org.scoula.error_note.service.CategoryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    // 카테고리 등록
    @PostMapping("")
    public ResponseEntity<CategoryDTO> createCategory(@RequestBody CategoryDTO categoryDTO) {
        return ResponseEntity.ok(service.createCategory(categoryDTO));
    }

    // 카테고리 하나 조회
    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> getOneCategory(@PathVariable int categoryId) {
        return ResponseEntity.ok(service.getOneCategory(categoryId));
    }

    // 카테고리 수정
    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> updateCategory(
            @PathVariable int categoryId,
            @RequestBody CategoryDTO category
    ) {
        category.setCategoryId(categoryId); // url에서 받은 id를 DTO에 넣어줌 (Body에 categoryId가 없을 수 있기 때문)
        return ResponseEntity.ok(service.updateCategory(category));
    }

    // 카테고리 삭제
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<CategoryDTO> deleteCategory(@PathVariable int categoryId) {
        return ResponseEntity.ok(service.deleteCategory(categoryId));
    }
}
