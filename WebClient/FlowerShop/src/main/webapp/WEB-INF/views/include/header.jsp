<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jstl/core_rt" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<!DOCTYPE html>
<html class="no-js" lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <title>DND Flower Shop</title>
    <meta name="robots" content="noindex, follow" />
    <meta name="description" content="">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <!-- Favicon -->
    <link rel="shortcut icon" type="image/x-icon" href="assets/images/favicon.ico">

    <!-- CSS
	============================================ -->
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="<c:url value='/resources/assets/css/vendor/bootstrap.min.css' />">
    
    <!-- Test -->
    <link rel="stylesheet" href="<c:url value='/resources/assets/css/style.css' />">
    <!-- Font Awesome CSS -->
    <link rel="stylesheet" href="<c:url value='/resources/assets/css/vendor/font.awesome.min.css' />">
    <!-- Linear Icons CSS -->
    <link rel="stylesheet" href="<c:url value='/resources/assets/css/vendor/linearicons.min.css' />">
    <!-- Swiper CSS -->
    <link rel="stylesheet" href="<c:url value='/resources/assets/css/plugins/swiper-bundle.min.css' />">
    
    <!-- Animation CSS -->
    <link rel="stylesheet" href="<c:url value='/resources/assets/css/plugins/animate.min.css' />">
    <!-- Jquery ui CSS -->
    <link rel="stylesheet" href="<c:url value='/resources/assets/css/plugins/jquery-ui.min.css' />">
    <!-- Nice Select CSS -->
    <link rel="stylesheet" href="<c:url value='/resources/assets/css/plugins/nice-select.min.css' />">
    <!-- Magnific Popup -->
    <link rel="stylesheet" href="<c:url value='/resources/assets/css/plugins/magnific-popup.css' />">
    <!-- Main Style CSS -->
    <link rel="stylesheet" href="<c:url value='/resources/assets/css/style.css' />">
    
	<base href="${pageContext.servletContext.contextPath }" />
</head>

<body>

    <!--===== Pre-Loading area Start =====-->
<!--     <div id="preloader">
        <div class="preloader">
            <div class="spinner-block">
                <h1 class="spinner spinner-3 mb-0">.</h1>
            </div>
        </div>
    </div> -->
    <!--==== Pre-Loading Area End ====-->

    <!-- Header Area Start Here -->
    <header class="main-header-area">
        <!-- Main Header Area Start -->
        <div class="main-header header-sticky" id="header_main">
            <div class="container-fluid">
                <div class="row align-items-center">
                    <div class="col-lg-2 col-xl-2 col-md-6 col-6 col-custom">
                        <div class="header-logo d-flex align-items-center">
                            <a href="${pageContext.request.contextPath}/home/index">
                                <img class="img-full" src="${pageContext.request.contextPath}/resources/assets/images/logo/logo.jpg" alt="Header Logo">
                            </a>
                        </div>
                    </div>
                    <div class="col-lg-8 d-none d-lg-flex justify-content-center col-custom">
                        <nav class="main-nav d-none d-lg-flex">
                            <ul class="nav" id="navbar_main">
                                <li>
                                    <a href="${pageContext.request.contextPath}/home/index">
                                        <span class="menu-text"> Home</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="${pageContext.request.contextPath}/shop/main">
                                        <span class="menu-text">Shop</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="${pageContext.request.contextPath}/blog/main">
                                        <span class="menu-text"> Blog</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="${pageContext.request.contextPath}/pages/frequently_questions">
                                        <span class="menu-text"> Pages</span>
                                        <i class="fa fa-angle-down"></i>
                                    </a>
                                    <ul class="dropdown-submenu dropdown-hover">
                                      <!-- chọn chức năng khi phân quyền -->
                                    	<c:choose>
	                    					<c:when test="${userLogin==null}">		
		                                        <li><a href="${pageContext.request.contextPath}/pages/frequently_questions">FAQ</a></li>
		                                        <li><a href="${pageContext.request.contextPath}/pages/login">Login</a></li>
		                                        <li><a href="${pageContext.request.contextPath}/pages/register">Register</a></li>
	                                    	</c:when>
	                                        <c:otherwise>   
	                                        	<li><a href="${pageContext.request.contextPath}/pages/my_account">My Account</a></li>
		                                        <li><a href="${pageContext.request.contextPath}/pages/frequently_questions">FAQ</a></li>
		                                    </c:otherwise>
                    					</c:choose>
                                    </ul>
                                </li>
                                <li>
                                    <a href="${pageContext.request.contextPath}/pages/about_us">
                                        <span class="menu-text"> About Us</span>
                                    </a>
                                </li>
                                <li>
                                    <a href="${pageContext.request.contextPath}/pages/contact_us">
                                        <span class="menu-text">Contact Us</span>
                                    </a>
                                </li>
                            </ul>
                        </nav>
                    </div>
                    <div class="col-lg-2 col-md-6 col-6 col-custom">
                        <div class="header-right-area main-nav">
                            <ul class="nav">
                            	<!-- Cart navbar -->
                                <li class="minicart-wrap">
	                            	<c:if test="${userLogin==null}">
					                    <a href="${pageContext.request.contextPath}/pages/login" class="minicart-btn toolbar-btn">
                                        <i class="fa fa-shopping-cart"></i>
                                        <!-- <span class="cart-item_count"></span> -->
                                    </a>
					                </c:if>
	                                <c:if test="${userLogin!=null}">
	                                    <a href="${pageContext.request.contextPath}/shop/cart" class="minicart-btn toolbar-btn">
	                                        <i class="fa fa-shopping-cart"></i>
	                                        <span class="cart-item_count">${sizelistCarts}</span>
	                                    </a>
	                                    <div class="cart-item-wrapper dropdown-sidemenu dropdown-hover-2">
	                                        
	                                        <c:forEach var="cart" items="${listCarts}">
	                                        <div class="single-cart-item">
	                                            <div class="cart-img">
	                                                <a href="${pageContext.request.contextPath}/shop/cart"><img src="${pageContext.request.contextPath}/resources/images/flower/${cart.flower.images[0]}" alt=""></a>
	                                            </div>
	                                            <div class="cart-text">
	                                                <h5 class="title"><a href="${pageContext.request.contextPath}/shop/cart">${cart.flower.name}</a></h5>
	                                                <div class="cart-text-btn">
	                                                    <div class="cart-qty">
	                                                        <span>${cart.quantity}×</span>
	                                                        <span class="cart-price"><fmt:formatNumber pattern="###,### VND"  value="${cart.amount}" type="currency" /></span>
	                                                    </div>
	                                                    <button type="button"><i class="ion-trash-b"></i></button>
	                                                </div>
	                                            </div>
                                                <label>
                                                    <input type="checkbox" disabled name="selected${cart.id}" <c:if test="${cart.isSelected==true}">checked=checked</c:if>
                                                           style="display: flex; width: 20px; height: 20px;">
                                                </label>
	                                        </div>
	                                        </c:forEach>
	                                        <div class="cart-price-total d-flex justify-content-between">
	                                            <h5>Total (No Shipping) :</h5>
	                                            <h5><fmt:formatNumber pattern="###,### VND"  value="${totalCarts}" type="currency" /></h5>
	                                        </div>
	                                        <div class="cart-links d-flex justify-content-between">
	                                            <a class="btn product-cart button-icon flosun-button dark-btn" href="${pageContext.request.contextPath}/shop/cart">View cart</a>
	                                            <a class="btn flosun-button secondary-btn rounded-0" href="${pageContext.request.contextPath}/shop/checkout">Checkout</a>
	                                        </div>
	                                    </div>
					                </c:if>
                                </li>
                                <!-- search -->
                                <li class="sidemenu-wrap">
                                    <a><i class="fa fa-search"></i> </a>
                                    <ul class="dropdown-sidemenu dropdown-hover-2 dropdown-search">
                                        <li>
                                            <form:form action="${pageContext.request.contextPath}/shop/search">
                                                <input name="search" id="search" placeholder="Search" type="text">
                                                <button type="submit"><i class="fa fa-search"></i></button>
                                            </form:form>
                                        </li>
                                    </ul>
                                </li>
                                <li class="account-menu-wrap d-none d-lg-flex">
                                    <a class="off-canvas-menu-btn">
                                        <i class="fa fa-bars"></i>
                                    </a>
                                </li>
                                <li class="mobile-menu-btn d-lg-none">
                                    <a class="off-canvas-btn" >
                                        <i class="fa fa-bars"></i>
                                    </a>
                                </li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Main Header Area End -->
        <!-- off-canvas menu start -->
        <aside class="off-canvas-wrapper" id="mobileMenu">
            <div class="off-canvas-overlay"></div>
            <div class="off-canvas-inner-content">
                <div class="btn-close-off-canvas">
                    <i class="fa fa-times"></i>
                </div>
                <div class="off-canvas-inner">
                    <div class="search-box-offcanvas">
                        <form>
                            <input type="text" placeholder="Search product...">
                            <button class="search-btn"><i class="fa fa-search"></i></button>
                        </form>
                    </div>
                    <!--  mobile menu start -->
                    <div class="mobile-navigation">
                       <!--  mobile menu navigation start -->
                        <nav>
                            <ul class="mobile-menu">
                                <li class="menu-item-has-children"><a href="#">Home</a>
                                    <ul class="dropdown">
                                        <li><a href="indexl">Home Page 1</a></li>
                                        <li><a href="index-2l">Home Page 2</a></li>
                                        <li><a href="index-3l">Home Page 3</a></li>
                                        <li><a href="index-4l">Home Page 4</a></li>
                                    </ul>
                                </li>
                                <li class="menu-item-has-children"><a href="#">Shop</a>
                                    <ul class="megamenu dropdown">
                                        <li class="mega-title has-children"><a href="#">Shop Layouts</a>
                                            <ul class="dropdown">
                                                <li><a href="shopl">Shop Left Sidebar</a></li>
                                                <li><a href="shop-right-sidebarl">Shop Right Sidebar</a></li>
                                                <li><a href="shop-list-leftl">Shop List Left Sidebar</a></li>
                                                <li><a href="shop-list-rightl">Shop List Right Sidebar</a></li>
                                                <li><a href="shop-fullwidthl">Shop Full Width</a></li>
                                            </ul>
                                        </li>
                                        <li class="mega-title has-children"><a href="#">Product Details</a>
                                            <ul class="dropdown">
                                                <li><a href="product-detailsl">Single Product Details</a></li>
                                                <li><a href="variable-product-detailsl">Variable Product Details</a></li>
                                                <li><a href="external-product-detailsl">External Product Details</a></li>
                                                <li><a href="gallery-product-detailsl">Gallery Product Details</a></li>
                                                <li><a href="countdown-product-detailsl">Countdown Product Details</a></li>
                                            </ul>
                                        </li>
                                        <li class="mega-title has-children"><a href="#">Others</a>
                                            <ul class="dropdown">
                                                <li><a href="error404l">Error 404</a></li>
                                                <li><a href="comparel">Compare Page</a></li>
                                                <li><a href="cartl">Cart Page</a></li>
                                                <li><a href="checkoutl">Checkout Page</a></li>
                                                <li><a href="wishlistl">Wish List Page</a></li>
                                            </ul>
                                        </li>
                                    </ul>
                                </li>
                                <li class="menu-item-has-children "><a href="#">Blog</a>
                                    <ul class="dropdown">
                                        <li><a href="blogl">Blog Left Sidebar</a></li>
                                        <li><a href="blog-list-right-sidebarl">Blog List Right Sidebar</a></li>
                                        <li><a href="blog-list-fullwidthl">Blog List Fullwidth</a></li>
                                        <li><a href="blog-gridl">Blog Grid Page</a></li>
                                        <li><a href="blog-grid-right-sidebarl">Blog Grid Right Sidebar</a></li>
                                        <li><a href="blog-grid-fullwidthl">Blog Grid Fullwidth</a></li>
                                        <li><a href="blog-details-sidebarl">Blog Details Sidebar Page</a></li>
                                        <li><a href="blog-details-fullwidthl">Blog Details Fullwidth Page</a></li>
                                    </ul>
                                </li>
                                <li class="menu-item-has-children "><a href="#">Pages</a>
                                    <ul class="dropdown">
                                        <li><a href="frequently-questionsl">FAQ</a></li>
                                        <li><a href="my-accountl">My Account</a></li>
                                        <li><a href="login-registerl">login &amp; register</a></li>
                                    </ul>
                                </li>
                                <li><a href="about-usl">About Us</a></li>
                                <li><a href="contact-usl">Contact</a></li>
                            </ul>
                        </nav>
                      <!--   mobile menu navigation end -->
                    </div>
                   <!--  mobile menu end  -->
                    <div class="offcanvas-widget-area">
                        <div class="switcher">
                            <div class="language">
                                <span class="switcher-title">Language: </span>
                                <div class="switcher-menu">
                                    <ul>
                                        <li><a href="#">English</a>
                                            <ul class="switcher-dropdown">
                                                <li><a href="#">German</a></li>
                                                <li><a href="#">French</a></li>
                                            </ul>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="currency">
                                <span class="switcher-title">Currency: </span>
                                <div class="switcher-menu">
                                    <ul>
                                        <li><a href="#">$ USD</a>
                                            <ul class="switcher-dropdown">
                                                <li><a href="#">€ EUR</a></li>
                                            </ul>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="top-info-wrap text-left text-black">
                            <ul class="address-info">
                                <li>
                                    <i class="fa fa-phone"></i>
                                    <a href="info@yourdomain.com">(1245) 2456 012</a>
                                </li>
                                <li>
                                    <i class="fa fa-envelope"></i>
                                    <a href="info@yourdomain.com">info@yourdomain.com</a>
                                </li>
                            </ul>
                            <div class="widget-social">
                                <a class="facebook-color-bg" title="Facebook-f" href="#"><i class="fa fa-facebook-f"></i></a>
                                <a class="twitter-color-bg" title="Twitter" href="#"><i class="fa fa-twitter"></i></a>
                                <a class="linkedin-color-bg" title="Linkedin" href="#"><i class="fa fa-linkedin"></i></a>
                                <a class="youtube-color-bg" title="Youtube" href="#"><i class="fa fa-youtube"></i></a>
                                <a class="vimeo-color-bg" title="Vimeo" href="#"><i class="fa fa-vimeo"></i></a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </aside>
        <!-- off-canvas menu end -->
        <!-- off-canvas menu start -->
        <aside class="off-canvas-menu-wrapper" id="sideMenu">
            <div class="off-canvas-overlay"></div>
            <div class="off-canvas-inner-content">
                <div class="off-canvas-inner">
                    <div class="btn-close-off-canvas">
                        <i class="fa fa-times"></i>
                    </div>
                    <!-- offcanvas widget area start -->
                    <div class="offcanvas-widget-area">
                        <ul class="menu-top-menu">
                            <li><a href="${pageContext.request.contextPath}/pages/about_us">About Us</a></li>
                        </ul>
                        <p class="desc-content">Đến với chúng tôi bạn sẽ thật sự an tâm với thái độ phục vụ nhiệt tình của đội ngũ nhân viên năng động, nhiệt tình và nhiều năm kinh nghiệm trong ngành, họ sẽ tư vấn cho quý khách hàng những bó hoa, những món quà phù hợp và ý nghĩa. Hướng đến sự chuyên nghiệp trong tất cả các quy trình là công việc mà chúng tôi đặt lên hàng đầu nhằm đem lại sự hài lòng tốt nhất cho bạn.</p>
                        <div class="switcher">
                            <div class="language">
                                <span class="switcher-title">Language: </span>
                                <div class="switcher-menu">
                                    <ul>
                                        <li><a href="#">English</a>
                                            <ul class="switcher-dropdown">
                                                <li><a href="#">VietNam</a></li>
                                                <li><a href="#">French</a></li>
                                            </ul>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                            <div class="currency">
                                <span class="switcher-title">Currency: </span>
                                <div class="switcher-menu">
                                    <ul>
                                        <li><a href="#">$ USD</a>
                                            <ul class="switcher-dropdown">
                                                <li><a href="#">đ VND</a></li>
                                            </ul>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="top-info-wrap text-left text-black">
                            <ul class="address-info">
                                <li>
                                    <i class="fa fa-phone"></i>
                                    <a href="dndflowershop@gmail.com">057 1405 128</a>
                                </li>
                                <li>
                                    <i class="fa fa-envelope"></i>
                                    <a href="dndflowershop@gmail.com">dndflowershop@gmail.com</a>
                                </li>
                            </ul>
                            <div class="widget-social">
                                <a class="facebook-color-bg" title="Facebook-f" href="#"><i class="fa fa-facebook-f"></i></a>
                                <a class="twitter-color-bg" title="Twitter" href="#"><i class="fa fa-twitter"></i></a>
                                <a class="linkedin-color-bg" title="Linkedin" href="#"><i class="fa fa-linkedin"></i></a>
                                <a class="youtube-color-bg" title="Youtube" href="#"><i class="fa fa-youtube"></i></a>
                                <a class="vimeo-color-bg" title="Vimeo" href="#"><i class="fa fa-vimeo"></i></a>
                            </div>
                        </div>
                    </div>
                    <!-- offcanvas widget area end -->
                </div>
            </div>
        </aside>
        <!-- off-canvas menu end -->
    </header>
    <!-- Header Area End Here -->
    
    
    
    
    
     <!-- JS
============================================ -->


    <!-- Modernizer JS -->
    <script src="<c:url value='/resources/assets/js/vendor/modernizr-3.7.1.min.js' />"></script>
    <!-- jQuery JS -->
    <script src="<c:url value='/resources/assets/js/vendor/jquery-3.5.1.min.js' />"></script>
    <!-- Bootstrap JS -->
    <script src="<c:url value='/resources/assets/js/vendor/bootstrap.bundle.min.js' />"></script>

    <!-- Swiper Slider JS -->
    <script src="<c:url value='/resources/assets/js/plugins/swiper-bundle.min.js' />"></script>
    <!-- nice select JS -->
    <script src="<c:url value='/resources/assets/js/plugins/nice-select.min.js' />"></script>
    <!-- Ajaxchimpt js -->
    <script src="<c:url value='/resources/assets/js/plugins/jquery.ajaxchimp.min.js' />"></script>
    <!-- Jquery Ui js -->
    <script src="<c:url value='/resources/assets/js/plugins/jquery-ui.min.js' />"></script>
    <!-- Jquery Countdown js -->
    <script src="<c:url value='/resources/assets/js/plugins/jquery.countdown.min.js' />"></script>
    <!-- jquery magnific popup js -->
    <script src="<c:url value='/resources/assets/js/plugins/jquery.magnific-popup.min.js' />"></script>

    <!-- Main JS -->
    <script src="<c:url value='/resources/assets/js/main.js' />"></script>
</body>
</html>