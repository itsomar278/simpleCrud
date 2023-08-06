package com.NetflixClone.Task.NetflixClone.Service;

import com.NetflixClone.Task.NetflixClone.Model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper
public interface ServiceUserMapping {
    @Mapping(target = "id", ignore = true)

    User userToUser(User newUser, @MappingTarget User oldUser);
}
