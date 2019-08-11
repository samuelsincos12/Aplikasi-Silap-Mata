<?php defined('BASEPATH') OR exit('No direct script access allowed');
/**
* Controller Auth 
*/
class Auth extends CI_Controller {
	
	public function __construct() { parent::__construct(); }

	public function index() { 
		if ($this->session->userdata('signin') == 9) { 
			redirect('dashboard'); 
		} else { 
			$this->load->view('pages/vlogin'); 
		}
	}

	public function signin() {
		$data = array('u1' => $this->input->post('nama'), 
		              'u2' => sha1($this->input->post('pass')));
		$check = $this->um->read($data);
		if (!$check) {
			$this->session->set_flashdata('er', 
										  '<strong>Kesalahan !</strong><br/> 
											Username atau Password yang Anda Input Salah.');
			return redirect('/');
		} else {
			$log = array('nama'	  => $check->name, 
						 'status' => $check->level, 
						 'gmb' 	  => $check->foto,
						 'signin' => 9);
			$this->session->set_userdata($log);
			return redirect('dashboard');
		}
	}

	public function signout() {
		$out = array('nama'	  => '', 
					 'status' => '', 
					 'gmb' 	  => '',
					 'signin' => 6);
		$this->session->unset_userdata($out);
		$this->session->sess_destroy();
		return redirect('/');
	}

	public function home() {
		if ($this->session->userdata('signin') == 9) {
			$data = array('baru'    => $this->lm->readMap1(), 
						  'selesai' => $this->lm->readMap2(), 
						  'nihil'   => $this->lm->readMap3(), 
						  'user'    => $this->pm->read());
			$this->load->view('elements/header');
			$this->load->view('pages/vdashboard', $data);
			$this->load->view('elements/footer');
			$this->load->view('elements/js');
		} else {
			redirect('/');
		}
	}
}
?>