package org.apache.flume.sink.sequoiadb;

import java.util.ArrayList;

import org.bson.BSONObject;
import org.bson.BasicBSONObject;

import com.sequoiadb.base.CollectionSpace;
import com.sequoiadb.base.DBCollection;
import com.sequoiadb.base.Sequoiadb;
import com.sequoiadb.base.SequoiadbDatasource;
import com.sequoiadb.datasource.ConnectStrategy;
import com.sequoiadb.datasource.DatasourceOptions;
import com.sequoiadb.exception.BaseException;
import com.sequoiadb.net.ConfigOptions;

public class Sample {
	public static void main(String[] args) {
		String connString = "172.20.10.13:11810";
		try {
			
			String user = "";
	        String password = "";
	        ConfigOptions nwOpt = new ConfigOptions();
	        DatasourceOptions dsOpt = new DatasourceOptions();
	        SequoiadbDatasource ds = null;
	        // 提供coord节点地址  
	        ArrayList<String> addrs = new ArrayList<String>();
	        addrs.add("172.20.10.13:11810");
	        
	        // 设置网络参数
	        nwOpt.setConnectTimeout(500);                      // 建连超时时间为500ms。
	        nwOpt.setMaxAutoConnectRetryTime(0);               // 建连失败后重试时间为0ms。
	        
	        // 设置连接池参数
	        dsOpt.setMaxCount(500);                            // 连接池最多能提供500个连接。
	        dsOpt.setDeltaIncCount(20);                        // 每次增加20个连接。
	        dsOpt.setMaxIdleCount(20);                         // 连接池空闲时，保留20个连接。
	        dsOpt.setKeepAliveTimeout(0);                      // 池中空闲连接存活时间。单位:毫秒。
	                                                           // 0表示不关心连接隔多长时间没有收发消息。
	        dsOpt.setCheckInterval(60 * 1000);                 // 每隔60秒将连接池中多于
	                                                           // MaxIdleCount限定的空闲连接关闭，
	                                                           // 并将存活时间过长（连接已停止收发
	                                                           // 超过keepAliveTimeout时间）的连接关闭。
	        dsOpt.setSyncCoordInterval(0);                     // 向catalog同步coord地址的周期。单位:毫秒。
	                                                           // 0表示不同步。
	        dsOpt.setValidateConnection(false);                // 连接出池时，是否检测连接的可用性，默认不检测。
	        dsOpt.setConnectStrategy(ConnectStrategy.BALANCE); // 默认使用coord地址负载均衡的策略获取连接。
	        
	        // 建立连接池
	        ds = new SequoiadbDatasource(addrs, user, password, nwOpt, dsOpt);
	        
			// 建立 SequoiaDB 数据库连接
			Sequoiadb sdb = ds.getConnection();
			CollectionSpace db = sdb.getCollectionSpace("space");
			// 获取所有 Collection 信息，并打印出来
			DBCollection cl = db.getCollection("collection");
//			  // 创建一个插入的 bson 对象
//			  BSONObject obj = new BasicBSONObject();
//			  obj.put("name", "user5");
//			  obj.put("age", 19);
//			  
//			  BSONObject obj1 = new BasicBSONObject();
//			  obj1.put("name", "user6");
//			  obj1.put("age", 21);
//			  
//			  List<BSONObject> array= new ArrayList<BSONObject>();
//			  array.add(obj);
//			  array.add(obj1);
//			  cl.insert(array);
//			  
//			  
//			  DBCursor cursor;
//			  BSONObject queryCondition = new BasicBSONObject();
//			  queryCondition = (BSONObject) JSON.parse("{age:{$ne:20}}");
//			  // 查询所有记录，并把查询结果放在游标对象中
//			  cursor = cl.query(queryCondition, null, null, null);//queryCondition, null, null, null
//			  // 从游标中显示所有记录
//			  while (cursor.hasNext()) {
//			    BSONObject record = cursor.getNext();
//			    String name = (String) record.get("name");
//			    System.out.println("name=" +  name);
//			  }
			  
//			BSONObject matcher = new BasicBSONObject();
//	    	
//	    	matcher.put("_id", "18f949cc-2727-48c6-9485-8ad6bf9e1ce1");
//	    	matcher.put("username", "user_3cQagdnH8T");
//	    	matcher.put("age", 35);
//	    	cl.delete(matcher);
//			
			BSONObject matcher = new BasicBSONObject();
	    	
	    	matcher.put("_id", "4a3d8a54-902b-4206-b0a6-90bd42197218");
	    	matcher.put("username", "user_ycmuCbaRJq");
	    	matcher.put("age", 24);
	    	
	    	BSONObject modify = new BasicBSONObject();
	    	BSONObject data  = new BasicBSONObject();
	    	data.put("age",22);
	    	modify.put("$set",data);
	    	cl.update(matcher,modify,null);
	    	
			
			  ds.releaseConnection(sdb);
//			  array.clear();
			  
		} catch (BaseException e) {
			System.out.println("Sequoiadb driver error, error description:" + e.getErrorType());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
