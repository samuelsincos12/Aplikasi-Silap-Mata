<?php defined('BASEPATH') OR exit('No direct script access allowed');
/**
* Model Auth
*/
class Auth_model extends CI_Model {
	
	public function __construct() { parent::__construct(); }

	public function read($d) {
		$data = array('user' 	 => $d['u1'], 
					  'password' => $d['u2'], 
					  'level' 	 => 1);
		$query = $this->db->get_where('tbl_petugas', $data);
		return ($query->num_rows() == 1) ? $query->row() : FALSE;
	}

	public function readA($d) {
		$data = array('user' 	 => $d['d1'], 
					  'password' => $d['d2']);
		$query = $this->db->get_where('tbl_petugas', $data);
		return ($query->num_rows() == 1) ? $query->row() : FALSE;
	}

	public function storeAc($d) {
		$data = array('id_petugas' => $d['d1'], 
					  'waktu_on'   => date("H:i:s"), 
					  'waktu_off'  => date("H:i:s"), 
					  'tanggal'    => date("Y-m-d"));
		$this->db->insert('tbl_tugas', $data);
		return ($this->db->affected_rows() > 0) ? TRUE : FALSE;
	}

	public function editAc($d) {
		$id = array('id_petugas' => $d['d1']);
		$data = array('waktu_off' => date("H:i:s"));
		$this->db->update('tbl_tugas', $data, $id);
		return ($this->db->affected_rows() > 0) ? TRUE : FALSE;
	}
}
?>