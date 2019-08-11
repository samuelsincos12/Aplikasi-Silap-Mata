<?php defined('BASEPATH') OR exit('No direct script access allowed');
/**
* Controller Laporan 
*/
class Laporan extends CI_Controller {
	
	public function __construct() { parent::__construct(); }

	public function index() {
		if ($this->session->userdata('signin') == 9) {
			$data['lapor'] = $this->lm->read();
			$this->load->view('elements/header');
			$this->load->view('pages/vlaporan', $data);
			$this->load->view('elements/footer');
			$this->load->view('elements/jsa');
		} else {
			redirect('/');
		}
	}

	public function destroy() {
		$id = $this->input->post('idl');
		@unlink('./assets/images/report/'.$this->input->post('x'));
		$result = $this->lm->delete($id);
		if ($result) {
			$this->session->set_flashdata('sr', 
										  '<strong>Berhasil !</strong> 
										  	Data berhasil dihapus.');
		} else {
			$this->session->set_flashdata('er', 
										  '<strong>Gagal !</strong> 
										  	Data gagal dihapus.');
		}
		return redirect('laporan');
	}

	public function maps() {
		if ($this->session->userdata('signin') == 9) {
			$this->load->view('elements/header');
			$this->load->view('pages/vmonitoring');
			$this->load->view('elements/footer');
			$this->load->view('elements/jsm');
		} else {
			redirect('/');
		}
	}
	
	public function printr() {
		require_once('./assets/plugins/html2pdf/html2pdf.class.php');
		ob_start();
		//$bl = $this->input->post('bln');
		$data['cetak'] = $this->lm->read();
		$this->load->view('pages/vcetak', $data);
		$html = ob_get_contents();
		ob_end_clean();

		$pdf = new HTML2PDF('P','A4','en');
		$pdf->WriteHTML($html);
		ob_start();
		$pdf->Output('Laporan.pdf');
	}
}
?>