package com.gwideal.common.util;

import java.net.URL;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;

public class WebServiceClient {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Client client=null;
//		try {
//			URL url = new URL("http://31.1.2.140/ptsjzx/services/ZfwZACorpInfoService?wsdl");
//			client=new Client(url);
//			//上海市普陀区青少年中心/12310107425079607T/425079607
//			Object[] objMessage=client.invoke("getCorpInfoToZFWZA", new Object[]{"e69d550e50414c30ba5c7c45b3b84623","12310107425079607T","Json"});
//			if(null!=objMessage && 0!=objMessage.length){
//				System.out.println(objMessage[0].toString());
//			}
//		}catch (Exception e){
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally{
//			client.close();
//		}
//		
//		try {
//			URL url = new URL("http://31.1.2.140/ptsjzx/services/ZfwZAPersonInfoService?wsdl");
//			client=new Client(url);
//			//王晓婕/652901197212116727；王渊/310105197508090011
//			Object[] objMessage=client.invoke("getPersonInfoToZFWZA", new Object[]{"66aa910c4835470ca9e95dc3dc49e2ea","652901197212116727","Json"});
//			if(null!=objMessage && 0!=objMessage.length){
//				System.out.println(objMessage[0].toString());
//			}
//		}catch (Exception e){
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}finally{
//			client.close();
//		}
		
		Service companyService=new Service();
		try {
			URL url = new URL("http://31.1.2.140/ptsjzx/services/ZfwZACorpInfoService?wsdl");
			Call call=(Call)companyService.createCall();
			call.setOperationName("getCorpInfoToZFWZA");
			call.setTargetEndpointAddress(url);
			String result=(String)call.invoke(new Object[]{"e69d550e50414c30ba5c7c45b3b84623","12310107425079607T","Json"});
			System.out.println(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long startTime=System.currentTimeMillis();
		Service personService=new Service();
		try {
			URL url = new URL("http://31.1.2.140/ptsjzx/services/ZfwZAPersonInfoService?wsdl");
			Call call=(Call)personService.createCall();
			call.setOperationName("getPersonInfoToZFWZA");
			call.setTargetEndpointAddress(url);
			String result=(String)call.invoke(new Object[]{"66aa910c4835470ca9e95dc3dc49e2ea","310101195308101260","Json"});
			System.out.println(result);
			//PopuInfoJson popuInfo=(PopuInfoJson)JSONObject.toBean(JSONObject.fromObject(JSONObject.fromObject(result).getString("data")), PopuInfoJson.class);
			//System.out.println(popuInfo);
			//List<PopuInfoJson> list=(List<PopuInfoJson>)JSONArray.toList(JSONArray.fromObject(JSONObject.fromObject(result).getString("data")), PopuInfoJson.class);
			//System.out.println(list.size());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		long endTime=System.currentTimeMillis();
		System.out.println("getPersonInfoToZFWZA cost="+(endTime-startTime));
	}

}
