Ext.define("core.cardCenter.subsidyConfig.controller.DetailController", {
    extend: "Ext.app.ViewController",
    alias: 'controller.cardCenter.subsidyConfig.detailController',
    mixins: {
        suppleUtil: "core.util.SuppleUtil",
        messageUtil: "core.util.MessageUtil",
        formUtil: "core.util.FormUtil",
        gridActionUtil: "core.util.GridActionUtil",
        dateUtil: 'core.util.DateUtil'
    },
    init: function () {
        /*执行一些初始化的代码*/
    },
    /** 该视图内的组件事件注册 */
    control: {
    	 
    	/* 补助设置-->添加/编辑: 添加人员  */
    	"basegrid[xtype=cardCenter.subsidyConfig.userGrid] button[ref=selectUser]":{
    		beforeclick: function(btn) {
    			this.doSelectUser(btn);
    			return false;
    		} 
    	},
    	/* 补助设置->添加/编辑: 批量删除  */
    	"basegrid[xtype=cardCenter.subsidyConfig.userGrid] button[ref=gridDelete]":{
    		beforeclick: function(btn) {
    			this.doDeleteUser(btn);
    			return false;
    		} 
    	},


    },

    doSelectUser :function(btn){
        var self=this;
        var basepanel = btn.up('basepanel[xtype=cardCenter.subsidyConfig.detailLayout]');
        var payMoneyForm = basepanel.down("form[xtype=cardCenter.subsidyConfig.detailForm]");
        var fillMoneyValue = payMoneyForm.query("field[name=fillMoney]")[0].getValue();
        if(!fillMoneyValue||fillMoneyValue<0.1||fillMoneyValue>5000){
          self.Warning("[充值金额]必须介于0.1元至5000.00元之间！");
          return ;
        }
        var fillDateValue = payMoneyForm.query("field[name=fillDate]")[0].getValue();
        var baseGrid = basepanel.down('basegrid[xtype=cardCenter.subsidyConfig.userGrid]')
        var funcPanel = "cardCenter.subsidyConfig.selectUserLayout";
        var funcTitle = "人员选择";
        var otherController = 'cardCenter.subsidyConfig.otherController';
        var win = Ext.create('core.base.view.BaseFormWin', {
          operType: "add",
          title: funcTitle,
          width: 1050,
          height: 500,
          controller: otherController,
          grid: baseGrid,
          funCode: "selectUser_main",
          funcPanel:funcPanel,
          fillMoney:fillMoneyValue,//充值金额
          fillDate:fillDateValue,//到账日期
          items: [{
              xtype: funcPanel
            }]
          });
        win.show();
      },

    doDeleteUser :function(btn){
        var self=this;
        var userGrid = btn.up('basegrid[xtype=cardCenter.subsidyConfig.userGrid]');
        var rows = userGrid.getSelectionModel().getSelection();
        if (rows <= 0) {
          self.msgbox("请选择要删除的数据!");
          return;
        }
        userGrid.getStore().remove(rows); 
      },

 

});