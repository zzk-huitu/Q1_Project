Ext.data.JsonP.Extensible_form_recurrence_option_Weekly({"tagname":"class","name":"Extensible.form.recurrence.option.Weekly","autodetected":{"aliases":true,"alternateClassNames":true,"extends":true,"mixins":true,"requires":true,"uses":true,"members":true,"code_type":true},"files":[{"filename":"Weekly.js","href":"Weekly.html#Extensible-form-recurrence-option-Weekly"}],"aliases":{"widget":["extensible.recurrence-weekly"]},"alternateClassNames":[],"extends":"Extensible.form.recurrence.AbstractOption","mixins":[],"requires":["Ext.form.CheckboxGroup","Ext.form.field.Checkbox","Extensible.form.recurrence.Parser"],"uses":[],"members":[{"name":"rrule","tagname":"cfg","owner":"Extensible.form.recurrence.AbstractOption","id":"cfg-rrule","meta":{}},{"name":"startDate","tagname":"cfg","owner":"Extensible.form.recurrence.AbstractOption","id":"cfg-startDate","meta":{}},{"name":"startDay","tagname":"cfg","owner":"Extensible.form.recurrence.option.Weekly","id":"cfg-startDay","meta":{}},{"name":"cls","tagname":"property","owner":"Extensible.form.recurrence.option.Weekly","id":"property-cls","meta":{"private":true}},{"name":"dayValueDelimiter","tagname":"property","owner":"Extensible.form.recurrence.option.Weekly","id":"property-dayValueDelimiter","meta":{"private":true}},{"name":"defaults","tagname":"property","owner":"Extensible.form.recurrence.AbstractOption","id":"property-defaults","meta":{"private":true}},{"name":"key","tagname":"property","owner":"Extensible.form.recurrence.AbstractOption","id":"property-key","meta":{"private":true}},{"name":"layout","tagname":"property","owner":"Extensible.form.recurrence.AbstractOption","id":"property-layout","meta":{"private":true}},{"name":"maxEndDate","tagname":"property","owner":"Extensible.form.recurrence.AbstractOption","id":"property-maxEndDate","meta":{}},{"name":"optionDelimiter","tagname":"property","owner":"Extensible.form.recurrence.AbstractOption","id":"property-optionDelimiter","meta":{"private":true}},{"name":"strings","tagname":"property","owner":"Extensible.form.recurrence.option.Weekly","id":"property-strings","meta":{"private":true}},{"name":"afterRender","tagname":"method","owner":"Extensible.form.recurrence.AbstractOption","id":"method-afterRender","meta":{"private":true}},{"name":"clearValue","tagname":"method","owner":"Extensible.form.recurrence.option.Weekly","id":"method-clearValue","meta":{"private":true}},{"name":"getCheckboxGroupItems","tagname":"method","owner":"Extensible.form.recurrence.option.Weekly","id":"method-getCheckboxGroupItems","meta":{}},{"name":"getDefaultValue","tagname":"method","owner":"Extensible.form.recurrence.AbstractOption","id":"method-getDefaultValue","meta":{"private":true}},{"name":"getItemConfigs","tagname":"method","owner":"Extensible.form.recurrence.option.Weekly","id":"method-getItemConfigs","meta":{"private":true}},{"name":"getStartDate","tagname":"method","owner":"Extensible.form.recurrence.AbstractOption","id":"method-getStartDate","meta":{"private":true}},{"name":"getValue","tagname":"method","owner":"Extensible.form.recurrence.option.Weekly","id":"method-getValue","meta":{"private":true}},{"name":"initComponent","tagname":"method","owner":"Extensible.form.recurrence.AbstractOption","id":"method-initComponent","meta":{"private":true}},{"name":"initRRule","tagname":"method","owner":"Extensible.form.recurrence.AbstractOption","id":"method-initRRule","meta":{"private":true}},{"name":"initRefs","tagname":"method","owner":"Extensible.form.recurrence.option.Weekly","id":"method-initRefs","meta":{"private":true}},{"name":"initValue","tagname":"method","owner":"Extensible.form.recurrence.option.Weekly","id":"method-initValue","meta":{"private":true}},{"name":"onSelectionChange","tagname":"method","owner":"Extensible.form.recurrence.option.Weekly","id":"method-onSelectionChange","meta":{"private":true}},{"name":"preSetValue","tagname":"method","owner":"Extensible.form.recurrence.AbstractOption","id":"method-preSetValue","meta":{"private":true}},{"name":"selectByDate","tagname":"method","owner":"Extensible.form.recurrence.option.Weekly","id":"method-selectByDate","meta":{"private":true}},{"name":"setFrequency","tagname":"method","owner":"Extensible.form.recurrence.AbstractOption","id":"method-setFrequency","meta":{"private":true}},{"name":"setStartDate","tagname":"method","owner":"Extensible.form.recurrence.AbstractOption","id":"method-setStartDate","meta":{"chainable":true,"private":true}},{"name":"setValue","tagname":"method","owner":"Extensible.form.recurrence.option.Weekly","id":"method-setValue","meta":{"private":true}},{"name":"change","tagname":"event","owner":"Extensible.form.recurrence.AbstractOption","id":"event-change","meta":{}}],"code_type":"ext_define","id":"class-Extensible.form.recurrence.option.Weekly","component":false,"superclasses":["Ext.form.FieldContainer","Extensible.form.recurrence.AbstractOption"],"subclasses":[],"mixedInto":[],"parentMixins":["Ext.form.field.Field"],"html":"<div><pre class=\"hierarchy\"><h4>Hierarchy</h4><div class='subclass first-child'>Ext.form.FieldContainer<div class='subclass '><a href='#!/api/Extensible.form.recurrence.AbstractOption' rel='Extensible.form.recurrence.AbstractOption' class='docClass'>Extensible.form.recurrence.AbstractOption</a><div class='subclass '><strong>Extensible.form.recurrence.option.Weekly</strong></div></div></div><h4>Inherited mixins</h4><div class='dependency'>Ext.form.field.Field</div><h4>Requires</h4><div class='dependency'>Ext.form.CheckboxGroup</div><div class='dependency'>Ext.form.field.Checkbox</div><div class='dependency'><a href='#!/api/Extensible.form.recurrence.Parser' rel='Extensible.form.recurrence.Parser' class='docClass'>Extensible.form.recurrence.Parser</a></div><h4>Files</h4><div class='dependency'><a href='source/Weekly.html#Extensible-form-recurrence-option-Weekly' target='_blank'>Weekly.js</a></div></pre><div class='doc-contents'><p>The widget that represents the weekly recurrence options of an RRULE.</p>\n</div><div class='members'><div class='members-section'><div class='definedBy'>Defined By</div><h3 class='members-title icon-cfg'>Config options</h3><div class='subsection'><div id='cfg-rrule' class='member first-child inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><a href='#!/api/Extensible.form.recurrence.AbstractOption' rel='Extensible.form.recurrence.AbstractOption' class='defined-in docClass'>Extensible.form.recurrence.AbstractOption</a><br/><a href='source/AbstractOption.html#Extensible-form-recurrence-AbstractOption-cfg-rrule' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.AbstractOption-cfg-rrule' class='name expandable'>rrule</a> : <a href=\"#!/api/Extensible.form.recurrence.Rule\" rel=\"Extensible.form.recurrence.Rule\" class=\"docClass\">Extensible.form.recurrence.Rule</a><span class=\"signature\"></span></div><div class='description'><div class='short'>The recurrence Rule instance underlying this recurrence\noption widget. ...</div><div class='long'><p>The <a href=\"#!/api/Extensible.form.recurrence.Rule\" rel=\"Extensible.form.recurrence.Rule\" class=\"docClass\">recurrence Rule</a> instance underlying this recurrence\noption widget. This is typically set by the parent <a href=\"#!/api/Extensible.form.recurrence.Fieldset\" rel=\"Extensible.form.recurrence.Fieldset\" class=\"docClass\">fieldset</a>\nso that the same instance is shared across option widgets.</p>\n</div></div></div><div id='cfg-startDate' class='member  inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><a href='#!/api/Extensible.form.recurrence.AbstractOption' rel='Extensible.form.recurrence.AbstractOption' class='defined-in docClass'>Extensible.form.recurrence.AbstractOption</a><br/><a href='source/AbstractOption.html#Extensible-form-recurrence-AbstractOption-cfg-startDate' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.AbstractOption-cfg-startDate' class='name expandable'>startDate</a> : <a href=\"#!/api/Date\" rel=\"Date\" class=\"docClass\">Date</a><span class=\"signature\"></span></div><div class='description'><div class='short'>The start date of the underlying recurrence series. ...</div><div class='long'><p>The start date of the underlying recurrence series. This is not always required, depending on the specific\nrecurrence rules in effect, and will default to the current date if required and not supplied. Like the\n<a href=\"#!/api/Extensible.form.recurrence.AbstractOption-cfg-rrule\" rel=\"Extensible.form.recurrence.AbstractOption-cfg-rrule\" class=\"docClass\">rrule</a> config, this is typically set by the parent <a href=\"#!/api/Extensible.form.recurrence.Fieldset\" rel=\"Extensible.form.recurrence.Fieldset\" class=\"docClass\">fieldset</a>.</p>\n</div></div></div><div id='cfg-startDay' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.option.Weekly'>Extensible.form.recurrence.option.Weekly</span><br/><a href='source/Weekly.html#Extensible-form-recurrence-option-Weekly-cfg-startDay' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.option.Weekly-cfg-startDay' class='name expandable'>startDay</a> : <a href=\"#!/api/Number\" rel=\"Number\" class=\"docClass\">Number</a><span class=\"signature\"></span></div><div class='description'><div class='short'>The 0-based index for the day on which the calendar week begins (0=Sunday, which is the default) ...</div><div class='long'><p>The 0-based index for the day on which the calendar week begins (0=Sunday, which is the default)</p>\n<p>Defaults to: <code>0</code></p><p>Overrides: <a href=\"#!/api/Extensible.form.recurrence.AbstractOption-cfg-startDay\" rel=\"Extensible.form.recurrence.AbstractOption-cfg-startDay\" class=\"docClass\">Extensible.form.recurrence.AbstractOption.startDay</a></p></div></div></div></div></div><div class='members-section'><div class='definedBy'>Defined By</div><h3 class='members-title icon-property'>Properties</h3><div class='subsection'><div id='property-cls' class='member first-child not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.option.Weekly'>Extensible.form.recurrence.option.Weekly</span><br/><a href='source/Weekly.html#Extensible-form-recurrence-option-Weekly-property-cls' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.option.Weekly-property-cls' class='name expandable'>cls</a> : <a href=\"#!/api/String\" rel=\"String\" class=\"docClass\">String</a><span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'> ...</div><div class='long'>\n<p>Defaults to: <code>&#39;extensible-recur-weekly&#39;</code></p></div></div></div><div id='property-dayValueDelimiter' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.option.Weekly'>Extensible.form.recurrence.option.Weekly</span><br/><a href='source/Weekly.html#Extensible-form-recurrence-option-Weekly-property-dayValueDelimiter' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.option.Weekly-property-dayValueDelimiter' class='name expandable'>dayValueDelimiter</a> : <a href=\"#!/api/String\" rel=\"String\" class=\"docClass\">String</a><span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'> ...</div><div class='long'>\n<p>Defaults to: <code>&#39;,&#39;</code></p></div></div></div><div id='property-defaults' class='member  inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><a href='#!/api/Extensible.form.recurrence.AbstractOption' rel='Extensible.form.recurrence.AbstractOption' class='defined-in docClass'>Extensible.form.recurrence.AbstractOption</a><br/><a href='source/AbstractOption.html#Extensible-form-recurrence-AbstractOption-property-defaults' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.AbstractOption-property-defaults' class='name expandable'>defaults</a> : <a href=\"#!/api/Object\" rel=\"Object\" class=\"docClass\">Object</a><span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'> ...</div><div class='long'>\n<p>Defaults to: <code>{margins: &#39;0 5 0 0&#39;}</code></p></div></div></div><div id='property-key' class='member  inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><a href='#!/api/Extensible.form.recurrence.AbstractOption' rel='Extensible.form.recurrence.AbstractOption' class='defined-in docClass'>Extensible.form.recurrence.AbstractOption</a><br/><a href='source/AbstractOption.html#Extensible-form-recurrence-AbstractOption-property-key' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.AbstractOption-property-key' class='name expandable'>key</a> : <a href=\"#!/api/Object\" rel=\"Object\" class=\"docClass\">Object</a><span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'>\n</div><div class='long'>\n</div></div></div><div id='property-layout' class='member  inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><a href='#!/api/Extensible.form.recurrence.AbstractOption' rel='Extensible.form.recurrence.AbstractOption' class='defined-in docClass'>Extensible.form.recurrence.AbstractOption</a><br/><a href='source/AbstractOption.html#Extensible-form-recurrence-AbstractOption-property-layout' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.AbstractOption-property-layout' class='name expandable'>layout</a> : <a href=\"#!/api/String\" rel=\"String\" class=\"docClass\">String</a><span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'> ...</div><div class='long'>\n<p>Defaults to: <code>&#39;hbox&#39;</code></p></div></div></div><div id='property-maxEndDate' class='member  inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><a href='#!/api/Extensible.form.recurrence.AbstractOption' rel='Extensible.form.recurrence.AbstractOption' class='defined-in docClass'>Extensible.form.recurrence.AbstractOption</a><br/><a href='source/AbstractOption.html#Extensible-form-recurrence-AbstractOption-property-maxEndDate' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.AbstractOption-property-maxEndDate' class='name expandable'>maxEndDate</a> : <a href=\"#!/api/Object\" rel=\"Object\" class=\"docClass\">Object</a><span class=\"signature\"></span></div><div class='description'><div class='short'><p>Maximum end date allowed when choosing dates from date fields (defaults to 12/31/9999).</p>\n</div><div class='long'><p>Maximum end date allowed when choosing dates from date fields (defaults to 12/31/9999).</p>\n</div></div></div><div id='property-optionDelimiter' class='member  inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><a href='#!/api/Extensible.form.recurrence.AbstractOption' rel='Extensible.form.recurrence.AbstractOption' class='defined-in docClass'>Extensible.form.recurrence.AbstractOption</a><br/><a href='source/AbstractOption.html#Extensible-form-recurrence-AbstractOption-property-optionDelimiter' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.AbstractOption-property-optionDelimiter' class='name expandable'>optionDelimiter</a> : <a href=\"#!/api/String\" rel=\"String\" class=\"docClass\">String</a><span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'> ...</div><div class='long'>\n<p>Defaults to: <code>&#39;;&#39;</code></p></div></div></div><div id='property-strings' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.option.Weekly'>Extensible.form.recurrence.option.Weekly</span><br/><a href='source/Weekly.html#Extensible-form-recurrence-option-Weekly-property-strings' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.option.Weekly-property-strings' class='name expandable'>strings</a> : <a href=\"#!/api/Object\" rel=\"Object\" class=\"docClass\">Object</a><span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'> ...</div><div class='long'>\n<p>Defaults to: <code>{on: &#39;on&#39;}</code></p></div></div></div></div></div><div class='members-section'><div class='definedBy'>Defined By</div><h3 class='members-title icon-method'>Methods</h3><div class='subsection'><div id='method-afterRender' class='member first-child inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><a href='#!/api/Extensible.form.recurrence.AbstractOption' rel='Extensible.form.recurrence.AbstractOption' class='defined-in docClass'>Extensible.form.recurrence.AbstractOption</a><br/><a href='source/AbstractOption.html#Extensible-form-recurrence-AbstractOption-method-afterRender' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.AbstractOption-method-afterRender' class='name expandable'>afterRender</a>( <span class='pre'></span> )<span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'> ...</div><div class='long'>\n</div></div></div><div id='method-clearValue' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.option.Weekly'>Extensible.form.recurrence.option.Weekly</span><br/><a href='source/Weekly.html#Extensible-form-recurrence-option-Weekly-method-clearValue' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.option.Weekly-method-clearValue' class='name expandable'>clearValue</a>( <span class='pre'></span> )<span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'> ...</div><div class='long'>\n</div></div></div><div id='method-getCheckboxGroupItems' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.option.Weekly'>Extensible.form.recurrence.option.Weekly</span><br/><a href='source/Weekly.html#Extensible-form-recurrence-option-Weekly-method-getCheckboxGroupItems' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.option.Weekly-method-getCheckboxGroupItems' class='name expandable'>getCheckboxGroupItems</a>( <span class='pre'></span> ) : <a href=\"#!/api/Array\" rel=\"Array\" class=\"docClass\">Array</a><span class=\"signature\"></span></div><div class='description'><div class='short'>Creates the item configuration for the checkbox group. ...</div><div class='long'><p>Creates the item configuration for the checkbox group. Takes into account the week start day.\nFor example:\n    [\n        { boxLabel: 'Sun', name: 'SU', id: this.id + '-SU' },\n        { boxLabel: 'Mon', name: 'MO', id: this.id + '-MO' },\n        { boxLabel: 'Tue', name: 'TU', id: this.id + '-TU' },\n        { boxLabel: 'Wed', name: 'WE', id: this.id + '-WE' },\n        { boxLabel: 'Thu', name: 'TH', id: this.id + '-TH' },\n        { boxLabel: 'Fri', name: 'FR', id: this.id + '-FR' },\n        { boxLabel: 'Sat', name: 'SA', id: this.id + '-SA' }\n    ];</p>\n<h3 class='pa'>Returns</h3><ul><li><span class='pre'><a href=\"#!/api/Array\" rel=\"Array\" class=\"docClass\">Array</a></span><div class='sub-desc'>\n</div></li></ul></div></div></div><div id='method-getDefaultValue' class='member  inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><a href='#!/api/Extensible.form.recurrence.AbstractOption' rel='Extensible.form.recurrence.AbstractOption' class='defined-in docClass'>Extensible.form.recurrence.AbstractOption</a><br/><a href='source/AbstractOption.html#Extensible-form-recurrence-AbstractOption-method-getDefaultValue' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.AbstractOption-method-getDefaultValue' class='name expandable'>getDefaultValue</a>( <span class='pre'></span> )<span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'> ...</div><div class='long'>\n</div></div></div><div id='method-getItemConfigs' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.option.Weekly'>Extensible.form.recurrence.option.Weekly</span><br/><a href='source/Weekly.html#Extensible-form-recurrence-option-Weekly-method-getItemConfigs' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.option.Weekly-method-getItemConfigs' class='name expandable'>getItemConfigs</a>( <span class='pre'></span> )<span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'> ...</div><div class='long'>\n</div></div></div><div id='method-getStartDate' class='member  inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><a href='#!/api/Extensible.form.recurrence.AbstractOption' rel='Extensible.form.recurrence.AbstractOption' class='defined-in docClass'>Extensible.form.recurrence.AbstractOption</a><br/><a href='source/AbstractOption.html#Extensible-form-recurrence-AbstractOption-method-getStartDate' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.AbstractOption-method-getStartDate' class='name expandable'>getStartDate</a>( <span class='pre'></span> )<span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'> ...</div><div class='long'>\n</div></div></div><div id='method-getValue' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.option.Weekly'>Extensible.form.recurrence.option.Weekly</span><br/><a href='source/Weekly.html#Extensible-form-recurrence-option-Weekly-method-getValue' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.option.Weekly-method-getValue' class='name expandable'>getValue</a>( <span class='pre'></span> )<span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'> ...</div><div class='long'>\n</div></div></div><div id='method-initComponent' class='member  inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><a href='#!/api/Extensible.form.recurrence.AbstractOption' rel='Extensible.form.recurrence.AbstractOption' class='defined-in docClass'>Extensible.form.recurrence.AbstractOption</a><br/><a href='source/AbstractOption.html#Extensible-form-recurrence-AbstractOption-method-initComponent' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.AbstractOption-method-initComponent' class='name expandable'>initComponent</a>( <span class='pre'></span> )<span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'>TODO: remove ...</div><div class='long'><p>TODO: remove</p>\n</div></div></div><div id='method-initRRule' class='member  inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><a href='#!/api/Extensible.form.recurrence.AbstractOption' rel='Extensible.form.recurrence.AbstractOption' class='defined-in docClass'>Extensible.form.recurrence.AbstractOption</a><br/><a href='source/AbstractOption.html#Extensible-form-recurrence-AbstractOption-method-initRRule' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.AbstractOption-method-initRRule' class='name expandable'>initRRule</a>( <span class='pre'></span> )<span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'> ...</div><div class='long'>\n</div></div></div><div id='method-initRefs' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.option.Weekly'>Extensible.form.recurrence.option.Weekly</span><br/><a href='source/Weekly.html#Extensible-form-recurrence-option-Weekly-method-initRefs' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.option.Weekly-method-initRefs' class='name expandable'>initRefs</a>( <span class='pre'></span> )<span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'> ...</div><div class='long'>\n<p>Overrides: <a href=\"#!/api/Extensible.form.recurrence.AbstractOption-method-initRefs\" rel=\"Extensible.form.recurrence.AbstractOption-method-initRefs\" class=\"docClass\">Extensible.form.recurrence.AbstractOption.initRefs</a></p></div></div></div><div id='method-initValue' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.option.Weekly'>Extensible.form.recurrence.option.Weekly</span><br/><a href='source/Weekly.html#Extensible-form-recurrence-option-Weekly-method-initValue' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.option.Weekly-method-initValue' class='name expandable'>initValue</a>( <span class='pre'></span> )<span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'> ...</div><div class='long'>\n</div></div></div><div id='method-onSelectionChange' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.option.Weekly'>Extensible.form.recurrence.option.Weekly</span><br/><a href='source/Weekly.html#Extensible-form-recurrence-option-Weekly-method-onSelectionChange' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.option.Weekly-method-onSelectionChange' class='name expandable'>onSelectionChange</a>( <span class='pre'>field, value, oldValue</span> )<span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'> ...</div><div class='long'>\n<h3 class=\"pa\">Parameters</h3><ul><li><span class='pre'>field</span> : <a href=\"#!/api/Object\" rel=\"Object\" class=\"docClass\">Object</a><div class='sub-desc'></div></li><li><span class='pre'>value</span> : <a href=\"#!/api/Object\" rel=\"Object\" class=\"docClass\">Object</a><div class='sub-desc'></div></li><li><span class='pre'>oldValue</span> : <a href=\"#!/api/Object\" rel=\"Object\" class=\"docClass\">Object</a><div class='sub-desc'></div></li></ul></div></div></div><div id='method-preSetValue' class='member  inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><a href='#!/api/Extensible.form.recurrence.AbstractOption' rel='Extensible.form.recurrence.AbstractOption' class='defined-in docClass'>Extensible.form.recurrence.AbstractOption</a><br/><a href='source/AbstractOption.html#Extensible-form-recurrence-AbstractOption-method-preSetValue' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.AbstractOption-method-preSetValue' class='name expandable'>preSetValue</a>( <span class='pre'>v, readyField</span> )<span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'> ...</div><div class='long'>\n<h3 class=\"pa\">Parameters</h3><ul><li><span class='pre'>v</span> : <a href=\"#!/api/Object\" rel=\"Object\" class=\"docClass\">Object</a><div class='sub-desc'></div></li><li><span class='pre'>readyField</span> : <a href=\"#!/api/Object\" rel=\"Object\" class=\"docClass\">Object</a><div class='sub-desc'></div></li></ul></div></div></div><div id='method-selectByDate' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.option.Weekly'>Extensible.form.recurrence.option.Weekly</span><br/><a href='source/Weekly.html#Extensible-form-recurrence-option-Weekly-method-selectByDate' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.option.Weekly-method-selectByDate' class='name expandable'>selectByDate</a>( <span class='pre'>dt</span> )<span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'> ...</div><div class='long'>\n<h3 class=\"pa\">Parameters</h3><ul><li><span class='pre'>dt</span> : <a href=\"#!/api/Object\" rel=\"Object\" class=\"docClass\">Object</a><div class='sub-desc'></div></li></ul></div></div></div><div id='method-setFrequency' class='member  inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><a href='#!/api/Extensible.form.recurrence.AbstractOption' rel='Extensible.form.recurrence.AbstractOption' class='defined-in docClass'>Extensible.form.recurrence.AbstractOption</a><br/><a href='source/AbstractOption.html#Extensible-form-recurrence-AbstractOption-method-setFrequency' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.AbstractOption-method-setFrequency' class='name expandable'>setFrequency</a>( <span class='pre'>freq</span> )<span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'> ...</div><div class='long'>\n<h3 class=\"pa\">Parameters</h3><ul><li><span class='pre'>freq</span> : <a href=\"#!/api/Object\" rel=\"Object\" class=\"docClass\">Object</a><div class='sub-desc'></div></li></ul></div></div></div><div id='method-setStartDate' class='member  inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><a href='#!/api/Extensible.form.recurrence.AbstractOption' rel='Extensible.form.recurrence.AbstractOption' class='defined-in docClass'>Extensible.form.recurrence.AbstractOption</a><br/><a href='source/AbstractOption.html#Extensible-form-recurrence-AbstractOption-method-setStartDate' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.AbstractOption-method-setStartDate' class='name expandable'>setStartDate</a>( <span class='pre'>dt</span> ) : <a href=\"#!/api/Extensible.form.recurrence.AbstractOption\" rel=\"Extensible.form.recurrence.AbstractOption\" class=\"docClass\">Extensible.form.recurrence.AbstractOption</a><span class=\"signature\"><span class='chainable' >chainable</span><span class='private' >private</span></span></div><div class='description'><div class='short'> ...</div><div class='long'>\n<h3 class=\"pa\">Parameters</h3><ul><li><span class='pre'>dt</span> : <a href=\"#!/api/Object\" rel=\"Object\" class=\"docClass\">Object</a><div class='sub-desc'></div></li></ul><h3 class='pa'>Returns</h3><ul><li><span class='pre'><a href=\"#!/api/Extensible.form.recurrence.AbstractOption\" rel=\"Extensible.form.recurrence.AbstractOption\" class=\"docClass\">Extensible.form.recurrence.AbstractOption</a></span><div class='sub-desc'><p>this</p>\n</div></li></ul></div></div></div><div id='method-setValue' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.option.Weekly'>Extensible.form.recurrence.option.Weekly</span><br/><a href='source/Weekly.html#Extensible-form-recurrence-option-Weekly-method-setValue' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.option.Weekly-method-setValue' class='name expandable'>setValue</a>( <span class='pre'>v</span> )<span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'> ...</div><div class='long'>\n<h3 class=\"pa\">Parameters</h3><ul><li><span class='pre'>v</span> : <a href=\"#!/api/Object\" rel=\"Object\" class=\"docClass\">Object</a><div class='sub-desc'></div></li></ul></div></div></div></div></div><div class='members-section'><div class='definedBy'>Defined By</div><h3 class='members-title icon-event'>Events</h3><div class='subsection'><div id='event-change' class='member first-child inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><a href='#!/api/Extensible.form.recurrence.AbstractOption' rel='Extensible.form.recurrence.AbstractOption' class='defined-in docClass'>Extensible.form.recurrence.AbstractOption</a><br/><a href='source/AbstractOption.html#Extensible-form-recurrence-AbstractOption-event-change' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.AbstractOption-event-change' class='name expandable'>change</a>( <span class='pre'>this, newValue, oldValue, eOpts</span> )<span class=\"signature\"></span></div><div class='description'><div class='short'>Fires when a user-initiated change is detected in the value of the field. ...</div><div class='long'><p>Fires when a user-initiated change is detected in the value of the field.</p>\n<h3 class=\"pa\">Parameters</h3><ul><li><span class='pre'>this</span> : <a href=\"#!/api/Extensible.form.recurrence.AbstractOption\" rel=\"Extensible.form.recurrence.AbstractOption\" class=\"docClass\">Extensible.form.recurrence.AbstractOption</a><div class='sub-desc'>\n</div></li><li><span class='pre'>newValue</span> : Mixed<div class='sub-desc'><p>The new value</p>\n</div></li><li><span class='pre'>oldValue</span> : Mixed<div class='sub-desc'><p>The old value</p>\n</div></li><li><span class='pre'>eOpts</span> : <a href=\"#!/api/Object\" rel=\"Object\" class=\"docClass\">Object</a><div class='sub-desc'><p>The options object passed to Ext.util.Observable.addListener.</p>\n\n\n\n</div></li></ul></div></div></div></div></div></div></div>","meta":{}});