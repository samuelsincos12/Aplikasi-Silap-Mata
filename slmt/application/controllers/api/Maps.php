<?php defined('BASEPATH') OR exit('No direct script access allowed');
/**
* API Maps 
*/
class Maps extends CI_Controller {
	
	public function __construct() { parent::__construct(); }

	public function getMarker() {
		foreach ($this->lm->readMap() as $row) { $data[] = $row; }
		if (empty($data)) {
			$resp = array('error'     => TRUE, 
				          'message'    => 'Data tidak ditemukan');
		} else {
			$resp = array('error'     => FALSE, 
				          'marker'    => $data);
		}
		$this->output->set_status_header(200)
					 ->set_content_type('application/json', 'utf-8')
					 ->set_output(json_encode($resp))
					 ->_display();
        exit;
	}
	
	public function getMarker1() {
		foreach ($this->lm->readMap1() as $row) { $data[] = $row; }
		if (empty($data)) {
			$resp = array('error'     => TRUE, 
				          'marker'    => 'Data tidak ditemukan');
		} else {
			$resp = array('error'     => FALSE, 
				          'marker'    => $data);
		}
		$this->output->set_status_header(200)
					 ->set_content_type('application/json', 'utf-8')
					 ->set_output(json_encode($resp))
					 ->_display();
        exit;
	}

	public function getMarker2() {
		foreach ($this->lm->readMap2() as $row) { $data[] = $row; }
		if (empty($data)) {
			$resp = array('error'     => TRUE, 
				          'marker'    => 'Data tidak ditemukan');
		} else {
			$resp = array('error'     => FALSE, 
				          'marker'    => $data);
		}
		$this->output->set_status_header(200)
					 ->set_content_type('application/json', 'utf-8')
					 ->set_output(json_encode($resp))
					 ->_display();
        exit;
	}

	public function getMarker3() {
		foreach ($this->lm->readMap3() as $row) { $data[] = $row; }
		if (empty($data)) {
			$resp = array('error'     => TRUE, 
				          'marker'    => 'Data tidak ditemukan');
		} else {
			$resp = array('error'     => FALSE, 
				          'marker'    => $data);
		}
		$this->output->set_status_header(200)
					 ->set_content_type('application/json', 'utf-8')
					 ->set_output(json_encode($resp))
					 ->_display();
        exit;
	}

	public function getPolygon() {
		$data = $this->low->po();
		if (empty($data)) {
			$resp = array('error'     => TRUE, 
				          'message'   => 'Data tidak ditemukan');
		} else {
			$resp = array('error'     => FALSE, 
				          'polygon'   => $data);
		}
		$this->output->set_status_header(200)
			 		 ->set_content_type('application/json', 'utf-8')
			 		 ->set_output(json_encode($resp))
			 		 ->_display();
        exit;
	}
}
?>