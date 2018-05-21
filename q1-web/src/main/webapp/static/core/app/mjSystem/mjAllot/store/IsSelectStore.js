Ext.define("core.dkSystem.dkAllot.store.IsSelectStore",{
    extend:"Ext.data.Store",
    alias: 'store.dkSystem.dkAllot.isSelectStore',
    model: factory.ModelFactory.getModelByName("com.yc.q1.model.base.pt.device.PtTerm", "checked").modelName,
  
});