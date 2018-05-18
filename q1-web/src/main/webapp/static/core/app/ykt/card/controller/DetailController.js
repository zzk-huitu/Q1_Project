/**
    ( *非必须，只要需要使用时，才创建他 )
    此视图控制器，提供于DeatilLayout范围内的界面组件注册事件
*/
Ext.define("core.ykt.card.controller.DetailController", {
	  extend: "Ext.app.ViewController",
	    alias: 'controller.ykt.card.detailcontroller',
	    mixins: {
	    	suppleUtil: "core.util.SuppleUtil",
	        messageUtil: "core.util.MessageUtil",
	        formUtil: "core.util.FormUtil",
	        gridActionUtil: "core.util.GridActionUtil",
	        dateUtil: 'core.util.DateUtil'
	        
	    },
	    init: function() {},
	    /** 该视图内的组件事件注册 */
	    control: {
	     /* 错扣补款->补款操作*/
        "basegrid[xtype=ykt.card.errorfillmoneygrid] button[ref=errorFillMoneyOperate]":{
              beforeclick: function(btn) {
                    this.dofillOperate(btn);
                    return false;
                }

        },
        "basegrid[xtype=ykt.card.errorfillmoneygrid] actioncolumn":{
            fillOperating_Win:function(data){
                this.dofillOperate(null,data.view,data.record);
                return false;
            },
        },

         //错扣补款->补款操作 :点击单元格，将数据选入
        "basegrid[xtype=ykt.card.consumedetailgrid]":{
            cellclick: function(table, td, cellIndex, record, tr, rowIndex, e, eOpts) {
                this.doSelectConsume(table,cellIndex,record);       
            }

        },
        /*错扣补款->补款操作 :快速搜索框 待完成*/
        "basegrid[xtype=ykt.card.consumedetailgrid] field[funCode=girdFastSearchText]":{
        	specialkey: function (field, e) {
        		if (e.getKey() == e.ENTER) {
        			this.doFastSearch(field,"consumedetail");  
              return false;              
        		}
        	}
        },
        "basegrid[xtype=ykt.card.consumedetailgrid] button[ref=gridFastSearchBtn]":{
        	beforeclick: function(btn) {
        		this.doFastSearch(btn,"consumedetail");
        		return false;
        	} 
        },

/////////////////////////////////////////////////////////////////////////
        /*充值/退款-> 用户操作 ：充值 */
        "basegrid[xtype=ykt.card.accountoperatorgrid] button[ref=userRecharge]":{
        	beforeclick: function(btn) {
        		this.doAccountOperator(btn,"recharge");
        		return false;
        	} 
        },
        /* 充值/退款-> 用户操作 ： 退款*/
        "basegrid[xtype=ykt.card.accountoperatorgrid] button[ref=userRefund]":{
        	beforeclick: function(btn) {
        		this.doAccountOperator(btn,"refund");
        		return false;
        	} 
        },
         /*  充值/退款-> 用户操作 ： 充值 退款 */
         "basegrid[xtype=ykt.card.accountoperatorgrid] actioncolumn":{
         	userRecharge_Win: function(data){
         		this.doAccountOperator(null,"recharge",data.view,data.record);
         		return false;

         	},
         	userRefund_Win: function(data){
         		this.doAccountOperator(null,"refund",data.view,data.record);
         		return false;

         	},
         },
         /*充值/退款-> 用户操作：快速搜索框*/
        "basegrid[xtype=ykt.card.accountoperatorgrid] field[funCode=girdFastSearchText]":{
         specialkey: function (field, e) {
          if (e.getKey() == e.ENTER) {
            this.doFastSearch(field,"accountoperator");   
            return false;           
          }
        }
      },
        "basegrid[xtype=ykt.card.accountoperatorgrid] button[ref=gridFastSearchBtn]":{
          beforeclick: function(btn) {
            this.doFastSearch(btn,"accountoperator");
            return false;
          } 
        },

	},

	 dofillOperate :function(btn,grid,recode){
        var self=this;
        //得到组件
       
        var recodeDate;
        var baseGrid = grid;
        if(!baseGrid){
            baseGrid =  btn.up("basegrid");
            var recodes = baseGrid.getSelectionModel().getSelection();
            if(recodes.length!=1){
                self.msgbox("请选择一条数据！");
                return;

            }
         recodeDate = recodes[0].getData();

        }else{
          recodeDate = recode.getData();
        }
       
        var iconCls= 'x-fa fa-plus-circle';
        var width = 800;
        var height = 600;

        var win = Ext.create('core.base.view.BaseFormWin', {
            iconCls:iconCls,
            title: "补款操作",
            operType: "add",
            width: width,
            height: height,
            tittle:"补款操作",
            controller: "ykt.card.othercontroller",
            grid: baseGrid,
            funCode: "filloperate_detail",
            recodeDate:recodeDate,//用户id
            items: [{
                xtype: "ykt.card.filloperatelayout"
            }]
        });
        win.show();
        var detPanel = win.down("basepanel[funCode='"+win.funCode+"']");
        var baseGrid = detPanel.down("basegrid[xtype=ykt.card.consumedetailgrid]");
        var store = baseGrid.getStore();
        var proxy = store.getProxy();
        proxy.extraParams.userId = recodeDate.userId;
        store.load();

      },
 
      doSelectConsume:function(table,cellIndex,record){
          var self=this;
          var detailLayout = table.up("basepanel[xtype=ykt.card.filloperatelayout]");
          var baseform = detailLayout.down("baseform[xtype=ykt.card.consumedetailform]");
          var formObject = baseform.getForm();
          record.data.consumeDate = Ext.Date.format(new Date(record.data.consumeDate*1), 'Y/m/d');
          self.setFormValue(formObject, record.data);
        },


    doAccountOperator :function(btn,cmd,grid,record){
      var self=this;
      var baseGrid = grid;
      var recordData;
      if(!baseGrid){
        baseGrid = btn.up('basegrid');
        var selectRecords = baseGrid.getSelectionModel().getSelection();
        if(selectRecords.length!=1){
           // self.msgbox("请选择一个用户！");
           // return;

         }
        //recordData = selectRecords[0].getData();
      }else{
       recordData = record.getData();

     }
      var sign = 0; //0b表示充值
      var funcPanel = "ykt.card.accountoperatorform";
      var funcTitle = "充值";// 某某充值/退款
      var otherController = 'ykt.card.othercontroller';
      if(cmd=="refund"){
	      	funcTitle = "退款";
	        sign = 1; // 1:退款
	    }
	    var win = Ext.create('core.base.view.BaseFormWin', {
	    	operType: "add",
	    	title: funcTitle,
	    	width: 600,
	    	height: 300,
	    	controller: otherController,
	    	grid: baseGrid,
	    	recordData:recordData,
	    	funCode: "accountOperator_main",
	    	funcPanel:funcPanel,
	    	cmd:cmd,
	    	sign:sign,
	    	items: [{
	    		xtype: funcPanel
	    	}]
	    });
	    win.show();
	    var baseForm = win.down("baseform[xtype='"+win.funcPanel+"']");
	    var formDeptObj = baseForm.getForm();
	    self.setFormValue(formDeptObj, recordData);
	}, 

	doFastSearch:function(com,cmd){
		var baseGrid = com.up("basegrid");
		var toolBar = com.up("toolbar");

		var girdSearchTexts = toolBar.query("field[funCode=girdFastSearchText]");
		var filter = new Array();
		var value = girdSearchTexts[0].getValue();
		var store = baseGrid.getStore();
		var proxy = store.getProxy();
		if(cmd=="accountoperator"){
          // filter.push({"type": "string", "value": girdSearchTexts[0].getValue(), "field": "bagCode", "comparison": ""});
          if(value){
          	proxy.extraParams.bagCode = value;
          	store.loadPage(1);
          }

      }else if(cmd=="consumedetail"){//消费机类型 未加 
        	if(value){
        		proxy.extraParams.consumeDate = value;
        		store.loadPage(1);
        	}
        }

    },

});