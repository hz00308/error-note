package org.scoula.error_note.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.scoula.error_note.dto.ErrorDTO;
import org.scoula.error_note.service.ErrorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/errors")
@RequiredArgsConstructor
@Log4j2
public class ErrorController {

    private final ErrorService service;


    // 에러 전체 & 카테고리별 조회 수민
    @GetMapping("")
    public ResponseEntity<List<ErrorDTO>> getErrorListByCategory(@RequestParam(required = false) Integer cid){
                if (cid == null){
                 return ResponseEntity.ok(service.getErrorList());
        } else
               return ResponseEntity.ok(service.getErrorListByCategory(cid));
    }


    // 에러 상세 조회


    // 에러 등록


    // 에러 수정


//     에러 삭제 수민
    @DeleteMapping("/{eid}")
    public ResponseEntity<ErrorDTO> deleteError(@PathVariable int eid){
        return ResponseEntity.ok(service.deleteError(eid));
    }

}
