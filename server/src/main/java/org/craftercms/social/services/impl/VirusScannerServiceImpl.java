package org.craftercms.social.services.impl;


import com.rivetlogic.isaca.virusscanner.impl.VirusScannerImpl;
import org.craftercms.social.services.VirusScannerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class VirusScannerServiceImpl implements VirusScannerService {

    private final transient Logger log = LoggerFactory.getLogger(VirusScannerServiceImpl.class);

    private VirusScannerImpl virusScanner;

    public VirusScannerServiceImpl(){
        this.virusScanner = new VirusScannerImpl();
    }

    @Override
    public String scan(MultipartFile[] files) {

        String userErrorMessage = null;

        if(files != null){
            for(MultipartFile multipartFile : files){

                File tempFile = null;
                InputStream inputStream = null;

                try {
                    tempFile = File.createTempFile("tmp",null);
                    multipartFile.transferTo(tempFile);
                    inputStream = new FileInputStream(tempFile);
                    userErrorMessage = this.virusScanner.scan(inputStream);
                } catch (IOException e) {
                    userErrorMessage = VirusScannerImpl.SCAN_FAILED_MESSAGE;
                    log.error(e + " - USER MESSAGE: " + userErrorMessage);
                }
                finally {
                    if(inputStream != null){
                        try {
                            inputStream.close();
                        } catch (IOException e) {
                            userErrorMessage = VirusScannerImpl.SCAN_FAILED_MESSAGE;
                            log.error(e + " - USER MESSAGE: " + userErrorMessage);
                        }
                    }
                    if(tempFile != null){
                        tempFile.delete();
                    }
                }

                if(userErrorMessage != null){
                    break;
                }

            }
        }

        return userErrorMessage;
    }
}