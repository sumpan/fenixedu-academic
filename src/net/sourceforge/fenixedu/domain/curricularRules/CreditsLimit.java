package net.sourceforge.fenixedu.domain.curricularRules;

import java.util.ArrayList;
import java.util.List;

import net.sourceforge.fenixedu.dataTransferObject.GenericPair;
import net.sourceforge.fenixedu.domain.DomainObject;
import net.sourceforge.fenixedu.domain.ExecutionPeriod;
import net.sourceforge.fenixedu.domain.degreeStructure.CourseGroup;
import net.sourceforge.fenixedu.domain.exceptions.DomainException;

public class CreditsLimit extends CreditsLimit_Base {

    /**
     * This constructor should be used in context of Composite Rule
     */
    protected CreditsLimit(Double minimum, Double maximum) {
        super();
        if (minimum == null || maximum == null) {
            throw new DomainException("curricular.rule.invalid.parameters");
        }
        if (minimum.doubleValue() > maximum.doubleValue()) {
            throw new DomainException("error.minimum.greater.than.maximum");
        }
        setMinimum(minimum);
        setMaximum(maximum);
        setCurricularRuleType(CurricularRuleType.CREDITS_LIMIT);
    }

    public CreditsLimit(CourseGroup degreeModuleToApplyRule, CourseGroup contextCourseGroup,
            ExecutionPeriod begin, ExecutionPeriod end, Double minimum, Double maximum) {

        this(minimum, maximum);

        if (degreeModuleToApplyRule == null || begin == null) {
            throw new DomainException("curricular.rule.invalid.parameters");
        }

        setDegreeModuleToApplyRule(degreeModuleToApplyRule);
        setContextCourseGroup(contextCourseGroup);
        setBegin(begin);
        setEnd(end);
    }
    
    public void edit(CourseGroup contextCourseGroup, Double minimumCredits, Double maximumCredits) {
        setContextCourseGroup(contextCourseGroup);
        setMinimum(minimumCredits);
        setMaximum(maximumCredits);
    }
    
    @Override
    public List<GenericPair<Object, Boolean>> getLabel() {
        List<GenericPair<Object, Boolean>> labelList = new ArrayList<GenericPair<Object, Boolean>>();

        labelList.add(new GenericPair<Object, Boolean>("label.creditsForApprovation", true));
        labelList.add(new GenericPair<Object, Boolean>(": ", false));
        labelList.add(new GenericPair<Object, Boolean>(getMinimum(), false));
        labelList.add(new GenericPair<Object, Boolean>(" ", false));
        labelList.add(new GenericPair<Object, Boolean>("label.to", true));
        labelList.add(new GenericPair<Object, Boolean>(" ", false));
        labelList.add(new GenericPair<Object, Boolean>(getMaximum(), false));

        if (getContextCourseGroup() != null) {
            labelList.add(new GenericPair<Object, Boolean>(", ", false));
            labelList.add(new GenericPair<Object, Boolean>("label.inGroup", true));
            labelList.add(new GenericPair<Object, Boolean>(" ", false));            
            labelList.add(new GenericPair<Object, Boolean>(getContextCourseGroup().getName(), false));
        }

        return labelList;

    }

    @Override
    public boolean evaluate(Class<? extends DomainObject> object) {
        // TODO Auto-generated method stub
        return false;
    }

}
