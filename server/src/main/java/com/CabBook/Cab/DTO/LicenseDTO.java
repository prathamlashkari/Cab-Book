package com.CabBook.cab.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LicenseDTO {

  private String number;
  private String expiryDate;

}
