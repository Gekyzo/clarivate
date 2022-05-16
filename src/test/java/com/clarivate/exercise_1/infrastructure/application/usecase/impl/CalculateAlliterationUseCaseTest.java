package com.clarivate.exercise_1.infrastructure.application.usecase.impl;

import com.clarivate.exercise_1.infrastructure.application.usecase.CalculateAlliterationUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
// TODO: use dataprovider
class CalculateAlliterationUseCaseTest {

  @Autowired
  private CalculateAlliterationUseCase useCase;

  @Test
  @DisplayName("Method returns correct calculatePercentage for text #1")
  void method_returns_correct_count_text_1() {

    String phrase = "Mike made mellow music with his new microphone.";
    double expectedValue = 0.63d;

    assertEquals(expectedValue, useCase.calculatePercentage(phrase));
  }

  @Test
  @DisplayName("Method returns correct calculatePercentage for text #1")
  void method_returns_correct_count_text_2() {

    String phrase = "Yarvis yanked his ankle at yoga, and Yolanda yelled out in surprise.";
    double expectedValue = 0.42d;

    assertEquals(expectedValue, useCase.calculatePercentage(phrase));
  }

}