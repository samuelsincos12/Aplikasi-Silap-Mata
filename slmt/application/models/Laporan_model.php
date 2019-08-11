<?php defined('BASEPATH') OR exit('No direct script access allowed');
/**
* Model Laporan
*/
class Laporan_model extends CI_Model {
	
	public function __construct() { parent::__construct(); }

	public function read() {
		$this->db->select('tbl_laporan.id_laporan, 
						   tbl_laporan.gambar, 
		                   tbl_laporan.jns_pelanggaran,
		                   tbl_laporan.lat,
		                   tbl_laporan.lon,
		                   tbl_laporan.alamat,
		                   tbl_laporan.keterangan, 
		                   tbl_laporan.tanggal, 
		                   tbl_laporan.waktu, 
		                   tbl_petugas.name, 
		                   tbl_aksi.status');
		$this->db->from('tbl_laporan');
		$this->db->join('tbl_aksi',
		                'tbl_aksi.id_laporan = tbl_laporan.id_laporan', 
		                'left');
		$this->db->join('tbl_petugas', 
						'tbl_petugas.id_petugas = tbl_aksi.id_petugas', 
						'left');
		$query = $this->db->get();
		return ($query->num_rows() > 0) ? $query->result() : FALSE;
	}

	public function delete($i) {
		$id = array('id_laporan' => $i);
		$this->db->delete('tbl_laporan', $id);
		return ($this->db->affected_rows() > 0) ? TRUE : FALSE;
	}

	public function readMap() {
		$this->db->select('tbl_laporan.id_laporan, 
						   tbl_laporan.gambar, 
		                   tbl_laporan.jns_pelanggaran,
		                   tbl_laporan.lat,
		                   tbl_laporan.lon, 
		                   tbl_laporan.alamat,
		                   tbl_laporan.keterangan,
		                   tbl_laporan.tanggal, 
		                   tbl_laporan.waktu,  
		                   tbl_aksi.status');
		$this->db->from('tbl_laporan');
		$this->db->join('tbl_aksi',
		                'tbl_aksi.id_laporan = tbl_laporan.id_laporan', 
		                'left');
		$query = $this->db->get();
		return ($query->num_rows() > 0) ? $query->result() : FALSE;
	}
	
	public function readMap1() {
		$data = array('tbl_aksi.status' => 1);
		$this->db->select('tbl_laporan.id_laporan, 
						   tbl_laporan.gambar, 
		                   tbl_laporan.jns_pelanggaran,
		                   tbl_laporan.lat,
		                   tbl_laporan.lon, 
		                   tbl_laporan.keterangan, 
		                   tbl_laporan.tanggal, 
		                   tbl_laporan.waktu,  
		                   tbl_aksi.status');
		$this->db->from('tbl_laporan');
		$this->db->join('tbl_aksi',
		                'tbl_aksi.id_laporan = tbl_laporan.id_laporan', 
		                'left');
		$this->db->where($data);
		$query = $this->db->get();
		return ($query->num_rows() > 0) ? $query->result() : FALSE;
	}

	public function readMap2() {
		$data = array('tbl_aksi.status' => 2);
		$this->db->select('tbl_laporan.id_laporan, 
						   tbl_laporan.gambar, 
		                   tbl_laporan.jns_pelanggaran,
		                   tbl_laporan.lat,
		                   tbl_laporan.lon, 
		                   tbl_laporan.keterangan, 
		                   tbl_laporan.tanggal, 
		                   tbl_laporan.waktu,  
		                   tbl_aksi.status');
		$this->db->from('tbl_laporan');
		$this->db->join('tbl_aksi',
		                'tbl_aksi.id_laporan = tbl_laporan.id_laporan', 
		                'left');
		$this->db->where($data);
		$query = $this->db->get();
		return ($query->num_rows() > 0) ? $query->result() : FALSE;
	}

	public function readMap3() {
		$data = array('tbl_aksi.status' => 3);
		$this->db->select('tbl_laporan.id_laporan, 
						   tbl_laporan.gambar, 
		                   tbl_laporan.jns_pelanggaran,
		                   tbl_laporan.lat,
		                   tbl_laporan.lon, 
		                   tbl_laporan.keterangan, 
		                   tbl_laporan.tanggal, 
		                   tbl_laporan.waktu,  
		                   tbl_aksi.status');
		$this->db->from('tbl_laporan');
		$this->db->join('tbl_aksi',
		                'tbl_aksi.id_laporan = tbl_laporan.id_laporan', 
		                'left');
		$this->db->where($data);
		$query = $this->db->get();
		return ($query->num_rows() > 0) ? $query->result() : FALSE;
	}

	public function viewR($d) {
		$data = array('gambar' => $d['r1']);
		$this->db->select('id_laporan');
		$this->db->from('tbl_laporan');
		$this->db->where($data);
		$query = $this->db->get();
		return ($query->num_rows() > 0) ? $query->row() : FALSE;
	}

	public function store($d) {
		$data = array('gambar' 			=> $d['r1'], 
		              'jns_pelanggaran' => $d['r2'],
		              'lat' 			=> $d['r3'],
		              'lon' 			=> $d['r4'],
		              'alamat'          => $d['r5'], 
		              'keterangan' 		=> $d['r6'], 
		              'tanggal' 		=> date("Y-m-d"), 
		              'waktu' 			=> date("H:i:s"));
		$this->db->insert('tbl_laporan', $data);
		return ($this->db->affected_rows() > 0) ? TRUE : FALSE;	
	}

	public function storeA($d) {
		$data = array('id_petugas' => 0, 
					  'id_laporan' => $d['r1'], 
					  'status'	   => 1, 
					  'tanggal'    => date("Y-m-d"), 
					  'waktu'      => date("H:i:s"));
		$this->db->insert('tbl_aksi', $data);
		return ($this->db->affected_rows() > 0) ? TRUE : FALSE;
	}

	public function updateA($d) {
		$id = array('id_laporan'   => $d['r2']);
		$data = array('id_petugas' => $d['r1'],
					  'status'	   => $d['r3'], 
					  'tanggal'    => date("Y-m-d"), 
					  'waktu'      => date("H:i:s"));
		$this->db->update('tbl_aksi', $data, $id);
		return ($this->db->affected_rows() > 0) ? TRUE : FALSE;
	}
}
?>