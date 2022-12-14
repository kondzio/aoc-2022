package com.kk.aoc2022.day1;

import com.kk.aoc.io.InputFileUtils;
import org.apache.commons.lang3.StringUtils;

public class Day1Part1Main {
    public static void main(String[] args) throws Exception {
        try (InputFileUtils.ChunkIterator chunkIterator = InputFileUtils.inChunks("D:\\poligon\\aoc-2022\\day-1\\src\\main\\resources\\input.txt", StringUtils::isBlank)) {
            ChunkCalculator chunkCalculator = new ChunkCalculator();
            int i = 0;
            int maxChunkIndex = -1;
            int maxChunkSize = 0;
            while (chunkIterator.hasNext()) {
                i++;
                int chunkSize = chunkCalculator.calculateChunkSize(chunkIterator.nextChunk());
                if (chunkSize > maxChunkSize) {
                    maxChunkSize = chunkSize;
                    maxChunkIndex = i;
                }
            }
            System.err.println(maxChunkIndex + " : " + maxChunkSize);
        }
    }
}
