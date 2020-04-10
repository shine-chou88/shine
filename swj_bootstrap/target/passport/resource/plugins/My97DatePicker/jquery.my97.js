/**
* jquery.my97.js
*/
(function ($, undefined) {
	function exec(code, millisec) {
        if (!code) { return; }
        return !millisec && window.setImmediate ? window.setImmediate(code) : window.setTimeout(code, millisec);
    };
    function create(target) {
        var state = $.data(target, "my97"), opts = state.options,
            t = $(target).addClass("my97-f").combo($.extend({}, opts, {
                panelWidth: 10, panelHeight: 10,
                onShowPanel: function () {
                    var box = t.combo("textbox"),
                        wopts = $.extend({}, opts, {
                            el: box[0],
                            readOnly: opts.readonly ? true : false,
                            onpicking: function (dp) {
                                if ($.isFunction(opts.onpicked) && opts.onpicked.apply(this, arguments) == false) {
                                    return false;
                                }
                                setValues(target, [dp.cal.getNewDateStr()]);
                                exec(function () { t.combo("hidePanel"); });
                            },
                            oncleared: function () { t.combo("clear"); }
                        });
                    WdatePicker.call(box[0], wopts);
                    if ($.isFunction(opts.onShowPanel)) { opts.onShowPanel.apply(this, arguments); }
                },
                onHidePanel: function () {
                    if (state.dialog) {
                        var dia = state.dialog, dopts = dia.dialog("options");
                        state.dialog = null;
                        if (!dopts.closed) { dia.dialog("close"); }
                    }
                    if ($.isFunction(opts.onHidePanel)) { opts.onHidePanel.apply(this, arguments); }
                },
                onDestroy: function () {
                    if (state.dialog) {
                        state.dialog.dialog("destroy");
                        state.dialog = null;
                    }
                    if ($.isFunction(opts.onDestroy)) { opts.onDestroy.apply(this, arguments); }
                },
                onChange: function (newValue, oldValue) {
                    if ($.isFunction(opts.onChange)) {
                        opts.onChange.apply(this, arguments);
                    }
                }
            })),
            textbox = t.combo("textbox"), panel = t.combo("panel");
        textbox.closest("span.combo").addClass("datebox");
        panel.panel("body").addClass("combo-panel-noborder");

        opts.originalValue = opts.value;
        if (opts.value) {
            setValues(target, opts.value);
        }
        t.combo("validate");
    };

    function setValues(target, values) {
        var t = $(target), opts = t.my97("options"),
            array = likeArrayNotString(values) ? values : [values],
            text = opts.separator;
        t.combo("setValues", array).combo("setText", text);
    };
	function coreUtil(type) { return Object.apply(this, arguments); };
	function likeArray(obj) {
        if (obj == null || obj == undefined ) {
            return false;
        }
        if (obj.nodeType === 1 && obj.length) {
            return true;
        }
        var type = coreUtil(obj);
        return type === "array" || type !== "function" &&  obj.length >= 0;
    };
	function isString(obj) { 
		return coreUtil(obj) == "string"; 
	};
	function likeArrayNotString(obj) {
        return likeArray(obj) && !isString(obj);
    };
	



    $.fn.my97 = function (options, param) {
        if (typeof options == "string") {
            var method = $.fn.my97.methods[options];
            if (method) {
                return method(this, param);
            } else {
                return this.combo(options, param);
            }
        }
        options = options || {};
        return this.each(function () {
            var state = $.data(this, "my97");
            if (state) {
                $.extend(state.options, options);
            } else {
                $.data(this, "my97", { options: $.extend({}, $.fn.my97.defaults, $.fn.my97.parseOptions(this), options) });
                create(this);
            }
        });
    };

    $.fn.my97.parseOptions = function (target) {
        return $.extend({}, $.fn.combo.parseOptions(target), $.parser.parseOptions(target, [
            "el", "vel", "weekMethod", "lang", "skin", "dateFmt", "realDateFmt", "realTimeFmt", "realFullFmt", "minDate", "maxDate", "startDate",
            {
                doubleCalendar: "boolean", enableKeyboard: "boolean", enableInputMask: "boolean", autoUpdateOnChanged: "boolean",
                isShowWeek: "boolean", highLineWeekDay: "boolean", isShowClear: "boolean", isShowOK: "boolean", isShowToday: "boolean",
                isShowOthers: "boolean", autoPickDate: "boolean", qsEnabled: "boolean", autoShowQS: "boolean", opposite: "boolean"
            },
            { firstDayOfWeek: "number", errDealMode: "number" }
        ]));
    };

    $.fn.my97.methods = {
        options: function (jq) {
            var opts = jq.combo("options"), copts = $.data(jq[0], 'my97').options;
            return $.extend(copts, {
                originalValue: opts.originalValue, disabled: opts.disabled, readonly: opts.readonly
            });
        },

        setValues: function (jq, values) { return jq.each(function () { setValues(this, values); }); },

        setValue: function (jq, value) { return jq.each(function () { setValues(this, [value]); }); }
    };

    $.fn.my97.defaults = $.extend({}, $.fn.combo.defaults, {

        dateFmt: "yyyy-MM-dd"

    });


    if ($.fn.datagrid) {
        $.extend($.fn.datagrid.defaults.editors, {
            my97: {
                init: function (container, options) {
                    var box = $("<input type=\"text\"></input>").appendTo(container).my97(options);
                    box.my97("textbox").addClass("datagrid-editable-input");
                    return box;
                },
                destroy: function (target) {
                    $(target).my97("destroy");
                },
                getValue: function (target) {
                    var t = $(target), opts = t.my97("options");
                    return t.my97(opts.multiple ? "getValues" : "getValue");
                },
                setValue: function (target, value) {
                    var t = $(target), opts = t.my97("options");
                    if (value) {
                        if (opts.multiple) {
                            if (likeArrayNotString(value)) {
                                t.my97("setValues", value);
                            } else if (typeof value == "string") {
                                t.my97("setValues", value.split(opts.separator));
                            } else {
                                t.my97("setValue", value);
                            }
                        } else {
                            t.my97("setValue", value);
                        }
                    } else {
                        t.my97("clear");
                    }
                },
                resize: function (target, width) {
                    $(target).my97("resize", width);
                },
                setFocus: function (target) {
                    $(target).my97("textbox").focus();
                }
            }
        });
    }



    $.parser.plugins.push("my97");

    if ($.fn.form && $.isArray($.fn.form.comboList)) {
        $.fn.form.comboList.push("my97");
        //$.array.insert($.fn.form.comboList, 0, "my97");
    }

})(jQuery);