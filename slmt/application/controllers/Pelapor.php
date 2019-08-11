<?php defined('BASEPATH') OR exit('No direct script access allowed');
/**
* Controller Pelapor 
*/
class Pelapor extends CI_Controller {

	public function __construct() { parent::__construct(); }

	public function index() {
		if ($this->session->userdata('signin') == 9) {
			$data['user'] = $this->pm->read();
			$this->load->view('elements/header');
			$this->load->view('pages/vpelapor', $data);
			$this->load->view('elements/footer');
			$this->load->view('elements/jsa');
		} else {
			redirect('/');
		}
	}

	public function destroy() {
		$id = $this->input->post('idp');
		$result = $this->pm->delete($id);
		if ($result) {
			$this->session->set_flashdata('sr', 
										  '<strong>Berhasil !</strong> 
										  	Data berhasil dihapus.');
		} else {
			$this->session->set_flashdata('er', 
										  '<strong>Gagal !</strong> 
										  	Data gagal dihapus.');
		}
		return redirect('pelapor');
	}
}
?>