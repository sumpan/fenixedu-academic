<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"><%@ taglib uri="/WEB-INF/struts-tiles.tld" prefix="tiles" %><%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html" %><html xmlns="http://www.w3.org/1999/xhtml" xhtml="true" lang="pt" xml:lang="pt"><head><meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" /><meta name="keywords" content="ensino,  ensino superior, universidade, instituto, ci�ncia, instituto superior t�cnico, investiga��o e desenvolvimento" /><meta name="description" content="O Instituto Superior T�cnico � a maior escola de engenharia, ci�ncia e tecnologia em Portugal." /><link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath() %>/CSS/iststyle.css" /><link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath() %>/CSS/dotist_timetables.css" /><link rel="stylesheet" type="text/css" media="screen" href="<%= request.getContextPath() %>/CSS/exam_map.css" /><link rel="stylesheet" type="text/css" media="print" href="<%= request.getContextPath() %>/CSS/gesdis-print.css" /><link rel="stylesheet" type="text/css" media="print" href="<%= request.getContextPath() %>/CSS/dotist_print.css" /><link rel="stylesheet" type="text/css" media="print" href="<%= request.getContextPath() %>/CSS/print.css" /><link rel="stylesheet" type="text/css" href="<%= request.getContextPath() %>/CSS/dotist_timetables.css" /><script type="text/javascript" src="<%= request.getContextPath() %>/CSS/scripts/expmenu.js"></script><title><tiles:getAsString name="title" ignore="true" /></title> <%-- TITLE --%></head><body><!-- BEGIN BROWSER UPGRADE MESSAGE --><div class="browser_upgrade">  <p><strong>Aviso:</strong>  Se est&aacute; a ler esta mensagem, provavelmente, o browser que utiliza n&atilde;o &eacute; compat&iacute;vel  com os &quot;standards&quot; recomendados pela <a href="http://www.w3.org">W3C</a>.  Sugerimos vivamente que actualize o seu browser para ter uma melhor experi&ecirc;ncia  de utiliza&ccedil;&atilde;o deste &quot;website&quot;.  Mais informa&ccedil;&otilde;es em <a href="http://www.webstandards.org/upgrade/">webstandards.org</a>.</p>  <p><strong>Warning:</strong> If you are reading this message, probably, your    browser is not compliant with the standards recommended by the <a href="http://www.w3.org">W3C</a>. We suggest    that you upgrade your browser to enjoy a better user experience of this website.    More informations on <a href="http://www.webstandards.org/upgrade/">webstandards.org</a>.</p></div><!-- END BROWSER UPGRADE MESSAGE --><!-- SYMBOLSROW --><div id="header">	<tiles:insert attribute="symbols_row" ignore="true"/></div><!-- PROFILE NAVIGATION --><div id="perfnav">	<tiles:insert attribute="profile_navigation" ignore="true"/></div><div id="holder"><table id="bigtable" width="100%" border="0" cellpadding="0" cellspacing="0"><tr><td id="latnav_container" width="155px" nowrap="nowrap"><!--MAIN NAVIGATION -->   <div id="latnav">	<tiles:insert attribute="main_navigation" ignore="true"/></div></td><!-- DEGREE SITE --><td width="100%" colspan="3" id="main"><tiles:insert attribute="body" ignore="true"/></td></tr></table></div><!-- FOOTER --> <div id="footer"><tiles:insert attribute="footer" ignore="true"/></div></body></html>