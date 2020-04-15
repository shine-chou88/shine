package com.gwideal.activiti.manager.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gwideal.activiti.entity.ActGeByteArray;
import com.gwideal.activiti.manager.ActGeByteArrayMng;
import com.gwideal.common.hibernate.BaseManagerImpl;

@Service
@Transactional
public class ActGeByteArrayMngImpl extends BaseManagerImpl<ActGeByteArray> implements ActGeByteArrayMng{

}
