package org.scoula.error_note.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.scoula.error_note.domain.ErrorVO;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ErrorDTO {
    private int errorId;
    private int categoryId;
    private String categoryName;
    private String title;
    private String contents;
    private Date createdAt;
    private Date updatedAt;

    public ErrorVO toVO() {
        return ErrorVO.builder()
                .errorId(errorId)
                .categoryId(categoryId)
                .categoryName(categoryName)
                .title(title)
                .contents(contents)
                .createdAt(createdAt)
                .updatedAt(updatedAt)
                .build();
    }

    public static ErrorDTO of(ErrorVO vo) {
        return vo == null ? null : ErrorDTO.builder()
                .errorId(vo.getErrorId())
                .categoryId(vo.getCategoryId())
                .categoryName(vo.getCategoryName())
                .title(vo.getTitle())
                .contents(vo.getContents())
                .createdAt(vo.getCreatedAt())
                .updatedAt(vo.getUpdatedAt())
                .build();
    }
}
