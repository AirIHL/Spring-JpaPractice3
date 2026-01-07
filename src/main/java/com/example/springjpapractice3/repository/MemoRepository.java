package com.example.springjpapractice3.repository;

import com.example.springjpapractice3.entity.Memo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemoRepository extends JpaRepository<Memo, Long> {
}
