package org.scoula.error_note.mapper;

import org.scoula.error_note.domain.ErrorVO;

public interface ErrorMapper {

    // 에러 상세 조회
    ErrorVO getError(int eid);

    // 에러 등록
    int createError(ErrorVO error);

    // 에러 수정
    int updateError(ErrorVO error);
}
