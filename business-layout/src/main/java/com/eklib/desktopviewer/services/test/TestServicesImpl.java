package com.eklib.desktopviewer.services.test;

import com.eklib.desktopviewer.convertor.fromdto.test.TestFromDTO;
import com.eklib.desktopviewer.convertor.todto.test.TestToDTO;
import com.eklib.desktopviewer.dto.test.ImageBytesDTO;
import com.eklib.desktopviewer.dto.test.TestDTO;
import com.eklib.desktopviewer.persistance.model.test.TestEntity;
import com.eklib.desktopviewer.persistance.repository.test.TestRepository;
import com.google.common.collect.FluentIterable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;

/**
 * Created by Maxim on 13.11.2014.
 */
@Service
@Transactional
public class TestServicesImpl implements TestServices {

    @Autowired
    private TestFromDTO testFromDTO;
    @Autowired
    private TestToDTO testToDTO;
    @Autowired
    private TestRepository testRepository;
    @Value("${imagesDir}")
    private String dirToImage;

    @Override
    public TestDTO insert(TestDTO dto) {
        TestEntity testEntity = testFromDTO.apply(dto);
        TestEntity newTestEntity = testRepository.insert(testEntity);
        return testToDTO.apply(newTestEntity);
    }



    @Override
    public TestDTO findById(Long id) {
        return testToDTO.apply(testRepository.findById(id));
    }

    @Override
    public Collection<TestDTO> findAll() {
        return FluentIterable.from(testRepository.findAll()).transform(testToDTO).toList();
    }

    public String handleFileUpload(ImageBytesDTO file){
        if (file.getBytes() != null && file.getBytes().length != 0) {
            try {
                byte[] bytes = file.getBytes();
                File yourFile = new File(dirToImage + getFileName() + ".jpg");
                yourFile.getParentFile().mkdirs();
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(yourFile));
                stream.write(bytes);
                stream.close();
                return "You successfully uploaded";
            } catch (Exception e) {
                throw new IllegalArgumentException("Bad format");
            }
        }
        throw new IllegalArgumentException("Bad format");
    }

    private String getFileName(){
        // Create an instance of SimpleDateFormat used for formatting
        // the string representation of date (month/day/year)
        DateFormat df = new SimpleDateFormat("yyyyMMdd_HH_mm_ss");

        // Get the date today using Calendar object.
        Date today = Calendar.getInstance().getTime();
        // Using DateFormat format method we can create a string
        // representation of a date with the defined format.
        String reportDate = df.format(today);
        return reportDate;
    }

}
