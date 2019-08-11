<?php defined('BASEPATH') OR exit('No direct script access allowed');
/**
* Controller Petugas 
*/
class Petugas extends CI_Controller {

	public function __construct() { parent::__construct(); }

	public function index() {
		if ($this->session->userdata('signin') == 9) {
			$data['tugas'] = $this->tm->read();
			$this->load->view('elements/header');
			$this->load->view('pages/vpetugas', $data);
			$this->load->view('elements/footer');
			$this->load->view('elements/jsa');
		} else {
			redirect('/');
		}
	}

	public function store() {
		$this->upload->set_upload_path('./assets/images/petugas');
		$this->upload->set_allowed_types('gif|jpg|JPG|png');
		$this->upload->set_max_filesize(2048);	
		$this->upload->do_upload('foto');
		$upload_data = $this->upload->data();

		$data = array('t1' => $this->input->post('nama'), 
					  't2' => $this->input->post('user'), 
		              't3' => sha1($this->input->post('pass')), 
		              't4' => $upload_data['file_name'], 
		              't5' => $this->input->post('level'));

		$result = $this->tm->create($data);
		if ($result) {
			$this->session->set_flashdata('sr', 
										  '<strong>Berhasil !</strong> 
										  	Data berhasil ditambah.');
		} else {
			$this->session->set_flashdata('er', 
										  '<strong>Gagal !</strong> 
										  	Data gagal ditambah.');
		}
		return redirect('petugas');
	}

	public function edit() {
		if ($_FILES['foto']['error'] == 0) {
			$this->upload->set_upload_path('./assets/images/petugas');
			$this->upload->set_allowed_types('gif|jpg|JPG|png');
			$this->upload->set_max_filesize(2048);      
			$this->upload->do_upload('foto');
			$upload_data = $this->upload->data();
			$file_name = $upload_data['file_name'];
			@unlink('./assets/images/petugas/'.$this->input->post('x'));
		} else {
			$file_name = $this->input->post('x');
		}

		$id = $this->input->post('id');
		$data = array('t1' => $this->input->post('nama'), 
					  't2' => $this->input->post('user'), 
		              't3' => sha1($this->input->post('pass')), 
		              't4' => $file_name, 
		              't5' => $this->input->post('level'));
		$result = $this->tm->edit($data, $id);
		if ($result) {
			$this->session->set_flashdata('sr', 
										  '<strong>Berhasil !</strong> 
										  	Data berhasil diubah.');
		} else {
			$this->session->set_flashdata('er', 
										  '<strong>Gagal !</strong> 
										  	Data gagal diubah.');
		}
		return redirect('petugas');
	}

	public function destroy() {
		$id = $this->input->post('id');
		@unlink('./assets/images/petugas/'.$this->input->post('x'));
		$result = $this->tm->delete($id);
		if ($result) {
			$this->session->set_flashdata('sr', 
										  '<strong>Berhasil !</strong> 
										  	Data berhasil dihapus.');
		} else {
			$this->session->set_flashdata('er', 
										  '<strong>Gagal !</strong> 
										  	Data gagal dihapus.');
		}
		return redirect('petugas');
	}
}
?>