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
    private LiveData<List<Component>> allMotherBoards;
    private LiveData<List<Component>> allCase;
    private LiveData<List<Component>> allpowerSupply;

    public ComponentViewModel(@NonNull Application application) {
        super(application);
        repository = new ComponentRepository(application);
        allComponents = repository.getAllComponents();
        allCPUs = repository.getAllCPUs();
        allGPU = repository.getAllGPUs();
        allRAMS = repository.getAllRams();
        allMotherBoards = repository.getAllMotherboards();
        allCase = repository.getAllCases();
        allpowerSupply = repository.getAllPowerSupply();
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

    public void deleteAllCPUS() {repository.deleteAllCPUS();}

    public void deleteAllComponent(){
        repository.deleteAllComponents();
    }

    public void deleteAllRam() {repository.deleteAllRam();}

    public void deleteAllMotherboard() {repository.deleteAllMotherboard();}

    public void deleteAllCases() {repository.deleteAllCases();}

    public void deleteAllPowerSupply() {repository.deleteAllPowerSupply();}

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

    public LiveData<List<Component>> getAllMotherBoards() {return allMotherBoards;}

    public LiveData<List<Component>> getAllCase() {return allCase;}

    public LiveData<List<Component>> getAllpowerSupply() {return allpowerSupply;}

}
