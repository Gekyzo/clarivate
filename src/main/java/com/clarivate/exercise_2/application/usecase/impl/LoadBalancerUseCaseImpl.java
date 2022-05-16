package com.clarivate.exercise_2.application.usecase.impl;

import com.clarivate.exercise_2.application.usecase.LoadBalancerUseCase;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoadBalancerUseCaseImpl implements LoadBalancerUseCase {

  @Override
  public boolean test(List<Integer> requests) {

    // Guard clause
    if (requests.size() <= 5) {
      return false;
    }

    return true;
  }

}
