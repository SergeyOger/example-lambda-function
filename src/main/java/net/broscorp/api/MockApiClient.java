package net.broscorp.api;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.broscorp.models.Task;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Slf4j
public class MockApiClient {

    private final String tasksBaseUrl;

    public MockApiClient() {
        this.tasksBaseUrl = System.getenv("TasksBaseUrl");
    }

    public List<Task> getTasks() {
        final HttpClient httpClient = HttpClient.newHttpClient();
        final HttpRequest request = HttpRequest.newBuilder(URI.create(tasksBaseUrl))
                .header("accept", "application/json").build();
        try {
            final HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            ObjectMapper objectMapper = new ObjectMapper();
            TypeReference<List<Task>> type = new TypeReference<>() {
            };
            List<Task> tasks = objectMapper.readValue(response.body(), type);
            log.info("Received {} tasks", tasks.size());

            return tasks;
        } catch (Exception ex) {
            log.error("Fetching tasks failed {}", ex.getMessage());
            throw new RuntimeException(ex);
        }
    }
}
