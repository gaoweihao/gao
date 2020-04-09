package com.flowable.springboot.service.impl;

import com.alibaba.fastjson.JSON;
import com.flowable.springboot.requestBean.*;
import com.flowable.springboot.responseBean.BaseResponse;
import com.flowable.springboot.service.FbpmService;
import com.flowable.springboot.util.HttpUtil;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service("fbpmService")
public class FbpmServiceImpl implements FbpmService {

    /**
     * 批量启动流程
     *
     * @param url              远程访问接口的路径
     * @param processStart 批量启动流程所需参数
     * @return baseResponse
     */
    @Override
    public BaseResponse batchProcessInstance(String url, ProcessStartQuery processStart) {

        String msg = HttpUtil.sendPost(url, JSON.toJSONString(processStart));
        BaseResponse response = (BaseResponse) JSON.toJSON(msg);
//        int code = response.getCode();
//        if (code == 100200) {
//            List<FbpmResponseEntity> fbpmResponseList = (List<FbpmResponseEntity>) response.getData();
//        }
        return response;
    }

    /**
     * 批量审核流程
     *
     * @param url              远程访问接口的路径
     * @param processApprove 批量审核流程所需参数
     * @return baseResponse
     */
    @Override
    public BaseResponse batchProcessApprove(String url, ProcessApproveQuery processApprove) {
        String msg = HttpUtil.sendPost(url, JSON.toJSONString(processApprove));
        BaseResponse response = (BaseResponse) JSON.toJSON(msg);
//        int code = response.getCode();
//        if (code == 100200) {
//            List<FbpmResponseEntity> fbpmResponseList = (List<FbpmResponseEntity>) response.getData();
//        }
        return response;
    }

    /**
     * 批量驳回流程
     *
     * @param url              远程访问接口的路径
     * @param processBack 批量审核流程所需参数
     * @return baseResponse
     */
    @Override
    public BaseResponse batchProcessBack(String url, ProcessBackQuery processBack) {
        String msg = HttpUtil.sendPost(url, JSON.toJSONString(processBack));
        BaseResponse response = (BaseResponse) JSON.toJSON(msg);
//        int code = response.getCode();
//        if (code == 100200) {
//            List<FbpmResponseEntity> fbpmResponseList = (List<FbpmResponseEntity>) response.getData();
//        }
        return response;
    }

    /**
     * 批量撤销流程
     *
     * @param url              远程访问接口的路径
     * @param processCancel 批量撤销流程所需参数
     * @return baseResponse
     */
    @Override
    public BaseResponse batchProcessCancel(String url, ProcessCancelQuery processCancel) {
        String msg = HttpUtil.sendPost(url, JSON.toJSONString(processCancel));
        BaseResponse response = (BaseResponse) JSON.toJSON(msg);
//        int code = response.getCode();
//        if (code == 100200) {
//            List<FbpmResponseEntity> fbpmResponseList = (List<FbpmResponseEntity>) response.getData();
//        }
        return response;
    }

    /**
     * 查询代办任务
     *
     * @param url              远程访问接口的路径
     * @param taskState 批量审核流程所需参数
     * @return baseResponse
     */
    @Override
    public BaseResponse selectTaskListByState(String url, TaskStateQuery taskState) {
        Map<String,Object> param = Maps.newHashMap();
        if(null != taskState){
            param.put("appCode",taskState.getAppCode());
            param.put("userCode",taskState.getUserCode());
            param.put("roleId",taskState.getRoleId());
            param.put("bizTypeCode",taskState.getBizTypeCode());
            param.put("menuId",taskState.getMenuId());
            param.put("state",taskState.getState());
        }
        String msg = HttpUtil.doGet(url, param);
        BaseResponse response = (BaseResponse) JSON.toJSON(msg);
//        int code = response.getCode();
//        if (code == 100200) {
//            List<FbpmResponseEntity> fbpmResponseList = (List<FbpmResponseEntity>) response.getData();
//        }
        return response;
    }
}
