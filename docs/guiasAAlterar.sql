SELECT COUNT(*) FROM GUIDE G, PERSON P, STUDENT S, STUDENT_CURRICULAR_PLAN SCP, DEGREE_CURRICULAR_PLAN DCP
WHERE G.KEY_PERSON = P.ID_INTERNAL
AND P.ID_INTERNAL = S.KEY_PERSON
AND S.ID_INTERNAL = SCP.KEY_STUDENT
AND DCP.ID_INTERNAL = SCP.KEY_DEGREE_CURRICULAR_PLAN
AND DCP.NAME LIKE '%02/04'
AND G.NUMBER < 500000;

SELECT S.NUMBER, G.NUMBER FROM GUIDE G, PERSON P, STUDENT S, STUDENT_CURRICULAR_PLAN SCP, DEGREE_CURRICULAR_PLAN DCP
WHERE G.KEY_PERSON = P.ID_INTERNAL
AND P.ID_INTERNAL = S.KEY_PERSON
AND S.ID_INTERNAL = SCP.KEY_STUDENT
AND DCP.ID_INTERNAL = SCP.KEY_DEGREE_CURRICULAR_PLAN
AND DCP.NAME LIKE '%02/04'
AND G.NUMBER < 500000
ORDER BY S.NUMBER;