<%@ page contentType="text/html; charset=UTF-8" %>

<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="card m-2">
	<div class="card-header">
		FileUpload & FileDownload
	</div>
	<div class="card-body">
		<div class="card">
			<div class="card-header">
				Form 태그를 이용한 FileUpload
			</div>
			<div class="card-body">
				<form method="post" enctype="multipart/form-data" action="fileupload">
					<div class="form-group">
						<label for="title">File Title</label> 
						<input type="text" class="form-control" id="title" name="title" placeholder="제목">
					</div>
					<div class="form-group">
						<label for="desc">File Description</label> 
						<input type="text" class="form-control" id="desc" name="desc" placeholder="설명">
					</div>
					<div class="form-group">
					    <label for="attach">Example file input</label>
					    <input type="file" class="form-control-file" id="attach" name="attach"> <%-- multiple 써주면 : 양식 하나에 파일 여러개를 넣을 수 있다 ->사진 선택할 때 컨트롤 누르면 된다--%>
				  	</div>
				  	<button class="btn btn-info btn-sm">Form 파일 업로드</button>
				  	<a href="javascript:fileupload()" class="btn btn-info btn-sm">AJAX 파일 업로드</a>
				</form>
			</div>
			<script>
				function fileupload() {
					//입력된 정보를 얻기
					const title = $("#title").val();
					const desc = $("#desc").val();
					const attach = document.querySelector("#attach").files[0];  //$("#attach")[0].files[0];도 가능
					
					//Multipart/form-data 객체
					const formData = new FormData();
					formData.append("title", title);  //문자 파트 업데이트
					formData.append("desc", desc);	  //문자 파트 업데이트
					formData.append("attach", attach);//파일 파트 업데이트
					
					//Ajax로 서버로 전송
					$.ajax({
						url: "fileuploadAjax",  //요청 경로
						method: "post",			//요청 방식 : multipart/form-data 쓰려면 반드시 post여야함
						data: formData,
						cache: false,			//캐시는 메모리에 저장하지 말아라 -> 캐시 기능 꺼 : 파일이 클스도 있으니 브라우저 메모리에 저장하지 말아라
						processData: false,		//쿼리스트링 형태(title=xxx&desc=xxxd&...)로 만들지 말아라 
						contentType: false		//파트마다 content-Type(이미지 파일인지, 엑셀파일인지...)이 포함되기 때문에 따로 헤더에 content-Type을 추가하지 말아라
					}).done((data) => {
						console.log(data);
						if(data.result === "success") {
							window.alert("파일 전송이 성공됨");
						}
					});
				}
			</script>
		</div>
	
		<div class="card">
			<div class="card-header">
				File Downlaod
			</div>
			<div class="card-body">
				<a href="filedownload?fileNo=1"
				   class="btn btn-info btn-sm">파일 다운로드</a>
				<hr/>
				<img src="filedownload?fileNo=1" width="200px"/>
			</div>
		</div>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>