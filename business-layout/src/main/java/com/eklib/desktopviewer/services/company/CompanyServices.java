package com.eklib.desktopviewer.services.company;

import com.eklib.desktopviewer.dto.CompanyDTO;
import com.eklib.desktopviewer.persistance.model.Company;
import com.eklib.desktopviewer.persistance.repository.CompanyRepository;
import com.eklib.desktopviewer.services.BaseCrudService;

/**
 * Created by maxim on 10.11.2014.
 */
public interface CompanyServices extends BaseCrudService<CompanyDTO, Company, Long, CompanyRepository>{

    CompanyDTO createCompany(CompanyDTO company);

    @Override
    CompanyDTO insert(CompanyDTO dto);
}
