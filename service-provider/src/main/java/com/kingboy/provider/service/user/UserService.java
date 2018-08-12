package com.kingboy.provider.service.user;

import com.kingboy.common.utils.page.Page;

import com.kingboy.provider.domain.dto.user.UserDTO;
import com.kingboy.provider.domain.vo.user.UserVO;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * User接口.
 *
 * @author KingBoy - KingBoyWorld@163.com
 * @since 2018-08-07 02:02
 */
@Validated
public interface UserService {

    /**
     * 保存User.
     *
     * @param userDTO
     * @return UserVO
     */
    UserVO save(@Valid UserDTO userDTO);

    /**
     * 更新User, 更新不为Null的字段.
     *
     * @param userDTO
     * @return UserVO
     */
    UserVO update(@Valid UserDTO userDTO);

    /**
     * 删除User.
     * <p>
     *     根据项目配置进行硬删除或者软删除
     * </p>
     * @param id
     */
    void remove(@NotNull(message = "PROV_0002") Long id);

    /**
     * 通过ID查询User.
     *
     * @param id
     * @return UserVO
     */
    UserVO get(@NotNull(message = "PROV_0002") Long id);

    /**
     * 根据分页和条件进行查询.
     * <p>
     *     分页查询
     * </p>
     * @param page
     * @return Page
     */
    Page list(Page page);

}
