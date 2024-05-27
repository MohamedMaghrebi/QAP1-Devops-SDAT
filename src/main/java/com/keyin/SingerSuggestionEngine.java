package com.keyin;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.Collator;
import java.util.Comparator;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class SingerSuggestionEngine {
    private SingerSuggestionDatabase suggestionDatabase;

    private Stream<String> wordEdits(final String word) {
        Stream<String> deletes = IntStream.range(0, word.length())
                .mapToObj((i) -> word.substring(0, i) + word.substring(i + 1));
        Stream<String> replaces = IntStream.range(0, word.length())
                .mapToObj((i) -> i)
                .flatMap((i) -> "abcdefghijklmnopqrstuvwxyz".chars()
                        .mapToObj((c) -> word.substring(0, i) + (char) c + word.substring(i + 1)));
        Stream<String> inserts = IntStream.range(0, word.length() + 1)
                .mapToObj((i) -> i)
                .flatMap((i) -> "abcdefghijklmnopqrstuvwxyz".chars()
                        .mapToObj((c) -> word.substring(0, i) + (char) c + word.substring(i)));
        Stream<String> transposes = IntStream.range(0, word.length() - 1)
                .mapToObj((i) -> word.substring(0, i) + word.charAt(i + 1) + word.charAt(i) + word.substring(i + 2));
        return Stream.of(deletes, replaces, inserts, transposes).flatMap(x -> x);
    }

    private Stream<String> known(Stream<String> words) {
        return words.filter(word -> getSuggestionDatabase().containsKey(word));
    }

    public void loadDictionaryData(Path dictionaryFile) throws IOException {
        Stream.of(new String(Files.readAllBytes(dictionaryFile)).toLowerCase().split("\\n"))
                .forEach(word -> getSuggestionDatabase().compute(word, (k, v) -> v == null ? 1 : v + 1));
    }

    public String generateSuggestions(String word) {
        if (getSuggestionDatabase().containsKey(word)) {
            return "";
        }

        Stream<String> e1 = known(wordEdits(word));
        Stream<String> e2 = known(wordEdits(word).map(w2 -> wordEdits(w2)).flatMap(x -> x));

        Stream<String> suggestions = Stream.concat(e1, e2);

        Map<String, Long> collectedSuggestions = suggestions
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        return collectedSuggestions.keySet().stream()
                .sorted(Comparator.comparing(collectedSuggestions::get)
                        .reversed()
                        .thenComparing(Collator.getInstance()))
                .limit(10)
                .collect(Collectors.joining("\n"));
    }

    public Map<String, Integer> getSuggestionDatabase() {
        if (suggestionDatabase == null) {
            suggestionDatabase = new SingerSuggestionDatabase();
        }
        return suggestionDatabase.getWordMap();
    }

    public void setSuggestionDatabase(SingerSuggestionDatabase suggestionDatabase) {
        this.suggestionDatabase = suggestionDatabase;
    }
}