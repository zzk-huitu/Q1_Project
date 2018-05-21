Ext.define("core.xfSystem.xfAllot.store.IsSelectStore",{
    extend:"Ext.data.Store",
    alias: 'store.xfSystem.xfAllot.isSelectStore',
    model: factory.ModelFactory.getModelByName("com.yc.q1.model.base.pt.device.PtTerm", "checked").modelName,
  
});