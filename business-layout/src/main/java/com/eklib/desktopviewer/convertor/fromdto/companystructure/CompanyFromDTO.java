package com.eklib.desktopviewer.convertor.fromdto.companystructure;

import com.eklib.desktopviewer.dto.companystructure.CompanyDTO;
import com.eklib.desktopviewer.persistance.model.companystructure.CompanyEntity;
import com.eklib.desktopviewer.persistance.repository.companystructure.CompanyRepository;
import com.google.common.base.Function;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by vadim on 13.11.2014.
 */
@Component
public class CompanyFromDTO implements Function<CompanyDTO, CompanyEntity> {

    @Autowired
    private CompanyRepository companyRepository;

    @Override
    public CompanyEntity apply(CompanyDTO companyDTO) {
        CompanyEntity company;
        if(companyDTO == null){
            return null;
        }
        if(companyDTO.getId() == null || companyDTO.getId() == 0l){
            company = new CompanyEntity();
        } else {
            company = companyRepository.findById(companyDTO.getId());
        }
        company.setName(companyDTO.getName());

        return company;
    }
}
