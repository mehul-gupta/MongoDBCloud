package com.example.mehul.mongodbcloud;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.mehul.mongodbcloud.Class.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ListView lstView;
    Button btnAdd,btnEdit,btnDelete;
    EditText editUser;
    User userSelected =null;
    List<User> users=new ArrayList<User>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lstView=(ListView)findViewById(R.id.lstView);
        btnAdd=(Button)findViewById(R.id.btnAdd);
        btnEdit=(Button)findViewById(R.id.btnEdit);
        btnDelete=(Button)findViewById(R.id.btnDelete);
        editUser=(EditText)findViewById(R.id.editUsername);

        new GetData().execute(Common.getAddressAPI());

        lstView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                userSelected=users.get(position);
                editUser.setText(userSelected.getUser());
            }
        });

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PostData(editUser.getText().toString()).execute(Common.getAddressAPI());
            }
        });

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new PutData(editUser.getText().toString()).execute(Common.getAddressSingle(userSelected));
            }
        });

        btnDelete.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                new DeleteData(userSelected).execute(Common.getAddressSingle(userSelected));
            }
        });
    }

    class GetData extends AsyncTask<String,Void,String>{

        ProgressDialog pd=new ProgressDialog(MainActivity.this);

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd.setTitle("Please wait...");
            pd.show();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);

            Gson gson=new Gson();
            Type listType=new TypeToken<List<User>>(){}.getType();
            users=gson.fromJson(s,listType);
            CustomAdapter adapter=new CustomAdapter(getApplicationContext(),users);
            lstView.setAdapter(adapter);
            pd.dismiss();
        }

        @Override
        protected String doInBackground(String... params) {
            String stream=null;
            String urlString=params[0];

            HTTPDataHandler http= new HTTPDataHandler();
            stream=http.GetHTTPData(urlString);
            return stream;
        }
    }

    class PostData extends AsyncTask<String,String,String>{

        ProgressDialog pd = new ProgressDialog(MainActivity.this);
        String userName;

        public PostData(String userName) {
            this.userName = userName;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd.setTitle("Please wait...");
            pd.show();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            new GetData().execute(Common.getAddressAPI());
            pd.dismiss();
        }

        @Override
        protected String doInBackground(String... params) {
            String urlString=params[0];
            HTTPDataHandler hh =new HTTPDataHandler();
            String json="{\"user\":\""+userName+"\"}";
            hh.PostHttpData(urlString,json);
            return "";
        }
    }

    class PutData extends AsyncTask<String,String,String>{

        ProgressDialog pd = new ProgressDialog(MainActivity.this);
        String userName;

        public PutData(String userName) {
            this.userName = userName;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd.setTitle("Please wait...");
            pd.show();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            new GetData().execute(Common.getAddressAPI());
            pd.dismiss();
        }

        @Override
        protected String doInBackground(String... params) {
            String urlString=params[0];
            HTTPDataHandler hh =new HTTPDataHandler();
            String json="{\"user\":\""+userName+"\"}";
            hh.PutHttpData(urlString,json);
            return "";
        }
    }

    class DeleteData extends AsyncTask<String,String,String>{

        ProgressDialog pd = new ProgressDialog(MainActivity.this);
        User user;

        public DeleteData(User user) {
            this.user = user;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd.setTitle("Please wait...");
            pd.show();
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            new GetData().execute(Common.getAddressAPI());
            pd.dismiss();
        }

        @Override
        protected String doInBackground(String... params) {
            String urlString=params[0];
            HTTPDataHandler hh =new HTTPDataHandler();
            String json="{\"user\":\""+user.getUser()+"\"}";
            hh.DeleteHttpData(urlString,json);
            return "";
        }
    }
}