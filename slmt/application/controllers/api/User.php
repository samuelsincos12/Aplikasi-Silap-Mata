<?php defined('BASEPATH') OR exit('No direct script access allowed');
/**
* API User 
*/
class User extends CI_Controller {
	
	public function __construct() { parent::__construct(); }

	public function signin() { 
		$data = array('s1' 				=> $this->input->post('nohp'));
		$check = $this->pm->readin($data);
		if ($check) { 
				$row = array('id' 	 	=> $check->id_pelapor, 
				             'nohp'  	=> $check->nohp);

				$resp = array('error' 	=> FALSE, 
				              'user'  	=> $row); 
		} elseif ($this->pm->viewin($data)) {
				$resp = array('error' 	=> TRUE, 
				              'message' => 'No.HP anda belum dikonfirmasi');
		} else {
				$resp = array('error' 	=> TRUE, 
				              'message' => 'No.HP anda salah');
		}

		$this->output->set_status_header(200)
			 		 ->set_content_type('application/json', 'utf-8')
			 		 ->set_output(json_encode($resp))
			 		 ->_display();
        exit;
	}

	public function signup() {
		$data = array('s1' 				=> $this->input->post('nohp'), 
		              's2' 				=> $this->low->kdu());
		if (empty($data['s1']) || empty($data['s2'])) {
				$resp = array('error' 	=> TRUE, 
				              'message' => 'input harus di isi');
		} elseif ($this->pm->viewup($data['s1'])) {
				$resp = array('error' 	=> TRUE, 
				              'message' => 'No.HP anda sudah diregistrasi, silakan login');
		} else {
				if (!$this->pm->create($data)) {
    				$resp = array('error' 	=> TRUE, 
    				              'message' => 'kesalahan sistem');
				} else {
				    $userkey = "samuel"; //userkey lihat di zenziva
                    $passkey = "1234567890ict"; // set passkey di zenziva
                    $telepon = $data['s1'];
                    $message = "(".$data['s2'].") Terima Kasih dari SILAP MATA";
                    $url = "https://reguler.zenziva.net/apps/smsapi.php";
                    $curlHandle = curl_init();
                    curl_setopt($curlHandle, CURLOPT_URL, $url);
                    curl_setopt($curlHandle, CURLOPT_POSTFIELDS, 'userkey='.$userkey.'&passkey='.$passkey.'&nohp='.$telepon.'&pesan='.$message);
                    curl_setopt($curlHandle, CURLOPT_HEADER, 0);
                    curl_setopt($curlHandle, CURLOPT_RETURNTRANSFER, 1);
                    curl_setopt($curlHandle, CURLOPT_SSL_VERIFYHOST, 2);
                    curl_setopt($curlHandle, CURLOPT_SSL_VERIFYPEER, 0);
                    curl_setopt($curlHandle, CURLOPT_TIMEOUT,30);
                    curl_setopt($curlHandle, CURLOPT_POST, 1);
                    $results = curl_exec($curlHandle);
                    curl_close($curlHandle);
    
    				$check = $this->pm->readup($data['s1']);
    				$row = array('id' 	 	=> $check->id_pelapor, 
    				             'nohp'  	=> $check->nohp);
    
    				$resp = array('error' 	=> FALSE, 
    				              'message' => 'registrasi berhasil, silakan tunggu sms balasan untuk verifikasi No.HP',
    				              'user' 	=> $row, 
    				              'sms'     => $results);
				}
		}

		$this->output->set_status_header(200)
			 		 ->set_content_type('application/json', 'utf-8')
			 		 ->set_output(json_encode($resp))
			 		 ->_display();
        exit;
	}

	public function signcv() {
		$data = array('s1' 				=> $this->input->post['code'], 
		              's2' 				=> $this->input->post['nohp']);

		if ($this->pm->readck($data)) {
			$this->pm->editck($data);

			$check = $this->pm->readup($data['s2']);
			$row = array('id' 	 		=> $check->id_pelapor, 
				         'nohp'  		=> $check->nohp);
			$resp = array('error' 		=> FALSE, 
				          'message' 	=> 'No.HP anda sudah dikonfirmasi',
				          'user' 		=> $row);
		} else {
			$resp = array('error' 		=> TRUE, 
				          'message' 	=> 'kode salah');
		}

		$this->output->set_status_header(200)
			 		 ->set_content_type('application/json', 'utf-8')
			 		 ->set_output(json_encode($resp))
			 		 ->_display();
        exit;
	}
}
?>