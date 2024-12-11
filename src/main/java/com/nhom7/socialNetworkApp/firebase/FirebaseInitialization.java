package com.nhom7.socialNetworkApp.firebase;


import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

@Service
public class FirebaseInitialization {


    // In your class
    private static final Logger logger = LoggerFactory.getLogger(FirebaseInitialization.class);

    @PostConstruct
    public void initialize() {
        FileInputStream serviceAccount = null;
        try {
            serviceAccount = new FileInputStream("./key-api-firebase.json");
        } catch (FileNotFoundException e) {
            logger.error("Service account file not found", e);
            throw new RuntimeException("Service account file not found", e);
        }

        FirebaseOptions options = null;
        try {
            options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .setDatabaseUrl("https://bailtweb-default-rtdb.firebaseio.com")
                    .build();
        } catch (IOException e) {
            logger.error("Error while setting Firebase options", e);
            throw new RuntimeException("Error while setting Firebase options", e);
        }

        FirebaseApp.initializeApp(options);
    }

}
