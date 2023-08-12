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
						<div class="col-md-6 col-sm-12">
							<div class="title">
								<h4>Danh sách đơn hàng</h4>
							</div>
						</div>
					</div>
				</div>
				<c:url value="admin/transaction" var="pagedLink">
					<c:param name="p" value="~" />
				</c:url>
				<!-- Simple Datatable start -->
				<div class="card-box mb-30">
					<hr>
					<div class="pb-20 overflow-auto ">
						<div class="mr-4">
							<form class="d-inline-flex mb-5" style="float: right">
								<input name="searchInput" id="searchInput"
									class="form-control me-2" type="search"
									placeholder="Nhập số điện thoại" aria-label="Search">

								<button name="btnsearch2" id="searchProduct"
									class="btn btn-outline-info" type="submit">Search</button>
							</form>
						</div>


						<table class="data-table table stripe hover" style="width: 100%"
							id="myTable">


							<thead class="table-dark">
								<tr>
									<th>MÃ ĐƠN HÀNG</th>
									<th>SỐ ĐIỆN THOẠI</th>
									<th>TÊN NGƯỜI ĐẶT</th>
									<th>THÀNH TIỀN</th>
									<th>TRẠNG THÁI</th>
									<th>NGÀY ĐẶT</th>
									<th class="">CHỨC NĂNG</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="item" items="${pagedListHolder.pageList}">
									<tr style="cursor: pointer;">

										<td><a href="admin/transaction/${item.id}">${item.id}</a></td>
										<td>${item.phone}</td>
										<td>${item.fullName}</td>

										<td><fmt:formatNumber pattern="###,### VND"
												value="${item.paymentTransaction.amount}" type="currency" /></td>
										<td>${item.tagName}</td>
										<td>${item.created}</td>
										<td class=""><c:if
												test="${item.tagName.trim().equals('Chờ xác nhận')}">

												<div class="row clearfix btn-list">
													<form action="admin/transaction/accept/${item.id}" class="row"
														method="post">
														<button class="btn btn-info mb-2 mr-2" type="submit"
															data-toggle="tooltip" data-placement="top" 
															title="Xác nhận">
															<i class="bi bi-check-circle"></i>
														</button>
													</form>
													<form action="admin/transaction/cancel/${item.id}"
														method="post">
														<button class="btn btn-danger delete_btn mb-2 " 
															data-toggle="tooltip" data-placement="top" title="Hủy"
															type="submit">
															<i class="bi bi-x-circle"></i>
														</button>
													</form>

												</div>

											</c:if></td>
									</tr>

								</c:forEach>



							</tbody>
						</table>
						<div class="mr-4">
							<tg:paging pagedListHolder="${pagedListHolder}"
								pagedLink="${pagedLink}" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!--======================================================Dùng để xem chi tiết======================================  -->
	<div class="modal fade bs-example-modal-lg" id="bd-edit-modal-lg"
		tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
		aria-hidden="true">
		<button hidden="true" id="update_modal_btn" data-toggle="modal"
			data-target="#bd-edit-modal-lg"></button>
		<div class="modal-dialog modal-lg modal-dialog-centered">
			<div class="modal-content">
				<div class="modal-body">
					<!-- Default Basic Forms Start -->
					<form:form action="admin/flower/update" modelAttribute="transDetail" enctype="multipart/form-data">
					<div class="pd-20 card-box mb-10">
						<div class="clearfix">
							<div class="row">
								<div class="col">
									<h4 class="text-blue h4 pull-left">Chi tiết đơn hàng ${transDetail.id}</h4>
								</div>
								<div class="col">
									<h4 class="text-success h4 pull-right">${transDetail.tagName}</h4>
								</div>

							</div>
						</div>
						<form:form action=""
							modelAttribute="transDetail"
							enctype="multipart/form-data">
							<div class="col">
								<div class="row mb-10">
									<div class=" col">
										<div class="row">
											<div class="col">
												<div class="row" ><p class="font-weight-bold ">Thông tin người đặt</p></div>
												<div class="row"><h6 class="font-weight-light">${transDetail.fullName}</h6> </div>
												<div class="row"><h6 class="font-weight-light">${transDetail.phone}</h6></div>
											</div>
										</div>
									</div>
									<div class=" col">
										<div class="row">
											<div class="col">
												<div class="row"><p class="font-weight-bold ">Thông tin người nhận</p></div>
												<div class="row"><h6 class="font-weight-light">${transDetail.recipientName}</h6></div>
												<div class="row"><h6 class="font-weight-light">${transDetail.recipientPhone}</h6></div>
												<div class="row"><h6 class="font-weight-light">${transDetail.recipientAddress}</h6></div>

											</div>
										</div>
									</div>
									<div class=" col">
										<div class="row">
											<div class="col">
												<div class="row"><h6 class="font-weight-bold">Ngày đặt hàng: </h6> <span class="font-weight-light">${transDetail.created}</span></div>
												<div class="row"><h6 class="font-weight-bold">Hình thức: </h6> <span class="font-weight-light">${paymentTransaction.paymentType}</span></div>
												
												<div class="row"><h6 class="font-weight-bold">Áp dụng loyal point: </h6 ><span class="font-weight-light"><fmt:formatNumber pattern="###,### VND"
												value="${paymentTransaction.pointUsed}" type="currency" /></span></div>
												<div class="row"><h6 class="font-weight-bold">Áp dụng giftvoucher: </h6><span class="font-weight-light"><fmt:formatNumber pattern="###,### VND"
												value="${paymentTransaction.giftVoucherValue}" type="currency" /></span></div>
											</div>
										</div>
									</div>

								</div>
								<div class="row">
								
									<table class="table">
										<thead>
											<tr>
												<th scope="col">Hình ảnh</th>
												<th scope="col">Sản phẩm</th>
												<th scope="col">Đơn giá</th>
												<th scope="col">Giảm giá</th>
												<th scope="col">Số lượng</th>
												<th scope="col">Thành tiền</th>
											</tr>
										</thead>
										<tbody>
										<c:forEach var="item" items="${listOrder}">
											<tr>
												<td ><img src="resources/images/flower/${item.flower.images[0]}"
                                         style="width: 40px; height: 40px"></td>
												<td>${item.flower.name}</td>
												<td><fmt:formatNumber pattern="###,###"
												value="${item.flower.price}" type="currency" /> </td>
												<td>${item.flower.discount}%</td>
												<td>${item.quantity}</td>
												<td><fmt:formatNumber pattern="###,###"
												value="${item.amount}" type="currency" /></td>
											</tr>
											</c:forEach>
										</tbody>
									</table>
								</div>
								<div class="row pull-right mb-20" >
										
										<p class="font-weight-bold h4">Tổng tiền: <fmt:formatNumber pattern="###,### VND"
												value="${paymentTransaction.amount}" type="currency" /> </p>
									
								</div>
							</div>

						</form:form>
					</div>
					</form:form>
					<!-- Default Basic Forms end -->
				</div>
			</div>
		</div>
	</div>


	<!-- js -->
	<%@include file="/resources/admin/common/footer.jsp"%>

	<!-- DÙNG ĐỂ SHOW FORM DETAIL -->
	<c:if test="${formDetail}">
		<script type="text/javascript">
        $(document).ready(function () {
            $('#update_modal_btn').click();
            console.log("Hiển thị detail form")
        });
    </script>
	</c:if>



	<!-- DÙNG ĐỂ SHOW FORM CANCEL -->
	<c:if test="${form_del}">
		<script type="text/javascript">
        $(document).ready(function () {
            $('#update_modal_btn').click();
            console.log("Hiển thị cancel form")
        });
    </script>
	</c:if>

	<!-- HIỂN THỊ THÔNG BÁO ACCEPT/CANCEL THÀNH CÔNG / THẤT BẠI -->
	<c:if test="${accept || cancel }">
		<script type="text/javascript">
        $(document).ready(function () {
        	console.log("test")
            show_success();
			
        });
    </script>
	</c:if>


	<!-- HIỂN THỊ THÔNG BÁO CÓ LỖI XẢY RA-->
	<c:if test="${accept == false || cancel == false}">
		<script type="text/javascript">
        $(document).ready(function () {
        	console.log("test false")
            show_error();
            
        });
    </script>
	</c:if>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
	<script type="text/javascript">
    //LOAD TABLE
    /*  $('#myTable').DataTable();  */


    //NẾU CLICK NÚT CANCEL
    $('#myTable').on('click', '.accept_btn', function () {
        let ten = $(this).parent().find("input[name='name']").val();
        let delete_btn = $(this).parent().find('.submit_del_btn');
        Swal.fire({
            title: 'Ẩn [' + ten + '] ?',
            text: "Bạn có chắc chắn muốn hủy?",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#3085d6',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Đồng ý',
            cancelButtonText: 'Thoát'
        }).then((result) => {
            if (result.isConfirmed) {
                delete_btn.click();
            }
        })
    });

    //NẾU CLICK NÚT KHOÁ
    $('#lock_btn').on('click', function () {
        if (confirm_lock()) {
            console.log(1)
        }
        console.log("locked")
    });


    //THÔNG BÁO THÀNH CÔNG
    function show_success(content = "Thao tác") {
        Swal.fire({
            title: 'THÀNH CÔNG',
            text: content + " thành công!",
            icon: 'success',
        })
    };

    //THÔNG BÁO LỖI
    function show_error(content = "Thao tác") {
        Swal.fire({
            title: 'THẤT BẠI',
            text: content + " thất bại!",
            icon: 'error',
        })
    };
    </script>
</body>
</html>