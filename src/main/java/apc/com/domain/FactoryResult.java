package apc.com.domain;

import java.util.Collection;

import lombok.Value;

@Value
public class FactoryResult {

    Integer producedChipsets;

    Collection<Collection<Integer>> usedMachines;
}
