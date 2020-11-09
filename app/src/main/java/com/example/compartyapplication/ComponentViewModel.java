package com.example.compartyapplication;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class ComponentViewModel extends AndroidViewModel
{
    private ComponentRepository repository;
    private LiveData<List<Component>> allComponents;
    private LiveData<List<Component>> allCPUs;
    private LiveData<List<Component>> allGPU;
    private LiveData<List<Component>> allRAMS;

    public ComponentViewModel(@NonNull Application application) {
        super(application);
        repository = new ComponentRepository(application);
        allComponents = repository.getAllComponents();
        allCPUs = repository.getAllCPUs();
        allGPU = repository.getAllGPUs();
        allRAMS = repository.getAllRams();
    }

    public void insert(Component component)
    {
        repository.insert(component);
    }

    public void update(Component component)
    {
        repository.update(component);
    }

    public void delete(Component component)
    {
        repository.delete(component);
    }

    public void deleteAllComponent(){
        repository.deleteAllComponents();
    }

    public LiveData<List<Component>> getAllComponents() {
        return allComponents;
    }

    public LiveData<List<Component>> getAllProcessors() {
        return allCPUs;
    }

    public LiveData<List<Component>> getAllGPUs() {
        return allGPU;
    }

    public LiveData<List<Component>> getAllRAMS() {return allRAMS;}

}
