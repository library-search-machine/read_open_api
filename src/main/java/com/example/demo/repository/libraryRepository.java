package com.example.demo.repository;

import com.example.demo.domain.library;
import org.springframework.data.jpa.repository.JpaRepository;

public interface libraryRepository extends JpaRepository<library,Long> {
}
