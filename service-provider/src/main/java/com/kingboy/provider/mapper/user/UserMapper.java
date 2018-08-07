package com.kingboy.provider.mapper.user;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kingboy.provider.domain.po.user.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * User存储层.
 * <p></p>
 *
 * @author KingBoy - KingBoyWorld@163.com
 * @since 2018-08-07 02:02
 */
public interface UserMapper extends BaseMapper<User> {

    List<User> listWithXML(IPage page);

    @Select("SELECT id,name,age,balance,birth,gender FROM `user`")
    List<User> listWithAnnotation(IPage page);

}
