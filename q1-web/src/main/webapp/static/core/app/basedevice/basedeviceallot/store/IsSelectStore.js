
Ext.define("core.basedevice.basedeviceallot.store.IsSelectStore",{
    extend:"Ext.data.Store",
    alias: 'store.basedevice.basedeviceallot.isselectstore',
    model: factory.ModelFactory.getModelByName("com.yc.q1.model.base.pt.device.PtTerm", "checked").modelName,
  
});