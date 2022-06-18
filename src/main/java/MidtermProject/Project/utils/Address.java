package MidtermProject.Project.utils;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Embeddable
@Data
@NoArgsConstructor
public class Address {
    private String address;
    private String city;
    private String country;
    private long postalCode;


}
