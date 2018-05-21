Ext.define("core.dkSystem.dkPrice.store.IsSelectStore",{
    extend:"Ext.data.Store",

    alias: 'store.dkSystem.dkPrice.isSelectStore',

 
    model: factory.ModelFactory.getModelByName("com.yc.q1.model.base.pt.device.PtTerm", "checked").modelName,
});