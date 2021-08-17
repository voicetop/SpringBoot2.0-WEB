package com.test.springtest.dto.group;

import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter @Setter
@NoArgsConstructor
public class SearchDTO {

    @Length(max = 30)
    @ApiParam(value = "그룹 이름", required = false, allowEmptyValue = true)
    String name;

}
