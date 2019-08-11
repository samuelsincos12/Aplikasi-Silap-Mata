<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8" />
        <title>Silap Mata</title>
        <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no"/>
        <meta content="Silap Mata" name="description"/>
        <meta content="Coderthemes" name="author" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
        <link rel="shortcut icon" href="assets/images/sm.png"/>
        <link href="assets/css/bootstrap.min.css" rel="stylesheet"/>
        <link href="assets/css/metisMenu.min.css" rel="stylesheet"/>
        <link href="assets/css/icons.css" rel="stylesheet"/>
        <link href="assets/css/style.css" rel="stylesheet"/>
    </head>
    <body>
        <section>
            <div class="container">
                <div class="row">
                    <div class="col-md-4 col-md-offset-4" style="margin-top: 50px;">
                        <div class="wrapper-page">
                            <div class="m-t-40 card-box">
                                <div class="text-center">
                                    <h2 class="text-uppercase m-t-0 m-b-30">
                                        <a href="" class="text-default">
                                            <span><img src="assets/images/sm.png" alt="" height="100"/></span><br/>
                                            <span style="color: #676a6c; font-size: 18px;">Silap Mata</span>
                                        </a>
                                    </h2>
                                </div>
<?php $this->load->view('elements/alert');?>
                                <div class="account-content">
                                    <form data-toggle="validator" role="form" class="form-horizontal" action="login" method="POST">
                                        <div class="form-group m-b-20">
                                            <div class="col-xs-12">
                                                <label for="e">Username</label>
                                                <input class="form-control" type="text" id="e" name="nama" data-error="Username harus diisi" required="" placeholder="Masukkan username anda"/>
                                                <div class="help-block with-errors"></div>
                                            </div>
                                        </div>

                                        <div class="form-group m-b-20">
                                            <div class="col-xs-12">
                                                <label for="p">Password</label>
                                                <input class="form-control" type="password" name="pass" id="p" data-error="Password harus diisi" required="" placeholder="Masukkan password anda"/>
                                                <div class="help-block with-errors"></div>
                                            </div>
                                        </div>
                                        <div class="form-group account-btn text-center m-t-10">
                                            <div class="col-xs-12">
                                                <button class="btn btn-lg btn-info btn-block" name="log" type="submit">Sign In</button>
                                            </div>
                                        </div>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>
    </body>
    <script src="assets/js/jquery-2.1.4.min.js" type="text/javascript"></script>
    <script src="assets/js/bootstrap.min.js" type="text/javascript"></script>
    <script src="assets/js/metisMenu.min.js" type="text/javascript"></script>
    <script src="assets/js/jquery.slimscroll.min.js" type="text/javascript"></script>
    <script src="assets/js/jquery.app.js" type="text/javascript"></script>
    <script src="assets/js/validator.min.js" type="text/javascript"></script>
    <script src="assets/js/conf.js" type="text/javascript"></script>
</html>