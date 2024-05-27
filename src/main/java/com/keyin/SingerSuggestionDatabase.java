package com.keyin;

import java.util.HashMap;
import java.util.Map;

public class SingerSuggestionDatabase {
    private Map<String, Integer> wordMap;

    public Map<String, Integer> getWordMap() {
        if (wordMap == null) {
            wordMap = new HashMap<>();
        }
        return wordMap;
    }

    public void setWordMap(Map<String, Integer> wordMap) {
        this.wordMap = wordMap;
    }

    public boolean containsKey(String word) {
        return getWordMap().containsKey(word);
    }

    public Integer compute(String word, java.util.function.BiFunction<? super String, ? super Integer, ? extends Integer> remappingFunction) {
        return getWordMap().compute(word, remappingFunction);
    }
}