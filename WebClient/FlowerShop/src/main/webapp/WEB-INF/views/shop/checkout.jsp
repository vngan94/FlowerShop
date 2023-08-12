<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt"%>
<!doctype html>
<html class="no-js" lang="zxx">

<head>
<meta charset="UTF-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>DND Flower Shop</title>
<meta name="robots" content="noindex, follow" />
<meta name="description" content="">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<!-- Favicon -->
<link rel="shortcut icon" type="image/x-icon"
	href="<c:url value='/resources/assets/images/icon.ico' />">

<!-- CSS
	============================================ -->
<!-- Bootstrap CSS -->
<link rel="stylesheet"
	href="<c:url value='/resources/assets/css/vendor/bootstrap.min.css' />">

<!-- Test -->
<link rel="stylesheet"
	href="<c:url value='/resources/assets/images/icon.ico' />">
<!-- Font Awesome CSS -->
<link rel="stylesheet"
	href="<c:url value='/resources/assets/css/vendor/font.awesome.min.css' />">
<!-- Linear Icons CSS -->
<link rel="stylesheet"
	href="<c:url value='/resources/assets/css/vendor/linearicons.min.css' />">
<!-- Swiper CSS -->
<link rel="stylesheet"
	href="<c:url value='/resources/assets/css/plugins/swiper-bundle.min.css' />">

<!-- Animation CSS -->
<link rel="stylesheet"
	href="<c:url value='/resources/assets/css/plugins/animate.min.css' />">
<!-- Jquery ui CSS -->
<link rel="stylesheet"
	href="<c:url value='/resources/assets/css/plugins/jquery-ui.min.css' />">
<!-- Nice Select CSS -->
<link rel="stylesheet"
	href="<c:url value='/resources/assets/css/plugins/nice-select.min.css' />">
<!-- Magnific Popup -->
<link rel="stylesheet"
	href="<c:url value='/resources/assets/css/plugins/magnific-popup.css' />">
<!-- Main Style CSS -->
<link rel="stylesheet"
	href="<c:url value='/resources/assets/css/style.css' />">

<base href="${pageContext.servletContext.contextPath }" />
</head>

<body>
	<%@include file="/WEB-INF/views/include/header.jsp"%>

	<!-- Breadcrumb Area Start Here -->
	<div class="breadcrumbs-area position-relative">
		<div class="container">
			<div class="row">
				<div class="col-12 text-center">
					<div class="breadcrumb-content position-relative section-content">
						<h3 class="title-3">Checkout</h3>
						<ul>
							<li><a href="${pageContext.request.contextPath}/home/index">Home</a></li>
							<li>Checkout</li>
						</ul>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- Breadcrumb Area End Here -->
	<!-- Checkout Area Start Here -->
	<div class="checkout-area mt-no-text">
		<div class="container custom-container">
			<div class="row">
				<div class="col-12 col-custom">
					<div class="coupon-accordion">
						<h3>${message}</h3>
					</div>
				</div>
			</div>
			<form:form action="${pageContext.request.contextPath}/shop/checkout"
				method="post" modelAttribute="detailTransaction">
				<div class="row">
					<div class="col-lg-6 col-12 col-custom">
						<div class="checkbox-form">
							<h3>Billing Details</h3>
							<div class="row">
								<div class="col-md-12 col-custom">
									<div class="checkout-form-list">
										<label>Full Name <span class="required">*</span></label>
										<form:input placeholder="" type="text" path="recipientName"
											value="${userLogin.fullName}" />
										<form:errors style="color:red" path="recipientName" />
									</div>
								</div>
								<div class="col-md-12 col-custom">
									<div class="checkout-form-list">
										<label>Address <span class="required">*</span></label>
										<form:input placeholder="" type="text" path="recipientAddress"
											value="${userLogin.address}" />
										<form:errors style="color:red" path="recipientAddress" />
									</div>
								</div>
								<div class="col-md-6 col-custom">
									<div class="checkout-form-list">
										<label>Email Address <span class="required">*</span></label>
										<form:input placeholder="" type="text" path="recipientEmail"
											value="${userLogin.email}" />
										<form:errors style="color:red" path="recipientEmail" />
									</div>
								</div>
								<div class="col-md-6 col-custom">
									<div class="checkout-form-list">
										<label>Phone <span class="required">*</span></label>
										<form:input type="text" path="recipientPhone"
											value="${userLogin.phone}" />
										<form:errors style="color:red" path="recipientPhone" />
									</div>
								</div>
							</div>
							<div class="different-address">
								<div class="order-notes mt-3">
									<div class="checkout-form-list checkout-form-list-2">
										<label>Order Notes</label>
										<form:textarea path="message" id="checkout-mess" cols="30"
											rows="10"
											placeholder="Notes about your order, e.g. special notes for delivery." />
									</div>
								</div>
							</div>
						</div>
					</div>
					<div class="col-lg-6 col-12 col-custom">
						<div class="your-order">
							<h3>Your order</h3>
							<div class="your-order-table table-responsive">
								<table class="table">
									<thead>
										<tr>
											<th class="cart-product-name">Product</th>
											<th class="cart-product-total">Total</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="cart" items="${carts}">
											<tr class="cart_item">
												<td class="cart-product-name">${cart.flower.name}<strong
													class="product-quantity"> × ${cart.quantity}</strong></td>
												<td class="cart-product-total text-center"><span
													class="amount"><fmt:formatNumber
															pattern="###,### VND"
															value="${cart.amount*cart.quantity}" type="currency" /></span>
												</td>
											</tr>
										</c:forEach>

									</tbody>
									<tfoot>
										<tr class="cart-subtotal">
											<th>Cart Subtotal</th>
											<td class="text-center"><span class="amount"
												style="font-weight: 800;"><fmt:formatNumber
														pattern="###,### VND" value="${subTotal}" type="currency" /></span></td>
										</tr>
										<tr class="cart-subtotal">
											<th>Shipping</th>
											<td class="text-center"><span class="amount"
												style="font-weight: 800;"><fmt:formatNumber
														pattern="###,### VND" value="${ship}" type="currency" /></span></td>
										</tr>
										<tr class="order-total">
											<th>Order Total</th>
											<td class="text-center"><strong><span
													class="amount"><fmt:formatNumber
															pattern="###,### VND" value="${subTotal+ship}"
															type="currency" /></span></strong></td>
										</tr>
										<tr class="cart-subtotal">
											<th>Loyalty Point</th>
											<td class="text-center"><span class="amount"
												style="font-weight: 800;"><fmt:formatNumber
														pattern="###,### VND" value="${loyaltyPoint}"
														type="currency" /></span> <a href="#pointModalCenter"
												title="Loyalty Point" data-toggle="modal"
												data-target="#pointModalCenter"> <i
													class="lnr lnr-plus-circle" data-toggle="tooltip"
													data-placement="right" title="Use Loyalty Point"></i>
											</a> <input type="number" name="usepointDisplay"
												value="${loyaltyPoint}" style="display: none"></td>
										</tr>
										<tr class="cart-subtotal">
											<th>Gift Voucher</th>
											<td class="text-center"><span class="amount"
												style="font-weight: 800;"><fmt:formatNumber
														pattern="###,### VND" value="${giftVoucher}"
														type="currency" /></span> <a href="#giftVoucherModalCenter"
												title="Add Gift Voucher" data-toggle="modal"
												data-target="#giftVoucherModalCenter"> <i
													class="lnr lnr-plus-circle" data-toggle="tooltip"
													data-placement="right" title="Add Gift Voucher"></i>
											</a> <input type="number" name="giftvoucherDisplay"
												value="${giftVoucher}" style="display: none"></td>
										</tr>

										<tr class="order-total">
											<th>Tổng tiền</th>
											<td class="text-center"><span class="amount"
												style="font-weight: 800;"><fmt:formatNumber
														pattern="###,### VND"
														value="${subTotal+ship-loyaltyPoint-giftVoucher-onlinePayment}"
														type="currency" /></span></td>
										</tr>
										
												
											
										
									</tfoot>
								</table>
							</div>
							<input  checked type="radio" name="rad" id="gender_male" value="1" style="margin-left: 6px;"> <label style="
    margin-right: 106px;
    font-weight: 500;
">Thanh toán online</label>
      <input type="radio"   name="rad" id="gender_female" value="2"> <label style="font-weight: 500">Thanh toán bằng tiền mặt</label>
							<div class="payment-method">
								<div class="payment-accordion">
									<div class="order-button-payment">
									
										<button
											class="btn flosun-button secondary-btn black-color rounded-0 w-100"
											type="submit">Place Order</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</form:form>
		</div>

		<!-- Modal Loyalty Point -->
		<div class="modal flosun-modal fade" id="pointModalCenter"
			tabindex="-1" role="dialog" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered" role="document">
				<div class="modal-content">
					<button type="button" class="close close-button"
						data-dismiss="modal" aria-label="Close">
						<span class="close-icon" aria-hidden="true">x</span>
					</button>
					<div class="modal-body">
						<div class="container-fluid custom-area">
							<div class="row">
								<div class="col-md-6 col-custom">
									<div class="modal-product-img">
										<a class="w-100" href="#"> <img class="w-100"
											src="https://www.binspiredstudio.com/wp-content/uploads/2018/03/download-1-2.jpg"
											alt="Product">
										</a>
									</div>
								</div>
								<div class="col-md-6 col-custom">
									<div class="modal-product" style="margin-top: 100px">
										<form:form
											action="${pageContext.request.contextPath}/shop/payment"
											method="post">
											<div class="product-content">
												<div class="product-title">
													<h4 class="title">Khách Hàng: ${userLogin.fullName}</h4>
												</div>
												<div class="price-box">
													<span class="regular-price ">Số điểm khả dụng: <fmt:formatNumber
															pattern="###,### VND" value="${userLogin.loyaltyPoint}"
															type="currency" /></span>
												</div>
												<div class="quantity-with-btn">
													<div class="quantity">
														<c:set value="${subTotal+ship-giftVoucher-onlinePayment}"
															var="maxValue" />
														<input type="number" value="0" min="0" step="500"
															max="${userLogin.loyaltyPoint <= maxValue ? userLogin.loyaltyPoint : maxValue}"
															name="usepointModal"> <input type="text"
															name="giftvoucherModal" value="${giftVoucher}"
															style="display: none"> <input type="text"
															name="onlinepaymentModal" value="${onlinePayment}"
															style="display: none"> <span>VND</span>
													</div>
													<div class="add-to_btn">
														<c:if test="${userLogin==null}">
															<a
																class="btn product-cart button-icon flosun-button dark-btn"
																href="${pageContext.request.contextPath}/pages/login">Use
																Loyalty Point</a>
														</c:if>
														<c:if test="${userLogin!=null}">
															<input
																class="btn flosun-button primary-btn rounded-0 black-btn"
																type="submit" value="Use Loyalty Point">
														</c:if>
													</div>
												</div>
											</div>
										</form:form>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- Modal PMH -->
		<div class="modal flosun-modal fade" id="giftVoucherModalCenter"
			tabindex="-1" role="dialog" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered" role="document">
				<div class="modal-content">
					<button type="button" class="close close-button"
						data-dismiss="modal" aria-label="Close">
						<span class="close-icon" aria-hidden="true">x</span>
					</button>
					<div class="modal-body">
						<div class="container-fluid custom-area">
							<div class="row">
								<div class="col-md-6 col-custom">
									<div class="modal-product-img">
										<a class="w-100" href="#"> <img class="w-100"
											src="https://images.rawpixel.com/image_png_social_square/czNmcy1wcml2YXRlL3Jhd3BpeGVsX2ltYWdlcy93ZWJzaXRlX2NvbnRlbnQvam9iMjQxLWJlZS0wMTBhLnBuZw.png?s=DIJRAX47BkYOcR0g3JGaiCVhTBvV8n8ZJ6rZz-OulnI"
											alt="Product">
										</a>
									</div>
								</div>
								<div class="col-md-6 col-custom">
									<div class="modal-product" style="margin-top: 100px">
										<form:form
											action="${pageContext.request.contextPath}/shop/payment"
											method="post">
											<div class="product-content">
												<div class="product-title">
													<h4 class="title">Khách Hàng: ${userLogin.fullName}</h4>
												</div>
												<div class="quantity-with-btn">
													<div class="quantity">
														<label style="font-weight: 700"> Nhập mã Phiếu mua
															hàng: </label> <input type="number" name="usepointModal"
															value="${loyaltyPoint}" style="display: none"> <input
															type="text" name="giftvoucherModal" required> <input
															type="text" name="onlinepaymentModal"
															value="${onlinePayment}" style="display: none">
													</div>
													<div class="add-to_btn">
														<c:if test="${userLogin==null}">
															<a
																class="btn product-cart button-icon flosun-button dark-btn"
																href="${pageContext.request.contextPath}/pages/login">Add
																Gift Voucher</a>
														</c:if>
														<c:if test="${userLogin!=null}">
															<button
																class="btn flosun-button primary-btn rounded-0 black-btn"
																type="submit">Add Gift Voucher</button>
														</c:if>
													</div>
													<div>
														<br>
														<c:if test="${messagePMH!=null}">
															<span style="font-weight: 800;">Thông báo:</span>
															<span style="font-weight: 400; color: red">${messagePMH}</span>
														</c:if>
													</div>
												</div>
											</div>
										</form:form>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>

		<!-- Modal Online Payment -->
		<div class="modal flosun-modal fade" id="onlinePaymentModalCenter"
			tabindex="-1" role="dialog" aria-hidden="true">
			<div class="modal-dialog modal-dialog-centered" role="document">
				<div class="modal-content">
					<button type="button" class="close close-button"
						data-dismiss="modal" aria-label="Close">
						<span class="close-icon" aria-hidden="true">x</span>
					</button>
					<div class="modal-body">
						<div class="container-fluid custom-area">
							<div class="row">
								<div class="col-md-6 col-custom">
									<div class="modal-product-img">
										<a class="w-100" href="#"> <img class="w-100"
											src="https://cdn.searchenginejournal.com/wp-content/uploads/2020/03/the-top-10-most-popular-online-payment-solutions-5e9978d564973.png"
											alt="Product">
										</a>
									</div>
								</div>
								<div class="col-md-6 col-custom">
									<div class="modal-product">
										<form:form
											action="${pageContext.request.contextPath}/shop/payment"
											method="post">
											<div class="product-content">
												<div class="product-title">
													<h4 class="title">Khách Hàng: ${userLogin.fullName}</h4>
													<h5 class="title">Phương thức thanh toán: Paypal</h5>
												</div>
												<div class="quantity-with-btn">
													<div class="quantity">
														<label style="font-weight: 700"> Nhập số tiền
															thanh toán: </label> <input type="number" name="usepointModal"
															value="${loyaltyPoint}" style="display: none"> <input
															type="text" name="giftvoucherModal"
															value="${giftVoucher}" style="display: none"> <input
															type="number" value="0" min="0" step="500"
															max="${subTotal+ship-loyaltyPoint-giftVoucher}"
															name="onlinepaymentModal">
													</div>
													<div class="add-to_btn">
														<c:if test="${userLogin==null}">
															<a
																class="btn product-cart button-icon flosun-button dark-btn"
																href="${pageContext.request.contextPath}/pages/login">Add
																Online Payment</a>
														</c:if>
														<c:if test="${userLogin!=null}">
															<input
																class="btn flosun-button primary-btn rounded-0 black-btn"
																type="submit" value="Add Online Payment">
														</c:if>
													</div>
												</div>
											</div>
										</form:form>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
		<button hidden="true" id="update_modal_giftvoucher_btn"
			data-toggle="modal" data-target="#giftVoucherModalCenter"></button>
	</div>

	<!-- Checkout Area End Here -->

	<%@include file="/WEB-INF/views/include/footer.jsp"%>


	<!-- JS
============================================ -->


	<!-- Modernizer JS -->
	<script
		src="<c:url value='/resources/assets/js/vendor/modernizr-3.7.1.min.js' />"></script>
	<!-- jQuery JS -->
	<script
		src="<c:url value='/resources/assets/js/vendor/jquery-3.5.1.min.js' />"></script>
	<!-- Bootstrap JS -->
	<script
		src="<c:url value='/resources/assets/js/vendor/bootstrap.bundle.min.js' />"></script>

	<!-- Swiper Slider JS -->
	<script
		src="<c:url value='/resources/assets/js/plugins/swiper-bundle.min.js' />"></script>
	<!-- nice select JS -->
	<script
		src="<c:url value='/resources/assets/js/plugins/nice-select.min.js' />"></script>
	<!-- Ajaxchimpt js -->
	<script
		src="<c:url value='/resources/assets/js/plugins/jquery.ajaxchimp.min.js' />"></script>
	<!-- Jquery Ui js -->
	<script
		src="<c:url value='/resources/assets/js/plugins/jquery-ui.min.js' />"></script>
	<!-- Jquery Countdown js -->
	<script
		src="<c:url value='/resources/assets/js/plugins/jquery.countdown.min.js' />"></script>
	<!-- jquery magnific popup js -->
	<script
		src="<c:url value='/resources/assets/js/plugins/jquery.magnific-popup.min.js' />"></script>

	<!-- Main JS -->
	<script src="<c:url value='/resources/assets/js/main.js' />"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.1/jquery.min.js"></script>
	<script type="text/javascript">
		// Get all <li>
		var lis = document.getElementById('navbar_main').querySelectorAll(
				'li a');
		/*  lis.forEach((item, index) => {
		 	
		 	item.className += " active";
		     console.log({ index, item })
		   }); */
		var item = lis[1];
		item.className += " active";
	</script>
	<!-- HIỂN THỊ THÔNG BÁO CÓ LỖI XẢY RA-->
	<c:if test="${addPMH == false}">
		<script type="text/javascript">
			$(document).ready(function() {
				$('#update_modal_giftvoucher_btn').click();
			});
		</script>
	</c:if>

</body>
</html>