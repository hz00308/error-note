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

import static org.junit.jupiter.api.Assertions.*;

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
}