package com.fanfan.mapper;

import com.fanfan.dto.UserInfoDTO;
import com.fanfan.pojo.User;

public interface UserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Long userId);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    UserInfoDTO loadUserByUsername(String username);

    String queryPasswordById(Integer userId);
}