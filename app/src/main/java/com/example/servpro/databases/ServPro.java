package com.example.servpro.databases;


import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.servpro.R;
import com.example.servpro.interfaces.ConnectionDao;
import com.example.servpro.interfaces.CustomerDao;
import com.example.servpro.interfaces.DealDao;
import com.example.servpro.interfaces.ServiceProviderDao;
import com.example.servpro.models.Connection;
import com.example.servpro.models.Customer;
import com.example.servpro.models.Deals;
import com.example.servpro.models.ServiceProvider;

@Database(entities = {Customer.class, ServiceProvider.class, Connection.class,Deals.class}, version = 1, exportSchema = false)
//kk

public abstract class ServPro extends RoomDatabase {


    public static ServPro instance;

    public abstract CustomerDao customerDao();

    public  abstract ServiceProviderDao serviceProviderDao();

    public  abstract ConnectionDao connectionDao();

//    public abstract CoordinateDao coordinateDao();

    public  abstract DealDao dealDao();

    public static synchronized  ServPro getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(), ServPro.class, "ServPro")
                    .fallbackToDestructiveMigration().addCallback(roomCallBack).build();
        }
        return  instance;
    }

    private static RoomDatabase.Callback roomCallBack= new RoomDatabase.Callback(){

        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateServiceProDbAsyncTask(instance).execute();
            new PopulateCustomerDbAsyncTask(instance).execute();
            new PopulateConnectionDbAsyncTask(instance).execute();
            new PopulateDealsDbAsyncTask(instance).execute();

        }
    };

    private static class PopulateServiceProDbAsyncTask extends AsyncTask<Void, Void, Void>{
        private ServiceProviderDao serviceProviderDao;

        public
 PopulateServiceProDbAsyncTask(ServPro db) {
            serviceProviderDao = db.serviceProviderDao();
        }


        @Override
        protected Void doInBackground(Void... voids) {
            serviceProviderDao.insert(new ServiceProvider("Harman Singh","21","Carpenter","harmanSingh@gmail.com","6598566325","6537 Cambie street","Vancouver","32","harman@123","iam somthing aaaaakk ksksks sksksk", R.drawable.person1,49.2820,-123.1171));
            serviceProviderDao.insert(new ServiceProvider("Peter Gabrial","32","Carpenter","peterGabri@gmail.com","6047258855","3454 glassmere street","Vancouver","23","peter@123","iam somthing aaaaakk ksksks sksksk",  R.drawable.person2,49.2626,-123.0692));
            serviceProviderDao.insert(new ServiceProvider("John Smith","21","Carpenter","smithJohn@gmail.com","6598555325","324 Fraser Street","Vancouver","32","john@123","iam somthing aaaaakk ksksks sksksk",  R.drawable.person3,49.2921,-123.0692));

            return null;
        }
    }


    private static class PopulateCustomerDbAsyncTask extends AsyncTask<Void, Void, Void>{

        private CustomerDao customerDao;

        public PopulateCustomerDbAsyncTask(ServPro db) {
            customerDao = db.customerDao();
        }


        @Override
        protected Void doInBackground(Void... voids) {
            customerDao.insert(new Customer("Karanpreet",21,"karanpreet@gmail.com","6598565856","909 welcome st Vancouver","1"));
            customerDao.insert(new Customer("Tashmeet",22,"tashMeet@gmail.com","6598500000","109 elson st Vancouver","tashmeet@123"));
            customerDao.insert(new Customer("Rohan",24,"rohanSharma@gmail.com","6500065856","4501St 100Ave Surrey","rohan@123"));

            return null;
        }
    }



    private static class PopulateConnectionDbAsyncTask extends AsyncTask<Void , Void , Void>{

        private ConnectionDao connectionDao;

        public PopulateConnectionDbAsyncTask(ServPro db) {
            this.connectionDao = db.connectionDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            connectionDao.insertConnection(new Connection("Tashmeet","tashMeet@gmail.com","harmanSingh@gmail.com","Harman Singh"));
            connectionDao.insertConnection(new Connection("Rohan","rohanSharma@gmail.com","harmanSingh@gmail.com","Harman Singh"));

            return null;
        }
    }

    private static class PopulateDealsDbAsyncTask extends AsyncTask<Void , Void , Void>{

        private DealDao dealDao;

        public PopulateDealsDbAsyncTask(ServPro db) {
            this.dealDao = db.dealDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            dealDao.insert(new Deals("harmanSingh@gmail.com", "karanpreet@gmail.com", "4 April 2022","Please Call me at..."));
            dealDao.insert(new Deals("harmanSingh@gmail.com","tashMeet@gmail.com","4 April 2022","Please Call me at..."));
            dealDao.insert(new Deals("harmanSingh@gmail.com","rohanSharma@gmail.com","4 April 2022","Please Call me at..."));

            return null;
        }
    }
}
