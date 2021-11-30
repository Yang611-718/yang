package com.yang.service.serviceimpl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.yang.mapper.MassageMapper;
import com.yang.pojo.Massage;
import com.yang.service.IMassageService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MassageServiceImpl extends ServiceImpl<MassageMapper, Massage> implements IMassageService {


}
