package kr.codesquad.Lotto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class Lotto {
    private final static int LOTTO_PRICE = 1000;
    private final static int LOTTO_COUNT = 6;
    private static ArrayList<Integer> LOTTO_NUMBERS;

    public Lotto() {
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 1; i < 46; i++) {
            numbers.add(i);
        }
        LOTTO_NUMBERS = numbers;
    }

    public List<Integer> getLottoNumbers() {
        Collections.shuffle(LOTTO_NUMBERS);
        List<Integer> generated = LOTTO_NUMBERS.stream().limit(6).sorted().collect(Collectors.toList());
        System.out.println(generated);
        return generated;
    }
}
