package org.scoula.error_note.service;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.config.RootConfig;
import org.scoula.error_note.dto.ErrorDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {RootConfig.class} )
@Log4j2

class ErrorServiceImplTest {

    @Autowired
    private ErrorService service;

    @Test
    void getErrorList() {
        List<ErrorDTO> list = service.getErrorList();
        for(ErrorDTO error : list){
            System.out.println(error);
        }
    }

    @Test
    void getErrorListByCategory() {
        List<ErrorDTO> list = service.getErrorListByCategory(2);
        for(ErrorDTO error : list){
            System.out.println(error);
        }
    }

    @Test
    void getError() {
        log.info(service.getError(1));
    }

    @Test
    void createError() {
        ErrorDTO error = new ErrorDTO();
        error.setCategoryId(2);
        error.setTitle("RootConfig에 ComponentScan 빠트림!!!");
        error.setContents("@Service 붙이고 나서 스캔하는 거 까먹지 말자");

        log.info(service.createError(error));
    }

    @Test
    void updateError() {
        ErrorDTO error = service.getError(1);
        error.setTitle("서비스에서 제목 수정 테스트 중입니다");
        log.info(service.updateError(error));
    }

    @Test
    void deleteError() {
        log.info("delete RESULT: " + service.deleteError(2));
    }
}