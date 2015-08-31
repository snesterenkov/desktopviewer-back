package com.eklib.desktopviewer.services.snapshot;

import com.eklib.desktopviewer.convertor.fromdto.snapshot.SnapshotFromDTO;
import com.eklib.desktopviewer.convertor.todto.snapshot.FullSnapshotToDTO;
import com.eklib.desktopviewer.convertor.todto.snapshot.SnapshotToDTO;
import com.eklib.desktopviewer.dto.snapshot.FullSnapshotDTO;
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
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
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

    private static int small_width = 100;
    private static int small_height = 80;
    private static int big_width = 800;
    private static int big_height = 600;
    private static String fileFormat = "jpg";

    @Value("${imagesDir}")
    private String dirToImage;

    @Value("${resizedImagesDir}")
    private String dirToResizedImage;

    @Autowired
    private SnapshotRepository repository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private SnapshotFromDTO snapshotFromDTO;
    @Autowired
    private SnapshotToDTO snapshotToDTO;

    @Autowired
    private FullSnapshotToDTO fullSnapshotToDTO;

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

                UserEntity userEntity = userRepository.getUserByName(client);
                String fileName = dirToResizedImage+"\\"+userEntity.getId()+"\\" + getFileName(snapshotDTO.getDate()) + ".jpg";
                if(!isResizedImageExists(fileName)) {
                    byte[] fileStream = resizeImage(snapshotDTO.getFileName());
                    saveFileStream(fileStream, fileName);
                    snapshotDTO.setFile(fileStream);
                }  else {
                    Path path = Paths.get(fileName);
                    try {
                        snapshotDTO.setFile(Files.readAllBytes(path));
                    } catch (IOException e) {
                        throw new IllegalArgumentException("Bad format file");
                    }
                }

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
                UserEntity userEntity = userRepository.getUserByName(client);
                String fileName = dirToResizedImage+"\\"+userEntity.getId()+"\\" + getFileName(snapshotDTO.getDate()) + ".jpg";
                if(!isResizedImageExists(fileName)) {
                    byte[] fileStream = resizeImage(snapshotDTO.getFileName());
                    saveFileStream(fileStream, fileName);
                    snapshotDTO.setFile(fileStream);
                }  else {
                    Path path = Paths.get(fileName);
                    try {
                        snapshotDTO.setFile(Files.readAllBytes(path));
                    } catch (IOException e) {
                        throw new IllegalArgumentException("Bad format file");
                    }
                }
            }
        }
        return snapshotDTOs;
    }

    @Override
    public FullSnapshotDTO findById(Long id) {
        return fullSnapshotToDTO.apply(repository.findById(id));
    }

    public List<Integer> calculateCountScreenshotsOnDayByMonth(Long userId, Date date) {
        Date  startDate = getFirstDayOfMonth(date);
        Date endDate = getLastDayOfMonth(date);
        Calendar start = Calendar.getInstance();
        start.setTime(startDate);

        Calendar end = Calendar.getInstance();
        end.setTime(endDate);
        List<Integer> counts = new ArrayList<Integer>();
        while(!start.after(end)){
            counts.add(repository.findByUserIdAndDate(userId, start.getTime()).size());
            start.add(Calendar.DATE, 1);
        }
        return counts;
    }

    private Date getLastDayOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMaximum(Calendar.DAY_OF_MONTH));

        return c.getTime();
    }

    private Date getFirstDayOfMonth(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.set(Calendar.DAY_OF_MONTH, c.getActualMinimum(Calendar.DAY_OF_MONTH));

        return c.getTime();
    }

    private boolean isResizedImageExists(String filePath) {
        File f = new File(filePath);
        return f.exists();
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
            saveFileStream(snapshotDTO.getFile(),fileName);
        }
        throw new IllegalArgumentException("Bad format file");
    }

    private void saveFileStream(byte[] bytes, String fileName){
        try {
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
