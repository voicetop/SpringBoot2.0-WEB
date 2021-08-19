package com.test.springtest.dto.member;

import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter @Setter
@NoArgsConstructor
public class SearchDTO {

    @Length(max = 50, groups = {})
    @ApiParam(value = "사용자 이름", required = false, allowEmptyValue = true)
    String name;

}
