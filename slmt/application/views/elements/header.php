<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="utf-8" />
    <title>Silap Mata</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
    <meta content="Silap Mata" name="description"/>
    <meta content="Coderthemes" name="author" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <link href="assets/images/sm.png" rel="shortcut icon"/>
    <link href="assets/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="assets/css/dataTables.bootstrap.min.css" rel="stylesheet"/>
    <link href="assets/css/metisMenu.min.css" rel="stylesheet"/>
    <link href="assets/css/icons.css" rel="stylesheet"/>
    <link href="assets/css/style.css" rel="stylesheet"/>
  </head>
  <body>
    <div id="page-wrapper">
      <div class="topbar">
        <div class="navbar navbar-default" role="navigation">
          <div class="container">
            <div class="">
              <div class="pull-left">
                <button type="button" class="button-menu-mobile visible-xs visible-sm">
                  <i class="fa fa-bars"></i>
                </button>
                <span class="clearfix"></span>
              </div>
              <div class="topbar-left">
                <div class="">
                  <a href="dashboard" class="logo">
                    <img src="assets/images/sm.png" alt="logo" class="logo-lg"/>
                    <span class="logo-lg">Silap Mata</span>
                  </a>
                </div>
              </div>
              <ul class="nav navbar-nav navbar-right top-navbar-items-right pull-right">
                <li class="dropdown top-menu-item-xs">
                  <a href="" class="dropdown-toggle menu-right-item profile" data-toggle="dropdown" aria-expanded="true">
                    <img src="assets/images/petugas/<?php echo $this->session->userdata('gmb');?>" alt="user-img" class="img-circle">
                  </a>
                  <ul class="dropdown-menu">
                    <li><a href="#out" data-toggle="modal"><i class="ti-power-off m-r-10"></i> Logout</a></li>
                  </ul>
                </li>
              </ul>
            </div>
          </div> 
        </div>
      </div>
<?php $this->load->view('elements/uform');?>
      <div class="page-contentbar">
        <aside class="sidebar-navigation">
          <div class="scrollbar-wrapper">
            <div>
              <button type="button" class="button-menu-mobile btn-mobile-view visible-xs visible-sm">
                <i class="mdi mdi-close"></i>
              </button>
              <div class="user-details">
                <div class="pull-left">
                  <img src="assets/images/petugas/<?php echo $this->session->userdata('gmb');?>" alt="" class="thumb-md img-circle">
                </div>
                <div class="user-info">
                  <a href=""><?php echo $this->session->userdata('nama');?></a>
                  <p class="text-muted m-0"><?php echo ($this->session->userdata('status') == 1) ? "Administrator" : "Petugas";?></p>
                </div>
              </div>
              <ul class="metisMenu nav" id="side-menu">
                <li><a href="dashboard"><i class="mdi mdi-view-dashboard mdi-18px"></i> Dashboard </a></li>
                <li><a href="monitoring"><i class="mdi mdi-monitor mdi-18px"></i> Monitoring</a></li>
                <li><a href="laporan"><i class="mdi mdi-clipboard-text mdi-18px"></i> Data Laporan </a></li>
                <li><a href="pelapor"><i class="mdi mdi-clipboard-account mdi-18px"></i> Data Pelapor </a></li>
                <li><a href="petugas"><i class="mdi mdi-account mdi-18px"></i> Data Petugas </a></li>
              </ul>
            </div>
          </div>
        </aside>