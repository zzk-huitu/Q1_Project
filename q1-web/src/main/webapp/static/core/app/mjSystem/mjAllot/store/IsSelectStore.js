Ext.define("core.mjSystem.mjAllot.store.IsSelectStore",{
    extend:"Ext.data.Store",
    alias: 'store.mjSystem.mjAllot.isSelectStore',
    model: factory.ModelFactory.getModelByName("com.yc.q1.model.base.pt.device.PtTerm", "checked").modelName,
  
});