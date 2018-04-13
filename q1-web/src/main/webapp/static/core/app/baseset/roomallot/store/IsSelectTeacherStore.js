Ext.define("core.baseset.roomallot.store.IsSelectTeacherStore",{
    extend:"Ext.data.Store",

    alias: 'store.baseset.roomallot.isselectteacherstore',

 
    model: factory.ModelFactory.getModelByName("com.yc.q1.model.base.pt.system.PtUser", "checked").modelName,
});