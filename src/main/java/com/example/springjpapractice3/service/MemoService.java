package com.example.springjpapractice3.service;

import com.example.springjpapractice3.dto.MemoRequestDto;
import com.example.springjpapractice3.dto.MemoResponseDto;
import com.example.springjpapractice3.entity.Memo;
import com.example.springjpapractice3.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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

    @Transactional(readOnly = true)
    public List<MemoResponseDto> findAll() {

        List<Memo> memos = memoRepository.findAll();
        List<MemoResponseDto> dtos = new ArrayList<>();
        for (Memo memo : memos) {
            dtos.add(new MemoResponseDto(memo.getId(), memo.getTitle(), memo.getContent()));
        }

        return dtos;
    }

    @Transactional(readOnly = true)
    public MemoResponseDto findById(Long id) {

        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("해당 id에 맞는 메모가 없습니다.")
        );

        return new MemoResponseDto(memo.getId(), memo.getTitle(), memo.getContent());
    }

    @Transactional
    public MemoResponseDto update(Long id, MemoRequestDto dto) {

        Memo memo = memoRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("이 id의 메모가 없습니다.")
        );
        memo.update(dto.getTitle(), dto.getContent());

        return new MemoResponseDto(memo.getId(), memo.getTitle(), memo.getContent());
    }

    public void deleteById(Long id) {

        if (!memoRepository.existsById(id)) {
            throw new IllegalArgumentException("삭제하려는 id의 메모가 없습니다.");
        }
        memoRepository.deleteById(id);
    }
}
