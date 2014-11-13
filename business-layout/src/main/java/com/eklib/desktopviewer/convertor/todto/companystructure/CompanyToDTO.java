package com.eklib.desktopviewer.convertor.todto.companystructure;

import com.eklib.desktopviewer.dto.companystructure.CompanyDTO;
import com.eklib.desktopviewer.persistance.model.companystructure.CompanyEntity;
import com.google.common.base.Function;
import org.springframework.stereotype.Component;

/**
 * Created by vadim on 13.11.2014.
 */
@Component
public class CompanyToDTO implements Function<CompanyEntity,CompanyDTO> {


    @Override
    public CompanyDTO apply(CompanyEntity company) {
        if(company == null){
            return null;
        }
        CompanyDTO companyDTO = new CompanyDTO();
        companyDTO.setId(company.getId());
        companyDTO.setName(company.getName());
        return companyDTO;
    }
}
