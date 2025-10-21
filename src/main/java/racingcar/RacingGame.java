package racingcar;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;

public class RacingGame {

    private List<Car> carList = new ArrayList<>();
    private int round = 0;


    public void start() {
        System.out.println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)");
        String carNamesInput = Console.readLine();

        if (carNamesInput == null) {
            throw new IllegalArgumentException("입력이 비어있습니다.");
        }

        String[] carNames = carNamesInput.split(",");
        for (int i = 0; i < carNames.length; i++) {
            carNames[i] = carNames[i].trim();
            if (carNames[i].isEmpty() || carNames[i].length() > 5 || carNames[i].contains(" ")) {
                throw new IllegalArgumentException("자동차 이름 양식이 잘못되었습니다.");
            }
            carList.add(new Car(carNames[i]));
        }


        System.out.println("시도할 횟수는 몇 회인가요?");
        String roundInput = Console.readLine();

        if (roundInput == null) {
            throw new IllegalArgumentException("입력이 비어있습니다.");
        }

        try {
            round = Integer.parseInt(roundInput);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("숫자 형식이 아닙니다");
        }

        System.out.println();
    }

    public void play() {
        System.out.println("실행 결과");

        for (int i = 0; i < round; ++i) {
            for (Car car : carList) {
                car.tryMove();
                car.printCarProgress();
            }
            System.out.println();
        }
    }

    public static class Car {
        private String name;
        private Integer distance = 0;

        public Car(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void tryMove() {
            if (Randoms.pickNumberInRange(0, 9) >= 4) {
                ++distance;
            }
        }

        public void printCarProgress() {
            System.out.println(name + " : " + "-".repeat(distance));
        }
    }
}
