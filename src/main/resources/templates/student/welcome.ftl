<!DOCTYPE html>
<html lang="en" >

<head>
  <meta charset="UTF-8">
  <title>HTML5/CSS3圆盘时钟动画</title>

      <link rel="stylesheet" href="${rc.contextPath}/static/css/studentIndex.css">

</head>

<body>

		<div class="clock" onclick="playMusic();">
        <div class="hourHand"></div>
        <div class="minuteHand"></div>
        <div class="secondHand"></div>
        <div class="center"></div>
        <div class="time"></div>
			<ul>
				<li><span>1</span></li>
				<li><span>2</span></li>
				<li><span>3</span></li>
				<li><span>4</span></li>
				<li><span>5</span></li>
				<li><span>6</span></li>
				<li><span>7</span></li>
				<li><span>8</span></li>
				<li><span>9</span></li>
				<li><span>10</span></li>
				<li><span>11</span></li>
				<li><span>12</span></li>
			</ul>
		</div>



    <script  src="${rc.contextPath}/static/js/studentIndex.js"></script>

	<div style="text-align:center;clear:both">
	        <audio class="audio" controls style="float:right;">
                <source id="music" src="${rc.contextPath}/static/sounds/dida.mp3" type="audio/mpeg">
            </audio>
    </div>

</body>
</html>
