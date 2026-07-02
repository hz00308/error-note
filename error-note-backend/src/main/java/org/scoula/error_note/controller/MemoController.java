package org.scoula.error_note.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.error_note.dto.MemoDTO;
import org.scoula.error_note.service.MemoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/memos")
@RequiredArgsConstructor
@Log4j2
public class MemoController {
    private final MemoService memoService;

    // 메모 에러별 조회
    @GetMapping("/{eid}")
    public ResponseEntity<List<MemoDTO>> getMemoByError(@PathVariable int eid) {
        log.info("메모 에러별 조회: {}",eid);
        return ResponseEntity.ok(memoService.getMemoByError(eid));
    }

    // 메모 등록
    @PostMapping("")
    public ResponseEntity<Boolean> createMemo(
            @RequestBody MemoDTO memo) {
        //memo.setErrorId(eid);
        return ResponseEntity.status(HttpStatus.CREATED).body(memoService.createMemo(memo));
    }

    // 메모 삭제
    @DeleteMapping("/{mid}")
    public ResponseEntity<Boolean> deleteMemo(@PathVariable int mid){
        log.info("메모 삭제: {}", mid);
        return ResponseEntity.ok(memoService.deleteMemo(mid));
    }
}
