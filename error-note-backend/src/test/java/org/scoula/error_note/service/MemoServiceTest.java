package org.scoula.error_note.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.scoula.config.RootConfig;
import org.scoula.error_note.dto.MemoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringJUnitConfig(classes = RootConfig.class)
@Log4j2
class MemoServiceTest {

    @Autowired
    private MemoService service;

    @Test
    void createMemo() {
        // 1번 에러노트에 메모를 등록한다고 가정
        MemoDTO memo = MemoDTO.builder()
                .errorId(1)
                .contents("JUnit 테스트 코드로 작성된 메모 본문입니다.?")
                .build();
        boolean result = service.createMemo(memo);
        assertTrue(result);

        log.info("메모 등록 성공", result);
    }

    @Test
    void deleteMemo() {
        // 삭제할 메모 ID 지정
        int memoId = 21;
        boolean isDeleted = service.deleteMemo(memoId);

        assertTrue(isDeleted);
        log.info("{}번 메모 삭제 결과: {}", memoId, isDeleted);
    }

    @Test
    void getMemoByError() {
        int errorId = 1;
        List<MemoDTO> list = service.getMemoByError(errorId);

        assertNotNull(list);
        list.forEach(memo -> log.info("조회된 메모: {}", memo));
    }
}