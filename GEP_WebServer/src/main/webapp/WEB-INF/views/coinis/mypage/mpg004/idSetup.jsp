<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp" %>

<section>
    <!--보안설정 전체 내용 시작-->
    <article class="bgwhite">

        <h1 class="sub-title">
            <ul>
                <li>My page</li>
                <li>ID Card</li>
            </ul>
        </h1>
        <div class="content-item">
            <div id="tab-group" class="secure">
                <ul class="tabs-2li">
                    <li class="tabs-gray"><a href="#tab1">Individual member </a></li>
                    <li class="tabs-gray"><a href="#tab2">Corporate member</a></li>
                </ul>
                
                <!-- 개인 신분증 인증 -->
                <div id="tab1">
                 	<form id="IdvForm">
	                    <table class="tblType01 mgt20">
	                        <colgroup>
	                            <col style="width: 200px">
	                            <col>
	                        </colgroup>
	                        <tbody>
	                            <tr>
	                                <th>Full Name</th>
	                                <td><input type="text" class="basic" placeholder="john doe" name="name" value=""></td>
	                            </tr>
	                            <tr>
	                                <th>Date of Birth</th>
	                                <td>
	                                    <input type="text" id="Date" required="" value="" class="basic" name="birthday">
	                                </td>
	                            </tr>
	                            <tr>
	                                <th>Address</th>
	                                <td><input type="text" class="basic" name="address" value="" paddnig-left="5" style="width:650px"></td>
	                            </tr>
	                            <tr>
	                                <th>Postal Code</th>
	                                <td><input type="text" class="basic" name="postalCode" value=""></td>
	                            </tr>
	                            <tr>
	                                <th>Country</th>
	                                <td>
	                                    <div class="select-box country">
	                                        <label for="country">Country</label>
	                                        <select id="ex-select" class="country" name="country">
	                                        	<c:forEach var="countryName" items="${countryList}">
	                                        		<option value="${countryName.COUNTRY_CD}">${countryName.ENG_NM}</option>
	                                        	</c:forEach>
	                                        </select>
	                                    </div>
	
	                                </td>
	                            </tr>
	
	                            <tr>
	                                <th>ID Type</th>
	                                <td>
	                                    <div class="rd-box">
	                                        <input type="radio" name="idType" id="ex_rd1" value="1" checked="checked">  
	                                        <label for="ex_rd1">Identity card</label>
	                                        <input type="radio" name="idType" id="ex_rd2" value="2">
	                                        <label for="ex_rd2">Driver's license</label>
	                                        <input type="radio" name="idType" id="ex_rd3" value="3">  
	                                        <label for="ex_rd3">Passport</label>
	                                    </div>
	                                </td>
	                            </tr>
	                            <tr>
	                                <th>
	                                    ID Card Photo
	                                </th>
	                                <td>
	                                   <div class="fileimg">
	                                        <h1>ID Card front :</h1>
	                                        <label>
	                                            <img id="image_section" src="/resources/img/img-id01.jpg" alt="your image" />
	                                            <span>Click Upload</span>
	                                            <input type="file" id="imgInput_1" name="file"/>
	                                        </label>
	                                        
	                                    </div> 
	                                    
	                                     <div class="fileimg">
	                                        <h1>Handheld ID card :</h1>
	                                        <label>
	                                            <img id="image_section1" src="/resources/img/img-id02.jpg" alt="your image" />
	                                            <span>Click Upload</span>
	                                            <input type="file" id="imgInput1_2" name="file"/>
	                                        </label>
	                                    </div>
	                                    
	                                   
	                                <p class="txt-gray mgt10">Only jpg/png files supported and the file should not exceed 4MB</p>
	                                </td>
	                            </tr>
	                            
	                        </tbody>
	                    </table>
					</form>
                    <div class="btn-wrap mgb20">
                        <button class="big bgblue" id="idvSubmitBtn" >Submit</button>
                    </div>
                </div>
                
                <!-- 법인 신분증 인증 -->
                <div id="tab2">
                	<form id="crpForm">
	                    <table class="tblType01 mgt20">
	                        <colgroup>
	                            <col style="width: 200px">
	                            <col>
	                        </colgroup>
	                        <tbody>
	                        	<tr>
	                                <th>Corporation Registration No</th>
	                                <td>
	                                	<input type="text" class="basic" name="corpNo">
	                                	<button type="button" onclick="authCorpNo()" class="small gray">Auth</button>	
	                                </td>
	                            </tr>
	                            <tr>
	                                <th>Corporate Name</th>
	                                <td id="corp_nm"><label class="basic"></label></td>
	                            </tr>
	                            <tr>
	                                <th>Representative Name</th>
	                                <td id="represent_nm"><label class="basic"></label></td>
	                            </tr>
	                            <tr>
	                                <th>E-mail(login)ID</th>
	                                <td><label class="basic" value="${user_id}">${user_id}</label></td>
	                            </tr>
	                            <tr>
	                                <th>Address</th>
	                                <td id="corp_addr">
	                                	<label class="basic"></label>
	                                </td>
	                            </tr>
								<tr>
	                                <th>Main Contact Number</th>
	                                <td id="corp_tel_no">
	                                	<label class="basic"></label>
	                                </td>
	                            </tr>
	                            <tr>
	                                <th>ID Type</th>
	                                <td>
	                                    <div class="rd-box">
	                                        <input type="radio" name="idType" id="ex_rd1" value="1" checked="checked">  
	                                        <label for="ex_rd1">Identity card</label>
	                                        <input type="radio" name="idType" id="ex_rd2" value="2">
	                                        <label for="ex_rd2">Driver's license</label>
	                                        <input type="radio" name="idType" id="ex_rd3" value="3">  
	                                        <label for="ex_rd3">Passport</label>
	                                    </div>
	                                </td>
	                            </tr>
	                            <tr>
	                                <th>
	                                    ID Card Photo
	                                </th>
	                                <td>
	                                   <div class="fileimg">
	                                        <h1>ID card of person in charge :</h1>
	                                        <label>
	                                            <img id="image_section" src="/resources/img/img-id01.jpg" alt="your image" />
	                                            <span>Click Upload</span>
	                                            <input type="file" id="imgInput2_1" name="file"/>
	                                        </label>
	                                        
	                                    </div> 
	                                    
	                                     <div class="fileimg">
	                                        <h1>Representative ID card :</h1>
	                                        <label>
	                                            <img id="image_section1" src="/resources/img/img-id01.jpg" alt="your image" />
	                                            <span>Click Upload</span>
	                                            <input type="file" id="imgInput2_2" name="file"/>
	                                        </label>
	                                    </div>
	                                    
	                                    <div class="fileimg">
	                                        <h1>Business Registration :</h1>
	                                        <label>
	                                            <img id="image_section1" src="/resources/img/img-id03.jpg" alt="your image" />
	                                            <span>Click Upload</span>
	                                            <input type="file" id="imgInput2_3" name="file"/>
	                                        </label>
	                                    </div>
	                                   
	                                <p class="txt-gray mgt10">Only jpg/png files supported and the file should not exceed 4MB</p>
	                                </td>
	                            </tr>
	                            
	                        </tbody>
	                    </table>
					</form>
                    <div class="btn-wrap mgb20">
                        <button class="big bgblue" id="CorpSubmitBtn">Submit</button>
                    </div>
                </div>
            </div>
        </div>

    </article>
    <!--보안설정 전체 내용 끝-->
</section>
    
<script>
    

$(document).ready(function() {
	//jquery 탭
	$("#tab-group").tabs();
	
    //달력
    $(function() {
        $("#Date").datepicker();
    });   
    
	//팝업 열고 닫기   
	function openForm() {
	    document.getElementById("myForm").style.display = "block";
	}
	
	function closeForm() {
	    document.getElementById("myForm").style.display = "none";
	}
	
	//label을 사용한 셀렉트
    var selectTarget = $('.select-box select');
    selectTarget.change(function() {
        var select_name = $(this).children('option:selected').text();
        $(this).siblings('label').text(select_name);
        $('label').addClass('on');
    });
    
    $('#ex-select').val(" ");
	
	//파일첨부
	$('input[type=file]').on('change', function() {
		readURL(this);
	})
	
	function readURL(input) {
	    if (input.files && input.files[0]) {
	        var reader = new FileReader();
	        reader.onload = function(e) {
	            $(input.previousElementSibling.previousElementSibling).attr('src', e.target.result);
	        }
	        reader.readAsDataURL(input.files[0]);
	    }
	}
    
});

// 개인 회원 신분증 인증 버튼
$('#idvSubmitBtn').on('click', function() {
	
	uploadIndividualIDCard();
});

// 밥인 회원 신분증 인증 버튼
$('#CorpSubmitBtn').on('click', function() {
	
	uploadCorporateIDCard();
});

// 개인 회원 신분증 인증 전송
 function uploadIndividualIDCard() {

	var form = $('#IdvForm');
	var file1 = form.find('#imgInput_1');
	var file2 = form.find('#imgInput1_2');
	
	var formData = new FormData(form[0]);
	
	if( form.find('input[name="name"]').val() == '') {
		lrt.client('Input your name', 'idSetup');
		return false;
	} else if( form.find('input[name="birthday"]').val() == '') {
		lrt.client('Input your birthday', 'idSetup');
		return false;
	} else if( form.find('input[name="address"]').val() == '') {
		lrt.client('Input your address', 'idSetup');
		return false;
	} else if( form.find('select[name="country"]').val() == null) {
		lrt.client('select country', 'idSetup');
		return false;
	} else if( form.find('select[name="idType"]').val() == '') {
		lrt.client('select ID type', 'idSetup');
		return false;
	} else if( file1.val() == '') {
		lrt.client('you should upload ID card photo', 'idSetup');
		return false;
	} else if ( file2.val() == '') {
		lrt.client('you should upload handheld ID card photo', 'idSetup');
		return false;
	} else if ( !checkFileSize(file1) ) {
		lrt.client('size of first file exceededed 4MB', 'idSetup');
		return false;
	} else if ( !checkFileSize(file2) ) {
		lrt.client('size of second file exceededed 4MB', 'idSetup');
		return false;
	} else if ( !checkExtension(file1) ) {
		lrt.client('only jpg/png extension is uploadable', 'idSetup');
		return false;
	}

	ajaxOption({
		url: '/coinis/idSetup/submitIdvIdcard',
		type: 'POST',
		data: formData,
		processData: false,
		contentType: false,
		success:  function(result) {
			
			if(result.rtnCd === 0) {
	        		lrt.client('신분증 인증 자료를 제출하였습니다.', 'idSetup 인증', function() {
	        			location.href='/coinis/info';
	        		});
	        } else {
	        		lrt.client(result.rtnMsg, '신분증 인증');
	        }
		}
		
	})
	
};

//사업자 등록번호 조회
function authCorpNo() {

	var p = {};
	p['corpNo'] = $('input[name=corpNo]').val();
	
	if ( p.corpNo == '') {
		lrt.client('input corporation No');
		$('input[name=corpNo]').focus();
		return false;
	}
	
	ajax('/coinis/idSetup/authCorpNo', p, function(result) {
		console.log(result);
		
		if(result.corp_nm != '-1') {
			// 법인 정보 세팅
			$('#corp_nm').find('label').text( result.corp_nm );
			$('#represent_nm').find('label').text( result.represent_nm );
			$('#corp_addr').find('label').text( result.corp_addr );
			$('#corp_tel_no').find('label').text( result.corp_tel_no );
			
			G__CorpNo_authorized = 1;
        } else
        	lrt.client('등록된 사업자등록번호가 존재하지 않습니다.', '신분증 인증');

	})
}

// 사업자등록번호가 정상 조회되었는지 확인하는 글로벌 변수
var G__CorpNo_authorized = 0;

// 법인 회원 신분증 인증 전송
function uploadCorporateIDCard() {

	var form = $('#crpForm');
	var file1 = form.find('#imgInput2_1');
	var file2 = form.find('#imgInput2_2');
	var file3 = form.find('#imgInput2_3');
	
	var formData = new FormData(form[0]);
	
	if( G__CorpNo_authorized != 1) {
		lrt.client('Corp Number is not authorized');
		form.find('input[name=corpNo]').focus();
		return false;
	}
	else if( file1.val() == '') {
		lrt.client('you should upload ID card photo', 'idSetup');
		return false;
	} else if ( file2.val() == '') {
		lrt.client('you should upload Representative ID card photo', 'idSetup');
		return false;
	} else if ( file3.val() == '') {
		lrt.client('you should upload Business Registration photo', 'idSetup');
		return false;
	} else if ( !checkFileSize(file1) ) {
		lrt.client('size of first file exceededed 4MB', 'idSetup');
		return false;
	} else if ( !checkFileSize(file2) ) {
		lrt.client('size of second file exceeded 4MB', 'idSetup');
		return false;
	} else if ( !checkFileSize(file3) ) {
		lrt.client('size of third file exceeded 4MB', 'idSetup');
		return false;
	} else if ( !checkExtension(file1) ) {
		lrt.client('only jpg/png extension is uploadable', 'idSetup');
		return false;
	} else if ( !checkExtension(file2) ) {
		lrt.client('only jpg/png extension is uploadable', 'idSetup');
		return false;
	} else if ( !checkExtension(file3) ) {
		lrt.client('only jpg/png extension is uploadable', 'idSetup');
		return false;
	}
	
	ajaxOption({
		url: '/coinis/idSetup/submitCorpIdcard',
		type: 'POST',
		data: formData,
		processData: false,
		contentType: false,
		success:  function(result) {
			
			if(result.rtnCd === 0) {
	        		lrt.client('인증 자료를 제출하였습니다.', 'idSetup 인증', function() {
	        			location.href='/coinis/info';
	        		});
	        } else
	        	lrt.client(result.rtnMsg, '신분증 인증');
		}
		
	})
}

// 첨부파일 크기 확인
function checkFileSize(selector) {
	var max = 4*1024*1024;
	if(selector[0].files[0].size[0] > max) {
		return false;
	} else {
		return true;
	}
}

// 첨부파일 확장자 확인
function checkExtension(selector) {
	var fileName = selector[0].files[0].name;
	var index = fileName.lastIndexOf('.');

	if(index != -1) {
		let ext = fileName.substr(index+1);
		
		if(ext === 'jpg' || ext === 'png') {
			return true;
		} else {
			return false;
		}
	}
}



</script>