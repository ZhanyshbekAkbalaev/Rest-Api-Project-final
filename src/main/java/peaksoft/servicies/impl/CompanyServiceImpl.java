package peaksoft.servicies.impl;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import peaksoft.dto.request.CompanyRequest;
import peaksoft.dto.response.CompanyResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.entity.Company;
import peaksoft.repositories.CompanyRepository;
import peaksoft.servicies.CompanyService;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@Transactional
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public CompanyResponse saveCompany(CompanyRequest companyRequest) {
        Company company = new Company();
        company.setName(companyRequest.getName());
        company.setCountry(companyRequest.getCountry());
        company.setAddress(companyRequest.getAddress());
        company.setPhoneNumber(companyRequest.getPhoneNumber());
        companyRepository.save(company);
        return new CompanyResponse(
                company.getId(),
                company.getName(),
                company.getCountry(),
                company.getAddress(),
                company.getPhoneNumber()
        );
    }

    @Override
    public CompanyResponse findByCompanyId(Long companyId) {
        return companyRepository.getCompaniesById(companyId);

    }

    @Override
    public List<CompanyResponse> findAllCompanies() {
        return companyRepository.getAll();
    }

    @Override
    public CompanyResponse updateCompany(Long companyId, CompanyRequest companyRequest) {
        Company company = companyRepository.findById(companyId).orElseThrow(() -> new NoSuchElementException("Company with id " + companyId + " is not found!"));
        company.setName(companyRequest.getName());
        company.setCountry(companyRequest.getCountry());
        company.setAddress(companyRequest.getAddress());
        company.setPhoneNumber(companyRequest.getPhoneNumber());
        companyRepository.save(company);
        return new CompanyResponse(
                company.getId(),
                company.getName(),
                company.getCountry(),
                company.getAddress(),
                company.getPhoneNumber()
        );
    }

    @Override
    public SimpleResponse deleteCompany(Long companyId) {
        if (!companyRepository.existsById(companyId)) {
            throw new NoSuchElementException("Company with id " + companyId + " is not found!");
        }
        companyRepository.deleteById(companyId);
        return SimpleResponse.builder()
                .status(HttpStatus.OK)
                .message(String.format("Company by id is: %s is successfully delete", companyId))
                .build();
    }
}
