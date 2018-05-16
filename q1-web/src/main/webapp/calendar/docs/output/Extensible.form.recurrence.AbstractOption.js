Ext.data.JsonP.Extensible_form_recurrence_AbstractOption({"tagname":"class","name":"Extensible.form.recurrence.AbstractOption","autodetected":{"aliases":true,"alternateClassNames":true,"extends":true,"mixins":true,"requires":true,"uses":true,"members":true,"code_type":true},"files":[{"filename":"AbstractOption.js","href":"AbstractOption.html#Extensible-form-recurrence-AbstractOption"}],"private":true,"aliases":{},"alternateClassNames":[],"extends":"Ext.form.FieldContainer","mixins":["Ext.form.field.Field"],"requires":["Extensible.form.recurrence.Rule"],"uses":[],"members":[{"name":"rrule","tagname":"cfg","owner":"Extensible.form.recurrence.AbstractOption","id":"cfg-rrule","meta":{}},{"name":"startDate","tagname":"cfg","owner":"Extensible.form.recurrence.AbstractOption","id":"cfg-startDate","meta":{}},{"name":"startDay","tagname":"cfg","owner":"Extensible.form.recurrence.AbstractOption","id":"cfg-startDay","meta":{}},{"name":"defaults","tagname":"property","owner":"Extensible.form.recurrence.AbstractOption","id":"property-defaults","meta":{"private":true}},{"name":"key","tagname":"property","owner":"Extensible.form.recurrence.AbstractOption","id":"property-key","meta":{"private":true}},{"name":"layout","tagname":"property","owner":"Extensible.form.recurrence.AbstractOption","id":"property-layout","meta":{"private":true}},{"name":"maxEndDate","tagname":"property","owner":"Extensible.form.recurrence.AbstractOption","id":"property-maxEndDate","meta":{}},{"name":"optionDelimiter","tagname":"property","owner":"Extensible.form.recurrence.AbstractOption","id":"property-optionDelimiter","meta":{"private":true}},{"name":"afterRender","tagname":"method","owner":"Extensible.form.recurrence.AbstractOption","id":"method-afterRender","meta":{"private":true}},{"name":"getDefaultValue","tagname":"method","owner":"Extensible.form.recurrence.AbstractOption","id":"method-getDefaultValue","meta":{"private":true}},{"name":"getStartDate","tagname":"method","owner":"Extensible.form.recurrence.AbstractOption","id":"method-getStartDate","meta":{"private":true}},{"name":"initComponent","tagname":"method","owner":"Extensible.form.recurrence.AbstractOption","id":"method-initComponent","meta":{"private":true}},{"name":"initRRule","tagname":"method","owner":"Extensible.form.recurrence.AbstractOption","id":"method-initRRule","meta":{"private":true}},{"name":"initRefs","tagname":"method","owner":"Extensible.form.recurrence.AbstractOption","id":"method-initRefs","meta":{"private":true}},{"name":"preSetValue","tagname":"method","owner":"Extensible.form.recurrence.AbstractOption","id":"method-preSetValue","meta":{"private":true}},{"name":"setFrequency","tagname":"method","owner":"Extensible.form.recurrence.AbstractOption","id":"method-setFrequency","meta":{"private":true}},{"name":"setStartDate","tagname":"method","owner":"Extensible.form.recurrence.AbstractOption","id":"method-setStartDate","meta":{"chainable":true,"private":true}},{"name":"change","tagname":"event","owner":"Extensible.form.recurrence.AbstractOption","id":"event-change","meta":{}}],"code_type":"ext_define","id":"class-Extensible.form.recurrence.AbstractOption","short_doc":"The abstract base class for all of the recurrence option widgets. ...","component":false,"superclasses":["Ext.form.FieldContainer"],"subclasses":["Extensible.form.recurrence.option.Duration","Extensible.form.recurrence.option.Interval","Extensible.form.recurrence.option.Monthly","Extensible.form.recurrence.option.Weekly"],"mixedInto":[],"parentMixins":[],"html":"<div><pre class=\"hierarchy\"><h4>Hierarchy</h4><div class='subclass first-child'>Ext.form.FieldContainer<div class='subclass '><strong>Extensible.form.recurrence.AbstractOption</strong></div></div><h4>Mixins</h4><div class='dependency'>Ext.form.field.Field</div><h4>Requires</h4><div class='dependency'><a href='#!/api/Extensible.form.recurrence.Rule' rel='Extensible.form.recurrence.Rule' class='docClass'>Extensible.form.recurrence.Rule</a></div><h4>Subclasses</h4><div class='dependency'><a href='#!/api/Extensible.form.recurrence.option.Duration' rel='Extensible.form.recurrence.option.Duration' class='docClass'>Extensible.form.recurrence.option.Duration</a></div><div class='dependency'><a href='#!/api/Extensible.form.recurrence.option.Interval' rel='Extensible.form.recurrence.option.Interval' class='docClass'>Extensible.form.recurrence.option.Interval</a></div><div class='dependency'><a href='#!/api/Extensible.form.recurrence.option.Monthly' rel='Extensible.form.recurrence.option.Monthly' class='docClass'>Extensible.form.recurrence.option.Monthly</a></div><div class='dependency'><a href='#!/api/Extensible.form.recurrence.option.Weekly' rel='Extensible.form.recurrence.option.Weekly' class='docClass'>Extensible.form.recurrence.option.Weekly</a></div><h4>Files</h4><div class='dependency'><a href='source/AbstractOption.html#Extensible-form-recurrence-AbstractOption' target='_blank'>AbstractOption.js</a></div></pre><div class='doc-contents'><div class='rounded-box private-box'><p><strong>NOTE:</strong> This is a private utility class for internal use by the framework. Don't rely on its existence.</p></div><p>The abstract base class for all of the recurrence option widgets. Intended to be subclassed.</p>\n</div><div class='members'><div class='members-section'><div class='definedBy'>Defined By</div><h3 class='members-title icon-cfg'>Config options</h3><div class='subsection'><div id='cfg-rrule' class='member first-child not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.AbstractOption'>Extensible.form.recurrence.AbstractOption</span><br/><a href='source/AbstractOption.html#Extensible-form-recurrence-AbstractOption-cfg-rrule' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.AbstractOption-cfg-rrule' class='name expandable'>rrule</a> : <a href=\"#!/api/Extensible.form.recurrence.Rule\" rel=\"Extensible.form.recurrence.Rule\" class=\"docClass\">Extensible.form.recurrence.Rule</a><span class=\"signature\"></span></div><div class='description'><div class='short'>The recurrence Rule instance underlying this recurrence\noption widget. ...</div><div class='long'><p>The <a href=\"#!/api/Extensible.form.recurrence.Rule\" rel=\"Extensible.form.recurrence.Rule\" class=\"docClass\">recurrence Rule</a> instance underlying this recurrence\noption widget. This is typically set by the parent <a href=\"#!/api/Extensible.form.recurrence.Fieldset\" rel=\"Extensible.form.recurrence.Fieldset\" class=\"docClass\">fieldset</a>\nso that the same instance is shared across option widgets.</p>\n</div></div></div><div id='cfg-startDate' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.AbstractOption'>Extensible.form.recurrence.AbstractOption</span><br/><a href='source/AbstractOption.html#Extensible-form-recurrence-AbstractOption-cfg-startDate' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.AbstractOption-cfg-startDate' class='name expandable'>startDate</a> : <a href=\"#!/api/Date\" rel=\"Date\" class=\"docClass\">Date</a><span class=\"signature\"></span></div><div class='description'><div class='short'>The start date of the underlying recurrence series. ...</div><div class='long'><p>The start date of the underlying recurrence series. This is not always required, depending on the specific\nrecurrence rules in effect, and will default to the current date if required and not supplied. Like the\n<a href=\"#!/api/Extensible.form.recurrence.AbstractOption-cfg-rrule\" rel=\"Extensible.form.recurrence.AbstractOption-cfg-rrule\" class=\"docClass\">rrule</a> config, this is typically set by the parent <a href=\"#!/api/Extensible.form.recurrence.Fieldset\" rel=\"Extensible.form.recurrence.Fieldset\" class=\"docClass\">fieldset</a>.</p>\n</div></div></div><div id='cfg-startDay' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.AbstractOption'>Extensible.form.recurrence.AbstractOption</span><br/><a href='source/AbstractOption.html#Extensible-form-recurrence-AbstractOption-cfg-startDay' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.AbstractOption-cfg-startDay' class='name expandable'>startDay</a> : <a href=\"#!/api/Number\" rel=\"Number\" class=\"docClass\">Number</a><span class=\"signature\"></span></div><div class='description'><div class='short'>The 0-based index for the day on which the calendar week begins (0=Sunday, which is the default). ...</div><div class='long'><p>The 0-based index for the day on which the calendar week begins (0=Sunday, which is the default).\nUsed anytime a calendar or date picker is displayed within the recurrence options.</p>\n<p>Defaults to: <code>0</code></p></div></div></div></div></div><div class='members-section'><div class='definedBy'>Defined By</div><h3 class='members-title icon-property'>Properties</h3><div class='subsection'><div id='property-defaults' class='member first-child not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.AbstractOption'>Extensible.form.recurrence.AbstractOption</span><br/><a href='source/AbstractOption.html#Extensible-form-recurrence-AbstractOption-property-defaults' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.AbstractOption-property-defaults' class='name expandable'>defaults</a> : <a href=\"#!/api/Object\" rel=\"Object\" class=\"docClass\">Object</a><span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'> ...</div><div class='long'>\n<p>Defaults to: <code>{margins: &#39;0 5 0 0&#39;}</code></p></div></div></div><div id='property-key' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.AbstractOption'>Extensible.form.recurrence.AbstractOption</span><br/><a href='source/AbstractOption.html#Extensible-form-recurrence-AbstractOption-property-key' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.AbstractOption-property-key' class='name expandable'>key</a> : <a href=\"#!/api/Object\" rel=\"Object\" class=\"docClass\">Object</a><span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'>\n</div><div class='long'>\n</div></div></div><div id='property-layout' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.AbstractOption'>Extensible.form.recurrence.AbstractOption</span><br/><a href='source/AbstractOption.html#Extensible-form-recurrence-AbstractOption-property-layout' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.AbstractOption-property-layout' class='name expandable'>layout</a> : <a href=\"#!/api/String\" rel=\"String\" class=\"docClass\">String</a><span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'> ...</div><div class='long'>\n<p>Defaults to: <code>&#39;hbox&#39;</code></p></div></div></div><div id='property-maxEndDate' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.AbstractOption'>Extensible.form.recurrence.AbstractOption</span><br/><a href='source/AbstractOption.html#Extensible-form-recurrence-AbstractOption-property-maxEndDate' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.AbstractOption-property-maxEndDate' class='name expandable'>maxEndDate</a> : <a href=\"#!/api/Object\" rel=\"Object\" class=\"docClass\">Object</a><span class=\"signature\"></span></div><div class='description'><div class='short'><p>Maximum end date allowed when choosing dates from date fields (defaults to 12/31/9999).</p>\n</div><div class='long'><p>Maximum end date allowed when choosing dates from date fields (defaults to 12/31/9999).</p>\n</div></div></div><div id='property-optionDelimiter' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.AbstractOption'>Extensible.form.recurrence.AbstractOption</span><br/><a href='source/AbstractOption.html#Extensible-form-recurrence-AbstractOption-property-optionDelimiter' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.AbstractOption-property-optionDelimiter' class='name expandable'>optionDelimiter</a> : <a href=\"#!/api/String\" rel=\"String\" class=\"docClass\">String</a><span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'> ...</div><div class='long'>\n<p>Defaults to: <code>&#39;;&#39;</code></p></div></div></div></div></div><div class='members-section'><div class='definedBy'>Defined By</div><h3 class='members-title icon-method'>Methods</h3><div class='subsection'><div id='method-afterRender' class='member first-child not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.AbstractOption'>Extensible.form.recurrence.AbstractOption</span><br/><a href='source/AbstractOption.html#Extensible-form-recurrence-AbstractOption-method-afterRender' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.AbstractOption-method-afterRender' class='name expandable'>afterRender</a>( <span class='pre'></span> )<span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'> ...</div><div class='long'>\n</div></div></div><div id='method-getDefaultValue' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.AbstractOption'>Extensible.form.recurrence.AbstractOption</span><br/><a href='source/AbstractOption.html#Extensible-form-recurrence-AbstractOption-method-getDefaultValue' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.AbstractOption-method-getDefaultValue' class='name expandable'>getDefaultValue</a>( <span class='pre'></span> )<span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'> ...</div><div class='long'>\n</div></div></div><div id='method-getStartDate' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.AbstractOption'>Extensible.form.recurrence.AbstractOption</span><br/><a href='source/AbstractOption.html#Extensible-form-recurrence-AbstractOption-method-getStartDate' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.AbstractOption-method-getStartDate' class='name expandable'>getStartDate</a>( <span class='pre'></span> )<span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'> ...</div><div class='long'>\n</div></div></div><div id='method-initComponent' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.AbstractOption'>Extensible.form.recurrence.AbstractOption</span><br/><a href='source/AbstractOption.html#Extensible-form-recurrence-AbstractOption-method-initComponent' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.AbstractOption-method-initComponent' class='name expandable'>initComponent</a>( <span class='pre'></span> )<span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'>TODO: remove ...</div><div class='long'><p>TODO: remove</p>\n</div></div></div><div id='method-initRRule' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.AbstractOption'>Extensible.form.recurrence.AbstractOption</span><br/><a href='source/AbstractOption.html#Extensible-form-recurrence-AbstractOption-method-initRRule' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.AbstractOption-method-initRRule' class='name expandable'>initRRule</a>( <span class='pre'></span> )<span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'> ...</div><div class='long'>\n</div></div></div><div id='method-initRefs' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.AbstractOption'>Extensible.form.recurrence.AbstractOption</span><br/><a href='source/AbstractOption.html#Extensible-form-recurrence-AbstractOption-method-initRefs' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.AbstractOption-method-initRefs' class='name expandable'>initRefs</a>( <span class='pre'></span> )<span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'> ...</div><div class='long'>\n</div></div></div><div id='method-preSetValue' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.AbstractOption'>Extensible.form.recurrence.AbstractOption</span><br/><a href='source/AbstractOption.html#Extensible-form-recurrence-AbstractOption-method-preSetValue' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.AbstractOption-method-preSetValue' class='name expandable'>preSetValue</a>( <span class='pre'>v, readyField</span> )<span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'> ...</div><div class='long'>\n<h3 class=\"pa\">Parameters</h3><ul><li><span class='pre'>v</span> : <a href=\"#!/api/Object\" rel=\"Object\" class=\"docClass\">Object</a><div class='sub-desc'></div></li><li><span class='pre'>readyField</span> : <a href=\"#!/api/Object\" rel=\"Object\" class=\"docClass\">Object</a><div class='sub-desc'></div></li></ul></div></div></div><div id='method-setFrequency' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.AbstractOption'>Extensible.form.recurrence.AbstractOption</span><br/><a href='source/AbstractOption.html#Extensible-form-recurrence-AbstractOption-method-setFrequency' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.AbstractOption-method-setFrequency' class='name expandable'>setFrequency</a>( <span class='pre'>freq</span> )<span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'> ...</div><div class='long'>\n<h3 class=\"pa\">Parameters</h3><ul><li><span class='pre'>freq</span> : <a href=\"#!/api/Object\" rel=\"Object\" class=\"docClass\">Object</a><div class='sub-desc'></div></li></ul></div></div></div><div id='method-setStartDate' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.AbstractOption'>Extensible.form.recurrence.AbstractOption</span><br/><a href='source/AbstractOption.html#Extensible-form-recurrence-AbstractOption-method-setStartDate' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.AbstractOption-method-setStartDate' class='name expandable'>setStartDate</a>( <span class='pre'>dt</span> ) : <a href=\"#!/api/Extensible.form.recurrence.AbstractOption\" rel=\"Extensible.form.recurrence.AbstractOption\" class=\"docClass\">Extensible.form.recurrence.AbstractOption</a><span class=\"signature\"><span class='chainable' >chainable</span><span class='private' >private</span></span></div><div class='description'><div class='short'> ...</div><div class='long'>\n<h3 class=\"pa\">Parameters</h3><ul><li><span class='pre'>dt</span> : <a href=\"#!/api/Object\" rel=\"Object\" class=\"docClass\">Object</a><div class='sub-desc'></div></li></ul><h3 class='pa'>Returns</h3><ul><li><span class='pre'><a href=\"#!/api/Extensible.form.recurrence.AbstractOption\" rel=\"Extensible.form.recurrence.AbstractOption\" class=\"docClass\">Extensible.form.recurrence.AbstractOption</a></span><div class='sub-desc'><p>this</p>\n</div></li></ul></div></div></div></div></div><div class='members-section'><div class='definedBy'>Defined By</div><h3 class='members-title icon-event'>Events</h3><div class='subsection'><div id='event-change' class='member first-child not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.AbstractOption'>Extensible.form.recurrence.AbstractOption</span><br/><a href='source/AbstractOption.html#Extensible-form-recurrence-AbstractOption-event-change' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.AbstractOption-event-change' class='name expandable'>change</a>( <span class='pre'>this, newValue, oldValue, eOpts</span> )<span class=\"signature\"></span></div><div class='description'><div class='short'>Fires when a user-initiated change is detected in the value of the field. ...</div><div class='long'><p>Fires when a user-initiated change is detected in the value of the field.</p>\n<h3 class=\"pa\">Parameters</h3><ul><li><span class='pre'>this</span> : <a href=\"#!/api/Extensible.form.recurrence.AbstractOption\" rel=\"Extensible.form.recurrence.AbstractOption\" class=\"docClass\">Extensible.form.recurrence.AbstractOption</a><div class='sub-desc'>\n</div></li><li><span class='pre'>newValue</span> : Mixed<div class='sub-desc'><p>The new value</p>\n</div></li><li><span class='pre'>oldValue</span> : Mixed<div class='sub-desc'><p>The old value</p>\n</div></li><li><span class='pre'>eOpts</span> : <a href=\"#!/api/Object\" rel=\"Object\" class=\"docClass\">Object</a><div class='sub-desc'><p>The options object passed to Ext.util.Observable.addListener.</p>\n\n\n\n</div></li></ul></div></div></div></div></div></div></div>","meta":{"private":true}});