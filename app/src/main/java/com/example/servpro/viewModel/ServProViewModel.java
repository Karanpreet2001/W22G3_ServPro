package com.example.servpro.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.servpro.interfaces.ServiceProviderDao;
import com.example.servpro.models.Customer;
import com.example.servpro.models.Connection;
import com.example.servpro.models.Deals;
import com.example.servpro.models.ServiceProvider;
import com.example.servpro.repository.ServProRepository;

import java.util.List;

public class ServProViewModel extends AndroidViewModel {

    private ServProRepository repository;



    public ServProViewModel(@NonNull Application application) {
        super(application);
        repository = new ServProRepository(application);
    }

    public LiveData<List<ServiceProvider>> getServicesByCAS(String city, String service){
        return repository.getServices(city, service);
    }

    public LiveData<List<Customer>> getCustomers(){
        return  repository.getCustomers();
    }

    public LiveData<List<Connection>> getConnections(String email){
        return repository.getConnections(email);
    }
    public LiveData<List<ServiceProvider>> getServPro(String email){
        return repository.getServPro(email);
    }

    public void insert(Connection connection){
        repository.insert(connection);
    }

    public void delete(ServiceProvider serviceProvider){repository.delete(serviceProvider);}

    public void insert(Deals deals){
        repository.insert(deals);
    }
    public  LiveData<Customer> getACustomer(String email){

        return repository.getACustomer(email);
    }

    public LiveData<List<Deals>> getDealsAccToUserName(String email){
        return  repository.getDealsAccToUserName(email);
    }

    public LiveData<List<ServiceProvider>> getAllServPro(String email){
        return repository.getAllServPro(email);
    }
    public void servProUpdate(ServiceProvider serviceProvider){
        repository.update(serviceProvider);
    }
}
