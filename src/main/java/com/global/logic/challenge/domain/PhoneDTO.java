package com.global.logic.challenge.domain;

import lombok.*;

/**
 * @author Freddy Paredes
 * This class Handle the basic Phone information.
 *
 */
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class PhoneDTO {
  private Long number;
  private Integer cityCode;
  private String countryCode;
}//method closure
