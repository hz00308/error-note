package org.scoula.error_note.mapper;

import org.scoula.error_note.domain.ErrorVO;

import java.util.List;

public interface ErrorMapper {

    // 에러 전체 조회
    List<ErrorVO> getErrorList();

    // 에러 카테고리별 조회
    List<ErrorVO> getErrorListByCategory(int cid);

    // 에러 삭제
    int deleteError(int eid);
}
