package org.scoula.error_note.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.scoula.error_note.domain.CategoryVO;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryDTO {
    private int categoryId;
    private String categoryName;

    public CategoryVO toVO() {
        return CategoryVO.builder()
                .categoryId(categoryId)
                .categoryName(categoryName)
                .build();
    }

    public static CategoryDTO of(CategoryVO vo) {
        return vo == null ? null : CategoryDTO.builder()
                .categoryId(vo.getCategoryId())
                .categoryName(vo.getCategoryName())
                .build();
    }
}
