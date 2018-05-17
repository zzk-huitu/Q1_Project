Ext.data.JsonP.Extensible_form_recurrence_Fieldset({"tagname":"class","name":"Extensible.form.recurrence.Fieldset","autodetected":{"aliases":true,"alternateClassNames":true,"extends":true,"mixins":true,"requires":true,"uses":true,"members":true,"code_type":true},"files":[{"filename":"Fieldset.js","href":"Fieldset.html#Extensible-form-recurrence-Fieldset"}],"aliases":{"widget":["extensible.recurrencefield"]},"alternateClassNames":[],"extends":"Ext.form.FieldContainer","mixins":["Ext.form.field.Field"],"requires":["Ext.form.Label","Extensible.form.recurrence.FrequencyCombo","Extensible.form.recurrence.Rule","Extensible.form.recurrence.option.Duration","Extensible.form.recurrence.option.Interval","Extensible.form.recurrence.option.Monthly","Extensible.form.recurrence.option.Weekly","Extensible.form.recurrence.option.Yearly"],"uses":[],"members":[{"name":"rrule","tagname":"cfg","owner":"Extensible.form.recurrence.Fieldset","id":"cfg-rrule","meta":{}},{"name":"startDate","tagname":"cfg","owner":"Extensible.form.recurrence.Fieldset","id":"cfg-startDate","meta":{}},{"name":"startDay","tagname":"cfg","owner":"Extensible.form.recurrence.Fieldset","id":"cfg-startDay","meta":{}},{"name":"cls","tagname":"property","owner":"Extensible.form.recurrence.Fieldset","id":"property-cls","meta":{"private":true}},{"name":"defaults","tagname":"property","owner":"Extensible.form.recurrence.Fieldset","id":"property-defaults","meta":{"private":true}},{"name":"displayStyle","tagname":"property","owner":"Extensible.form.recurrence.Fieldset","id":"property-displayStyle","meta":{"private":true}},{"name":"fieldContainerWidth","tagname":"property","owner":"Extensible.form.recurrence.Fieldset","id":"property-fieldContainerWidth","meta":{"private":true}},{"name":"fieldLabel","tagname":"property","owner":"Extensible.form.recurrence.Fieldset","id":"property-fieldLabel","meta":{"private":true}},{"name":"frequencyWidth","tagname":"property","owner":"Extensible.form.recurrence.Fieldset","id":"property-frequencyWidth","meta":{"private":true}},{"name":"layout","tagname":"property","owner":"Extensible.form.recurrence.Fieldset","id":"property-layout","meta":{"private":true}},{"name":"monitorChanges","tagname":"property","owner":"Extensible.form.recurrence.Fieldset","id":"property-monitorChanges","meta":{"private":true}},{"name":"options","tagname":"property","owner":"Extensible.form.recurrence.Fieldset","id":"property-options","meta":{"private":true}},{"name":"afterRender","tagname":"method","owner":"Extensible.form.recurrence.Fieldset","id":"method-afterRender","meta":{"private":true}},{"name":"getDescription","tagname":"method","owner":"Extensible.form.recurrence.Fieldset","id":"method-getDescription","meta":{"private":true}},{"name":"getStartDate","tagname":"method","owner":"Extensible.form.recurrence.Fieldset","id":"method-getStartDate","meta":{}},{"name":"getValue","tagname":"method","owner":"Extensible.form.recurrence.Fieldset","id":"method-getValue","meta":{"private":true}},{"name":"includeItemValue","tagname":"method","owner":"Extensible.form.recurrence.Fieldset","id":"method-includeItemValue","meta":{"private":true}},{"name":"initChangeEvents","tagname":"method","owner":"Extensible.form.recurrence.Fieldset","id":"method-initChangeEvents","meta":{"private":true}},{"name":"initComponent","tagname":"method","owner":"Extensible.form.recurrence.Fieldset","id":"method-initComponent","meta":{"private":true}},{"name":"initRRule","tagname":"method","owner":"Extensible.form.recurrence.Fieldset","id":"method-initRRule","meta":{"private":true}},{"name":"initRefs","tagname":"method","owner":"Extensible.form.recurrence.Fieldset","id":"method-initRefs","meta":{"private":true}},{"name":"initValue","tagname":"method","owner":"Extensible.form.recurrence.Fieldset","id":"method-initValue","meta":{"private":true}},{"name":"isRecurring","tagname":"method","owner":"Extensible.form.recurrence.Fieldset","id":"method-isRecurring","meta":{}},{"name":"onChange","tagname":"method","owner":"Extensible.form.recurrence.Fieldset","id":"method-onChange","meta":{"private":true}},{"name":"onFrequencyChange","tagname":"method","owner":"Extensible.form.recurrence.Fieldset","id":"method-onFrequencyChange","meta":{"private":true}},{"name":"onStartDateChange","tagname":"method","owner":"Extensible.form.recurrence.Fieldset","id":"method-onStartDateChange","meta":{"private":true}},{"name":"setFrequency","tagname":"method","owner":"Extensible.form.recurrence.Fieldset","id":"method-setFrequency","meta":{"private":true}},{"name":"setStartDate","tagname":"method","owner":"Extensible.form.recurrence.Fieldset","id":"method-setStartDate","meta":{"chainable":true}},{"name":"setValue","tagname":"method","owner":"Extensible.form.recurrence.Fieldset","id":"method-setValue","meta":{"private":true}},{"name":"showOptions","tagname":"method","owner":"Extensible.form.recurrence.Fieldset","id":"method-showOptions","meta":{"private":true}},{"name":"startchange","tagname":"event","owner":"Extensible.form.recurrence.Fieldset","id":"event-startchange","meta":{}}],"code_type":"ext_define","id":"class-Extensible.form.recurrence.Fieldset","short_doc":"The widget that represents a single recurrence rule field in the UI. ...","component":false,"superclasses":["Ext.form.FieldContainer"],"subclasses":[],"mixedInto":[],"parentMixins":[],"html":"<div><pre class=\"hierarchy\"><h4>Hierarchy</h4><div class='subclass first-child'>Ext.form.FieldContainer<div class='subclass '><strong>Extensible.form.recurrence.Fieldset</strong></div></div><h4>Mixins</h4><div class='dependency'>Ext.form.field.Field</div><h4>Requires</h4><div class='dependency'>Ext.form.Label</div><div class='dependency'><a href='#!/api/Extensible.form.recurrence.FrequencyCombo' rel='Extensible.form.recurrence.FrequencyCombo' class='docClass'>Extensible.form.recurrence.FrequencyCombo</a></div><div class='dependency'><a href='#!/api/Extensible.form.recurrence.Rule' rel='Extensible.form.recurrence.Rule' class='docClass'>Extensible.form.recurrence.Rule</a></div><div class='dependency'><a href='#!/api/Extensible.form.recurrence.option.Duration' rel='Extensible.form.recurrence.option.Duration' class='docClass'>Extensible.form.recurrence.option.Duration</a></div><div class='dependency'><a href='#!/api/Extensible.form.recurrence.option.Interval' rel='Extensible.form.recurrence.option.Interval' class='docClass'>Extensible.form.recurrence.option.Interval</a></div><div class='dependency'><a href='#!/api/Extensible.form.recurrence.option.Monthly' rel='Extensible.form.recurrence.option.Monthly' class='docClass'>Extensible.form.recurrence.option.Monthly</a></div><div class='dependency'><a href='#!/api/Extensible.form.recurrence.option.Weekly' rel='Extensible.form.recurrence.option.Weekly' class='docClass'>Extensible.form.recurrence.option.Weekly</a></div><div class='dependency'><a href='#!/api/Extensible.form.recurrence.option.Yearly' rel='Extensible.form.recurrence.option.Yearly' class='docClass'>Extensible.form.recurrence.option.Yearly</a></div><h4>Files</h4><div class='dependency'><a href='source/Fieldset.html#Extensible-form-recurrence-Fieldset' target='_blank'>Fieldset.js</a></div></pre><div class='doc-contents'><p>The widget that represents a single recurrence rule field in the UI.\nIn reality, it is made up of many constituent\noption widgets internally.</p>\n</div><div class='members'><div class='members-section'><div class='definedBy'>Defined By</div><h3 class='members-title icon-cfg'>Config options</h3><div class='subsection'><div id='cfg-rrule' class='member first-child not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.Fieldset'>Extensible.form.recurrence.Fieldset</span><br/><a href='source/Fieldset.html#Extensible-form-recurrence-Fieldset-cfg-rrule' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.Fieldset-cfg-rrule' class='name expandable'>rrule</a> : <a href=\"#!/api/Extensible.form.recurrence.Rule\" rel=\"Extensible.form.recurrence.Rule\" class=\"docClass\">Extensible.form.recurrence.Rule</a><span class=\"signature\"></span></div><div class='description'><div class='short'>The recurrence Rule instance underlying this component and\nshared by all child recurrence option widgets. ...</div><div class='long'><p>The <a href=\"#!/api/Extensible.form.recurrence.Rule\" rel=\"Extensible.form.recurrence.Rule\" class=\"docClass\">recurrence Rule</a> instance underlying this component and\nshared by all child recurrence option widgets. If not supplied a default instance will be created.</p>\n</div></div></div><div id='cfg-startDate' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.Fieldset'>Extensible.form.recurrence.Fieldset</span><br/><a href='source/Fieldset.html#Extensible-form-recurrence-Fieldset-cfg-startDate' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.Fieldset-cfg-startDate' class='name expandable'>startDate</a> : <a href=\"#!/api/Date\" rel=\"Date\" class=\"docClass\">Date</a><span class=\"signature\"></span></div><div class='description'><div class='short'>The start date of the underlying recurrence series. ...</div><div class='long'><p>The start date of the underlying recurrence series. This is not always required, depending on the specific\nrecurrence rules in effect, and will default to the current date if required and not supplied.</p>\n</div></div></div><div id='cfg-startDay' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.Fieldset'>Extensible.form.recurrence.Fieldset</span><br/><a href='source/Fieldset.html#Extensible-form-recurrence-Fieldset-cfg-startDay' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.Fieldset-cfg-startDay' class='name expandable'>startDay</a> : <a href=\"#!/api/Number\" rel=\"Number\" class=\"docClass\">Number</a><span class=\"signature\"></span></div><div class='description'><div class='short'>The 0-based index for the day on which the calendar week begins (0=Sunday, which is the default) ...</div><div class='long'><p>The 0-based index for the day on which the calendar week begins (0=Sunday, which is the default)</p>\n<p>Defaults to: <code>0</code></p></div></div></div></div></div><div class='members-section'><div class='definedBy'>Defined By</div><h3 class='members-title icon-property'>Properties</h3><div class='subsection'><div id='property-cls' class='member first-child not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.Fieldset'>Extensible.form.recurrence.Fieldset</span><br/><a href='source/Fieldset.html#Extensible-form-recurrence-Fieldset-property-cls' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.Fieldset-property-cls' class='name expandable'>cls</a> : <a href=\"#!/api/String\" rel=\"String\" class=\"docClass\">String</a><span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'> ...</div><div class='long'>\n<p>Defaults to: <code>&#39;extensible-recur-field&#39;</code></p></div></div></div><div id='property-defaults' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.Fieldset'>Extensible.form.recurrence.Fieldset</span><br/><a href='source/Fieldset.html#Extensible-form-recurrence-Fieldset-property-defaults' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.Fieldset-property-defaults' class='name expandable'>defaults</a> : <a href=\"#!/api/Object\" rel=\"Object\" class=\"docClass\">Object</a><span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'> ...</div><div class='long'>\n<p>Defaults to: <code>{anchor: &#39;100%&#39;}</code></p></div></div></div><div id='property-displayStyle' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.Fieldset'>Extensible.form.recurrence.Fieldset</span><br/><a href='source/Fieldset.html#Extensible-form-recurrence-Fieldset-property-displayStyle' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.Fieldset-property-displayStyle' class='name expandable'>displayStyle</a> : <a href=\"#!/api/String\" rel=\"String\" class=\"docClass\">String</a><span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'>TODO: implement ...</div><div class='long'><p>TODO: implement</p>\n<p>Defaults to: <code>&#39;field&#39;</code></p></div></div></div><div id='property-fieldContainerWidth' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.Fieldset'>Extensible.form.recurrence.Fieldset</span><br/><a href='source/Fieldset.html#Extensible-form-recurrence-Fieldset-property-fieldContainerWidth' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.Fieldset-property-fieldContainerWidth' class='name expandable'>fieldContainerWidth</a> : <a href=\"#!/api/Number\" rel=\"Number\" class=\"docClass\">Number</a><span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'> ...</div><div class='long'>\n<p>Defaults to: <code>400</code></p></div></div></div><div id='property-fieldLabel' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.Fieldset'>Extensible.form.recurrence.Fieldset</span><br/><a href='source/Fieldset.html#Extensible-form-recurrence-Fieldset-property-fieldLabel' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.Fieldset-property-fieldLabel' class='name expandable'>fieldLabel</a> : <a href=\"#!/api/String\" rel=\"String\" class=\"docClass\">String</a><span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'>or 'dialog' ...</div><div class='long'><p>or 'dialog'</p>\n<p>Defaults to: <code>&#39;Repeats&#39;</code></p></div></div></div><div id='property-frequencyWidth' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.Fieldset'>Extensible.form.recurrence.Fieldset</span><br/><a href='source/Fieldset.html#Extensible-form-recurrence-Fieldset-property-frequencyWidth' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.Fieldset-property-frequencyWidth' class='name expandable'>frequencyWidth</a> : <a href=\"#!/api/Object\" rel=\"Object\" class=\"docClass\">Object</a><span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'>\n</div><div class='long'>\n</div></div></div><div id='property-layout' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.Fieldset'>Extensible.form.recurrence.Fieldset</span><br/><a href='source/Fieldset.html#Extensible-form-recurrence-Fieldset-property-layout' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.Fieldset-property-layout' class='name expandable'>layout</a> : <a href=\"#!/api/String\" rel=\"String\" class=\"docClass\">String</a><span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'>defaults to the anchor value ...</div><div class='long'><p>defaults to the anchor value</p>\n<p>Defaults to: <code>&#39;anchor&#39;</code></p></div></div></div><div id='property-monitorChanges' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.Fieldset'>Extensible.form.recurrence.Fieldset</span><br/><a href='source/Fieldset.html#Extensible-form-recurrence-Fieldset-property-monitorChanges' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.Fieldset-property-monitorChanges' class='name expandable'>monitorChanges</a> : <a href=\"#!/api/Boolean\" rel=\"Boolean\" class=\"docClass\">Boolean</a><span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'>enableFx: true, ...</div><div class='long'><p>enableFx: true,</p>\n<p>Defaults to: <code>true</code></p></div></div></div><div id='property-options' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.Fieldset'>Extensible.form.recurrence.Fieldset</span><br/><a href='source/Fieldset.html#Extensible-form-recurrence-Fieldset-property-options' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.Fieldset-property-options' class='name expandable'>options</a> : <a href=\"#!/api/Array\" rel=\"Array\" class=\"docClass\">Array</a><span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'>TODO: implement code to use this config. ...</div><div class='long'><p>TODO: implement code to use this config.\n Maybe use xtypes instead for dynamic loading of custom options?\n Include secondly/minutely/hourly, plugins for M-W-F, T-Th, weekends</p>\n<p>Defaults to: <code>[&#39;daily&#39;, &#39;weekly&#39;, &#39;weekdays&#39;, &#39;monthly&#39;, &#39;yearly&#39;]</code></p></div></div></div></div></div><div class='members-section'><div class='definedBy'>Defined By</div><h3 class='members-title icon-method'>Methods</h3><div class='subsection'><div id='method-afterRender' class='member first-child not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.Fieldset'>Extensible.form.recurrence.Fieldset</span><br/><a href='source/Fieldset.html#Extensible-form-recurrence-Fieldset-method-afterRender' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.Fieldset-method-afterRender' class='name expandable'>afterRender</a>( <span class='pre'></span> )<span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'> ...</div><div class='long'>\n</div></div></div><div id='method-getDescription' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.Fieldset'>Extensible.form.recurrence.Fieldset</span><br/><a href='source/Fieldset.html#Extensible-form-recurrence-Fieldset-method-getDescription' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.Fieldset-method-getDescription' class='name expandable'>getDescription</a>( <span class='pre'></span> )<span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'> ...</div><div class='long'>\n</div></div></div><div id='method-getStartDate' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.Fieldset'>Extensible.form.recurrence.Fieldset</span><br/><a href='source/Fieldset.html#Extensible-form-recurrence-Fieldset-method-getStartDate' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.Fieldset-method-getStartDate' class='name expandable'>getStartDate</a>( <span class='pre'></span> ) : <a href=\"#!/api/Date\" rel=\"Date\" class=\"docClass\">Date</a><span class=\"signature\"></span></div><div class='description'><div class='short'>Returns the start date of the recurrence pattern (defaults to the current date\nif not explicitly set via setStartDate...</div><div class='long'><p>Returns the start date of the recurrence pattern (defaults to the current date\nif not explicitly set via <a href=\"#!/api/Extensible.form.recurrence.Fieldset-method-setStartDate\" rel=\"Extensible.form.recurrence.Fieldset-method-setStartDate\" class=\"docClass\">setStartDate</a> or the constructor).</p>\n<h3 class='pa'>Returns</h3><ul><li><span class='pre'><a href=\"#!/api/Date\" rel=\"Date\" class=\"docClass\">Date</a></span><div class='sub-desc'><p>The recurrence start date</p>\n</div></li></ul></div></div></div><div id='method-getValue' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.Fieldset'>Extensible.form.recurrence.Fieldset</span><br/><a href='source/Fieldset.html#Extensible-form-recurrence-Fieldset-method-getValue' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.Fieldset-method-getValue' class='name expandable'>getValue</a>( <span class='pre'></span> )<span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'> ...</div><div class='long'>\n</div></div></div><div id='method-includeItemValue' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.Fieldset'>Extensible.form.recurrence.Fieldset</span><br/><a href='source/Fieldset.html#Extensible-form-recurrence-Fieldset-method-includeItemValue' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.Fieldset-method-includeItemValue' class='name expandable'>includeItemValue</a>( <span class='pre'>value</span> )<span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'> ...</div><div class='long'>\n<h3 class=\"pa\">Parameters</h3><ul><li><span class='pre'>value</span> : <a href=\"#!/api/Object\" rel=\"Object\" class=\"docClass\">Object</a><div class='sub-desc'></div></li></ul></div></div></div><div id='method-initChangeEvents' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.Fieldset'>Extensible.form.recurrence.Fieldset</span><br/><a href='source/Fieldset.html#Extensible-form-recurrence-Fieldset-method-initChangeEvents' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.Fieldset-method-initChangeEvents' class='name expandable'>initChangeEvents</a>( <span class='pre'></span> )<span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'> ...</div><div class='long'>\n</div></div></div><div id='method-initComponent' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.Fieldset'>Extensible.form.recurrence.Fieldset</span><br/><a href='source/Fieldset.html#Extensible-form-recurrence-Fieldset-method-initComponent' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.Fieldset-method-initComponent' class='name expandable'>initComponent</a>( <span class='pre'></span> )<span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'> ...</div><div class='long'>\n</div></div></div><div id='method-initRRule' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.Fieldset'>Extensible.form.recurrence.Fieldset</span><br/><a href='source/Fieldset.html#Extensible-form-recurrence-Fieldset-method-initRRule' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.Fieldset-method-initRRule' class='name expandable'>initRRule</a>( <span class='pre'></span> )<span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'> ...</div><div class='long'>\n</div></div></div><div id='method-initRefs' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.Fieldset'>Extensible.form.recurrence.Fieldset</span><br/><a href='source/Fieldset.html#Extensible-form-recurrence-Fieldset-method-initRefs' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.Fieldset-method-initRefs' class='name expandable'>initRefs</a>( <span class='pre'></span> )<span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'> ...</div><div class='long'>\n</div></div></div><div id='method-initValue' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.Fieldset'>Extensible.form.recurrence.Fieldset</span><br/><a href='source/Fieldset.html#Extensible-form-recurrence-Fieldset-method-initValue' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.Fieldset-method-initValue' class='name expandable'>initValue</a>( <span class='pre'></span> )<span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'> ...</div><div class='long'>\n<h3 class='pa'>Fires</h3><ul><li>change</li></ul></div></div></div><div id='method-isRecurring' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.Fieldset'>Extensible.form.recurrence.Fieldset</span><br/><a href='source/Fieldset.html#Extensible-form-recurrence-Fieldset-method-isRecurring' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.Fieldset-method-isRecurring' class='name expandable'>isRecurring</a>( <span class='pre'></span> )<span class=\"signature\"></span></div><div class='description'><div class='short'>Return true if the fieldset currently has a recurrence value set, otherwise returns false. ...</div><div class='long'><p>Return true if the fieldset currently has a recurrence value set, otherwise returns false.</p>\n</div></div></div><div id='method-onChange' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.Fieldset'>Extensible.form.recurrence.Fieldset</span><br/><a href='source/Fieldset.html#Extensible-form-recurrence-Fieldset-method-onChange' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.Fieldset-method-onChange' class='name expandable'>onChange</a>( <span class='pre'></span> )<span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'> ...</div><div class='long'>\n<h3 class='pa'>Fires</h3><ul><li>change</li></ul></div></div></div><div id='method-onFrequencyChange' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.Fieldset'>Extensible.form.recurrence.Fieldset</span><br/><a href='source/Fieldset.html#Extensible-form-recurrence-Fieldset-method-onFrequencyChange' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.Fieldset-method-onFrequencyChange' class='name expandable'>onFrequencyChange</a>( <span class='pre'>freq</span> )<span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'> ...</div><div class='long'>\n<h3 class=\"pa\">Parameters</h3><ul><li><span class='pre'>freq</span> : <a href=\"#!/api/Object\" rel=\"Object\" class=\"docClass\">Object</a><div class='sub-desc'></div></li></ul><h3 class='pa'>Fires</h3><ul><li>change</li></ul></div></div></div><div id='method-onStartDateChange' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.Fieldset'>Extensible.form.recurrence.Fieldset</span><br/><a href='source/Fieldset.html#Extensible-form-recurrence-Fieldset-method-onStartDateChange' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.Fieldset-method-onStartDateChange' class='name expandable'>onStartDateChange</a>( <span class='pre'>interval, newDate, oldDate</span> )<span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'> ...</div><div class='long'>\n<h3 class=\"pa\">Parameters</h3><ul><li><span class='pre'>interval</span> : <a href=\"#!/api/Object\" rel=\"Object\" class=\"docClass\">Object</a><div class='sub-desc'></div></li><li><span class='pre'>newDate</span> : <a href=\"#!/api/Object\" rel=\"Object\" class=\"docClass\">Object</a><div class='sub-desc'></div></li><li><span class='pre'>oldDate</span> : <a href=\"#!/api/Object\" rel=\"Object\" class=\"docClass\">Object</a><div class='sub-desc'></div></li></ul><h3 class='pa'>Fires</h3><ul><li><a href=\"#!/api/Extensible.form.recurrence.Fieldset-event-startchange\" rel=\"Extensible.form.recurrence.Fieldset-event-startchange\" class=\"docClass\">startchange</a></li></ul></div></div></div><div id='method-setFrequency' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.Fieldset'>Extensible.form.recurrence.Fieldset</span><br/><a href='source/Fieldset.html#Extensible-form-recurrence-Fieldset-method-setFrequency' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.Fieldset-method-setFrequency' class='name expandable'>setFrequency</a>( <span class='pre'>freq</span> )<span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'> ...</div><div class='long'>\n<h3 class=\"pa\">Parameters</h3><ul><li><span class='pre'>freq</span> : <a href=\"#!/api/Object\" rel=\"Object\" class=\"docClass\">Object</a><div class='sub-desc'></div></li></ul></div></div></div><div id='method-setStartDate' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.Fieldset'>Extensible.form.recurrence.Fieldset</span><br/><a href='source/Fieldset.html#Extensible-form-recurrence-Fieldset-method-setStartDate' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.Fieldset-method-setStartDate' class='name expandable'>setStartDate</a>( <span class='pre'>The</span> ) : <a href=\"#!/api/Extensible.form.recurrence.Fieldset\" rel=\"Extensible.form.recurrence.Fieldset\" class=\"docClass\">Extensible.form.recurrence.Fieldset</a><span class=\"signature\"><span class='chainable' >chainable</span></span></div><div class='description'><div class='short'>Sets the start date of the recurrence pattern ...</div><div class='long'><p>Sets the start date of the recurrence pattern</p>\n<h3 class=\"pa\">Parameters</h3><ul><li><span class='pre'>The</span> : <a href=\"#!/api/Date\" rel=\"Date\" class=\"docClass\">Date</a><div class='sub-desc'><p>new start date</p>\n</div></li></ul><h3 class='pa'>Returns</h3><ul><li><span class='pre'><a href=\"#!/api/Extensible.form.recurrence.Fieldset\" rel=\"Extensible.form.recurrence.Fieldset\" class=\"docClass\">Extensible.form.recurrence.Fieldset</a></span><div class='sub-desc'><p>this</p>\n</div></li></ul></div></div></div><div id='method-setValue' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.Fieldset'>Extensible.form.recurrence.Fieldset</span><br/><a href='source/Fieldset.html#Extensible-form-recurrence-Fieldset-method-setValue' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.Fieldset-method-setValue' class='name expandable'>setValue</a>( <span class='pre'>value</span> )<span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'> ...</div><div class='long'>\n<h3 class=\"pa\">Parameters</h3><ul><li><span class='pre'>value</span> : <a href=\"#!/api/Object\" rel=\"Object\" class=\"docClass\">Object</a><div class='sub-desc'></div></li></ul></div></div></div><div id='method-showOptions' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.Fieldset'>Extensible.form.recurrence.Fieldset</span><br/><a href='source/Fieldset.html#Extensible-form-recurrence-Fieldset-method-showOptions' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.Fieldset-method-showOptions' class='name expandable'>showOptions</a>( <span class='pre'>freq</span> )<span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'> ...</div><div class='long'>\n<h3 class=\"pa\">Parameters</h3><ul><li><span class='pre'>freq</span> : <a href=\"#!/api/Object\" rel=\"Object\" class=\"docClass\">Object</a><div class='sub-desc'></div></li></ul></div></div></div></div></div><div class='members-section'><div class='definedBy'>Defined By</div><h3 class='members-title icon-event'>Events</h3><div class='subsection'><div id='event-startchange' class='member first-child not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.Fieldset'>Extensible.form.recurrence.Fieldset</span><br/><a href='source/Fieldset.html#Extensible-form-recurrence-Fieldset-event-startchange' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.Fieldset-event-startchange' class='name expandable'>startchange</a>( <span class='pre'>this, newDate, oldDate, eOpts</span> )<span class=\"signature\"></span></div><div class='description'><div class='short'>Fires when the start date of the recurrence series is changed ...</div><div class='long'><p>Fires when the start date of the recurrence series is changed</p>\n<h3 class=\"pa\">Parameters</h3><ul><li><span class='pre'>this</span> : <a href=\"#!/api/Extensible.form.recurrence.option.Interval\" rel=\"Extensible.form.recurrence.option.Interval\" class=\"docClass\">Extensible.form.recurrence.option.Interval</a><div class='sub-desc'>\n</div></li><li><span class='pre'>newDate</span> : <a href=\"#!/api/Date\" rel=\"Date\" class=\"docClass\">Date</a><div class='sub-desc'><p>The new start date</p>\n</div></li><li><span class='pre'>oldDate</span> : <a href=\"#!/api/Date\" rel=\"Date\" class=\"docClass\">Date</a><div class='sub-desc'><p>The previous start date</p>\n</div></li><li><span class='pre'>eOpts</span> : <a href=\"#!/api/Object\" rel=\"Object\" class=\"docClass\">Object</a><div class='sub-desc'><p>The options object passed to Ext.util.Observable.addListener.</p>\n\n\n\n</div></li></ul></div></div></div></div></div></div></div>","meta":{}});