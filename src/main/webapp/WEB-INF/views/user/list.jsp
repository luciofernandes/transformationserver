<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
	<head>
		<meta charset="UTF-8">
    	<title>M2* Transform | User </title>
	    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
	    <!-- Bootstrap 3.3.4 -->
	    <link href="<%=request.getContextPath()%>/resource/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css" />
	    <!-- Font Awesome Icons -->
	    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css" rel="stylesheet" type="text/css" />
	    <!-- Ionicons -->
	    <link href="https://code.ionicframework.com/ionicons/2.0.1/css/ionicons.min.css" rel="stylesheet" type="text/css" />
    	<!-- DATA TABLES -->
    	<link href="<%=request.getContextPath()%>/resource/plugins/datatables/dataTables.bootstrap.css" rel="stylesheet" type="text/css" />
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
			            <li><a href="<%=request.getContextPath()%>"><i class='fa fa-dashboard'></i> <span>Dashboard</span></a></li>
			            <li class="treeview">
			              <a href="#">
			                <i class="fa fa-gear"></i>
			                <span>Transformation</span>
			                <i class="fa fa-angle-left pull-right"></i>
			              </a>
			              <ul class="treeview-menu">
			                <li><a href="pages/charts/chartjs.html"><i class="fa fa-circle-o"></i> List</a></li>
			                <li><a href="pages/charts/morris.html"><i class="fa fa-circle-o"></i> Create</a></li>
			              </ul>
			            </li>
			             <li class="active treeview">
			              <a href="#">
			                <i class="fa fa-user"></i>
			                <span>User</span>
			                <i class="fa fa-angle-left pull-right"></i>
			              </a>
			              <ul class="treeview-menu">
			                <li class="active" ><a href="<%=request.getContextPath()%>/user"><i class="fa fa-circle-o"></i> List</a></li>
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
		            User
		            <small>List</small>
		          </h1>
		          <ol class="breadcrumb">
		            <li><a href="<%=request.getContextPath()%>"><i class="fa fa-dashboard"></i> Home</a></li>
		            <li> User</li>
		            <li class="active" ><a href="<%=request.getContextPath()%>/users"> List</a></li>
		          </ol>
		        </section>
              
		        <!-- Main content -->
		      	<section class="content">
				
            <div class="box box-primary">
               <!--  <div class="box-header">
                  <h3 class="box-title">Data Table With Full Features</h3>
                </div> -->

                <div class="box-body">
                  <table id="list" class="table table-bordered table-striped">
                    <thead>
                      <tr>
                        <th>Email</th>
                        <th>Name</th>
                      </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${users}" var="user">
	                      <tr>
	                        <td>
		                        <a href=" <spring:url value= "/user/form/${user.id}" />" > 
		                        	${user.email}</a>
	                        </td>
	                        <td>${user.name}</td>
	                      </tr>
                     </c:forEach> 
                    </tbody>
                  </table>
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
        
	    <!-- jQuery 2.1.4 -->
	    <script src="<%=request.getContextPath()%>/resource/plugins/jQuery/jQuery-2.1.4.min.js"></script>
	    <!-- Bootstrap 3.3.2 JS -->
	    <script src="<%=request.getContextPath()%>/resource/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>
	    <!-- DATA TABES SCRIPT -->
	    <script src="<%=request.getContextPath()%>/resource/plugins/datatables/jquery.dataTables.min.js" type="text/javascript"></script>
	    <script src="<%=request.getContextPath()%>/resource/plugins/datatables/dataTables.bootstrap.min.js" type="text/javascript"></script>
	    <!-- SlimScroll -->
	    <script src="<%=request.getContextPath()%>/resource/plugins/slimScroll/jquery.slimscroll.min.js" type="text/javascript"></script>
	    <!-- FastClick -->
	    <script src='<%=request.getContextPath()%>/resource/plugins/fastclick/fastclick.min.js'></script>
	    <!-- AdminLTE App -->
	    <script src="<%=request.getContextPath()%>/resource/dist/js/app.min.js" type="text/javascript"></script>
	    <!-- page script -->
	    <script type="text/javascript">
	      $(function () {
	        $("#list").dataTable();
	      });
	    </script>
        
	</body>
</html>