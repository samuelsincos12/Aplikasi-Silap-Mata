        
        <div id="page-right-content">
          <div class="container">
            <div class="row">
              <div class="col-md-12 col-sm-12">
                <h4 class="header-title m-t-0 m-b-20">Data Pelapor</h4>
              </div>
            </div>
<?php $this->load->view('elements/alert');?>
            <div class="row">
              <div class="col-md-12 col-sm-12">
                <div class="card-box table-responsive">
                  <table id="tugas" class="table table-striped table-bordered table-responsive" cellspacing="0" width="100%">
                    <thead>
                      <tr>
                        <th width="5%">No</th>
                        <th width="20%">No.HP</th>
                        <th width="10%">Kode</th>
                        <th width="10%">Status</th>
                        <th width="10%">Opsi</th>
                      </tr>
                    </thead>
                    <tbody>
<?php $c = 1;?><?php foreach ($user as $usr): ?>
                      <tr>
                        <td><?php echo $c++;?></td>
                        <td><?php echo $usr->nohp;?></td>
                        <td><?php echo $usr->kode;?></td>
                        <td><?php echo ($usr->status == 1) ? "Aktif" : "Belum Aktif";?></td>
                        <td>
                          <button class="btn btn-danger" data-toggle="modal" data-target="#hapus<?php echo $usr->id_pelapor;?>"><i class="mdi mdi-delete mdi-18px"></i> Hapus</button>
                        </td>
                      </tr>
<?php endforeach;?>
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
          </div>
<?php $this->load->view('elements/pform');?>
