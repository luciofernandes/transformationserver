<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"  %>
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
			                <li ><a href="<%=request.getContextPath()%>/user"><i class="fa fa-circle-o"></i> List</a></li>
			                <li class="active" ><a href="<%=request.getContextPath()%>/user/form"><i class="fa fa-circle-o"></i> Form</a></li>
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
		            <c:if test="${mode == 'create'}">
		            	<small>Create</small>
		            </c:if>
		            <c:if test="${mode == 'update'}">
		            	<small>Update</small>
		            </c:if>
		          </h1>
		          <ol class="breadcrumb">
		            <li><a href="<%=request.getContextPath()%>"><i class="fa fa-dashboard"></i> Home</a></li>
		            <li> User</li>
		            <li class="active" ><a href="<%=request.getContextPath()%>/user/form"> form</a></li>
		          </ol>
		        </section>
              
		        <!-- Main content -->
		      	<section class="content" >

              <!-- general form elements -->
              <div class="box box-primary">
                <!--<div class="box-header">
                    <h3 class="box-title">Quick Example</h3>
                </div>--><!-- /.box-header -->
      
				<c:url value="/user/save" var="url"/>
                <form:form role="form" modelAttribute="user" method="POST" action="${url}">
                  <div class="box-body">
              
                  <form:hidden path="id" />
                  <c:if test="${exception != null}">
	                  <div class="alert alert-danger alert-dismissable">
	                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
	                    <h4><i class="icon fa fa-ban"></i> Error!</h4>
	                    ${exception}
	                  </div>
                  </c:if>
                  <c:if test="${warning != null}">
	                  <div class="alert alert-warning alert-dismissable">
	                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
	                    <h4><i class="icon fa fa-warning"></i> Warning!</h4>
	                    <form:errors path="*" element="div"/>  
	                  </div>
		      	  </c:if>	
		      	  <c:if test="${success != null}">
			      	  <div class="alert alert-success alert-dismissable">
	                    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
	                    <h4>	<i class="icon fa fa-check"></i> Success!</h4>
	                    ${success}
	                  </div>
                  </c:if>
                    <div class="form-group">
                      <label for="name">Name</label>
                      <form:input type="text" class="form-control" placeholder="Enter name" id="name" path="name" />
                      <form:errors path="name" cssClass="text-danger"/>
                    </div>
                    <div class="form-group">
                      <label for="email">Email address</label>
                      <form:input type="email" class="form-control" id="email" path="email" placeholder="Enter email" />
                      <form:errors path="email" cssClass="text-danger"/>
                    </div>
                    <div class="form-group">
                      <label for="password">Password</label>
                      <form:input type="password" class="form-control" id="password" placeholder="Password" path="password" />
                      <form:errors path="password" cssClass="text-danger"/>
                    </div>
                    <div class="form-group">
                      <label for="confirmPassword">Confirm Password</label>
                      <form:input type="password" class="form-control" id="confirmPassword" placeholder="Confirm Password" path="confirmPassword" />
                      <form:errors path="confirmPassword" cssClass="text-danger"/>
                    </div>
                  </div><!-- /.box-body -->

                  <div class="row box-footer">
            		<div class="col-xs-12">
            			<c:if test="${mode != 'create'}">
            				<a href="#deleteModal_${id}" role="button" class="btn btn-danger" data-toggle="modal"><i class="fa fa-trash-o"></i> Delete</a>
              			</c:if>
              			<button type="submit" class="btn btn-primary pull-right"><i class="fa fa-save"></i> Submit</button>
              			<c:if test="${mode != 'create'}">
              				<spring:url var="historyButtonURL" value="/user/history/${id}" />  
              				<a href="${historyButtonURL}" class="btn btn-info pull-right" style="margin-right: 5px;"><i class="fa fa-history"></i> History</a>
            			</c:if>
            		</div>
        		</div>
        		        		
     			<div id="deleteModal_${id}" class="modal fade">
    				<div class="modal-dialog">
        				<div class="modal-content">
				            <div class="modal-header">
				                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
				                <h4 class="modal-title">Confirm Delete</h4>
				            </div>

				            <div class="modal-body">
				                <p>Are you sure you want to delete this user? </p>
				            </div>
				            
				            <div class="modal-footer">
				                <button type="button" class="btn btn-default pull-left" data-dismiss="modal">Cancel</button>
				                <spring:url var="deleteButtonURL" value="/user/delete/${id}" />  
				                <a href="${deleteButtonURL}" title="Delete" class="btn btn-danger" ><i class="fa fa-trash-o"></i> Delete</a>
				            </div>
        				</div>
    				</div>
    			</div> 

                </form:form>
		</div>
		
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
	    <!-- SlimScroll -->
	    <script src="<%=request.getContextPath()%>/resource/plugins/slimScroll/jquery.slimscroll.min.js" type="text/javascript"></script>
	    <!-- FastClick -->
	    <script src='<%=request.getContextPath()%>/resource/plugins/fastclick/fastclick.min.js'></script>
	    <!-- AdminLTE App -->
	    <script src="<%=request.getContextPath()%>/resource/dist/js/app.min.js" type="text/javascript"></script>
        
	</body>
</html>