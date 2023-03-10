package com.fishlog.kalalogi_back.fishlog.register;

import com.fishlog.kalalogi_back.domain.user.User;
import com.fishlog.kalalogi_back.domain.user.UserMapper;
import com.fishlog.kalalogi_back.domain.user.UserService;
import com.fishlog.kalalogi_back.domain.user.role.Role;
import com.fishlog.kalalogi_back.domain.user.role.RoleService;
import com.fishlog.kalalogi_back.validation.Validator;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    @Resource
    private UserMapper userMapper;

    @Resource
    private RoleService roleService;

    @Resource
    private UserService userService;

    public void addUser(RegisterDto registerDto) {
        userService.userExists(registerDto.getUsername());

        User user = userMapper.registerEntity(registerDto);
        user.setRole(roleService.getByRoleId(2));

        userService.saveUser(user);
    }
}
