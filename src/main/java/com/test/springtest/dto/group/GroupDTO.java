package com.test.springtest.dto.group;

import com.test.springtest.domain.Group;
import io.swagger.annotations.ApiParam;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
public class GroupDTO {

    @ApiParam(value = "그룹 ID", required = false)
    String id;

    @ApiParam(value = "그룹 이름", required = false)
    String name;

    public Group toEntity(){
        return Group.builder()
                .id(id)
                .name(name)
                .build();
    }
}
