
        <div id="page-right-content">
          <div class="container">
            <div class="row">
              <div class="col-md-12 col-sm-12">
                <h4 class="header-title m-t-0 m-b-20">Data Petugas</h4>
              </div>
            </div>
<?php $this->load->view('elements/alert');?>
            <div class="row">
              <div class="col-md-12 col-sm-12">
                <button class="btn btn-info" data-toggle="modal" data-target="#add"><i class="mdi mdi-plus-box mdi-18px"></i>&nbsp;Tambah </button><br><br>
                <div class="card-box table-responsive">
                   <table id="tugas" class="table table-striped table-bordered table-responsive" cellspacing="0" width="100%">
                    <thead>
                      <tr>
                        <th width="5%">No</th>
                        <th width="20%">Nama</th>
                        <th width="20%">User</th>
                        <th width="20%">Foto</th>
                        <th width="10%">Level</th>
                        <th width="20%">Opsi</th>
                      </tr>
                    </thead>
                    <tbody>
<?php $i = 1;?><?php foreach ($tugas as $tgs): ?>
                      <tr>
                        <td><?php echo $i++;?></td>
                        <td><?php echo $tgs->name;?></td>
                        <td><?php echo $tgs->user;?></td>
                        <td><img width="50%" src="assets/images/petugas/<?php echo $tgs->foto;?>"/></td>
                        <td><?php echo ($tgs->level == 1) ? "Admin" : "Petugas";?></td>
                        <td>
                          <button class="btn btn-warning" data-toggle="modal" data-target="#edit<?php echo $tgs->id_petugas;?>"><i class="mdi mdi-pencil-box mdi-18px"></i> Edit</button>
                          <button class="btn btn-danger" data-toggle="modal" data-target="#hapus<?php echo $tgs->id_petugas;?>"><i class="mdi mdi-delete mdi-18px"></i> Hapus</button>
                        </td>
                      </tr>
<?php endforeach;?>
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
          </div>
<?php $this->load->view('elements/tform');?>