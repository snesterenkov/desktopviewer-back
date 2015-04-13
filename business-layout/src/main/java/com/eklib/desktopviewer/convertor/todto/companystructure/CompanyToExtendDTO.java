package com.eklib.desktopviewer.convertor.todto.companystructure;

import com.eklib.desktopviewer.dto.companystructure.CompanyExtendDTO;
import com.eklib.desktopviewer.dto.enums.StatusDTO;
import com.eklib.desktopviewer.persistance.model.companystructure.CompanyEntity;
import com.google.common.base.Function;
import org.springframework.stereotype.Component;

/**
 * Created by human on 09.04.2015.
 */
@Component
public class CompanyToExtendDTO implements Function<CompanyEntity, CompanyExtendDTO>{

    @Override
    public CompanyExtendDTO apply(CompanyEntity company){
        if(company == null) {
            return null;
        }
        CompanyExtendDTO companyExtendDTO = new CompanyExtendDTO();
        companyExtendDTO.setId(company.getId());
        companyExtendDTO.setName(company.getName());
        companyExtendDTO.setStatus(StatusDTO.valueOf(company.getStatus().name()));
        return companyExtendDTO;
    }
}
