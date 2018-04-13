Ext.define("core.basedevice.baserate.store.IsSelectStore",{
    extend:"Ext.data.Store",

    alias: 'store.basedevice.baserate.isselectstore',

 
    model: factory.ModelFactory.getModelByName("com.yc.q1.model.base.pt.device.PtTerm", "checked").modelName,
});