package com.webapp.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.webapp.common.core.domain.entity.SysUser;
import org.apache.ibatis.annotations.Mapper;


/**
 * 用户表 数据层
 * 
 * @author ruoyi
 */
@Mapper
public interface SysUserMapper extends BaseMapper<SysUser>
{

}
