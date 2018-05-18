
Ext.define("core.cardCenter.subsidyConfig.IsSelectedUserStore",{
    extend:"Ext.data.Store",

    alias: 'store.cardCenter.subsidyConfig.issSelectedUserStore',

    model: factory.ModelFactory.getModelByName("com.yc.q1.model.base.pt.system.PtUser", "checked").modelName,
});