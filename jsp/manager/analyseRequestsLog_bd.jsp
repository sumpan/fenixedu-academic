<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %><%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %><%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic" %><%@ taglib uri="/WEB-INF/taglibs-datetime.tld" prefix="dt" %><h2><bean:message key="manager.monitor.requests.title"/></h2><br /><logic:present name="profiles">	<bean:define id="logDirName" name="monitorRequestLogsPathForm" property="logDirName"/>	<bean:define id="logFileName" name="logFileName"/>	<bean:define id="logPath"><%= logDirName %>/<%= logFileName %></bean:define>	<%= logPath %>	<!--<img alt="" src="<%= logPath %>" width="100" height="100"/>-->	<table>		<logic:iterate indexId="i" id="profile" name="profiles">			<tr>				<td>					<bean:write name="profile" property="requestPath"/>				</td>				<td>					<bean:write name="profile" property="averageExecutionTime"/>				</td>				<td>					<bean:write name="profile" property="executionTime"/>				</td>				<td>					<bean:write name="profile" property="numberCalls"/>				</td>			</tr>		</logic:iterate>	</table></logic:present>