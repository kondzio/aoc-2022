package com.kk.aoc2022.day1;

import com.kk.aoc.io.InputFileUtils;
import com.kk.aoc.io.InputFileUtils.ChunkIterator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import java.io.IOException;

public class Day1Part1Test {
    private ChunkIterator chunkIterator;

    @BeforeEach
    void setUp() throws IOException {
        chunkIterator = InputFileUtils.inChunks("src/test/resources/test_input.txt", StringUtils::isBlank);
    }

    @Test
    void test() {
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
