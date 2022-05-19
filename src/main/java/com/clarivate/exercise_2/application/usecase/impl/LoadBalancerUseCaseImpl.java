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

  /**
   * Number of workers/groups of requests.
   */
  private static final int NUM_WORKERS = 3;

  /**
   * Number of requests dropped/avoided by the workers.
   */
  private static final int NUM_REQUESTS_DROPPED = 2;

  @Override
  public boolean requestsCanBeBalanced(List<Integer> requests) {

    // Guard clause to avoid testing too short lists
    if (requests.size() <= NUM_WORKERS + NUM_REQUESTS_DROPPED) {
      return false;
    }

    int requestsSum = requests.stream().mapToInt(Integer::intValue).sum();
    int maxSublistSum = BigDecimal.valueOf(requestsSum / NUM_WORKERS).setScale(0, RoundingMode.DOWN).intValue();

    return this.requestsCanBeBalancedForMaximum(requests, maxSublistSum);
  }

  private boolean requestsCanBeBalancedForMaximum(List<Integer> requests, int maxSublistSum) {

    // Initialize counters and lists
    int sublistOneSum = 0;
    int sublistTwoSum = 0;
    int sublistThreeSum = 0;

    List<Integer> sublistOne = new ArrayList<>();
    List<Integer> sublistTwo = new ArrayList<>();
    List<Integer> sublistThree = new ArrayList<>();

    // Calculate sublist sum
    sublistOneSum = calculateSublistSum(0, requests, maxSublistSum, sublistOneSum, sublistOne);
    sublistTwoSum = calculateSublistSum(sublistOne.size() + 1, requests, sublistOneSum, sublistTwoSum, sublistTwo);
    sublistThreeSum = calculateSublistSum(sublistOne.size() + sublistTwo.size() + 2, requests, sublistOneSum, sublistThreeSum, sublistThree);

    // Check if recursive call is necessary
    if (checkShouldDoRecursiveCall(sublistOne, sublistTwo, sublistThree)) {
      int newMaxSublistSum = calculateNewMaxSublistSum(sublistOne);
      return requestsCanBeBalancedForMaximum(requests, newMaxSublistSum);
    }

    // Final check
    boolean listsSizeMatch = requests.size() == sublistOne.size() + sublistTwo.size() + sublistThree.size() + NUM_REQUESTS_DROPPED;
    boolean listsTimeMatch = Stream.of(sublistOneSum, sublistTwoSum, sublistThreeSum).distinct().count() == 1;

    return listsSizeMatch && listsTimeMatch;
  }

  private int calculateSublistSum(int index, List<Integer> requests, int maxSublistSum, int currentSublistSum, List<Integer> sublist) {

    for (int i = index; i < requests.size(); i++) {
      int currentRequest = requests.get(i);
      if (currentSublistSum + currentRequest <= maxSublistSum) {
        currentSublistSum += currentRequest;
        sublist.add(currentRequest);
      } else {
        break;
      }
    }

    return currentSublistSum;
  }

  /**
   * Indicates whether the method should be called recursively by comparing the sums of the sublists.
   *
   * @param sublistA THe first sublist.
   * @param sublistB THe second  sublist.
   * @param sublistC THe third sublist.
   * @return True if the sum of the lists don't match. False otherwise.
   */
  private boolean checkShouldDoRecursiveCall(List<Integer> sublistA, List<Integer> sublistB, List<Integer> sublistC) {

    return !Objects.equals(sublistA.stream().mapToInt(Integer::intValue).sum(), sublistB.stream().mapToInt(Integer::intValue).sum())
        || !Objects.equals(sublistA.stream().mapToInt(Integer::intValue).sum(), sublistC.stream().mapToInt(Integer::intValue).sum());
  }

  /**
   * Calculates the new value of the maximum sublist total.
   *
   * @param sublist The sublist obtained from the original request list.
   * @return int The new maxSublistSum value
   */
  private int calculateNewMaxSublistSum(List<Integer> sublist) {

    // Remove last element from the list
    sublist.remove(sublist.size() - 1);

    // Subtracts the last element value from the total of the list
    return sublist.stream().mapToInt(Integer::intValue).sum();
  }

}
