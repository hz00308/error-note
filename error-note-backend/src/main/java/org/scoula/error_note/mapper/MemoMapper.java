package org.scoula.error_note.mapper;

import org.scoula.error_note.domain.MemoVO;

import java.util.List;

public interface MemoMapper {

    // 메모 등록
    //int createMemo(MemoVO memo);
    public int createMemo(MemoVO memo);

    // 메모 삭제
    int deleteMemo(int memoId);

    //메모 에러별 조회
    List<MemoVO> getMemoByError(int errorId);
}
