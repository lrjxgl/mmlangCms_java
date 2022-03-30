<?php
$arr=[
	"forum/index",
	"forum/admin",
	"index/index",
	"index/admin"
];
$old='return JSON.toJSONString(redata);';
$new='
		Map<String,Object> reJson=new HashMap<String,Object>();
		reJson.put("data", redata);
		reJson.put("error",0);
		reJson.put("message","succcess");
		return JSON.toJSONString(reJson);
';
foreach($arr as $dir){
	$files=glob($dir."/*");
	foreach($files as $file){
		$c=file_get_contents($file);
		$c=str_replace($old,$new,$c);
		$c=str_replace('redata.put("error",0);','',$c);
		$c=str_replace('redata.put("message","succcess");','',$c);
		file_put_contents($file,$c);
	}
}
echo "success";

?>