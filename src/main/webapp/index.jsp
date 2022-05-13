<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Orange-poolvilla</title>
    <meta name="description" content="Free Bootstrap Theme by uicookies.com">
    <meta name="keywords" content="free website templates, free bootstrap themes, free template, free bootstrap, free website template">
    
    <link href="https://fonts.googleapis.com/css?family=Open+Sans:300,400" rel="stylesheet">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/template/css/styles-merged.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/template/css/style.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/template/css/custom.css">

  </head>
  
  	<!-- jquery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    
  <body>

 	<!-- START: header -->
	       <div id="includeHeader"></div>
	<!-- END: header -->
	
  <section class="probootstrap-slider flexslider">
    <div class="probootstrap-wrap-banner">
      <div class="container">
        <div class="row">
          <div class="col-md-8 col-md-offset-2">

            <div class="probootstrap-home-search probootstrap-animate">
              <form action="${pageContext.request.contextPath}/template/#" method="post">
                <h2 class="heading">Search your next dream home here</h2>
                <div class="probootstrap-field-group">
                  <div class="probootstrap-fields">
                    
                    <div class="search-field">
                      <i class="icon-location2"></i>
                      <input type="text" class="form-control" placeholder="Enter address, ZIP code, Neighborhoods">
                    </div>
                    <div class="search-category">
                      <i class="icon-chevron-down"></i>
                      <select name="#" id="" class="form-control">
                        <option value="">For Rent</option>
                        <option value="">For Sale</option>
                      </select>
                    </div>
                  </div>
                  <button class="btn btn-success" type="submit"><i class="icon-magnifying-glass t2"></i> Start Search</button>
                </div>
              </form>
              <p class="mb0 text-left"><small>A free HTML5 template by <a href="https://uicookies.com/">uicookies.com</a> under license <a href="https://uicookies.com/license">Creative Commons 3.0</a></small> </p>
            </div>

          </div>
        </div>
      </div>
    </div>
    <ul class="slides">
      <li style="background-image: url(${pageContext.request.contextPath}/template/img/slider_1.jpg);" class="overlay"></li>
      <li style="background-image: url(${pageContext.request.contextPath}/template/img/slider_4.jpg);" class="overlay"></li>
      <li style="background-image: url(${pageContext.request.contextPath}/template/img/slider_2.jpg);" class="overlay"></li>
    </ul>
  </section>
  <!-- END: slider  -->

  <section class="probootstrap-section probootstrap-section-lighter">
    <div class="container">
      <div class="row">
        <div class="col-md-4">
          <div class="probootstrap-card text-center probootstrap-animate">
            <div class="probootstrap-card-media svg-sm colored">
              <img src="${pageContext.request.contextPath}/template/img/flaticon/svg/001-prize.svg" class="svg" alt="Free HTML5 Template by uicookies.com">
            </div>
            <div class="probootstrap-card-text">
              <h2 class="probootstrap-card-heading">Award Winning Brooker</h2>
              <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts.</p>
              <p><a href="${pageContext.request.contextPath}/template/#">Find out more</a></p>
            </div>
          </div>
        </div>
        <div class="col-md-4">
          <div class="probootstrap-card text-center probootstrap-animate">
            <div class="probootstrap-card-media svg-sm colored">
              <img src="${pageContext.request.contextPath}/template/img/flaticon/svg/005-new.svg" class="svg" alt="Free HTML5 Template by uicookies.com">
            </div>
            <div class="probootstrap-card-text">
              <h2 class="probootstrap-card-heading">New Houses</h2>
              <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts.</p>
              <p><a href="${pageContext.request.contextPath}/template/#">Find out more</a></p>
            </div>
          </div>
        </div>
        <div class="col-md-4">
          <div class="probootstrap-card text-center  probootstrap-animate">
            <div class="probootstrap-card-media svg-sm colored">
              <img src="${pageContext.request.contextPath}/template/img/flaticon/svg/006-coin.svg" class="svg" alt="Free HTML5 Template by uicookies.com">
            </div>
            <div class="probootstrap-card-text">
              <h2 class="probootstrap-card-heading">Affordable Houses</h2>
              <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts.</p>
              <p><a href="${pageContext.request.contextPath}/template/#">Find out more</a></p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>
  <!-- END: section -->

  <section class="probootstrap-section">
    <div class="container">
      <div class="row heading">
        <h2 class="mt0 mb50 text-center">Explore Our Neighborhoods</h2>
      </div>
      <div class="row probootstrap-gutter10">
        <div class="col-md-6 col-sm-6">
          <a href="${pageContext.request.contextPath}/template/#" class="probootstrap-hover-overlay">
            <img src="${pageContext.request.contextPath}/template/img/slider_2.jpg" alt="Free Bootstrap Template by uicookies.com" class="img-responsive">
            <div class="probootstrap-text-overlay">
              <h3>New York</h3>
              <p>430 Properties</p>
            </div>
          </a>
        </div>
        <div class="col-md-6 col-sm-6">
          <a href="${pageContext.request.contextPath}/template/#" class="probootstrap-hover-overlay">
            <img src="${pageContext.request.contextPath}/template/img/slider_1.jpg" alt="Free Bootstrap Template by uicookies.com" class="img-responsive">
            <div class="probootstrap-text-overlay">
              <h3>San Francisco</h3>
              <p>294 Properties</p>
            </div>
          </a>
        </div>
        <div class="clearfix visible-sm-block"></div>

        <div class="col-md-4 col-sm-6">
          <a href="#" class="probootstrap-hover-overlay">
            <img src="${pageContext.request.contextPath}/template/img/slider_3.jpg" alt="Free Bootstrap Template by uicookies.com" class="img-responsive">
            <div class="probootstrap-text-overlay">
              <h3>Brooklyn</h3>
              <p>300 Properties</p>
            </div>
          </a>
        </div>
        <div class="col-md-4 col-sm-6">
          <a href="#" class="probootstrap-hover-overlay">
            <img src="${pageContext.request.contextPath}/template/img/slider_4.jpg" alt="Free Bootstrap Template by uicookies.com" class="img-responsive">
            <div class="probootstrap-text-overlay">
              <h3>Chicago</h3>
              <p>268 Properties</p>
            </div>
          </a>
        </div>
        <div class="clearfix visible-sm-block"></div>
        <div class="col-md-4 col-sm-6">
          <a href="${pageContext.request.contextPath}/template/#" class="probootstrap-hover-overlay">
            <img src="${pageContext.request.contextPath}/template/img/slider_2.jpg" alt="Free Bootstrap Template by uicookies.com" class="img-responsive">
            <div class="probootstrap-text-overlay">
              <h3>Los Angeles</h3>
              <p>342 Properties</p>
            </div>
          </a>
        </div>

      </div>
    </div>
  </section>
  <!-- END: section -->

  <section class="probootstrap-section probootstrap-bg" style="background-image: url(${pageContext.request.contextPath}/template/img/slider_2.jpg);">
    <div class="container text-center probootstrap-animate" data-animate-effect="fadeIn">
      <h2 class="heading">Best Home &amp; Properties</h2>
      <p class="sub-heading">Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts.</p>
      <p><a href="${pageContext.request.contextPath}/template/#" class="btn btn-primary mb10">Find out more</a></p>
    </div>
  </section>
  <!-- END: section -->

  <section class="probootstrap-section probootstrap-section-lighter">
    <div class="container">
      <div class="row heading">
        <h2 class="mt0 mb50 text-center">Featured Listing</h2>
      </div>
      <div class="row">
        <div class="col-md-4 col-sm-6">
          <div class="probootstrap-card probootstrap-listing">
            <div class="probootstrap-card-media">
              <img src="${pageContext.request.contextPath}/template/img/slider_1.jpg" class="img-responsive" alt="Free HTML5 Template by uicookies.com">
              <a href="${pageContext.request.contextPath}/template/#" class="probootstrap-love"><i class="icon-heart"></i></a>
            </div>
            <div class="probootstrap-card-text">
              <h2 class="probootstrap-card-heading"><a href="${pageContext.request.contextPath}/template/#">3 Bed Room Property for Sale</a></h2>
              <div class="probootstrap-listing-location">
                <i class="icon-location2"></i> <span>360 W Wellington Chicago, IL 60657</span>
              </div>
              <div class="probootstrap-listing-category for-sale"><span>for sale</span></div>
              <div class="probootstrap-listing-price"><strong>$ 1,121,000</strong> / month</div>
            </div>
            <div class="probootstrap-card-extra">
              <ul>
                <li>
                  Area
                  <span>2400 m2</span>
                </li>
                <li>
                  Beds
                  <span>3</span>
                </li>
                <li>
                  Baths
                  <span>2</span>
                </li>
                <li>
                  Garages
                  <span>1</span>
                </li>
              </ul>
            </div>
          </div>
          <!-- END listing -->
        </div>
        <div class="col-md-4 col-sm-6">
          <div class="probootstrap-card probootstrap-listing">
            <div class="probootstrap-card-media">
              <img src="${pageContext.request.contextPath}/template/img/slider_2.jpg" class="img-responsive" alt="Free HTML5 Template by uicookies.com">
              <a href="${pageContext.request.contextPath}/template/#" class="probootstrap-love"><i class="icon-heart"></i></a>
            </div>
            <div class="probootstrap-card-text">
              <h2 class="probootstrap-card-heading"><a href="#">3 Bed Room Property for Sale</a></h2>
              <div class="probootstrap-listing-location">
                <i class="icon-location2"></i> <span>360 W Wellington Chicago, IL 60657</span>
              </div>
              <div class="probootstrap-listing-category for-sale"><span>for sale</span></div>
              <div class="probootstrap-listing-price"><strong>$ 250,000</strong> / month</div>
            </div>
            <div class="probootstrap-card-extra">
              <ul>
                <li>
                  Area
                  <span>2400 m2</span>
                </li>
                <li>
                  Beds
                  <span>3</span>
                </li>
                <li>
                  Baths
                  <span>2</span>
                </li>
                <li>
                  Garages
                  <span>1</span>
                </li>
              </ul>
            </div>
          </div>
          <!-- END listing -->
        </div>
        <div class="clearfix visible-sm-block"></div>
        <div class="col-md-4 col-sm-6">
          <div class="probootstrap-card probootstrap-listing">
            <div class="probootstrap-card-media">
              <img src="${pageContext.request.contextPath}/template/img/slider_3.jpg" class="img-responsive" alt="Free HTML5 Template by uicookies.com">
              <a href="${pageContext.request.contextPath}/template/#" class="probootstrap-love"><i class="icon-heart"></i></a>
            </div>
            <div class="probootstrap-card-text">
              <h2 class="probootstrap-card-heading"><a href="#">3 Bed Room Property for Sale</a></h2>
              <div class="probootstrap-listing-location">
                <i class="icon-location2"></i> <span>360 W Wellington Chicago, IL 60657</span>
              </div>
              <div class="probootstrap-listing-category for-rent"><span>for rent</span></div>
              <div class="probootstrap-listing-price"><strong>$ 60,000</strong> / month</div>
            </div>
            <div class="probootstrap-card-extra">
              <ul>
                <li>
                  Area
                  <span>2400 m2</span>
                </li>
                <li>
                  Beds
                  <span>3</span>
                </li>
                <li>
                  Baths
                  <span>2</span>
                </li>
                <li>
                  Garages
                  <span>1</span>
                </li>
              </ul>
            </div>
          </div>
          <!-- END listing -->
        </div>
        <div class="col-md-4 col-sm-6">
          <div class="probootstrap-card probootstrap-listing">
            <div class="probootstrap-card-media">
              <img src="${pageContext.request.contextPath}/template/img/slider_4.jpg" class="img-responsive" alt="Free HTML5 Template by uicookies.com">
              <a href="${pageContext.request.contextPath}/template/#" class="probootstrap-love"><i class="icon-heart"></i></a>
            </div>
            <div class="probootstrap-card-text">
              <h2 class="probootstrap-card-heading"><a href="${pageContext.request.contextPath}/template/#">3 Bed Room Property for Sale</a></h2>
              <div class="probootstrap-listing-location">
                <i class="icon-location2"></i> <span>360 W Wellington Chicago, IL 60657</span>
              </div>
              <div class="probootstrap-listing-category for-sale"><span>for sale</span></div>
              <div class="probootstrap-listing-price"><strong>$ 125,000</strong> / month</div>
            </div>
            <div class="probootstrap-card-extra">
              <ul>
                <li>
                  Area
                  <span>2400 m2</span>
                </li>
                <li>
                  Beds
                  <span>3</span>
                </li>
                <li>
                  Baths
                  <span>2</span>
                </li>
                <li>
                  Garages
                  <span>1</span>
                </li>
              </ul>
            </div>
          </div>
          <!-- END listing -->
        </div>
        <div class="clearfix visible-sm-block"></div>
        <div class="col-md-4 col-sm-6">
          <div class="probootstrap-card probootstrap-listing">
            <div class="probootstrap-card-media">
              <img src="${pageContext.request.contextPath}/template/img/slider_2.jpg" class="img-responsive" alt="Free HTML5 Template by uicookies.com">
              <a href="${pageContext.request.contextPath}/template/#" class="probootstrap-love"><i class="icon-heart"></i></a>
            </div>
            <div class="probootstrap-card-text">
              <h2 class="probootstrap-card-heading"><a href="${pageContext.request.contextPath}/template/#">3 Bed Room Property for Sale</a></h2>
              <div class="probootstrap-listing-location">
                <i class="icon-location2"></i> <span>360 W Wellington Chicago, IL 60657</span>
              </div>
              <div class="probootstrap-listing-category for-sale"><span>for sale</span></div>
              <div class="probootstrap-listing-price"><strong>$ 125,000</strong> / month</div>
            </div>
            <div class="probootstrap-card-extra">
              <ul>
                <li>
                  Area
                  <span>2400 m2</span>
                </li>
                <li>
                  Beds
                  <span>3</span>
                </li>
                <li>
                  Baths
                  <span>2</span>
                </li>
                <li>
                  Garages
                  <span>1</span>
                </li>
              </ul>
            </div>
          </div>
          <!-- END listing -->
        </div>
        <div class="col-md-4 col-sm-6">
          <div class="probootstrap-card probootstrap-listing">
            <div class="probootstrap-card-media">
              <img src="${pageContext.request.contextPath}/template/img/slider_1.jpg" class="img-responsive" alt="Free HTML5 Template by uicookies.com">
              <a href="${pageContext.request.contextPath}/template/#" class="probootstrap-love"><i class="icon-heart"></i></a>
            </div>
            <div class="probootstrap-card-text">
              <h2 class="probootstrap-card-heading"><a href="${pageContext.request.contextPath}/template/#">3 Bed Room Property for Sale</a></h2>
              <div class="probootstrap-listing-location">
                <i class="icon-location2"></i> <span>360 W Wellington Chicago, IL 60657</span>
              </div>
              <div class="probootstrap-listing-category for-sale"><span>for sale</span></div>
              <div class="probootstrap-listing-price"><strong>$ 125,000</strong> / month</div>
            </div>
            <div class="probootstrap-card-extra">
              <ul>
                <li>
                  Area
                  <span>2400 m2</span>
                </li>
                <li>
                  Beds
                  <span>3</span>
                </li>
                <li>
                  Baths
                  <span>2</span>
                </li>
                <li>
                  Garages
                  <span>1</span>
                </li>
              </ul>
            </div>
          </div>
          <!-- END listing -->
        </div>
      </div>
    </div>
  </section>

  <section class="probootstrap-half reverse">
    <div class="image-wrap">
      <div class="image" style="background-image: url(${pageContext.request.contextPath}/template/img/slider_5.jpg);"></div>
    </div>
    <div class="text">
      <p class="mb10 subtitle">Why Choose Us</p>
      <h3 class="mt0 mb40">You Will Love Our Services</h3>
      <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts. Separated they live in Bookmarksgrove right at the coast of the Semantics, a large language ocean.</p>
      <p class="mb50">A small river named Duden flows by their place and supplies it with the necessary regelialia. It is a paradisematic country, in which roasted parts of sentences fly into your mouth.</p>
      <p><a href="${pageContext.request.contextPath}/template/#" class="btn btn-primary mb10">Find out more</a></p>
    </div>
  </section>

  <section class="probootstrap-section">
    <div class="container">
      <div class="row heading">
        <h2 class="mt0 mb50 text-center">Our Services</h2>
      </div>
      <div class="row">
        <div class="col-lg-4 col-md-4 col-sm-6 col-xs-12 probootstrap-animate">
          <div class="service text-center">
            <div class="icon"><i class="icon-list2"></i></div>
            <h2 class="heading">Property Listing</h2>
            <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts.</p>
          </div>
        </div>
        <div class="col-lg-4 col-md-4 col-sm-6 col-xs-12 probootstrap-animate">
          <div class="service text-center">
            <div class="icon"><i class="icon-power-cord"></i></div>
            <h2 class="heading">Property Management</h2>
            <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts.</p>
          </div>
        </div>
        <div class="clearfix visible-sm-block"></div>
        <div class="col-lg-4 col-md-4 col-sm-6 col-xs-12 probootstrap-animate">
          <div class="service text-center">
            <div class="icon"><i class="icon-price-tag2"></i></div>
            <h2 class="heading">Renting Properties</h2>
            <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts.</p>
          </div>
        </div>
        <div class="clearfix visible-lg-block visible-md-block"></div>
        <div class="col-lg-4 col-md-4 col-sm-6 col-xs-12 probootstrap-animate">
          <div class="service text-center">
            <div class="icon"><i class="icon-direction"></i></div>
            <h2 class="heading">Selling Properties</h2>
            <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts.</p>
          </div>
        </div>
        <div class="clearfix visible-sm-block"></div>
        <div class="col-lg-4 col-md-4 col-sm-6 col-xs-12 probootstrap-animate">
          <div class="service text-center">
            <div class="icon"><i class="icon-home3"></i></div>
            <h2 class="heading">Brook A Property</h2>
            <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts.</p>
          </div>
        </div>
        <div class="col-lg-4 col-md-4 col-sm-6 col-xs-12 probootstrap-animate">
          <div class="service text-center">
            <div class="icon"><i class="icon-magnifying-glass"></i></div>
            <h2 class="heading">Search Property</h2>
            <p>Far far away, behind the word mountains, far from the countries Vokalia and Consonantia, there live the blind texts.</p>
          </div>
        </div>
        <div class="clearfix visible-lg-block visible-md-block"></div>
      </div>
    </div>
  </section>

  <!-- END: section -->

  <section class="probootstrap-section probootstrap-section-lighter">
    <div class="container">
      <div class="row heading">
        <h2 class="mt0 mb50 text-center">Our Agents</h2>
      </div>
      <div class="row">
        <div class="col-md-3">
          <div class="probootstrap-card probootstrap-person text-left">
            <div class="probootstrap-card-media">
              <img src="${pageContext.request.contextPath}/template/img/person_1.jpg" class="img-responsive" alt="Free HTML5 Template by uicookies.com">
            </div>
            <div class="probootstrap-card-text">
              <h2 class="probootstrap-card-heading mb0">Jeremy Slater</h2>
              <p><small>Real Estate Brooker</small></p>
              <p><a href="${pageContext.request.contextPath}/template/#">View Details</a></p>
            </div>
          </div>
        </div>
        <div class="col-md-3">
          <div class="probootstrap-card probootstrap-person text-left">
            <div class="probootstrap-card-media">
              <img src="${pageContext.request.contextPath}/template/img/person_2.jpg" class="img-responsive" alt="Free HTML5 Template by uicookies.com">
            </div>
            <div class="probootstrap-card-text">
              <h2 class="probootstrap-card-heading mb0">James Butterly</h2>
              <p><small>Buying Agent</small></p>
              <p><a href="${pageContext.request.contextPath}/template/#">View Details</a></p>
            </div>
          </div>
        </div>
        <div class="col-md-3">
          <div class="probootstrap-card probootstrap-person text-left">
            <div class="probootstrap-card-media">
              <img src="${pageContext.request.contextPath}/template/img/person_3.jpg" class="img-responsive" alt="Free HTML5 Template by uicookies.com">
            </div>
            <div class="probootstrap-card-text">
              <h2 class="probootstrap-card-heading mb0">James Smith</h2>
              <p><small>Real Estate Brooker</small></p>
              <p><a href="${pageContext.request.contextPath}/template/#">View Details</a></p>
            </div>
          </div>
        </div>
        <div class="col-md-3">
          <div class="probootstrap-card probootstrap-person text-left">
            <div class="probootstrap-card-media">
              <img src="${pageContext.request.contextPath}/template/img/person_4.jpg" class="img-responsive" alt="Free HTML5 Template by uicookies.com">
            </div>
            <div class="probootstrap-card-text">
              <h2 class="probootstrap-card-heading mb0">Chris White</h2>
              <p><small>Selling Agent</small></p>
              <p><a href="${pageContext.request.contextPath}/template/#">View Details</a></p>
            </div>
          </div>
        </div>
      </div>
    </div>
  </section>

  	<!-- START: footer -->
	       <div id="includeFooter"></div>
	<!-- END: footer -->

  <div class="gototop js-top">
    <a href="${pageContext.request.contextPath}/template/#" class="js-gotop"><i class="icon-chevron-thin-up"></i></a>
  </div>

  </body>
  
  <script>
        $("#includeHeader").load('${pageContext.request.contextPath}/includeHeaderController');
        $("#includeFooter").load('${pageContext.request.contextPath}/includeFooterController');
  </script>
  
  <script src="${pageContext.request.contextPath}/template/js/scripts.min.js"></script>
  <script src="${pageContext.request.contextPath}/template/js/main.min.js"></script>
  <script src="${pageContext.request.contextPath}/template/js/custom.js"></script>
  
</html>