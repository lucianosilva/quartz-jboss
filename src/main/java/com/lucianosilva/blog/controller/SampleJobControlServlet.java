/**
 * 
 */
package com.lucianosilva.blog.controller;

import java.io.IOException;
import java.text.ParseException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.quartz.CronTrigger;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

import com.lucianosilva.blog.SampleJobExecuter;

/**
 * @author luciano
 *
 */
@WebServlet(urlPatterns = "/SampleJobController", loadOnStartup = 2)
public class SampleJobControlServlet extends HttpServlet{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6112297000125365478L;

	private static final Logger logger = Logger.getLogger( SampleJobControlServlet.class );
	
	@Override
	public void init( ServletConfig config ) throws ServletException {
		super.init( config );

		// define o agendamento da execucao do servico
		// executa a cada 3 minutos todos os dias.
		String firstJobDetail = "0 0/2 * * * ?";
		logger.info("Getting servlet configuration for ");
		
		try {
			SchedulerFactory sf = new StdSchedulerFactory();
			Scheduler scheduler = sf.getScheduler();
			
			logger.info("Scheduler Factory already started");
			
			JobDetail jobSampleExecuter = new JobDetail("jobSampleExecuter", Scheduler.DEFAULT_GROUP, SampleJobExecuter.class);
			
			logger.info("get job sample executer");
			
			CronTrigger ctjobSampleExecuter = new CronTrigger("cronTriggerJobSampleExecuter", "group1", firstJobDetail);
			
			logger.info("get cron trigger for job sample executer");
			
			scheduler.scheduleJob(jobSampleExecuter, ctjobSampleExecuter);
			
			logger.info("schedule has been started...");
			
			scheduler.start();
			
		} catch (SchedulerException se) {
			logger.error(se.getMessage(), se);
		} catch (ParseException pe) {
			logger.error(pe.getMessage(), pe);
		}
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.doGet(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		super.doPost(request, response);
	}
}
