package peaksoft.servicies;

import peaksoft.dto.request.CompanyRequest;
import peaksoft.dto.response.CompanyResponse;
import peaksoft.dto.response.SimpleResponse;

import java.util.List;

public interface CompanyService {
    CompanyResponse saveCompany(CompanyRequest companyRequest);
    CompanyResponse findByCompanyId(Long companyId);
    List<CompanyResponse> findAllCompanies();
    CompanyResponse updateCompany(Long companyId,CompanyRequest companyRequest);
    SimpleResponse deleteCompany(Long companyId);
}
