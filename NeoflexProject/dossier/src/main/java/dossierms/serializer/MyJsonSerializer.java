package dossierms.serializer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.common.errors.SerializationException;

import java.util.Map;

public class MyJsonSerializer<T> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    public MyJsonSerializer() {

    }

    public void configure(Map<String, ?> config, boolean isKey) {

    }


    public byte[] serialize(String topic, T data) {
        if (data == null) {
            return null;
        }
        try {
            return objectMapper.writeValueAsBytes(data);
        } catch (Exception e) {
            throw new SerializationException("Error serializing JSON message", e);
        }
    }

    public void close() {

    }
}
