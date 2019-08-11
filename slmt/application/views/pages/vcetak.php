  <!-- Setting CSS bagian header/ kop -->
<style type="text/css">
  table.page_header {width: 1020px; border: none; background-color: #DDDDFF; border-bottom: solid 1mm #AAAADD; padding: 2mm }
  table.page_footer {width: 1020px; border: none; background-color: #DDDDFF; border-top: solid 1mm #AAAADD; padding: 2mm}
  h1 {color: #000033}
  h2 {color: #000055}
  h3 {color: #000077}
</style>
<!-- Setting Margin header/ kop -->
<page backtop="14mm" backbottom="14mm" backleft="1mm" backright="10mm">
  <page_header>
    <!-- Setting Header -->
    <table class="page_header">
      <tr>
        <td style="text-align: left;    width: 10%">SILAP MATA</td>
        <td style="text-align: center;    width: 80%">LAPORAN PELANGGARAN KESELURUHAN</td>
        <td style="text-align: right;    width: 10%"><?php echo date('d/m/Y'); ?></td>
      </tr>
    </table>
  </page_header>
  <!-- Setting CSS Tabel data yang akan ditampilkan -->
  <style type="text/css">
  .tabel2 {
    border-collapse: collapse;
  }
  .tabel2 th, .tabel2 td {
      padding: 5px 5px;
      border: 1px solid #000;
  }
  </style>
  <table>
    <tr>
      <th rowspan="3"><img src="assets/images/sm.png" style="width:120px;height:100px" /></th>
      <td align="center" style="width: 800px;"><font style="font-size: 18px"><br><b>LAPORAN PELANGGARAN RAMBU LALU LINTAS</b></font>
        <br><br>
        <br><br></td>
    </tr>
  </table>
  <hr><br><br>
  <table class="tabel2">
    <thead>
      <tr>
        <td style="text-align: center; background: #ddd"><b>No.</b></td>
        <td style="text-align: center; background: #ddd"><b>Gambar</b></td>
        <td style="text-align: center; background: #ddd"><b>Jenis Pelanggaran</b></td>
        <td style="text-align: center; background: #ddd"><b>Alamat</b></td>
        <td style="text-align: center; background: #ddd"><b>Keterangan</b></td>
        <td style="text-align: center; background: #ddd"><b>Tanggal</b></td>
        <td style="text-align: center; background: #ddd"><b>Nama Petugas</b></td>
        <td style="text-align: center; background: #ddd"><b>Status Laporan</b></td>
      </tr>
    </thead>
    <tbody>
<?php $aa = 1;?><?php foreach ($cetak as $ct): ?>
      <tr>
        <td style="text-align: center; width=10px;"><?php echo $aa++; ?></td>
        <td style="text-align: center; width=100px;"><img src="assets/images/report/<?php echo $ct->gambar;?>" style="width=100px;"/></td>
        <td style="text-align: center; width=40px;"><?php echo $ct->jns_pelanggaran; ?></td>
        <td style="text-align: center; width=85px;"><?php echo $ct->alamat; ?></td>
        <td style="text-align: center; width=75px;"><?php echo $ct->keterangan; ?></td>
        <td style="text-align: center; width=50px;"><?php echo $ct->tanggal; ?></td>
        <td style="text-align: center; width=45px;"><?php echo $ct->name; ?></td>
        <td style="text-align: center; width=45px;"><?php echo $ct->status; ?></td>
      </tr>
<?php endforeach; ?>
    </tbody>
  </table>
</page>
<!-- Setting Footer -->
  <page_footer>
    <table class="page_footer">
      <tr>
        <td style="width: 33%; text-align: left">
          Dicetak oleh: <?php echo $this->session->userdata('nama') ?>
        </td>
        <td style="width: 33%; text-align: right">
          Halaman [[page_cu]]/[[page_nb]]
        </td>
      </tr>
    </table>
  </page_footer>