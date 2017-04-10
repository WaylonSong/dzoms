package com.dz.module.contract;

import com.dz.common.factory.HibernateSessionFactory;
import com.dz.common.global.Page;
import com.dz.common.other.ObjectAccess;
import com.dz.common.other.PageUtil;
import com.dz.common.other.TimeComm;
import com.dz.module.user.User;
import com.opensymphony.xwork2.ActionSupport;

import net.sf.json.JSONObject;

import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
@Controller
@Scope("prototype")
public class BankCardAction extends ActionSupport implements ServletRequestAware{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BankCard bankCard;
	@Autowired
	private BankCardService bankCardService;
	private HttpServletRequest request;
	private int id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public BankCardService getBankCardService() {
		return bankCardService;
	}
	
	public void setBankCardService(BankCardService bankCardService) {
		this.bankCardService = bankCardService;
	}
	
	public BankCard getBankCard() {
		return bankCard;
	}
	
	public void setBankCard(BankCard bankCard) {
		this.bankCard = bankCard;
	}
	
	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		// TODO Auto-generated method stub
		this.request = arg0;
	}
	
	/**
	 * ���п����Action������
	 * @return
	 */
	public String bankCardAdd() {
		User user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			bankCard.setOperator(user.getUid());
			bankCard.setOpeTime(TimeComm.getCurrentTime());
		}
		boolean flag = bankCardService.bankCardAdd(bankCard);
		if (!flag) {
			this.addActionError("添加失败。");
			return "add_success";
		} else {
			this.addActionError("添加成功。");
			return "add_success";
		}
	}
	
	private String dept,order;
	private Boolean rank;
	
	public String searchCard(){
		int currentPage = 0;
		if (request.getParameter("currentPage") != null
				&& !(request.getParameter("currentPage")).isEmpty()) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));

			System.out.println(request.getParameter("currentPage"));
		} else {
			currentPage = 1;
		}
		
		if(bankCard==null){
			bankCard = new BankCard();
		}
		
		String hql = "from BankCard where 1=1 ";
		
		if (!StringUtils.isEmpty(dept)&&!dept.startsWith("all")) {
			hql+= String.format("and carNum in (select licenseNum from Vehicle where dept='%s') ", dept);
		}
        
        if (!StringUtils.isEmpty(bankCard.getIdNumber())) {
			hql+= String.format("and idNumber like '%%%s%%' ", bankCard.getIdNumber());
		}
        
        if (!StringUtils.isEmpty(bankCard.getCardNumber())) {
			hql+= String.format("and cardNumber like '%%%s%%' ", bankCard.getCardNumber());
		}
        
        if (!StringUtils.isEmpty(bankCard.getCarNum())) {
			hql+= String.format("and carNum in(select carframeNum from Vehicle where licenseNum like '%%%s%%') ", bankCard.getCarNum());
		}
        
        hql += " order by "+order;
        
        if (BooleanUtils.isFalse(rank)){
        	hql += " desc ";
        }
        
        Session session = HibernateSessionFactory.getSession();
        Query query = session.createQuery(hql);
        Query query2 = session.createQuery("select count(*) "+hql);
        
        long count = (long)query2.uniqueResult();
        
        Page page = PageUtil.createPage(30, (int)count, currentPage);
        
        query.setFirstResult(page.getBeginIndex());
		query.setMaxResults(page.getEveryPage());
		
		
		List<BankCard> cardList = query.list();
		
		HibernateSessionFactory.closeSession();
		
		request.setAttribute("list", cardList);
		request.setAttribute("page", page);
	
		return SUCCESS;
	}
	
	/**
	 * ���п���ҳ��ѯActionʵ��
	 * @throws IOException
	 */
	public void bankCardSearch() throws IOException{
		ServletActionContext.getResponse().setContentType("application/json");
		ServletActionContext.getResponse().setCharacterEncoding("utf-8");
		PrintWriter out = ServletActionContext.getResponse().getWriter();
		int currentPage = 0;
		if (request.getParameter("currentPage") != null
				&& !(request.getParameter("currentPage")).isEmpty()) {
			currentPage = Integer.parseInt(request.getParameter("currentPage"));

			System.out.println(request.getParameter("currentPage"));
		} else {
			currentPage = 1;
		}
		Page page = PageUtil.createPage(PageUtil.PAGE_SIZE,
				bankCardService.queryCardCount(), currentPage);
		List<BankCard> cardList = bankCardService.bankCardSearch(page);
		
		JSONObject json = new JSONObject();
		json.put("list", cardList);
		json.accumulate("hasPrePage", page.isHasPrePage());
		json.accumulate("hasNextPage", page.isHasNexPage());
		json.accumulate("currentPage", currentPage);
		json.accumulate("totalPage", page.getTotalPage());
		
		out.print(json.toString());
		
		out.flush();
		out.close();
	}
	
	public String bankCardShow() {
		BankCard card = null;
		card = (BankCard) ObjectAccess.getObject("com.dz.module.contract.BankCard", id);
		if (card == null) {
			return ERROR;
		}
		request.getSession().setAttribute("card", card);
		return "show_success";
	}
	
	public String bankCardUpdate() {
		User user = (User) request.getSession().getAttribute("user");
		
		if (user != null) {
			bankCard.setOperator(user.getUid());
			bankCard.setOpeTime(TimeComm.getCurrentTime());
		}
		
		if (bankCardService.bankCardUpdate(bankCard))
			return "update_success";
		
		return ERROR;
	}
	
	public String getDept() {
		return dept;
	}

	public void setDept(String dept) {
		this.dept = dept;
	}

	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}

	public Boolean getRank() {
		return rank;
	}

	public void setRank(Boolean rank) {
		this.rank = rank;
	}
	
	
}
