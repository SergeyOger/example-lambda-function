package net.broscorp.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.Instant;


@RequiredArgsConstructor
public class SyncReport {

    @Getter
    private final int processedRecordsCount;

    @Getter
    private final Instant syncTimestamp = Instant.now();

}
