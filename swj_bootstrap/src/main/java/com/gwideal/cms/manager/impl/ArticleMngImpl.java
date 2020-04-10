package com.gwideal.cms.manager.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.gwideal.common.hibernate.BaseManagerImpl;
import com.gwideal.common.hibernate.Finder;
import com.gwideal.common.page.Pagination;
import com.gwideal.common.util.StringUtil;
import com.gwideal.core.manager.DepartMng;
import com.gwideal.core.manager.FunctionMng;
import com.gwideal.core.manager.LookupsMng;
import com.gwideal.core.model.Depart;
import com.gwideal.core.model.Lookups;
import com.gwideal.core.model.User;
import com.gwideal.cms.entity.Article;
import com.gwideal.cms.manager.ArticleMng;
import com.gwideal.attachment.entity.Attachment;
import com.gwideal.attachment.manager.AttachmentMng;

@Transactional
@Service
public class ArticleMngImpl extends BaseManagerImpl<Article> implements ArticleMng{

	@Autowired
	private AttachmentMng attachmentMng;
	@Autowired
	private LookupsMng lookupsMng;
	@Autowired
	private DepartMng departMng;
	@Autowired
	private FunctionMng functionMng;
	
	@Override
	public Pagination list(Article bean,String comeFrom, String sort, String order,
			int pageIndex, int pageSize,boolean isQuRole, boolean isStreetRole, User user) {
		Pagination pagination = null;
		try {
			Finder f = Finder.create();
			if(isQuRole){
				f.append("from Article a Where a.flag=1");
				f.append(" and (a.isRelease = true or a.creator.id='"+user.getId()+"')");
			}else{
				f.append("from Article a left join fetch a.departs d Where a.flag=1");
				f.append(" and ((a.isRelease = true and d.id='"+user.getDepart().getId()+"') or a.creator.id='"+user.getId()+"')");
			}
			if(!StringUtil.isEmpty(comeFrom)){
				List<Lookups> list=lookupsMng.getLookupsByCategoryCode(comeFrom);
				StringBuffer sIds=new StringBuffer();
				for(Lookups l:list){
					sIds.append("'"+l.getId()+"',");
				}
				String inIds=sIds.toString();
				if(!StringUtil.isEmpty(inIds)){
					f.append(" and a.type.id in ("+inIds.substring(0, inIds.length()-1)+")");
				}
			}
			if(null!=bean){
				if(!StringUtil.isEmpty(bean.getTitle())){
					f.append(" and a.title like :title ").setParam("title","%"+bean.getTitle()+"%");
				}
				if(!StringUtil.isEmpty(bean.getQuery())){
					f.append(" and (a.content like :query or a.title like :query) ").setParam("query","%"+bean.getQuery()+"%");
					String[] query = bean.getQuery().split(" ");
					f.append(" and (a.content like :query or a.title like :query").setParam("query","%" + query[0] + "%");
					for (int i = 1; i < query.length; i++) {
						f.append(" or a.content like :query"+i+" or a.title like :query"+i).setParam("query"+i,"%" + query[i] + "%");
					}
					f.append(" )");
				}
				if(!StringUtil.isEmpty(bean.getSearchTypeId())){
					f.append(" and a.type.id = :typeId ").setParam("typeId",bean.getSearchTypeId());
				}
				if(null!=bean.getType() && !StringUtil.isEmpty(bean.getType().getCode())){
					f.append(" and a.type.code = :typeCode ").setParam("typeCode",bean.getType().getCode());
				}
				if(null!=bean.getIsRelease()){
					f.append(" and a.isRelease = :isRelease ").setParam("isRelease",bean.getIsRelease().booleanValue());
				}
				if(null!=bean.getIsRead()){
					f.append(" and a.isRead = :isRead ").setParam("isRead",bean.getIsRead().booleanValue());
				}
				if(null!=bean.getIndexFloat()){
					f.append(" and a.indexFloat = :indexFloat ").setParam("indexFloat",bean.getIndexFloat().booleanValue());
				}
				if(!StringUtil.isEmpty(bean.getStartDate())){
					f.append(" and date_format(a.releaseTime, '%Y-%m-%d') >= :startDate");
					f.setParam("startDate", bean.getStartDate());
				}
				if(!StringUtil.isEmpty(bean.getEndDate())){
					f.append(" and date_format(a.releaseTime, '%Y-%m-%d') <= :endDate");
					f.setParam("endDate", bean.getEndDate());
				}
			}
			if(!StringUtil.isEmpty(sort) && !StringUtil.isEmpty(order) && (order.equals("asc") || order.equals("desc"))){
				f.append(" order by a."+sort+" "+order);
			}else{
				f.append(" order by a.releaseTime desc");
			}
			pagination = super.find(f, pageIndex, pageSize);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return pagination;
	}

	@Override
	public void save(Article bean,String departIds,MultipartFile[] xxfbFiles,MultipartFile[] xxfbPlayFiles,String[] originalFileName,String savePath,User user,String picId) {
			// TODO Auto-generated method stub
		//区别新增和修改
		if (null != bean && !StringUtil.isEmpty(bean.getId())) {
			Article article = super.findById(bean.getId());
			bean.setUpdator(user);
			if (article.getDeparts() != null) {
				bean.setDeparts(article.getDeparts());
			}
		} else {
			bean.setCreator(user);
		}
		bean.setIsRead(false);
		bean.setIsRelease(false);
		bean = (Article) super.merge(bean);
		if (!StringUtil.isEmpty(departIds)) {
			//前台选择的CommStreet集合
			String[] departArr = departIds.split(",");
			List<String> stres = new ArrayList<String>();
			for (String id : departArr) {
				stres.add(String.valueOf(id));
			}
			//设置article对象绑定的street
			if (null == bean.getDeparts()) {
				bean.setDeparts(new ArrayList<Depart>());
			}
			//移除未选择的街道
			List<Depart> listRemoveDepart = new ArrayList<Depart>();
			for (Depart depart : bean.getDeparts()) {
				if (!stres.contains(depart.getId())) {
					listRemoveDepart.add(depart);
				}
			}
		} else {
			bean.setDeparts(null);
		}
		//保存上传附件
		attachmentMng.upload(bean,null,xxfbFiles,savePath,user);
		attachmentMng.uploadmp4(bean,null,xxfbPlayFiles,savePath,user);
		//保存上传图片
		//删除该实例原有的绑定图片
		if(!StringUtil.isEmpty(picId)){
			List<Attachment> listAtt=attachmentMng.list(bean);
			if(null!=listAtt && listAtt.size()>0){
				for(Attachment att:listAtt){
					if(att.getServiceType()!=null && att.getServiceType().equals("INDEX_PIC")){
						att.setFlag(0);
						att.setUpdator(user);
						att.setUpdateTime(new Date());
						attachmentMng.save(att);
					}
				}
			}
			//将新图片与该实例绑定
			Attachment picBean=attachmentMng.findById(picId);
			picBean.setEntryId(bean.getEntryId());
			picBean.setJoinTable(bean.getJoinTable());
			picBean.setServiceType("INDEX_PIC");
			picBean.setUpdator(user);
			picBean.setUpdateTime(new Date());
			attachmentMng.save(picBean);
		}
	}

	@Override
	public List<Article> getAll(boolean isQuRole, boolean isStreetRole, User user) {
		// TODO Auto-generated method stub
		StringBuffer sql=new StringBuffer("from Article a Where flag=1 and isRelease=true ");
		sql.append(" order by releaseTime desc");
		return super.find(sql.toString(),new Object[]{});
	}
	@Override
	public List<Article> getAll(boolean isQuRole, boolean isStreetRole, User user,String id) {
		// TODO Auto-generated method stub
		StringBuffer sql=new StringBuffer("from Article a Where flag=1 and isRelease=true ");
		sql.append(" and type.code='"+id+"'");
		sql.append(" order by releaseTime desc");
		return super.find(sql.toString(),new Object[]{});
	}
	@Override
	public List<String> getArticleReceiverId(Article article) {
		// TODO Auto-generated method stub
		List<String> rList = new ArrayList<String>();//返回的街道/居委id集合
		//如果是区发布 则得到街道列表
		List<Depart> departList = article.getDeparts();
		if(departList!=null && departList.size()>0){
			for(Depart d: departList){
				rList.add(d.getId());
			}
		}
		return rList;
	}

	@Override
	public List<String> getArticleReceiverName(Article article) {
		// TODO Auto-generated method stub
		List<String> rList = new ArrayList<String>();//返回的街道/居委id集合
		//如果是区发布 则得到街道列表
		List<Depart> departList = article.getDeparts();
		if(departList!=null && departList.size()>0){
			for(Depart d: departList){
				rList.add(d.getName());
			}
		}
		return rList;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public boolean isExistIndexFloat(Article article,boolean isQuRole, boolean isStreetRole,User user) {
		// TODO Auto-generated method stub
		boolean indexFloat=false;
		StringBuffer hql=new StringBuffer("from Article art Where art.flag=1 and indexFloat=true");
		if(null!=article && !StringUtil.isEmpty(article.getId())){
			hql.append(" and id<>'"+article.getId()+"'");
		}
		List list=super.find(hql.toString(), new Object[]{});
		if(null!=list && list.size()>0){
			indexFloat=true;
		}
		return indexFloat;
	}
	
	/**
	 * 根据用户权限获得展示到首页飘窗的信息
	 */
	@Override
	public List<Article> getIndexFloat(boolean isQuRole, boolean isStreetRole,User user){
		StringBuffer hql=new StringBuffer("from Article a Where a.flag=1 and indexFloat=true and a.isRelease=1");
		return  super.find(hql.toString(),new Object[]{});
	}

	@Override
	public List<User> listUnReadUser(String articleId,List<Depart> listDepart,boolean isWwgzTzgg) {
		// TODO Auto-generated method stub
		List<User> listUser=new ArrayList<User>();
		//接收部门
		if(null!=listDepart && listDepart.size()>0){
			StringBuffer dIds=new StringBuffer();
			int t=listDepart.size();
			for (int i = 0; i < t; i++) {
				Depart d=listDepart.get(i);
				if(i==t-1){
					dIds.append("'"+d.getId()+"'");
				}else{
					dIds.append("'"+d.getId()+"',");
				}
			}
			StringBuffer hql=new StringBuffer("from User Where flag=1 and id not in ");
			hql.append(" (Select creator.id from ArticleReadRecord Where flag=1 and articleId='"+articleId+"')");
			hql.append(" and depart.id in ("+dIds.toString()+") order by depart.code");
			List<User> list=super.find(hql.toString(),new Object[]{});
			if(null!=list && list.size()>0){
				for(User u:list){
					//如果是维稳工作的通知公告，未读人员只显示有维稳工作权限的用户
					if(isWwgzTzgg){
						if(functionMng.hasWwgzFunction(u.getId())){
							listUser.add(u);
						}
					}else{
						listUser.add(u);
					}
				}
			}
		}
		return listUser;
	}
	
}
