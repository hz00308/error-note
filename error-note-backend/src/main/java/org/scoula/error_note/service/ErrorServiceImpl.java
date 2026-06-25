package org.scoula.error_note.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.error_note.dto.ErrorDTO;
import org.scoula.error_note.mapper.ErrorMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service // 스프링 시작할 때 스캔해서 싱글톤으로 만들어줌.
@RequiredArgsConstructor //생성자호출할 때 Autowired해줌.
@Log4j2
public class ErrorServiceImpl implements ErrorService{

    private final ErrorMapper mapper;

    @Override // s
    public List<ErrorDTO> getErrorList() {
        return mapper.getErrorList().stream().map(ErrorDTO::of).toList();
    }

    @Override // s
    public List<ErrorDTO> getErrorListByCategory(int cid) {
        return mapper.getErrorListByCategory(cid).stream().map(ErrorDTO::of).toList();
    }

    @Override
    public ErrorDTO getError(int eid) {
        return null;
    }

    @Override
    public ErrorDTO createError(ErrorDTO error) {
        return null;
    }

    @Override
    public ErrorDTO updateError(ErrorDTO error) {
        return null;
    }

    @Override //s
    public ErrorDTO deleteError(int eid) {
        return null;
    }
}
