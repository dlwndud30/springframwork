<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="card m-2">
	<div class="card-header">
		JSP 템플릿 엔진 이해(~.jsp -> ~.java -> ~.class -> 이 안의 내용으로 화면을 보여줌)
	</div>
	<div class="card-body">
		오늘날짜: <%= request.getAttribute("strDate")%> <br>
		오늘날짜: ${strDate} <%-- Expression Language(EL) --%>
	</div>
</div>

<div class="card m-2">
	<div class="card-header">
		객체 저장 범위
	</div>
	<div class="card-body">
		<p>request 범위 객체 값: <%=request.getAttribute("requestScopeValue") %></p>
		<p>session 범위 객체 값: <%=session.getAttribute("sessionScopeValue") %></p>
		<p>application 범위 객체 값: <%=application.getAttribute("applicationScopeValue") %></p>
		<hr>
		
		<%-- 키를 request범위에서 찾고 없으면 session 범위에서 찾고 없으면 application 범위에서 찾는다 --%>
		<p>
			request 범위 객체 값: ${requestScopeValue}<br>
			member's name: ${member.name} <br>
			member's age: ${member.age} <br>
			member's job: ${member.job} <br>
			member's city: ${member.city.name} <br>
			
		</p>
		<hr>
		
		<p>
			session 범위 객체 값: ${sessionScopeValue}<br>
			member1's name: ${member2.name} <br>
			member1's age: ${member2.age} <br>
			member1's job: ${member2.job} <br>
			member1's city: ${member2.city.name} <br>		
		</p>
		<hr>
		
		<p>
			application 범위 객체 값: ${applicationScopeValue}	<br>
			방문 카운팅: ${counter} 
		</p>
		<hr>
		
		<a href="requestScopeSave" class="btn btn-dark btn-sm">request 범위에 객체 저장</a>
		<a href="sessionScopeSave" class="btn btn-dark btn-sm">session 범위에 객체 저장</a>
		<a href="applicationScopeSave" class="btn btn-dark btn-sm">application 범위에 객체 저장</a>
		
	</div>
</div>

<div class="card m-2">
	<div class="card-header">
		JSTL(Java Standard Tag Library)
	</div>
	<div class="card-body">
		<a href="useJstl1" class="btn btn-dark btn-sm">JSTL 사용하기</a>
		<a href="useJstl2" class="btn btn-dark btn-sm">JSTL 사용하기</a>
	</div>
</div>

<div class="card m-2">
	<div class="card-header">
		ModelAndView로 객체 전달
	</div>
	<div class="card-body">
		<a href="modelAndViewReturn" class="btn btn-dark btn-sm">ModelAndView 리턴해서 객체 전달</a>
	</div>
</div>

<div class="card m-2">
	<div class="card-header">
		Model 매개변수로 객체 전달
	</div>
	<div class="card-body">
		<a href="modelArgument" class="btn btn-dark btn-sm">Model 매개변수로 객체 전달</a>
	</div>
</div>

<div class="card m-2">
	<div class="card-header">
		@ModelAttribute로 객체 전달
	</div>
	<div class="card-body">
		<a href="modelAttribute?kind=suit&sex=woman" class="btn btn-dark btn-sm">@ModelAttribute로 객체 전달</a>
	</div>
</div>

<div class="card m-2">
	<div class="card-header">
		Command(dto)로 객체 전달
	</div>
	<div class="card-body">
		<a href="commandObject?kind=suit&sex=woman" class="btn btn-dark btn-sm">Command로 객체 전달</a>
	</div>
</div>

<%@ include file="/WEB-INF/views/common/footer.jsp" %>
