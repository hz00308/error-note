package org.scoula.error_note.mapper;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.config.RootConfig;
import org.scoula.error_note.domain.MemoVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class})
@Log4j2
class MemoMapperTest {

    @Autowired
    private MemoMapper mapper;

    @Test
    void createMemo() {
        MemoVO memo = new MemoVO();
        memo.setErrorId(2);
        memo.setContents("test메모내용입니다.");
        int result = mapper.createMemo(memo);
        assertEquals(1, result, "메모 등록에 실패했습니다.");
        log.info("등록된 메모 정보: " + memo);
    }
    @Test
    void deleteMemo() {
        int targetMemoId = 16;
        int result = mapper.deleteMemo(targetMemoId);
        assertEquals(1, result, "메모 삭제에 실패했습니다. 해당 ID가 없을 수 있습니다.");
        log.info("삭제된 메모 행 수: " + result);
    }
    @Test
    void selectMemo() {
        int targetErrorId = 2;
        List<MemoVO> list = mapper.getMemoByError(targetErrorId);

        assertNotNull(list, "조회된 리스트 객체가 null입니다.");
        assertFalse(list.isEmpty(), "해당 ErrorID에 등록된 메모가 없습니다.");

        log.info("조회된 메모 건수: " + list.size());
        for (MemoVO memo : list) {
            log.info("조회된 메모 상세: " + memo);
        }
    }
}