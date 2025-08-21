package com.system.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class AdvancedSystemInfiltrationConfig {
  private final InfiltrationPlanListener infiltrationPlanListener;

  @Bean
  public Job systemInfiltrationJob(JobRepository jobRepository, Step reconStep, Step attackStep){
    return new JobBuilder("systemInfiltrationJob", jobRepository)
        .start(reconStep)
        .next(attackStep)        
        .build();
  }

  @Bean
  public Step reconStep(JobRepository jobRepository, PlatformTransactionManager transactionManager){
    return new StepBuilder("reconStep", jobRepository)
        .tasklet((contribution, chunkContext) -> {
          log.info("Reconnaissance step started.");
          // Reconnaissance logic here
          return null;
        }, transactionManager)
        
  }

}
