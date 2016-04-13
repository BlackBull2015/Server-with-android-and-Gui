<?php ?>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="stylesheet" href="style.css" type="text/css">
        <script src="amcharts/amcharts.js" type="text/javascript"></script>

        <script src="amcharts/serial.js" type="text/javascript"></script>
    <title>Scrolling Nav - Start Bootstrap Template</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/scrolling-nav.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

    <script>
        var chartData = [];
        generateChartData();

        var chart1 = AmCharts.makeChart("chartdiv", {
            type: "serial",
            dataProvider: chartData,
            categoryField: "Location",
            categoryAxis: {
                //   parseDates: true,
                gridAlpha: 0.15,
                minorGridEnabled: true,
                axisColor: "#DADADA"
            },
            valueAxes: [{
                axisAlpha: 0.2,
                id: "v1"
            }],
            graphs: [{
                title: "red line",
                id: "g1",
                valueAxis: "v1",
                valueField: "Lin1",
                //  bullet: "round",
                bulletBorderColor: "#FFFFFF",
                bulletBorderAlpha: 1,
                lineThickness: 2,
                lineColor: "#b5030d",
                // negativeLineColor: "#0352b5",
                balloonText: "[[date]]<br><b><span style='font-size:14px;'>XAxis: [[value]]</span></b>"
            },{
                title: "blue line",
                id: "g2",
                valueAxis: "v1",
                valueField: "Lin2",
                temp:"Lin1",
                bullet: "round",
                bulletBorderColor: "#FFFFFF",
                bulletBorderAlpha: 1,
                lineThickness: 2,
                lineColor: "#0352b5",
                //  negativeLineColor: "#0352b5",
                balloonText: "[[date]]<br><b><span style='font-size:14px;'>YAxis: [[value]]</span></b><br><b><span style='font-size:14px;'>Temp: [[Lin1]]</span></b>"

            },{
                title: "blue line",
                id: "g3",
                valueAxis: "v1",
                valueField: "Lin3",
                bullet: "round",
                bulletBorderColor: "#FFFFFF",
                bulletBorderAlpha: 1,
                lineThickness: 2,
                lineColor: "#FFFF00",
                //  negativeLineColor: "#0352b5",
                balloonText: "[[date]]<br><b><span style='font-size:14px;'>ZAxis: [[value]]</span></b>"

            }],
            chartCursor: {
                fullWidth:true,
                cursorAlpha:0.1
            },
            chartScrollbar: {
                scrollbarHeight: 40,
                color: "#FFFFFF",
                autoGridCount: true,
                graph: "g1",
                graph: "g2",
                graph: "g3",
            },

            mouseWheelZoomEnabled:true
        });

        var chart2 = AmCharts.makeChart("chartdiv", {
            type: "serial",
            dataProvider: chartData,
            categoryField: "Location",
            categoryAxis: {
                //   parseDates: true,
                gridAlpha: 0.15,
                minorGridEnabled: true,
                axisColor: "#DADADA"
            },
            valueAxes: [{
                axisAlpha: 0.2,
                id: "v1"
            }],
            graphs: [{
                title: "red line",
                id: "g1",
                valueAxis: "v1",
                valueField: "Lin1",
                //  bullet: "round",
                bulletBorderColor: "#FFFFFF",
                bulletBorderAlpha: 1,
                lineThickness: 2,
                lineColor: "#b5030d",
                // negativeLineColor: "#0352b5",
                balloonText: "[[date]]<br><b><span style='font-size:14px;'>XAxis: [[value]]</span></b>"
            },{
                title: "blue line",
                id: "g2",
                valueAxis: "v1",
                valueField: "Lin2",
                temp:"Lin1",
                bullet: "round",
                bulletBorderColor: "#FFFFFF",
                bulletBorderAlpha: 1,
                lineThickness: 2,
                lineColor: "#0352b5",
                //  negativeLineColor: "#0352b5",
                balloonText: "[[date]]<br><b><span style='font-size:14px;'>YAxis: [[value]]</span></b><br><b><span style='font-size:14px;'>Temp: [[Lin1]]</span></b>"

            },{
                title: "blue line",
                id: "g3",
                valueAxis: "v1",
                valueField: "Lin3",
                bullet: "round",
                bulletBorderColor: "#FFFFFF",
                bulletBorderAlpha: 1,
                lineThickness: 2,
                lineColor: "#FFFF00",
                //  negativeLineColor: "#0352b5",
                balloonText: "[[date]]<br><b><span style='font-size:14px;'>ZAxis: [[value]]</span></b>"

            }],
            chartCursor: {
                fullWidth:true,
                cursorAlpha:0.1
            },
            chartScrollbar: {
                scrollbarHeight: 40,
                color: "#FFFFFF",
                autoGridCount: true,
                graph: "g1",
                graph: "g2",
                graph: "g3",
            },

            mouseWheelZoomEnabled:true
        });

        chart1.addListener("dataUpdated", zoomChart);
        chart2.addListener("dataUpdated", zoomChart);


        // generate some random data, quite different range
        function generateChartData() {

            for (var i = 0; i < 500; i++) {
                // we create date objects here. In your data, you can have date strings
                // and then set format of your dates using chart.dataDateFormat property,
                // however when possible, use date objects, as this will speed up chart rendering.
                var newDate =  AmCharts.stringToDate("2016-04-04-16:47:03", "YYYY-MM-DD-JJ:NN:SS");

                var line1 = Math.round(Math.random() * 40) - 20;
                var line2 = Math.round(Math.random() * 40) - 20;
                var line3 = Math.round(Math.random() * 40) - 20;
                var tmp = Math.round(Math.random() * 40) - 20;
                var Loc = i;

                chartData.push({
                    date: newDate,
                    Lin1: line1,
                    Lin2: line2,
                    Lin3: line3,
                    temp: tmp,
                    Location: Loc
                });
            }
        }

        // this method is called when chart is first inited as we listen for "dataUpdated" event
        function zoomChart() {
            // different zoom methods can be used - zoomToIndexes, zoomToDates, zoomToCategoryValues
            chart1.zoomToIndexes(chartData.length - 40, chartData.length - 1);
            chart2.zoomToIndexes(chartData.length - 40, chartData.length - 1);
        }

        // changes cursor mode from pan to select
        function setPanSelect() {
            var chartCursor = chart.chartCursor;

            if (document.getElementById("rb1").checked) {
                chartCursor.pan = false;
                chartCursor.zoomable = true;

            } else {
                chartCursor.pan = true;
            }
            chart.validateNow();
        }
    </script>
</head>

<!-- The #page-top ID is part of the scrolling feature - the data-spy and data-target are part of the built-in Bootstrap scrollspy function -->

<body id="page-top" data-spy="scroll" data-target=".navbar-fixed-top">

<!-- Navigation -->
<nav class="navbar navbar-default navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header page-scroll">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-ex1-collapse">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand page-scroll" href="#page-top">Start Bootstrap</a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse navbar-ex1-collapse">
            <ul class="nav navbar-nav">
                <!-- Hidden li included to remove active class from about link when scrolled up past about section -->
                <li class="hidden">
                    <a class="page-scroll" href="#page-top"></a>
                </li>
                <li>
                    <a class="page-scroll" href="#about">About</a>
                </li>
                <li>
                    <a class="page-scroll" href="#services">Services</a>
                </li>
                <li>
                    <a class="page-scroll" href="#contact">Contact</a>
                </li>
            </ul>
        </div>
        <!-- /.navbar-collapse -->
    </div>
    <!-- /.container -->
</nav>

<!-- Intro Section -->
<section id="intro" class="intro-section">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <h1>Scrolling Nav</h1>
                <p><strong>Usage Instructions:</strong> Make sure to include the <code>scrolling-nav.js</code>, <code>jquery.easing.min.js</code>, and <code>scrolling-nav.css</code> files. To make a link smooth scroll to another section on the page, give the link the <code>.page-scroll</code> class and set the link target to a corresponding ID on the page.</p>
                <a class="btn btn-default page-scroll" href="#about">Click Me to Scroll Down!</a>
            </div>
        </div>
    </div>

</section>

<!-- About Section -->
<section id="about" class="about-section">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <h1>About Section</h1>
            </div>
        </div>
    </div>
</section>

<!-- Services Section -->
<section id="services" class="services-section">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                              <div id="chart1.write("chart1div");" style="width: 100%; height: 400px;"></div>
                sadffgfafdas
                <?php
                echo "hello there";
                ?>
                <h1>Services Section</h1>
            </div>
        </div>
    </div>
</section>

<!-- Contact Section -->
<section id="contact" class="contact-section">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <div id="chart2.write("chart2div");" style="width: 100%; height: 400px;"></div>
                <h1>Contact Section</h1>
            </div>
        </div>
    </div>
</section>

<!-- jQuery -->
<script src="js/jquery.js"></script>

<!-- Bootstrap Core JavaScript -->
<script src="js/bootstrap.min.js"></script>

<!-- Scrolling Nav JavaScript -->
<script src="js/jquery.easing.min.js"></script>
<script src="js/scrolling-nav.js"></script>

</body>

</html>
