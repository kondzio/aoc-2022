package com.kk.aoc.io;

import org.apache.commons.io.LineIterator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;

import java.io.IOException;
import java.util.List;

class InputFileUtilsTest {

    @Test
    void readAllAtOnce() throws IOException {
        List<String> lines = InputFileUtils.allLinesAtOnce("src/main/resources/io_tests/sample_input.txt");
        Assertions.assertEquals(8, lines.size());
    }

    @Test
    void readLineIterator() throws IOException {
        int count = 0;
        try (LineIterator iterator = InputFileUtils.byLineIterator("src/main/resources/io_tests/sample_input.txt")) {
            while (iterator.hasNext()) {
                iterator.next();
                count++;
            }
        }
        Assertions.assertEquals(8, count);
    }

    @Test
    void readInChunks() throws Exception {
        int count = 0;
        try (InputFileUtils.ChunkIterator iterator = InputFileUtils.inChunks("src/main/resources/io_tests/sample_input.txt", StringUtils::isBlank)) {
            while (iterator.hasNext()) {
                iterator.nextChunk();
                count++;
            }
        }
        Assertions.assertEquals(3, count);
    }

}