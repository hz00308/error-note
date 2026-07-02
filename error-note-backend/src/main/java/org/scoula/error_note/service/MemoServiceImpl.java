package org.scoula.error_note.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.error_note.domain.MemoVO;
import org.scoula.error_note.dto.MemoDTO;
import org.scoula.error_note.mapper.MemoMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class MemoServiceImpl implements MemoService{

    private final MemoMapper mapper;

    @Override
    public boolean createMemo(MemoDTO memo){
        // dto를 vo로 변환해서 db에 넣기
        MemoVO vo = memo.toVO();
        return mapper.createMemo(vo) > 0;
    }

   @Override
   public boolean deleteMemo(int memoId) {
        // 성공하면 삭제된 행의 개수(1)가 반환됨
        return mapper.deleteMemo(memoId) > 0;
   }

   @Override
   public List<MemoDTO> getMemoByError(int errorId) {
        List<MemoVO> voList = mapper.getMemoByError(errorId);
        return voList.stream()
                .map(MemoDTO::of)
                .toList();
   }
}
