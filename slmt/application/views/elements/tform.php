          <div class="modal fade" id="add" tabindex="-1" role="dialog" aria-labelledby="myModalAdd">
            <div class="modal-dialog" role="document">
              <div class="modal-content">
                <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                  <h4 class="modal-title" id="myModalLabel">Tambah Petugas</h4>
                </div>
                <form data-toggle="validator" role="form" method="POST" action="padd" enctype="multipart/form-data">
                <div class="modal-body">
                  <div class="form-group">
                    <label for="nama">Nama Petugas</label>
                    <input type="text" id="nama" name="nama" data-error="Nama Petugas harus diisi" required="" placeholder="Nama Petugas" class="form-control"/>
                    <div class="help-block with-errors"></div>
                  </div>
                  <div class="form-group">
                    <label for="username">Username</label>
                      <input type="text" id="username" name="user" data-error="Username harus diisi" required="" placeholder="Username" class="form-control"/>
                      <div class="help-block with-errors"></div>
                  </div>
                  <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="pass" data-error="Password harus diisi" required="" placeholder="Password" class="form-control"/>
                    <div class="help-block with-errors"></div>
                  </div>
                  <div class="form-group">
                    <label for="level">Level</label>
                    <select id="level" class="form-control" data-error="Level harus diisi" required="" name="level">
                      <option value="" disabled selected>Pilih Level</option>
                      <option value="1">Admin</option>
                      <option value="2">Petugas</option>
                    </select>
                    <div class="help-block with-errors"></div>
                  </div>
                  <div class="form-group">
                    <label for="gambar">Foto</label>
                    <input type="file" id="gambar" accept="image/*" onchange="tampilkanPreview(this, 'vadd')" name="foto" data-error="Foto harus diisi" required=""/>
                    <div class="help-block with-errors"></div><br/>
                    <center>
                      <img src="assets/images/document.svg" id="vadd" width="150"/>
                    </center>
                  </div>
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-default" data-dismiss="modal"><i class="mdi mdi-close-box mdi-18px"></i>&nbsp; Batal</button>
                  <button type="submit" class="btn btn-info" name="ins"><i class="mdi mdi-content-save mdi-18px"></i>&nbsp; Simpan</button>
                </div>
                </form>
              </div>
            </div>
          </div>
<?php foreach ($tugas as $tgs): ?>
          <div class="modal fade" id="edit<?php echo $tgs->id_petugas;?>" tabindex="-1" role="dialog" aria-labelledby="myModalEdit">
            <div class="modal-dialog" role="document">
              <div class="modal-content">
                <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                  <h4 class="modal-title" id="myModalLabel">Edit Petugas</h4>
                </div>
                <form method="POST" data-toggle="validator" role="form" action="pedt" enctype="multipart/form-data">
                <div class="modal-body">
                  <input type="hidden" id="id" name="id" value="<?php echo $tgs->id_petugas;?>"/>
                  <div class="form-group">
                    <label for="nama">Nama Petugas</label>
                    <input type="text" id="nama" name="nama" data-error="Nama Petugas harus diisi" required="" placeholder="Nama Petugas" value="<?php echo $tgs->name;?>" class="form-control"/>
                    <div class="help-block with-errors"></div>
                  </div>
                  <div class="form-group">
                    <label for="username">Username</label>
                    <input type="text" id="username" name="user" data-error="Username harus diisi" required="" placeholder="Username" value="<?php echo $tgs->user;?>" class="form-control"/>
                    <div class="help-block with-errors"></div>
                  </div>
                  <div class="form-group">
                    <label for="password">Password</label>
                    <input type="password" id="password" name="pass" data-error="Password harus diisi" required="" placeholder="Password" class="form-control"/>
                    <div class="help-block with-errors"></div>
                  </div>
                  <div class="form-group">
                    <label for="level">Level</label>
                    <select id="level" class="form-control" data-error="Level harus diisi" required="" name="level">
                      <option value="" disabled selected>Pilih Level</option>
                      <option <?php echo ($tgs->level == 1) ? "selected" : "";?> value="1">Admin</option>
                      <option <?php echo ($tgs->level == 2) ? "selected" : "";?> value="2">Petugas</option>
                    </select>
                    <div class="help-block with-errors"></div>
                  </div>
                  <div class="form-group">
                    <label for="gambar">Foto</label>
                    <input type="hidden" name="x" value="<?php echo $tgs->foto;?>"/>
                    <input type="file" id="gambar" accept="image/*" onchange="tampilkanPreview(this, 'vedit<?php echo $tgs->id_petugas;?>')" name="foto"/><br/>
                    <center>
                      <img src="assets/images/petugas/<?php echo $tgs->foto;?>" id="vedit<?php echo $tgs->id_petugas;?>" width="150"/>
                    </center>
                  </div>
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-default" data-dismiss="modal"><i class="mdi mdi-close-box mdi-18px"></i>&nbsp; Batal</button>
                  <button type="submit" class="btn btn-info" name="edt"><i class="mdi mdi-content-save mdi-18px"></i>&nbsp; Edit</button>
                </div>
                </form>
              </div>
            </div>
          </div>
          <div class="modal fade" id="hapus<?php echo $tgs->id_petugas;?>" tabindex="-1" role="dialog" aria-labelledby="myModalHapus">
            <div class="modal-dialog" role="document">
              <div class="modal-content">
                <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                  <h4 class="modal-title" id="myModalLabel">Hapus Petugas</h4>
                </div>
                <form method="POST" action="pdes" enctype="multipart/form-data">
                <div class="modal-body">
                  <p>Apakah anda yakin menghapus data ini ?</p>
                  <input type="hidden" id="id" name="id" value="<?php echo $tgs->id_petugas;?>" class="form-control"/>
                  <input type="hidden" id="x" name="x" value="<?php echo $tgs->foto;?>" class="form-control"/>
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-default" data-dismiss="modal"><i class="mdi mdi-close-box mdi-18px"></i>&nbsp; Batal</button>
                  <button id="btndel" type="submit" class="btn btn-info" name="del"><i class=" mdi mdi-check mdi-18px"></i>&nbsp; Ya</button>
                </div>
                </form>
              </div>
            </div>
          </div>
<?php endforeach;?>