<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>

    <#include "${ctx}/public/common.ftl"/>

</head>
<body>
<fieldset class="layui-elem-field layui-field-title" style="margin-top: 30px;">
    <legend>拖拽上传</legend>
</fieldset>

<div class="layui-upload-drag" id="test10">
    <i class="layui-icon"></i>
    <p>点击上传，或将文件拖拽到此处</p>
</div>

<p style="margin-top:20px " id="fp">


</p>
<script type="text/javascript">
    //拖拽上传
    layui.upload.render({
        elem: '#test10',
        accept: "video",
        before: function (obj) {
            layerUtil.loading("正在上传....");
        },
        url: '${ctx}/upload',
        done: function (res) {
            layerUtil.closeAll();
            if (res.code == 0) {
                layerUtil.success("文件上传成功");
                $("#fp").text(res.data.src)
            }
        }
    });

</script>
</body>
</html>