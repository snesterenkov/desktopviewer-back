package com.eklib.desktopviewer.persistance.repository.companystructure;

import com.eklib.desktopviewer.persistance.model.companystructure.CompanyEntity;
import com.eklib.desktopviewer.persistance.model.enums.StatusEnum;
import com.eklib.desktopviewer.persistance.repository.BasePagingAndSortingRepository;

import java.util.List;

/**
 * Created by maxim on 10.11.2014.
 */
public interface CompanyRepository extends BasePagingAndSortingRepository<CompanyEntity, Long> {

    CompanyEntity getCompanyByName(String name);

    List<CompanyEntity> findByUser(String client);

    boolean changeStatus(CompanyEntity companyEntity, StatusEnum status);

    boolean hasOpenComponyForClient(Long idCompany, String login);

    List<CompanyEntity> findOpenByUser(String client);

    List<CompanyEntity> findOpenByUserHasProjectsAndNotOwner(String client);
}
