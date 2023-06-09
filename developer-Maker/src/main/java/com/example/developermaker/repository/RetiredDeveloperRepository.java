package com.example.developermaker.repository;

import com.example.developermaker.entity.RetiredDeveloper;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RetiredDeveloperRepository extends JpaRepository<RetiredDeveloper, Long> {
}
