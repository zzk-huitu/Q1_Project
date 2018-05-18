Ext.define("core.skSystem.skPrice.store.IsSelectStore",{
    extend:"Ext.data.Store",

    alias: 'store.skSystem.skPrice.isSelectStore',

 
    model: factory.ModelFactory.getModelByName("com.yc.q1.model.base.pt.device.PtTerm", "checked").modelName,
});