package com.clarivate.exercise_2.application.usecase;

import java.util.List;

public interface LoadBalancerUseCase {

  boolean test(List<Integer> requests);

}
