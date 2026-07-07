package org.scoula.error_note.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MemoVO {
    private int memoId;
    private int errorId;
    private String contents;
    private Date createdAt;
}
