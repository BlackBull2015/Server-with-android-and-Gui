<?php

$blogID = $_GET['blogID'];

?>
<html>
<head>
	<meta charset="utf-8">
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<meta name="description" content="">
	<meta name="author" content="">

	<!-- Latest compiled and minified CSS -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">

	<!-- Optional theme -->
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">

	<script type="text/javascript" src="https://code.jquery.com/jquery-2.2.0.min.js"></script>

	<link type ="text/css" rel="stylesheet" href="_/lib/lightGal/css/lightgallery.css"/>

	<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">

	<script type="text/javascript" src="_/lib/lightGal/js/lightgallery.min.js" ></script>

	<!-- Latest compiled and minified JavaScript -->
	<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>

	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery-backstretch/2.0.4/jquery.backstretch.min.js"></script>

	<title>SidelineEye Blog</title>
<style type="text/css">

#toTop{
	position: fixed;
	bottom: 10px;
	right: 10px;
	cursor: pointer;
	display: none;
}


.navbar.transparent .navbar-inner {
  background: rgba(0,0,0,0.4);
  color: #ffffff;
}
.navbar.transparent .navbar-inner li a {
   color: #ffffff;
   font-family: "Arial";
   font-size: 8pt;
   letter-spacing: 3pt;
}
.navbar.transparent .nav > li > a:focus,
.navbar.transparent .nav > li > a:hover {
 color: #666666;
 text-decoration: none;
 background-color: transparent;
}
.button.navbar-toggle.collapsed:focus,
.button.navbar-toggle.collapsed:hover {
 color: #666666;
}
.navbar.transparent .navbar-brand:hover, 
.navbar.transparent .navbar-brand:focus {
 background-color: transparent;
 color: #ffffff;
}
.navbar.transparent .navbar-brand{
 background-color: transparent;
 color: #ffffff;
 font-family: "Arial";
 font-size: 16pt;
 letter-spacing: 1pt;
}

.jumbotron{
	background: url("https://www.dropbox.com/s/h9iqrl7ajix7y1t/bg-crokePark.jpg?dl=1") no-repeat center center; 
    -webkit-background-size: 100% 100%;
    -moz-background-size: 100% 100%;
    -o-background-size: 100% 100%;
    background-size: 100% 100%;
    border-color: black;
    border: 6px;
    border-style:solid;
    color: white;
	text-shadow:
   		-1px -1px 0 #000,  
    	1px -1px 0 #000,
    	-1px 1px 0 #000,
     	1px 1px 0 #000;
}
.jumbotron h2,h4{
	
}

img {
	padding: 10px;
}

body {padding-top: 70px;  background-color:#f9f9f9;}

/* Visitor */
a.visitor i,.visitor h4.list-group-item-heading { color:#E48A07; }
a.visitor:hover { background-color:#E48A07; }
a.visitor:hover * { color:#FFF; }
/* Facebook */
a.facebook-like i,.facebook-like h4.list-group-item-heading { color:#3b5998; }
a.facebook-like:hover { background-color:#3b5998; }
a.facebook-like:hover * { color:#FFF; }
/* Google */
a.google-plus i,.google-plus h4.list-group-item-heading { color:#dd4b39; }
a.google-plus:hover { background-color:#dd4b39; }
a.google-plus:hover * { color:#FFF; }
/* Twitter */
a.twitter i,.twitter h4.list-group-item-heading { color:#00acee; }
a.twitter:hover { background-color:#00acee; }
a.twitter:hover * { color:#FFF; }

.pager a { float: left; font color: "Black";}

/*Footer*/

.cuadro_intro_hover {
	padding: 0px;
	position: relative;
	overflow: hidden;
	height: 200px;
}
.cuadro_intro_hover:hover .caption {
	opacity: 1;
	transform: translateY(-150px);
	-webkit-transform:translateY(-150px);
    -moz-transform:translateY(-150px);
    -ms-transform:translateY(-150px);
    -o-transform:translateY(-150px);
}
.cuadro_intro_hover img {
	z-index: 4;
}
.cuadro_intro_hover .caption {
	position: absolute;
    top:150px;
    -webkit-transition:all 0.3s ease-in-out;
    -moz-transition:all 0.3s ease-in-out;
    -o-transition:all 0.3s ease-in-out;
    -ms-transition:all 0.3s ease-in-out;
    transition:all 0.3s ease-in-out;
    width: 100%;
}
.cuadro_intro_hover .blur {
	background-color: rgba(0,0,0,0.7);
	height: 300px;
	z-index: 5;
	position: absolute;
width: 100%;
}
.cuadro_intro_hover .caption-text {
	z-index: 10;
	color: #fff;
	position: absolute;
	height: 300px;
	text-align: center;
	top:-20px;
	width: 100%;
}

#login-dp {
	min-width: 250px;
	padding: 14px 14px 0;
	overflow:hidden;
	background-color:rgba(255,255,255,.8);
}
#login-dp .help-block {
    font-size:12px    
}
#login-dp .bottom {
    background-color:rgba(255,255,255,.8);
    border-top:1px solid #ddd;
    clear:both;
    padding:14px;
}
#login-dp .social-buttons {
    margin:12px 0    
}
#login-dp .social-buttons a {
    width: 49%;
}
#login-dp .form-group {
    margin-bottom: 10px;
}
.btn-fb {
    color: #fff;
    background-color:#3b5998;
}
.btn-fb:hover {
    color: #fff;
    background-color:#496ebc 
}
.btn-tw {
    color: #fff;
    background-color:#55acee;
}
.btn-tw:hover {
    color: #fff;
    background-color:#59b5fa;
}
@media(max-width:768px){
	#login-dp{
		background-color: inherit;
		color: #fff;
	}
	#login-dp .bottom{
		background-color: inherit;
		border-top:0 none;
	}
	@media (max-width:992px) {
	.container {
	       width: 100%;
	}
}
}

</style>
</head>
<body>
	<nav class="navbar transparent navbar-fixed-top" role="navigation">
     <nav class="navbar-inner">
       <div class="container-fluid">
         <div class="navbar-header">
           <button type="button" class="navbar-toggle"  data-toggle="collapse" data-target="#example-nav-collapse">
             <span class="sr-only">Toggle navigation</span>
             <span class="icon-bar"></span>
             <span class="icon-bar"></span>
             <span class="icon-bar"></span>
           </button>
           <a class="navbar-brand" href="index.php">SidlineEye <i class="fa fa-home"></i></a></div>
   <div class="collapse navbar-collapse" id="example-nav-collapse">
           <ul class="nav navbar-nav navbar-right">
             <li><a href="#">OUR DNA</a></li>
             <li><a href="#">FEATURES</a></li>
             <li><a href="#">CLIENTS</a></li>
             <li><a href="#">TESTIMONALS</a></li>
             <li><a href="#">TEAM</a></li>
             <li><a href="#">BLOG</a></li>
             <li><a href="#">PRICE</a></li>
             <li><a href="#">CONTACT</a></li>
             <li><a href="#">FQA</a></li>
           </ul>
         </div>
       </div>
     </nav>
   </nav>

<div class="container">
	<div class="jumbotron">
	<!--<img src="test.jpg" class="img-responsive col-md-12" alt="Responsive image">-->
		<h2><span id="blogTitle"></span></h2>
		<h4><span id="blogSubTitle"></span></h4>
	</div><!--/.jumbotron-->
    <div class="row">
		<div class="col-md-9">
		<div class="panel panel-default box-shadow--2dp">
			<div class="panel-body">
    
		    	<div id="blogTextDiv">

				</div><!-- /#blogTextDiv-->
			</div><!-- /.panelBody-->
		</div><!-- /.panel-->
	</div><!-- /.col-->
	<div class="col-md-3">
  <!-- Panel for Lightbox Gallery of Stats-->
      <div class="panel panel-default box-shadow--2dp">
        <div class="panel-body">
        	<button class="btn btn-primary btn-block" id="statsGallery">
          <br>
            <i class="fa fa-bar-chart-o fa-4x"></i>
            <h3>Game Stats</h3>
          </button>
          <button class="btn btn-block btn-info">
          <h4>Page Views <span class="badge" id="hitsCount"></span></h4>

        </button>
        </div><!/.panel body-->

      </div><!--/.panel-->

		<div class="panel panel-default box-shadow--2dp">
			<h4 class="text-center">FACEBOOK</h4>
        	<div class="panel-body">
				<div class="fb-page"data-href="https://www.facebook.com/sidelineye" data-width="340"
        				data-hide-cover="false"
        				data-show-facepile="true">
				</div>
			</div>
		</div><!-- /.panel-->
		<div class="panel panel-default box-shadow--2dp">
			<h4 class="text-center">TWITTER</h4>
			<div class="panel-body">
				<a class="twitter-timeline" href="https://twitter.com/sidelineye" data-widget-id="486863748192149504">Tweets by @sidelineye</a>
      			<script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0],p=/^http:/.test(d.location)?'http':'https';if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src=p+"://platform.twitter.com/widgets.js";fjs.parentNode.insertBefore(js,fjs);}}(document,"script","twitter-wjs");</script>
			</div>
		</div><!--/.panel-->
    </div>
  </div><!--./col-->
	

   <footer>
            <div class="row">
                <div class="col-lg-12">
                <div class="col-lg-9col-md-3">
                <div class="container">
    
   
        <div class="col-lg-3">
                    <div class="cuadro_intro_hover " style="background-color:#cccccc;">
                        <p style="text-align:center; margin-top:20px;">
                            <img src="http://placehold.it/200x200" class="img-responsive" alt="">
                        </p>
                        <div class="caption">
                            <div class="blur"></div>
                            <div class="caption-text">
                                <h3 style="border-top:2px solid white; border-bottom:2px solid white; padding:10px;">Kerry vs Dublin</h3>
                                <p>Loren ipsum dolor si amet ipsum dolor si amet ipsum dolor...</p>
                                <a class=" btn btn-success" href="#"><span class="glyphicon glyphicon-plus"> INFO</span></a>
                            </div>
                        </div>
                    </div>
                
        </div>
       <div class="col-lg-3">
                    <div class="cuadro_intro_hover " style="background-color:#cccccc;">
                        <p style="text-align:center; margin-top:20px;">
                            <img src="http://placehold.it/200x200" class="img-responsive" alt="">
                        </p>
                        <div class="caption">
                            <div class="blur"></div>
                            <div class="caption-text">
                                <h3 style="border-top:2px solid white; border-bottom:2px solid white; padding:10px;">Cork vs Mayo</h3>
                                <p>Loren ipsum dolor si amet ipsum dolor si amet ipsum dolor...</p>
                                <a class=" btn btn-success" href="#"><span class="glyphicon glyphicon-plus"> INFO</span></a>
                            </div>
                        </div>
                    </div>
                
        </div>
            

           <div class="col-lg-3 col-md-6">
            <h3>Categories:</h3>
                <ul>
                <li><a href=""><i class="fa fa-file"></i> Match Analysis</a></li>
                <li><a href=""><i class="fa fa-android"></i> Football</a></li>
                <li><a href=""><i class="fa fa-coffee "></i> Hurling</a></li>
                <li><a href=""><i class="fa fa-cloud "></i> Coaching</a></li>
                <li><a href=""><i class="fa fa-book"></i> League</a></li>
                <li><a href=""><i class="fa fa-globe"></i> NFL</a></li>
                </ul>
            </div>

            <div class="col-lg-3 col-md-6">

            <h3>Follow:</h3>
            <a href="" id="gh" target="_blank" title="Twitter"><span class="fa-stack fa-lg">
        <i class="fa fa-square-o fa-stack-2x"></i>
        <i class="fa fa-twitter fa-stack-1x"></i>
      </span>
      Twitter</a>
      <a href=""  target="_blank" title="Facebook"><span class="fa-stack fa-lg">
        <i class="fa fa-square-o fa-stack-2x"></i>
        <i class="fa fa-facebook fa-stack-1x"></i>
      </span>
      Facebook</a> 
              </div>
              </div>
            <div id="fb-root"></div>

                <hr>
                    <p>Copyright @ SideLineEye| <a href="">Privacy Policy</a> | <a href="">Terms of Use</a></p>
                    <hr>  
                    </div>
                </div>           
        </footer>

<!--/.container-->
  </div>
</div>

<!--<script type="text/javascript">
	$(document).ready(function(){
		$.backstretch("grassSideline.jpg");
	});
  </script>-->

<script type="text/javascript">
$(document).ready(function(){
      $('body').append('<div id="toTop" class="btn btn-info "><span class="glyphicon glyphicon-chevron-up"></span> Back to Top</div>');
      $(window).scroll(function () {
      if ($(this).scrollTop() != 0) {
        $('#toTop').fadeIn();
      } else {
        $('#toTop').fadeOut();
      }
    }); 
    $('#toTop').click(function(){
        $("html, body").animate({ scrollTop: 0 }, 600);
        return false;
    });
});
</script>

    <script type="text/javascript">

	// Animate the element's value from x to y:
$({ someValue: 0 }).animate({ someValue: Math.floor(Math.random() * 3000) }, {
    duration: 3000,
    easing: 'swing', // can be anything
    step: function () { // called on every step
        // Update the element's text with rounded-up value:
        $('.count').text(commaSeparateNumber(Math.round(this.someValue)));
    }
});
</script>

<script type="text/javascript">

function commaSeparateNumber(val) {
    while (/(\d+)(\d{3})/.test(val.toString())) {
        val = val.toString().replace(/(\d)(?=(\d\d\d)+(?!\d))/g, "$1,");
    }
    return val;
  }

    </script>

    <div id="fb-root"></div>

  <script>
  (function(d, s, id) {
 var js, fjs = d.getElementsByTagName(s)[0];
 if (d.getElementById(id)) return;
 js = d.createElement(s); js.id = id;
 js.src = "//connect.facebook.net/en_GB/sdk.js#xfbml=1&version=v2.5";
 fjs.parentNode.insertBefore(js, fjs);
  }
(document, 'script', 'facebook-jssdk'));
</script>

<script type="text/javascript">
$(document).ready(function(){
	$.backstretch("https://www.dropbox.com/s/lajg9fpl98ydvn6/grassSideline.jpg?dl=1");
	$.ajax({
			type: 'POST',
            url: '_/ajax/blogPage_getBlogJSON.php',
            data: {
				blogID: <?= $blogID;?>
			},
			success: function(data)
				{
					//console.log(data.blogText);
					$("#blogTextDiv").html(data.blogText);
					$("#blogTitle").html(data.blogTitle);
					$("#blogSubTitle").html(data.blogSubTitle);
          $("#hitsCount").html(data.blogHits);
					$('.aniimated-thumbnials').lightGallery({
						thumbnail:true,
						animateThumb: false,
						showThumbByDefault: false
					});
				}
		});//End Ajax

		$(document).on('click','.animatedGIF', function() {
			gifSrc = $(this).data("gifsrc");
			console.log("GIF Src " + gifSrc);
			$(this).attr("src", gifSrc);
		});

    /*When the Stats Button click on load JSON file with data
    $(this).lightGallery({
        dynamic: true,
        mode: 'lg-fade',
        dynamicEl: [
            {
              'src': 'https://www.dropbox.com/s/o679a8rpmevj70o/Dublin%20Shooting%20Stats.png?dl=1',
              'thumb': 'https://www.dropbox.com/s/o679a8rpmevj70o/Dublin%20Shooting%20Stats.png?dl=1',
              'subHtml': "<h4>Dublin Shooting Stats</h4>"
          },{
              'src': 'https://www.dropbox.com/s/c5txae2h2f1gk46/Kerry%20Attacks%202nd%20Half%20Map.png?dl=1',
              'thumb': 'https://www.dropbox.com/s/c5txae2h2f1gk46/Kerry%20Attacks%202nd%20Half%20Map.png?dl=1',
              'subHtml': "<h4>Kerry 2nd Half Attacks Map</h4>"
          }]
      })
    */

    $('#statsGallery').on('click', function() {
      $.ajax({
        type: 'POST',
              url: '_/ajax/blogPage_getBlogPicsJSON.php',
              data: {
          blogID: <?= $blogID;?>
        },
        success: function(data)
          {
            $(this).lightGallery({
              dynamic: true,
              mode: 'lg-fade',
              dynamicEl: data
            });
            
          }
      });//End Ajax



});



	});//End jQuery
</script>
</body>
</html>