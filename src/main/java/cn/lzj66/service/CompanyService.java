package cn.lzj66.service;


import cn.lzj66.entity.Company;

import java.util.List;

public interface CompanyService {
    List<Company> getCompanyList();
    void createCompany(Company company);
    void deleteCompany(String id);

    void updateCompany(Company company);

    List<Company> getUserCompanyList(String userId);
}
