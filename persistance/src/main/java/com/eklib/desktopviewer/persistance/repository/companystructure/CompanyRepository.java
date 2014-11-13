package com.eklib.desktopviewer.persistance.repository.companystructure;

import com.eklib.desktopviewer.persistance.model.companystructure.CompanyEntity;
import com.eklib.desktopviewer.persistance.repository.BasePagingAndSortingRepository;

/**
 * Created by maxim on 10.11.2014.
 */
public interface CompanyRepository extends BasePagingAndSortingRepository<CompanyEntity, Long> {
    CompanyEntity getCompanyByName(String name);
}
