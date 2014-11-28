package com.eklib.desktopviewer.services.companystructure;

import com.eklib.desktopviewer.convertor.fromdto.companystructure.CompanyFromDTO;
import com.eklib.desktopviewer.convertor.todto.companystructure.CompanyToDTO;
import com.eklib.desktopviewer.convertor.todto.companystructure.CompanyToDelatilDTO;
import com.eklib.desktopviewer.dto.companystructure.CompanyDTO;
import com.eklib.desktopviewer.dto.companystructure.CompanyDetailDTO;
import com.eklib.desktopviewer.dto.enums.StatusDTO;
import com.eklib.desktopviewer.persistance.model.companystructure.CompanyEntity;
import com.eklib.desktopviewer.persistance.model.enums.StatusEnum;
import com.eklib.desktopviewer.persistance.model.security.RoleEntity;
import com.eklib.desktopviewer.persistance.model.security.UserEntity;
import com.eklib.desktopviewer.persistance.repository.companystructure.CompanyRepository;
import com.eklib.desktopviewer.persistance.repository.security.UserRepository;
import com.google.common.collect.FluentIterable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.Collection;
import java.util.Set;


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
    @Autowired
    private CompanyToDelatilDTO companyToDelatilDTO;
    @Autowired
    private UserRepository userRepository;

    @Override
    public CompanyDTO update(Long id, CompanyDTO dto, String client) {
        dto.setId(id);
        CompanyEntity newCompany = companyFromDTO.apply(dto);
        if(newCompany != null && newCompany.getOwner() != null ){
            if(newCompany.getOwner().getLogin().equals(client)){
                CompanyEntity updatedCompany = companyRepository.update(newCompany);
                return companyToDTO.apply(updatedCompany);
            }
            Assert.isTrue(false, "Cann`t update not your company");
        }
        Assert.isTrue(false, "Cann`t find company");
        return new CompanyDTO();
    }

    @Override
    public CompanyDTO insert(CompanyDTO companyDTO, String client) {
        Assert.hasLength(companyDTO.getName(), "Company name must not be null and not the empty String.");
        Assert.isNull(companyDTO.getId(), "Company id is not null");
        if(companyRepository.getCompanyByName(companyDTO.getName()) != null){
            Assert.isTrue(false, "Company already exists");
            return new CompanyDTO();
        }
        CompanyEntity newCompany = companyFromDTO.apply(companyDTO);
        UserEntity userEntity = userRepository.getUserByName(client);
        if(userEntity == null){
            Assert.isTrue(false, "Client is null");
            return new CompanyDTO();
        }
        if(newCompany != null) {
            newCompany.setOwner(userEntity);
            CompanyEntity updatedCompany = companyRepository.insert(newCompany);
            Set<RoleEntity> roles = userEntity.readRoles();
            if(!roles.contains(RoleEntity.DESK_USER_COMPANY)){
                roles.add(RoleEntity.DESK_USER_COMPANY);
                userEntity.writeRoles(roles);
                userRepository.update(userEntity);
            }
            return companyToDTO.apply(updatedCompany);
        }
        Assert.isTrue(false, "Cann`t create company");
        return new CompanyDTO();
    }

    @Override
    public CompanyDetailDTO findById(Long id, String client) {
        CompanyEntity companyEntity = companyRepository.findById(id);
        if(companyEntity != null && companyEntity.getOwner() != null) {
            if (companyEntity.getOwner().getLogin().equals(client)) {
                return companyToDelatilDTO.apply(companyEntity);
            }
        }
        Assert.isTrue(false, "Cann`t find company");
        return new CompanyDetailDTO();
    }

    @Override
    public Collection<CompanyDetailDTO> findAll(String client) {
        return FluentIterable.from(companyRepository.findByUser(client)).transform(companyToDelatilDTO).toList();
    }

    @Override
    public CompanyDetailDTO changeStatus(Long id, StatusDTO statusDTO, String client) {
        CompanyEntity company = companyRepository.findById(id);
        StatusEnum newStatus = StatusEnum.valueOf(statusDTO.name());
        if(company.getOwner().getLogin().equals(client)) {
            if (companyRepository.changeStatus(company, newStatus)) {
                company = companyRepository.findById(id);
            }
            return companyToDelatilDTO.apply(company);
        }
        Assert.isTrue(false, "Cann`t change status in company if you not owner");
        return new CompanyDetailDTO();
    }
}
