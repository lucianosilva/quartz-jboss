package com.lucianosilva.blog;

import java.text.SimpleDateFormat;

import org.apache.log4j.Logger;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

/**
 * @author luciano
 *
 */
public class SampleJobExecuter implements Job {

	private static final Logger logger = Logger.getLogger( SampleJobExecuter.class );
	
	/* (non-Javadoc)
	 * @see org.quartz.Job#execute(org.quartz.JobExecutionContext)
	 */
	@Override
	public void execute( JobExecutionContext jobExecutionContext ) throws JobExecutionException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-M-yyyy H:m");
		
		// 
		logger.info("| ==== | ");
		logger.info("It has began the job execution. Context detail: ");
		logger.info( "Fire Time : " + dateFormat.format(jobExecutionContext.getFireTime()) );
		logger.info( "Next Fire Time : " +  dateFormat.format(jobExecutionContext.getNextFireTime()) );
		logger.info("| ==== | ");
		
	}

}
