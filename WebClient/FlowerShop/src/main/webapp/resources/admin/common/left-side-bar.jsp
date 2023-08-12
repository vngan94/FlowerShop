<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="loadcss.jsp"%>
 <base href="${pageContext.servletContext.contextPath}/" />  
<div class="left-side-bar">
	<div class="brand-logo">
			<a class="" href="admin/flower">
				<img class="" src="resources/admin/vendors/images/logo.png" alt="lgogo"> 
			</a>
		</div>
	<div class="menu-block customscroll">
		<div class="sidebar-menu">
			<ul id="accordion-menu">
				<li>
					<div class="dropdown-divider"></div>
				</li>
				<li class="dropdown" id="qlh" style="display: block"><a
					href="admin/flower" class="dropdown-toggle no-arrow"
					data-toggle="tooltip" data-placement="right" title=""
					data-original-title="QUẢN LÝ SẢN PHẨM"> <span
						class="micon dw dw-file"></span> <span class="text-yellow">QUẢN LÝ SẢN PHẨM</span>
				</a></li>
				<li class="dropdown" id="qluser" style="display: block"><a
					href="admin/user" class="dropdown-toggle no-arrow"
					data-toggle="tooltip" data-placement="right" title=""
					data-original-title="QUẢN LÝ TÀI KHOẢN"> <span
						class="micon dw dw-ruler"></span> <span class="text-yellow">QUẢN LÝ TÀI KHOẢN</span>
				</a></li>
				<li class="dropdown" id="qldh" style="display: block"><a
					href="admin/transaction" class="dropdown-toggle no-arrow"
					data-toggle="tooltip" data-placement="right" title=""
					data-original-title="QUẢN LÍ ĐƠN HÀNG"> <span
						class="micon dw dw-shopping-bag"></span> <span class="text-yellow">QUẢN LÍ ĐƠN HÀNG</span>
				</a></li>
				<li class="dropdown" id="bctk" style="display: block"><a
					href="admin/report" class="dropdown-toggle no-arrow"
					data-toggle="tooltip" data-placement="right" title=""
					data-original-title="THỐNG KÊ - BÁO CÁO"> <span
						class="micon dw dw-analytics-22"></span> <span class="text-yellow">THỐNG KÊ - BÁO CÁO</span>
				</a></li>

				<!-- 				<li> -->
				<!-- 					<div class="dropdown-divider"></div> -->
				<!-- 				</li> -->
				<li>
					<div class="dropdown-divider"></div>
				</li>
				<!-- 				<li> -->
				<!-- 					<div class="sidebar-small-cap">MỞ RỘNG</div> -->
				<!-- 				</li> -->
				<li class="dropdown" id="dangxuat" style=""><a
					href="${pageContext.request.contextPath}/pages/logout" class="dropdown-toggle no-arrow"
					data-toggle="tooltip" data-placement="right" title=""
					data-original-title="Đăng xuất"> 
					<span
						class="micon dw dw-logout1"></span> 
						<span class="text-yellow">Đăng xuất</span>
				</a></li>
			</ul>
		</div>
	</div>
</div>
<script>
	window.addEventListener('load', function() {
		if ("${role}" === "Admin") {
			document.getElementById("bctk").style.display = "block";
		}
	})
</script>