package to.msn.wings.common;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class CheckUtil {
	private ArrayList<String> _errs=null;
	
	public CheckUtil() {
		this._errs = new ArrayList<String>();
	}
	
	public boolean hasErrors(){
		return !this._errs.isEmpty();
	}
	
	public void setError(String msg){
		this._errs.add(msg);
	}
	
	public ArrayList<String> getErrors(){
		return this._errs;
	}
	
	public void requiredCheck(String value,String err) {
		if(value==null || value.equals("")){
			this._errs.add(err + "は必須入力です");
		}
	}
	
	public void lengthCheck(String value,int max,String err){
		if(value!=null && !value.equals("")){
			if(value.length()>max){
				this._errs.add(err + "は" + max + "桁以下で入力してください");
			}
		}
	}
	
	public void numberTypeCheck(String value,String err){
		if(value!=null && !value.equals("")){
			try {
				int ival=Integer.parseInt(value);
			} catch (NumberFormatException e) {
				this._errs.add(err + "は数値で入力してください");
			}
		}
	}
	
	public void dateTypeCheck(String value,String err){
		if(value!=null && !value.equals("")){
			Pattern ptn=Pattern.compile("^[0-9]{4}/[0-9]{1,2}/[0-9]{1,2}$",Pattern.CASE_INSENSITIVE);
			Matcher mch=ptn.matcher(value);
			if(mch.find()){
				StringTokenizer token=new StringTokenizer(value,"/");
				int year =Integer.parseInt((String)token.nextToken());
				int month=Integer.parseInt((String)token.nextToken());
				int day  =Integer.parseInt((String)token.nextToken());
				Calendar cal=Calendar.getInstance();
				cal.set(year,month-1,day);
				if(cal.get(Calendar.YEAR)!=year || cal.get(Calendar.MONTH)!=month-1 || cal.get(Calendar.DATE)!=day){
					this._errs.add(err + "は正しい日付で入力してください");
				}
			}else{
				this._errs.add(err + "は日付形式で入力してください");
			}
		}
	}
	
	public void dateTypeCheck(String year,String month,String day,String err){
		int iyear=0,imonth=0,iday=0;
		try {
			iyear =Integer.parseInt(year);
			imonth=Integer.parseInt(month);
			iday  =Integer.parseInt(day);
		} catch (NumberFormatException e) {
			this._errs.add("年月日は数値で入力してください");
		}
		Calendar cal=Calendar.getInstance();
		cal.set(iyear,imonth-1,iday);
		if(cal.get(Calendar.YEAR)!=iyear || cal.get(Calendar.MONTH)!=imonth-1 || cal.get(Calendar.DATE)!=iday){
			this._errs.add(err + "は正しい日付形式で入力してください");
		}
	}
	public void rangeCheck(String value,int max,int min,String err){
		if(value!=null && !value.equals("")){
			int val=0;
			try {
				val=Integer.parseInt(value);
			} catch (NumberFormatException e) {
				this._errs.add(err + "は数値で入力してください");
			}
			if(val<min && val>max){
				this._errs.add(err + "は" + min + "桁以上、かつ" + max + "桁以下で入力してください");
			}
		}
	}
	
	public void regExCheck(String value,String ptn,String err){
		if(value!=null && !value.equals("")){
			Pattern optn=Pattern.compile(ptn,Pattern.CASE_INSENSITIVE);
			Matcher mch=optn.matcher(value);
			if(!mch.find()){
				this._errs.add(err + "を正しい形式で入力してください");
			}
		}
	}

	public void compareCheck(String value1,String value2,String err1,String err2){
		if(value1!=null && !value1.equals("") && value2!=null && !value2.equals("")){
			if(value1.compareTo(value2)>=0){
				this._errs.add(err1 + "は" + err2 + "より小さい値を指定してください");
			}
		}
	}
	
	public void duplicateCheck(String sql,String err) {
		Connection db=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			Context ctx=new InitialContext();
			DataSource ds=(DataSource)ctx.lookup("java:comp/env/jdbc/Sample");
			db=ds.getConnection();
			ps=db.prepareStatement(sql);
			rs=ps.executeQuery();
			if(rs.next()){this._errs.add(err + "が重複しています");}
		} catch (NamingException e) {
			e.printStackTrace(System.err);
		} catch (SQLException e) {
			e.printStackTrace(System.err);
		} finally {
			try {
				if(rs!=null){rs.close();}
				if(ps!=null){ps.close();}
				if(db!=null){db.close();}
			} catch (SQLException se) {
				se.printStackTrace(System.err);
			}
		}
	}
	
	public void urlCheck(String value){
		if(!value.startsWith( "http://" ) && !value.startsWith( "https://" )){
				this._errs.add(value + "は" + "正しいURLではありません。");
		}
	}
}