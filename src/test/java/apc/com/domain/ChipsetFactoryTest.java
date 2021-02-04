package apc.com.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static apc.com.domain.ChipsetFactory.EMPTY_RESPONSE;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ChipsetFactoryTest {

    private static Stream<Arguments> testData() {
        return Stream.of(
            Arguments.of(new int[]{1, 2}, 1, new FactoryResult(1, List.of(List.of(1)))),
            Arguments.of(new int[]{1, 2, 3}, 1, new FactoryResult(1, List.of(List.of(1)))),
            Arguments.of(new int[]{1, 2, 3}, 2, new FactoryResult(2, List.of(List.of(2)))),
            Arguments.of(new int[]{5, 5, 6}, 10, new FactoryResult(10, List.of(List.of(5, 5)))),
            Arguments.of(new int[]{10, 11, 12}, 24, new FactoryResult(33, List.of(List.of(10, 11, 12)))),
            Arguments.of(new int[]{5, 4, 8, 1, 3}, 9, new FactoryResult(9, List.of(List.of(5, 4), List.of(8, 1), List.of(5, 1, 3)))),
            Arguments.of(null, 1, EMPTY_RESPONSE),
            Arguments.of(new int[]{1, 2}, 10, EMPTY_RESPONSE),
            Arguments.of(new int[1], 1, EMPTY_RESPONSE),
            Arguments.of(new int[1], 0, EMPTY_RESPONSE),
            Arguments.of(new int[0], 0, EMPTY_RESPONSE)
        );
    }

    @ParameterizedTest
    @MethodSource("testData")
    void it_checks_chipset_factory_produce(int[] machines, int needProduce, FactoryResult expectValue) {
        var chipsetFactory = new ChipsetFactory();
        var result = chipsetFactory.produce(machines, needProduce);

        assertEquals(expectValue, result);
    }
}