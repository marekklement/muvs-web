package cz.asmk.muvsweb.util;

import java.time.LocalDate;
import java.time.Month;
import java.time.ZoneId;
import java.util.Date;
import java.util.Objects;

public class DateUtil {

	public static final Date LEAST_FROM_CREATED =
			Date.from(LocalDate.of( 2019 , Month.JANUARY , 1 ).atStartOfDay(ZoneId.systemDefault()).toInstant());
	public static final Date LEAST_TO_CREATED =
			Date.from(LocalDate.of( 2999 , Month.JANUARY , 1 ).atStartOfDay(ZoneId.systemDefault()).toInstant());

	public static boolean checkDate(Date currentDate, Date from, Date to){
		return currentDate.before(to) && currentDate.after(from);
	}

	public static boolean validationOfDate(Date date){
		if(Objects.isNull(date)){
			throw new IllegalArgumentException("Date is null!");
		}
		boolean b = checkDate(date, LEAST_FROM_CREATED, LEAST_TO_CREATED);
		if(!b){
			throw new IllegalArgumentException("Date is wrong!");
		} else return true;
	}


}
