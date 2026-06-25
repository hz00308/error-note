package org.scoula.error_note.service;

import org.scoula.error_note.dto.ErrorDTO;

import java.util.List;

public interface ErrorService {
    // 에러 전체 조회
    List<ErrorDTO> getErrorList();

    // 에러 카테고리별 조회
    List<ErrorDTO> getErrorListByCategory(int cid);

    // 에러 상세 조회
    ErrorDTO getError(int eid);

    // 에러 등록
    ErrorDTO createError(ErrorDTO error);

    // 에러 수정
    ErrorDTO updateError(ErrorDTO error);

    // 에러 삭제
    ErrorDTO deleteError(int eid);

}
