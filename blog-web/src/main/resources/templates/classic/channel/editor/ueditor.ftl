<script type="text/javascript" charset="utf-8" src="${base}/dist/js/ueditor/ueditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="${base}/dist/js/ueditor/ueditor.all.min.js"> </script>
<script type="text/javascript" charset="utf-8" src="${base}/dist/js/ueditor/lang/zh-cn/zh-cn.js"></script>

<script id="editor" name="content" type="text/plain" style="width:100%;height:500px;">${view.content?html}</script>

<script type="text/javascript">
    var ue = UE.getEditor('editor');
    <#if view.content?exists>
         UM.getEditor('editor').setContent(${view.content?html}, false);
    </#if>
</script>
