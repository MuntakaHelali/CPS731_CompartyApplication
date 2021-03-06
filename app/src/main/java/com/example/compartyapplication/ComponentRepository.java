package com.example.compartyapplication;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class ComponentRepository
{
    private ComponentDao componentDao;
    private LiveData<List<Component>> allComponents;
    private LiveData<List<Component>> allCPUs;
    private LiveData<List<Component>> allGraphicsCards;
    private LiveData<List<Component>> allRams;
    private LiveData<List<Component>> allMotherboards;
    private LiveData<List<Component>> allCases;
    private LiveData<List<Component>> allPowerSupply;

    public ComponentRepository(Application application)
    {
        ComponentDatabase database = ComponentDatabase.getInstance(application);
        componentDao = database.componentDao();
        allComponents = componentDao.getAllComponents();
        allCPUs = componentDao.getAllCPUs();
        allGraphicsCards = componentDao.getAllGPUs();
        allRams = componentDao.getAllRAMs();
        allMotherboards = componentDao.getAllMOTHERBOARDS();
        allCases = componentDao.getAllCASES();
        allPowerSupply = componentDao.getAllPOWERSUPPLY();
    }

    public void insert(Component component)
    {
        new InsertComponentAsyncTask(componentDao).execute(component);
    }

    public void update(Component component)
    {
        new UpdateComponentAsyncTask(componentDao).execute(component);
    }

    public void delete(Component component)
    {
        new DeleteComponentAsyncTask(componentDao).execute(component);
    }

    public void deleteAllCPUS() {new DeleteAllCPUSAsyncTask(componentDao).execute();}

    public void deleteAllComponents()
    {
        new DeleteAllComponentAsyncTask(componentDao).execute();
    }

    public void deleteAllRam()
    {
        new DeleteAllRamAsyncTask(componentDao).execute();
    }

    public void deleteAllMotherboard() {new DeleteAllMotherboardAsyncTask(componentDao).execute();}

    public void deleteAllCases() {new DeleteAllCasesAsyncTask(componentDao).execute();}

    public void deleteAllPowerSupply() {new DeleteAllPowerSupplyAsyncTask(componentDao).execute();}


    public LiveData<List<Component>> getAllComponents()
    {
        return allComponents;
    }

    public LiveData<List<Component>> getAllCPUs()
    {
        return allCPUs;
    }

    public LiveData<List<Component>> getAllGPUs()
    {
        return allGraphicsCards;
    }

    public LiveData<List<Component>> getAllRams() {return allRams;}

    public LiveData<List<Component>> getAllMotherboards() {return allMotherboards;}

    public LiveData<List<Component>> getAllCases() {return allCases;}

    public LiveData<List<Component>> getAllPowerSupply(){return allPowerSupply;}


    private static class InsertComponentAsyncTask extends AsyncTask<Component, Void, Void>
    {
        private ComponentDao componentDao;

        private InsertComponentAsyncTask(ComponentDao componentDao)
        {
            this.componentDao = componentDao;
        }

        @Override
        protected Void doInBackground(Component... components) {
            componentDao.insert(components[0]);
            return null;
        }
    }

    private static class UpdateComponentAsyncTask extends AsyncTask<Component, Void, Void>
    {
        private ComponentDao componentDao;

        private UpdateComponentAsyncTask(ComponentDao componentDao)
        {
            this.componentDao = componentDao;
        }

        @Override
        protected Void doInBackground(Component... components) {
            componentDao.update(components[0]);
            return null;
        }
    }

    private static class DeleteComponentAsyncTask extends AsyncTask<Component, Void, Void>
    {
        private ComponentDao componentDao;

        private DeleteComponentAsyncTask(ComponentDao componentDao)
        {
            this.componentDao = componentDao;
        }

        @Override
        protected Void doInBackground(Component... components) {
            componentDao.delete(components[0]);
            return null;
        }
    }

    private static class DeleteAllCPUSAsyncTask extends AsyncTask<Void, Void, Void> {
        private ComponentDao componentDao;
        private DeleteAllCPUSAsyncTask(ComponentDao componentDao){
            this.componentDao = componentDao;
        }

        @Override
        protected Void doInBackground(Void... Voids){
            componentDao.deleteAllCPUS();
            return null;
        }
    }

    private static class DeleteAllComponentAsyncTask extends AsyncTask<Void, Void, Void> {
        private ComponentDao componentDao;
        private DeleteAllComponentAsyncTask(ComponentDao componentDao){
            this.componentDao = componentDao;
        }

        @Override
        protected Void doInBackground(Void... Voids){
            componentDao.deleteAllComponents();
            return null;
        }
    }

    private static class DeleteAllRamAsyncTask extends AsyncTask<Void, Void, Void> {
        private ComponentDao componentDao;
        private DeleteAllRamAsyncTask(ComponentDao componentDao){
            this.componentDao = componentDao;
        }

        @Override
        protected Void doInBackground(Void... Voids){
            componentDao.deleteAllRam();
            return null;
        }
    }

    private static class DeleteAllMotherboardAsyncTask extends AsyncTask<Void, Void, Void> {
        private ComponentDao componentDao;
        private DeleteAllMotherboardAsyncTask(ComponentDao componentDao){
            this.componentDao = componentDao;
        }

        @Override
        protected Void doInBackground(Void... Voids){
            componentDao.deleteAllMotherboard();
            return null;
        }
    }

    private static class DeleteAllCasesAsyncTask extends AsyncTask<Void, Void, Void> {
        private ComponentDao componentDao;
        private DeleteAllCasesAsyncTask(ComponentDao componentDao){
            this.componentDao = componentDao;
        }

        @Override
        protected Void doInBackground(Void... Voids){
            componentDao.deleteAllCases();
            return null;
        }
    }

    private static class DeleteAllPowerSupplyAsyncTask extends AsyncTask<Void, Void, Void> {
        private ComponentDao componentDao;
        private DeleteAllPowerSupplyAsyncTask(ComponentDao componentDao){
            this.componentDao = componentDao;
        }

        @Override
        protected Void doInBackground(Void... Voids){
            componentDao.deleteAllPowerSupply();
            return null;
        }
    }
}
