package org.scoula.error_note.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.scoula.config.RootConfig;
import org.scoula.error_note.dto.CategoryDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Log4j2
@SpringJUnitConfig
@ContextConfiguration(classes = RootConfig.class)
class CategoryServiceTest {

    @Autowired
    private CategoryService categoryService;

    @Test
    void getCategoryList() {
        List<CategoryDTO> list = categoryService.getCategoryList();

        assertNotNull(list);

        list.forEach(category -> log.info(category));
    }

    @Test
    void createCategory() {
        CategoryDTO category = CategoryDTO.builder()
                .categoryName("테스트 카테고리")
                .build();

        CategoryDTO result = categoryService.createCategory(category);

        assertNotNull(result);
        assertEquals("테스트 카테고리", result.getCategoryName());

        log.info(result);
    }

    @Test
    void getOneCategory() {
        int categoryId = 1;

        CategoryDTO category = categoryService.getOneCategory(categoryId);

        assertNotNull(category);
        log.info(category);
    }

    @Test
    void updateCategory() {
        CategoryDTO category = CategoryDTO.builder()
                .categoryId(1)
                .categoryName("수정된 카테고리")
                .build();

        CategoryDTO result = categoryService.updateCategory(category);

        assertNotNull(result);
        assertEquals("수정된 카테고리", result.getCategoryName());

        log.info(result);
    }

    @Test
    void deleteCategory() {
        int categoryId = 3;

        CategoryDTO result = categoryService.deleteCategory(categoryId);

        assertNotNull(result);
        log.info(result);
    }
}