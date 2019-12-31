package com.fanfan.service;

import com.fanfan.dto.UserInfoDTO;
import com.fanfan.mapper.UserMapper;
import com.fanfan.pojo.User;
import com.fanfan.pojo.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserInfoService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        if(username != null && username.length() > 0) {
            UserInfoDTO userInfoDto = userMapper.loadUserByUsername(username);
            if(userInfoDto == null) {
                return null;
            }
            User user = userMapper.selectByPrimaryKey((long)userInfoDto.getUserId());
            if(user != null) {
                UserInfo userInfo = new UserInfo(user.getUsername(),
                        user.getPassword(),userInfoDto.getRoleCode(),
                        true,true,
                        true, true);
                return userInfo;
            }
        }
        return null;
    }
}
