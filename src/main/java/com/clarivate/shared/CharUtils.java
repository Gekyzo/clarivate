package com.clarivate.shared;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.text.Normalizer;
import java.util.regex.Pattern;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CharUtils {

  private static final String VOWELS = "[aeiou]";

  public static boolean isVowel(char character) {

    String normalizedChar = Normalizer.normalize(String.valueOf(character), Normalizer.Form.NFD)
        .replaceAll("\\p{InCombiningDiacriticalMarks}+", "");

    return Pattern.matches(VOWELS, normalizedChar);
  }

}
