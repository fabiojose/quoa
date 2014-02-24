package net.sourceforge.quoa.bean.visitor;

import net.sourceforge.beanoa.bean.Visitor;
import net.sourceforge.quoa.bean.Period;

public class PeriodMilisecondsVisitor implements Visitor<Period, Long> {

	@Override
	public Long visit(final Period period) {
		long _result = 0;
		switch(period.getType()){
			case SECONDS:
				_result = (long)period.getTime() * 1000;
				break;
				
			case MINUTES:
				_result = (long)period.getTime() * 60 * 1000;
				break;
				
			case HOURS:
				_result = (long)period.getTime() * 60 * 60 * 1000;
				break;
				
			case DAYS:
				_result = (long)period.getTime() * 24 * 60 * 60 * 1000;
				break;
		}
		return _result;
	}

	public static void main(String...args){
		System.out.println(1 * 60 * 60 * 1000);
	}
}
