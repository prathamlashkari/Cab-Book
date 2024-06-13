package com.CabBook.cab.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

  private Integer id;
  private String email;
  private String name;
  private String mobile;

}
