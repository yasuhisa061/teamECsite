<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/radish.css">
<link rel="stylesheet" href="./css/table.css">
<title>パスワード再設定確認画面</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<div id="contents">
        <h1>パスワード再設定確認画面</h1>
          <table class="importConfirmTable">

              <tr>
                  <th scope="row"><s:label value="ユーザーID"/></th>
                  <!-- scopeはthの列方向（横）を示し"row"は行方向、   "col"は列方向（下）を示す データ方向がはっきりすることでアクセシブルなものになる -->
                  <td><s:property value="userId"/></td>
              </tr>

              <tr>
                  <th scope="row"><s:label value="新しいパスワード"/></th>
                  <td><s:property value="concealedPassword"/></td>
              </tr>
          </table>

      <s:form action="ResetPasswordCompleteAction">
          <div class="submitBtnBox">
              <s:submit value="パスワード再設定" class="submitBtn"/>
          </div>

      </s:form>

      <s:form action="ResetPasswordAction">
         <div class="submitBtnBox">
             <s:submit value="戻る" class="submitBtn"/>
         </div>
         <s:hidden id="backFlag" name="backFlag" value="1"/>

      </s:form>

</div>

</body>
</html>