package org.scoula.error_note.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.error_note.domain.CategoryVO;
import org.scoula.error_note.dto.CategoryDTO;
import org.scoula.error_note.mapper.CategoryMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryMapper mapper;

    @Override
    public List<CategoryDTO> getCategoryList() {
        return mapper.getCategoryList()
                .stream()
                .map(CategoryDTO::of)
                .toList();
    }

    @Override
    public CategoryDTO createCategory(CategoryDTO category) {
        CategoryVO vo = category.toVO();
        mapper.createCategory(vo);
        return CategoryDTO.of(vo);
    }

    @Override
    public CategoryDTO getOneCategory(int categoryId) {
        CategoryVO vo = mapper.getOneCategory(categoryId);
        return CategoryDTO.of(vo);
    }

    @Override
    public CategoryDTO updateCategory(CategoryDTO category) {
        CategoryVO vo = category.toVO();
        mapper.updateCategory(vo);
        return CategoryDTO.of(vo);
    }

    @Override
    public CategoryDTO deleteCategory(int categoryId) {
        CategoryVO vo = mapper.getOneCategory(categoryId);
        mapper.deleteCategory(categoryId);
        return CategoryDTO.of(vo);
    }
}
