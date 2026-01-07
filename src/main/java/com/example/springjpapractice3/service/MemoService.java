package com.example.springjpapractice3.service;

import com.example.springjpapractice3.dto.MemoRequestDto;
import com.example.springjpapractice3.dto.MemoResponseDto;
import com.example.springjpapractice3.entity.Memo;
import com.example.springjpapractice3.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemoService {

    private final MemoRepository memoRepository;

    @Transactional
    public MemoResponseDto save(MemoRequestDto dto) {
        Memo memo = new Memo(dto.getTitle(), dto.getContent());
        Memo savedMemo = memoRepository.save(memo);

        return new MemoResponseDto(savedMemo.getId(), savedMemo.getTitle(), savedMemo.getContent());
    }
}
