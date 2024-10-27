package racingcar.validators;

import java.util.List;
import racingcar.common.Languages;
import racingcar.common.Ranges;

public class InputValidator {

    public void validateCars(List<String> cars) {
        validateCharacters(cars);
        validateLength(cars);
        validateEmpty(cars);
        validateDuplicates(cars);
        validateMaxCarCount(cars);
    }

    public void validateRounds(String rounds) {
        validateEmpty(rounds);
        validateNumber(rounds);
        validateRange(rounds);
    }

    private void validateCharacters(List<String> cars) {
        if (!cars.stream().allMatch(car -> car.matches(
                "^[" + Languages.ENGLISH.getRegex() + Languages.KOREAN.getRegex() + "]*$"
        ))) {
            throw new IllegalArgumentException("이름에 허용되지 않는 문자가 들어가 있습니다 (한국어+영어만 가능).");
        }
    }

    private void validateLength(List<String> cars) {
        if (cars.stream().anyMatch(car -> car.length() > Ranges.MAX_NAME_LENGTH.getValue())) {
            throw new IllegalArgumentException("각 이름의 길이는 " + Ranges.MAX_NAME_LENGTH.getValue() + "자를 넘길 수 없습니다.");
        }
    }

    private void validateEmpty(List<String> cars) {
        if (cars.stream().anyMatch(String::isEmpty)) {
            throw new IllegalArgumentException("빈 이름이 존재합니다.");
        }
    }

    private void validateDuplicates(List<String> cars) {
        if (cars.size() != cars.stream().distinct().count()) {
            throw new IllegalArgumentException("중복되는 이름이 존재합니다.");
        }
    }

    private void validateMaxCarCount(List<String> cars) {
        if (cars.size() > Ranges.MAX_CARS.getValue()) {
            throw new IllegalArgumentException("차의 대수는 " + Ranges.MAX_CARS.getValue() + "대를 넘길 수 없습니다.");
        }
    }

    private void validateEmpty(String rounds) {
        if (rounds.isEmpty()) {
            throw new IllegalArgumentException("값을 입력해주세요.");
        }
    }

    private void validateNumber(String rounds) {
        try {
            Integer.parseInt(rounds);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("유효하지 않은 숫자입니다.");
        }
    }

    private void validateRange(String rounds) {
        if (Integer.parseInt(rounds) < Ranges.MIN_ROUNDS.getValue()) {
            throw new IllegalArgumentException(
                    "시도할 횟수로 " + Ranges.MIN_ROUNDS.getValue() + " 이상의 값을 입력해주세요.");
        } else if (Integer.parseInt(rounds) > Ranges.MAX_ROUNDS.getValue()) {
            throw new IllegalArgumentException(
                    "시도할 횟수가 너무 큽니다. " + Ranges.MAX_ROUNDS.getValue() + " 이하의 값을 입력해주세요.");
        }
    }
}
