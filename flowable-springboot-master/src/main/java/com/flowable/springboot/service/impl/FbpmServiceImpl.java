package com.flowable.springboot.service.impl;

import com.alibaba.fastjson.JSON;
import com.codingapi.txlcn.tc.annotation.DTXPropagation;
import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.flowable.springboot.bean.UserInfoEntity;
import com.flowable.springboot.requestBean.*;
import com.flowable.springboot.responseBean.BaseResponse;
import com.flowable.springboot.service.FbpmService;
import com.flowable.springboot.service.UserInfoService;
import com.flowable.springboot.util.HttpUtil;
import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;

@Service("fbpmService")
public class FbpmServiceImpl implements FbpmService {

    @Autowired
    private UserInfoService userInfoService;


    /**
     * 批量启动流程
     *
     * @param url          远程访问接口的路径
     * @param processStart 批量启动流程所需参数
     * @return baseResponse
     */
    @Override
    @Transactional
    @LcnTransaction(propagation = DTXPropagation.REQUIRED)
    public BaseResponse batchProcessInstance(String url, ProcessStartQuery processStart) {
        BaseResponse response = new BaseResponse();
        String json = HttpUtil.sendPost(url, JSON.toJSONString(processStart));
        //该返回参数不用解析为baseResponse 类，解析只是为了更好的返回参数
        HashMap<String, Object> parseMap = JSON.parseObject(json, HashMap.class);
        response.setCode((int) parseMap.get("code"));
        response.setMsg((String) parseMap.get("msg"));
        response.setData(parseMap.get("data"));
        if (response.getCode() == 100200) {
            UserInfoEntity user = new UserInfoEntity();
            user.setId(UUID.randomUUID().toString());
            user.setUserCode("123456");
            user.setUserName("lisi2");
            user.setPassword("123");
            userInfoService.save(user);
            if (Objects.nonNull(user)) {
                throw new IllegalStateException("by exFlag");
            }
        }
        return response;
    }

    /**
     * 批量审核流程
     *
     * @param url            远程访问接口的路径
     * @param processApprove 批量审核流程所需参数
     * @return baseResponse
     */
    @Override
    @Transactional
    @LcnTransaction(propagation = DTXPropagation.REQUIRED)
    public BaseResponse batchProcessApprove(String url, ProcessApproveQuery processApprove) {
        BaseResponse response = new BaseResponse();
        String json = HttpUtil.sendPost(url, JSON.toJSONString(processApprove));
        //该返回参数不用解析为baseResponse 类，解析只是为了更好的返回参数
        HashMap<String, Object> parseMap = JSON.parseObject(json, HashMap.class);
        response.setCode((int) parseMap.get("code"));
        response.setMsg((String) parseMap.get("msg"));
        response.setData(parseMap.get("data"));
        if (response.getCode() == 100200) {
            UserInfoEntity user = new UserInfoEntity();
            user.setId(UUID.randomUUID().toString());
            user.setUserCode("123456");
            user.setUserName("张三si");
            user.setPassword("123");
            userInfoService.save(user);
            if (Objects.nonNull(user)) {
                throw new IllegalStateException("by exFlag");
            }
        }
        return response;
    }

    /**
     * 批量驳回流程
     *
     * @param url         远程访问接口的路径
     * @param processBack 批量审核流程所需参数
     * @return baseResponse
     */
    @Override
    @Transactional
    //   @TxTransaction
    public BaseResponse batchProcessBack(String url, ProcessBackQuery processBack) {
        BaseResponse response = new BaseResponse();
        String json = HttpUtil.sendPost(url, JSON.toJSONString(processBack));
        HashMap<String, Object> parseMap = JSON.parseObject(json, HashMap.class);
        response.setCode((int) parseMap.get("code"));
        response.setMsg((String) parseMap.get("msg"));
        response.setData(parseMap.get("data"));
        return response;
    }

    /**
     * 批量撤销流程
     *
     * @param url           远程访问接口的路径
     * @param processCancel 批量撤销流程所需参数
     * @return baseResponse
     */
    @Override
    @Transactional
    // @TxTransaction
    public BaseResponse batchProcessCancel(String url, ProcessCancelQuery processCancel) {
        BaseResponse response = new BaseResponse();
        String json = HttpUtil.sendPost(url, JSON.toJSONString(processCancel));
        HashMap<String, Object> parseMap = JSON.parseObject(json, HashMap.class);
        response.setCode((int) parseMap.get("code"));
        response.setMsg((String) parseMap.get("msg"));
        response.setData(parseMap.get("data"));
        return response;
    }

    /**
     * 查询代办任务
     *
     * @param url       远程访问接口的路径
     * @param taskState 批量审核流程所需参数
     * @return baseResponse
     */
    @Override
    @Transactional
    //  @TxTransaction
    public BaseResponse selectTaskListByState(String url, TaskStateQuery taskState) {
        BaseResponse response = new BaseResponse();
        Map<String, Object> param = Maps.newHashMap();
        if (null != taskState) {
            param.put("appCode", taskState.getAppCode());
            param.put("userCode", taskState.getUserCode());
            param.put("roleId", taskState.getRoleId());
            param.put("bizTypeCode", taskState.getBizTypeCode());
            param.put("menuId", taskState.getMenuId());
            param.put("state", taskState.getState());
        }
        String json = HttpUtil.doGet(url, param);
        HashMap<String, Object> parseMap = JSON.parseObject(json, HashMap.class);
        response.setCode((int) parseMap.get("code"));
        response.setMsg((String) parseMap.get("msg"));
        response.setData(parseMap.get("data"));
        return response;
    }

    /**
     * 查询已办任务
     *
     * @param url        远程访问接口的路径
     * @param taskFinish 批量审核流程所需参数
     * @return baseResponse
     */
    @Override
    @Transactional
    public BaseResponse selectTaskFinishList(String url, TaskFinishQuery taskFinish) {
        BaseResponse response = new BaseResponse();
        Map<String, Object> param = Maps.newHashMap();
        if (null != taskFinish) {
            param.put("appCode", taskFinish.getAppCode());
            param.put("userCode", taskFinish.getUserCode());
            param.put("menuId", taskFinish.getMenuId());
            param.put("bizTypeCode", taskFinish.getBizTypeCode());
        }
        String json = HttpUtil.doGet(url, param);
        HashMap<String, Object> parseMap = JSON.parseObject(json, HashMap.class);
        response.setCode((int) parseMap.get("code"));
        response.setMsg((String) parseMap.get("msg"));
        response.setData(parseMap.get("data"));
        return response;
    }
}
