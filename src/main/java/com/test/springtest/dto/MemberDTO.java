package com.test.springtest.dto;

import com.test.springtest.domain.Member;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class MemberDTO {

    @ApiParam(value = "사용자 ID", required = false)
    String id;

    @ApiParam(value = "사용자 이름", required = false)
    String name;

    @ApiParam(value = "사용자 패스워드", required = false)
    String password;

    public Member toEntity(){
        return Member.builder()
                .id(id)
                .name(name)
                .password(password)
                .build();
    }
}
