package com.kimzing.provider.service.user.impl;

import com.kimzing.utils.page.Page;
import com.kimzing.provider.domain.po.user.User;
import com.kimzing.provider.mapper.user.UserMapper;
import com.kimzing.provider.service.user.UserService;
import org.springframework.stereotype.Service;

import com.kimzing.utils.mapper.MapperUtils;
import com.kimzing.provider.domain.dto.user.UserDTO;
import com.kimzing.provider.domain.vo.user.UserVO;
import com.kimzing.provider.common.utils.exception.ExceptionManager;
import javax.annotation.Resource;
import java.util.List;

/**
 * User接口实现.
 *
 * @author KimZing - kimzing@163.com
 * @since 2018-08-07 02:02
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    ExceptionManager exceptionManager;

    @Resource
    UserMapper userMapper;

    /**
     * 保存User.
     */
    @Override
    public UserVO save(UserDTO userDTO) {
        User user = MapperUtils.mapperBean(userDTO, User.class);
        userMapper.save(user);
        return MapperUtils.mapperBean(user, UserVO.class);
    }

    /**
     * 更新User, 更新不为Null的字段.
     */
    @Override
    public UserVO update(UserDTO userDTO) {
        if (userDTO.getId() == null) {
            throw exceptionManager.create("您所更新的资源ID不存在!");
        }
        User user = MapperUtils.mapperBean(userDTO, User.class);
        userMapper.updateById(user);
        return MapperUtils.mapperBean(user, UserVO.class);
    }

    /**
     * 删除User.
     * <p>
     *     根据项目配置进行硬删除或者软删除
     * </p>
     */
    @Override
    public void remove(Long id) {
        userMapper.removeById(id);
    }

    /**
     * 通过ID查询User.
     */
    @Override
    public UserVO get(Long id) {
        User user = userMapper.getById(id);
        if (user == null) {
            throw exceptionManager.create("您所查询的资源不存在!");
        }
        UserVO userVO = MapperUtils.mapperBean(user, UserVO.class);
        return userVO;
    }

    /**
    * 根据分页和条件进行查询.
    * <p>
    *     分页查询
    * </p>
    */
    @Override
    public Page list(Page page) {
        List<User> result = userMapper.list(page);
        page.setData(result);
        Long count = userMapper.count();
        page.setTotal(count);
        page.convert(UserVO.class);
        return page;
    }

}
