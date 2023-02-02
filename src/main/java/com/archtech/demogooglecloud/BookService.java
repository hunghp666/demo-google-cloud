package com.archtech.demogooglecloud;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;
    public String newBook(){
        return "";
    }

    private static final String BUCKET_NAME = "hunghp-demo-spring.appspot.com";

    public void uploadFile(String fileName, MultipartFile multipartFile) throws Exception {
        Storage storage = StorageOptions.newBuilder()
                .setCredentials(GoogleCredentials.fromStream(new ClassPathResource("account.json").getInputStream()))
                .build()
                .getService();

        Bucket bucket = storage.get(BUCKET_NAME);
        BlobId blobId = BlobId.of(BUCKET_NAME, fileName);
        Blob blob = bucket.create(blobId.getName(), multipartFile.getBytes(), "application/octet-stream");
    }
}
