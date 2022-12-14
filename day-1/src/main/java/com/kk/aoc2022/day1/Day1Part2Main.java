package com.kk.aoc2022.day1;

import com.kk.aoc.io.InputFileUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Day1Part2Main {
    public static void main(String[] args) throws Exception {
        try (InputFileUtils.ChunkIterator chunkIterator = InputFileUtils.inChunks("D:\\poligon\\aoc-2022\\day-1\\src\\main\\resources\\input.txt", StringUtils::isBlank)) {
            ChunkCalculator chunkCalculator = new ChunkCalculator();

            List<Integer> chunkSizes = new ArrayList<>();
            while (chunkIterator.hasNext()) {
                chunkSizes.add(chunkCalculator.calculateChunkSize(chunkIterator.nextChunk()));
            }
            chunkSizes.sort(Comparator.reverseOrder());
            System.err.println(chunkSizes.get(0) + chunkSizes.get(1) + chunkSizes.get(2));
        }
    }
}
