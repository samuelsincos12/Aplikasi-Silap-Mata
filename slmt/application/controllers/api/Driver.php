<?php  defined('BASEPATH') OR exit('No direct script access allowed');
/**
* API Driver 
*/
class Driver extends CI_Controller {
	
	public function __construct() { parent::__construct(); }

	public function signin() {
		$data = array('d1' => $this->input->post('user'), 
					  'd2' => sha1($this->input->post('pass')));
		$check = $this->um->readA($data);
		if ($check) {
			$row = array('id_petugas' => $check->id_petugas, 
						 'name'       => $check->name, 
						 'foto'       => $check->foto, 
						 'level'      => $check->level);
			
			$resp = array('error'     => FALSE, 
				          'driver'    => $row);
		} else {
			$resp = array('error' 	  => TRUE, 
				          'message'   => 'User atau Password Anda Salah');
		}

		$this->output->set_status_header(200)
			 		 ->set_content_type('application/json', 'utf-8')
			 		 ->set_output(json_encode($resp))
			 		 ->_display();
        exit;
	}

	public function driveron() {
		$data = array('d1' => $this->input->post('id_petugas'));
		$check = $this->um->storeAc($data);
		if ($check) {
			$resp = array('error'     => FALSE, 
				          'message'   => 'Anda Online');
		} else {
			$resp = array('error' 	  => TRUE, 
				          'message'   => 'Kesalahan Sistem');
		}

		$this->output->set_status_header(200)
			 		 ->set_content_type('application/json', 'utf-8')
			 		 ->set_output(json_encode($resp))
			 		 ->_display();
        exit;
	}

	public function driveroff() {
		$data = array('d1' => $this->input->post('id_petugas'));
		$check = $this->um->editAc($data);
		if ($check) {
			$resp = array('error'     => FALSE, 
				          'message'   => 'Anda Offline');
		} else {
			$resp = array('error' 	  => TRUE, 
				          'message'   => 'Kesalahan Sistem');
		}

		$this->output->set_status_header(200)
			 		 ->set_content_type('application/json', 'utf-8')
			 		 ->set_output(json_encode($resp))
			 		 ->_display();
        exit;
	}
}
?>