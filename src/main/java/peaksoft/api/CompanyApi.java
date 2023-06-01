package peaksoft.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import peaksoft.dto.request.CompanyRequest;
import peaksoft.dto.response.CompanyResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.servicies.CompanyService;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyApi {
    private final CompanyService companyService;

    @Autowired
    public CompanyApi(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping
    public List<CompanyResponse> getAll() {
        return companyService.findAllCompanies();
    }

    @PostMapping
    public CompanyResponse saveCompany(@RequestBody CompanyRequest companyRequest) {
        return companyService.saveCompany(companyRequest);
    }

    @GetMapping("/{companyId}")
    public CompanyResponse getById(@PathVariable Long companyId) {
        return companyService.findByCompanyId(companyId);
    }

    @PutMapping("/{companyId}")
    public CompanyResponse updateCompany(@PathVariable Long companyId, @RequestBody CompanyRequest companyRequest) {
        return companyService.updateCompany(companyId, companyRequest);
    }

    @DeleteMapping("/{companyId}")
    public SimpleResponse deleteCompanyById(@PathVariable Long companyId) {
        return companyService.deleteCompany(companyId);
    }

}
