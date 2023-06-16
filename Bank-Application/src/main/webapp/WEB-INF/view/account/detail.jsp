<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@include file="../layout/header.jsp" %>

<h1>계좌상세보기</h1>
<hr />
<div class="user-box">
    ${account.fullname}님 계좌<br />
    계좌번호 : ${account.number}<br />
    잔액 : ${account.balance}원
</div>
<div class="list-box">
    <a href="/account/${account.id}">전체</a>
    <a href="/account/${account.id}?gubun=deposit">입금</a>
    <a href="/account/${account.id}?gubun=withdraw">출금</a>
    <br />
    <table border="1">
        <thead>
        <tr>
            <th>날짜</th>
            <th>보낸이</th>
            <th>받은이</th>
            <th>입출금금액</th>
            <th>계좌잔액</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="history" items="${historyList}">
            <tr>
                <td>${history.createdAt}</td>
                <td>${history.sender}</td>
                <td>${history.receiver}</td>
                <td>${history.amount}원</td>
                <td>${history.balance}원</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

</body>

</html>