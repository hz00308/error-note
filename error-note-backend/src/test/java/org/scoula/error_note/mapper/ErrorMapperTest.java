package org.scoula.error_note.mapper;

import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.scoula.config.RootConfig;
import org.scoula.error_note.domain.ErrorVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { RootConfig.class })
@Log4j2
class ErrorMapperTest {
    @Autowired
    private ErrorMapper mapper;


    @Test
    void getErrorList() {
        List<ErrorVO> list = mapper.getErrorList();
        for(ErrorVO error : list){
            log.info(error);
        }
    }

    @Test
    void getErrorListByCategory() {
        List<ErrorVO> list = mapper.getErrorListByCategory(2);
        for(ErrorVO error : list){
            log.info(error);
        }
    }

    @Test
    void deleteError() {
        int cnt = mapper.deleteError(3);
        log.info(cnt);
    }
}