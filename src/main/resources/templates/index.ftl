<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
    <link rel="stylesheet" href="https://g.alicdn.com/de/prismplayer/2.3.5/skins/default/aliplayer-min.css"/>
    <script type="text/javascript" src="https://g.alicdn.com/de/prismplayer/2.3.5/aliplayer-min.js"></script>
    <script type="text/javascript">
        document.write("<div id = 'J_prismPlayer' style='width: 1360px;height: 720px;' class='prism-player'></div >");
        window.onload = function () {
            new Aliplayer({
                id: "J_prismPlayer",
                autoplay: true,
                width: "1920px",
                height: "1280px",
                vid: "${videoInfo.videoMeta.videoId}",
                playauth: "${videoInfo.playAuth}"
            });
        };
    </script>
</head>
<body>

</body>
</html>