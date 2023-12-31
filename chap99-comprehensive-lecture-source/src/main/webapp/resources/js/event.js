window.onload = function() {
	
	/* 화면에 랜더링 된 태그들이 존재하지 않는 경우 에러 발생 가능성이 있어서 if문으로 태그가 존재하는지 부터 확인하고 이벤트를 연결한다. */
	if(document.getElementById("regist")) {
		const $regist = document.getElementById("regist");
		$regist.onclick = function() {
			location.href = "/spring/member/regist";
		}
	}
	
	if(document.getElementById("duplicationCheck")) {
		const $duplication = document.getElementById("duplicationCheck");
		$duplication.onclick = function() {
			var userId = document.getElementById("memberId").value.trim();
			$.ajax({
				url:"/spring/member/idDupCheck",
				type:"post",
				data:{userId:userId},
				success:function(data){
					console.log(data);
					alert(data);
				}, error:function(data){
					console.log(data);
				}
			});
			return false;
		}
	}
	
	if(document.getElementById("logout")) {
		const $logout = document.getElementById("logout");
		$logout.onclick = function() {
			location.href = "/spring/member/logout";
		}
	}
	
	if(document.getElementById("updateMember")) {
		const $update = document.getElementById("updateMember");
		$update.onclick = function() {
			location.href = "/spring/member/update";
		}
	}
	
	if(document.getElementById("writeNotice")) {
		const $writeNotice = document.getElementById("writeNotice");
		$writeNotice.onclick = function() {
			location.href = "/spring/notice/regist";
		}
	}
	
	if(document.getElementById("writeBoard")) {
		const $writeBoard = document.getElementById("writeBoard");
		$writeBoard.onclick = function() {
			location.href = "/spring/board/regist";
		}
	}
	
	if(document.getElementById("writeThumbnail")) {
		const $writeThumbnail = document.getElementById("writeThumbnail");
		$writeThumbnail.onclick = function() {
			location.href = "/spring/thumbnail/regist";
		}
	}
}