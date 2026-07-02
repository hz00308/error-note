package org.scoula.error_note.service;

import org.scoula.error_note.dto.MemoDTO;

import java.util.List;

public interface MemoService {
    // 메모 등록
    boolean createMemo(MemoDTO memo);

    // 메모 삭제
    boolean deleteMemo(int memoId);

    // 메모 에러별 조회
    List<MemoDTO> getMemoByError(int errorId);
}
