package racingcar;

import camp.nextstep.edu.missionutils.Randoms;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class RacingGame {
    private final List<Car> carList = new ArrayList<>();
    private int round = 0;
    private boolean initialized = false;

    //레이싱 게임 초기화 함수
    public void init(String[] carNames, Integer round) {
        validateNames(carNames);
        validateRound(round);

        carList.clear();
        for (String carName : carNames) {
            carList.add(new Car(carName.trim()));
        }
        this.round = round;
        this.initialized = true;
    }

    //입력된 이름 검증 함수
    private void validateNames(String[] names) {
        if (names == null || names.length == 0) {
            throw new IllegalArgumentException("자동차 이름이 비어있습니다.");
        }

        for (String name : names) {
            if (name == null) {
                throw new IllegalArgumentException("자동차 이름에 null이 있습니다.");
            }
            if (name.trim().isEmpty() || name.trim().length() > 5){
                throw new IllegalArgumentException("자동차 이름 양식이 잘못되었습니다.");
            }
        }
    }

    //입력된 시도 횟수 검증 함수
    private void validateRound(Integer round) {
        if (round < 0) {
            throw new IllegalArgumentException("시도 횟수에 음수는 입력할 수 없습니다.");
        }
    }

    //레이싱 게임 실행 함수
    public void play() {
        if (!initialized){
            throw new IllegalArgumentException("게임이 초기화되지 않았습니다.");
        }

        System.out.println("실행 결과");

        //각 라운드 별로 모든 자동차는 이동을 시도하고 그 결과를 출력한다.
        for (int i = 0; i < round; ++i) {
            for (Car car : carList) {
                car.tryMove();
                car.printCarProgress();
            }
            System.out.println();
        }
    }

    //레이싱 게임 결과 출력 함수
    public void printResult() {
        //stream함수를 사용하여 자동차 리스트에서 가장 멀리간 자동차의 거리와 그 거리만큼 이동한 자동차 리스트를 구함
        Integer maxDistance = carList.stream().mapToInt(Car::getDistance).max().orElse(0);
        List<Car> winners = carList.stream().filter(car -> car.getDistance().equals(maxDistance)).toList();

        String winnerNames = winners.stream().map(Car::getName).collect(Collectors.joining(", "));

        System.out.println("최종 우승자 : " + winnerNames);
    }

    //자동차 클래스
    public static class Car {
        private final String name;
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

        public Integer getDistance() {
            return distance;
        }
    }
}
