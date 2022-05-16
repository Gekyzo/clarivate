package com.clarivate.exercise_1.infrastructure.application.usecase.impl;

import com.clarivate.exercise_1.infrastructure.application.usecase.CountAlliterationsUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class CountAlliterationsUseCaseImplTest {

  @Autowired
  private CountAlliterationsUseCase useCase;

  @Test
  @DisplayName("Method returns expected value")
  void count() {

    assertEquals(5, useCase.count("hi"));
  }

}