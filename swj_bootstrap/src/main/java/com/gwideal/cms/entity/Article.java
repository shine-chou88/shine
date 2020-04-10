package com.gwideal.cms.entity;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Where;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gwideal.common.entity.EntityDao;
import com.gwideal.common.entity.GenericEntityNow;
import com.gwideal.common.util.StringUtil;
import com.gwideal.core.model.Depart;
import com.gwideal.core.model.Lookups;
import com.gwideal.core.model.User;
/**
 * 信息发布
 * @author zhou_liang
 *
 */
@Entity
@Table(name="T_CMS_ARTICLE")
public class Article extends GenericEntityNow implements Serializable,EntityDao{
	private static final long serialVersionUID = 8483604067154966894L;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="TYPE")
	@JsonIgnore
	private Lookups type;//信息类型
	
	private String title;//标题
	
	private String origin;//来源
	
	private String content;//内容
	
	@Column(name="IS_RELEASE")
	private Boolean isRelease;//是否发布
	
	@Column(name="RELEASE_TIME")
	private Date releaseTime;//发布时间
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="RELEASE_USER_ID")
	@JsonIgnore
	private User releaseUser;//发布人
	
	@Column(name="IS_READ")
	private Boolean isRead;//是否阅读
	
	@Column(name="READ_COUNT")
	private Integer readCount;//阅读次数
	
	@Column(name="INDEX_FLOAT")
	private Boolean indexFloat;//是否首页漂窗
	
	@Column(name="LINK")
	private String link;//外部链接
	
	@Column(name="CHANNEL_NAME")
	private String channelName;
	
	@Transient
	private String isMdjf;
	
	@Transient
	private boolean userRead=false;
	
	@ManyToMany(targetEntity = Depart.class,cascade = {CascadeType.PERSIST,CascadeType.REFRESH,CascadeType.MERGE},fetch=FetchType.LAZY)
	@JoinTable(name="T_CMS_ARTICLE_SENDEE",joinColumns=@JoinColumn(name="ARTICLE_ID"),inverseJoinColumns=@JoinColumn(name="DEPART_ID"))
	@Where(clause = "pflag = 1")
	@JsonIgnore
	private List<Depart> departs;
	
	@Transient
	private String fileUrl;
	
	@Transient
	private String startDate;
	
	@Transient
	private String endDate;
	
	@Transient
	private String searchTypeId;
	
	@Transient
	private String query;
	
	public String getSearchTypeId() {
		return searchTypeId;
	}

	public void setSearchTypeId(String searchTypeId) {
		this.searchTypeId = searchTypeId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public Boolean getIndexFloat() {
		return indexFloat;
	}

	public void setIndexFloat(Boolean indexFloat) {
		this.indexFloat = indexFloat;
	}

	public boolean isUserRead() {
		return userRead;
	}

	public void setUserRead(boolean userRead) {
		this.userRead = userRead;
	}

	public Lookups getType() {
		return type;
	}

	public void setType(Lookups type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getContentText(){
		String s=getContent();
		if(!StringUtil.isEmpty(s)){
			Document doc=Jsoup.parse(s);
			s=doc.text();
		}
		return s;
	}
	
	public Date getReleaseTime() {
		return releaseTime;
	}

	public void setReleaseTime(Date releaseTime) {
		this.releaseTime = releaseTime;
	}

	public User getReleaseUser() {
		return releaseUser;
	}

	public void setReleaseUser(User releaseUser) {
		this.releaseUser = releaseUser;
	}

	public Integer getReadCount() {
		return readCount;
	}

	public void setReadCount(Integer readCount) {
		this.readCount = readCount;
	}

	public Boolean getIsRelease() {
		return isRelease;
	}

	public void setIsRelease(Boolean isRelease) {
		this.isRelease = isRelease;
	}

	public Boolean getIsRead() {
		return isRead;
	}

	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}

	@Override
	public String getJoinTable() {
		// TODO Auto-generated method stub
		return "T_CMS_ARTICLE";
	}

	@Override
	public String getEntryId() {
		// TODO Auto-generated method stub
		return getId();
	}

	public List<Depart> getDeparts() {
		return departs;
	}

	public void setDeparts(List<Depart> departs) {
		this.departs = departs;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getChannelName() {
		return channelName;
	}

	public void setChannelName(String channelName) {
		this.channelName = channelName;
	}

	public String getIsMdjf() {
		return isMdjf;
	}

	public void setIsMdjf(String isMdjf) {
		this.isMdjf = isMdjf;
	}
	
	/**
	 * 创建人
	 * @return
	 */
	public String getCreatorId(){
		String creatorId="";
		if(null!=getCreator() && !StringUtil.isEmpty(getCreator().getId())){
			creatorId=getCreator().getId();
		}
		return creatorId;
	}
	
	/**
	 * 创建人
	 * @return
	 */
	public String getCreatorName(){
		String creatorName="";
		if(null!=getCreator() && !StringUtil.isEmpty(getCreator().getName())){
			creatorName=getCreator().getName();
		}
		return creatorName;
	}
	
	/**
	 * 创建时间
	 * @return
	 */
	public String getCreateTimeStr(){
		String createTimeStr="";
		if(null!=getCreateTime()){
			createTimeStr=new SimpleDateFormat("yyyy-MM-dd HH:mm").format(getCreateTime());
		}
		return createTimeStr;
	}
	
	/**
	 * 文章类型
	 * @return
	 */
	public String getTypeName(){
		String typeName="";
		if(null!=getType() && !StringUtil.isEmpty(getType().getName())){
			typeName=getType().getName();
		}
		return typeName;
	}
	
	/**
	 * 发布状态
	 * @return
	 */
	public String getReleaseStatus(){
		String releaseStatus="未发布";
		if(null!=getIsRelease() && getIsRelease().booleanValue()){
			releaseStatus="已发布";
		}
		return releaseStatus;
	}
	
	/**
	 * 发布时间
	 * @return
	 */
	public String getReleaseTimeStr(){
		String releaseTimeStr="";
		if(null!=getReleaseTime()){
			releaseTimeStr=new SimpleDateFormat("yyyy-MM-dd HH:mm").format(getReleaseTime());
		}
		return releaseTimeStr;
	}

	@Override
	public String getEntryCode() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}
	
}
