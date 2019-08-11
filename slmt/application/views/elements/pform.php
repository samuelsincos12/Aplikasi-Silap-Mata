<?php foreach ($user as $usr): ?>
          <div class="modal fade" id="hapus<?php echo $usr->id_pelapor;?>" tabindex="-1" role="dialog" aria-labelledby="myModalHapusp">
            <div class="modal-dialog" role="document">
              <div class="modal-content">
                <div class="modal-header">
                  <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                  <h4 class="modal-title" id="myModalHapusp">Hapus Pelapor</h4>
                </div>
                <form method="POST" action="odes">
                <div class="modal-body">
                  <p>Apakah anda yakin menghapus data ini ?</p>
                  <input type="hidden" id="id" name="idp" value="<?php echo $usr->id_pelapor;?>" class="form-control"/>
                </div>
                <div class="modal-footer">
                  <button type="button" class="btn btn-default" data-dismiss="modal"><i class="mdi mdi-close-box mdi-18px"></i>&nbsp; Batal</button>
                  <button type="submit" class="btn btn-info" name="del"><i class="mdi mdi-check mdi-18px"></i>&nbsp; Ya</button>
                </div>
                </form>
              </div>
            </div>
          </div>
<?php endforeach;?>