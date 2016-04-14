<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN" "http://www.w3.org/TR/html4/strict.dtd">
<html>

<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>amCharts examples</title>
    <link rel="stylesheet" href="style.css" type="text/css">
    <script src="amcharts/amcharts.js" type="text/javascript"></script>
    <script src="amcharts/serial.js" type="text/javascript"></script>
    <?php
    include 'creategraphs.php'
    ?>

</head>

<body>
<div id="chart1div" style="width: 100%; height: 400px;"></div>
<div style="margin-left:35px;">
    <input type="radio" checked="true" name="group" id="rb1" onclick="setPanSelect()">Select
    <input type="radio" name="group" id="rb2" onclick="setPanSelect()">Pan
</div>
<div id="chart2div" style="width: 100%; height: 400px;"></div>
<div style="margin-left:35px;">
    <input type="radio" checked="true" name="group" id="rb3" onclick="setPanSelect()">Select
    <input type="radio" name="group" id="rb4" onclick="setPanSelect()">Pan
</div>
</body>

</html>