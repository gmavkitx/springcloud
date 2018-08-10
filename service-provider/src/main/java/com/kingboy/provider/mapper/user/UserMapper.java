package com.kingboy.provider.mapper.user;

import com.kingboy.common.utils.page.Page;
import com.kingboy.provider.domain.po.user.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * User存储层.
 * <p></p>
 *
 * @author KingBoy - KingBoyWorld@163.com
 * @since 2018-08-07 02:02
 */
public interface UserMapper {

    @Insert("INSERT INTO `user`(name, age, balance, birth, gender) VALUES(#{name}, #{age}, #{balance}, #{birth}, #{gender})")
    void save(User user);

    @Update("UPDATE `user` SET name = #{name}, age = #{age}, balance = ${balance},birth = #{birth}, gender = #{gender} WHERE id = #{id}")
    void updateById(User user);

    @Delete("DELETE FROM `user` WHERE id = #{id}")
    void removeById(@Param("id") Long id);

    @Select("SELECT id, name, age, balance, birth, gender FROM `user` WHERE id = #{id}")
    User getById(@Param("id") Long id);

    @Select("SELECT id, name, age, balance, birth, gender FROM `user` LIMIT #{size} OFFSET #{start}")
    List<User> list(Page page);

    @Select("SELECT count(*) FROM `user`")
    Long count();
}
