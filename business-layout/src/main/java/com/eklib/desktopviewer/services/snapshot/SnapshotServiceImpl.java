package com.eklib.desktopviewer.services.snapshot;

import com.eklib.desktopviewer.convertor.fromdto.snapshot.SnapshotFromDTO;
import com.eklib.desktopviewer.dto.snapshot.SnapshotDTO;
import com.eklib.desktopviewer.persistance.model.security.UserEntity;
import com.eklib.desktopviewer.persistance.model.snapshot.SnapshotEntity;
import com.eklib.desktopviewer.persistance.repository.security.UserRepository;
import com.eklib.desktopviewer.persistance.repository.snapshot.SnapshotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by vadim on 18.12.2014.
 */
@Service
public class SnapshotServiceImpl implements SnapshotService {

    @Value("${imagesDir}")
    private String dirToImage;

    @Autowired
    private SnapshotRepository repository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SnapshotFromDTO snapshotFromDTO;

    @Override
    public SnapshotDTO insert(SnapshotDTO snapshotDTO, String client) {
        UserEntity userEntity = userRepository.getUserByName(client);
        Assert.notNull(userEntity, "Client is  null");
        Date date = Calendar.getInstance().getTime();
        String fileName = dirToImage+"\\"+userEntity.getId()+"\\" + getFileName(date) + ".jpg";
        saveFile(snapshotDTO, fileName);
        SnapshotEntity entity = snapshotFromDTO.apply(snapshotDTO);
        entity.setUser(userEntity);
        entity.setFilename(fileName);
        entity.setDate(date);
        repository.insert(entity);
        return null;
    }


    private String getFileName(Date date){
        // Create an instance of SimpleDateFormat used for formatting
        // the string representation of date (month/day/year)
        DateFormat df = new SimpleDateFormat("yyyyMMdd_HH_mm_ss");

        // Using DateFormat format method we can create a string
        // representation of a date with the defined format.
        String reportDate = df.format(date);
        return reportDate;
    }


    private void saveFile(SnapshotDTO snapshotDTO, String fileName){
        if (snapshotDTO.getFile() != null && snapshotDTO.getFile().length != 0) {
            try {
                byte[] bytes = snapshotDTO.getFile();
                File yourFile = new File(fileName);
                yourFile.getParentFile().mkdirs();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(yourFile));
                stream.write(bytes);
                stream.close();
                return;
            } catch (Exception e) {
                throw new IllegalArgumentException("Bad format file");
            }
        }
        throw new IllegalArgumentException("Bad format file");
    }

    @Override
    public List<String> getFileName( String client) {
        List<String> fileNames = new ArrayList<String>();
        List<SnapshotEntity> snapshots = repository.findByUserName(client);
        for(SnapshotEntity entity : snapshots){
            fileNames.add(entity.getFilename());
        }
        return fileNames;
    }
}
