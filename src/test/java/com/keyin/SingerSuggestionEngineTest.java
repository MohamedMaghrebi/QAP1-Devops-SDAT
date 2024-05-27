//Mohamed
package com.keyin;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
public class SingerSuggestionEngineTest {
    private final SingerSuggestionEngine suggestionEngine = new SingerSuggestionEngine();

    @Mock
    private SingerSuggestionDatabase mockSuggestionDB;

    @Test
    public void testGenerateSuggestions() throws Exception {
        suggestionEngine.loadDictionaryData(Paths.get(ClassLoader.getSystemResource("words.txt").getPath()));
        Assertions.assertTrue(suggestionEngine.generateSuggestions("singr").contains("singer"));
    }

    @Test
    public void testGenerateSuggestionsFail() throws Exception {
        suggestionEngine.loadDictionaryData(Paths.get(ClassLoader.getSystemResource("words.txt").getPath()));
        Assertions.assertFalse(suggestionEngine.generateSuggestions("singer").contains("singer"));
    }

    @Test
    public void testSuggestionsAsMock() {
        Map<String, Integer> wordMapForTest = new HashMap<>();
        wordMapForTest.put("concert", 1);

        Mockito.when(mockSuggestionDB.getWordMap()).thenReturn(wordMapForTest);
        suggestionEngine.setSuggestionDatabase(mockSuggestionDB);

        Assertions.assertFalse(suggestionEngine.generateSuggestions("concert").contains("concert"));
        Assertions.assertTrue(suggestionEngine.generateSuggestions("concet").contains("concert"));
    }
}