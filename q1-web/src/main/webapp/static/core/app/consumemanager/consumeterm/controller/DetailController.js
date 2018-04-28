Ext.define("core.consumemanager.consumeterm.controller.DetailController", {
    extend: "Ext.app.ViewController",
    alias: 'controller.consumemanager.consumeterm.detailcontroller',
    mixins: {},
    init: function () {
        /*执行一些初始化的代码*/
    },
    /** 该视图内的组件事件注册 */
    control: {
    	"basepanel[xtype=consumemanager.consumeterm.batchaddlayout]  button[ref=btnBack]":{
    		beforeclick: function(btn) {
    			this.doNavigate(btn,'prev');            
    			return false;
    		}
    	},
    	"basepanel[xtype=consumemanager.consumeterm.batchaddlayout]  button[ref=btnNext]":{
    		beforeclick: function(btn) {
    			this.doNavigate(btn,'next');            
    			return false;
    		}
    	},

    },
    doNavigate:function(btn, direction){     
    	var panel = btn.up("basepanel[xtype=consumemanager.consumeterm.batchaddlayout]");
        var layout = panel.getLayout();    
        layout[direction]();     
        Ext.getCmp('move-prev').setDisabled(!layout.getPrev());     
        Ext.getCmp('move-next').setDisabled(!layout.getNext());
    },
});