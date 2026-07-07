package org.scoula.error_note.mapper;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.config.RootConfig;
import org.scoula.error_note.domain.CategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class})
@Log4j2
class CategoryMapperTest {

    @Autowired
    private CategoryMapper mapper;

    @Test
    void getCategoryList() {
        List<CategoryVO> list = mapper.getCategoryList();
        for(CategoryVO category : list) {
            log.info(category);
        }
    }

    @Test
    void createCategory() {
        CategoryVO category = new CategoryVO();
        category.setCategoryName("JavaScript");
        int result = mapper.createCategory(category); // 영향받은 행 수
        log.info(result);
    }

    @Test
    void getOneCategory() {
        CategoryVO result = mapper.getOneCategory(2);
        log.info(result);
    }

    @Test
    void updateCategory() {
        CategoryVO category = new CategoryVO();
        category.setCategoryId(1);
        category.setCategoryName("수정된 카테고리");
        int result = mapper.updateCategory(category);
        log.info("수정 행 수 : " + result);
    }

    @Test
    void deleteCategory() {
        int categoryId = 1;
        int result = mapper.deleteCategory(categoryId);
        log.info("삭제 결과: " + result);
    }

}