package net.broscorp.processor;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import net.broscorp.api.MockApiClient;
import net.broscorp.models.SyncReport;
import net.broscorp.models.Task;
import net.broscorp.storage.S3StorageClient;

import java.util.List;

@Slf4j
public class TaskSyncProcessor {

    private final S3StorageClient storageClient;

    private final ObjectMapper objectMapper;

    private final MockApiClient apiClient;

    public TaskSyncProcessor() {
        this.storageClient = new S3StorageClient();
        this.objectMapper = new ObjectMapper();
        this.apiClient = new MockApiClient();
    }

    public SyncReport syncTasks() {
        List<Task> tasks = apiClient.getTasks();
        SyncReport report = new SyncReport(tasks.size());
        storageClient.saveFile(
                String.valueOf(report.getSyncTimestamp().toEpochMilli()), dumpTasks(tasks));
        return report;
    }

    private String dumpTasks(List<Task> sourceRecords) {
        try {
            return objectMapper.writeValueAsString(sourceRecords);
        } catch (JsonProcessingException ex) {
            log.error("Processing tasks failed, {}", ex.getMessage());
            throw new RuntimeException(ex);
        }
    }
}
