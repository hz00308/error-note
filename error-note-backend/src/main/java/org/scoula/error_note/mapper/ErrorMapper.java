package org.scoula.error_note.mapper;

import org.scoula.error_note.domain.ErrorVO;

import java.util.List;

public interface ErrorMapper {

    // 에러 전체 조회 /s
    List<ErrorVO> getErrorList();

    // 에러 카테고리별 조회 /s
    List<ErrorVO> getErrorListByCategory(int cid);
  
    // 에러 상세 조회
    ErrorVO getError(int eid);

    // 에러 등록
    int createError(ErrorVO error);

    // 에러 수정
    int updateError(ErrorVO error);

    // 에러 삭제 /s
    int deleteError(int eid);
}
