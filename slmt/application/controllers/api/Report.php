<?php defined('BASEPATH') OR exit('No direct script access allowed');
/**
* API Report 
*/
class Report extends CI_Controller {
	
	public function __construct() { parent::__construct(); }

	public function create() {
		$this->upload->set_upload_path('./assets/images/report');
		$this->upload->set_allowed_types('gif|jpg|JPG|png');
		$this->upload->set_max_filesize(8192);	
		$this->upload->do_upload('img');
		$upload_data = $this->upload->data();

		$data = array('r1' => $upload_data['file_name'], 
					  'r2' => $this->input->post('jns'), 
		              'r3' => $this->input->post('lat'), 
		              'r4' => $this->input->post('lon'),
		              'r5' => $this->input->post('alamat'), 
		              'r6' => $this->input->post('ket'));
		if (!empty($data['r1'])) {
			$this->lm->store($data);
			$list = $this->lm->viewR($data);
			$row = array('r1' => $list->id_laporan);
			$this->lm->storeA($row);
			$resp = array('error' 	=> FALSE, 
						  'message' => 'Anda Berhasil Melapor !');
		} else {
			$resp = array('error' 	=> TRUE, 
						  'message' => 'Anda Gagal Melapor !');
		}
		
		$this->output->set_status_header(200)
					 ->set_content_type('application/json', 'utf-8')
					 ->set_output(json_encode($resp))
					 ->_display();
        exit;
	}

	public function edit() {
		$data = array('r1' => $this->input->post('id_petugas'), 
					  'r2' => $this->input->post('id_laporan'), 
		              'r3' => $this->input->post('status'));
		if (!empty($data['r1']) && !empty($data['r2']) && !empty($data['r3'])) {
			$this->lm->updateA($data);
			$resp = array('error' 	=> FALSE, 
						  'message' => 'Laporan Berhasil di Verifikasi');
		} else {
			$resp = array('error' 	=> TRUE, 
						  'message' => 'Laporan Gagal di Verifikasi');
		}
		
		$this->output->set_status_header(200)
					 ->set_content_type('application/json', 'utf-8')
					 ->set_output(json_encode($resp))
					 ->_display();
        exit;
	}
}
?>