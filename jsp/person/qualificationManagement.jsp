<%@ page language="java" %>
<%@ page import="ServidorApresentacao.Action.sop.utils.SessionConstants" %>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<h2><bean:message key="title.teacherInformation"/></h2>
<logic:present name="infoSiteQualifications">
<br/>
<h3><bean:message key="message.teacherInformation.qualifications" /></h3>
<p class="infoop"><span class="emphasis-box">1</span>
<bean:message key="message.qualification.management" /></p>
<bean:message key="message.qualification.managementInsertExplanation" />
<bean:message key="message.qualification.managementCleanExplanation" />
<bean:message key="message.qualification.managementInsertQualExplanation" />
<bean:message key="message.qualification.managementSaveExplanation" />
<table border="1" style="margin-top:10px">	
<logic:iterate id="infoQualification" name="infoSiteQualifications" property="infoQualifications">
<tr>
	<td>
		<bean:write name="infoQualification" property="year" />
	</td>
	<td>
		<bean:write name="infoQualification" property="school" />
	</td>
	<td>
		<bean:write name="infoQualification" property="title" />
	</td>
	<td>
		<bean:write name="infoQualification" property="degree" />
	</td>
	<td>
		<bean:write name="infoQualification" property="mark" />
	</td>
	<td>
		<div class="gen-button">
			<html:link page="/qualificationForm.do?method=prepareEdit&amp;page=0" 
					   paramId="idInternal" 
					   paramName="infoQualification" 
					   paramProperty="idInternal">
				<bean:message key="label.edit" />
			</html:link>
		</div>
	</td>
	<td>
		<div class="gen-button">
			<html:link page="/qualificationForm.do?method=delete&amp;page=0" 
					   paramId="idInternal" 
					   paramName="infoQualification" 
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
	<html:link page="/qualificationForm.do?method=prepareEdit&amp;page=0" 
			   paramId="infoPerson#idInternal" 
			   paramName="infoSiteQualifications" 
			   paramProperty="infoPerson.idInternal" >
		<bean:message key="message.qualification.insert" />
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