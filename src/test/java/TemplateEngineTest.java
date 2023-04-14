import org.example.Result;
import org.example.TemplateEngine;
import org.example.TransformError;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class TemplateEngineTest {

    @Test
    void shouldReturnInputTextWhenItHasNoVariables() {
        TemplateEngine engine = new TemplateEngine();
        Map<String, String> tagDictionary = new HashMap<>();
        String inputText = "IRRELEVANT_TEXT";

        String output = engine.transform(tagDictionary, inputText).getValue();

        assertEquals(output, inputText);
    }

    @Test
    void shouldSubstituteASingleVariable() {
        TemplateEngine engine = new TemplateEngine();
        Map<String, String> tagDictionary = new HashMap<>();
        tagDictionary.put("variable", "foo");
        String inputText = "This is a template with one ${variable}";

        String output = engine.transform(tagDictionary, inputText).getValue();

        assertEquals(output, "This is a template with one foo");
    }

    @Test
    void shouldSubstituteMoreThanOneVariables() {
        TemplateEngine engine = new TemplateEngine();
        Map<String, String> tagDictionary = new HashMap<>();
        tagDictionary.put("variable", "foo");
        tagDictionary.put("variable2", "foo2");
        String inputText = "This is a template with both ${variable} and ${variable2}";

        String output = engine.transform(tagDictionary, inputText).getValue();

        assertEquals(output, "This is a template with both foo and foo2");
    }


    @Test
    void shouldNotUsedVariablesNotContainedTest() {
        TemplateEngine engine = new TemplateEngine();
        Map<String, String> tagDictionary = new HashMap<>();
        tagDictionary.put("variable", "foo");
        tagDictionary.put("variable2", "foo2");
        tagDictionary.put("variable3", "foo3");
        String inputText = "This is a template with both ${variable} and ${variable2}";

        String output = engine.transform(tagDictionary, inputText).getValue();

        assertEquals(output, "This is a template with both foo and foo2");
    }

    @Test
    void shouldBeReturnTheSameTextIfNotFoundOnDictionary() {
        TemplateEngine engine = new TemplateEngine();
        Map<String, String> tagDictionary = new HashMap<>();
        tagDictionary.put("variable", "foo");
        String inputText = "This is a template with both ${variable} and ${variable2}";

        Result<TransformError, String> output = engine.transform(tagDictionary, inputText);

        assertEquals(output.getError(), TransformError.NOT_FOUND_DICTIONARY_KEY);
    }

    @Test
    void shouldSubstituteMoreThanOnceTheSameVariable() {
        TemplateEngine engine = new TemplateEngine();
        Map<String, String> tagDictionary = new HashMap<>();
        tagDictionary.put("variable", "foo");
        String inputText = "This is a template with both ${variable} and ${variable}";

        String output = engine.transform(tagDictionary, inputText).getValue();

        assertEquals(output, "This is a template with both foo and foo");
    }

    @Test
    void shouldBeReturnTheSameTextIfDictionaryIsNull() { //TODO para mirar
        TemplateEngine engine = new TemplateEngine();
        String inputText = "This is a template with both ${variable} and ${variable}";

        Result<TransformError, String> output = engine.transform(null, inputText);

        assertEquals(output.getError(), TransformError.NOT_DICTIONARY_FOUND);
    }

    @Test
    void shouldBeReturnNullIfInputTextIsNull() { // TODO para mirar
        TemplateEngine engine = new TemplateEngine();
        Map<String, String> tagDictionary = new HashMap<>();
        tagDictionary.put("variable", "foo");

        String output = engine.transform(tagDictionary, null).getValue();

        assertEquals(output, null);
    }

    @Test
    void shouldBeReturnValueIfIsEmptyKeyOnDictionary() { //TODO parar mirar
        TemplateEngine engine = new TemplateEngine();
        Map<String, String> tagDictionary = new HashMap<>();
        tagDictionary.put("", "foo");
        tagDictionary.put(" ", "foo2");
        String inputText = "This is a template with one ${} and other ${ }";

        String output = engine.transform(tagDictionary, inputText).getValue();

        assertEquals(output, "This is a template with one foo and other foo2");
    }

    @Test
    void foo() { //TODO parar mirar
        TemplateEngine engine = new TemplateEngine();
        Map<String, String> tagDictionary = new HashMap<>();
        tagDictionary.put("variable", "foo");
        String inputText = "This is a template with one ${variable}}";

        String output = engine.transform(tagDictionary, inputText).getValue();

        assertEquals(output, "This is a template with one foo");
    }

}
