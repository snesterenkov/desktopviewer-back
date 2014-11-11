package com.eklib.desktopviewer.services.company;

import com.eklib.desktopviewer.dto.CompanyDTO;
import com.eklib.desktopviewer.persistance.model.Company;
import com.eklib.desktopviewer.persistance.repository.CompanyRepository;
import com.eklib.desktopviewer.services.BasePagingAndSortingServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;


/**
 * Created by maxim on 10.11.2014.
 */

@Service
@Transactional
public class CompanyServicesImpl extends BasePagingAndSortingServiceImpl<CompanyDTO, Company, Long, CompanyRepository>
        implements CompanyServices {

    @Override
    public Class<Company> getEntityType() {
        return Company.class;
    }

    @Override
    public Class<CompanyDTO> getDTOType() {
        return CompanyDTO.class;
    }

    @Override
    public CompanyDTO update(Long id, CompanyDTO dto) {
        Company newCompany = getModelMapper().map(dto, getEntityType());
        newCompany.setId(id);
        Company updatedCompany = getRepository().merge(newCompany);
        return getModelMapper().map(updatedCompany, getDTOType());
    }

    @Override
    public CompanyDTO insert(CompanyDTO companyDTO) {
        Assert.hasLength(companyDTO.getName(), "Company name must not be null and not the empty String.");
        if(getRepository().getCompanyByName(companyDTO.getName()) != null){
            Assert.isTrue(true, "Company already exists");
        }
        return super.insert(companyDTO);
    }
}
