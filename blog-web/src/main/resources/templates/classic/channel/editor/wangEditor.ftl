<script src="https://unpkg.com/wangeditor@3.1.1/release/wangEditor.min.js"></script>
<script src="${base}/dist/js/modules/core.js"></script>

<div id="editor" style="width: 100%;height: 450px;">${view.content?html}</div>

<script type="text/javascript">
    $(function () {
        zhyd.wangEditor.init({
            container: "#editor",
            textareaName: "content",
            uploadUrl: "/api/uploadFile",
            uploadFileName: "file",
            uploadType: "article",
            customCss: {
                "overflow-y": "scroll",
                "height": "100%",
                "max-height": "500px",
                "background-color": "#FFF"
            }
        });
    });
</script>
