package com.eklib.desktopviewer.services.snapshot;

import com.eklib.desktopviewer.convertor.fromdto.snapshot.SnapshotFromDTO;
import com.eklib.desktopviewer.convertor.todto.snapshot.SnapshotToDTO;
import com.eklib.desktopviewer.dto.snapshot.SnapshotDTO;
import com.eklib.desktopviewer.persistance.model.security.RoleEntity;
import com.eklib.desktopviewer.persistance.model.security.UserEntity;
import com.eklib.desktopviewer.persistance.model.snapshot.SnapshotEntity;
import com.eklib.desktopviewer.persistance.repository.security.UserRepository;
import com.eklib.desktopviewer.persistance.repository.snapshot.SnapshotRepository;
import com.google.common.collect.FluentIterable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
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
@Transactional
public class SnapshotServiceImpl implements SnapshotService {

    private static int small_width = 30;
    private static int small_height = 27;
    private static int big_width = 800;
    private static int big_height = 600;
    private static String fileFormat = "jpg";

    @Value("${imagesDir}")
    private String dirToImage;

    @Autowired
    private SnapshotRepository repository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SnapshotFromDTO snapshotFromDTO;
    @Autowired
    private SnapshotToDTO snapshotToDTO;

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

    @Override
    public List<String> getFileName( String client) {
        List<String> fileNames = new ArrayList<String>();
        List<SnapshotEntity> snapshots = repository.findByUserName(client);
        for(SnapshotEntity entity : snapshots){
            fileNames.add(entity.getFilename());
        }
        return fileNames;
    }


    @Override
    public List<SnapshotDTO> findSnapshotsByUser(Long userId, String client) {
        List<SnapshotEntity> snapshots;
        List<SnapshotDTO> snapshotDTOs = new ArrayList<SnapshotDTO>();

        if(hasPermissionsViewSnapshots(userId, client)) {
            snapshots = repository.findByUserId(userId);

            snapshotDTOs = FluentIterable.from(snapshots).transform(snapshotToDTO).toList();

            for (SnapshotDTO snapshotDTO : snapshotDTOs) {
                snapshotDTO.setFile(resizeImage(snapshotDTO.getFileName()));
            }
        }
        return snapshotDTOs;
    }

    @Override
    public List<SnapshotDTO> findSnapshotsByUserAndDate(Long userId, Date date, String client) {
        List<SnapshotEntity> snapshots;
        List<SnapshotDTO> snapshotDTOs = new ArrayList<SnapshotDTO>();

        if(hasPermissionsViewSnapshots(userId, client)) {
            snapshots = repository.findByUserIdAndDate(userId,date);

            snapshotDTOs = FluentIterable.from(snapshots).transform(snapshotToDTO).toList();

            for (SnapshotDTO snapshotDTO : snapshotDTOs) {
                snapshotDTO.setFile(resizeImage(snapshotDTO.getFileName()));
            }
        }
        return snapshotDTOs;
    }

    @Override
    public SnapshotDTO findById(Long id) {
        return snapshotToDTO.apply(repository.findById(id));
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

    private boolean hasPermissionsViewSnapshots(Long userId, String client)  {
        UserEntity userEntity = userRepository.findById(userId);
        return (userEntity.readRoles().contains(RoleEntity.DESK_ADMIN) || userEntity.getLogin().equals(client));
    }

    private byte[] resizeImage(String fileName) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            Image newImg = ImageIO.read(new File(fileName)).getScaledInstance(small_width, small_height, Image.SCALE_SMOOTH);
            BufferedImage bim = new BufferedImage(small_width, small_height, java.awt.image.BufferedImage.TYPE_INT_RGB);
            bim.createGraphics().drawImage(newImg, 0, 0, null);
            ImageIO.write(bim, fileFormat, out);
        } catch (IOException exp) {
            //ToDO write log
        } finally {
            try {
                out.close();
            } catch (IOException e) {
                //ToDO write log
            }
        }
        return out.toByteArray();
    }
}
