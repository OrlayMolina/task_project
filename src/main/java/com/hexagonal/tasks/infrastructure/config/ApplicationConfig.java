package com.hexagonal.tasks.infrastructure.config;

import com.hexagonal.tasks.application.services.TaskService;
import com.hexagonal.tasks.application.usecases.*;
import com.hexagonal.tasks.domain.ports.in.GetAdditionalTaskUseCase;
import com.hexagonal.tasks.domain.ports.out.ExternalServicePort;
import com.hexagonal.tasks.domain.ports.out.TaskRepositoryPort;
import com.hexagonal.tasks.infrastructure.adapters.ExternalServiceAdapter;
import com.hexagonal.tasks.infrastructure.repositories.JpaTaskRepositoryAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfig {

    @Bean
    public TaskService taskService(TaskRepositoryPort taskRepositoryPort, GetAdditionalTaskUseCase getAdditionalTaskUseCase){
        return new TaskService(
                new CreateTaskUseCaseImpl(taskRepositoryPort),
                new DeleteTaskUseCaseImpl(taskRepositoryPort),
                getAdditionalTaskUseCase,
                new RetrieveTaskUseImpl(taskRepositoryPort),
                new UpdateTaskUseCaseImpl(taskRepositoryPort)

        );
    }

    @Bean
    public TaskRepositoryPort taskRepositoryPort(JpaTaskRepositoryAdapter jpaTaskRepositoryAdapter){
        return jpaTaskRepositoryAdapter;
    }

    @Bean
    public GetAdditionalTaskUseCase getAdditionalTaskUseCase(ExternalServicePort externalServicePort){
        return new GetAdditionalTaskUseCaseImpl(externalServicePort);
    }

    @Bean
    public ExternalServicePort externalServicePort(){
        return new ExternalServiceAdapter();
    }
}
