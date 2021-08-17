package com.test.springtest.dto.member;

import com.sun.istack.NotNull;
import com.test.springtest.domain.Member;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class MemberDTO {

    @NotNull
    @ApiParam(value = "사용자 ID", required = true)
    String id;

    @NotNull
    @ApiParam(value = "사용자 이름", required = true)
    String name;

    @NotNull
    @ApiParam(value = "사용자 패스워드", required = true)
    String password;

    public Member toEntity(){
        return Member.builder()
                .id(id)
                .name(name)
                .password(password)
                .build();
    }
}
