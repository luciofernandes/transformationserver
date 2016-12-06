<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
	<head>
		<meta charset="UTF-8">
    	<title>M2* Transform | Dashboard</title>
    	<!-- 
    	<script type="text/javascript">
	<c:set var='message' value='${tagline}'/> 
	var message = '<c:out value="${message}"/>';
	alert(message);	
 
</script>
-->
 
	    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
	    <!-- Bootstrap 3.3.4 -->
	    <link href="<%=request.getContextPath()%>/resource/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
	    <!-- Font Awesome Icons -->
	    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
	    <!-- Ionicons -->
	    <link href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css" rel="stylesheet" type="text/css" />
	    <!-- jvectormap -->
	    <link href="<%=request.getContextPath()%>/resource/plugins/jvectormap/jquery-jvectormap-1.2.2.css" rel="stylesheet" type="text/css" />
	    <!-- Theme style -->
	    <link href="<%=request.getContextPath()%>/resource/dist/css/AdminLTE.min.css" rel="stylesheet" type="text/css" />
	    <!-- AdminLTE Skins. Choose a skin from the css/skins
	         folder instead of downloading all of them to reduce the load. -->
	    <link href="<%=request.getContextPath()%>/resource/dist/css/skins/_all-skins.min.css" rel="stylesheet" type="text/css" />
	

    	<!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    	<!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    	<!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
        <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    	<![endif]-->
    

  	</head>
	<body class="skin-blue sidebar-mini" >
		<div class="wrapper">

     		<!-- Main Header -->
      		<header class="main-header">
	
	        	<!-- Logo -->
	        	<a href="<%=request.getContextPath()%>" class="logo">
	          		<!-- mini logo for sidebar mini 50x50 pixels -->
	          		<span class="logo-mini"><b>M2*</b></span>
	          		<!-- logo for regular state and mobile devices -->
	          		<span class="logo-lg"><b>M2*</b>Transform</span>
	        	</a>
	        	 <!-- Header Navbar -->
        		<nav class="navbar navbar-static-top" role="navigation">
          		<!-- Sidebar toggle button-->
          			<a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            			<span class="sr-only">Toggle navigation</span>
          			</a>

          		</nav>
        	</header>
        	<!-- Left side column. contains the logo and sidebar -->
      		<aside class="main-sidebar">
      			<!-- sidebar: style can be found in sidebar.less -->
        		<section class="sidebar">
        		    <!-- Sidebar Menu -->
			    	<ul class="sidebar-menu">
			        	<li class="header">MAIN NAVIGATION</li>
			            <!-- Optionally, you can add icons to the links -->
			            <li class="active"><a href=""><i class='fa fa-dashboard'></i> <span>Dashboard</span></a></li>
			            <li class="treeview">
			              <a href="#">
			                <i class="fa fa-gear"></i>
			                <span>Transformations</span>
			                <i class="fa fa-angle-left pull-right"></i>
			              </a>
			              <ul class="treeview-menu">
			                <li><a href="pages/charts/chartjs.html"><i class="fa fa-circle-o"></i> List</a></li>
			                <li><a href="pages/charts/morris.html"><i class="fa fa-circle-o"></i> Create</a></li>
			              </ul>
			            </li>
			             <li class="treeview">
			              <a href="#">
			                <i class="fa fa-user"></i>
			                <span>Users</span>
			                <i class="fa fa-angle-left pull-right"></i>
			              </a>
			              <ul class="treeview-menu">
			                <li><a href="<%=request.getContextPath()%>/user"><i class="fa fa-circle-o"></i> List</a></li>
			                <li><a href="<%=request.getContextPath()%>/user/form"><i class="fa fa-circle-o"></i> Create</a></li>
			              </ul>
			            </li>
			        </ul><!-- /.sidebar-menu -->
			    </section>
        		<!-- /.sidebar -->
        	</aside>

		      <!-- Content Wrapper. Contains page content -->
		      <div class="content-wrapper">
		        <!-- Content Header (Page header) -->
		        <section class="content-header">
		          <h1>
		            Dashboard 
		            <small>Version 1.0</small>
		          </h1>
		          <ol class="breadcrumb">
		            <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
		            <li class="active">Dashboard</li>
		          </ol>
		        </section>
              
		        <!-- Main content -->
		      	<section class="content">
				
              <!-- MAP & BOX PANE -->
              <div class="box box-success">
                <div class="box-header with-border">
                  <h3 class="box-title">Transformations Report</h3>
                  <div class="box-tools pull-right">
                    <button class="btn btn-box-tool" data-widget="collapse"><i class="fa fa-minus"></i></button>
                  </div>
                </div><!-- /.box-header -->
                <div class="box-body no-padding">
                  <div class="row">
                    <div class="col-md-9 col-sm-8">
                      <div class="pad">
                        <!-- Map will be created here -->
                        <div id="world-map-markers" style="height: 325px;"></div>
                      </div>
                    </div><!-- /.col -->
                    <div class="col-md-3 col-sm-4">
                      <div class="pad box-pane-right bg-green" style="min-height: 280px">
                        <div class="description-block margin-bottom">
                          <h5 class="description-header">8390</h5>
                          <span class="description-text">Transformations</span>
                        </div><!-- /.description-block -->
                        
                      </div>
                    </div><!-- /.col -->
                  </div><!-- /.row -->
                </div><!-- /.box-body -->
              </div><!-- /.box -->

		        </section><!-- /.content -->
		      </div><!-- /.content-wrapper -->
      
       <!-- Main Footer -->
      <footer class="main-footer">
        <!-- To the right -->
        <div class="pull-right hidden-xs">
          edson@viciouscode.com
        </div>
        <!-- Default to the left -->
        <strong>Copyright &copy; 2015 <a href="#">Edson Alves</a>.</strong> All rights reserved.
      </footer>
      
        </div><!-- ./wrapper -->
         <!-- REQUIRED JS SCRIPTS -->
	    <!-- jQuery 2.1.4 -->
	    <script src="<%=request.getContextPath()%>/resource/plugins/jQuery/jQuery-2.1.4.min.js"></script>
	    <!-- Bootstrap 3.3.2 JS -->
	    <script src="<%=request.getContextPath()%>/resource/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
	    <!-- FastClick -->
	    <script src='<%=request.getContextPath()%>/resource/plugins/fastclick/fastclick.min.js'></script>
	    <!-- AdminLTE App -->
	    <script src="<%=request.getContextPath()%>/resource/dist/js/app.min.js" type="text/javascript"></script>
	    <!-- Sparkline -->
	    <script src="<%=request.getContextPath()%>/resource/plugins/sparkline/jquery.sparkline.min.js" type="text/javascript"></script>
	    <!-- jvectormap -->
	    <script src="<%=request.getContextPath()%>/resource/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js" type="text/javascript"></script>
	    <script src="<%=request.getContextPath()%>/resource/plugins/jvectormap/jquery-jvectormap-world-mill-en.js" type="text/javascript"></script>
	    <!-- SlimScroll 1.3.0 -->
	    <script src="<%=request.getContextPath()%>/resource/plugins/slimScroll/jquery.slimscroll.min.js" type="text/javascript"></script>
	    <!-- ChartJS 1.0.1 -->
	    <script src="<%=request.getContextPath()%>/resource/plugins/chartjs/Chart.min.js" type="text/javascript"></script>
	
	    <!-- AdminLTE dashboard demo (This is only for demo purposes) -->
	    <script src="<%=request.getContextPath()%>/resource/dist/js/pages/dashboard2x.js" type="text/javascript"></script>
	
	    <!-- AdminLTE for demo purposes -->
	    <script src="<%=request.getContextPath()%>/resource/dist/js/demo.js" type="text/javascript"></script>
	    
	</body>
</html>