Ext.data.JsonP.Extensible_form_recurrence_FrequencyCombo({"tagname":"class","name":"Extensible.form.recurrence.FrequencyCombo","autodetected":{"aliases":true,"alternateClassNames":true,"extends":true,"mixins":true,"requires":true,"uses":true,"members":true,"code_type":true},"files":[{"filename":"FrequencyCombo.js","href":"FrequencyCombo.html#Extensible-form-recurrence-FrequencyCombo"}],"aliases":{"widget":["extensible.recurrence-frequency"]},"alternateClassNames":[],"extends":"Ext.form.field.ComboBox","mixins":[],"requires":["Ext.data.ArrayStore","Extensible.form.recurrence.Parser"],"uses":[],"members":[{"name":"frequencyOptions","tagname":"cfg","owner":"Extensible.form.recurrence.FrequencyCombo","id":"cfg-frequencyOptions","meta":{}},{"name":"cls","tagname":"property","owner":"Extensible.form.recurrence.FrequencyCombo","id":"property-cls","meta":{"private":true}},{"name":"displayField","tagname":"property","owner":"Extensible.form.recurrence.FrequencyCombo","id":"property-displayField","meta":{"private":true}},{"name":"fieldLabel","tagname":"property","owner":"Extensible.form.recurrence.FrequencyCombo","id":"property-fieldLabel","meta":{"private":true}},{"name":"forceSelection","tagname":"property","owner":"Extensible.form.recurrence.FrequencyCombo","id":"property-forceSelection","meta":{"private":true}},{"name":"queryMode","tagname":"property","owner":"Extensible.form.recurrence.FrequencyCombo","id":"property-queryMode","meta":{"private":true}},{"name":"triggerAction","tagname":"property","owner":"Extensible.form.recurrence.FrequencyCombo","id":"property-triggerAction","meta":{"private":true}},{"name":"valueField","tagname":"property","owner":"Extensible.form.recurrence.FrequencyCombo","id":"property-valueField","meta":{"private":true}},{"name":"initComponent","tagname":"method","owner":"Extensible.form.recurrence.FrequencyCombo","id":"method-initComponent","meta":{"private":true}},{"name":"onSelect","tagname":"method","owner":"Extensible.form.recurrence.FrequencyCombo","id":"method-onSelect","meta":{"private":true}},{"name":"frequencychange","tagname":"event","owner":"Extensible.form.recurrence.FrequencyCombo","id":"event-frequencychange","meta":{}}],"code_type":"ext_define","id":"class-Extensible.form.recurrence.FrequencyCombo","short_doc":"The widget used to choose the frequency of recurrence. ...","component":false,"superclasses":["Ext.form.field.ComboBox"],"subclasses":[],"mixedInto":[],"parentMixins":[],"html":"<div><pre class=\"hierarchy\"><h4>Hierarchy</h4><div class='subclass first-child'>Ext.form.field.ComboBox<div class='subclass '><strong>Extensible.form.recurrence.FrequencyCombo</strong></div></div><h4>Requires</h4><div class='dependency'>Ext.data.ArrayStore</div><div class='dependency'><a href='#!/api/Extensible.form.recurrence.Parser' rel='Extensible.form.recurrence.Parser' class='docClass'>Extensible.form.recurrence.Parser</a></div><h4>Files</h4><div class='dependency'><a href='source/FrequencyCombo.html#Extensible-form-recurrence-FrequencyCombo' target='_blank'>FrequencyCombo.js</a></div></pre><div class='doc-contents'><p>The widget used to choose the frequency of recurrence. While this could be created\nas a standalone widget, it is typically created automatically as part of a\n<a href=\"#!/api/Extensible.form.recurrence.Fieldset\" rel=\"Extensible.form.recurrence.Fieldset\" class=\"docClass\">Extensible.form.recurrence.Fieldset</a> and does not normally need to be configured directly.</p>\n</div><div class='members'><div class='members-section'><div class='definedBy'>Defined By</div><h3 class='members-title icon-cfg'>Config options</h3><div class='subsection'><div id='cfg-frequencyOptions' class='member first-child not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.FrequencyCombo'>Extensible.form.recurrence.FrequencyCombo</span><br/><a href='source/FrequencyCombo.html#Extensible-form-recurrence-FrequencyCombo-cfg-frequencyOptions' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.FrequencyCombo-cfg-frequencyOptions' class='name expandable'>frequencyOptions</a> : <a href=\"#!/api/Array\" rel=\"Array\" class=\"docClass\">Array</a><span class=\"signature\"></span></div><div class='description'><div class='short'>An array of arrays, each containing the name/value pair that defines a recurring\nfrequency option supported by the fr...</div><div class='long'><p>An array of arrays, each containing the name/value pair that defines a recurring\nfrequency option supported by the frequency combo. This array is bound to the underlying\nstore to provide the combo list items. The string descriptions\nare defined in the <a href=\"#!/api/Extensible.form.recurrence.Parser-cfg-strings\" rel=\"Extensible.form.recurrence.Parser-cfg-strings\" class=\"docClass\">Extensible.form.recurrence.Parser.strings</a> config.\nDefaults to:</p>\n\n<pre><code>[\n    ['NONE', 'Does not repeat'],\n    ['DAILY', 'Daily'],\n    ['WEEKDAYS', 'Every weekday (Mon-Fri)'],\n    ['WEEKLY', 'Weekly'],\n    ['MONTHLY', 'Monthly'],\n    ['YEARLY', 'Yearly']\n]\n</code></pre>\n</div></div></div></div></div><div class='members-section'><div class='definedBy'>Defined By</div><h3 class='members-title icon-property'>Properties</h3><div class='subsection'><div id='property-cls' class='member first-child not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.FrequencyCombo'>Extensible.form.recurrence.FrequencyCombo</span><br/><a href='source/FrequencyCombo.html#Extensible-form-recurrence-FrequencyCombo-property-cls' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.FrequencyCombo-property-cls' class='name expandable'>cls</a> : <a href=\"#!/api/String\" rel=\"String\" class=\"docClass\">String</a><span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'> ...</div><div class='long'>\n<p>Defaults to: <code>&#39;extensible-recur-frequency&#39;</code></p></div></div></div><div id='property-displayField' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.FrequencyCombo'>Extensible.form.recurrence.FrequencyCombo</span><br/><a href='source/FrequencyCombo.html#Extensible-form-recurrence-FrequencyCombo-property-displayField' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.FrequencyCombo-property-displayField' class='name expandable'>displayField</a> : <a href=\"#!/api/String\" rel=\"String\" class=\"docClass\">String</a><span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'> ...</div><div class='long'>\n<p>Defaults to: <code>&#39;pattern&#39;</code></p></div></div></div><div id='property-fieldLabel' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.FrequencyCombo'>Extensible.form.recurrence.FrequencyCombo</span><br/><a href='source/FrequencyCombo.html#Extensible-form-recurrence-FrequencyCombo-property-fieldLabel' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.FrequencyCombo-property-fieldLabel' class='name expandable'>fieldLabel</a> : <a href=\"#!/api/String\" rel=\"String\" class=\"docClass\">String</a><span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'> ...</div><div class='long'>\n<p>Defaults to: <code>&#39;Repeats&#39;</code></p></div></div></div><div id='property-forceSelection' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.FrequencyCombo'>Extensible.form.recurrence.FrequencyCombo</span><br/><a href='source/FrequencyCombo.html#Extensible-form-recurrence-FrequencyCombo-property-forceSelection' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.FrequencyCombo-property-forceSelection' class='name expandable'>forceSelection</a> : <a href=\"#!/api/Boolean\" rel=\"Boolean\" class=\"docClass\">Boolean</a><span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'> ...</div><div class='long'>\n<p>Defaults to: <code>true</code></p></div></div></div><div id='property-queryMode' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.FrequencyCombo'>Extensible.form.recurrence.FrequencyCombo</span><br/><a href='source/FrequencyCombo.html#Extensible-form-recurrence-FrequencyCombo-property-queryMode' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.FrequencyCombo-property-queryMode' class='name expandable'>queryMode</a> : <a href=\"#!/api/String\" rel=\"String\" class=\"docClass\">String</a><span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'> ...</div><div class='long'>\n<p>Defaults to: <code>&#39;local&#39;</code></p></div></div></div><div id='property-triggerAction' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.FrequencyCombo'>Extensible.form.recurrence.FrequencyCombo</span><br/><a href='source/FrequencyCombo.html#Extensible-form-recurrence-FrequencyCombo-property-triggerAction' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.FrequencyCombo-property-triggerAction' class='name expandable'>triggerAction</a> : <a href=\"#!/api/String\" rel=\"String\" class=\"docClass\">String</a><span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'> ...</div><div class='long'>\n<p>Defaults to: <code>&#39;all&#39;</code></p></div></div></div><div id='property-valueField' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.FrequencyCombo'>Extensible.form.recurrence.FrequencyCombo</span><br/><a href='source/FrequencyCombo.html#Extensible-form-recurrence-FrequencyCombo-property-valueField' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.FrequencyCombo-property-valueField' class='name expandable'>valueField</a> : <a href=\"#!/api/String\" rel=\"String\" class=\"docClass\">String</a><span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'> ...</div><div class='long'>\n<p>Defaults to: <code>&#39;id&#39;</code></p></div></div></div></div></div><div class='members-section'><div class='definedBy'>Defined By</div><h3 class='members-title icon-method'>Methods</h3><div class='subsection'><div id='method-initComponent' class='member first-child not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.FrequencyCombo'>Extensible.form.recurrence.FrequencyCombo</span><br/><a href='source/FrequencyCombo.html#Extensible-form-recurrence-FrequencyCombo-method-initComponent' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.FrequencyCombo-method-initComponent' class='name expandable'>initComponent</a>( <span class='pre'></span> )<span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'> ...</div><div class='long'>\n</div></div></div><div id='method-onSelect' class='member  not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.FrequencyCombo'>Extensible.form.recurrence.FrequencyCombo</span><br/><a href='source/FrequencyCombo.html#Extensible-form-recurrence-FrequencyCombo-method-onSelect' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.FrequencyCombo-method-onSelect' class='name expandable'>onSelect</a>( <span class='pre'>combo, records</span> )<span class=\"signature\"><span class='private' >private</span></span></div><div class='description'><div class='short'> ...</div><div class='long'>\n<h3 class=\"pa\">Parameters</h3><ul><li><span class='pre'>combo</span> : <a href=\"#!/api/Object\" rel=\"Object\" class=\"docClass\">Object</a><div class='sub-desc'></div></li><li><span class='pre'>records</span> : <a href=\"#!/api/Object\" rel=\"Object\" class=\"docClass\">Object</a><div class='sub-desc'></div></li></ul><h3 class='pa'>Fires</h3><ul><li><a href=\"#!/api/Extensible.form.recurrence.FrequencyCombo-event-frequencychange\" rel=\"Extensible.form.recurrence.FrequencyCombo-event-frequencychange\" class=\"docClass\">frequencychange</a></li></ul></div></div></div></div></div><div class='members-section'><div class='definedBy'>Defined By</div><h3 class='members-title icon-event'>Events</h3><div class='subsection'><div id='event-frequencychange' class='member first-child not-inherited'><a href='#' class='side expandable'><span>&nbsp;</span></a><div class='title'><div class='meta'><span class='defined-in' rel='Extensible.form.recurrence.FrequencyCombo'>Extensible.form.recurrence.FrequencyCombo</span><br/><a href='source/FrequencyCombo.html#Extensible-form-recurrence-FrequencyCombo-event-frequencychange' target='_blank' class='view-source'>view source</a></div><a href='#!/api/Extensible.form.recurrence.FrequencyCombo-event-frequencychange' class='name expandable'>frequencychange</a>( <span class='pre'>combo, value, eOpts</span> )<span class=\"signature\"></span></div><div class='description'><div class='short'>Fires when a frequency list item is selected. ...</div><div class='long'><p>Fires when a frequency list item is selected.</p>\n<h3 class=\"pa\">Parameters</h3><ul><li><span class='pre'>combo</span> : Extensible.form.recurrence.Combo<div class='sub-desc'><p>This combo box</p>\n</div></li><li><span class='pre'>value</span> : <a href=\"#!/api/String\" rel=\"String\" class=\"docClass\">String</a><div class='sub-desc'><p>The selected frequency value (one of the names\nfrom <a href=\"#!/api/Extensible.form.recurrence.FrequencyCombo-cfg-frequencyOptions\" rel=\"Extensible.form.recurrence.FrequencyCombo-cfg-frequencyOptions\" class=\"docClass\">frequencyOptions</a>, e.g. 'DAILY')</p>\n</div></li><li><span class='pre'>eOpts</span> : <a href=\"#!/api/Object\" rel=\"Object\" class=\"docClass\">Object</a><div class='sub-desc'><p>The options object passed to Ext.util.Observable.addListener.</p>\n\n\n\n</div></li></ul></div></div></div></div></div></div></div>","meta":{}});