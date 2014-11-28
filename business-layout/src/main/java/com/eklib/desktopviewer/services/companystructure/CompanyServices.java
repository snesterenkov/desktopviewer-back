package com.eklib.desktopviewer.services.companystructure;

import com.eklib.desktopviewer.dto.companystructure.CompanyDTO;
import com.eklib.desktopviewer.dto.companystructure.CompanyDetailDTO;
import com.eklib.desktopviewer.dto.enums.StatusDTO;
import com.eklib.desktopviewer.persistance.model.companystructure.CompanyEntity;
import com.eklib.desktopviewer.services.BaseCrudService;

import java.util.Collection;

/**
 * Created by maxim on 10.11.2014.
 */
public interface CompanyServices {

    CompanyDTO update(Long id, CompanyDTO dto, String client);

    CompanyDTO insert(CompanyDTO companyDTO, String client);

    CompanyDetailDTO findById(Long id, String client);

    Collection<CompanyDetailDTO> findAll(String client);

    CompanyDetailDTO changeStatus(Long id, StatusDTO statusDTO,String client);
}
