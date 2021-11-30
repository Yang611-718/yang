package com.yang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.yang.pojo.Massage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MassageMapper extends BaseMapper<Massage> {

    public List<Massage> getList(String email,Integer firstNum,Integer num);

    public Integer getTotal(String email);
}
