package jo.com.pcstores.rpos.pos.Activities;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import io.realm.Realm;
import jo.com.pcstores.rpos.R;
import jo.com.pcstores.rpos.pos.Classes.GlobalVar;
import jo.com.pcstores.rpos.pos.Classes.User;

public class LoginActivity extends AppCompatActivity {

    TextView tvPw, tvName;
   // Spinner spName;
    EditText etPw, etName;
    ImageView imgName,imgPw;
    Button btnLogin, btnRegistration;
    Switch swKeepLogged;
    Realm realm;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        realm = Realm.getDefaultInstance();
        //Control orientation depending on size
        int screenSize = getResources().getConfiguration().screenLayout &Configuration.SCREENLAYOUT_SIZE_MASK;
        if(screenSize==Configuration.SCREENLAYOUT_SIZE_XLARGE) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        }
        else{
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        }

        //HIDE ACTIONBAR
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();

        //TO FORCE KEYBOARD HIDDEN ON START
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);


        //INITIALIZE
        tvPw = findViewById(R.id.tvPw);
        tvName = findViewById(R.id.tvName);
        etPw = findViewById(R.id.etPw);
        etName = findViewById(R.id.etName);
        imgName = findViewById(R.id.imgName);
        imgPw = findViewById(R.id.imgPw);
        btnLogin = findViewById(R.id.btnLogin);
        btnRegistration = findViewById(R.id.btnRegistration);
        swKeepLogged = findViewById(R.id.swKeepLogged);

        etName.clearFocus();
        etPw.clearFocus();

        //SET ANIMATION FOR IMAGES
        //onTouch pass edittext
        etPw.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Animation a = AnimationUtils.loadAnimation(LoginActivity.this, R.anim.translatefadexy);
                imgPw.startAnimation(a);
                return false;
            }
        });

        //onTouch name edittext
        etName.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                Animation a = AnimationUtils.loadAnimation(LoginActivity.this, R.anim.translatefadexy);
                imgName.startAnimation(a);
                return false;
            }
        });

        //Clear images animation when there is no focus
        etPw.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus) {
                    imgPw.clearAnimation();
                }else{
                    Animation a = AnimationUtils.loadAnimation(LoginActivity.this, R.anim.translatefadexy);
                    imgPw.startAnimation(a);
                }
            }
        });

        etName.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if(!hasFocus) {
                    imgName.clearAnimation();
                }
            }
        });

        //hide keyboard when press the key done in keyboard
        etPw.setOnEditorActionListener(new EditText.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((actionId & EditorInfo.IME_ACTION_DONE) != 0) {
                    imgPw.clearAnimation();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
                    imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);

                    return true;
                }
                return true;
            }
        });

        //fill data to spinner (users name)
        //fillData(Config.url+"readAllUsersFromDatabase");
    }
/*

    public void fillData(String url) {
        try
        {
        final List<User> l=new ArrayList<User>();
        RequestQueue requestQueue= Volley.newRequestQueue(getApplicationContext());

        StringRequest stringRequest=new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try{
                    JSONObject jsonObject=new JSONObject(response);
                    //String result = jsonObject.getString("result");
                    // if(result.equals("1")){

                    //JSONObject o=new JSONObject(s);
                    JSONArray a=jsonObject.getJSONArray("MyUsers");

                    for(int i=0;i<a.length();i++)
                    {
                        JSONObject x=a.getJSONObject(i);
                        User u=new User();
                        u.name=x.getString("uNameInDotNet");
                        u.pass = "";
                        l.add (u);
                    }
                    // }
                  //  spName.setAdapter(new ArrayAdapter<User>(LoginActivity.this,R.layout.custom_spinner_login,R.id.Name, l));
                }catch (JSONException e){e.printStackTrace();}
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        int socketTimeout = 30000;
        RetryPolicy policy = new DefaultRetryPolicy(socketTimeout, DefaultRetryPolicy.DEFAULT_MAX_RETRIES, DefaultRetryPolicy.DEFAULT_BACKOFF_MULT);
        stringRequest.setRetryPolicy(policy);
        requestQueue.add(stringRequest);
        }
        catch (Exception ex)
        {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
*/

 /*   public void login2(View v){
        //LOGIN
         try {
//             RequestQueue queue = Volley.newRequestQueue(this);
//             String url = Config.url + "LoginWithDB";
//
//
//             StringRequest req = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//                 @Override
//                 public void onResponse(String s) {
//
//                     // Toast.makeText(getApplicationContext(),s,Toast.LENGTH_LONG).show();
//                     try {
//                         JSONObject o = new JSONObject(s);
//                         String data = o.getString("result");
//                         if (data.equals("1")) {
                             boolean keepMeLogged =swKeepLogged.isChecked();
                             if (keepMeLogged){
                                 sharedPreferences=   PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
                                 SharedPreferences.Editor pen=sharedPreferences.edit();
                                 pen.putBoolean("keep me logged",swKeepLogged.isChecked());
                                 pen.commit();
                             }
                             Intent i = new Intent(LoginActivity.this, NavMainActivity.class);
                             startActivity(i);
//                         } else {
//                             Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_LONG).show();
//                         }
//                     } catch (JSONException e) {
//                         e.printStackTrace();
//                     }
//
//                 }
//             }, new Response.ErrorListener() {
//                 @Override
//                 public void onErrorResponse(VolleyError volleyError) {
//                     String errorDescription = "";
//                     if (volleyError instanceof NetworkError) {
//                     } else if (volleyError instanceof ServerError) {
//                         errorDescription = "Server Error";
//                     } else if (volleyError instanceof AuthFailureError) {
//                         errorDescription = "AuthFailureError";
//                     } else if (volleyError instanceof ParseError) {
//                         errorDescription = "Parse Error";
//                     } else if (volleyError instanceof NoConnectionError) {
//                         errorDescription = "No Conenction";
//                     } else if (volleyError instanceof TimeoutError) {
//                         errorDescription = "Time Out";
//                     }
//                     Toast.makeText(getApplicationContext(), errorDescription, Toast.LENGTH_SHORT).show();
//                 }
//             }) {
//                 @Override
//                 protected Map<String, String> getParams() throws AuthFailureError {
//                     Map<String, String> param = new HashMap<String, String>();
//                     param.put("userName", etName.getSelectedItem().toString());
//                     param.put("password", etPw.getText().toString());
//
//                     return param;
//                 }
//             };
//             req.setRetryPolicy(new DefaultRetryPolicy(
//                     10000,
//                     DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                     DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//             queue.add(req);
         }
         catch (Exception ex)
         {
             Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
          }
    }
*/

    //onClick on login button
    public void login(View v){
        try{
            realm.beginTransaction();
            User result = realm.where(User.class).equalTo("name", etName.getText().toString().toLowerCase()).findFirst();
            if ((result == null)){
                Toast.makeText(getApplicationContext(), R.string.invalidUsernameError, Toast.LENGTH_SHORT).show();
                realm.commitTransaction();
            }else {
                if (!(result.getPass().toString().matches(etPw.getText().toString().toLowerCase()))){
                    Toast.makeText(getApplicationContext(),  R.string.invalidPwError, Toast.LENGTH_SHORT).show();
                    realm.commitTransaction();
                }else {
                    realm.commitTransaction();
                    boolean keepMeLogged = swKeepLogged.isChecked();
                    if (keepMeLogged) {
                        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
                        SharedPreferences.Editor pen = sharedPreferences.edit();
                        pen.putBoolean("keep me logged", swKeepLogged.isChecked());
                        pen.putString("cashier name", etName.getText().toString());
                        pen.commit();
                        GlobalVar.CashierName = etName.getText().toString();
                    }
                    Intent i = new Intent(LoginActivity.this, NavMainActivity.class);
                    startActivity(i);
                }
            }
        }catch (Exception ex){
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    //onClick on regestration Btn
    public void registration(View view) {

        try{
        LayoutInflater li = LayoutInflater.from(this);
        View myView = li.inflate(R.layout.fragment_registration_dialog, null);
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setView(myView);
        final EditText etName = myView.findViewById(R.id.etName);
        final EditText etPw = myView.findViewById(R.id.etPw);

            alertDialogBuilder.setTitle( R.string.Reservations);
            alertDialogBuilder.setIcon(getResources().getDrawable(R.drawable.registration));
        alertDialogBuilder
                .setPositiveButton( android.R.string.ok,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                if (etName.getText().toString().matches("") || etPw.getText().toString().matches("")){
                                    Toast.makeText(getApplicationContext(), R.string.regEnterNamePw, Toast.LENGTH_SHORT).show();
                                }else {
                                    //insert data to realm
                                    realm.beginTransaction();
                                    User data = new User();
                                    data.setId(maxID());
                                    data.setName(etName.getText().toString().toLowerCase());
                                    data.setPass(etPw.getText().toString().toLowerCase());
                                    realm.copyToRealmOrUpdate(data);
                                    realm.commitTransaction();

                                    //add username to shared preference
                                    sharedPreferences = PreferenceManager.getDefaultSharedPreferences(LoginActivity.this);
                                    SharedPreferences.Editor pen = sharedPreferences.edit();
                                    pen.putString("cashier name", etName.getText().toString());
                                    pen.commit();
                                    GlobalVar.CashierName = etName.getText().toString();
                                    Toast.makeText(getApplicationContext(), R.string.userSuccess, Toast.LENGTH_SHORT).show();

                                    //show the main activity
                                    Intent i = new Intent(LoginActivity.this,NavMainActivity.class);
                                    startActivity(i);
                                }
                            }
                        })
                .setNegativeButton(android.R.string.cancel,
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog,int id) {
                                dialog.cancel();
                            }
                        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
        }catch (Exception ex){
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    public Integer maxID(){

        Number id = realm.where(User.class).max("id");
        // If id is null, set it to 1, else set increment it by 1
        int nextId = (id == null) ? 1 : id.intValue() + 1;

        return nextId;
    }

    }
