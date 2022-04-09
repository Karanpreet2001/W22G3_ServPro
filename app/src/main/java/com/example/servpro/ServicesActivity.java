package com.example.servpro;

import androidx.appcompat.app.AppCompatActivity;

//import android.widget.SearchView;

public class ServicesActivity extends AppCompatActivity {

    /*String city, service;
    ServPro servPro;
    ServiceProviderDao serviceProviderDao;

    ServProViewModel servProViewModel;
    ServicesRecyclerView servicesRecyclerView;
    ShimmerFrameLayout shimmerFrameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services);

        shimmerFrameLayout = findViewById(R.id.shimmer);

        shimmerFrameLayout.startShimmer();



        Bundle b1= getIntent().getExtras();
        String city = b1.getString("CITY");
        String service = b1.getString("SERVICE");
        String username = b1.getString("EMAIL");

        Log.d("city",city +service);

        RecyclerView recyclerViewServices = findViewById(R.id.recyclerViewServices);
        LinearLayoutManager ln = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerViewServices.setLayoutManager(ln);


        servProViewModel= new ViewModelProvider(this).get(ServProViewModel.class);
        servProViewModel.getServicesByCAS(city,service).observe(this, new Observer<List<ServiceProvider>>() {
            @Override
            public void onChanged(List<ServiceProvider> extractList) {

                shimmerFrameLayout.stopShimmer();
                shimmerFrameLayout.setVisibility(View.GONE);
                recyclerViewServices.setVisibility(View.VISIBLE);
                Toast.makeText(ServicesActivity.this,extractList.size()+"" , Toast.LENGTH_SHORT).show();

                recyclerViewServices.setAdapter(new ServicesRecyclerView(extractList, new ServicesRecyclerView.OnClickItem() {
                    @Override
                    public void onClickItem(int index) {


                        Intent result = new Intent(ServicesActivity.this, ServiceDetails.class);
                        Bundle b = new Bundle();
                        b.putString("USERNAME", username);
                        b.putString("NAME",extractList.get(index).getServiceProvider());
                        b.putString("AGE", extractList.get(index).getAge());
                        b.putString("OCCU", extractList.get(index).getOccupation());
                        b.putString("EMAIL", extractList.get(index).getEmail());
                        b.putString("PHONE", extractList.get(index).getPhone());
                        b.putString("ADDRESS", extractList.get(index).getStreet());
                        b.putString("CITY", extractList.get(index).getCity());
                        b.putString("WAGE", extractList.get(index).getWage());
                        b.putString("DESCRIPTION", extractList.get(index).getDescription());

                        result.putExtras(b);
                        startActivity(result);
                    }
                }));






            }
        });






//
//        servPro = Room.databaseBuilder(getApplicationContext(),ServPro.class, "servpro.db").build();
//        serviceProviderDao= servPro.serviceProviderDao();







//        ExecutorService executorService = Executors.newSingleThreadExecutor();
//
//        executorService.execute(() ->{
//
//
//             extractList = serviceProviderDao.getServiceProviderAccordingly(city, service);
//
//
//
//
//            runOnUiThread(() -> {
//
//                 servicesRecyclerView = new ServicesRecyclerView(extractList, new ServicesRecyclerView.OnClickItem() {
//                    @Override
//                    public void onClickItem(int index) {
//
//                        Intent result = new Intent(ServicesActivity.this, ServiceDetails.class);
//                        Bundle b = new Bundle();
//                        b.putString("NAME",extractList.get(index).getServiceProvider());
//                        b.putString("AGE", extractList.get(index).getAge());
//                        b.putString("OCCU", extractList.get(index).getOccupation());
//                        b.putString("EMAIL", extractList.get(index).getEmail());
//                        b.putString("PHONE", extractList.get(index).getPhone());
//                        b.putString("ADDRESS", extractList.get(index).getStreet());
//                        b.putString("CITY", extractList.get(index).getCity());
//                        b.putString("WAGE", extractList.get(index).getWage());
//                        b.putString("DESCRIPTION", extractList.get(index).getDescription());
//
//                        result.putExtras(b);
//                        startActivity(result);
//                    }
//                });
//
//                shimmerFrameLayout.stopShimmer();
//                shimmerFrameLayout.setVisibility(View.GONE);
//                recyclerViewServices.setVisibility(View.VISIBLE);
//
//
//                recyclerViewServices.setAdapter(servicesRecyclerView);
//            });
//
//
//        });
//
//
       }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_serachbar, menu);

        MenuItem searchItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) searchItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                servicesRecyclerView.getFilter().filter(s);
                return false;
            }
        });

        return  true;
    }*/
}