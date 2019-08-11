
        <div id="page-right-content">
          <div class="container">
            <div class="row">
              <div class="col-md-12 col-sm-12">
                <h4 class="header-title m-t-0 m-b-20">Data Laporan</h4>
              </div>
            </div>
<?php $this->load->view('elements/alert');?>
            <div class="row">
              <div class="col-md-12 col-sm-12">
                <button class="btn btn-info" data-toggle="modal" data-target="#prints"><i class="mdi mdi-printer mdi-18px"></i>&nbsp;Cetak Laporan </button><br><br>
                <div class="card-box table-responsive">
                  <table id="tugas" class="table table-striped table-bordered table-responsive" cellspacing="0" width="100%">
                    <thead>
                      <tr>
                        <th width="5%">No</th>
                        <th width="5%">Tanggal</th>
                        <th width="5%">Waktu</th>
                        <th width="5%">Jenis Pelanggaran</th>
                         <th width="5%">Nama Petugas</th>
                        <th width="5%">Status</th>
                        <th width="20%">Opsi</th>
                      </tr>
                    </thead>
                    <tbody>
<?php $a = 1;?><?php foreach ($lapor as $lp): ?>
                      <tr>
                        <td><?php echo $a++;?></td>
                        <td><?php echo $lp->tanggal;?></td>
                        <td><?php echo $lp->waktu;?></td>
                        <td><?php echo $lp->jns_pelanggaran;?></td>
                        <td><?php echo (!empty($lp->name)) ? $lp->name : "Belum Ada" ;?></td>
                        <td><?php if($lp->status == 1): echo "Laporan Baru"; elseif($lp->status == 2): echo "Laporan Selesai"; else: echo "Laporan Nihil"; endif;?></td>
                        <td>
                          <button id="btn_v" class="btn btn-warning" data-toggle="modal" data-target="#lihat<?php echo $lp->id_laporan; ?>"><i class=" mdi mdi-eye mdi-18px"></i> Lihat</button>
                          <button class="btn btn-danger" data-toggle="modal" data-target="#hapus<?php echo $lp->id_laporan; ?>"><i class="mdi mdi-delete mdi-18px"></i> Hapus</button>
                        </td>
                      </tr>
<?php endforeach;?>
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
          </div>
<?php $this->load->view('elements/lform');?>
