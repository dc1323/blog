/**
 *
 * 项目核心Js类，负责项目前端模板方面的初始化等操作
 */
var editor = null, simplemde = null;
var zhyd = window.zhyd || {
    wangEditor: {
        _instance: window.wangEditor,
        defaultConfig: {
            container: "#editor",
            textareaName: "content",
            uploadUrl: "",
            uploadFileName: "file",
            uploadType: "",
            customCss: {},
            content: ""

        },
        init: function (options) {
            var config = $.extend(zhyd.wangEditor.defaultConfig, options);
            var E = window.wangEditor;
            editor = new E(config.container);
            // 配置编辑器 start
            // 关闭粘贴样式的过滤
            editor.customConfig.pasteFilterStyle = false;
            editor.customConfig.zIndex = 100;
            if (config.textareaName) {
                $('<textarea class="wangeditor-textarea" id="' + config.textareaName + '" name="' + config.textareaName + '" style="display: none" required="required"></textarea>').insertAfter($(config.container));
            }
            var $contentBox = $('textarea[name=' + config.textareaName + ']');
            editor.customConfig.onchange = function (html) {
                // 监控变化，同步更新到 textarea
                $contentBox.val(html);
            };
            editor.html($("#editor").html());
            // 注册上传文件插件
            zhyd.wangEditor.plugins.registerUpload(editor, config.uploadUrl, config.uploadFileName, config.uploadType, function (result, curEditor) {
                // 图片上传并返回结果，自定义插入图片的事件（而不是编辑器自动插入图片！！！）
                // insertImg 是插入图片的函数，editor 是编辑器对象，result 是服务器端返回的结果
                if (result.status == 200) {
                    var imgFullPath = result.data;
                    curEditor.txt.append('<img src="' + imgFullPath + '" alt="" style="max-width: 100%;height: auto;border-radius: 6px;"/>');
                    // 解决上传完图片如果未进行其他操作，则不会触发编辑器的“change”事件，导致实际文章内容中缺少最后上传的图片文件 2018-07-13
                    $contentBox.val(editor.txt.html());
                } else {
                    $.alert.error(result.message);
                }
            });
            // 配置编辑器 end
            editor.create();
            // 注册全屏插件
            zhyd.wangEditor.plugins.registerFullscreen(config.container);
            // 注册图片资源库
            zhyd.wangEditor.plugins.registerMaterial(editor, $contentBox);

            if (config.customCss) {
                // 自定义编辑器的样式
                for (var key in config.customCss) {
                    var value = config.customCss[key];
                    editor.$textContainerElem.css(key, value);
                }
            }
        },
        plugins: {
            registerFullscreen: function () {
                var E = zhyd.wangEditor._instance;
                // 全屏插件
                E.fullscreen = {
                    init: function (editorBox) {
                        $(editorBox + " .w-e-toolbar").append('<div class="w-e-menu"><a class="_wangEditor_btn_fullscreen" href="###" onclick="window.wangEditor.fullscreen.toggleFullscreen(\'' + editorBox + '\')" data-toggle="tooltip" data-placement="bottom" title data-original-title="全屏编辑"><i class="fa fa-expand"></i></a></div>')
                    },
                    toggleFullscreen: function (editorSelector) {
                        $(editorSelector).toggleClass('fullscreen-editor');
                        var $a = $(editorSelector + ' ._wangEditor_btn_fullscreen');
                        var $i = $a.find("i:first-child");
                        if ($i.hasClass("fa-expand")) {
                            $a.attr("data-original-title", "退出全屏");
                            $i.removeClass("fa-expand").addClass("fa-compress")
                        } else {
                            $a.attr("data-original-title", "全屏编辑");
                            $i.removeClass("fa-compress").addClass("fa-expand")
                        }
                    }
                };

                // 初始化全屏插件
                var n = arguments.length;
                for (var i = 0; i < n; i++) {
                    E.fullscreen.init(arguments[i]);
                }
            },
            registerUpload: function (editor, uploadUrl, uploadFileName, uploadType, callback) {
                if (uploadUrl) {
                    // 上传图片到服务器
                    editor.customConfig.uploadImgServer = uploadUrl;
                    editor.customConfig.uploadFileName = uploadFileName;
                    // 将图片大小限制为 50M
                    editor.customConfig.uploadImgMaxSize = 50 * 1024 * 1024;
                    // 超时时间
                    editor.customConfig.uploadImgTimeout = 10000;
                    // 自定义上传参数
                    editor.customConfig.uploadImgParams = {
                        // 如果版本 <=v3.1.0 ，属性值会自动进行 encode ，此处无需 encode
                        // 如果版本 >=v3.1.1 ，属性值不会自动 encode ，如有需要自己手动 encode
                        uploadType: uploadType
                    };
                    editor.customConfig.customAlert = function (msg) {
                        $.alert.error(msg);
                    };
                    editor.customConfig.uploadImgHooks = {
                        error: function (xhr, editor) {
                            $.alert.error("图片上传出错");
                        },
                        timeout: function (xhr, editor) {
                            $.alert.error("请求超时");
                        },
                        customInsert: function (insertImg, result, editor) {
                            if (callback) {
                                callback(result, editor);
                            } else {
                                console.log('upload callback：' + insertImg, result, editor);
                            }
                        }
                    };
                }
            },
            registerMaterial: function (editor, $contentBox) {
                $("div[id^='w-e-img']").unbind("click").click(function () {
                    setTimeout(function () {
                        // 删掉原来的input#file，防止触发原选中文件的函数
                        $(".w-e-up-img-container").find("input").remove();
                        // 重新绑定选中图片按钮的事件
                        $("div[id^='up-trigger'], div.w-e-up-btn").unbind("click").click(function () {
                            $.modal.material.open({multiSelect: true, selectable: 10}, function (selectedImageUrls) {
                                if(!selectedImageUrls) {
                                    return false;
                                }
                                for(var i = 0; i < selectedImageUrls.length; i ++){
                                    editor.txt.append('<img src="' + selectedImageUrls[i] + '" alt="" style="max-width: 100%;height: auto;border-radius: 6px;"/>');
                                    $contentBox.val(editor.txt.html());
                                }
                            })
                        })
                    }, 50);
                })
            }
        }
    }
};