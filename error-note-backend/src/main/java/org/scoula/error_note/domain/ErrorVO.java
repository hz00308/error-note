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
public class ErrorVO {
    private int errorId;
    private int categoryId;
    private String categoryName;
    private String title;
    private String contents;
    private Date createdAt;
    private Date updatedAt;
}
