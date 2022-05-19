package com.clarivate.shared;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.math.RoundingMode;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MathUtils {

  public static double asDecimal(double number) {

    return BigDecimal.valueOf(number).setScale(2, RoundingMode.HALF_UP).doubleValue();
  }

}
