package org.example;

import java.util.Map;

public class TemplateEngine {

    public Result<TransformError, String> transform(Map<String, String> tagDictionary, String inputText) {
        if (inputText == null) {
            return new Result<TransformError, String>(TransformError.NOT_INPUT_TEXT_FOUND);
        }
        if (tagDictionary == null) {
            return new Result<TransformError, String>(TransformError.NOT_DICTIONARY_FOUND);
        }

        for (String key : tagDictionary.keySet()) {
            String tag = "${" + key + "}";
            inputText = inputText.replace(tag, tagDictionary.get(key));
        }

        boolean hasMoreTags = inputText.contains("${");
        if (hasMoreTags) {
            return new Result<TransformError, String>(TransformError.NOT_FOUND_DICTIONARY_KEY);
        }
        return new Result<TransformError, String>(inputText);
    }
}
