package com.clarivate.shared;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.regex.Pattern;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CharUtils {

  /**
   *
   */
  private static final List<String> VOWELS = List.of("a", "e", "i", "o", "u");

  /**
   *
   */
  private static final String ACCENTS = "/^[A-Za-z\\u00C0-\\u017F]+$/";

  /**
   * @param character
   * @return
   */
  public static boolean isVowel(char character) {

    String normalizedChar = String.valueOf(character).toLowerCase().replaceAll(ACCENTS, "");

    return VOWELS.contains(normalizedChar);
  }

}
