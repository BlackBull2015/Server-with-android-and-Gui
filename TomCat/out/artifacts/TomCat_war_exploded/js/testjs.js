var chartData = [];
var chartData2 = [];
generateChartData();
generateChartData2();

var chart1 = AmCharts.makeChart("chart1div", {
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

var chart2 = AmCharts.makeChart("chart2div", {
    type: "serial",
    dataProvider: chartData2,
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
function generateChartData2() {

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

        chartData2.push({
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
    var chartCursor = chart1.chartCursor;
    var chartCursor2 = chart2.chartCursor;

    if (document.getElementById("rb1").checked) {
        chartCursor.pan = false;
        chartCursor.zoomable = true;

    } else {
        chartCursor.pan = true;
    }
    if (document.getElementById("rb3").checked) {
        chartCursor2.pan = false;
        chartCursor2.zoomable = true;

    } else {
        chartCursor2.pan = true;
    }
    chart1.validateNow();
    chart2.validateNow();
}