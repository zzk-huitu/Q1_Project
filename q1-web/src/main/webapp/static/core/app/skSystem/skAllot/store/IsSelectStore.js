Ext.define("core.skSystem.skAllot.store.IsSelectStore",{
    extend:"Ext.data.Store",
    alias: 'store.skSystem.skAllot.isSelectStore',
    model: factory.ModelFactory.getModelByName("com.yc.q1.model.base.pt.device.PtTerm", "checked").modelName,
  
});