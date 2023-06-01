package peaksoft.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import peaksoft.dto.response.CompanyResponse;
import peaksoft.dto.response.SimpleResponse;
import peaksoft.entity.Company;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Long> {
    @Query("select new peaksoft.dto.response.CompanyResponse(c.id,c.name,c.country,c.address,c.phoneNumber)from Company c")
    List<CompanyResponse> getAll();
    @Query("select new peaksoft.dto.response.CompanyResponse(c.id,c.name,c.country,c.address,c.phoneNumber)from Company c where c.id = :companyId")
    CompanyResponse getCompaniesById(Long companyId);
}