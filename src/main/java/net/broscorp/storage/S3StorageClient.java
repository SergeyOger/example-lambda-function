package net.broscorp.storage;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class S3StorageClient {

    private final String tasksDumbBaseBucketName;

    private final AmazonS3 s3client;

    public S3StorageClient() {
        this.tasksDumbBaseBucketName = System.getenv("TaskBuketName");
        this.s3client = AmazonS3ClientBuilder.standard().withRegion(Regions.DEFAULT_REGION).build();
    }

    public void saveFile(String fileName, String content) {
        s3client.putObject(tasksDumbBaseBucketName, fileName, content);
        log.info("FIle {} successfully saved to bucket {}", fileName, tasksDumbBaseBucketName);
    }
}
