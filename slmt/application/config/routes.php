<?php
defined('BASEPATH') OR exit('No direct script access allowed');

/*
| -------------------------------------------------------------------------
| URI ROUTING
| -------------------------------------------------------------------------
| This file lets you re-map URI requests to specific controller functions.
|
| Typically there is a one-to-one relationship between a URL string
| and its corresponding controller class/method. The segments in a
| URL normally follow this pattern:
|
|	example.com/class/method/id/
|
| In some instances, however, you may want to remap this relationship
| so that a different class/function is called than the one
| corresponding to the URL.
|
| Please see the user guide for complete details:
|
|	https://codeigniter.com/user_guide/general/routing.html
|
| -------------------------------------------------------------------------
| RESERVED ROUTES
| -------------------------------------------------------------------------
|
| There are three reserved routes:
|
|	$route['default_controller'] = 'welcome';
|
| This route indicates which controller class should be loaded if the
| URI contains no data. In the above example, the "welcome" class
| would be loaded.
|
|	$route['404_override'] = 'errors/page_missing';
|
| This route will tell the Router which controller/method to use if those
| provided in the URL cannot be matched to a valid route.
|
|	$route['translate_uri_dashes'] = FALSE;
|
| This is not exactly a route, but allows you to automatically route
| controller and method names that contain dashes. '-' isn't a valid
| class or method name character, so it requires translation.
| When you set this option to TRUE, it will replace ALL dashes in the
| controller and method URI segments.
|
| Examples:	my-controller/index	-> my_controller/index
|		my-controller/my-method	-> my_controller/my_method
*/
$route['default_controller'] = 'auth';
$route['404_override'] = '';
$route['translate_uri_dashes'] = FALSE;

// Web Routes
$route['login']['post'] = 'auth/signin';
$route['logout']['post'] = 'auth/signout';
$route['dashboard']['get'] = 'auth/home';

$route['monitoring']['get'] = 'laporan/maps';
$route['laporan']['get'] = 'laporan/index';
$route['ldes']['post'] = 'laporan/destroy';
$route['lprt']['post'] = 'laporan/printr';

$route['pelapor']['get'] = 'pelapor/index';
$route['odes']['post'] = 'pelapor/destroy';

$route['petugas']['get'] = 'petugas/index';
$route['padd']['post'] = 'petugas/store';
$route['pedt']['post'] = 'petugas/edit';
$route['pdes']['post'] = 'petugas/destroy';
// End Web Routes

// API Routes
$route['marker']['get'] = 'api/maps/getmarker';
$route['marker1']['get'] = 'api/maps/getmarker1';
$route['marker2']['get'] = 'api/maps/getmarker2';
$route['marker3']['get'] = 'api/maps/getmarker3';
$route['polygon']['get'] = 'api/maps/getpolygon';

$route['log']['post'] = 'api/driver/signin';
$route['onr']['post'] = 'api/driver/driveron';
$route['offr']['post'] = 'api/driver/driveroff';

$route['logp']['post'] = 'api/user/signin';
$route['regp']['post'] = 'api/user/signup';
$route['conp']['post'] = 'api/user/signcv';

$route['rept']['post'] = 'api/report/create';
$route['vers']['post'] = 'api/report/edit';
// End API Routes
