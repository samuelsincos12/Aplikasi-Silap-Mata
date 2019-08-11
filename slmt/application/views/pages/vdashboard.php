
         <div id="page-right-content">
          <div class="container">
            <div class="row">
				<div class="col-md-12 col-sm-12">
					<div class="card-box widget-inline">
						<div class="row">
							<div class="col-md-3 col-sm-6">
								<div class="widget-inline-box text-center">
									<h3 class="m-t-10"><i class="text-primary mdi mdi-clipboard-arrow-down"></i>
									    <b data-plugin="counterup">
									        <?php 
											foreach ($baru as $br): 
												$dr[] = $br;
											endforeach;

											echo count($dr);
											?>
									    </b></h3>
									<p class="text-muted">Laporan Baru</p>
								</div>
							</div>
							<div class="col-md-3 col-sm-6">
								<div class="widget-inline-box text-center">
									<h3 class="m-t-10"><i class="text-custom mdi mdi-clipboard-check"></i>
									    <b data-plugin="counterup">
									        <?php 
											foreach ($selesai as $sl): 
												$dl[] = $sl;
											endforeach;

											echo count($dl);
											?>
									    </b></h3>
									<p class="text-muted">Laporan Selesai</p>
								</div>
							</div>
							<div class="col-md-3 col-sm-6">
								<div class="widget-inline-box text-center">
									<h3 class="m-t-10"><i class="text-danger mdi mdi-clipboard-alert"></i>
									<b data-plugin="counterup">
									    <?php 
											foreach ($nihil as $nh): 
												$dh[] = $nh;
											endforeach;

											echo count($dh);
											?>
									</b></h3>
									<p class="text-muted">Laporan Nihil</p>
								</div>
							</div>
							<div class="col-md-3 col-sm-6">
								<div class="widget-inline-box text-center b-0">
									<h3 class="m-t-10"><i class="text-info mdi mdi-account-multiple"></i>
									<b data-plugin="counterup">
									    <?php 
											foreach ($user as $se): 
												$de[] = $se;
											endforeach;

											echo count($de);
											?>
									</b></h3>
									<p class="text-muted">Total Pelapor</p>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
            <div class="row">
              <div class="col-md-12 col-sm-12">
                <div class="card-box" style="margin-bottom: 0px">
                  <div style="height: 278px;"></div>
                </div>
              </div>
            </div>
        </div>
