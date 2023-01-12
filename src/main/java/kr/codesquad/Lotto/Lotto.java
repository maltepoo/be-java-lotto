package kr.codesquad.Lotto;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {
    private final static List<Integer> LOTTO_NUMBERS = Arrays.asList(
            1, 2, 3, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 29, 20,
            21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37,
            38, 39, 40, 41, 42, 43, 44, 45
    );


    public List<Integer> getLottoNumbers() {
        Collections.shuffle(LOTTO_NUMBERS);

        List<Integer> numbers = LOTTO_NUMBERS.stream().limit(6).sorted().collect(Collectors.toList());
        System.out.println(numbers);

        return numbers;
    }
}
