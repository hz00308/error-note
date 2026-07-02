package org.scoula.error_note.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.scoula.error_note.domain.ErrorVO;
import org.scoula.error_note.domain.MemoVO;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemoDTO {
    private int memoId;
    private int errorId;
    private String contents;
    private Date createdAt;

    public MemoVO toVO(){
        return MemoVO.builder()
                .memoId(memoId)
                .errorId(errorId)
                .contents(contents)
                .createdAt(createdAt)
                .build();
    }

    public static MemoDTO of(MemoVO vo) {
        return vo == null ? null : MemoDTO.builder()
                                   .memoId(vo.getMemoId())
                                   .errorId(vo.getErrorId())
                                   .contents(vo.getContents())
                                   .createdAt(vo.getCreatedAt())
                                   .build();
    }
}
