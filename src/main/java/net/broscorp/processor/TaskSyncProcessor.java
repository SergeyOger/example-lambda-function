package net.broscorp.processor;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.broscorp.models.Task;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

@Slf4j
public class TaskSyncProcessor {

    private final static String TASK_BASE_PATH = "https://jsonplaceholder.typicode.com/users/1/todos";

    public void syncTasks() {

    }

    public List<Task> getTasks() throws IOException, InterruptedException {
        final HttpClient httpClient = HttpClient.newHttpClient();
        final HttpRequest request = HttpRequest.newBuilder(
                URI.create(TASK_BASE_PATH))
                .header("accept", "application/json")
                .build();

        final HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
        ObjectMapper objectMapper = new ObjectMapper();
        TypeReference<List<Task>> type = new TypeReference<>() {
        };
        List<Task> tasks = objectMapper.readValue(response.body(), type);
        log.info("Received {} tasks", tasks.size());
        return tasks;
    }

    private void saveDump(List<Task> tasks) {

    }
}
