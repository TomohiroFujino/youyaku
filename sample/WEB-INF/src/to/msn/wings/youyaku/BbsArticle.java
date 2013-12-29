package to.msn.wings.youyaku;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import to.msn.wings.common.WingsUtil;

public class BbsArticle implements Serializable {
	/**
	 * 
	 */;
	//201301014 update Fujino  start 
	public String mid=null;
	public String pid=null;
	public String title=null;	
	public String nam=null;
	public Timestamp sdat=null;
	public Timestamp pdat=null;
	public Timestamp searchdat=null;
	public String marticle=null;
	public String particle=null;
	public String searcharticle=null;
	public String passwd=null;
	public String source=null;
	public String url=null;
	//String型の理由→文字数欄に文字列が入力される可能性があるため。
	public String mcharalen=null;
	public String pcharalen=null;
	public int pointbiutiful=0;
	public int idbiutiful=0;
	public int deleted=0;
	public int parent=0;
	public int mcount=0;
	public String getMid(){return this.mid;}
	public String getPid(){return this.pid;}
	public String getPassword(){return this.passwd;}
	public String getTitle(){return this.title;}
	public String getNam(){return this.nam;}
	public Timestamp getSdat(){return this.sdat;}
	public Timestamp getPdat(){return this.pdat;}
	public Timestamp getSearchdat(){return this.searchdat;}
	public String getMarticle(){return this.marticle;}
	public String getParticle(){return this.particle;}
	public String getSearcharticle(){return this.searcharticle;}
	public String getMcharalen(){return this.mcharalen;}
	public String getPcharalen(){return this.pcharalen;}
	public int getPointbiutiful(){return this.pointbiutiful;}
	public int getIdbiutiful(){return this.idbiutiful;}
	//20130914 update Fujino  end
	public int getDeleted(){return this.deleted;}
	public int getParent(){return this.parent;}
	public int getMcount(){return this.mcount;}
	public String getPasswd(){return this.passwd;}
	public String getSource(){return this.source;}
	public String getUrl(){return this.url;}
	public void setMid(String mid){this.mid=mid;}
	public void setPid(String pid){this.pid=pid;}
	public void setTitle(String title){this.title=title;}
	public void setNam(String nam){this.nam=nam;}
	public void setSdat(Timestamp sdat){this.sdat=sdat;}
	public void setPdat(Timestamp pdat){this.pdat=pdat;}
	public void setPointbiutiful(int pointbiutiful){this.pointbiutiful=pointbiutiful;}
	public void setIdbiutiful(int idbiutiful){this.idbiutiful=idbiutiful;}
	public void setMarticle(String marticle){this.marticle=marticle;}
	public void setParticle(String particle){this.particle=particle;}
	public void setSearcharticle(String searcharticle){this.searcharticle=searcharticle;}
	public void setMcharalen(String mcharalen){this.mcharalen=mcharalen;}
	public void setPcharalen(String pcharalen){this.pcharalen=pcharalen;}
	public void setDeleted(int deleted){this.deleted=deleted;}
	public void setParent(int parent){this.parent=parent;}
	public void setMcount(int mcount){this.mcount=mcount;}
	public void setPasswd(String passwd){this.passwd=passwd;}
	public void setSource(String source){this.source=source;}
	public void setUrl(String url){this.url=url;}
	public void setSearchdat(Timestamp searchdat){this.searchdat=searchdat;}
	//20130914 update Fujino  end 
	public BbsArticle() { /* コンストラクタ */ }
	public void setArticleInfo(){
		Connection db=null;
		PreparedStatement ps=null;
		try {
			Context ctx=new InitialContext();
			DataSource ds=(DataSource)ctx.lookup("java:comp/env/jdbc/Sample");
			db=ds.getConnection();
			ps=db.prepareStatement("INSERT INTO bbs_parent(title,p_article,p_len_chara,pdat,source,url) "
					+ "VALUES(?,?,?,?,?,?)");
			ps.setString(1,this.title);
			ps.setString(2,this.particle);
			ps.setString(3,this.pcharalen);
			ps.setTimestamp(4,this.pdat);
			ps.setString(5,this.source);
			ps.setString(6,this.url);
			//20130914 update Fujino  end
			ps.executeUpdate();
		} catch (NamingException e) {
			e.printStackTrace(System.err);
		} catch (SQLException e) {
			e.printStackTrace(System.err);
		} finally {
			try {
				if(ps!=null){ps.close();}
				if(db!=null){db.close();}
			} catch (SQLException se) {
				se.printStackTrace(System.err);
			}
		}
	}
	//20130923 insert Fujino  start
	public void setArticleInfoRes(int pid){
		Connection db=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			Context ctx=new InitialContext();
			DataSource ds=(DataSource)ctx.lookup("java:comp/env/jdbc/Sample");
			db=ds.getConnection();
			ps=db.prepareStatement("SELECT m_count FROM bbs_parent WHERE p_id=?");
			ps.setInt(1,pid);
			rs = ps.executeQuery();
			if(rs.next()){
				int mcount=rs.getInt("m_count")+1;
				ps=db.prepareStatement("INSERT INTO bbs_master(nam,sdat,m_article,parent,m_len_chara,passwd)"
						+ " VALUES(?,?,?,?,?,?)");
				ps.setString(1,this.nam);
				ps.setTimestamp(2,this.sdat);
				ps.setString(3,this.marticle);
				ps.setInt(4,this.parent);
				ps.setString(5,this.mcharalen);
				ps.setString(6,this.passwd);
				ps.executeUpdate();
				ps=null;
				ps=db.prepareStatement("UPDATE bbs_parent SET m_count=? where p_id=?");
				ps.setInt(1,mcount);
				ps.setInt(2,pid);
				ps.executeUpdate();
			}
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
	//2013/9/28
	public static void setArticleInfoResButton(String mid){
		Connection db=null;
		PreparedStatement ps=null;
		try {
			Context ctx=new InitialContext();
			DataSource ds=(DataSource)ctx.lookup("java:comp/env/jdbc/Sample");
			db=ds.getConnection();
			ResultSet rs=null;
	    	ps=db.prepareStatement("SELECT * FROM bbs_master WHERE m_id=?");
			ps.setString(1,mid);
			rs=ps.executeQuery();
			if(rs.next()){
				String point=rs.getString("point_biutiful");
				ps=db.prepareStatement("UPDATE bbs_master SET point_biutiful=? WHERE m_id=?");
				ps.setInt(1,Integer.parseInt(point) + 1);
				ps.setString(2,mid);
				ps.executeUpdate();
			}
		} catch (NamingException e) {
			e.printStackTrace(System.err);
		} catch (SQLException e) {
			e.printStackTrace(System.err);
		} finally {
			try {
				if(ps!=null){ps.close();}
				if(db!=null){db.close();}
			} catch (SQLException se) {
				se.printStackTrace(System.err);
			}
		}
	}

	//引数に対応したIDの要約元・要約文章データを取得
	//flg0・・・要約元文章ID  flg1・・・要約文章ID
	public void getArticleById(String id,int flg){
		Connection db=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			Context ctx=new InitialContext();
			DataSource ds=(DataSource)ctx.lookup("java:comp/env/jdbc/Sample");
			db=ds.getConnection();
			
			if(flg==0){
				//要約元文章テーブルから、抽出
			    ps=db.prepareStatement("SELECT * FROM bbs_master WHERE parent=? ORDER BY point_biutiful DESC");
			}else if(flg==1){
				//要約文章テーブルから、抽出
				ps=db.prepareStatement("SELECT * FROM bbs_master WHERE m_id=?");
			}else{}
			    ps.setString(1,id);
			    rs=ps.executeQuery();
			    //Boolean isExists=rs.next();
			    //要約投稿ありの要約文章・要約元欄に、表示する値を取得
			    if(rs.next()){
			    	if(flg==0){
			    		//要約元文章テーブルから、抽出
			    		ps=db.prepareStatement("SELECT * FROM v_bbs_display WHERE p_id=?");
			    	}else if(flg==1){
			    		//要約文章テーブルから、抽出
			    		ps=db.prepareStatement("SELECT * FROM v_bbs_display WHERE m_id=?");
			    	}else{}
					ps.setString(1,id);
					rs=ps.executeQuery();
					if(rs.next()){
						this.setMid(rs.getString("m_id"));
						this.setTitle(rs.getString("title"));
						this.setNam(rs.getString("nam"));
						this.setSdat(rs.getTimestamp("sdat"));
						this.setPid(id);
						this.setPointbiutiful(rs.getInt("point_biutiful"));
						this.setMcharalen(rs.getString("m_len_chara"));
						this.setPcharalen(rs.getString("p_len_chara"));
						this.setPdat(rs.getTimestamp("pdat"));
						String strM=WingsUtil.htmlEncode(rs.getString("m_article"));
						strM=strM.replaceAll(System.getProperty("line.separator"),"<br />");				
						Pattern ptnM=Pattern.compile("(http://|https://){1}[\\w\\.\\-/:]+",Pattern.CASE_INSENSITIVE);
						Matcher mchM=ptnM.matcher(strM);
						strM=mchM.replaceAll("<a href='$0'>$0</a>");
						this.setMarticle(strM);
						String strP=WingsUtil.htmlEncode(rs.getString("p_article"));
						strP=strP.replaceAll(System.getProperty("line.separator"),"<br />");				
						Pattern ptnP=Pattern.compile("(http://|https://){1}[\\w\\.\\-/:]+",Pattern.CASE_INSENSITIVE);
						Matcher mchP=ptnP.matcher(strP);
						strP=mchP.replaceAll("<a href='$0'>$0</a>");
						this.setParticle(strP);
						this.setMcount(rs.getInt("m_count"));
						this.setSource(rs.getString("source"));
						this.setUrl(rs.getString("url"));
						this.setPointbiutiful(rs.getInt("point_biutiful"));
					}
				//要約投稿なしの要約文章・要約元欄に、表示する値を取得
				}else{
				    	ps=db.prepareStatement("SELECT * FROM bbs_parent WHERE p_id=?");
						ps.setString(1,id);
						rs=ps.executeQuery();
						if(rs.next()){
							this.setMid("未投稿");
							this.setTitle(rs.getString("title"));
							this.setNam("未投稿");
							this.setSdat(null);
							this.setPid(id);
							this.setPointbiutiful (0);
							this.setIdbiutiful (0);
							this.setMcharalen("0");
							this.setPcharalen(rs.getString("p_len_chara"));
							this.setPdat(rs.getTimestamp("pdat"));
							this.setMarticle(" ");
							String strP=WingsUtil.htmlEncode(rs.getString("p_article"));
							strP=strP.replaceAll(System.getProperty("line.separator"),"<br />");				
							Pattern ptnP=Pattern.compile("(http://|https://){1}[\\w\\.\\-/:]+",Pattern.CASE_INSENSITIVE);
							Matcher mchP=ptnP.matcher(strP);
							strP=mchP.replaceAll("<a href='$0'>$0</a>");
							this.setParticle(strP);
							this.setSource(rs.getString("source"));
							this.setUrl(rs.getString("url"));
						}
				}
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
	//2013/09/28 INSERT FUJINO start
	//引数<要約元文章ID>に対応した、要約元・要約文章データをすべて取得
    public static ArrayList<BbsArticle> getArticlesByIDs(String pid){
		ArrayList<BbsArticle> list=new ArrayList<BbsArticle>();
		Connection db=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			Context ctx=new InitialContext();
			DataSource ds=(DataSource)ctx.lookup("java:comp/env/jdbc/Sample");
			db=ds.getConnection();
			ps=db.prepareStatement("SELECT * FROM bbs_master WHERE parent=?");
			ps.setString(1,pid);
			rs=ps.executeQuery();
			Boolean isExists=rs.next();
			    if(isExists){
			    	ps=db.prepareStatement("SELECT * FROM v_bbs_display WHERE p_id=?  ORDER BY point_biutiful DESC");
					ps.setString(1,pid);
					rs=ps.executeQuery();
					while(rs.next()){
						BbsArticle art=new BbsArticle();
						art.setMid(rs.getString("m_id"));
						art.setTitle(rs.getString("title"));
						art.setNam(rs.getString("nam"));
						art.setSdat(rs.getTimestamp("sdat"));
						art.setPid(pid);
						art.setMcharalen(rs.getString("m_len_chara"));
						art.setPcharalen(rs.getString("p_len_chara"));
						art.setPdat(rs.getTimestamp("pdat"));
						art.setPointbiutiful(rs.getInt("point_biutiful"));
						String strM=WingsUtil.htmlEncode(rs.getString("m_article"));
						strM=strM.replaceAll(System.getProperty("line.separator"),"<br />");				
						Pattern ptnM=Pattern.compile("(http://|https://){1}[\\w\\.\\-/:]+",Pattern.CASE_INSENSITIVE);
						Matcher mchM=ptnM.matcher(strM);
						strM=mchM.replaceAll("<a href='$0'>$0</a>");
						art.setMarticle(strM);
					
						String strP=WingsUtil.htmlEncode(rs.getString("p_article"));
						strP=strP.replaceAll(System.getProperty("line.separator"),"<br />");				
						Pattern ptnP=Pattern.compile("(http://|https://){1}[\\w\\.\\-/:]+",Pattern.CASE_INSENSITIVE);
						Matcher mchP=ptnP.matcher(strP);
						strP=mchP.replaceAll("<a href='$0'>$0</a>");
						art.setParticle(strP);
						art.setMcount(rs.getInt("m_count"));
						art.setSource(rs.getString("source"));
						art.setUrl(rs.getString("url"));
						list.add(art);
					}
				}else{
				    	ps=db.prepareStatement("SELECT * FROM bbs_parent WHERE p_id=?");
						ps.setString(1,pid);
						rs=ps.executeQuery();
						if(rs.next()){
							BbsArticle art=new BbsArticle();
							art.setMid("未投稿");
							art.setTitle(rs.getString("title"));
							art.setNam("未投稿");
							art.setSdat(null);
							art.setPid(pid);
							art.setPointbiutiful (0);
							art.setIdbiutiful (0);
							art.setMcharalen("0");
							art.setPcharalen(rs.getString("p_len_chara"));
							art.setPdat(rs.getTimestamp("pdat"));
							art.setMarticle(" ");
						
							String strP=WingsUtil.htmlEncode(rs.getString("p_article"));
							strP=strP.replaceAll(System.getProperty("line.separator"),"<br />");				
							Pattern ptnP=Pattern.compile("(http://|https://){1}[\\w\\.\\-/:]+",Pattern.CASE_INSENSITIVE);
							Matcher mchP=ptnP.matcher(strP);
							strP=mchP.replaceAll("<a href='$0'>$0</a>");
							art.setParticle(strP);
							art.setSource(rs.getString("source"));
							art.setUrl(rs.getString("url"));
							list.add(art);
						}
					}
				
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
		    return list;
	}
    //end
    //update 2013/10/6
	public static ArrayList<BbsArticle> getArticlesByKeywordP(String keywd){
		ArrayList<BbsArticle> list=new ArrayList<BbsArticle>();
		Connection db=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			Context ctx=new InitialContext();
			DataSource ds=(DataSource)ctx.lookup("java:comp/env/jdbc/Sample");
			db=ds.getConnection();
			ps=db.prepareStatement(
					"SELECT * FROM bbs_parent WHERE (title Like BINARY ? OR p_article Like BINARY ? OR source Like BINARY ?) "
							+ "ORDER BY pdat DESC");
			ps.setString(1,"%" + keywd + "%");
			ps.setString(2,"%" + keywd + "%");
			ps.setString(3,"%" + keywd + "%");
			rs=ps.executeQuery();
			int count=0;
			while(rs.next()){
				BbsArticle art=new BbsArticle();
				art.setPid(rs.getString("p_id"));
				art.setTitle(rs.getString("title"));
				art.setSearchdat(rs.getTimestamp("pdat"));
				String body=WingsUtil.htmlEncode(rs.getString("p_article"));
				body=body.replaceAll(System.getProperty("line.separator"),"<br />");				
				Pattern ptn=Pattern.compile("(http://|https://){1}[\\w\\.\\-/:]+",Pattern.CASE_INSENSITIVE);
				Matcher mch=ptn.matcher(body);
				body=mch.replaceAll("<a href='$0'>$0</a>");
				body=StrReplace.grantTag(body, keywd, "span", "search");
				art.setSearcharticle(body);
				art.setSource(StrReplace.grantTag(rs.getString("source"),keywd,"span","search"));
				
				list.add(art);
				count++;
			}  
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
		return list;
	}
	public static ArrayList<BbsArticle> getArticlesByKeywordM(String keywd){
		ArrayList<BbsArticle> list=new ArrayList<BbsArticle>();
		Connection db=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		try {
			Context ctx=new InitialContext();
			DataSource ds=(DataSource)ctx.lookup("java:comp/env/jdbc/Sample");
			db=ds.getConnection();
			ps=db.prepareStatement(
					"SELECT * FROM v_bbs_display WHERE (title Like BINARY ? OR m_article Like BINARY ? OR nam Like BINARY ?) "
							+ "ORDER BY pdat DESC");
			ps.setString(1,"%" + keywd + "%");
			ps.setString(2,"%" + keywd + "%");
			ps.setString(3,"%" + keywd + "%");
			rs=ps.executeQuery();
			while(rs.next()){
				BbsArticle art=new BbsArticle();
				art.setPid(rs.getString("p_id"));	//親記事表示の為
				art.setTitle(StrReplace.grantTag(rs.getString("title"),keywd,"span","search"));
				art.setSearchdat(rs.getTimestamp("sdat"));
				String body=WingsUtil.htmlEncode(rs.getString("m_article"));
				body=body.replaceAll(System.getProperty("line.separator"),"<br />");				
				Pattern ptn=Pattern.compile("(http://|https://){1}[\\w\\.\\-/:]+",Pattern.CASE_INSENSITIVE);
				Matcher mch=ptn.matcher(body);
				body=mch.replaceAll("<a href='$0'>$0</a>");
				body=StrReplace.grantTag(body, keywd, "span", "search");
				art.setSearcharticle(body);
				art.setNam(StrReplace.grantTag(rs.getString("nam"),keywd,"span","search"));
				art.setSource(StrReplace.grantTag(rs.getString("source"),keywd,"span","search"));
				list.add(art);
			}  
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
		return list;
	}

public boolean removeArticle(){
	boolean flag=true;
	Connection db=null;
	PreparedStatement ps=null;
	ResultSet rs=null;
	try {
		Context ctx=new InitialContext();
		DataSource ds=(DataSource)ctx.lookup("java:comp/env/jdbc/Sample");
		db=ds.getConnection();
		ps=db.prepareStatement("SELECT * FROM bbs_master WHERE m_id=? AND passwd=?");
		ps.setString(1,this.mid);
		ps.setString(2,this.passwd);
		rs=ps.executeQuery();
		if(rs.next()){
			//ps=db.prepareStatement("UPDATE bbs_master SET deleted=1 WHERE m_id=?");
			PreparedStatement preparedStatement = ps=db.prepareStatement("SELECT m_count FROM v_bbs_display WHERE m_id=?");
			ps.setString(1,mid);
			rs = ps.executeQuery();
			rs.next();
			int count=rs.getInt("m_count");
			ps=db.prepareStatement("UPDATE v_bbs_display SET m_count=? where m_id=?");
			ps.setInt(1,count-1);
			ps.setString(2,mid);
			ps.executeUpdate();
			ps=db.prepareStatement("DELETE from bbs_master  WHERE m_id=?");
			ps.setString(1,this.mid);
			ps.executeUpdate();
		}else{
			flag=false;
		}
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
	return flag;
}
}
