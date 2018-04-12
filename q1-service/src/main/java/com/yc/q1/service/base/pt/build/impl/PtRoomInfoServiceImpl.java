package com.yc.q1.service.base.pt.build.impl;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yc.q1.core.dao.BaseDao;
import com.yc.q1.core.service.BaseServiceImpl;
import com.yc.q1.core.util.StringUtils;
import com.yc.q1.model.base.pt.build.PtDormDefine;
import com.yc.q1.model.base.pt.build.PtRoomInfo;
import com.yc.q1.model.base.pt.system.PtUser;
import com.yc.q1.service.base.pt.build.PtClassRoomDefineService;
import com.yc.q1.service.base.pt.build.PtDormDefineService;
import com.yc.q1.service.base.pt.build.PtFuncRoomDefineService;
import com.yc.q1.service.base.pt.build.PtOfficeDefineService;
import com.yc.q1.service.base.pt.build.PtRoomInfoService;
import com.yc.q1.service.base.redis.PrimaryKeyRedisService;

/**
 * 
 * ClassName: RoomInfoServiceImpl Function: TODO ADD FUNCTION. Reason: TODO
 * ADD REASON(可选). Description: 教室信息实体Service接口实现类. date: 2016-08-23
 *
 * @author luoyibo 创建文件
 * @version 0.1
 * @since JDK 1.8
 */
@Service
@Transactional
public class PtRoomInfoServiceImpl extends BaseServiceImpl<PtRoomInfo> implements PtRoomInfoService {
	private static Logger logger = Logger.getLogger(PtRoomInfoServiceImpl.class);

	@Resource(name = "ptRoomInfoDao") // 将具体的dao注入进来
	public void setDao(BaseDao<PtRoomInfo> dao) {
		super.setDao(dao);
	}
    
	@Resource
	private PtClassRoomDefineService classRoomService;// 教室service层接口
	
	@Resource
	private PtDormDefineService dormRoomService; // 宿舍service层接口
	
	@Resource
	private PtOfficeDefineService offRoomService; // 办公室service层接口
	
	@Resource
	private PtFuncRoomDefineService funRoomService; // 功能室service层接口
	
	@Resource
	private PrimaryKeyRedisService keyRedisService;

	/**
     * 批量添加房间信息
     * @param roominfo 房间信息实体
     * @param currentUser 当前操作用户
     * @return
     * 
     */
    public Boolean doBatchAddRoom(PtRoomInfo roominfo, PtUser currentUser) {
        String benginChar = roominfo.getRoomCode();
        Integer roomCount = roominfo.getRoomCount();
        String areaId = roominfo.getAreaId();
        String roomType = "0";	//默认为 未定义房间
        String createUser = currentUser.getId();
        PtRoomInfo saveRoom = null;
        String roomName = "";   
        int orderIndex=1;
        for (int i = 1; i <= roomCount; i++) {
        	if(i==0)
            	orderIndex = this.getDefaultOrderIndex(saveRoom);
        	
            roomName = benginChar + StringUtils.addString(String.valueOf(i), "0", 2, "L");
            saveRoom = new PtRoomInfo();
            saveRoom.setRoomName(roomName);
            saveRoom.setRoomCode(roomName);                      
            saveRoom.setOrderIndex(orderIndex++);
            //saveRoom.setExtField01(roomName);
            saveRoom.setHouseNo01(roomName);
            saveRoom.setAreaId(areaId);
            saveRoom.setRoomType(roomType);
            saveRoom.setCreateUser(createUser);
            
            saveRoom.setId(keyRedisService.getId(PtRoomInfo.ModuleType));	//手动设置id
            this.merge(saveRoom);
        }
        return true;       
    }

	@Override
	public Integer getCount(String roomName) {
		Integer conut=0;
		String hql=" select count(*) from RoomInfo where 1=1 ";
		if(roomName!=null){
			hql+=" and roomName = '"+roomName+"'";	
		}
		conut=this.getQueryCountByHql(hql);
		return conut;
	}

	@Override
	public Boolean doDeleteRoomDefine(String delIds, String xm,Map<String, Object> hashMap) {
		Boolean flag=true;
		String[] ids = delIds.split(",");
		String roomType = "";// 房间类型 1.宿舍，2.办公室，3.教室，5、功能室，0、未分配
		PtRoomInfo roomInfo = null;
		
		List<String> roomList=new ArrayList<>();
		for (int j = 0; j < ids.length; j++) {
			roomInfo = this.get(ids[j]); // 获取RoomInfo对象
			roomType = roomInfo.getRoomType();
			if (roomType.equals("1")) {
				flag = dormRoomService.delDormRoom(roomInfo, ids[j], xm);
				if (!flag){
					roomList.add(roomInfo.getRoomName());				
				}

			} else if (roomType.equals("2")) {
				flag = offRoomService.delOffRoom(roomInfo, ids[j], xm);
				if (!flag){
					roomList.add(roomInfo.getRoomName());				
				}			

			} else if (roomType.equals("3")) {
				flag = classRoomService.delClassRoom(roomInfo, ids[j], xm);
				if (!flag){
					roomList.add(roomInfo.getRoomName());				
				}		
				
			} else if (roomType.equals("5")) {
				flag = funRoomService.delFunRoom(roomInfo, ids[j], xm);
				if (!flag){
					roomList.add(roomInfo.getRoomName());			
				}
			}
		}
		if(roomList.size()>0){		
			hashMap.put("roomNames", roomList.stream().collect(Collectors.joining(",")).toString());
			return false;
		}
		
		return true;
	}

	@Override
	public Boolean doAddRoomDefine(PtRoomInfo entity, HttpServletRequest request, String userCh) throws IllegalAccessException, InvocationTargetException {
		boolean flag=false;
		String roomType = "";// 房间类型 1.宿舍，2.办公室，3.教室，5、功能室，0、未分配
		String id = ""; // RoomInfo的主键
		
		roomType = entity.getRoomType();
		id = entity.getId();// RoomInfo的uuid
		
		if (id != null) {
			if (roomType.equals("1")) {// 宿舍
				boolean cz = dormRoomService.IsFieldExist("roomId", id, "-1", "isdelete=0");// 判断该房间是否存在
				if (!cz) {
					String dormType = request.getParameter("dormType");// 宿舍类型
					String dormTypeLb = request.getParameter("dormTypeLb");// 宿舍类别

					String dormBedCount = request.getParameter("dormBedCount");// 床位数
					String dormChestCount = request.getParameter("dormChestCount");// 柜子数
					String dormPhone = request.getParameter("dormPhone");// 电话
					String dormFax = request.getParameter("dormFax");// 传真

					PtDormDefine dormRoom = new PtDormDefine();
					dormRoom.setDormType(dormType);
					dormRoom.setDormCategory(dormTypeLb);
					dormRoom.setBedCount(Integer.valueOf(dormBedCount));
					dormRoom.setSarkCount(Integer.valueOf(dormChestCount));
					dormRoom.setDormPhone(dormPhone);
					dormRoom.setDormFax(dormFax);

					dormRoomService.addDormRoom(entity, dormRoom, id, userCh);		
					flag=true;
				}

			} else if (roomType.equals("2")) {// 办公室
				boolean cz = offRoomService.IsFieldExist("roomId", id, "-1", "isdelete=0");
				if (!cz) {
					offRoomService.addOffRoom(entity, id, userCh);
					flag=true;
				}
					
			} else if (roomType.equals("3")) {// 教室
				boolean cz = classRoomService.IsFieldExist("roomId", id, "-1", "isdelete=0");
				if (!cz){
					classRoomService.addClassRoom(entity, id, userCh);				
					flag=true;
				}
				
			} else if (roomType.equals("5")) {// 功能室
				boolean cz = funRoomService.IsFieldExist("roomId", id, "-1", "isdelete=0");
				if (!cz) {
					funRoomService.addFunRoom(entity, id, userCh);			
					flag=true;
				}
			}
		}
		
		return flag;
	}
    
}