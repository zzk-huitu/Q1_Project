package com.yc.q1.controller.card;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yc.q1.controller.base.FrameWorkController;
import com.yc.q1.core.constant.Constant;
import com.yc.q1.model.base.pt.card.PtSubsidyFillMoneyItem;
import com.yc.q1.service.base.redis.PrimaryKeyRedisService;
import com.yc.q1.service.storage.pt.PtSubsidyFillMoneyItemService;
/**
 * 在线补助明细表
 * 
 * @author Administrator
 *
 */
@Controller
@RequestMapping("/PtSubsidyFillMoneyItem")
public class PtSubsidyFillMoneyItemController  extends FrameWorkController<PtSubsidyFillMoneyItem> implements Constant{
	@Resource
	PtSubsidyFillMoneyItemService thisService; // service层接口

	@Resource
	private PrimaryKeyRedisService keyRedisService;
}
