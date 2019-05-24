package com.gencode.generate.service;

import com.gencode.generate.persistence.model.Test;
import com.gencode.generate.persistence.mapper.TestMapper;
import com.codegen.vo.Page;
import com.gencode.generate.service.TestService;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;

import org.apache.ibatis.annotations.Param;

/**
 *
 * Created by wangrenzhong on 2019/05/24.
 */
@Service
public class TestServiceImpl implements TestService{

	@Autowired
    private TestMapper testMapper;
    
    public int deleteByPrimaryKey(Integer id){
        return testMapper.deleteByPrimaryKey(id);
    }

    public int insert(Test record){
        return testMapper.insert(record);
    }

    public int insertSelective(Test record){
        return testMapper.insertSelective(record);
    }

    public Test selectByPrimaryKey(Integer id){
        return testMapper.selectByPrimaryKey(id);
    }

    public int updateByPrimaryKeySelective(Test record){
        return testMapper.updateByPrimaryKeySelective(record);
    }

    public int updateByPrimaryKey(Test record){
        return testMapper.updateByPrimaryKey(record);
    }

    public List<Test> selectByCondition(Test record){
        return testMapper.selectByCondition(record);
    }

    public List<Test> selectAll(){
        return testMapper.selectAll();
    }

    public List<Test> selectByPage(Page<Test> page){
        return testMapper.selectByPage(page);
    }

    public Integer count(Test record){
        return testMapper.count(record);
    }

    public int deleteByCondition(Test record){
        return testMapper.deleteByCondition(record);
    }

}
