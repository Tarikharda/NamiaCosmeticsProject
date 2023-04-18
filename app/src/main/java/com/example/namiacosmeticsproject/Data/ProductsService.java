package com.example.namiacosmeticsproject.Data;

import android.content.Context;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.namiacosmeticsproject.Classes.ProductClass;
import com.example.namiacosmeticsproject.Classes.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ProductsService {

    private static final String URL_PRODUCTS = "https://volleyhost.000webhostapp.com/getProducts.php";
    private Context myContext;

    public ProductsService(Context myContext) {
        this.myContext = myContext;
    }

    public interface ProductsInfo {
        void getProductsArray(ArrayList<ProductClass> productsArrayList);

        void onError(String errorMessage);
    }

    public interface UserInfo {
        void getUser(User user);

        void onError(String errorMessage);
    }

    public void getProducts(ProductsInfo productsInfo) {
        ArrayList<ProductClass> getProductsArray = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(myContext);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URL_PRODUCTS, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String productId = jsonObject.getString("id");
                        String productName = jsonObject.getString("productName");
                        String productDes = jsonObject.getString("productDes");
                        float productPrice = Float.parseFloat(jsonObject.getString("productPrice"));
                        String productImgUrl = jsonObject.getString("productImgUrl");
                        String productCategory = jsonObject.getString("productCategory");
                        getProductsArray.add(new ProductClass(productId, productName, productDes, productPrice, productImgUrl, productCategory));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                productsInfo.getProductsArray(getProductsArray);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                productsInfo.onError("Failed connection :" + error);
            }
        });
        queue.add(request);
    }

    public void getProductsById(int id, ProductsInfo productsInfo) {
        ArrayList<ProductClass> getProductsArray = new ArrayList<>();
        RequestQueue queue = Volley.newRequestQueue(myContext);
        JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, URL_PRODUCTS, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject jsonObject = response.getJSONObject(i);
                        String productId = jsonObject.getString("id");
                        String productName = jsonObject.getString("productName");
                        String productDes = jsonObject.getString("productDes");
                        float productPrice = Float.parseFloat(jsonObject.getString("productPrice"));
                        String productImgUrl = jsonObject.getString("productImgUrl");
                        String productCategory = jsonObject.getString("productCategory");
                        if (id == Integer.parseInt(productId)) {
                            getProductsArray.add(new ProductClass(productId, productName, productDes, productPrice, productImgUrl, productCategory));
                            productsInfo.getProductsArray(getProductsArray);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                productsInfo.onError("Failed connection :" + error);
            }
        });
        queue.add(request);
    }

    public void getUser(String email, String password, UserInfo userInfo) {

        String url = String.format("https://volleyhost.000webhostapp.com/login_test.php?e=%s&p=%s", email, password);
//        String url = "https://volleyhost.000webhostapp.com/login_test.php?e=tarik@gmail.com&p=tarik123";
        Log.d("DEBUG", "URL: " + url);
        RequestQueue queue = Volley.newRequestQueue(myContext);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    userInfo.getUser(new User(
                            response.getString("userId"),
                            response.getString("userName"),
                            response.getString("userEmail"),
                            response.getString("userPassword"),
                            response.getString("userImgUrl")
                    ));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                userInfo.onError("Failed " + error);
            }
        });
        queue.add(request);
    }
}
