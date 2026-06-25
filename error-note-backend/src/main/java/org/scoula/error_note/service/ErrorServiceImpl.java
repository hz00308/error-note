package org.scoula.error_note.service;

import org.scoula.error_note.dto.ErrorDTO;

import java.util.List;

public class ErrorServiceImpl implements ErrorService{
    @Override
    public List<ErrorDTO> getErrorList() {
        return List.of();
    }

    @Override
    public List<ErrorDTO> getErrorListByCategory(int cid) {
        return List.of();
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

    @Override
    public ErrorDTO deleteError(int eid) {
        return null;
    }
}
