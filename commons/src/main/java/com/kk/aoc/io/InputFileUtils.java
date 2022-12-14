package com.kk.aoc.io;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class InputFileUtils {

    public static final String INPUT_TXT = "src/main/resources/input.txt";

    public static List<String> allLinesAtOnce(@NonNull String path) throws IOException {
        return Files.readAllLines(Path.of(path));
    }

    public static List<String> allInputLinesAtOnce() throws IOException {
        return allLinesAtOnce(INPUT_TXT);
    }

    public static LineIterator byLineIterator(@NonNull String path) throws IOException {
        return FileUtils.lineIterator(Path.of(path).toFile(), "UTF-8");
    }

    public static LineIterator inputByLineIterator() throws IOException {
        return byLineIterator(INPUT_TXT);
    }

    public static ChunkIterator inChunks(@NonNull String path, Predicate<String> chunkEndCondition) throws IOException {
        if (chunkEndCondition == null) {
            return SingleChunkIterator.of(allInputLinesAtOnce());
        }
        return MultipleChunksIterator.of(byLineIterator(path), chunkEndCondition);
    }

    public static ChunkIterator inputInChunks(Predicate<String> chunkEndCondition) throws IOException {
        return inChunks(INPUT_TXT, chunkEndCondition);
    }

    public interface ChunkIterator extends Iterator<List<String>>, AutoCloseable {
        List<String> nextChunk();
    }

    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    static class SingleChunkIterator implements ChunkIterator {
        @NonNull
        private final Iterator<List<String>> iterator;

        static ChunkIterator of(List<String> chunk) {
            return new SingleChunkIterator((List.of(chunk)).iterator());
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public List<String> next() {
            return iterator.next();
        }

        @Override
        public List<String> nextChunk() {
            return next();
        }

        @Override
        public void remove() {
            ChunkIterator.super.remove();
        }

        @Override
        public void forEachRemaining(Consumer<? super List<String>> action) {
            ChunkIterator.super.forEachRemaining(action);
        }

        @Override
        public void close() {
            //noop;
        }
    }

    @RequiredArgsConstructor
    static class MultipleChunksIterator implements ChunkIterator {

        @NonNull
        private final LineIterator iterator;
        private final Predicate<String> chunkEndCondition;

        static ChunkIterator of(LineIterator iterator, Predicate<String> chunkEndCondition) {
            return new MultipleChunksIterator(iterator, chunkEndCondition);
        }

        @Override
        public List<String> nextChunk() {
            return next();
        }

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public List<String> next() {
            List<String> nextChunk = new ArrayList<>();
            while (iterator.hasNext()) {
                String line = iterator.next();
                if (chunkEndCondition.test(line)) {
                    break;
                }
                nextChunk.add(line);
            }
            return nextChunk;
        }

        @Override
        public void remove() {
            ChunkIterator.super.remove();
        }

        @Override
        public void forEachRemaining(Consumer<? super List<String>> action) {
            ChunkIterator.super.forEachRemaining(action);
        }

        @Override
        public void close() throws IOException {
            iterator.close();
        }
    }
}
