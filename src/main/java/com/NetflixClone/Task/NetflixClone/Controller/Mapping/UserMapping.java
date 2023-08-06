package com.NetflixClone.Task.NetflixClone.Controller.Mapping;

import com.NetflixClone.Task.NetflixClone.Controller.DTOs.UserDTO;
import com.NetflixClone.Task.NetflixClone.Model.User;
import org.mapstruct.Mapper;

@Mapper
public interface UserMapping {
    User oUser(UserDTO userDTO);
    UserDTO toUserDTO(User user);
}
