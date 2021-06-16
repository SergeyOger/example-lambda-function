package net.broscorp.handler;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.ScheduledEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.broscorp.models.SyncReport;
import net.broscorp.processor.TaskSyncProcessor;

@Slf4j
@RequiredArgsConstructor
public class TaskSyncHandler implements RequestHandler<ScheduledEvent, SyncReport> {

    private final TaskSyncProcessor taskSyncProcessor;

    @Override
    public SyncReport handleRequest(ScheduledEvent scheduledEvent, Context context) {
        SyncReport report = taskSyncProcessor.syncTasks();
        log.info("Report from {} contains {} records", report.getSyncTimestamp(), report.getProcessedRecordsCount());
        return report;
    }
}

