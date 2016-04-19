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
    <title>SSP Bootstrap</title>

    <!-- Bootstrap Core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="css/scrolling-nav.css" rel="stylesheet">
    <!--Footer CSS  and linked in and github     -->
    <link rel="stylesheet" href="css/footer-distributed-with-address-and-phones.css">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    <?php
    include 'creategraphs.php';
    ?>

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
            <a class="navbar-brand page-scroll" href="#page-top">Welcome To SSP</a>
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
                    <a class="page-scroll" href="#accgrp">Accelerometer graph</a>
                </li>
                <li>
                    <a class="page-scroll" href="#maggrp">Magnetometer Graph</a>
                </li>
                <li>
                    <a class="page-scroll" href="#credits">Credits</a>
                </li>
                <li>
                    <a class="page-scroll" href="#Contact">Contact Us</a>
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
                <h1>SSP</h1>
                <p><strong>Innovative</strong> technology in smart sensors field<br><strong>Totally new approach</strong> to communication between sensor pack and servers <br><strong>Welcome</strong> to ssp page</p>
              <a class="btn btn-default page-scroll" href="#about">Click here to start</a>
            </div>
        </div>
    </div>

</section>

<!-- About Section -->
<section id="about"class="about-section" >
        <div class="container">
            <div class="row">
                <div class="col-lg-12">
                    <h1 style="color: whitesmoke">About Section</h1>
                    <p style="color: whitesmoke"><strong>Short description:</strong> SSP is a smart sensor pack that use KL26Z and can be mounted on any vehical.<br>By smart communication to servers all data comes live in just a second.<br>SSP is a final year project for Computer and Electronig Engineering in GMIT<br>Most recent data below></p>
                    <a class="btn btn-default page-scroll" href="#accgrp">Click to proceed</a>
                </div>
            </div>
        </div>
</section>

<!-- Services Section -->
<section id="accgrp" class="accgrp-section" >
                <h1 style="margin-top: 0">Accelerometer Graph</h1>
                <div id="chart1div" style="width: 100%; height: 400px;"></div>
                <input type="radio" checked="true" name="group" id="rb1" onclick="setPanSelect()">Select
                <input type="radio" name="group" id="rb2" onclick="setPanSelect()">Pan</div>
</section>

<!-- Contact Section -->
<section id="maggrp" class="maggrp-section">

                <h1 style="margin-top: 0">Magnetometer Graph</h1>
                <div id="chart2div" style="width: 100%; height: 400px; "></div>
                <input type="radio" checked="true" name="group" id="rb3" onclick="setPanSelect()">Select
                <input type="radio" name="group" id="rb4" onclick="setPanSelect()">Pan
</section>

<!-- Contact Section -->
<section id="credits" class="credit-section">
    <div class="container">
        <div class="row">
            <div class="col-lg-12">
                <h1 >Credits Section</h1>
                <p>Thank you for visiting my website for SSP project.<br>Please come back to see more data</p>

            </div>
        </div>
    </div>
</section>

<section id="Contact" class="contact-section">
    <footer class="footer-distributed">

        <div class="footer-left">

            <h3>SSP<span>-Smart Sensor Pack</span></h3>

            <p class="footer-links">
                <a href="#intro">Home</a>-
                <a href="#about">About</a>-
                <a href="#accgrp">Graph1</a>-
                <a href="#maggrp">Graph2</a>-
                <a href="#credits">Credits</a>-
                <a href="#Contact">Contact</a>
            </p>

            <p class="footer-company-name">Arkadiusz Bochen &copy; 2016</p>

            <p>Templates used<br><a href="http://startbootstrap.com/">Main template<br><a href="http://tutorialzine.com/2015/01/freebie-5-responsive-footer-templates/">Footer tempate</a> </a> </p>

        </div>

        <div class="footer-center">

            <div>
                <i class="fa fa-map-marker"></i>
                <p><span>konckatee West, Dunmore</span> Galway, Ireland</p>
            </div>

            <div>
                <i class="fa fa-phone"></i>
                <p>+353 86 3249 640</p>
            </div>

            <div>
                <i class="fa fa-envelope"></i>
                <p><a href="mailto:bochenarkadiusz@gmail.com">bochenarkadiusz@gmail.com</a></p>
            </div>

        </div>

        <div class="footer-right">

            <p class="footer-company-about">
                <span>About project</span>
                SSp is a smart sensor pack that can be delivered in varoius forms and be conneced by different technologies. Also it can be installes on any vehicle
            </p>

            <div class="footer-icons">

                <a href="#"><i class="fa fa-facebook"></i></a>
                <a href="#"><i class="fa fa-twitter"></i></a>
                <a href="https://ie.linkedin.com/in/arkadiusz-bochen-3346b180"><i class="fa fa-linkedin"></i></a>
                <a href="https://github.com/BlackBull2015"><i class="fa fa-github"></i></a>

            </div>

        </div>

    </footer>
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
