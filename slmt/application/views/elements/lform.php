 <?php $dd = 1; ?><?php foreach ($lapor as $lp): ?>
          <div class="modal fade" id="lihat<?php echo $lp->id_laporan;?>" tabindex="-1" role="dialog" aria-labelledby="myModalLihat">
            <div class="modal-dialog" role="document">
              <div class="modal-content">
                <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                  <h4 class="modal-title" id="myModalLihat">Lihat Laporan</h4>
                </div>
                <div class="modal-body">
                  <div class="text-center card-box">
                    <div class="clearfix"></div>
<?php if ($lp->status == 1): ?>
                    <span class="user-badge bg-primary">Laporan Baru</span>
<?php elseif ($lp->status == 2): ?>
                    <span class="user-badge bg-success">Laporan selesai</span>
<?php else: ?>
                    <span class="user-badge bg-danger">Laporan Nihil</span>
<?php endif;?>
                    <div class="tab-content">
                      <div class="tab-pane active" id="home-b1<?php echo $dd++;?>">
                        <div class="member-card">
                          <div class="thumb-xl member-thumb m-b-10 center-block">
                            <img src="assets/images/report/<?php echo $lp->gambar;?>" class="img img-thumbnail" alt="image"/>
                          </div>
                          <div class="">
                            <h4 class="m-b-5"><?php echo $lp->jns_pelanggaran;?></h4>
                            <span> <a class="text-pink"> <?php echo $lp->tanggal;?> <span> | </span> <?php echo $lp->waktu;?></a> </span></p>
                          </div>
                          <p class="text-muted font-13" id="results"><?php echo $lp->alamat;?>
                          </p>
                          <a href="" class="btn btn-default btn-sm m-t-10">
                            <?php echo (!empty($lp->name)) ? $lp->name : "Belum Ada" ;?></a>
                        </div>
                      </div>
                    </div>
                  </div>
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-default" data-dismiss="modal"><i class="mdi mdi-close-box mdi-18px"></i>&nbsp; Tutup</button>
                </div>
              </div>
            </div>
          </div>
          <div class="modal fade" id="hapus<?php echo $lp->id_laporan;?>" tabindex="-1" role="dialog" aria-labelledby="myModalHapusl">
            <div class="modal-dialog" role="document">
              <div class="modal-content">
                <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                  <h4 class="modal-title" id="myModalHapusl">Hapus Laporan</h4>
                </div>
                <form method="POST" action="ldes">
                <div class="modal-body">
                  <p>Apakah anda yakin menghapus data ini ?</p>
                  <input type="hidden" id="id" name="idl" value="<?php echo $lp->id_laporan;?>" class="form-control"/>
                  <input type="hidden" id="x" name="x" value="<?php echo $lp->gambar;?>" class="form-control"/>
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-default" data-dismiss="modal"><i class="mdi mdi-close-box mdi-18px"></i>&nbsp; Batal</button>
                  <button type="submit" class="btn btn-info" name="del"><i class=" mdi mdi-check mdi-18px"></i>&nbsp; Ya</button>
                </div>
                </form>
              </div>
            </div>
          </div>
<?php endforeach;?>
          <div class="modal fade" id="prints" tabindex="-1" role="dialog" aria-labelledby="myModalPrintl">
            <div class="modal-dialog" role="document">
              <div class="modal-content">
                <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                  <h4 class="modal-title" id="myModalPrintll">Cetak Laporan</h4>
                </div>
                <form method="POST" action="lprt">
                <div class="modal-body">
                  <select id="bulan" class="form-control" data-error="Bulan harus diisi" required="" name="bln">
                      <option value="" disabled selected>Pilih Bulan</option>
                      <option value="1">Januari</option>
                      <option value="2">Februari</option>
                      <option value="3">Maret</option>
                      <option value="4">April</option>
                      <option value="5">Mei</option>
                      <option value="6">Juni</option>
                      <option value="7">Juli</option>
                      <option value="8">Agustus</option>
                      <option value="9">September</option>
                      <option value="10">Oktober</option>
                      <option value="11">November</option>
                      <option value="12">Desember</option>
                    </select>
                    <div class="help-block with-errors"></div>
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-default" data-dismiss="modal"><i class="mdi mdi-close-box mdi-18px"></i>&nbsp; Batal</button>
                  <button type="submit" class="btn btn-info" name="del"><i class="mdi mdi-printer mdi-18px"></i>&nbsp; Cetak</button>
                </div>
                </form>
              </div>
            </div>
          </div>