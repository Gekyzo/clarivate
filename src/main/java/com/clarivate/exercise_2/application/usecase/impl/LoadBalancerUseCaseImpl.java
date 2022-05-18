package com.clarivate.exercise_2.application.usecase.impl;

import com.clarivate.exercise_2.application.usecase.LoadBalancerUseCase;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;

@Service
public class LoadBalancerUseCaseImpl implements LoadBalancerUseCase {

  private static final int NUM_WORKERS = 3;

  @Override
  public boolean requestsCanBeBalanced(List<Integer> requests) {

    if (input.size() <= 5) {
      return false;
    }

    int valuesSum = input.stream().reduce(0, Integer::sum);
    int maxSubsetSum = BigDecimal.valueOf(valuesSum / NUM_WORKERS).setScale(0, RoundingMode.DOWN).intValue();

    return this.calc(input, maxSubsetSum);
  }

  private boolean calc(List<Integer> input, int maxSubsetSum) {

    int sum1 = 0;
    int sum2 = 0;
    int sum3 = 0;

    List<Integer> list1 = new ArrayList<>();
    List<Integer> list2 = new ArrayList<>();
    List<Integer> list3 = new ArrayList<>();

    for (int i = 0; i < input.size(); i++) {
      if (sum1 + input.get(i) <= maxSubsetSum) {
        sum1 += input.get(i);
        list1.add(input.get(i));
      } else {
        break;
      }
    }

    for (int i = list1.size() + 1; i < input.size(); i++) {
      if (sum2 + input.get(i) <= sum1) {
        sum2 += input.get(i);
        list2.add(input.get(i));
      } else {
        break;
      }
    }

    if (!Objects.equals(list1.stream().reduce(0, Integer::sum), list2.stream().reduce(0, Integer::sum))) {
      int index = list1.size() - 1;
      list1.remove(index);
      int newMaxSubsetSum = list1.stream().reduce(0, Integer::sum);
      if (this.calc(input, newMaxSubsetSum))
        return true;
    }

    for (int i = list1.size() + list2.size() + 2; i < input.size(); i++) {
      if (sum3 + input.get(i) <= sum1) {
        sum3 += input.get(i);
        list3.add(input.get(i));
      } else {
        break;
      }
    }

    if (!Objects.equals(list1.stream().reduce(0, Integer::sum), list3.stream().reduce(0, Integer::sum))) {
      int index = list1.size() - 1;
      list1.remove(index);
      int newMaxSubsetSum = list1.stream().reduce(0, Integer::sum);
      if (this.calc(input, newMaxSubsetSum))
        return true;
    }

    return input.size() == list1.size() + list2.size() + list3.size() + 2 && Stream.of(sum1, sum2, sum3).distinct().count() == 1;

  }

}
