package kr.codesquad.Lotto;

import java.util.*;

public class LottoGame {
    private static final Lotto lotto = new Lotto();
    private static final LottoInput lottoInput = new LottoInput();
    private static final LottoPrints lottoPrints = new LottoPrints();

    private static int budget;
    private static int lottoCount;

    public void play() {
        // 로또 예산 입력받기
        budget = lottoInput.getBudget();
        lottoCount = lottoInput.getLottoCount(budget);

        // lotto count개 랜덤로또 생성
        ArrayList<List<Integer>> bought = purchaseLotto(lottoCount);

        // 당첨번호 입력받기
        List<Integer> winNumbers = lottoInput.getWinNumbers();

        // 당첨통계 & 표시
        lottoPrints.printStatics();
        List<Integer> result = matchResult(winNumbers ,bought);
        lottoPrints.printResult(result);

        // 총 수익률 표시
        lottoPrints.printTotalProfilt(lottoCount, result);
    }

    private ArrayList<List<Integer>> purchaseLotto(int cnt) {
        ArrayList<List<Integer>> lottos = new ArrayList<>();
        while (cnt > 0) {
            lottos.add(lotto.getLottoNumbers());
            cnt--;
        }
        return lottos;
    }

    private List<Integer> matchResult(List<Integer> win, ArrayList<List<Integer>> bought) {
        List<Integer> result = Arrays.asList(0, 0, 0, 0, 0, 0, 0);

        for (List<Integer> integers : bought) {
            int matchCount = verifyNumbers(win, integers);
            int countValue = result.get(matchCount);

            result.set(matchCount, countValue + 1);
        }
        return result;
    }

    private int verifyNumbers(List<Integer> win, List<Integer> lotto) {
        return (int) lotto.stream().filter(win::contains).count();
    }

}

class LottoInput {

    int getBudget() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("구입금액을 입력해 주세요.");
        return scanner.nextInt();
    }

    int getLottoCount(int budget) {
        System.out.println(budget / 1000 + "개를 구매했습니다.");
        return budget / 1000;
    }

    List<Integer> getWinNumbers() {
        System.out.println();
        System.out.println("당첨 번호를 입력해 주세요.");

        Scanner scanner = new Scanner(System.in);

        List<Integer> winNumbers = new ArrayList<>();
        String[] inputNumbers = scanner.nextLine().split(", ");

        for (String i : inputNumbers) winNumbers.add(Integer.parseInt(i));
        scanner.close();

        return winNumbers;
    }
}

class LottoPrints {
    void printStatics() {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");
    }

    void printResult(List<Integer> result) {
        System.out.println("3개 일치 (5000원) - " + result.get(3) + "개");
        System.out.println("4개 일치 (50000원) - " + result.get(4) + "개");
        System.out.println("5개 일치 (1500000원) - " + result.get(5) + "개");
        System.out.println("6개 일치 (2000000000원) - " + result.get(6) + "개");
    }

    void printTotalProfilt(int lottoCount, List<Integer> result) {
        System.out.print("총 수익률은 ");
        System.out.print(profitRate(lottoCount*1000, result.get(3), result.get(4), result.get(5), result.get(6)));
        System.out.println("%입니다.");
    }

    private int profitRate(int money, int three, int four, int five, int six) {
        int profit = (three * 5000) + (four * 50000) + (five * 1500000) + (six * 2000000000);
        return (profit - money) / money * 100;
    }
}
