package kr.codesquad.Lotto;

import java.util.*;
import java.util.stream.Collectors;

public class LottoGame {

    public void play() {
        // 로또 예산 입력받기
        Scanner scanner = new Scanner(System.in);

        System.out.println("구입금액을 입력해 주세요.");
        int budget = scanner.nextInt();

        int lottoCount = budget / 1000;
        System.out.println(lottoCount + "개를 구매했습니다.");

        // 로또 생성
        ArrayList bought = LotteryNumbers(lottoCount);

        // 당첨번호 입력받기
        List<Integer> win = getWinNumbers();

        // 당첨통계 표시
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");
        matchResult(lottoCount, win ,bought);

        // 총 수익률 표시
    }

    public ArrayList LotteryNumbers(int cnt) {
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 1; i < 46; i++) {
            numbers.add(i);
        }

        ArrayList<List<Integer>> generated = new ArrayList<>();
        while (cnt > 0) {
            Collections.shuffle(numbers);
            List<Integer> lotto = numbers.stream().limit(6).sorted().collect(Collectors.toList());

            generated.add(lotto);
            System.out.println(lotto);
            cnt--;
        }

        return generated;
    }

    public List<Integer> getWinNumbers() {
        Scanner scanner = new Scanner(System.in);
        List<Integer> winNumbers = new ArrayList<>();

        System.out.println();
        System.out.println("당첨 번호를 입력해 주세요.");
        String[] inputNumbers = scanner.nextLine().split(", ");

        for (String i: inputNumbers) winNumbers.add(Integer.parseInt(i));
        scanner.close();

        return winNumbers;
    }

    public void matchResult(int lottoCount, List win, List bought) {
        List<Integer> result = Arrays.asList(0, 0, 0, 0, 0, 0);

        for (int i = 0; i<bought.size(); i++) {
            int matchCount = checkNumbers(win, (List) bought.get(i));
            int before = result.get(matchCount);

            result.set(matchCount, before+1);
        }

        System.out.println("3개 일치 (5000원) - " + result.get(2) + "개");
        System.out.println("4개 일치 (50000원) - " + result.get(3) + "개");
        System.out.println("5개 일치 (1500000원) - " + result.get(4) + "개");
        System.out.println("6개 일치 (2000000000원) - " + result.get(5) + "개");

        System.out.print("총 수익률은 ");
        System.out.print(profitRate(lottoCount*1000, result.get(2), result.get(3), result.get(4), result.get(5)));
        System.out.println("%입니다.");
    }

    public int checkNumbers(List win, List lotto) {
        return (int) lotto.stream().filter(win::contains).count();
    }

    public int profitRate(int money, int three, int four, int five, int six) {
        int profit = (three * 5000) + (four * 50000) + (five * 1500000) + (six * 2000000000);

        // (현재가치 - 초기가치) / 초기가치 x 100
        return (profit - money) / money * 100;
    }
}
