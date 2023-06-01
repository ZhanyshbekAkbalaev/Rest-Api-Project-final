package peaksoft.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CompanyRequest {
    private String name;
    private String country;
    private String address;
    private String phoneNumber;
}
