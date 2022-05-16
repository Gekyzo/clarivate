package com.clarivate.exercise_2.application.usecase.impl;

import com.clarivate.exercise_2.application.usecase.LoadBalancerUseCase;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
class LoadBalancerUseCaseTest {

  @Autowired
  private LoadBalancerUseCase useCase;

  @Test
  @DisplayName("Method returns true #1")
  void method_returns_true() {

    List<Integer> requests = List.of(1, 3, 4, 2, 2, 2, 1, 1, 2);

    assertTrue(useCase.test(requests));
  }

  @Test
  @DisplayName("Method returns true #2")
  void method_returns_true_two() {

    List<Integer> requests = List.of(2, 1, 1, 2, 2, 2, 4, 3, 1);

    assertTrue(useCase.test(requests));
  }

  @Test
  @DisplayName("Method returns true #3")
  void method_returns_true_three() {

    List<Integer> requests = List.of(1, 1, 1, 1, 1, 1, 1, 1);

    assertTrue(useCase.test(requests));
  }

  @Test
  @DisplayName("Method returns false")
  void method_returns_false() {

    List<Integer> requests = List.of(1, 1, 1, 1, 1, 1);

    assertFalse(useCase.test(requests));
  }

}