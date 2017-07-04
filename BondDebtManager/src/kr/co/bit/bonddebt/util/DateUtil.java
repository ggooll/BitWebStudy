package kr.co.bit.bonddebt.util;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtil {

	public static Date getDate(String dateStr) {

		// 2017-06-26 21:48:43.0
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date sqlDate = null;

		try {
			java.util.Date date = sdf.parse(dateStr);
			sqlDate = new Date(date.getTime());

		} catch (ParseException e) {
			e.printStackTrace();
		}

		return sqlDate;
	}

}
