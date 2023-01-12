package kr.codesquad.Lotto;

import java.util.*;
import java.util.stream.Collectors;

public class LottoGame {

    public void play() {
        // 로또 예산 입력받기
        int budget = getBudget();
        int lottoCount = getLottoCount(budget);

        // lotto count개 랜덤로또 생성
        ArrayList<List<Integer>> bought = purchaseLotto(lottoCount);

        // 당첨번호 입력받기
        List<Integer> win = getWinNumbers();

        // 당첨통계 & 표시
        List<Integer> result = matchResult(win ,bought);
        printResult(result);

        // 총 수익률 표시
        System.out.print("총 수익률은 ");
        System.out.print(profitRate(lottoCount*1000, result.get(3), result.get(4), result.get(5), result.get(6)));
        System.out.println("%입니다.");
    }

    public int getBudget() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("구입금액을 입력해 주세요.");
        return scanner.nextInt();
    }

    public int getLottoCount(int budget) {
        System.out.println(budget / 1000 + "개를 구매했습니다.");
        return budget / 1000;
    }

    public ArrayList<List<Integer>> purchaseLotto(int cnt) {
        ArrayList<List<Integer>> lottos = new ArrayList<>();
        while (cnt > 0) {
            Lotto lotto = new Lotto();
            lottos.add(lotto.getLottoNumbers());
            cnt--;
        }
        return lottos;
    }

    public List<Integer> getWinNumbers() {
        System.out.println();
        System.out.println("당첨 번호를 입력해 주세요.");

        Scanner scanner = new Scanner(System.in);

        List<Integer> winNumbers = new ArrayList<>();
        String[] inputNumbers = scanner.nextLine().split(", ");

        for (String i: inputNumbers) winNumbers.add(Integer.parseInt(i));
        scanner.close();

        return winNumbers;
    }

    public List<Integer> matchResult(List<Integer> win, ArrayList<List<Integer>> bought) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");

        List<Integer> result = Arrays.asList(0, 0, 0, 0, 0, 0, 0);

        for (List<Integer> integers : bought) {
            int matchCount = checkNumbers(win, integers);
            int countValue = result.get(matchCount);

            result.set(matchCount, countValue + 1);
        }

        return result;
    }

    private void printResult(List<Integer> result) {
        System.out.println("3개 일치 (5000원) - " + result.get(3) + "개");
        System.out.println("4개 일치 (50000원) - " + result.get(4) + "개");
        System.out.println("5개 일치 (1500000원) - " + result.get(5) + "개");
        System.out.println("6개 일치 (2000000000원) - " + result.get(6) + "개");
    }

    public int checkNumbers(List<Integer> win, List<Integer> lotto) {
        return (int) lotto.stream().filter(win::contains).count();
    }

    public int profitRate(int money, int three, int four, int five, int six) {
        int profit = (three * 5000) + (four * 50000) + (five * 1500000) + (six * 2000000000);
        return (profit - money) / money * 100;
    }
}
