package com.zd.school.plartform.baseset.service;

import java.util.List;

import com.zd.core.service.BaseService;
import com.zd.school.build.define.model.RoomArea;
import com.zd.school.build.define.model.RoomAreaTree;

/**
 * 
 * ClassName: BuildRoomareaService Function: TODO ADD FUNCTION. Reason: TODO ADD
 * REASON(可选). Description: 教室区域实体Service接口类. date: 2016-08-23
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */

public interface BaseRoomareaService extends BaseService<RoomArea> {

    public List<RoomAreaTree> getBuildAreaList(String whereSql);

    public Integer getChildCount(String areaId);
}