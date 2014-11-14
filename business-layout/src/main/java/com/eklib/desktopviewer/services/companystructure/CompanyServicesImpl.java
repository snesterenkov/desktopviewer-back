package com.eklib.desktopviewer.services.companystructure;

import com.eklib.desktopviewer.convertor.fromdto.companystructure.CompanyFromDTO;
import com.eklib.desktopviewer.convertor.todto.companystructure.CompanyToDTO;
import com.eklib.desktopviewer.dto.companystructure.CompanyDTO;
import com.eklib.desktopviewer.persistance.model.companystructure.CompanyEntity;
import com.eklib.desktopviewer.persistance.repository.companystructure.CompanyRepository;
import com.google.common.collect.FluentIterable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Collection;


/**
 * Created by maxim on 10.11.2014.
 */

@Service
@Transactional
public class CompanyServicesImpl implements CompanyServices {

    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private CompanyFromDTO companyFromDTO;
    @Autowired
    private CompanyToDTO companyToDTO;

    @Override
    public CompanyDTO update(Long id, CompanyDTO dto) {
        dto.setId(id);
        CompanyEntity newCompany = companyFromDTO.apply(dto);
        CompanyEntity updatedCompany = companyRepository.update(newCompany);
        return companyToDTO.apply(updatedCompany);
    }

    @Override
    public CompanyDTO insert(CompanyDTO companyDTO) {
        Assert.hasLength(companyDTO.getName(), "Company name must not be null and not the empty String.");
        if(companyRepository.getCompanyByName(companyDTO.getName()) != null){
            Assert.isTrue(true, "Company already exists");
        }
        CompanyEntity newCompany = companyFromDTO.apply(companyDTO);
        CompanyEntity updatedCompany = companyRepository.insert(newCompany);
        return companyToDTO.apply(updatedCompany);
    }

    @Override
    public CompanyDTO findById(Long id) {
        return companyToDTO.apply(companyRepository.findById(id));
    }

    @Override
    public Collection<CompanyDTO> findAll() {
        return FluentIterable.from(companyRepository.findAll()).transform(companyToDTO).toList();
    }

    @Override
    public void delete(Long id) {
        CompanyEntity company = companyRepository.findById(id);
        companyRepository.delete(company);
    }
}
