package com.clarivate.exercise_1.application.usecase.impl;

import com.clarivate.exercise_1.application.exceptions.NoInitialConsonantFoundException;
import com.clarivate.exercise_1.application.usecase.CalculateAlliterationUseCase;
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
  void method_returns_correct_count_text_1() throws NoInitialConsonantFoundException {

    String phrase = "Mike made mellow music with his new microphone.";
    double expectedValue = 0.63d;

    assertEquals(expectedValue, useCase.calculatePercentage(phrase));
  }

  @Test
  @DisplayName("Method returns correct calculatePercentage for text #1")
  void method_returns_correct_count_text_2() throws NoInitialConsonantFoundException {

    String phrase = "Yarvis yanked his ankle at yoga, and Yolanda yelled out in surprise.";
    double expectedValue = 0.42d;

    assertEquals(expectedValue, useCase.calculatePercentage(phrase));
  }

  @Test
  @DisplayName("Method returns correct initial consonant for text #1")
  void method_returns_correct_initial_consonant_one() throws NoInitialConsonantFoundException {

    String phrase = "Mike made mellow music with his new microphone.";
    char expectedChar = 'm';

    assertEquals(expectedChar, useCase.getCountableConsonant(phrase));
  }

  @Test
  @DisplayName("Method returns correct initial consonant for text #2")
  void method_returns_correct_initial_consonant_two() throws NoInitialConsonantFoundException {

    String phrase = "Yarvis yanked his ankle at yoga, and Yolanda yelled out in surprise.";
    char expectedChar = 'y';

    assertEquals(expectedChar, useCase.getCountableConsonant(phrase));
  }

  @Test
  @DisplayName("Method throws expected exception when no word begins with consonant")
  void method_throws_expected_exception() {

    String phrase = "ankle at and out in entry untitled.";
    String expectedExceptionText = MessageFormat.format("The phrase \"{0}\" doesn''t any word that begins with consonant", phrase);

    Exception ex = assertThrows(NoInitialConsonantFoundException.class, () -> useCase.getCountableConsonant(phrase));
    assertEquals(expectedExceptionText, ex.getMessage());
  }

}