package org.scoula.error_note.service;

import org.scoula.error_note.domain.CategoryVO;
import org.scoula.error_note.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {
    List<CategoryDTO> getCategoryList();

    CategoryDTO createCategory(CategoryDTO category);

    CategoryDTO getOneCategory(int categoryId);

    CategoryDTO updateCategory(CategoryDTO category);

    CategoryDTO deleteCategory(int categoryId);
}
