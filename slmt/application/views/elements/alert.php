<?php if ($this->session->flashdata('sr')): ?>
            <div id="alertsm" class="alert alert-icon alert-success alert-dismissible fade in" role="alert">
              <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
              <i class="mdi mdi-check-all"></i>
<?php echo $this->session->flashdata('sr');?>
            </div>
<?php elseif ($this->session->flashdata('er')): ?>
            <div id="alertsm" class="alert alert-icon alert-danger alert-dismissible fade in" role="alert">
              <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                <span aria-hidden="true">&times;</span>
              </button>
              <i class="mdi mdi-block-helper"></i>
<?php echo $this->session->flashdata('er');?>
            </div>
<?php endif;?>