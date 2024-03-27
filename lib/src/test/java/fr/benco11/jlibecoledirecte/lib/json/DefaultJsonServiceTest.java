package fr.benco11.jlibecoledirecte.lib.json;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

class DefaultJsonServiceTest {
    static final String json1 =
            """
                {"name":"C'est un test","id":78563,"date":"1944-06-06","time":"20:41","localDateTime":"2020-02-20 05:44"}""";

    static final String json2 =
            """
                {"code":45.86,"test1Dtos":[{"name":"C'est un test","id":78563,"date":"1944-06-06","time":"20:41","localDateTime":"2020-02-20 05:44"},{"name":"Test 2","id":5,"localDateTime":"1445-03-07 14:20"}]}""";

    static final Test1DTO dto1 = new Test1DTO(
            "C'est un test",
            78563,
            LocalDate.of(1944, 6, 6),
            LocalTime.of(20, 41),
            LocalDateTime.of(2020, 2, 20, 5, 44));

    static final Test2DTO dto2 = new Test2DTO(
            45.86, Arrays.asList(dto1, new Test1DTO("Test 2", 5, null, null, LocalDateTime.of(1445, 3, 7, 14, 20))));

    @Test
    void testSerialization() {
        JsonService service = new DefaultJsonService();

        assertEquals("data=" + json1, service.serialize(dto1));
        assertEquals("data=" + json2, service.serialize(dto2));
    }

    @Test
    void testEmptySerialization() {
        JsonService service = new DefaultJsonService();
        assertEquals("data={}", service.serialize(null));
        assertEquals("data={}", service.serialize(new EmptyDTO()));
    }

    @Test
    void testDeserialization() {
        JsonService service = new DefaultJsonService();

        JsonObject jsonObject1 = service.deserialize(json1);
        testDto(service, jsonObject1, dto1);
        assertEquals(dto1, service.deserialize(json1, Test1DTO.class));

        JsonObject jsonObject2 = service.deserialize(json2);

        assertEquals(45.86, jsonObject2.get("code").getAsDouble());

        JsonArray jsonObject2DtosArray = jsonObject2.getAsJsonArray("test1Dtos");
        testDto(
                service,
                jsonObject2DtosArray.get(0).getAsJsonObject(),
                dto2.test1Dtos().get(0));
        testDto(
                service,
                jsonObject2DtosArray.get(1).getAsJsonObject(),
                dto2.test1Dtos().get(1));

        assertEquals(dto2, service.deserialize(json2, Test2DTO.class));
    }

    private void testDto(JsonService service, JsonObject jsonObject1, Test1DTO dto) {
        assertEquals(dto.name(), jsonObject1.get("name").getAsString());
        assertEquals(dto.name(), service.deserialize(jsonObject1.get("name"), String.class));
        assertEquals(dto.name(), service.deserialize(jsonObject1.get("name"), new TypeToken<String>() {}.getType()));

        assertEquals(dto.id(), jsonObject1.get("id").getAsInt());
        assertEquals(dto.id(), service.deserialize(jsonObject1.get("id"), Integer.class));
        assertEquals(
                Integer.valueOf(dto.id()),
                service.deserialize(jsonObject1.get("id"), new TypeToken<Integer>() {}.getType()));
        assertEquals(dto.date(), service.deserialize(jsonObject1.get("date"), LocalDate.class));
        assertEquals(dto.date(), service.deserialize(jsonObject1.get("date"), new TypeToken<LocalDate>() {}.getType()));
        assertEquals(dto.time(), service.deserialize(jsonObject1.get("time"), LocalTime.class));
        assertEquals(dto.time(), service.deserialize(jsonObject1.get("time"), new TypeToken<LocalTime>() {}.getType()));
        assertEquals(dto.localDateTime(), service.deserialize(jsonObject1.get("localDateTime"), LocalDateTime.class));
        assertEquals(
                dto.localDateTime(),
                service.deserialize(jsonObject1.get("localDateTime"), new TypeToken<LocalDateTime>() {}.getType()));
    }

    public record Test1DTO(String name, int id, LocalDate date, LocalTime time, LocalDateTime localDateTime) {}

    public record Test2DTO(double code, List<Test1DTO> test1Dtos) {}

    public record EmptyDTO() {}
}
