<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp"%>

<script src="/resources/js/common/paging.js"></script>

<!--컨텐츠 내용 영역 시작-->
<section>
	<!--공지사항 전체 내용 시작-->
	<article class="bgwhite">

		<h1 class="sub-title">
			<ul>
				<li>1:1 Inquiry</li>
			</ul>
		</h1>
		<div class="content-item">
			<div class="search-option-group">
				<div class="fl-right">
					<button class="bggray" onclick="openForm()">1:1 Inquiry</button>
				</div>
			</div>

			<!--보유 코인 테이블 시작-->
			<div class="tblType02 mgt0">
				<table>
					<colgroup>
						<col>
						<col style="width: 700px;">
						<col>
						<col>
						<col>
					</colgroup>
					<thead>
						<tr>
							<th>번호</th>
							<th>제목</th>
							<th>작성일</th>
							<th>답변일</th>
							<th>답변여부</th>
						</tr>
					</thead>
					<tbody id="InquiryTable">
						<a onclick="inquiryClick()">
					</tbody>
				</table>

				<!-- 페이징 : Start -->
				<ul class="pagination">
					<li><a class="btn-page first"></a></li>
					<li><a class="btn-page prev"></a></li>
					<li><a href="#" class="active">1</a></li>
					<li><a class="btn-page next"></a></li>
					<li><a class="btn-page last"></a></li>
				</ul>
				<!-- //페이징 : End -->
			</div>
			<!--보유코인 테이블 끝-->

			<!--1:1 문의 내용 및 답변 보기 시작-->
			<div class="mgt20">
				<h1>
					<strong>문의 내용 및 답변</strong>
				</h1>

				<div class="inquiry-view" id=inquiry-view>
					<!--문의 분류 및 제목 영역 시작-->
					<dl>
						<dt id="InquiryType"></dt>
						<dd>
							<strong id="InquiryTitle"></strong>
						</dd>
					</dl>
					<!--문의 분류 및 제목 영역 끝-->

					<div class="inquiry-conversation" id=inquiry-conversation>
						<!--질문 내용 보여지는 영역 시작-->
						<div class="ask-view" id="ask-view"></div>
						<!--질문 내용 보여지는 영역 끝-->

						<!--답변 내용 보여지는 영역 시작-->
						<div class="answer-view" id="answer-view"></div>
						<!--답변 내용 보여지는 영역 끝-->
					</div>


					<!--re-inquiry 눌렀을 경우 나타나는 재문의 입력영역 시작-->
					<div class="re-ask">
						<div class="re-ask-write">
							<textarea placeholder="내용을 입력해 주세요" id="reply_contents"></textarea>
							<div class="filebox">

								<!-- 파일첨부 버튼 : Start -->
								<span id="fileUploadBtnArea">
									<label class="btn-base btn-light" id="fileUploadBtn" onclick="document.all.fileUpload1.click();"> Add file </label> 
									<input type="file" id="ex_filename1" class="upload-hidden">
								</span> 
								<span id="typeFileArea">
									<input type="file" name="fileUpload" id="fileUpload1" onchange="fn_change(1)" />
								</span>
								<!-- 파일첨부 버튼 : End -->

								<!-- 첨부된 파일 리스트 : Start -->
								<div class="filebox-inner mCustomScrollbar fl-right">
									<div class="filebox-item"></div>
								</div>
								<!-- //첨부된 파일 리스트 : End -->
							</div>
						</div>
						<div class="re-ask-regist">
							<button class="bggray" onclick="reaskregistClick();">등록</button>
						</div>

					</div>
					<!--re-inquiry 눌렀을 경우 나타나는 재문의 입력영역 끝-->

				</div>
			</div>
			<!--1:1 문의 내용 및 답변 끝-->

		</div>

	</article>
	<!--공지사항전체 전체 내용 끝-->
</section>
<!--컨텐츠 내용 영역 끝-->


<!--문의하기 팝업 시작-->
<div class="inquiry-pop" id="myForm">
	<div class="popup-back"></div>
	<div class="popup more">
		<form action="">
			<div class="pop-title">
				<span>1:1 Inquiry</span>
				<div class="cancel" onclick="closeForm()">
					<img src="/resources/img/btn-alert-close.png" alt="닫기버튼">
				</div>
			</div>
		</form>

		<div class="popup-cont">

			<h1>Choose 1:1 Inquiry Classification</h1>
			<table class="tblType01">
				<colgroup>
					<col style="width: 130px">
					<col>
				</colgroup>
				<tbody>
					<tr>
						<th>Classification</th>
						<td>
							<div class="select-box">
								<label for="country" id="selectLabel">Choose Classification</label> <select id="ex-select"></select>
							</div>
						</td>
					</tr>
					<tr>
						<th>Attach files</th>
						<td>
							<div class="filebox">
								<!-- 첨부된 파일 리스트 : Start -->
								<div class="filebox-inner mCustomScrollbar">
									<div class="filebox-itemP"></div>
								</div>
								<!-- //첨부된 파일 리스트 : End -->

								<!-- 파일첨부 버튼 : Start -->
								<span id="fileUpPBtnArea"> <label class="btn-base btn-light" id="fileUpPBtn" style="float: right;" onclick="document.all.fileUpP1.click();"> Attach files </label> <input type="file" id="ex_filename1" class="upload-hidden">
								</span> <span id="typeFilePArea"> <input type="file" name="fileUpP" id="fileUpP1" onchange="fn_changeP(1)" />
								</span>
								<!-- 파일첨부 버튼 : End -->
							</div>
							<div class="tip-txt">
								- Photo file must be one of jpg, png, gif format <br> - Total size of attached files can't exceed 50MB
							</div>
						</td>
					</tr>

				</tbody>
			</table>

			<h1 class="mgt20">Enter 1:1 Inquiry</h1>
			<table class="tblType01">
				<colgroup>
					<col style="width: 130px">
					<col>
				</colgroup>
				<tbody>
					<tr>
						<th>Subject</th>
						<td><input type="text" placeholder="Enter the Subject" id="inquiry_title"></td>
					</tr>
					<tr>
						<th>Contents</th>
						<td><textarea placeholder="Enter body text" id="inquiry_contents"></textarea></td>
					</tr>
				</tbody>
			</table>
			<div class="btn-wrap">
				<button type="submit" class="big bggray" onclick="newInauiryClick();">1:1 Inquiry</button>
			</div>
		</div>
	</div>
</div>


<!--문의하기 팝업 끝-->



<script>
	var Click_inquiry_no;
	var paging;
	var file_Index;
	var zoomx = 100;

	//상단 전체메뉴 
	$(document).ready(function() {
		$(".gnb-menu").hover(function() {
			$(".sub-menu-wrap").stop().slideDown(400);
		}, function() {
			$(".sub-menu-wrap").stop().slideUp(200);
		});

		$(".sub-menu-wrap").hover(function() {
			$(".sub-menu-wrap").stop().slideDown(400);
		}, function() {
			$(".sub-menu-wrap").stop().slideUp(200);
		});
	});

	//마켓별 전체보기
	$(document).ready(function() {
		$('.btn-folding').click(function() {
			$('.folding').toggleClass('more');
		});
	});

	/*북마크*/
	$('.bookmark').click(function() {
		$(this).toggleClass('mark-on');
	});

	/*슬릭스 슬라이더*/
	$(".regular").slick({
		dots : true,
		infinite : true,
		slidesToShow : 4,
		slidesToScroll : 4,
		autoplay : false,
		autoplaySpeed : 4000
	});

	//jquery 탭
	$(document).ready(function() {
		$("#tab-group").tabs();

		$("#datepicker").datepicker({
			inline : true
		});
	});

	//1:1문의 토글
	function filetitleClick() {
		$('.filetitle-box').toggle();
	}

	//label을 사용한 셀렉트
	var selectTarget = $('.select-box select');
	selectTarget.change(function() {
		var select_name = $(this).children('option:selected').text();
		$(this).siblings('label').text(select_name);
		$('label').addClass('on');
	});

	//팝업 열기
	function openForm() {
		var p = {};

		document.getElementById("myForm").style.display = "block";
		ajax(
				'/coinis/inquiry/inquiryCode',
				p,
				function(data) {
					if (data.length > 0) {
						for ( var arrCnt in data)
							$('#ex-select').append($('<option value=' + data[arrCnt].code_num_val + '>'
									+ data[arrCnt].code_val_nm
									+ '</option>'));
						$('#selectLabel').text(data[0].code_val_nm);
					} else {
						lrt.client('조회에 실패하였습니다.', '1:1문의');
					}
				})
	}

	//팝업 닫기   
	function closeForm() {
		document.getElementById("myForm").style.display = "none";
		$('#ex-select').empty();
	}

	/* 페이징 */
	$(document).ready(function() {
		// Paging
		paging = Pu.init({
			count : 5,
			PageCount : 5,
			draw : doInquiry,
			paginate : '.pagination',
			initCllBck : true,
			type : 'search',
			totalText : '#count',
			initEventFun : doInquiry
		});
	});

	/* 1:1 문의 리스트 가져오기 */
	function doInquiry(i, done) {
		var p = {};
		p["startIndex"] = i;
		p["queryDataNum"] = 5;

		ajaxOption({
			url : '/coinis/inquiry/inquiryList',
			data : p,
			success : function(result) {
				var inquiryList = result.inquirydata;
				var dataTemplate = $('#template-inquiry-list').html();
				var rows = "";

				if (result.size > 0) {
					$(inquiryList).each(function() {
						// usertype = 1 : 사용자, usertype = 2 : 관리자
						if(this.rep_reg_dt != null && this.usertype != 1) {
							rows += dataTemplate.replace(/{{reg_dt}}/g, getCstmFrmt(this.reg_dt, "yyyyMMddHHmm","yyyy-MM-dd HH:mm"))
							.replace(/{{rep_reg_dt}}/g, getCstmFrmt(this.rep_reg_dt, "yyyyMMddHHmm", "yyyy-MM-dd HH:mm"))
							.replace(/{{inquiry_no}}/g, this.inquiry_no)
							
							.replace(/{{inquiry_title}}/g, this.inquiry_title)
							.replace(/{{replyYN}}/g, "답변완료")
						} else {
							rows += dataTemplate.replace(/{{reg_dt}}/g, getCstmFrmt(this.reg_dt, "yyyyMMddHHmm", "yyyy-MM-dd HH:mm"))
							.replace(/{{rep_reg_dt}}/g, "")
							.replace(/{{inquiry_no}}/g, this.inquiry_no)
							.replace(/{{inquiry_title}}/g, this.inquiry_title)
							.replace(/{{replyYN}}/g, "대기중")
						}
					});
					$('tbody[id="InquiryTable"]').html(rows);
					inquiry_no = inquiryList[0].inquiry_no;

					for ( var arrCnt in inquiryList) {
						if(inquiryList[arrCnt].rep_reg_dt == null || inquiryList[arrCnt].usertype == 1) {
							var replyYN = document.getElementById(eval('"replyYN" + inquiryList[arrCnt].inquiry_no'));
							replyYN.style.color = "#74797e";
						}
					}
					
					SelectOne(inquiry_no);
				} else
					$('tbody[id="InquiryTable"]').html(
							$('#template-inquiry-list-empty').html());
			},
			done : done
		})
	}

	/* 특정 1:1 문의 조회 버튼 */
	function inquiryClick(inquiry_no) {
		SelectOne(inquiry_no);
	}

	/* 답변(재질문) form 열기 버튼 */
	function ReinquiryClick() {
		$('.re-ask').toggle();
	}

	/* 답변(재질문) 등록 버튼 */
	function reaskregistClick() {
		
		var fileP_length = $("P[id^=fileP]").length;
		var formData = new FormData();
		var reply_contents = $("#reply_contents").val();
		
		for (var Cnt = 1; Cnt <= fileP_length; Cnt++) {
			var fileUpload_var = document.getElementById("fileUpload" + Cnt);
			formData.append("uploadfile", fileUpload_var.files[0]);
		}
		
		formData.append("inquiry_no", Click_inquiry_no);
		formData.append("reply_contents", reply_contents);
				
		if (reply_contents == '') {
			lrt.client('문의 내용을 입력해주세요.');
			return;
		} else if (typeof Click_inquiry_no == "undefined") {
			lrt.client('재문의를 할 수 없습니다.');
			return;
		}
				
		$.ajax({
			url: "/coinis/inquiry/addreask",
			type: 'POST',
			data: formData,
			contentType: false,
			processData: false,
			success: function (data) {
				if (data.rtnCd == 0) {
					SelectOne(Click_inquiry_no);
					document.getElementById("reply_contents").value='';
					
					for (var Cnt = 1; Cnt <= fileP_length; Cnt++) {
						fn_Delete(Cnt);
					}
					
					$('.filebox-item').html('');
					$('#typeFileArea').html('<input type="file" name="fileUpload" id="fileUpload1" onchange="fn_change(1)" />');
					$('#fileUploadBtnArea').html('<label class="btn-base btn-light" id="fileUploadBtn" onclick="document.all.fileUpload1.click();"> Add file </label>');
				} else {
					lrt.client(data.message, '1:1문의');	
				}
	        }
		});	
	}

	/* 새로운 질문 등록 버튼 */
	function newInauiryClick() {

		var fileP_length = $("P[id^=fileP]").length;
		var formData = new FormData();
		var target = document.getElementById("ex-select");
		var inquiry_type_cd = target.options[target.selectedIndex].value;
		var inquiry_title = $("#inquiry_title").val();
		var inquiry_contents = $("#inquiry_contents").val();
		
		for (var Cnt = 1; Cnt <= fileP_length; Cnt++) {
			var fileUpload_var = document.getElementById("fileUpP" + Cnt);
			formData.append("uploadfile", fileUpload_var.files[0]);
		}

		formData.append("inquiry_type_cd", inquiry_type_cd);
		formData.append("inquiry_title", inquiry_title);
		formData.append("inquiry_contents", inquiry_contents);
		
		if (inquiry_title == '') {
			lrt.client('문의 제목을 입력해주세요.');
			return;
		} else if (inquiry_contents == '') {
			lrt.client('문의 내용을 입력해주세요.');
			return;
		}		
		
		$.ajax({
			url: "/coinis/inquiry/addInquiry",
			type: 'POST',
			data: formData,
			contentType: false,
			processData: false,
			success: function (data) {
				if (data.rtnCd == 0) {
					location.reload();
				} else {
					lrt.client(data.message, '1:1문의');	
				}
	        }
		});
	}

	/* 특정 1:1 문의 타입, 제목, 내용 조회 */
	function SelectOne(inquiry_no) {
		
		if(typeof Click_inquiry_no != "undefined") {
			var Aftertitle = document.getElementById(eval('"title" + Click_inquiry_no'));
			Aftertitle.style.color = "#0f1217";
		}

		Click_inquiry_no = inquiry_no;
		
		var title = document.getElementById(eval('"title" + inquiry_no'));
		title.style.color = "#0089ff";
	    
		var p = {};
		p["inquiry_no"] = inquiry_no

		ajax('/coinis/inquiry/oneinquiry', p, function(data) {
			document.getElementById("inquiry-conversation").innerHTML = ""

			var reg_dt = getCstmFrmt(data.reg_dt, "yyyyMMddHHmm","yyyy-MM-dd HH:mm")
			
			$("#InquiryType").text(data.inquiry_type);
			$("#InquiryTitle").text(data.inquiry_title);
			
			if(data.filename.length > 0){
				add_ask_file_div(data.inquiry_contents, reg_dt, data.filename);
			} else {
				add_ask_div(data.inquiry_contents, reg_dt);
			}
			
			var InquiryAnswer = data.answer;
			var flag = 0;
			
			if(InquiryAnswer.length > 0) {
				for (var arrCnt in InquiryAnswer) {
					var reg_dt = getCstmFrmt(InquiryAnswer[arrCnt].reg_dt, "yyyyMMddHHmm","yyyy-MM-dd HH:mm");
					var replycontents = InquiryAnswer[arrCnt].replycontents;
					
					if (InquiryAnswer[arrCnt].usertype == 1) {
						if(InquiryAnswer[arrCnt].filename.length > 0) {
							add_ask_file_div(replycontents, reg_dt, InquiryAnswer[arrCnt].filename);
						} else {
							add_ask_div(replycontents, reg_dt);
						}
					} else {
						if (InquiryAnswer.length - 1 == arrCnt)
							add_answer_div(replycontents, reg_dt, 1);
						else
							add_answer_div(replycontents, reg_dt, 0);
						flag = 1;
					}
				}
			}
			if(flag == 0) {
				add_answer_div("No Answer", "", 1);
			}
		});
	}
</script>

<!-- 팝업 내 파일 관련 -->
<script>
	/* 파일 첨부 */
	function fn_changeP(file_Index) {
		// 사이즈체크								
		var maxSize = 5 * 1024 * 1024;
		var fileUpload_var = document.getElementById("fileUpP" + file_Index);
		var fileUpload_length = $("input[name=fileUpP]").length - 1;
		var fileUpload_var_length = fileUpload_var.files.length;
		var fileUpload_tot_count = fileUpload_length + fileUpload_var_length;

		if (fileUpload_tot_count > 4) {
			lrt.client("첨부파일 갯수는 최대 4개까지 가능합니다.");
			return;
		}
		for (var i = 0; i < fileUpload_var.files.length; i++) {
			var fileName = fileUpload_var.files[i].name;
			var fileSize = fileUpload_var.files[i].size;
			if (fileSize > maxSize) {
				lrt.client("첨부파일 사이즈는 5MB 이내로 등록 가능합니다.", "인증자료제출");
				return;
			}
			//alert(fileName);
			var fileExt = fileName.substring(fileName.lastIndexOf('.') + 1); //파일의 확장자를 구합니다.							
			if (fileExt.toUpperCase() != "JPG"
					&& fileExt.toUpperCase() != "PNG"
					&& fileExt.toUpperCase() != "GIF"
					&& fileExt.toUpperCase() != "JPEG") {
				lrt.client("JPG, JPEG, PNG, GIF파일만 업로드 할 수 있습니다!", "인증자료제출");
				return;
			}
			fileSize = fileSize / 1048576;
			fileSize = fileSize.toFixed(2);

			var htmlStr = "<p id='fileP" + file_Index + "' >";
			htmlStr += "<span style='display:inline-block;'><a href='javascript:void(0);' onclick='javascript:fn_Delete("
					+ file_Index
					+ "); return false;' class='file-btn-del'></a></span>";
			htmlStr += "<span class='file-name text-ellips' style='vertical-align:text-bottom' id='UpfileP" + file_Index + "'>"
					+ fileName + "</span>";
			htmlStr += "<span class='file-volume'>";

			htmlStr += "<span>" + fileSize + "</span>";
			htmlStr += "<span>MB</span>";
			htmlStr += "</span>";
			htmlStr += "</p>";

			$(".filebox-itemP").append(htmlStr);
			file_Index = Number(file_Index) + 1;

			var fileText = 'Add file';
			htmlStr = "<label class='btn-base btn-light' id='fileUpPBtn' onclick='document.all.fileUpP"
					+ file_Index + ".click();'>" + fileText + "</label>";
			$("#fileUpPBtnArea").empty().append(htmlStr);

			htmlStr = "<input type='file' name='fileUpP' class='upload-hidden' id='fileUpP"
					+ file_Index
					+ "'onchange='fn_changeP("
					+ file_Index
					+ ")' multiple/>";
			$("#typeFilePArea").append(htmlStr);
		}
	}

	/* 파일 삭제 */
	function fn_Delete(file_Index) {
		$("#fileP" + file_Index).remove();
		$("#fileUpP" + file_Index).remove();
	}
</script>

<!-- 메인 내 파일 관련 -->
<script>
	/* 파일 첨부 */
	function fn_change(file_Index) {
		console.log(file_Index);
		// 사이즈체크								
		var maxSize = 5 * 1024 * 1024;
		var fileUpload_var = document.getElementById("fileUpload" + file_Index);
		var fileUpload_length = $("input[name=fileUpload]").length - 1;
		var fileUpload_var_length = fileUpload_var.files.length;
		var fileUpload_tot_count = fileUpload_length + fileUpload_var_length;

		if (fileUpload_tot_count > 4) {
			lrt.client("첨부파일 갯수는 최대 4개까지 가능합니다.");
			return;
		}
		for (var i = 0; i < fileUpload_var.files.length; i++) {
			var fileName = fileUpload_var.files[i].name;
			var fileSize = fileUpload_var.files[i].size;
			if (fileSize > maxSize) {
				lrt.client("첨부파일 사이즈는 5MB 이내로 등록 가능합니다.", "인증자료제출");
				return;
			}

			var fileExt = fileName.substring(fileName.lastIndexOf('.') + 1); //파일의 확장자를 구합니다.							
			if (fileExt.toUpperCase() != "JPG"
					&& fileExt.toUpperCase() != "PNG"
					&& fileExt.toUpperCase() != "GIF"
					&& fileExt.toUpperCase() != "JPEG") {
				lrt.client("JPG, JPEG, PNG, GIF파일만 업로드 할 수 있습니다!", "인증자료제출");
				return;
			}
			fileSize = fileSize / 1048576;
			fileSize = fileSize.toFixed(2);

			var htmlStr = "<p id='fileP" + file_Index + "' >";
			htmlStr += "<span style='display:inline-block;'><a href='javascript:void(0);' onclick='javascript:fn_Delete("
					+ file_Index
					+ "); return false;' class='file-btn-del'></a></span>";
			htmlStr += "<span class='file-name text-ellips' style='vertical-align:text-bottom'>"
					+ fileName + "</span>";
			htmlStr += "<span class='file-volume'>";

			htmlStr += "<span>" + fileSize + "</span>";
			htmlStr += "<span>MB</span>";
			htmlStr += "</span>";
			htmlStr += "</p>";

			$(".filebox-item").append(htmlStr);
			file_Index = Number(file_Index) + 1;

			var fileText = '파일첨부';
			htmlStr = "<label for='ex_filename' class='btn-base btn-light' id='fileUploadBtn' onclick='document.all.fileUpload"
					+ file_Index + ".click();'>" + fileText + "</label>";
			$("#fileUploadBtnArea").empty().append(htmlStr);

			htmlStr = "<input type='file' name='fileUpload' class='upload-hidden' id='fileUpload"
					+ file_Index
					+ "'onchange='fn_change("
					+ file_Index
					+ ")' multiple/>";
			$("#typeFileArea").append(htmlStr);
		}
	}

	/* 파일 삭제 */
	function fn_Delete(file_Index) {
		$("#fileP" + file_Index).remove();
		$("#fileUpload" + file_Index).remove();
	}
</script>

<!-- 문의 추가 -->
<script type="text/javascript">

	/* 문의 내용 */
	function add_ask_div(replycontents, reg_dt) {
		var div = document.createElement('div');
		div.innerHTML = "<div class=\"ask-view\">"
				+ "<div>"
				+ replycontents
				+ "</div>"
				+ "<div class=\"file-view\">"
				+ " <p>" + reg_dt + "</p>" 
				+ "</div>" 
				+ "</div>";

		document.getElementById('inquiry-conversation').appendChild(div);
	}
	
	/* 자료가 있는 문의 내용 */
	function add_ask_file_div(replycontents, reg_dt, filename) {
		var div = document.createElement('div');
		var htmlStr = "";
		
		for (var arrCnt in filename) {
			htmlStr += "<p><span class=\"file-name\">"
					+ filename[arrCnt].fileName
					+ "</span></p>";
		}
		
		div.innerHTML = "<div class=\"ask-view\">" + "<div>" + replycontents + "</div>"
				+ "<div class=\"file-view\">"
				+ "<p class=\"filetitle-view\" onclick=\"filetitleClick();\">"
				+ "<img src=\"/resources/img/icon-photo.jpg\" alt=\"image icon\"></p>"
				+ " <p>" + reg_dt + "</p>" 
				+ "</div>" 
				+ "<span class=\"filetitle-box\">"
				+ htmlStr				
				+ "</span>"
				+ "</div>";

		document.getElementById('inquiry-conversation').appendChild(div);
	}
	
</script>

<!-- 답변 추가 -->
<script type="text/javascript">
	function add_answer_div(replycontents, reg_dt, flag) {
		var div = document.createElement('div');

		/* flag = 0 : 마지막 답변이 아님, flag = 1 : 마지막 답변 */
		if (flag == 1) {
			div.innerHTML = "<div class=\"answer-view\">"
					+ "<div class=\"re-wrap\">"
					+ "<div class=\"icon-re\"></div>"
					+ "</div>"
					+ "<div class=\"answer-box\">"
					+ replycontents
					+ "</div>"
					+ "<div class=\"re-btn-box\">"
					+ "<p class=\"re-btn\">"
					+ "<button class=\"small bgblue\"onclick=\"ReinquiryClick();\">Re-inquiry</button></p>"
					+ " <p>"
					+ reg_dt
					+ "</p>"
					+ "</div>" + "</div>";
		} else {
			div.innerHTML = "<div class=\"answer-view\">"
					+ "<div class=\"re-wrap\">"
					+ "<div class=\"icon-re\"></div>" + "</div>"
					+ "<div class=\"answer-box\">" + replycontents + "</div>"
					+ "<div class=\"re-btn-box\">"
					+ " <p>"
					+ reg_dt
					+ "</p>"
					+ "</div>";
		}

		document.getElementById('inquiry-conversation').appendChild(div);
	}
</script>

<!-- 1:1 문의 리스트  시작 -->
<script id="template-inquiry-list" type="text/template">
	<tr onclick="inquiryClick({{inquiry_no}})">
		<td>{{inquiry_no}}</td>
		<td id="title{{inquiry_no}}">{{inquiry_title}}</td>
		<td>{{reg_dt}}</td>
		<td>{{rep_reg_dt}}</td>
		<td id="replyYN{{inquiry_no}}">{{replyYN}}</td>
	</tr>
</script>
<Script id="template-inquiry-list-empty" type="text/template">
	<tr>
		<td colspan="5" class="center">You have no Inauiry </td>
	</tr>
</Script>
<!-- 1:1 문의 리스트 끝 -->

</body>

</html>
