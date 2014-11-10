package com.eklib.desktopviewer.persistance.repository;

import com.eklib.desktopviewer.persistance.model.Company;

/**
 * Created by maxim on 10.11.2014.
 */
public interface CompanyRepository extends BasePagingAndSortingRepository<Company, Long>{
    Company getCompanyByName(String name);
}
