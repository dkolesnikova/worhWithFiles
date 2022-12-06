package guru.qa;

import com.fasterxml.jackson.databind.ObjectMapper;
import guru.qa.model.JsonObject;
import org.junit.jupiter.api.Test;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


import static org.assertj.core.api.Assertions.assertThat;


public class JsonParsingTest {

@Test
    public void jsonTest() throws IOException {
        ClassLoader cl = JsonParsingTest.class.getClassLoader();
        ObjectMapper objectMapper = new ObjectMapper();
        try (
                InputStream resources = cl.getResourceAsStream("example.json");
                InputStreamReader reader = new InputStreamReader(resources)

        ){
            JsonObject jsonObject = objectMapper.readValue(reader, JsonObject.class);
            assertThat (jsonObject.id).isEqualTo(5);
            assertThat (jsonObject.name).isEqualTo("Farm Shop");
            assertThat (jsonObject.work).isTrue();
            assertThat (jsonObject.products.get(0)).isEqualTo("Vegetable");
            assertThat (jsonObject.products.get(1)).isEqualTo("Fruit");
            assertThat (jsonObject.products.get(2)).isEqualTo("Cheese");
            assertThat (jsonObject.products.get(3)).isEqualTo("Meat");
            assertThat (jsonObject.products.get(4)).isEqualTo("Fish");
            assertThat (jsonObject.address.city).isEqualTo("Smolensk");
            assertThat (jsonObject.address.country).isEqualTo("Russia");
        }
    }
}
