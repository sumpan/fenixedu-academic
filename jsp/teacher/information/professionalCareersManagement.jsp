<%@ page language="java" %>
<%@ page import="ServidorApresentacao.Action.sop.utils.SessionConstants" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<h2><bean:message key="title.teacherInformation"/></h2>
<logic:present name="siteView"> 
<bean:define id="infoSiteCareers" name="siteView" property="component"/>
<br/>
<h3><bean:message key="message.professionalCareer" /></h3>
<p class="infoop"><span class="emphasis-box">1</span>
<bean:message key="message.professionalCareer.management" /></p>
<bean:message key="message.professionalCareer.managementInsertExplanation" />
<bean:message key="message.professionalCareer.managementCleanExplanation" />
<bean:message key="message.professionalCareer.managementInsertCareerExplanation" />
<bean:message key="message.professionalCareer.managementSaveExplanation" />
<table border="1" style="margin-top:10px">
<logic:iterate id="infoProfessionalCareer" name="infoSiteCareers" property="infoCareers">
<tr>
	<td>
		<bean:write name="infoProfessionalCareer" property="beginYear" />-
		<bean:write name="infoProfessionalCareer" property="endYear" />
	</td>
	<td>
		<bean:write name="infoProfessionalCareer" property="entity" />
	</td>
	<td>
		<bean:write name="infoProfessionalCareer" property="function" />
	</td>
	<td>
		<div class="gen-button">
			<html:link page="/professionalCareer.do?method=prepareEdit&amp;page=0" 
					   paramId="idInternal" 
					   paramName="infoProfessionalCareer" 
					   paramProperty="idInternal">
				<bean:message key="label.edit" />
			</html:link>
		</div>
	</td>
	<td>
		<div class="gen-button">
			<html:link page="/professionalCareer.do?method=delete&amp;page=0" 
					   paramId="idInternal" 
					   paramName="infoProfessionalCareer" 
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
	<html:link page="/professionalCareer.do?method=prepareEdit&amp;page=0&amp;careerType=Professional" 
			   paramId="infoTeacher#idInternal" 
			   paramName="infoSiteCareers" 
			   paramProperty="infoTeacher.idInternal" >
		<bean:message key="message.professionalCareer.insert" />
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
