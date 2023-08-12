<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/resources/admin/common/taglib.jsp"%>
<!DOCTYPE html>
<html>
<head>
<!-- Basic Page Info -->
<meta charset="utf-8">
<title>Dashboard</title>
<link rel="shortcut icon" type="image/x-icon"
	href="<c:url value='/resources/assets/images/icon.ico' />">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css" />
<%@include file="/resources/admin/common/loadcss.jsp"%>
<%@ taglib prefix="tg" tagdir="/WEB-INF/tags"%>
<style type="text/css">
*[id$=errors] {
	color: red;
	font-style: italic;
}

.tdcon {
	width: 300px
}

.contentss {
	width: 100%;
	display: -webkit-box;
	-webkit-line-clamp: 4;
	-webkit-box-orient: vertical;
	text-overflow: ellipsis;
	word-wrap: break-word;
	overflow: hidden;
	max-height: 8em;
	line-height: 2em;
}
</style>
</head>
<body>
	<%@include file="/resources/admin/common/header.jsp"%>
	<%@include file="/resources/admin/common/left-side-bar.jsp"%>
	<div class="mobile-menu-overlay"></div>
	<div class="main-container">
		<div class="pd-ltr-20 xs-pd-20-10">
			<div class="min-height-200px">
				<div class="page-header">
					<div class="row">


						<div class="row">

							<div class="form-row">
								<form class="form-row">
									<h5 class=" title" style="margin-left: 31px;">Báo cáo
										doanh thu theo sản phẩm</h5>
									<div class="" style="margin-left: 44px;">
										<label for="">Từ: </label> <input type="date" name="from"
											>

									</div>
									<div class="" style="margin-left: 33px;">
										<label for="">Đến: </label> <input type="date" name="to"
											>
									</div>
									<a href="/admin/report/"><button name="btnReport"
											id="searchProduct" class="btn btn-outline-info" type="submit"
											style="margin-left: 26px;">Search</button></a>

								</form>


							</div>




						</div>
					</div>
					<c:url value="admin/transaction" var="pagedLink">
						<c:param name="p" value="~" />
					</c:url>
					<!-- Simple Datatable start -->
					<div class="card-box mb-30">
						<hr>
						<div class="pb-20 overflow-auto  " style="margin: 20px">
							<div class="mr-4"></div>



							<table class=" table table-bordered hover " style="width: 100%"
								id="myTable">


								<thead class="">
									<tr>
										<th>MÃ SẢN PHẨM</th>
										<th>TÊN SẢN PHẨM</th>
										<th>SỐ LƯỢNG BÁN</th>
										<th>THÀNH TIỀN</th>

									</tr>
								</thead>
								<tbody>

									<c:forEach var="item" items="${pagedListHolder.pageList}">
										<tr>

											<td>${item.idFlower}</td>
											<td>${item.flower.name}</td>
											<td>${item.quantity}</td>
											<td><fmt:formatNumber pattern="###,###"
													value="${item.amount}" type="currency" /></td>
									</c:forEach>



								</tbody>
							</table>
							<div class="mr-4">
								<tg:paging pagedListHolder="${pagedListHolder}"
									pagedLink="${pagedLink}" />
							</div>
							<h5 style="margin-left: 480px;">
								Tổng tiền thực nhận:
								<fmt:formatNumber pattern="###,### VND" value="${amount}"
									type="currency" />
							</h5>
						</div>
					</div>
				</div>
			</div>
		</div>



		<!-- js -->
</body>
</html>