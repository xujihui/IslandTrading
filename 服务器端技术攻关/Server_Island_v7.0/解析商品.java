        /*
			解析商品
		*/
		
        * http://localhost:8080/supermarket/analysis/lookupprice?pId={PRODUCT_ID:3}
        * http://localhost:8080/supermarket/analysis/lookupprice?pName={PRODUCT_NAME:Apple}


	AsyncHttpClient asyncHttpClient = new AsyncHttpClient();
        asyncHttpClient.get(str_url,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                super.onSuccess(statusCode, headers, response);
                System.out.println("----成功object:"+response.toString());
                Toast.makeText(getContext(),response.toString(),Toast.LENGTH_SHORT).show();
                JSONObject PRODUCT = new JSONObject();
                String res = null;
                try {
                    PRODUCT = response.getJSONObject("PRODUCT");
                    res = PRODUCT.getString("res");
                    JSONObject content = PRODUCT.getJSONObject("content");
                    boolean PRODUCT_STATUS = content.getBoolean("PRODUCT_STATUS");  //获得结果为 false true，数据库存的是0  1
                    boolean PRODUCT_TOP = content.getBoolean("PRODUCT_TOP");
                    String CLASSIFY_NAME = content.getString("CLASSIFY_NAME");
                    String str_time = content.getString("PRODUCT_TIME");
                    String PRODUCT_NAME = content.getString("PRODUCT_NAME");
                    String PRODUCT_DESCRIBE = content.getString("PRODUCT_DESCRIBE");
                    String PRODUCT_SITE = content.getString("PRODUCT_SITE");
                    String PRODUCT_TYPE = content.getString("PRODUCT_TOP");
                    Date PRODUCT_TIME = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(str_time);
                    int PRODUCT_FAVOUR = content.getInt("PRODUCT_FAVOUR");
                    int PRODUCT_ID = content.getInt("PRODUCT_ID");
                    int PRODUCT_HIT = content.getInt("PRODUCT_HIT");
                    double PRODUCT_PRICE = content.getDouble("PRODUCT_PRICE");

//                    System.out.println("----PRODUCT_ID:"+PRODUCT_ID);
                    Tv.setText(res+ "\n" + PRODUCT_PRICE+"\n");
                } catch (JSONException e) {
                    e.printStackTrace();
                    Tv.setText(res+"\n" );
                    Toast.makeText(getContext(),"异常处理，没有指定商品：" ,Toast.LENGTH_SHORT).show();
                } catch (ParseException e) {
                    e.printStackTrace();
                }
            }