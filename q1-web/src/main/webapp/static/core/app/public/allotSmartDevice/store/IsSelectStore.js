
Ext.define("core.public.allotSmartDevice.store.IsSelectStore",{
    extend:"Ext.data.Store",
    alias: 'store.public.allotSmartDevice.isSelectStore',
    model: factory.ModelFactory.getModelByName("com.yc.q1.model.base.pt.device.PtTerm", "checked").modelName,
  
});