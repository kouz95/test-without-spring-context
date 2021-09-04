package com.example.testwithoutspringcontext;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SomeRepository extends JpaRepository<SomeEntity, Long> {
}
