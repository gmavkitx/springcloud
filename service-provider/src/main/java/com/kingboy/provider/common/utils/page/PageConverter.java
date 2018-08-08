package com.kingboy.provider.common.utils.page;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.kingboy.common.utils.mapper.MapperUtils;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * MybatisPlus分页工具转换.
 * <p></p>
 *
 * @author KingBoy - KingBoyWorld@163.com
 * @since 2018-08-07 02:02
 */
@Slf4j
public class PageConverter {

    /**
     * 将page中的records类型进行转换.
     * <p></p>
     * @param page 分页对象
     * @param clazz 需要转换的类型
     * @return void
     * @author KingBoy - KingBoyWorld@163.com
     * @since 2018/8/7 03:14
     */
    public static void convert(IPage page, Class clazz) {
        if (page.getRecords() == null) {
            log.warn("分页结果数据为空");
            return;
        }
        List convertData = MapperUtils.mapperList(page.getRecords(), clazz);
        page.setRecords(convertData);
    }

    /**
     * 将page中的records类型进行转换，将不同的字段名进行对应转换.
     * <p></p>
     * @param page 分页对象
     * @param clazz 需要转换的类型
     * @param diffFieldMap 不同字段对应关系
     * @return void
     * @author KingBoy - KingBoyWorld@163.com
     * @since 2018/8/7 03:16
     */
    public static void convert(IPage page, Class clazz, Map<String, String> diffFieldMap) {
        if (page.getRecords() == null) {
            log.warn("分页结果数据为空");
            return;
        }
        List convertData = MapperUtils.mapperList(page.getRecords(), clazz, diffFieldMap);
        page.setRecords(convertData);
    }

}
