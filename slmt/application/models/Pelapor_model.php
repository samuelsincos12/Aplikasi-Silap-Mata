<?php defined('BASEPATH') OR exit('No direct script access allowed');
/**
* Model Pelapor
*/
class Pelapor_model extends CI_Model {
	
	public function __construct() { parent::__construct(); }

	public function read() {
		$this->db->order_by('id_pelapor', 'desc');
		$query = $this->db->get('tbl_pelapor');
		return ($query->num_rows() > 0) ? $query->result() : FALSE;
	}

	public function delete($i) {
		$id = array('id_pelapor' => $i);
		$this->db->delete('tbl_pelapor', $id);
		return ($this->db->affected_rows() > 0) ? TRUE : FALSE;
	}

	public function readin($d) {
		$data = array('nohp' 	 => $d['s1'], 
		              'status' 	 => 1);
		$this->db->select('id_pelapor, nohp');
		$this->db->from('tbl_pelapor');
		$this->db->where($data);
		$query = $this->db->get();
		return ($query->num_rows() > 0) ? $query->row() : FALSE;		
	}

	public function viewin($d) {
		$data = array('nohp' 	 => $d['s1'], 
		              'status' 	 => 2);
		$this->db->select('id_pelapor, nohp');
		$this->db->from('tbl_pelapor');
		$this->db->where($data);
		$query = $this->db->get();
		return ($query->num_rows() == 1) ? TRUE : FALSE;		
	}

	public function create($d) {
		$data = array('nohp' 	 => $d['s1'], 
		              'kode' 	 => $d['s2'], 
		              'status' 	 => 2);
		$this->db->insert('tbl_pelapor', $data);
		return ($this->db->affected_rows() > 0) ? TRUE : FALSE;
	}

	public function readup($d) {
		$data = array('nohp' 	 => $d['s1']);
		$this->db->select('id_pelapor, nohp');
		$this->db->from('tbl_pelapor');
		$this->db->where($data);
		$query = $this->db->get();
		return ($query->num_rows() > 0) ? $query->row() : FALSE;		
	}

	public function viewup($d) {
		$data = array('nohp' 	=> $d['s1']);
		$query = $this->db->get_where('tbl_pelapor', $data);
		return ($query->num_rows() > 0) ? TRUE : FALSE;
	}

	public function readck($d) {
		$data = array('nohp'    => $d['s2'],
		              'kode' 	=> $d['s1']);
		$query = $this->db->get_where('tbl_pelapor', $data);
		return ($query->num_rows() > 0) ? TRUE : FALSE;
	}

	public function editck($d) {
		$status = array('status' => 1);
		$data = array('nohp'    => $d['s2'],
		              'kode' 	=> $d['s1']);
		$this->db->update('tbl_pelapor', $status, $data);
		return ($this->db->affected_rows() > 0) ? TRUE : FALSE;
	}
}
?>