package org.scoula.error_note.mapper;

import org.scoula.error_note.domain.CategoryVO;

import java.util.List;

public interface CategoryMapper {

    // 카테고리 전체 조회
    List<CategoryVO> getCategoryList();

}
