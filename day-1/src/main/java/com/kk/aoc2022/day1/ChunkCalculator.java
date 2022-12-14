package com.kk.aoc2022.day1;

import lombok.NonNull;

import java.util.List;

public class ChunkCalculator {

    public int calculateChunkSize(@NonNull List<String> chunk) {
        return chunk.stream().map(Integer::parseInt).reduce(0, Integer::sum);
    }

}
