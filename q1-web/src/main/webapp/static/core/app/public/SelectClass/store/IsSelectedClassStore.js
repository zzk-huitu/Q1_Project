
Ext.define("core.public.SelectClass.store.IsSelectedClassStore",{
    extend:"Ext.data.Store",

    alias: 'store.public.SelectClass.isselectedclassstore',
    model: factory.ModelFactory.getModelByName("com.yc.q1.model.base.pt.basic.PtGradeClass", "checked").modelName,
});