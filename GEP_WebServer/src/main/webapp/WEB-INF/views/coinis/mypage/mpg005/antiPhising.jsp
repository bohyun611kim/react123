<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ include file="/WEB-INF/views/taglib/taglib.jsp" %>

<section>
	<article class="bgwhite">

		<h1 class="sub-title">
			<ul>
				<li>My page</li>
				<li>Anti-Phishing Code</li>
			</ul>
		</h1>
		
		<div class="content-item">
			<table class="tblType01">
				<colgroup>
					<col style="width: 200px">
					<col>
				</colgroup>
				<tbody>
					<tr>
						<th>Anti-Phishing Code</th>
						<td>
							<input type="text" id="anti_phishing_code" name="" value="">
						</td>
						<td class="txt-left">
						Please enter 4-20 non-special characters.
						</td>
					</tr>
				</tbody>
			</table>
			
			 <div class="btn-wrap">
				<button class="big bgblue">Submit</button>
				<p class="mgt20">Once you have successfully set your Anti-Phishing Code, all Coinis emails that you receive will contain this exact code.</p>
			</div>
		</div>

	</article>
</section>

<script>

$('.bgblue').on('click', function() {
	
	var code = $('#anti_phishing_code').val();

	if( code == '') {
		lrt.client('Enter the Anti Phishing Code');
		return false;
	} 
	else if ( code.length < 4 || code.length > 20 ) {
		lrt.client('check length');
		return false;
	}
	else if( !checkSpace(code) ) {
		lrt.client('code cannot contain white space');
		return false;
	}
	else if ( !checkNonSpecial(code) ) {
		lrt.client('code cannot contain special characters');
		return false;
	}
	
	var p = {anti_phishing_code: code};
	
	ajax('/coinis/antiPhising/setCode', p, function(result) {
		console.log(p);
		
		if( result.res_cd == 0 ) {
			lrt.client(result.res_msg, 'success', function() {
				location.href='/coinis/info';
			});
		} else 
			lrt.client(result.res_msg);
		
	});
	
})

// 특수문자 없는지 체크
function checkNonSpecial(string) {
	
	var specialChars = "<>@!#$%^&*()_+[]{}?:;|'\"\\,./~`-="
	
    for(i = 0; i < specialChars.length; i++)
        if(string.indexOf(specialChars[i]) > -1)
            return false;

    return true;
}

// 공백이 없는지 체크 
function checkSpace(str) {
	if(str.indexOf(" ") == -1) {
		return true;
	} else { 
		return false; 
	} 
} 






</script>