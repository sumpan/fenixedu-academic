<%@ page language="java" %>
<%@ page import="ServidorApresentacao.Action.sop.utils.SessionConstants" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<h2><bean:message key="title.teacherInformation"/></h2>
<logic:present name="siteView"> 
<bean:define id="infoSiteCareers" name="siteView" property="component"/>
<br/>
<h3><bean:message key="message.teachingCareer" /></h3>
<p class="infoop"><span class="emphasis-box">1</span>
<bean:message key="message.teachingCareer.management" /></p>
<bean:message key="message.teachingCareer.managementInsertExplanation" />
<bean:message key="message.teachingCareer.managementCleanExplanation" />
<bean:message key="message.teachingCareer.managementInsertCareerExplanation" />
<bean:message key="message.teachingCareer.managementSaveExplanation" />
<table border="1" style="margin-top:10px">	
<logic:iterate id="infoTeachingCareer" name="infoSiteCareers" property="infoCareers">
<tr>
	<td>
		<bean:write name="infoTeachingCareer" property="beginYear" />-
		<bean:write name="infoTeachingCareer" property="endYear" />
	</td>
	<td>
		<bean:write name="infoTeachingCareer" property="infoCategory.shortName" />
	</td>
	<td>
		<bean:write name="infoTeachingCareer" property="courseOrPosition" />
	</td>
		<td>
		<div class="gen-button">
			<html:link page="/teachingCareer.do?method=prepareEdit&amp;page=0" 
					   paramId="idInternal" 
					   paramName="infoTeachingCareer" 
					   paramProperty="idInternal">
				<bean:message key="label.edit" />
			</html:link>
		</div>
	</td>
	<td>
		<div class="gen-button">
			<html:link page="/teachingCareer.do?method=delete&amp;page=0" 
					   paramId="idInternal" 
					   paramName="infoTeachingCareer" 
					   paramProperty="idInternal">
				<bean:message key="label.delete" />
			</html:link>
		</div>
	</td>
</tr>
</logic:iterate>
</table>
<br />
<div class="gen-button">
	<html:link page="/teachingCareer.do?method=prepareEdit&amp;page=0&amp;careerType=Teaching" 
			   paramId="infoTeacher#idInternal" 
			   paramName="infoSiteCareers" 
			   paramProperty="infoTeacher.idInternal" >
		<bean:message key="message.teachingCareer.insert" />
	</html:link>
</div>
<br />
<h3>
<table>
<tr align="center">	
	<td>
	<html:form action="/voidAction">
		<html:submit styleClass="inputbutton" property="confirm">
			<bean:message key="button.continue"/>
		</html:submit>
	</html:form>
	</td>
</tr>
</table>
</h3>
</logic:present>
