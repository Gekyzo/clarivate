package com.clarivate.exercise_1.infrastructure.application.usecase.impl;

import com.clarivate.exercise_1.infrastructure.application.exceptions.NoConsonantsFoundException;
import com.clarivate.exercise_1.infrastructure.application.usecase.CalculateAlliterationUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.MessageFormat;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
// TODO: use dataprovider
class CalculateAlliterationUseCaseTest {

  @Autowired
  private CalculateAlliterationUseCase useCase;

  @Test
  @DisplayName("Method returns correct calculatePercentage for text #1")
  void method_returns_correct_count_text_1() throws NoConsonantsFoundException {

    String phrase = "Mike made mellow music with his new microphone.";
    double expectedValue = 0.63d;

    assertEquals(expectedValue, useCase.calculatePercentage(phrase));
  }

  @Test
  @DisplayName("Method returns correct calculatePercentage for text #1")
  void method_returns_correct_count_text_2() throws NoConsonantsFoundException {

    String phrase = "Yarvis yanked his ankle at yoga, and Yolanda yelled out in surprise.";
    double expectedValue = 0.42d;

    assertEquals(expectedValue, useCase.calculatePercentage(phrase));
  }

  @Test
  @DisplayName("Method returns correct initial consonant for text #1")
  void method_returns_correct_initial_consonant_one() throws NoConsonantsFoundException {

    String phrase = "Mike made mellow music with his new microphone.";
    char expectedChar = 'm';

    assertEquals(expectedChar, useCase.getCountableConsonant(phrase));
  }

  @Test
  @DisplayName("Method returns correct initial consonant for text #2")
  void method_returns_correct_initial_consonant_two() throws NoConsonantsFoundException {

    String phrase = "Yarvis yanked his ankle at yoga, and Yolanda yelled out in surprise.";
    char expectedChar = 'y';

    assertEquals(expectedChar, useCase.getCountableConsonant(phrase));
  }

  @Test
  @DisplayName("Method throws expected exception when no word begins with consonant")
  void method_throws_expected_exception() {

    String phrase = "ankle at and out in entry untitled.";
    String expectedExceptionText = MessageFormat.format("The phrase \"{0}\" doesn''t contain any first consonant in any word", phrase);

    Exception ex = assertThrows(NoConsonantsFoundException.class, () -> useCase.getCountableConsonant(phrase));
    assertEquals(expectedExceptionText, ex.getMessage());
  }

}