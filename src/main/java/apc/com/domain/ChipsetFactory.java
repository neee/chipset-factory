package apc.com.domain;

import java.util.*;

public class ChipsetFactory {

    public static final FactoryResult EMPTY_RESPONSE = new FactoryResult(0, List.of());
    private static final char ONE = '1';
    private static final int TWO = 2;

    public FactoryResult produce(int[] machines, int needProduce) {
        if (needProduce < 1 || machines == null) {
            return EMPTY_RESPONSE;
        }
        //TreeMap helps us find close to requested value if it exists
        var numProducedChipsetsByMachines = new TreeMap<Integer, Collection<Collection<Integer>>>();
        //Combinations of all machines
        int numOfVariants = (int) Math.pow(TWO, machines.length);

        //Skip 0 cause no any machines
        for (int i = 1; i < numOfVariants; i++) {

            var binaryString = new StringBuilder(Integer.toBinaryString(i)).reverse().toString();
            //Amount produced chipsets by this selected machines
            int amountProducedChipsets = 0;
            //Collection of selected machines
            var selectedMachines = new ArrayList<Integer>();
            //Chipsets amount calculation
            for (int j = 0; j < binaryString.length(); j++) {
                if (binaryString.charAt(j) == ONE) {
                    amountProducedChipsets += machines[j];
                    selectedMachines.add(machines[j]);
                }
            }

            //Create new collection of selected machines if doesn't exist
            //and put new list of selected machines with the same chipsets power
            addSelectedMachinesCombination(numProducedChipsetsByMachines, amountProducedChipsets, selectedMachines);
        }

        var foundVariant = numProducedChipsetsByMachines.ceilingEntry(needProduce);
        return foundVariant != null
            ? new FactoryResult(foundVariant.getKey(), foundVariant.getValue())
            : EMPTY_RESPONSE;
    }

    private void addSelectedMachinesCombination(TreeMap<Integer, Collection<Collection<Integer>>> producedCombinations, int numProducedChipsets, ArrayList<Integer> selectedMachines) {
        var allMachinesByNumOfProducedChipsets = producedCombinations.get(numProducedChipsets);
        if (allMachinesByNumOfProducedChipsets == null) {
            var machines = new ArrayList<Collection<Integer>>();
            machines.add(selectedMachines);
            producedCombinations.put(numProducedChipsets, machines);
        } else {
            allMachinesByNumOfProducedChipsets.add(selectedMachines);
        }
    }
}
