package com.x.report.assemble.control.factory;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.openjpa.lib.util.StringUtil;

import com.x.base.core.project.exception.ExceptionWhen;
import com.x.base.core.project.tools.ListTools;
import com.x.report.assemble.control.AbstractFactory;
import com.x.report.assemble.control.Business;
import com.x.report.core.entity.Report_C_WorkPlan;
import com.x.report.core.entity.Report_C_WorkPlanNext;
import com.x.report.core.entity.Report_C_WorkPlanNextDetail;
import com.x.report.core.entity.Report_C_WorkPlanNextDetail_;
import com.x.report.core.entity.Report_C_WorkPlanNext_;

/**
 * 系统汇报信息服务类
 * @author O2LEE
 */
public class Report_C_WorkPlanNextFactory extends AbstractFactory {
	
	public Report_C_WorkPlanNextFactory( Business business) throws Exception {
		super(business);
	}

	//@MethodDescribe("获取指定Id的Report_C_WorkPlan信息对象")
	public Report_C_WorkPlan get( String id ) throws Exception {
		return this.entityManagerContainer().find(id, Report_C_WorkPlan.class, ExceptionWhen.none);
	}

	//@MethodDescribe("列示全部的Report_C_WorkPlan信息列表")
	@SuppressWarnings("unused")
	public List<Report_C_WorkPlan> listAll() throws Exception {
		EntityManager em = this.entityManagerContainer().get( Report_C_WorkPlan.class );
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Report_C_WorkPlan> cq = cb.createQuery( Report_C_WorkPlan.class );
		Root<Report_C_WorkPlan> root = cq.from( Report_C_WorkPlan.class );
		return em.createQuery(cq).getResultList();
	}
	
	//@MethodDescribe("列示指定Id的Report_C_WorkPlan信息列表")
	public List<Report_C_WorkPlanNext> list(List<String> ids) throws Exception {
		EntityManager em = this.entityManagerContainer().get(Report_C_WorkPlanNext.class);
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Report_C_WorkPlanNext> cq = cb.createQuery(Report_C_WorkPlanNext.class);
		Root<Report_C_WorkPlanNext> root = cq.from(Report_C_WorkPlanNext.class);
		Predicate p = root.get(Report_C_WorkPlanNext_.id).in(ids);
		return em.createQuery(cq.where(p)).getResultList();
	}

	public List<Report_C_WorkPlanNext> listWithReportId(String reportId) throws Exception {
		EntityManager em = this.entityManagerContainer().get(Report_C_WorkPlanNext.class);
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Report_C_WorkPlanNext> cq = cb.createQuery(Report_C_WorkPlanNext.class);
		Root<Report_C_WorkPlanNext> root = cq.from(Report_C_WorkPlanNext.class);
		Predicate p = cb.equal( root.get(Report_C_WorkPlanNext_.reportId), reportId);
		return em.createQuery(cq.where(p)).getResultList();
	}
	
	public List<String> listWithReportIds( List<String> reportIds) throws Exception {
		if( ListTools.isEmpty( reportIds ) ){
			throw new Exception("reportId is empty!");
		}
		EntityManager em = this.entityManagerContainer().get(Report_C_WorkPlanNext.class);
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<String> cq = cb.createQuery(String.class);
		Root<Report_C_WorkPlanNext> root = cq.from(Report_C_WorkPlanNext.class);
		Predicate p = root.get(Report_C_WorkPlanNext_.reportId).in( reportIds );
		cq.select( root.get(Report_C_WorkPlanNext_.id));
		return em.createQuery(cq.where(p)).getResultList();
	}

	public List<Report_C_WorkPlanNextDetail> listDetailNextWithPlanId(String planId) throws Exception {
		EntityManager em = this.entityManagerContainer().get(Report_C_WorkPlanNextDetail.class);
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Report_C_WorkPlanNextDetail> cq = cb.createQuery(Report_C_WorkPlanNextDetail.class);
		Root<Report_C_WorkPlanNextDetail> root = cq.from(Report_C_WorkPlanNextDetail.class);
		Predicate p = cb.equal( root.get(Report_C_WorkPlanNextDetail_.planId), planId);
		return em.createQuery( cq.where(p) ).getResultList();
	}

	public List<String> listWithReportAndWorkInfoId(String reportId, String workInfoId) throws Exception {
		EntityManager em = this.entityManagerContainer().get(Report_C_WorkPlanNext.class);
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<String> cq = cb.createQuery(String.class);
		Root<Report_C_WorkPlanNext> root = cq.from(Report_C_WorkPlanNext.class);
		Predicate p = cb.equal( root.get(Report_C_WorkPlanNext_.reportId), reportId);
		p = cb.and( p, cb.equal( root.get(Report_C_WorkPlanNext_.workInfoId), workInfoId));
		cq.select( root.get(Report_C_WorkPlanNext_.id));
		return em.createQuery( cq.where(p) ).getResultList();
	}

	public List<String> listIdsWithProfileId(String profileId) throws Exception {
		if( StringUtil.isEmpty( profileId )) {
			throw new Exception("profileId is null!");
		}
		EntityManager em = this.entityManagerContainer().get(Report_C_WorkPlanNext.class);
		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<String> cq = cb.createQuery(String.class);
		Root<Report_C_WorkPlanNext> root = cq.from(Report_C_WorkPlanNext.class);
		Predicate p = cb.equal( root.get(Report_C_WorkPlanNext_.profileId), profileId);
		cq.select( root.get(Report_C_WorkPlanNext_.id));
		return em.createQuery( cq.where(p) ).getResultList();
	}
}