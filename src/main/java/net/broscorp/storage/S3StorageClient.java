package net.broscorp.storage;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class S3StorageClient {

    private static final String TASKS

    private final AmazonS3 s3client;

    public S3StorageClient() {
        this.s3client = AmazonS3ClientBuilder.standard().withRegion(Regions.DEFAULT_REGION).build();
    }

    public void saveFile() {
        s3client.
    }

    private void createBucket(String bucketName) {

    }
}
