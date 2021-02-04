package apc.com;

import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;

import apc.com.domain.ChipsetFactory;
import apc.com.domain.exceptions.WrongInputValues;

public class Main {

    public static void main(String[] args) {
        try (var scanner = new Scanner(System.in)) {

            System.out.println("Input number of machines (example: 6):");
            int numMachines = Integer.parseInt(scanner.nextLine());

            System.out.println("Input powers of machines (example: 1 2 4 10 5 6):");
            var machinesPowers = Arrays.stream(scanner.nextLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

            if (numMachines != machinesPowers.length) {
                throw new WrongInputValues(String.format("Expect: %d machines, but got: %d", numMachines, machinesPowers.length));
            }
            System.out.println("Input num of chipset for producing (example: 11):");
            int numChipsets = Integer.parseInt(scanner.nextLine());

            ChipsetFactory chipsetFactory = new ChipsetFactory();
            var result = chipsetFactory.produce(machinesPowers, numChipsets);
            if (result == null) {
                System.out.printf("No one variant of machines can't produce the chipsets amount: %d%n", numChipsets);
            }
            System.out.printf("Nr solutions=%d%n", result.getUsedMachines().size());
            result.getUsedMachines().forEach(e ->
                System.out.println(e.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(" ")))
            );
            System.out.printf("Waste=%d%n", result.getProducedChipsets() - numChipsets);

        } catch (Exception ex) {
            System.out.printf("Check input values: %s%n", ex);
        }
    }
}
