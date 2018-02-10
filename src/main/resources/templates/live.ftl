<!DOCTYPE HTML>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="IE=edge">
    <meta name="viewport"
          content="width=device-width, height=device-height, initial-scale=1, maximum-scale=1, minimum-scale=1, user-scalable=no"/>
    <title>Aliplayer在线配置</title>
    <link rel="stylesheet" href="//g.alicdn.com/de/prismplayer/2.5.0/skins/default/aliplayer-min.css"/>
    <script type="text/javascript" src="//g.alicdn.com/de/prismplayer/2.5.0/aliplayer-min.js"></script>
</head>
<body>
<div class="prism-player" id="J_prismPlayer"></div>
<script>
    var player = new Aliplayer({
                id: "J_prismPlayer",
                autoplay: true,
                isLive: true,
                playsinline: true,
                width: "800px",
                height: "400px",
                controlBarVisibility: "always",
                useH5Prism: false,
                useFlashPrism: false,
                source: "rtmp://live.jypj.org/AppName/StreamName?auth_key=1518158508-0-0-a11ce550a1fbf9183b82d1726f79674b",
                cover: ""
            }, function (player) {
                console.log("播放器创建了。");
            }
    );
</script>
</body>
</html>