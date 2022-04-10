package com.example.servpro.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.servpro.databases.ServPro;
import com.example.servpro.interfaces.ConnectionDao;
import com.example.servpro.interfaces.CustomerDao;
import com.example.servpro.interfaces.DealDao;
import com.example.servpro.interfaces.ServiceProviderDao;
import com.example.servpro.models.Connection;
import com.example.servpro.models.Customer;
import com.example.servpro.models.Deals;
import com.example.servpro.models.ServiceProvider;

import java.util.List;

public class ServProRepository {

        private ServiceProviderDao serviceProviderDao;
        private CustomerDao customerDao;
        private ConnectionDao connectionDao;
        private DealDao dealDao;

        public ServProRepository(Application application){
            ServPro db = ServPro.getInstance(application);
            serviceProviderDao = db.serviceProviderDao();
            customerDao = db.customerDao();
            connectionDao = db.connectionDao();
            dealDao = db.dealDao();
        }

    public void insert(ServiceProvider serviceProvider){

        new InsertServiceProAsyncTask(serviceProviderDao).execute(serviceProvider);
    }

    public void insert(Customer customer){

        new InsertCustomerAsyncTask(customerDao).execute(customer);
    }

    public void insert(Connection connection){

        new InsertConnectionAsyncTask(connectionDao).execute(connection);
    }

    public void insert(Deals deal){

        new InsertDealAsyncTask(dealDao).execute(deal);
    }
    public void update(ServiceProvider serviceProvider){

            new UpdateServiceProviderAsyncTask(serviceProviderDao).execute(serviceProvider);
    }
    public void delete(ServiceProvider serviceProvider){

        new UpdateServiceProviderAsyncTask(serviceProviderDao).execute(serviceProvider);
    }


    public LiveData<List<ServiceProvider>> getServices(String city, String service){
            return serviceProviderDao.getSPAccordingToCAO(city, service);
    }

    public LiveData<List<Customer>> getCustomers(){
        return customerDao.getCustomers();
    }

    public LiveData<List<ServiceProvider>> getServicePros(){
        return serviceProviderDao.getServiceProviderAccordingToCAO();
    }

    public LiveData<List<Connection>> getConnections(String email){
            return connectionDao.getConnections(email);
    }

    public LiveData<List<ServiceProvider>> getServPro(String email){
        return serviceProviderDao.getServProName(email);
    }

    public LiveData<List<Deals>> getDealsAccToUserName(String email){
        return dealDao.getDealsAccToUserName(email);
    }

    public LiveData<Customer> getACustomer(String email){
        return customerDao.getCustomer(email);
    }

    public LiveData<List<ServiceProvider>> getAllServPro(String email){
            return serviceProviderDao.getAllServPro(email);
    }


    private class InsertServiceProAsyncTask  extends AsyncTask<ServiceProvider, Void, Void> {
            private ServiceProviderDao serviceProviderDao;

        public InsertServiceProAsyncTask(ServiceProviderDao serviceProviderDao) {
            this.serviceProviderDao= serviceProviderDao;
        }


        @Override
        protected Void doInBackground(ServiceProvider... serviceProviders) {
            serviceProviderDao.insert(serviceProviders[0]);
            return null;
        }
    }

    private class InsertCustomerAsyncTask  extends AsyncTask<Customer, Void, Void> {
        private CustomerDao customerDao;

        public InsertCustomerAsyncTask(CustomerDao customerDao) {
            this.customerDao= customerDao;
        }


        @Override
        protected Void doInBackground(Customer... customers) {
            customerDao.insert(customers[0]);
            return null;
        }
    }

    private class InsertConnectionAsyncTask extends AsyncTask<Connection, Void, Void> {

            private ConnectionDao connectionDao;

        public InsertConnectionAsyncTask(ConnectionDao connectionDao) {
            this.connectionDao=connectionDao;
        }

        @Override
        protected Void doInBackground(Connection... connections) {
            connectionDao.insertConnection(connections[0]);
            return null;
        }
    }

    private class InsertDealAsyncTask extends AsyncTask<Deals, Void, Void> {
            private DealDao dealDao;

        public InsertDealAsyncTask(DealDao dealDao) {
            this.dealDao=dealDao;
        }

        @Override
        protected Void doInBackground(Deals... deals) {
            dealDao.insert(deals[0]);
            return null;
        }
    }

    private class UpdateServiceProviderAsyncTask extends AsyncTask<ServiceProvider, Void, Void>{

        private ServiceProviderDao serviceProviderDao;

        public UpdateServiceProviderAsyncTask(ServiceProviderDao serviceProviderDao) {
            this.serviceProviderDao = serviceProviderDao;
        }

        @Override
        protected Void doInBackground(ServiceProvider... serviceProviders) {
            serviceProviderDao.serProUpdate(serviceProviders[0]);
            return null;
        }
    }

    private class DeleteServiceProviderAsyncTask extends AsyncTask<ServiceProvider, Void, Void>{

        private ServiceProviderDao serviceProviderDao;

        public DeleteServiceProviderAsyncTask(ServiceProviderDao serviceProviderDao) {
            this.serviceProviderDao = serviceProviderDao;
        }

        @Override
        protected Void doInBackground(ServiceProvider... serviceProviders) {
            serviceProviderDao.serProDelete(serviceProviders[0]);
            return null;
        }
    }
}
