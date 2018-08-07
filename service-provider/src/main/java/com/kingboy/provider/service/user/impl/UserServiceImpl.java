package com.kingboy.provider.service.user.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.kingboy.provider.domain.po.user.User;
import com.kingboy.provider.mapper.user.UserMapper;
import com.kingboy.provider.service.user.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import com.kingboy.common.utils.mapper.MapperUtils;
import com.kingboy.provider.domain.dto.user.UserDTO;
import com.kingboy.provider.domain.vo.user.UserVO;
import com.kingboy.provider.common.exception.ExceptionManager;
import com.kingboy.provider.common.utils.page.PageConverter;
import com.baomidou.mybatisplus.core.metadata.IPage;
import javax.annotation.Resource;

/**
 * User接口实现.
 * <p></p>
 *
 * @author KingBoy - KingBoyWorld@163.com
 * @since 2018-08-07 02:02
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Resource
    ExceptionManager exceptionManager;

    @Resource
    UserMapper userMapper;

    /**
     * 保存User.
     * <p></p>
     * @author KingBoy
     * @since 2018-08-07 18:35:44
     *
     */
    @Override
    public UserVO save(UserDTO userDTO) {
        User user = MapperUtils.mapperBean(userDTO, User.class);
        boolean success = this.save(user);
        if (!success) throw exceptionManager.create("资源创建失败!");

        return MapperUtils.mapperBean(user, UserVO.class);
    }

    /**
     * 更新User, 更新不为Null的字段.
     * <p></p>
     * @author KingBoy
     * @since 2018-08-07 18:36:34
     *
     */
    @Override
    public UserVO update(UserDTO userDTO) {
        if (userDTO.getId() == null) throw exceptionManager.create("您所更新的资源ID不存在!");

        User user = MapperUtils.mapperBean(userDTO, User.class);
        boolean success = this.updateById(user);
        if (!success) throw exceptionManager.create("资源更新失败!");

        return MapperUtils.mapperBean(user, UserVO.class);
    }

    /**
     * 删除User.
     * <p>
     *     根据项目配置进行硬删除或者软删除
     * </p>
     * @author KingBoy
     * @since 2018-08-07 18:37:30
     *
     */
    @Override
    public void remove(Long id) {
        boolean success = this.removeById(id);
        if (!success) throw exceptionManager.create("资源删除失败!");
    }

    /**
     * 通过ID查询User.
     * <p></p>
     * @author KingBoy
     * @since 2018-08-07 18:40:06
     *
     */
    public UserVO get(Long id) {
        User user = this.getById(id);
        if (user == null) throw exceptionManager.create("您所查询的资源不存在!");

        UserVO userVO = MapperUtils.mapperBean(user, UserVO.class);
        return userVO;
    }

    /**
    * 根据分页和条件进行查询.
    * <p>
    *     条件为空时，查询全部
    * </p>
    * @author KingBoy
    * @since 2018-08-07 18:40:50
    *
    */
    @Override
    public IPage<UserVO> list(IPage page, UserDTO userDTO) {
        User user = MapperUtils.mapperBean(userDTO, User.class);
        IPage result = userMapper.selectPage(page, new QueryWrapper<User>().setEntity(user));
        PageConverter.convert(result, UserVO.class);
        return result;
    }

    @Override
    public IPage<UserVO> listWithXML(IPage page) {
        page.setRecords(userMapper.listWithXML(page));
        PageConverter.convert(page, UserVO.class);
        return page;
    }

    @Override
    public IPage<UserVO> listWithAnnotation(IPage page) {
        page.setRecords(userMapper.listWithAnnotation(page));
        PageConverter.convert(page, UserVO.class);
        return page;
    }

    @Override
    public IPage<UserVO> listByConditionWithWrapper(IPage page, UserDTO userDTO) {
        IPage result = this.page(page, new QueryWrapper<User>()
                .eq("gender", userDTO.getGender())
                .likeRight("name", userDTO.getName())
                .lt("age", userDTO.getAge())
                .orderByDesc("age")
        );

        PageConverter.convert(result, UserVO.class);
        return result;
    }


}
