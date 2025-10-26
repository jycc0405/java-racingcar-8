package racingcar;

import camp.nextstep.edu.missionutils.Console;

public class Application {
    public static void main(String[] args) {
        RacingGame game = new RacingGame();

        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        String carNamesInput = Console.readLine();

        System.out.println("시도할 횟수는 몇 회인가요?");
        String roundInput = Console.readLine();

        if (carNamesInput == null || roundInput == null) {
            throw new IllegalArgumentException("입력이 비어있습니다.");
        }

        String[] carNames = carNamesInput.split(",");
        int round;
        try {
            round = Integer.parseInt(roundInput.trim());
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("시도 횟수는 숫자여야 합니다.", e);
        }

        game.init(carNames, round);
        game.play();
        game.printResult();
    }
}
