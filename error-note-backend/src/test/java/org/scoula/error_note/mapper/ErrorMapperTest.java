package org.scoula.error_note.mapper;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.config.RootConfig;
import org.scoula.error_note.domain.ErrorVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class})
@Log4j2
class ErrorMapperTest {

    @Autowired
    private ErrorMapper mapper;

    @Test
    void getError() {
        ErrorVO error = mapper.getError(1);
        log.info(error);
    }

    @Test
    void createError() {
        ErrorVO error = new ErrorVO();
        error.setCategoryId(1);
        error.setTitle("제목: 에러 등록 테스트 중");
        error.setContents("내용: 에러 등록 테스트 중");

        int cnt = mapper.createError(error);
        log.info(error); // ErrorVO의 ErrorID 자동으로 채워짐
        log.info("create count: " + cnt);
    }

    @Test
    void updateError() {
        ErrorVO error = new ErrorVO();
        error.setErrorId(11); // 위에서 추가한 에러(11번) 수정
        error.setCategoryId(3); // 3으로 수정
        error.setTitle("수정된 제목: 에러 수정 테스트 중");
        error.setContents("수정된 내용: 에러 수정 테스트 중");

        int cnt = mapper.updateError(error);
        log.info("update count: " + cnt);
    }
}