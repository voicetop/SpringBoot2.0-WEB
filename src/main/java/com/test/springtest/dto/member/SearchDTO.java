package com.test.springtest.dto.member;

import com.sun.istack.NotNull;
import com.test.springtest.domain.Member;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter @Setter
@NoArgsConstructor
public class SearchDTO {

    @Length(max = 50)
    @ApiParam(value = "사용자 이름", required = false, allowEmptyValue = true)
    String name;

}
