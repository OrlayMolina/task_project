package com.hexagonal.tasks.application.usecases;

import com.hexagonal.tasks.domain.models.Task;
import com.hexagonal.tasks.domain.ports.in.RetrieveTaskUseCase;
import com.hexagonal.tasks.domain.ports.out.TaskRepositoryPort;

import java.util.List;
import java.util.Optional;

public class RetrieveTaskUseImpl implements RetrieveTaskUseCase {

    private final TaskRepositoryPort taskRepositoryPort;

    public RetrieveTaskUseImpl(TaskRepositoryPort taskRepositoryPort) {
        this.taskRepositoryPort = taskRepositoryPort;
    }

    @Override
    public Optional<Task> getTask(Long id) {
        return taskRepositoryPort.findById(id);
    }

    @Override
    public List<Task> getAllTasks() {
        return taskRepositoryPort.findAll();
    }
}
