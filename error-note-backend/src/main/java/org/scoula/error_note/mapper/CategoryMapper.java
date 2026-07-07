package org.scoula.error_note.mapper;

import org.scoula.error_note.domain.CategoryVO;

import java.util.List;

public interface CategoryMapper {

    // 카테고리 전체 조회
    List<CategoryVO> getCategoryList();

    // 카테고리 등록
    int createCategory(CategoryVO category);

    // 카테고리 하나 조회
    CategoryVO getOneCategory(int categoryId);

    // 카테고리 수정
    int updateCategory(CategoryVO category);

    // 카테고리 삭제
    int deleteCategory(int categoryId);
}
