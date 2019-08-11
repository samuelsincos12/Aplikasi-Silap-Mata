<?php defined('BASEPATH') OR exit('No direct script access allowed');
/**
* Model Petugas
*/
class Petugas_model extends CI_Model {
	
	public function __construct() { parent::__construct(); }
	
	public function create($d) {
		$data = array('name' 	 => $d['t1'], 
		              'user' 	 => $d['t2'], 
		              'password' => $d['t3'], 
		              'foto' 	 => $d['t4'], 
		              'level' 	 => $d['t5']);
		$this->db->insert('tbl_petugas', $data);
		return ($this->db->affected_rows() > 0) ? TRUE : FALSE;
	}

	public function read() {
		$this->db->order_by('id_petugas', 'desc');
		$query = $this->db->get('tbl_petugas');
		return ($query->num_rows() > 0) ? $query->result() : FALSE;
	}

	public function edit($d, $i) {
		$id = array('id_petugas' => $i);
		$data = array('name' 	 => $d['t1'], 
		              'user' 	 => $d['t2'], 
		              'password' => $d['t3'], 
		              'foto' 	 => $d['t4'], 
		              'level' 	 => $d['t5']);
		$this->db->update('tbl_petugas', $data, $id);
		return ($this->db->affected_rows() > 0) ? TRUE : FALSE;
	}

	public function delete($i) {
		$id = array('id_petugas' => $i);
		$this->db->delete('tbl_petugas', $id);
		return ($this->db->affected_rows() > 0) ? TRUE : FALSE;
	}
}
?>